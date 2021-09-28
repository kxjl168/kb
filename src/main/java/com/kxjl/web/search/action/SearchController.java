package com.kxjl.web.search.action;

import static com.google.common.collect.FluentIterable.from;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.http.HttpHeaders.CONTENT_LENGTH;
import static org.apache.http.HttpHeaders.HOST;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.HeaderGroup;
import org.apache.http.util.EntityUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.gson.Gson;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.prerenderio.PrerenderConfig;
import com.kxjl.tool.prerenderio.PrerenderSeoService;
import com.kxjl.tool.sendmail.MailUtil;
import com.kxjl.tool.utils.AESEncryptUtil;
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.tool.utils.UUIDUtil;
import com.kxjl.web.autodata.pojo.SearchKeys;
import com.kxjl.web.autodata.service.SearchKeysService;
import com.kxjl.web.device.model.Device;
import com.kxjl.web.device.service.DeviceService;
import com.kxjl.web.stastic.model.ActionLog.StasticTypeOne;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;

@Controller
@RequestMapping(value = "/search")
public class SearchController extends BaseController {

	@Autowired
	DeviceService deviceService;

	@Autowired
	SearchKeysService searchKeysService;

	private final static Logger log = LoggerFactory.getLogger(SearchController.class);

	@RequestMapping(value = "/banner/bannerList")
	public ModelAndView GroupList() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/banner/banner");

		return view;
	}

	//express ,香港服务器本地-》prerender
	String expressurl = "http://google.256kb.cn:3002/";
	//香港服务器prerender
	String prerenderurl = "http://google.256kb.cn:13333/";
	// String searengin = "https://www.baidu.com";
	String searengin = "https://www.baidu.com";

	/**
	 * 处理翻页或者直接网页访问
	 * @param url
	 * @return
	 * @author:kxjl
	 * @date 2021年7月2日
	 */
	private String geturl(String url) {
		String desturl = "";
		if (url.startsWith("htt")) {

			
			//加密 url 防墙！
			//AESEncryptUtil.crypt(content, key)
			String aesurl=AESEncryptUtil.cryptWidthNode(url);
			desturl = expressurl +"/gurl?q="+ aesurl;
		} else //翻页
			desturl = expressurl  + url;

		return desturl;
	}

	private String getFullUrl(HttpServletRequest request) {
		final String url = request.getRequestURL().toString();
		final String queryString = request.getQueryString();
		return isNotBlank(queryString) ? String.format("%s?%s", url, queryString) : url;
	}

	private static final HeaderGroup hopByHopHeaders;
	static {
		hopByHopHeaders = new HeaderGroup();
		String[] headers = new String[] { "Connection", "Keep-Alive", "Proxy-Authenticate", "Proxy-Authorization", "TE",
				"Trailers", "Transfer-Encoding", "Upgrade" };
		for (String header : headers) {
			hopByHopHeaders.addHeader(new BasicHeader(header, null));
		}

	}
	private CloseableHttpClient httpClient;

	private String getContentCharSet(final HttpEntity entity) throws ParseException {
		if (entity == null) {
			return null;
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}
		return charset;
	}

	private void copyResponseHeaders(HttpResponse proxyResponse, final HttpServletResponse servletResponse) {
		servletResponse.setCharacterEncoding(getContentCharSet(proxyResponse.getEntity()));
		from(Arrays.asList(proxyResponse.getAllHeaders())).filter(new Predicate<Header>() {
			@Override
			public boolean apply(Header header) {
				return !hopByHopHeaders.containsHeader(header.getName());
			}
		}).transform(new Function<Header, Boolean>() {
			@Override
			public Boolean apply(Header header) {
				servletResponse.addHeader(header.getName(), header.getValue());
				return true;
			}
		}).toList();
	}

	private void copyRequestHeaders(HttpServletRequest servletRequest, HttpRequest proxyRequest)
			throws URISyntaxException {
		// Get an Enumeration of all of the header names sent by the client
		Enumeration<?> enumerationOfHeaderNames = servletRequest.getHeaderNames();
		while (enumerationOfHeaderNames.hasMoreElements()) {
			String headerName = (String) enumerationOfHeaderNames.nextElement();
			// Instead the content-length is effectively set via
			// InputStreamEntity
			if (!headerName.equalsIgnoreCase(CONTENT_LENGTH) && !hopByHopHeaders.containsHeader(headerName)) {
				Enumeration<?> headers = servletRequest.getHeaders(headerName);
				while (headers.hasMoreElements()) {// sometimes more than one
													// value
					String headerValue = (String) headers.nextElement();
					// In case the proxy host is running multiple virtual
					// servers,
					// rewrite the Host header to ensure that we get content
					// from
					// the correct virtual server
					/*
					 * if (headerName.equalsIgnoreCase(HOST)) { HttpHost host =
					 * URIUtils.extractHost(new URI( prerenderConfig.getPrerenderServiceUrl()));
					 * headerValue = host.getHostName(); if (host.getPort() != -1) { headerValue +=
					 * ":" + host.getPort(); } }
					 */
					proxyRequest.addHeader(headerName, headerValue);
				}
			}
		}
	}

	public CloseableHttpClient getHttpClient() {
		HttpClientBuilder builder = HttpClients.custom()
				.setConnectionManager(new PoolingHttpClientConnectionManager())
				.disableAutomaticRetries()
				
			.setMaxConnTotal(500).setMaxConnPerRoute(10); 

		return builder.build();
	}

	private String proxyPrerenderedPageResponse(String url, HttpServletRequest request, HttpServletResponse response) {

		String html = "";
		CloseableHttpResponse prerenderServerResponse = null;
		try {
			final String apiUrl = url;// getFullUrl(request);
			log.info(String.format(" send request to:%s", apiUrl));
			HttpGet getMethod = new HttpGet(apiUrl);
			
			
			   RequestConfig defaultRequestConfig = RequestConfig.custom()
	                     .setSocketTimeout(60000)
	                     .setConnectTimeout(60000)
	                     
	                     
	                     .setConnectionRequestTimeout(60000)
	                     .build();
			   
			   getMethod.setConfig(defaultRequestConfig);
	
			   getMethod.setHeader("accept", "*/*");
			  // getMethod.setHeader("Connection", "close");
			  
			// HttpPost getMethod = new HttpPost(apiUrl);
			// copyRequestHeaders(request, getMethod);
			// withPrerenderToken(getMethod);
			   
		

			prerenderServerResponse = httpClient.execute(getMethod);
			response.setStatus(prerenderServerResponse.getStatusLine().getStatusCode());
			// copyResponseHeaders(prerenderServerResponse, response);

			int status = prerenderServerResponse.getStatusLine().getStatusCode();
			log.trace("status:" + status);
			if (status == 307 || status == 302 || status == 301 || status == 303) {
				String redirectURl = "";
				for (Header head : prerenderServerResponse.getAllHeaders()) {
					if (head.getName().equals("Location")||head.getName().equals("location")) {
						redirectURl = head.getValue();
						break;
					}
				}

				String desturl = geturl(redirectURl);
				closeQuietly(prerenderServerResponse);
				return proxyPrerenderedPageResponse(desturl, request, response);

			} else
			{
				html = getResponseHtml(prerenderServerResponse);
				
				HttpEntity resEntity = prerenderServerResponse.getEntity();
				EntityUtils.consume(resEntity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			// responseEntity(html, response);
			// return true;
		} finally {
			closeQuietly(prerenderServerResponse);
		}
		return html;
	}

	private void responseEntity(String html, HttpServletResponse servletResponse) throws IOException {
		PrintWriter printWriter = servletResponse.getWriter();
		try {
			printWriter.write(html);
			printWriter.flush();
		} finally {
			closeQuietly(printWriter);
		}
	}

	private String getResponseHtml(HttpResponse proxyResponse) throws IOException {
		HttpEntity entity = proxyResponse.getEntity();
		
		
		
		return entity != null ? EntityUtils.toString(entity) : "";
	}

	protected void closeQuietly(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			log.error("Close proxy error", e);
		}
	}

	@RequestMapping(value = "/init")
	public void init(HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			saveStaticInfo(request, StasticTypeOne.GSearch.getDesc(), "");

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");

		} catch (Exception e) {

		}

		rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);
	}
	
	
	/**
	 * 申请google search key
	 * @param request
	 * @param response
	 * @author zj
	 * @date 2020年1月15日
	 */
	@RequestMapping(value = "/applyKey")
	public void applyKey(HttpServletRequest request, HttpServletResponse response)
	{
	/*	String email=request.getParameter("email");
		String txt=request.getParameter("context");
		String nickname=request.getParameter("nickname");
		*/
		String email = parseStringParam(request, "email");
		String txt = parseStringParam(request, "context");
		String nickname = parseStringParam(request, "nickname");
		
		SearchKeys skey=new SearchKeys();
		skey.setEmail(email);
		skey.setContext(JEscape.unescape(txt));
		skey.setNickname(nickname);
		skey.setGkey(UUIDUtil.getUUID());
		skey.setContext2("感谢您的申请");
		searchKeysService.saveSearchKeys(skey);
		
		//email
		emailNotify(skey);
		
		JSONObject jsonOut = new JSONObject();
		jsonOut.put("ResponseCode", "200");
		jsonOut.put("ResponseMsg", "感谢您的申请,请耐心等待...");
		String rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}
	private String xssEncode(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			/*
			 * case '\'': sb.append('‘');// 全角单引号 break; case '\"': sb.append('“');// 全角双引号
			 * break; case '&': sb.append('＆');// 全角 break; case '\\': sb.append('＼');//
			 * 全角斜线 break; case '#': sb.append('＃');// 全角井号 break;
			 */
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	private void emailNotify(SearchKeys skey) {
		// 访客留言
	
		final String title = "KxのBook [新的Google试用Key申请]";
		
		String ucontent = xssEncode(JEscape.unescape(skey.getContext()));
		// final String message = "From " + uid + ":\r\n<br>" + ucontent + "";
		final String recvmail = ConfigReader.getInstance().getProperty("AdminMail");

		final String nickname="喵主子";
		final String action="喵主子:<br>"+skey.getNickname()+"/"+"["+skey.getEmail()+"] 申请了 Google search的使用key!";

		new Thread(new Runnable() {

			@Override
			public void run() {
				
				//MailUtil.sendMail(recvmail, title, message);
				
				MailUtil.sendMailCode(recvmail, title,action);
				
			}
		}).start();
	}
	
	
	/**
	 * 除google外的搜索
	 * @param engin
	 * @param data
	 * @param key
	 * @param url
	 * @param request
	 * @param response
	 * @return
	 * @author:kxjl
	 * @date 2020年10月15日
	 */
	public String normalrealSearch(String engin,String data,String key,String url,HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonOut = new JSONObject();
		String rst = "";
		try {

			
			// searengin="https://www.google.co.jp";
			 String searengin = "https://www.baidu.com";

			 searengin = ConfigReader.getInstance().getProperty("searchEngin", searengin);
			 
			 if(engin!=null&&!engin.equals(""))
			 {
				 //参数输入
				 searengin=engin;
			 }

			 prerenderurl = ConfigReader.getInstance().getProperty("prerenderurl", prerenderurl);

			httpClient = getHttpClient();
			String desturl = "";
			if (data != null && !data.equals("")) {
				if (searengin.contains("baidu.com"))
					desturl = prerenderurl + searengin + "/s?ie=utf-8&wd=" + URLEncoder.encode(data, "utf-8");
				
					

				// 记录搜索关键词
				saveStaticInfo(request, StasticTypeOne.GSearch.getDesc(), "", data+" kg blog search prerender ");

			
			}

			if (!desturl.equals("")) {
				String htmldata = proxyPrerenderedPageResponse(desturl, request, response);

				//200204 html前缀问题
				if(htmldata.startsWith("html"))
				{
					htmldata=htmldata.substring(4);
				}

				jsonOut.put("ResponseCode", "200");
				jsonOut.put("ResponseMsg", "");

				jsonOut.put("html", htmldata);
			} else {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "请输入查询关键字");

			}

		} catch (Exception e) {

		} finally {
			closeQuietly(httpClient);
		}
		rst = jsonOut.toString();
		return rst;
	}
	
	
	/**
	 * 
	 * @param engin
	 * @param data
	 * @param key
	 * @param url
	 * @param request
	 * @param response
	 * @return
	 * @author:kxjl
	 * @date 2020年10月15日
	 */
	public String realSearch(String engin,String data,String key,String url,HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonOut = new JSONObject();
		String rst = "";
		try {

			
			// searengin="https://www.google.co.jp";
			 String searengin = "https://www.google.com.hk";

			 searengin = ConfigReader.getInstance().getProperty("searchEngin", searengin);
			 
			 if(engin!=null&&!engin.equals(""))
			 {
				 //参数输入
				 searengin=engin;
			 }
			 
			// 监测是否登录
				// 190728
				int cansearch = checkCansearch(request,key);
				if (cansearch<0) {
					jsonOut.put("ResponseCode", "201");
					if(cansearch==-1)
					jsonOut.put("ResponseMsg", "非常抱歉,鉴于多方考虑,本站搜索已关闭访客使用.");
					if(cansearch==-2)
					jsonOut.put("ResponseMsg", "非常抱歉,您的授权码无效.");
					if(cansearch==-3)
						jsonOut.put("ResponseMsg", "非常抱歉,您的授权码已达使用上限.");
					rst = jsonOut.toString();
					JsonUtil.responseOutWithJson(response, rst);
					return "";
				}
			 
			 

			expressurl = ConfigReader.getInstance().getProperty("preurl", expressurl);

			httpClient = getHttpClient();
			String desturl = "";
			if (data != null && !data.equals("")) {
				if (searengin.contains("baidu.com"))
					desturl = expressurl + searengin + "/s?ie=utf-8&wd=" + URLEncoder.encode(data, "utf-8");
				else if (searengin.contains("google"))
					desturl = expressurl + searengin + "/search?q=" + URLEncoder.encode(data, "utf-8");
				else //请求 后台 node express ->服务器本地3002/search prerender google
				{
					String aeskeystr=AESEncryptUtil.cryptWidthNode( URLEncoder.encode(data, "utf-8"));
					desturl = expressurl  + "/search?q=" +aeskeystr;
				}
					

				// 记录搜索关键词
				saveStaticInfo(request, StasticTypeOne.GSearch.getDesc(), "", data+" key:"+key);

				if(engin==null||engin.equals(""))
				{//默认检查
					
				
				}

			} else if (url != null && !url.equals("")) {
				//后续 翻页链接
				// 替换自动附加的本站链接前缀
				url = url.replace(ConfigReader.getInstance().getProperty("domainSearch", request.getHeader("Host")), "");
				
				//
				
				desturl = geturl(url);
			}

			if (!desturl.equals("")) {
				String htmldata = proxyPrerenderedPageResponse(desturl, request, response);

				//200204 html前缀问题
				if(htmldata.startsWith("html"))
				{
					htmldata=htmldata.substring(4);
				}
				
				refreshCansearchTime(request,key);
				
				jsonOut.put("ResponseCode", "200");
				jsonOut.put("ResponseMsg", "");

				jsonOut.put("html", htmldata);
			} else {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "请输入查询关键字");

			}

		} catch (Exception e) {

		} finally {
			closeQuietly(httpClient);
		}
		rst = jsonOut.toString();
		return rst;
	}

	/**
	 * 页面-获取banner列表
	 * 
	 * @param clientType
	 * @param packageName
	 * @param curPage
	 *            当前页
	 * @param pageCount
	 *            每页多少条
	 * @return
	 */
	@RequestMapping(value = "/dosearch")
	public void dosearch(HttpServletRequest request, HttpServletResponse response) {

		// String deviceid = request.getParameter("deviceid");

		/*
		 * int pageCount = Integer.parseInt(request.getParameter("rows"));//
		 * request.getParameter("pageCount"); int curPage =
		 * Integer.parseInt(request.getParameter("page"));
		 */

		String data = parseStringParam(request, "keyword");
		String url = parseStringParam(request, "url");
		String key= parseStringParam(request, "gkey");
	
		String searchdata=realSearch("", data, key, url, request, response);
		
		JsonUtil.responseOutWithJson(response, searchdata);

	}

	/**
	 * 计算key可用次数
	 * @param request
	 * @return
	 * @author zj
	 * @date 2020年1月15日
	 */
	public void refreshCansearchTime(HttpServletRequest request,String key)
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpSession session = request.getSession();
				//String key=request.getParameter("gkey");
				
				SysUserBean user = (SysUserBean) session.getAttribute(Constant.SESSION_USER);
				if(user!=null)
				 {
					if (user.getUtype() == UserType.Root || user.getUtype() == UserType.Admin) {
						
					}
					else 
					{
					
						//20200115 增加授权码检测
						SearchKeys skey= searchKeysService.selectSearchKeysByKey(key);
						if(skey!=null&& Integer.parseInt(skey.getUseTime())>=0)
						{
							searchKeysService.reduceUseTimeByKey(key);
							
							//return null; //-1 永久使用  >0可用次数
						}
						
						// null;
					}
				}

			}
		}).start();
		
	
	}

	/**
	 * 检查是否可以搜索
	 * @param request
	 * @param key
	 * @return -1 关闭访客搜索提示  -2 授权码错误  ,-3授权码过期1 正常
	 * @author zj
	 * @date 2020年1月16日
	 */
	public int checkCansearch(HttpServletRequest request,String key) {
		HttpSession session = request.getSession();
		SysUserBean user = (SysUserBean) session.getAttribute(Constant.SESSION_USER);
		if (user == null)
			return -1;
		else {
			if (user.getUtype() == UserType.Root || user.getUtype() == UserType.Admin) {
				return 1;
			} else {
				//String key = request.getParameter("gkey");
				if (key == null || key.equals(""))
					return -1;
				// 20200115 增加授权码检测
				SearchKeys skey = searchKeysService.selectSearchKeysByKey(key);
				if(skey==null)
					return -2;
				if (skey != null && (Integer.parseInt(skey.getUseTime()) > 0 ||Integer.parseInt(skey.getUseTime()) == -1) ) {
					return 1; // -1 永久使用 >0可用次数
				}
				else {
					return -3;
				}

			}
		}

	}
}
