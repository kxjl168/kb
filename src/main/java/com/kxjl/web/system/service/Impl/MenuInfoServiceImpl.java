package com.kxjl.web.system.service.Impl;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.tool.common.Constant;
import com.kxjl.web.blog.action.Kdata;
import com.kxjl.web.privilege.dao.ManagerRoleDao;
import com.kxjl.web.privilege.dao.RoleDao;
import com.kxjl.web.privilege.model.Role;
import com.kxjl.web.system.dao.MenuInfoDao;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;
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

	public List<MenuInfo> getLeftMenuTree(HttpSession session, HttpServletRequest request) {
		JSONObject jobj = new JSONObject();
		// logger.info("sid:"+ session.getId());
		SysUserBean user = (SysUserBean) session.getAttribute(Constant.SESSION_USER);

		if (user == null) {
			// 设置普通用户模式
			user = new SysUserBean();
			user.setUtype(UserType.UnLoginUser);
			user.setUserid(request.getRequestedSessionId());
			session.setAttribute(Constant.SESSION_USER, user);
		}

		String key = user.getUtype().toString();
		List<MenuInfo> rst = Kdata.getInstance().getMenuList(key);

		String key_left = user.getUtype().toString() + "_left";
		List<MenuInfo> leftrst = Kdata.getInstance().getMenuList(key_left);

		if (leftrst != null && leftrst.size() != 0)
			return leftrst;

		if (rst == null || rst.size() == 0) {
			rst = updateUserMenus(user);
			Kdata.getInstance().SavedMenuList(key, rst);// list)

		}

		leftrst = calMenuTree(rst);
		Kdata.getInstance().SavedMenuList(key_left, leftrst);// list)
		return leftrst;
	}

	/**
	 * 计算层级关系
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-20
	 */
	public List<MenuInfo> calMenuTree(List<MenuInfo> allmenu) {

		List<MenuInfo> ms = new ArrayList<>();

		for (int i = 0; i < allmenu.size(); i++) {

			if (allmenu.get(i).getMenuParentid() == null || allmenu.get(i).getMenuParentid().equals(""))
				ms.add(allmenu.get(i));
		}

		for (int k = 0; k < ms.size(); k++) {

			MenuInfo pInfo = ms.get(k);

			for (int i = 0; i < allmenu.size(); i++) {

				if (allmenu.get(i).getMenuParentid() != null
						&& allmenu.get(i).getMenuParentid().equals(pInfo.getMenuId())) {
					List<MenuInfo> cinfos = pInfo.getChildMenus();
					cinfos.add(allmenu.get(i));
					pInfo.setChildMenus(cinfos);
				}
			}
		}

		return ms;

	}

	@Override
	public List<MenuInfo> queryMenusByParent(String parentId) {
		// TODO Auto-generated method stub
		return menuDao.queryMenusByParent(parentId);
	}

}
