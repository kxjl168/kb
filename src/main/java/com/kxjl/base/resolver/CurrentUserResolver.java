package com.kxjl.base.resolver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.aopAspect.CurrentUser;
import com.kxjl.base.interceptor.TokeUtils;

import com.kxjl.base.service.ManagerService;
import com.kxjl.web.system.model.SysUserBean;


public class CurrentUserResolver implements HandlerMethodArgumentResolver

{

	@Autowired
	TokeUtils tokeUtils;

	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(CurrentUser.class)) {
			return true;
		}
		return false;
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		SysUserBean m = tokeUtils.getCurrentUser();
		if(m!=null)
		{
			LoginUser user=new LoginUser();
			user.setUserId(m.getUserid());
			
			String roleids="";
			for (String r : m.getRoles()) {
				if(roleids.equals(""))
					roleids+=""+r;
				else
				roleids+=","+r;
			}
			
			user.setRoleId(roleids);
			user.setUserName(m.getName());
			
			return user;
		}
		
		//访客
		LoginUser user=new LoginUser();
		user.setUserName("访客");
		user.setUserId("test"); //内置测试
		user.setRoleId("null");
		

		return user;

	}
}
