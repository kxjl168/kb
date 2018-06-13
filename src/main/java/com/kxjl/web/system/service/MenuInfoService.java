package com.kxjl.web.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;




/**
 * 菜单 service
 * @author kangyongji
 *
 */

public interface MenuInfoService {
	
	/**
	 * 查询跟菜单
	 * @return
	 */
	public List<MenuInfo> queryRootMenus();
	
	public List<MenuInfo> queryAllMenus();
	
	
	/**
	 * 更新用户菜单信息
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-20
	 */
	public List<MenuInfo> updateUserMenus(SysUserBean user);
	
	/**
	 * 根据父菜单ID查询子菜单
	 * @param parentId
	 * @return
	 */
	public List<MenuInfo> queryMenusByParent(String parentId);
}
