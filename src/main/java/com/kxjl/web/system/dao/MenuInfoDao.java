package com.kxjl.web.system.dao;

import java.util.List;

import com.kxjl.web.system.model.MenuInfo;




/**
 * 菜单Dao
 * @author kangyongji
 *
 */
public interface MenuInfoDao {
	
	/**
	 * 查询跟菜单
	 * @return
	 */
	public List<MenuInfo> queryRootMenus();
	public List<MenuInfo> queryAllMenus();
	
	
	/**
	 * 根据父菜单ID查询子菜单
	 * @param parentId
	 * @return
	 */
	public List<MenuInfo> queryMenusByParent(String parentId);
}
