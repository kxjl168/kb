package com.kxjl.web.blog.action;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.http.HttpHeaders.CONTENT_LENGTH;
import static org.apache.http.HttpHeaders.HOST;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.HeaderGroup;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.config.TxNamespaceHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kxjl.tool.common.Config;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.config.configWarthDog;
import com.kxjl.tool.html.FileProcessor;
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;

import com.kxjl.tool.utils.wuliu.WuliuHelper;
import com.kxjl.web.autodata.dao.LikeInfoMapper;
import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.BlogService;
import com.kxjl.web.blog.service.KurlService;
import com.kxjl.web.stastic.model.ActionLog.StasticTypeOne;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;
import com.kxjl.web.system.service.MenuInfoService;
import com.kxjl.web.system.service.SysService;
import com.sun.glass.ui.View;
import com.sun.org.apache.xalan.internal.xsltc.dom.KeyIndex.KeyIndexIterator;

import sun.util.logging.resources.logging;

@Controller
@RequestMapping(value = "/")
public class PublicController extends BaseController {
	private Logger logger = Logger.getLogger(PublicController.class);

	@Autowired
	KurlService kurlService;

	@Autowired
	SysService sysService;

	@Autowired
	MenuInfoService menuService;

	@Autowired
	WuliuHelper wuliuHelper;

	@Autowired
	BlogService bservice;

	@Autowired
	LikeInfoMapper likemaper;

	@RequestMapping("/public/wuliu")
	public String wuliu(Map<String, Object> map) {

		return "/public/wuliu/main";
	}

	@RequestMapping("/public/wuliuinfo")

	public void wuliuinfo(String id, HttpServletResponse response) {

		String jStr = wuliuHelper.getWuliuInfo(id);

		JsonUtil.responseOutWithJson(response, jStr);
	}

	@RequestMapping(value = "/google{value}.html")
	public String listhtml(HttpServletRequest request, @PathVariable("value") String value) {

		return "google" + value;
	}

	@RequestMapping(value = "/public/index/{type}/{value}.html")
	public String listhtml(HttpServletRequest request, @PathVariable("type") String type,
			@PathVariable("value") String value) {

		return "forward:/public/index/?" + type + "=" + value;
	}

	@RequestMapping(value = "/public/index")
	public ModelAndView list(HttpServletRequest request) {

		ModelAndView view = getSysData();
		view.setViewName("/public/index/main");

		Enumeration paras = request.getParameterNames();
		while (paras.hasMoreElements()) {

			String v = (String) paras.nextElement();

			view.addObject(v, request.getParameter(v));

		}

		saveStaticInfo(request, StasticTypeOne.HomePage.toString(), "index");

		return view;
	}

	@RequestMapping(value = "/public/about")
	public ModelAndView about(HttpServletRequest request) {

		ModelAndView view = getSysData();
		view.setViewName("/public/about/main");

		saveStaticInfo(request, StasticTypeOne.AboutPage.toString(), "");

		return view;
	}

	@RequestMapping(value = "/public/bx")
	public ModelAndView bx(HttpServletRequest request) {

		ModelAndView view = getSysData();
		view.setViewName("/public/bx/main");

		return view;
	}

	private ModelAndView getSysData() {
		ModelAndView view = new ModelAndView();

		Map jsInfo = sysService.getSysInfo();

		view.addObject("httppath", jsInfo.get("httppath"));
		view.addObject("head", jsInfo.get("fileinfo"));
		view.addObject("sign", jsInfo.get("sign"));
		view.addObject("visitdata", jsInfo.get("visitData"));
		return view;
	}

	@RequestMapping(value = "/")
	public String index3() {

		ModelAndView view = getSysData();
		view.setViewName("/public/index/");

		return "redirect:/public/index";
	}

	@RequestMapping(value = "/welcome")
	public String index() {
		ModelAndView view = getSysData();
		view.setViewName("/public/index/");

		return "redirect:/public/index/";
	}

	@RequestMapping(value = "/public/share")
	public ModelAndView share() {

		ModelAndView view = getSysData();
		view.setViewName("/public/share/main");

		return view;
	}

	@RequestMapping(value = "/public/search")
	public String search() {
		ModelAndView view = getSysData();
		view.setViewName("/public/index/");

		return "/public/search/main";
	}

	@RequestMapping(value = "/page/set")
	public String p_set(Map<String, Object> maps, HttpSession session, HttpServletRequest request) {

		maps.putAll(sysService.getSysInfo());
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(session, request);

		maps.put("menus", leftmenus);
		return "/page/set/main";
	}

	@RequestMapping(value = "/page/portal")
	public String p_portal(Map<String, Object> maps, HttpSession session, HttpServletRequest request) {

		maps.putAll(sysService.getSysInfo());
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(session, request);

		maps.put("menus", leftmenus);
		return "/page/stastic/portal";
	}

	@RequestMapping(value = "/page/todo")
	public String p_todo(Map<String, Object> maps, HttpSession session, HttpServletRequest request) {

		maps.putAll(sysService.getSysInfo());
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(session, request);

		maps.put("menus", leftmenus);
		return "forward:/todo/index";
	}

	@RequestMapping(value = "/page/blog/detail")
	public String p_detail_edit(Map<String, Object> maps, HttpSession session, HttpServletRequest request) {

		maps.putAll(sysService.getSysInfo());
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(session, request);

		maps.put("menus", leftmenus);
		return "/page/blog/detail";
	}

	@RequestMapping(value = "/page/yqurl")
	public String yqurl(Map<String, Object> maps, HttpSession session, HttpServletRequest request) {

		maps.putAll(sysService.getSysInfo());
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(session, request);

		maps.put("menus", leftmenus);
		return "/page/burl/index_list";
	}
	
	
	@RequestMapping(value = "/page/burl")
	public String p_brul(Map<String, Object> maps, HttpSession session, HttpServletRequest request) {

		//maps.putAll(sysService.getSysInfo());

		//List<MenuInfo> leftmenus = menuService.getLeftMenuTree(session, request);
		
		setLeftUrls(maps);
		return "/page/burl/index";
	}
	
	private void setLeftUrls(Map<String, Object> maps) {

		Kurl query = new Kurl();
		query.setPage(1);
		query.setPageCount(100);
		query.setVal1("1");

		query.setUrl_name("");// (url_name);
		Map<String, List<Kurl>> datas=kurlService.getKurlItemPageList(query);

		List<MenuInfo> urls=new ArrayList<>();
		for (List<Kurl> item : datas.values()) {
			MenuInfo urlmenu=new MenuInfo();
			urlmenu.setMenuName(	item.get(0).getUrl_type());
			
			urls.add(urlmenu);
			
		}
		
		
		maps.put("menus", urls);

	}
	
	@RequestMapping(value = "/pown/url/")
	public String p_ourl(Map<String, Object> maps, HttpSession session, HttpServletRequest request) {

		//maps.putAll(sysService.getSysInfo());

		//List<MenuInfo> leftmenus = menuService.getLeftMenuTree(session, request);
		
		setLeftUrls(maps);
		return "/pown/url/main";
	}
	

	@RequestMapping(value = "/page/{url}")
	public String p_btype(Map<String, Object> maps, HttpSession session, HttpServletRequest request,
			@PathVariable(name = "url") String url) {

		maps.putAll(sysService.getSysInfo());

		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(session, request);

		maps.put("menus", leftmenus);

		return "/page/" + url + "/index";
	}

	@RequestMapping(value = "/pown/{url}")
	public String pown_btype(Map<String, Object> maps, @PathVariable(name = "url") String url) {

		maps.putAll(sysService.getSysInfo());

		return "/pown/" + url + "/main";
	}

	@RequestMapping(value = "/public/cat/")
	public String cat() {
		ModelAndView view = getSysData();
		view.setViewName("/public/index/");

		return "/public/cat/main";
	}

	/**
	 * 带上真实的请求地址
	 * 
	 * @param proxyRequest
	 * @param request
	 * @author zj
	 * @date 2018年9月28日
	 */
	private void widthRealIp(HttpRequest proxyRequest, HttpServletRequest request) {

		proxyRequest.addHeader("X-Prerender-Token", stasticService.getIpAddr(request));
		// 补充原始地址

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

					proxyRequest.addHeader(headerName, headerValue);
				}
			}
		}
	}

	/**
	 * 生成html静态页面
	 * 
	 * @param request
	 * @param detailurl
	 * @param localFilePath
	 * @param htmlName
	 * @author zj
	 * @date 2018年10月15日
	 */
	public void generateHtml(HttpServletRequest request, String detailurl, String localFilePath, String htmlName) {

		final HttpGet getMethod = new HttpGet(detailurl);
		try {
			copyRequestHeaders(request, getMethod);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		widthRealIp(getMethod, request);
		CloseableHttpResponse prerenderServerResponse = null;
		HttpClientBuilder builder = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager())
				.disableRedirectHandling();
		CloseableHttpClient httpClinet = builder.build();
		try {
			prerenderServerResponse = httpClinet.execute(getMethod);

			HttpEntity entity = prerenderServerResponse.getEntity();
			String html = (entity != null) ? EntityUtils.toString(entity) : "";
			String fpath = FileProcessor.writeFile(html, localFilePath, htmlName);
			logger.info("write file done:" + fpath);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				if (prerenderServerResponse != null) {
					prerenderServerResponse.close();
				}
			} catch (IOException e) {
				logger.error("Close proxy error", e);
			}
		}

	}
	/**
	 * 读取并返回静态html
	 * @param localFile
	 * @param response
	 * @author zj
	 * @date 2018年10月15日
	 */
	public void responseHtml(File localFile,HttpServletResponse response) {
		try {

			Scanner sc = new Scanner(localFile);
			StringBuffer sb = new StringBuffer();

			while (sc.hasNextLine()) {
				String filetxt = sc.nextLine();
				sb.append("\r\n");
				sb.append(filetxt);
			}

			sc.close();
			String htmldata= sb.toString();
			response.setHeader("content-type", "text/html; charset=UTF-8");
			response.setHeader("Content-Length", "" + htmldata.getBytes("UTF-8").length);
			response.setCharacterEncoding("UTF-8");
			
			
			response.getWriter().write(htmldata);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/public/html/{year}/{month}/{imei}.html")
	public void htmldetail(HttpServletRequest request, HttpServletResponse response, @PathVariable("year") String year,
			@PathVariable("month") String month, @PathVariable("imei") String imei) {

		String localPath = ConfigReader.getInstance().getProperty("LOCAL_HTML_PATH",
				"F:\\kxjl\\code\\kb\\WebContent\\public/html/");

		// String month = DateUtil.getNowStr("yyyy/MM");

		String htmlPath = request.getContextPath() + "/" + "public/html/" + year + "/" + month + "/" + imei + ".html";

		String localFilePath = localPath + year + "/" + month + "/";
		String htmlName = imei + ".html";

		File localFile = new File(localFilePath + htmlName);
		if (!(localFile.exists())) {

			String domain=ConfigReader.getInstance().getProperty("domain","http://www.256kb.cn");
			
			String detailurl = domain + request.getContextPath() + "/public/detail/?i=" + imei;

			//第一次访问信息记录在/detail?i=xxx中
			generateHtml(request, detailurl, localFilePath, htmlName);

			responseHtml(localFile,response);
			
			/*try {
				request.getRequestDispatcher("/public/detail/?i=" + imei).forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

		} else {

			Blog bq = new Blog();
			bq.setImei(imei);
			Blog detailitem = bservice.getBlogInfoById(bq);

			//后续静态页面的访问信息记录
			saveVisitInfo(detailitem, request);

			responseHtml(localFile,response);

			// return "/public/html/"+year+"/"+month+"/"+imei;
		}
	}

	@RequestMapping(value = "/public/detail")
	public ModelAndView detail(HttpServletRequest request) {
		ModelAndView view = getSysData();
		try {

			String imei = request.getParameter("i");
			if (imei.equals("null") || imei.equals("")) {
				String url = request.getHeader("Referer");
				try {
					imei = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
				} catch (Exception e) {
					// TODO: handle exception
				}

				// http://127.0.0.1:8080/kb/public/detail/i/344f5834-fe93-49e8-835d-b07e1a1def96.html
			}

			Blog query = new Blog();
			query.setImei(imei);

			Blog detailitem = bservice.getBlogInfoById(query);

			// cur ,next, pre
			List<Blog> details = bservice.getBlogDetailPageList(query);

			String prepath = getImgHttpOutPath();
			for (Blog blog : details) {
				blog.setBlog_type_url(prepath + blog.getBlog_type_url());
			}

			details.get(0).setContent(JEscape.unescape(details.get(0).getContent()));

			view.addObject("preurl", ConfigReader.getInstance().getProperty("domain", "http://www.256kb.cn"));

			view.addObject("curBlog", details.get(0));

			if (details.size() > 1)
				view.addObject("nextBlog", details.get(1));
			if (details.size() > 2)
				view.addObject("preBlog", details.get(2));

			int total = likemaper.getTotalLikeNum(imei);
			view.addObject("goodnum", total);

			Page<Object> pg = PageHelper.startPage(1, 10);
			List<Blog> relatedBLogs = bservice.getRelatedBlogs(detailitem);

			for (Blog blog : relatedBLogs) {
				blog.setBlog_type_url(prepath + blog.getBlog_type_url());
			}

			view.addObject("relatedBLogs", relatedBLogs);

			view.addObject("tplist", bservice.getBlogTypeGroups());
			view.addObject("hlist", bservice.getBlogMonthGroup());
			view.addObject("tglist", bservice.getBlogTags());

			Kurl kquery = new Kurl();
			kquery.setPage(1);
			kquery.setPageCount(100);
			kquery.setVal1("2");
			view.addObject("yqlist", kurlService.getKurlPageList(kquery));

			saveVisitInfo(detailitem, request);

		} catch (Exception e) {
			// TODO: handle exception
		}

		view.setViewName("/public/detail/main");

		return view;
	}

	private void saveVisitInfo(Blog blog, HttpServletRequest request) {
		// 计数
		SysUserBean user = (SysUserBean) request.getSession().getAttribute(Constant.SESSION_USER);

		// 无用户信息，爬虫
		boolean isspider = false;
		if (user == null)
			isspider = true;

		stasticService.saveStaticInfo(request, StasticTypeOne.DetailPage.toString(), blog.getBlog_type_name(),
				blog.getImei(), isspider);

		Blog query = new Blog();
		query.setImei(blog.getImei());
		if (user == null || (user.getUtype() != UserType.Root && user.getUtype() != UserType.Admin)) {
			bservice.updateBlogReadTime(query);
			Kdata.getInstance().cleanrBLogList("");
		}
	}

	/**
	 * angularjs 页面加载方式
	 * 
	 * @param request
	 * @return
	 * @author zj
	 * @date 2018年9月12日
	 */
	@RequestMapping(value = "/public/detailo")
	public ModelAndView detailo(HttpServletRequest request) {
		ModelAndView view = getSysData();

		String imei = request.getParameter("i");
		if (imei.equals("null") || imei.equals("")) {
			String url = request.getHeader("Referer");
			try {
				imei = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
			} catch (Exception e) {
				// TODO: handle exception
			}

			// http://127.0.0.1:8080/kb/public/detail/i/344f5834-fe93-49e8-835d-b07e1a1def96.html
		}

		Blog query = new Blog();
		query.setImei(imei);

		Blog detailitem = bservice.getBlogInfoById(query);

		// 记录直接访问html的日志，多为爬虫访问链接，页面js统计未执行.
		stasticService.saveStaticInfo(request, StasticTypeOne.DetailPage.toString(), detailitem.getBlog_type_name(),
				imei);

		view.setViewName("/public/detail/main_angular");

		return view;
	}

	@RequestMapping(value = "/sitemap.xml")
	@ResponseBody
	public void sitemap(HttpServletRequest request, HttpServletResponse response) {

		/*
		 * <urlset xmlns=“网页列表地址”> <url> <loc>网址</loc>
		 * <lastmod>2005-06-03T04:20-08:00</lastmod> <changefreq>always</changefreq>
		 * <priority>1.0</priority> </url> <url> <loc>网址</loc>
		 * <lastmod>2005-06-02T20:20:36Z</lastmod> <changefreq>daily</changefreq>
		 * <priority>0.8</priority> </url> </urlset>
		 */

		String domain = ConfigReader.getInstance().getProperty("domain", "http://www.256kb.cn");

		Blog query = new Blog();
		query.setPageCount(2000000);
		query.setShowflag("1");
		List<Blog> blogs = bservice.getBlogPageList(query);

		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");

		for (Blog blog : blogs) {
			// /public/html/${curBlog.showdate}/${curBlog.imei}.html
			sb.append("<url>");
			sb.append("<loc>" + domain + "/public/html/" + blog.getShowdate() + "/" + blog.getImei() + ".html</loc>");

			String date = blog.getUpdate_date();
			if (date == null)
				date = blog.getCreate_date();

			date = date.substring(0, 10);

			sb.append("<lastmod>" + date + "</lastmod>");
			sb.append("<changefreq>always</changefreq>");
			sb.append("<priority>1.0</priority>");
			sb.append("</url>");

		}
		sb.append("</urlset>");
		// return sb.toString();

		/*
		 * try {
		 * 
		 * response.setCharacterEncoding("UTF-8");
		 * response.setContentType("application/octet-stream");
		 * response.setHeader("Content-Disposition",
		 * "attachment;filename=sitemap.xml");// mod // by // pengqp // at // try {
		 * 
		 * // 2012/8/29 // 下载文件乱码 ServletOutputStream out = response.getOutputStream();
		 * 
		 * out.write(sb.toString().getBytes("UTF-8")); out.flush(); out.close(); } catch
		 * (Exception e) { // TODO: handle exception
		 * 
		 * }
		 */

		try {

			response.setHeader("content-type", "application/xml; charset=UTF-8");
			response.setHeader("Content-Length", "" + sb.toString().getBytes("UTF-8").length);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(sb.toString());
			response.getWriter().flush();
		} catch (Exception e) { // TODO: handle exception }

		}
	}

}
