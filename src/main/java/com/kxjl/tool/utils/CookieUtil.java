package com.kxjl.tool.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

	
	private Logger logger=Logger.getLogger(CookieUtil.class);
	
	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	// @RequestMapping("/addCookie")
	public void addCookie(HttpServletResponse response, String name, String value, int timeoutsec) {
		Cookie cookie = new Cookie(name.trim(), value.trim());
		cookie.setMaxAge(timeoutsec);// 30 * 60);// 设置为30min
		cookie.setPath("/");
		logger.debug("已添加===============");
		response.addCookie(cookie);
	}

	/**
	 * 修改cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 *            注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
	 */
	// @RequestMapping("/editCookie")
	public void editCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,int timeoutsec) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			logger.debug("没有cookie==============");
		} else {
			
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					logger.debug("原值为:" + cookie.getValue());
					cookie.setValue(value);
					cookie.setPath("/");
					//cookie.setMaxAge(30 * 60);// 设置为30min
					logger.debug("被修改的cookie名字为:" + cookie.getName() + ",新值为:" + cookie.getValue());
					response.addCookie(cookie);
					break;
				}
			}
			
			addCookie(response, name, value, timeoutsec);
		}

	}

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	// @RequestMapping("/delCookie")
	public void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			logger.debug("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath("/");
					logger.debug("被删除的cookie名字为:" + cookie.getName());
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	public String getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} else {
			return "";
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}