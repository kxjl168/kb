package com.kxjl.web.system.action.base;

import java.io.IOException;
import java.net.URLEncoder;
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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.blog.service.BlogService;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;
import com.kxjl.web.system.service.MenuInfoService;

public class UrlFilter implements Filter {

	public FilterConfig config;

	@Autowired
	BlogService blogService;

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

		String basePath = request.getContextPath();
		try {
			if (request.getRequestURI().startsWith(basePath + "/public/s/")) {
				// 通用搜索

			} else if (request.getRequestURI().startsWith(
					basePath + "/public/t/")) {
				// tag过滤

				String tag = "";
				JSONObject jobj = new JSONObject();
				jobj.put("blog_tag", tag);
				String dataString = URLEncoder.encode(jobj.toString(), "utf-8");

				request.getRequestDispatcher(
						"/blog/getInfoList.action?data=" + dataString).forward(
						request, response);
				return;

			} else if (request.getRequestURI().startsWith(
					basePath + "/public/h/")) {
				// 时间归档过滤
				String tag = "";
				JSONObject jobj = new JSONObject();
				jobj.put("month", tag);
				String dataString = URLEncoder.encode(jobj.toString(), "utf-8");
				
				request.getRequestDispatcher(
						"/blog/getInfoList.action?data=" + dataString).forward(
						request, response);
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		} catch (Exception e) {
			chain.doFilter(request, response);
			return;
		}

	}

	public void destroy() {
	}
}