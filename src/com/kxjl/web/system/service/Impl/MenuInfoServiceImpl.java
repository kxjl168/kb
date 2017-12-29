package com.kxjl.web.system.service.Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.privilege.dao.ManagerRoleDao;
import com.kxjl.web.privilege.dao.RoleDao;
import com.kxjl.web.privilege.model.Role;
import com.kxjl.web.system.dao.MenuInfoDao;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.service.MenuInfoService;

/**
 * 菜单 service实现
 * 
 * @author kangyongji
 * 
 */
@Service(value = "menuService")
public class MenuInfoServiceImpl implements MenuInfoService {

	@Autowired
	private MenuInfoDao menuDao;

	@Autowired
	RoleDao roleDao;
	@Autowired
	ManagerRoleDao managerRoleDao;

	
	@Override
	public List<MenuInfo> queryRootMenus() {
		// TODO Auto-generated method stub
		return menuDao.queryRootMenus();
	}

	public List<MenuInfo> queryAllMenus() {

		return menuDao.queryAllMenus();
	}
	
	
	/**
	 * 更新用户菜单信息
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-20
	 */
	public List<MenuInfo> updateUserMenus(SysUserBean user) {
		List<MenuInfo> rst = new ArrayList<MenuInfo>();

		// List<MenuInfo> menus = menuService.queryRootMenus();

		List<Role> roles = managerRoleDao.getManagerRoleList(user);

		boolean isroot = false;
		// 过滤权限
		if (roles != null) {

			for (int i = 0; i < roles.size(); i++) {
				if (roles.get(i).getRole_en().equals("root")) {

					isroot = true;
					break;
				}
			}
		}

		if (isroot) {
			rst = queryAllMenus();
		} else {
			Role r = new Role();
			r.setRole_en(user.getUtype().toString());// 用户类型与角色一样
			rst = roleDao.getRoleMenusList(r);
		}

		user.setMenus(rst);
		return rst;
	}
	

	@Override
	public List<MenuInfo> queryMenusByParent(String parentId) {
		// TODO Auto-generated method stub
		return menuDao.queryMenusByParent(parentId);
	}

}
