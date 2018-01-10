package com.kxjl.web.system.action.base;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;
import com.kxjl.web.system.service.MenuInfoService;

public class PageFilter implements Filter {

	public FilterConfig config;

	@Autowired
	MenuInfoService menuService;

	private static Logger logger = Logger.getLogger(PageFilter.class);

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		// autowire起作用
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	public void doFilter(ServletRequest orequest, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

	
		
		
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(
				(HttpServletResponse) response);

		HttpServletRequest request = (HttpServletRequest) orequest;

		HttpSession session = request.getSession();

		
	/*	chain.doFilter(request, response);
		if(true)
		return;*/
		
		
		// web.xml中读取
		String excludedUrls = config.getInitParameter("excludedUrls");
		String[] urls = excludedUrls.split(",");

		for (int i = 0; i < urls.length; i++) {
			if (urls[i].trim().equals(""))
				continue;
			if (request.getRequestURI().endsWith(urls[i])
					|| request.getRequestURI().endsWith(".css")
						|| request.getRequestURI().endsWith(".map")
					|| request.getRequestURI().endsWith(".js")
					|| request.getRequestURI().endsWith(".woff2")
					|| request.getRequestURI().endsWith(".woff")
					|| request.getRequestURI().endsWith(".ttf")

			) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		
					

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

			//*匹配
			if (urls[i].contains("*")) {
				String pattern = urls[i].trim();

				boolean isMatch = Pattern.matches(pattern,
						request.getRequestURI());
				if(isMatch)
				{
					chain.doFilter(request, response);
					return;	
				}
			}

		}

		SysUserBean user = (SysUserBean) session
				.getAttribute(Constant.SESSION_USER);

		
		
		
		// System.out.println("pageFilter:" + request.getRequestURI());
		if (user == null) {
			
			//跟目录，放
			if(request.getContextPath().equals(""))
			{
				if(request.getRequestURI().equals("/"))
				{
				logger.debug("pass:user is null but is / && request.getContextPath():"+request.getContextPath());
				chain.doFilter(request, response);
				return;
				}
			}
			
			logger.debug("request.getContextPath():"+request.getContextPath());//, redirect to login page!");
			logger.debug("request.getRequestURI(): "+request.getRequestURI());
			String loginPath = request.getContextPath() + "/login.jsp";
			logger.debug("no userinfo, redirect to login page!");
			wrapper.sendRedirect(loginPath);
			return;
		} else {

			// 登录后，刷新过菜单时，访问根目录，直接跳转第一个菜单
			if ((request.getRequestURI().equals(request.getContextPath()))
					|| (request.getRequestURI().equals(request.getContextPath()
							+ "/"))) {

				if (user.getMenus().size() == 0)
					menuService.updateUserMenus(user);

				for (int i = 0; i < user.getMenus().size(); i++) {
					if (user.getMenus().get(i).getMenuUrl() != null
							&& !user.getMenus().get(i).getMenuUrl().trim()
									.equals("")) {
						wrapper.sendRedirect(user.getMenus().get(i)
								.getMenuUrl());
						return;
					}
				}

			}

			if (request.getRequestURI().endsWith("/")) {

				// zj 171215 直接访问url权限过滤
				// System.out.println("filter:"+request.getRequestURI());
				boolean inaccess = false;

				if (user.getMenus().size() == 0)
					inaccess = true;
				else
					inaccess = false;

				for (int i = 0; i < user.getMenus().size(); i++) {
					if (user.getMenus().get(i) != null
							&& user.getMenus().get(i).getMenuUrl() != null
							&& request.getRequestURI().contains(
									user.getMenus().get(i).getMenuUrl())) {
						inaccess = true;
						break;
					}
				}

				if (!inaccess) {
					String loginPath = request.getContextPath()
							+ "/noaccess.jsp";
					wrapper.sendRedirect(loginPath);

				}

			}

			chain.doFilter(request, response);
			return;
		}

	}

	public void destroy() {
	}
}