package com.kxjl.web.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;



/**
 * 菜单权限管理
 * @author zj
 * @date 2018年7月30日
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
	 * 左侧分级菜单，jstl使用
	 * @param session
	 * @param request
	 * @return
	 * @author zj
	 * @date 2018年7月27日
	 */
	public List<MenuInfo> getLeftMenuTree(HttpSession session, HttpServletRequest request) ;
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
