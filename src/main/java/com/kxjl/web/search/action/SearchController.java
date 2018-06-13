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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.prerenderio.PrerenderConfig;
import com.kxjl.tool.prerenderio.PrerenderSeoService;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.device.model.Device;
import com.kxjl.web.device.service.DeviceService;
import com.kxjl.web.stastic.model.ActionLog.StasticTypeOne;
import com.kxjl.web.system.action.base.BaseController;

@Controller
@RequestMapping(value = "/search")
public class SearchController extends BaseController {

	@Autowired
	DeviceService deviceService;
	private final static Logger log = LoggerFactory
			.getLogger(SearchController.class);

	@RequestMapping(value = "/banner/bannerList")
	public ModelAndView GroupList() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/banner/banner");

		return view;
	}

	String preurl = "http://256kb.cn:13333/";
	// String searengin = "https://www.baidu.com";
	String searengin = "https://www.baidu.com";

	private String geturl(String url) {
		String desturl = "";
		if (url.startsWith("htt")) {

			desturl = preurl + url;
		} else
			desturl = preurl + searengin + url;

		return desturl;
	}

	private String getFullUrl(HttpServletRequest request) {
		final String url = request.getRequestURL().toString();
		final String queryString = request.getQueryString();
		return isNotBlank(queryString) ? String.format("%s?%s", url,
				queryString) : url;
	}

	private static final HeaderGroup hopByHopHeaders;
	static {
		hopByHopHeaders = new HeaderGroup();
		String[] headers = new String[] { "Connection", "Keep-Alive",
				"Proxy-Authenticate", "Proxy-Authorization", "TE", "Trailers",
				"Transfer-Encoding", "Upgrade" };
		for (String header : headers) {
			hopByHopHeaders.addHeader(new BasicHeader(header, null));
		}

	}
	private CloseableHttpClient httpClient;

	private String getContentCharSet(final HttpEntity entity)
			throws ParseException {
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

	private void copyResponseHeaders(HttpResponse proxyResponse,
			final HttpServletResponse servletResponse) {
		servletResponse.setCharacterEncoding(getContentCharSet(proxyResponse
				.getEntity()));
		from(Arrays.asList(proxyResponse.getAllHeaders()))
				.filter(new Predicate<Header>() {
					@Override
					public boolean apply(Header header) {
						return !hopByHopHeaders.containsHeader(header.getName());
					}
				}).transform(new Function<Header, Boolean>() {
					@Override
					public Boolean apply(Header header) {
						servletResponse.addHeader(header.getName(),
								header.getValue());
						return true;
					}
				}).toList();
	}

	private void copyRequestHeaders(HttpServletRequest servletRequest,
			HttpRequest proxyRequest) throws URISyntaxException {
		// Get an Enumeration of all of the header names sent by the client
		Enumeration<?> enumerationOfHeaderNames = servletRequest
				.getHeaderNames();
		while (enumerationOfHeaderNames.hasMoreElements()) {
			String headerName = (String) enumerationOfHeaderNames.nextElement();
			// Instead the content-length is effectively set via
			// InputStreamEntity
			if (!headerName.equalsIgnoreCase(CONTENT_LENGTH)
					&& !hopByHopHeaders.containsHeader(headerName)) {
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
					 * URIUtils.extractHost(new URI(
					 * prerenderConfig.getPrerenderServiceUrl())); headerValue =
					 * host.getHostName(); if (host.getPort() != -1) {
					 * headerValue += ":" + host.getPort(); } }
					 */
					proxyRequest.addHeader(headerName, headerValue);
				}
			}
		}
	}

	public CloseableHttpClient getHttpClient() {
		HttpClientBuilder builder = HttpClients.custom()
				.setConnectionManager(new PoolingHttpClientConnectionManager())
				.disableRedirectHandling();

		return builder.build();
	}

	private String proxyPrerenderedPageResponse(String url,
			HttpServletRequest request, HttpServletResponse response) {

		String html = "";
		CloseableHttpResponse prerenderServerResponse = null;
		try {
			final String apiUrl = url;// getFullUrl(request);
			log.trace(String.format(" send request to:%s", apiUrl));
			HttpGet getMethod = new HttpGet(apiUrl);
			// HttpPost getMethod = new HttpPost(apiUrl);
			// copyRequestHeaders(request, getMethod);
			// withPrerenderToken(getMethod);

			prerenderServerResponse = httpClient.execute(getMethod);
			response.setStatus(prerenderServerResponse.getStatusLine()
					.getStatusCode());
			// copyResponseHeaders(prerenderServerResponse, response);

			int status = prerenderServerResponse.getStatusLine()
					.getStatusCode();
			log.trace("status:" + status);
			if (status == 307 || status == 302 || status == 301
					|| status == 303) {
				String redirectURl = "";
				for (Header head : prerenderServerResponse.getAllHeaders()) {
					if (head.getName().equals("Location")) {
						redirectURl = head.getValue();
						break;
					}
				}

				String desturl = geturl(redirectURl);
				closeQuietly(prerenderServerResponse);
				return proxyPrerenderedPageResponse(desturl, request, response);

			} else
				html = getResponseHtml(prerenderServerResponse);
		} catch (Exception e) {
			log.trace(e.getMessage());
			// responseEntity(html, response);
			// return true;
		} finally {
			closeQuietly(prerenderServerResponse);
		}
		return html;
	}

	private void responseEntity(String html, HttpServletResponse servletResponse)
			throws IOException {
		PrintWriter printWriter = servletResponse.getWriter();
		try {
			printWriter.write(html);
			printWriter.flush();
		} finally {
			closeQuietly(printWriter);
		}
	}

	private String getResponseHtml(HttpResponse proxyResponse)
			throws IOException {
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
	public void dosearch(HttpServletRequest request,
			HttpServletResponse response) {

		// String deviceid = request.getParameter("deviceid");

		/*
		 * int pageCount = Integer.parseInt(request.getParameter("rows"));//
		 * request.getParameter("pageCount"); int curPage =
		 * Integer.parseInt(request.getParameter("page"));
		 */

		String data = parseStringParam(request, "keyword");
		String url = parseStringParam(request, "url");

		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			// searengin="https://www.google.co.jp";
			searengin = "https://www.google.com.hk";

			searengin = ConfigReader.getInstance().getProperty("searchEngin",
					searengin);

			preurl = ConfigReader.getInstance().getProperty("preurl", preurl);

			httpClient = getHttpClient();
			String desturl = "";
			if (data != null && !data.equals("")) {
				if (searengin.contains("baidu.com"))
					desturl = preurl + searengin + "/s?ie=utf-8&wd="
							+ URLEncoder.encode(data, "utf-8");
				else if (searengin.contains("google"))
					desturl = preurl + searengin + "/search?q="
							+ URLEncoder.encode(data, "utf-8");

			} else if (url != null && !url.equals("")) {
				// 替换自动附加的本站链接前缀
				url = url.replace("http://" + request.getHeader("Host"), "");
				desturl = geturl(url);
			}

			if (!desturl.equals("")) {
				String htmldata = proxyPrerenderedPageResponse(desturl,
						request, response);

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
		JsonUtil.responseOutWithJson(response, rst);

	}
}
