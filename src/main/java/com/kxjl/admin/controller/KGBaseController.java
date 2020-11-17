package com.kxjl.admin.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import com.kxjl.admin.common.LoginUser;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.base.aopAspect.CurrentUser;
import com.kxjl.base.aopAspect.NoNeedAuthorization;
import com.kxjl.base.interceptor.TokeUtils;

import com.kxjl.base.service.ManagerService;

import com.kxjl.tool.common.Md5Encrypt;
import com.kxjl.web.privilege.model.Role;
import com.kxjl.web.privilege.service.PrivilegeService;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;

@RestController
@RequestMapping("/kg/auths")
public class KGBaseController {

	Logger logger = LoggerFactory.getLogger(KGBaseController.class);

	@Autowired
	ManagerService managerService;

	@Autowired
	private PrivilegeService roleService;

	@Autowired
	TokeUtils tokeUtils;

	@PostMapping("/login")
	@NoNeedAuthorization
	public WZResponseEntity<?> login(String username, String password) {

		// String username = userIn.getUsername();
		// String password = userIn.getPassword();

		SysUserBean manager = new SysUserBean();
		manager.setUserid(username);
		manager.setPassword(password);

		// 46,20
		SysUserBean user = managerService.getLoginUserByUserId(username);

		if (user != null
				&& (user.getPassword().equals(password) || user.getPassword().equals(Md5Encrypt.MD5(password)))) {

			String usertoken = UUID.randomUUID().toString();

			if (user.getToken() != null && !user.getToken().equals(""))
				usertoken = user.getToken();

			usertoken = tokeUtils.refreshOrgenerateNewToken(usertoken);

			user.setToken(usertoken);
			// // 更新pass，加密存储,token
			managerService.updateToken(username, usertoken);

			// 特定manager表登录管理
			user.setUtype(UserType.Admin);

			String roleids = "";
			List<Role> roleList = roleService.getManagerRoleList(user);
			List<String> roles = new ArrayList<>();
			for (Role item : roleList) {
				if (roleids.equals(""))
					roleids += "" + item.getRole_en();
				else
					roleids += "," + item.getRole_en();
			}

			user.setRole_id(roleids);

			return new WZResponseEntity<>(user);
		} else {
			return new WZResponseEntity<>("用户不存在或密码错误");
		}

	}

	@GetMapping("/checkToken/{token}")
	@NoNeedAuthorization
	public WZResponseEntity<?> checkToken(@PathVariable String token) {
		SysUserBean mquery = new SysUserBean();
		mquery.setToken(token);
		SysUserBean curUser = managerService.getUserByToken(mquery);
		if (curUser == null) {
			return new WZResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new WZResponseEntity<>(curUser);

	}

	@GetMapping("/currentUser")
	public WZResponseEntity<?> getCurrentUser(@CurrentUser LoginUser user) {

		// User u=new User();
		// u.setName("111");
		// u.setUserid("1");
		// u.setTokenName("111-1,2-1");
		// if(true)
		// return new WZResponseEntity<>(u);

		// SysUserBean m = new SysUserBean();
		// m.setUsername(user.getUserId());

		if (user.getUserName() != null && user.getUserName().equals("访客"))
			return new WZResponseEntity<>(user);

		SysUserBean currUser = managerService.getLoginUserByUserId(user.getUserId());
		if (currUser != null) {

			String roleids = "";
			List<Role> roleList = roleService.getManagerRoleList(currUser);
			List<String> roles = new ArrayList<>();
			for (Role item : roleList) {
				if (roleids.equals(""))
					roleids += "" + item.getRole_en();
				else
					roleids += "," + item.getRole_en();
			}

			currUser.setRole_id(roleids);

			return new WZResponseEntity<>(currUser);
		} else {
			SysUserBean youke = new SysUserBean();
			youke.setName("游客");
			youke.setUserid("test");
			return new WZResponseEntity<>(youke);
		}

	}

	public static void main(String[] args) {
		String pass;
		try {
			// pass=PasswordUtil.encrypt("666666","test");
			// pass = MD5Util.md5("test2","123321");
			// pass = MD5Util.md5("user","123321");
			// System.out.println(pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
