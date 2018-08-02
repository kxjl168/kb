package com.kxjl.web.privilege.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.kxjl.web.privilege.model.Permission;
import com.kxjl.web.privilege.model.Role;

public interface PermissionService {

	int deleteByPrimaryKey(String id);

	int insert(Permission record);

	JSONObject insertSelective(Permission record);

	Permission selectByPrimaryKey(String id);

	JSONObject updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);

	
	/**
	 * update / save
	 * @param record
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public JSONObject saveOrUpdate(Permission record);
	/**
	 * 条件查询
	 * 
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<Permission> selectPermissionList(Permission permission);
	

	/**
	 * 条件查询-二级权限
	 * 
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<String> getPermissionTreeSecond(String role_id);
	
	/**
	 * 三级权限菜单树 -含按钮
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月31日
	 */
	List<String> getPermissionTreeThree(String role_id);

	
	
	/**
	 * 获取角色的所有权限
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	List<Permission> getRolePermissionList(Role role);
	
	/**
	 * 获取用户角色树
	 * @param Manager_id
	 * @return
	 * @author zj
	 * @date 2018年7月31日
	 */
	public List<String> getRoleTree(String Manager_id);
	
	/**
	 * 通过roleId 查询角色对应权限
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	List<Permission> selectPermissionsByRoleId(String roleId);
	
	
	/**
	 * 通过mangerId 查询角色对应权限
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	List<Permission> selectPermissionsByManagerId(String managerId);


}
