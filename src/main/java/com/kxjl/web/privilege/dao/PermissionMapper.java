package com.kxjl.web.privilege.dao;

import java.util.List;
import java.util.Map;

import com.kxjl.web.privilege.model.Permission;
import com.kxjl.web.privilege.model.Role;


public interface PermissionMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    
    /**
     * 条件查询
     * @param permission
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    List<Permission> selectPermissionList(Permission permission);
    
    public List<Permission> getRolePermissionList(Role role);
    /**
     * 根据角色id查询角色对应权限列表
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(String roleId);
    
    /**
     * 根据角色id查询角色对应权限列表
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByManagerId(String managerId);


}