package com.kxjl.base.service;

import com.kxjl.web.system.model.SysUserBean;

public interface ManagerService {

	
	public SysUserBean selectManagerByAccessKey(String appid);
	
	public SysUserBean getUserByToken(SysUserBean user);

	/**
	 * 获取用户信息
	 * @param id  userid，
	 * @return
	 * @author:kxjl
	 * @date 2020年9月29日
	 */
	public SysUserBean getLoginUserByUserId(String id);
	
	
	/**
	 * 接口登录更新token
	 * @param id
	 * @param token
	 * @return
	 * @author:kxjl
	 * @date 2020年9月29日
	 */
	public boolean updateToken(String id,String token);
}