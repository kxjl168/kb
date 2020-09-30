package com.kxjl.base.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.base.service.ManagerService;
import com.kxjl.web.privilege.dao.SysUserBeanDao;
import com.kxjl.web.system.model.SysUserBean;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	SysUserBeanDao sysUserBeanDao;

	public SysUserBean selectManagerByAccessKey(String appid) {
		SysUserBean u = null;
		
		SysUserBean user=new SysUserBean();
		user.setAccessKey(appid);
		u = sysUserBeanDao.selectManagerByAccessKey(user);
		return u;
	}

	public SysUserBean getUserByToken(SysUserBean user) {
		SysUserBean u = null;

		u = sysUserBeanDao.getUserByToken(user);

		return u;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 *            userid，
	 * @return
	 * @author:kxjl
	 * @date 2020年9月29日
	 */
	public SysUserBean getLoginUserByUserId(String userid) {
		SysUserBean u = null;

		SysUserBean q = new SysUserBean();
		q.setUserid(userid);
		u = sysUserBeanDao.getLoginUserByUserId(q);

		return u;
	}

	/**
	 * 接口登录更新token
	 * 
	 * @param id
	 * @param token
	 * @return
	 * @author:kxjl
	 * @date 2020年9月29日
	 */
	public boolean updateToken(String id, String token) {
		boolean rst = false;

		SysUserBean u = new SysUserBean();
		u.setUserid(id);
		u.setToken(token);
		rst = sysUserBeanDao.updateSysUserBean(u) > -1;

		return rst;
	}
}
