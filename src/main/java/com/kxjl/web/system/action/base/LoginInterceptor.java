package com.kxjl.web.system.action.base;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;

public class LoginInterceptor implements HandlerInterceptor {

	// 日志记录对象
	private Logger logger = Logger.getLogger(LoginInterceptor.class);

	private List<String> excludedUrls = new ArrayList<String>();

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

		// System.out.println("posthandle:" + request.getRequestURI());

	}

	/**
	 * 请求前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub

		String excludedUrls = ConfigReader.getInstance().getProperty("excludedUrls");
		String[] urls = excludedUrls.split(",");
		for (int i = 0; i < urls.length; i++) {
			if (urls[i].trim().equals(""))
				continue;
			if (request.getRequestURI().endsWith(urls[i])) {
				//chain.doFilter(request, response);
				return true;
			}

			// *匹配
			if (urls[i].contains("*")) {
				String pattern = urls[i].trim();

				boolean isMatch = Pattern.matches(pattern, request.getRequestURI());
				if (isMatch) {
					//chain.doFilter(request, response);
					return true;
				}
			}

		}

		// System.out.println("preHandle:" + request.getRequestURI());

		String uri = request.getRequestURI();

		String reqAction = uri.substring(uri.lastIndexOf("/") + 1);
		// 请求为updatePwd.action时不要求登录加密
		if (reqAction.contains("updatePwd")) {
			logger.info("请求Action为" + reqAction);
			return true;
		}

		if (reqAction.contains("login")) {
			logger.info("请求Action为" + reqAction);
			return true;
		}

		// logger.info("验证用户是否登录");
		HttpSession session = request.getSession();

		SysUserBean user = (SysUserBean) session.getAttribute(Constant.SESSION_USER);
		if (user == null || user.getUserid() == null || user.getUserid().isEmpty()) {

			logger.warn(uri+"用户没有登录");
			return false;
		} else {

			if (user.getUtype()==UserType.LoginUser) {

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

					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}

		}
		// logger.info("用户已登录，用户ID："+user.getUserid());
		return true;
	}

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

}
