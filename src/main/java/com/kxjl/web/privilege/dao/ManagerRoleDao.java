package com.kxjl.web.privilege.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.kxjl.web.privilege.model.ManagerRole;
import com.kxjl.web.privilege.model.Role;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;

public interface ManagerRoleDao {

	/**
	 * 添加ManagerRole
	 * 
	 * @param ManagerRole
	 * @return
	 */
	public int addManagerRole(ManagerRole ManagerRole);

	/**
	 * 删除用户的所有角色
	 * 
	 * @param id
	 * @return
	 */
	public int deleteManagerRole(ManagerRole user);



	/**
	 * 获取后台用户的所有角色
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Role> getManagerRoleList(SysUserBean query);

	/**
	 * 获取前台用户的角色
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年7月31日
	 */
	public List<Role> getUserRoleList(SysUserBean query);
	
	
	/**
	 * 获取管理用户的所有菜单
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<MenuInfo> getManagerMenusList(SysUserBean query);
	
	/**
	 * 获取前台用户的所有菜单
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<MenuInfo> getUserMenusList(SysUserBean query);
	
}
