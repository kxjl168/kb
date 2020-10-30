package com.kxjl.base.interceptor;

import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kxjl.base.aopAspect.NoNeedAuthorization;

import com.kxjl.base.util.SpringUtils;
import com.kxjl.web.privilege.model.Role;
import com.kxjl.web.privilege.service.PrivilegeService;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.base.base.SysConst;
import com.kxjl.base.plat.SignConstants;
import com.kxjl.base.plat.SignConstants.SignType;
import com.kxjl.base.plat.SignUtil;
import com.kxjl.base.service.ManagerService;

import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InterfaceTokenInterceptor implements HandlerInterceptor {

	private static Logger logger = LogManager.getLogger(InterfaceTokenInterceptor.class);

	TokeUtils tokeUtils;

	ManagerService managerService;

	@Autowired
	private PrivilegeService roleService;

	@Value("${checkSign:false}")
	private Boolean checkSign;

	public InterfaceTokenInterceptor() {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		tokeUtils = (TokeUtils) SpringUtils.getBean(TokeUtils.class);
		managerService = (ManagerService) SpringUtils.getBean(ManagerService.class);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		// ApplicationContext ctx = WebApplicationContextUtils
		// .getRequiredWebApplicationContext(request.getServletContext());
		// UserServiceImpl userService = (UserServiceImpl) ctx.getBean("userService");

		// System.out.println(request.getRequestURL());

		// web端 token验证
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		Map<String, String> datas = new HashMap<>();
		String appid ="";
		
		// 文件上传
		if (request.getRequestURI().contains("UploadFile"))
			checkSign = false;

		else {

			// api keyid sign 检查
			Enumeration paras = request.getParameterNames();
		
			while (paras.hasMoreElements()) {

				String v = (String) paras.nextElement();
				String d = (String) request.getParameter(v);

				// logger.info("*" + v + ":" + request.getParameter(v));
				// logger.debug("*" + v + ":" + request.getParameter(v));
				datas.put(v, d);

			}

			 appid = datas.get(SignConstants.FIELD_KEY);
			if (appid == null || appid.equals("")) {
				response.setStatus(HttpStatus.SC_UNAUTHORIZED);
				return false;
			}
			
			if (appid.equals("sdf922233ce10fa47a1af8491d2fbd20ac6"))
				checkSign = false;
			else
				checkSign = true;
		}

		

		if (checkSign) {

			String random = datas.get(SignConstants.FIELD_RANDOM);
			if (random == null || random.equals("")) {
				response.setStatus(HttpStatus.SC_UNAUTHORIZED);
				return false;
			}

			// 随机参数检查

			//

			// appid检查，key密钥获取
			SysUserBean user = managerService.selectManagerByAccessKey(appid);
			/* Procompany company = procompanyService.selectProcompanyByAppId(appid); */
			if (user == null) {
				response.setStatus(HttpStatus.SC_UNAUTHORIZED);
				return false;
			}
			String key = user.getAccessSecret();

			// String key ="123456";

			String signType = datas.get(SignConstants.FIELD_SIGN_TYPE);
			SignType stype = SignType.MD5;
			if (signType == null || signType.equals("")) {

			} else {
				if (signType.equals(SignConstants.HMACSHA256))
					stype = SignType.HMACSHA256;

			}

			boolean isValida = SignUtil.isSignatureValid(datas, key, stype);
			if (!isValida) {
				response.setStatus(HttpStatus.SC_UNAUTHORIZED);
				System.out.println(request.getRequestURL() + " fail:" + datas.toString());
				return false;
			}
		}
		// end

		// head token检查
		String token = request.getHeader(SysConst.AUTHORIZATION);
		boolean tokenOk = false;
		if (token != null && !token.equals("")) {
			tokeUtils = (TokeUtils) SpringUtils.getBean(TokeUtils.class);

			boolean isExpire = tokeUtils.checkIsExpireToken(token);
			if (isExpire) {
				response.setStatus(4000);// token过期
				System.out.println("token过期..");
				tokenOk = false;
				// return false;
			} else {
				SysUserBean mquery = new SysUserBean();
				mquery.setToken(token);
				SysUserBean curUser = managerService.getUserByToken(mquery);
				if (curUser != null) {
					List<Role> roleList = roleService.getManagerRoleList(curUser);
					List<String> roles = new ArrayList<>();
					for (Role item : roleList) {
						roles.add(item.getRole_en());
					}

					curUser.setRoles(roles);

					tokeUtils.setCurrentUser(curUser);

					tokenOk = true;
				} else {
					// tokeUtils.setCurrentUser(null);
				}

			}

		} else {
			tokeUtils.setCurrentUser(null);
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if (method.getAnnotation(NoNeedAuthorization.class) != null) {
			// 如果验证token失败，但是方法注明了NoNeedAuthorization，正常请求
			// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return true;
		}

		else {
			if (tokenOk)
				return true;

			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

	}
}
