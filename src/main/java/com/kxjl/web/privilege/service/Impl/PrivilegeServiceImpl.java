package com.kxjl.web.privilege.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.privilege.dao.ManagerRoleDao;
import com.kxjl.web.privilege.dao.RoleDao;
import com.kxjl.web.privilege.dao.RoleMenuDao;
import com.kxjl.web.privilege.model.ManagerRole;
import com.kxjl.web.privilege.model.Role;
import com.kxjl.web.privilege.model.RoleMenu;
import com.kxjl.web.privilege.service.PrivilegeService;
import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;

@Service(value = "PrivilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	RoleMenuDao roleMenuDao;

	@Autowired
	ManagerRoleDao managerRoleDao;

	@Autowired
	RoleDao roleDao;

	@Override
	public int updateRoleMenuList(String role_id, String menuids) {

		int rst = -1;
		try {
			RoleMenu query = new RoleMenu();
			query.setRole_id(role_id);

			// 清空
			roleMenuDao.deleteRoleMenu(query);

			// 添加
			String[] menus = menuids.split(",");
			for (int i = 0; i < menus.length; i++) {
				if (menus[i].trim().equals(""))
					continue;

				RoleMenu item = new RoleMenu();
				item.setRole_id(role_id);
				item.setMenu_id(menus[i]);

				roleMenuDao.addRoleMenu(item);
			}

			rst = 1;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return rst;
	}

	@Override
	public int updateManagerRoleList(String manager_id, String roleids) {
		// 1后台用户
		return updateURoleList(manager_id, "1", roleids);
	}

	/**
	 * 跟新用户（前台/后台用户） 角色列表
	 * 
	 * @param uid
	 * @param user_type
	 * @param roleids
	 * @return
	 * @author zj
	 * @date 2018年7月31日
	 */
	private int updateURoleList(String uid, String user_type, String roleids) {
		int rst = -1;
		try {

			ManagerRole query = new ManagerRole();
			query.setManager_id(uid);
			query.setUser_type(user_type);

			// 清空
			managerRoleDao.deleteManagerRole(query);

			// 添加
			String[] menus = roleids.split(",");
			for (int i = 0; i < menus.length; i++) {
				if (menus[i].trim().equals(""))
					continue;

				ManagerRole item = new ManagerRole();
				item.setManager_id(uid);
				item.setRole_id(menus[i]);
				item.setUser_type(user_type);

				managerRoleDao.addManagerRole(item);
			}

			rst = 1;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return rst;
	}

	public int updateUserRoleList(String manager_id, String roleids) {
		// 2 普通用户
		return updateURoleList(manager_id, "2", roleids);
	}

	/**
	 * 获取用户的所有角色
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Role> getManagerRoleList(SysUserBean query) {
		if (query.getUtype() == UserType.Admin)
			return managerRoleDao.getManagerRoleList(query);
		else if (query.getUtype() == UserType.LoginUser)
			return managerRoleDao.getUserRoleList(query);
		else {
			// 未登陆用户
			Role r = new Role();
			r.setRole_en(query.getUtype().toString());
			Role nologerRole = roleDao.getRoleInfoById(r);
			List<Role> roles = new ArrayList<>();
			roles.add(nologerRole);
			return roles;
		}

	}

	/**
	 * 获取用户的所有菜单
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<MenuInfo> getManagerMenusList(SysUserBean query) {
		if (query.getUtype() == UserType.Admin)
			return managerRoleDao.getManagerMenusList(query);
		else if (query.getUtype() == UserType.LoginUser)
			return managerRoleDao.getUserMenusList(query);
		else {
			Role r = new Role();
			r.setRole_en(query.getUtype().toString());
			return getRoleMenusList(r);
		}

	}

	/**
	 * 获取角色的所有菜单
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<MenuInfo> getRoleMenusList(Role query) {
		return roleDao.getRoleMenusList(query);
	}

}
