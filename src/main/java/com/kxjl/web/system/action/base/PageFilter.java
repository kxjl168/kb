package com.kxjl.web.system.action.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.html.CharResponseWrapper;
import com.kxjl.tool.html.FileProcessor;
import com.kxjl.tool.utils.DateUtil;
import com.kxjl.tool.utils.IPUtils;
import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.blog.service.BlogService;
import com.kxjl.web.stastic.model.ActionLog;
import com.kxjl.web.stastic.service.StasticService;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;
import com.kxjl.web.system.service.CommonService;
import com.kxjl.web.system.service.MenuInfoService;

/**
 * 权限控制!
 * @author zj
 * @date 2018年7月2日
 *
 */
public class PageFilter implements Filter {

	public FilterConfig config;

	@Autowired
	MenuInfoService menuService;

	@Autowired
	CommonService commonService;

	@Autowired
	BlogService bservice;

	@Autowired
	public StasticService stasticService;

	private static Logger logger = Logger.getLogger(PageFilter.class);

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		// autowire起作用
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	/**
	 * 伪静态页面
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @return
	 * @author zj
	 * @date 2018年5月7日
	 */
	private Boolean WeiStaticFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

		boolean isDone = false;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);

		// 伪静态页面。解析url
		if (request.getRequestURI().endsWith(".htm") || request.getRequestURI().endsWith(".html")) {
			try {

				String cpath = request.getContextPath();
				String curl = request.getRequestURI();
				logger.debug("request.getContextPath():" + cpath);// ,
																	// redirect//
																	// page!");
				logger.debug("request.getRequestURI(): " + curl);

				if (curl.contains("public/detail/")) {
					/*int xindex = curl.lastIndexOf("/");
					int dotindex = curl.lastIndexOf(".");
					int jindex = curl.lastIndexOf("#");

					// request.get

					String imei = curl.substring(xindex + 1, dotindex);
					String nurl = "/public/detail/?i=" + imei;
					if (imei.equals(""))
						nurl = "../../" + imei;

					RequestDispatcher dispathcer = request.getRequestDispatcher(nurl);
					dispathcer.forward(request, response);

					// wrapper.sendRedirect(nurl);
					isDone = true;*/
				} else if (curl.contains("public/index/")) {

					String pretag = "i";
					int iindex = curl.indexOf("/i/");
					if (iindex > 0)
						pretag = "i";
					int tpindex = curl.indexOf("/tg/");
					if (tpindex > 0)
						pretag = "tg";
					int hpindex = curl.indexOf("/h/");
					if (hpindex > 0)
						pretag = "h";
					int btpindex = curl.indexOf("/bt/");
					if (btpindex > 0)
						pretag = "bt";

					int xindex = curl.lastIndexOf("/");
					int dotindex = curl.lastIndexOf(".");
					// int jindex = curl.lastIndexOf("#");

					// request.get

					String imei = curl.substring(xindex + 1, dotindex);

					//String nurl = cpath+"/public/index/?" + pretag + "=" + imei;
					String nurl = cpath+"/public/index/?" + pretag + "=" + imei;
					RequestDispatcher dispathcer = request.getRequestDispatcher(nurl);
					
					
					//request.removeAttribute("com.opensymphony.sitemesh.APPLIED_ONCE"); 
					
					dispathcer.forward(request, response);

					// wrapper.sendRedirect(nurl);
					isDone = true;
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		return isDone;
	}

	/**
	 * 通过prerender.io 及CharResponseWrapper 生成静态页面，修改prerender.io不剔除 全部js，
	 * 只提出ng-repeat
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @return
	 * @author zj
	 * @date 2018年5月7日
	 */
	private Boolean StaticFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

		boolean isDone = false;
		try {

			String userAgent = request.getHeader("User-Agent");
			// 先触发本地filter，在触发prerender,prerender过滤直接放行
			if (userAgent.contains("Prerender")) {
				return isDone;
			}

			String cpath = request.getContextPath();
			String curl = request.getRequestURI();
			logger.debug("request.getContextPath():" + cpath);// ,
																// redirect//
																// page!");
			logger.debug("request.getRequestURI(): " + curl);

			// if (request.getRequestURI().endsWith(".htm") ||
			// request.getRequestURI().endsWith(".html")) {

			String pattern = ".*/public/html/(\\d+/\\d+)/.*.html";
			Pattern p = Pattern.compile(pattern);
			Matcher mcher = p.matcher(curl);
			Map<String, String[]> params = request.getParameterMap();
			String imei = "";
			for (String key : params.keySet()) {
				if (curl.contains("?"))
					curl += "&" + key + "=" + request.getParameter(key);
				else
					curl += "?" + key + "=" + request.getParameter(key);

				if (key.equals("i"))
					imei = request.getParameter("i");
			}
			if (curl.contains("public/detail/?i=") || mcher.matches()) {

				String month = "";

				if (imei.equals(""))

				{
					// url
					try {
						imei = curl.substring(curl.lastIndexOf("/") + 1, curl.lastIndexOf("."));

						String pattern2 = ".*/public/html/(\\d+/\\d+)/.*.html";
						// String
						// curl="/kb/public/html/2018/04/86b49ac4-8616-41b9-862e-e022c111915e.html";

						month = mcher.group(1);

					} catch (Exception e) {
						// TODO: handle exception
					}
				} else {
					// ?i=
					Blog q = new Blog();
					q.setImei(imei);
					Blog bg = bservice.getBlogInfoById(q);
					month = bg.getShowdate();

				}

				String localPath = ConfigReader.getInstance().getProperty("LOCAL_HTML_PATH",
						"F:\\kxjl\\code\\kb\\WebContent\\public/html/");

				// String month = DateUtil.getNowStr("yyyy/MM");

				String htmlPath = request.getContextPath() + "/" + "public/html/" + month + "/" + imei + ".html";

				String localFilePath = localPath + month + "/";
				String htmlName = imei + ".html";

				File localFile = new File(localFilePath + htmlName);
				if (!(localFile.exists())) {

					if (curl.contains("public/detail/?i=")) {
						if (!curl.contains("_escaped_fragment_")) {
							String newurl = "";
							if (curl.contains("?"))
								newurl = curl + "&_escaped_fragment_";
							else
								newurl = curl + "?_escaped_fragment_";
							// RequestDispatcher dispatcher = request.getRequestDispatcher(newurl);
							// dispatcher.forward(request, response);
							response.sendRedirect(newurl);
							isDone = true;
							return isDone;
						}
					}
					if (Pattern.matches(pattern, curl)) {
						String newurl = request.getContextPath() + "/public/detail/?i=" + imei + "&_escaped_fragment_";
						 //RequestDispatcher dispatcher = request.getRequestDispatcher(newurl);
						 //dispatcher.forward(request, response);
						response.sendRedirect(newurl);
						isDone = true;
						return isDone;
					}

					CharResponseWrapper localCharResponseWrapper = new CharResponseWrapper(response);
					chain.doFilter(request, localCharResponseWrapper);
					String str8 = localCharResponseWrapper.toString();
					if (str8 != null) {
						String fpath=FileProcessor.writeFile(str8, localFilePath, htmlName);
						logger.info("write file done:"+fpath);
						Thread.sleep(3600);
						//RequestDispatcher dispatcher = request.getRequestDispatcher(htmlPath);
						// dispatcher.forward(request, response);
						
						//RequestDispatcher dispatcher = request.getRequestDispatcher(htmlPath);
						//dispatcher.forward(request, localCharResponseWrapper);
						
						response.sendRedirect(htmlPath);

						isDone = true;
					}
				} else {
					//Thread.sleep(2000);
					//logger.info("html "+htmlPath+" visited!");
					// 不用重定向，直接访问html。
					// response.sendRedirect(htmlPath);
					// isDone = true;
				}

			}

			// }

		} catch (

		Exception e) {
			logger.error(e.getMessage());
		}
		return isDone;
	}

	public void doFilter(ServletRequest orequest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);

		HttpServletRequest request = (HttpServletRequest) orequest;
		HttpServletResponse response2 = (HttpServletResponse) response;

		HttpSession session = request.getSession();

		// 黑名单过滤
		if (commonService.isInBlackIPList(request))
			return;

		/*
		 * chain.doFilter(request, response); if(true) return;
		 */
		
		String cpath= request.getContextPath();// ,
		String curl= request.getRequestURI();
		
		if(curl.equals(cpath)||curl.equals(cpath+"/")) {
		//	chain.doFilter(request, response);
		//	return;
			//wrapper.sendRedirect("public/index");
			//
			//return;
		}
		
		
		

		// web.xml中读取
		String excludedUrls = config.getInitParameter("excludedUrls");
		String[] urls = excludedUrls.split(",");

		for (int i = 0; i < urls.length; i++) {
			if (urls[i].trim().equals(""))
				continue;
			if (request.getRequestURI().endsWith(urls[i]) || request.getRequestURI().endsWith(".css")
					|| request.getRequestURI().endsWith(".map") || request.getRequestURI().endsWith(".js")
					|| request.getRequestURI().endsWith(".woff2") || request.getRequestURI().endsWith(".woff")
					|| request.getRequestURI().endsWith(".ttf") || request.getRequestURI().endsWith(".png")
					|| request.getRequestURI().endsWith(".jpg") || request.getRequestURI().endsWith(".jpeg")
					|| request.getRequestURI().endsWith(".gif")

			) {
				chain.doFilter(request, response);
				return;
			}
		}

	
		if (StaticFilter(request, response2, chain))
			return;
		
		// if (WeiStaticFilter(request, response2, chain))
		// return;


		// 配置文件中读取
		excludedUrls = ConfigReader.getInstance().getProperty("excludedUrls");
		urls = excludedUrls.split(",");
		for (int i = 0; i < urls.length; i++) {
			if (urls[i].trim().equals(""))
				continue;
			if (request.getRequestURI().endsWith(urls[i])) {
				chain.doFilter(request, response);
				return;
			}

			// *匹配
			if (urls[i].contains("*")) {
				String pattern = urls[i].trim();

				boolean isMatch = Pattern.matches(pattern, request.getRequestURI());
				if (isMatch) {
					chain.doFilter(request, response);
					return;
				}
			}

		}

		SysUserBean user = (SysUserBean) session.getAttribute(Constant.SESSION_USER);

		// System.out.println("pageFilter:" + request.getRequestURI());
		if (user == null) {

			// 跟目录，放
			if (request.getContextPath().equals("")) {
				if (request.getRequestURI().equals("/")) {
					logger.warn("pass:user is null but is / && request.getContextPath():" + request.getContextPath());
					chain.doFilter(request, response);
					return;
				}
			}

			logger.debug("request.getContextPath():" + request.getContextPath());// ,
			// page!");
			logger.debug("request.getRequestURI(): " + request.getRequestURI());
			String loginPath = request.getContextPath() + "/login.jsp";
			logger.debug("no userinfo, redirect to login page!");
			wrapper.sendRedirect(loginPath);

			if (!(request.getRequestURI().startsWith("/public") || request.getRequestURI().startsWith("/page")
					|| request.getRequestURI().startsWith("/pown"))) {
				stasticService.saveStaticInfo(request, "attack", "");
			}

			return;
		} else {

			// 登录后，刷新过菜单时，访问根目录，直接跳转第一个菜单
			if ((request.getRequestURI().equals(request.getContextPath()))
					|| (request.getRequestURI().equals(request.getContextPath() + "/"))) {

				if (user.getMenus().size() == 0)
					menuService.updateUserMenus(user);

				for (int i = 0; i < user.getMenus().size(); i++) {
					if (user.getMenus().get(i).getMenuUrl() != null
							&& !user.getMenus().get(i).getMenuUrl().trim().equals("")) {
						wrapper.sendRedirect(user.getMenus().get(i).getMenuUrl());
						return;
					}
				}

			}

		/*	if (request.getRequestURI().endsWith("/")) {*/

			//所有目录都要过滤
				// zj 171215 直接访问url权限过滤
				// System.out.println("filter:"+request.getRequestURI());
				boolean inaccess = false;

				if (user.getMenus().size() == 0)
					inaccess = true;
				else
					inaccess = false;

				for (int i = 0; i < user.getMenus().size(); i++) {
					if (user.getMenus().get(i) != null && user.getMenus().get(i).getMenuUrl() != null
							&& request.getRequestURI().contains(user.getMenus().get(i).getMenuUrl())) {
						inaccess = true;
						break;
					}
				}

				if (!inaccess) {
					
					
					if(user.getUtype()==UserType.Root||user.getUtype()==UserType.Admin)
					{
						chain.doFilter(request, response);
						return;
					}
					else
					{
					String loginPath = request.getContextPath() + "/noaccess.jsp";
					wrapper.sendRedirect(loginPath);
					logger.debug("inaccess:" + inaccess);// ,
					return ;
					}
					
				}

				

			}

			logger.debug("request.getContextPath():" + request.getContextPath());// ,
			logger.debug("request.getRequestURI(): " + request.getRequestURI());

			chain.doFilter(request, response);
			return;
		/*}*/

	}

	public void destroy() {
	}
}