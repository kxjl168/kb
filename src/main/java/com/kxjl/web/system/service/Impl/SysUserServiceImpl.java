package com.kxjl.web.system.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.tool.utils.DateUtil;
import com.kxjl.web.system.dao.MenuInfoDao;
import com.kxjl.web.system.dao.SvrFileInfoDao;
import com.kxjl.web.system.dao.SysUserDao;
import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SvrFileInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.service.MenuInfoService;
import com.kxjl.web.system.service.SvrFileInfoService;
import com.kxjl.web.system.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	/**
	 * 根据用户名和密码查询后台登录用户账号信息
	 * 
	 * @param userid
	 * @param password
	 * @return
	 */
	public SysUserBean getUserInfoByUseridAndPwd(SysUserBean model) {
		return sysUserDao.getUserInfoByUseridAndPwd(model);
	}

	/**
	 * 更新系统用户信息- 密码
	 * 
	 * @param model
	 * @return
	 * @date 2016-7-29
	 * @author zj
	 */
	public int updateSysuer(SysUserBean model) {
		return sysUserDao.updateSysuer(model);
	}

	public List<SysUserBean> getUserListInfo(SysUserBean model)

	{
		return sysUserDao.getUserListInfo(model);
	}

}
