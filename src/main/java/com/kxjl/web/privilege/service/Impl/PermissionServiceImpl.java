package com.kxjl.web.privilege.service.Impl;

import com.kxjl.web.privilege.dao.PermissionMapper;
import com.kxjl.web.privilege.dao.RoleDao;
import com.kxjl.web.privilege.model.Permission;
import com.kxjl.web.privilege.model.Role;
import com.kxjl.web.privilege.service.PermissionService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RoleDao roleDao;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {
		return permissionMapper.deleteByPrimaryKey(id);
	}

	public int insert(Permission record) {
		return permissionMapper.insert(record);
	}

	public JSONObject insertSelective(Permission record) {

		JSONObject rtn = new JSONObject();

		try {

			permissionMapper.insertSelective(record);

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}

			return rtn;
		}

	}

	public JSONObject saveOrUpdate(Permission record) {
		if (null == record.getMenuId() || "".equals(record.getMenuId()))
			return insertSelective(record);
		else
			return updateByPrimaryKeySelective(record);
	}

	public Permission selectByPrimaryKey(String id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 构造权限树数据 3级菜单/含按钮
	 * 
	 * @param role_id
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<String> getPermissionTreeThree(String role_id) {
		List<String> lstTree = new ArrayList<String>();

		try {

			lstTree = buildPTree(role_id, 3,true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstTree;
	}

	/**
	 * 构造权限树
	 * 
	 * @param level
	 * @return
	 * @author zj
	 * @date 2018年5月31日
	 */
	private List<String> buildPTree(String role_id, int level,boolean isopen) {
		List<String> lstTree = new ArrayList<String>();

		try {

			Role q=new Role();
			q.setRole_en(role_id);
			Role bean = roleDao.getRoleInfoById(q);

			List<Permission> selectMenus = getRolePermissionList(bean);

			// 一级菜单
			Permission query = new Permission();
			query.setType("1");
			query.setAvailable("1");
			List<Permission> menus = selectPermissionList(query);

			String rootid = "0";
			for (Permission menu : menus) {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append("{");
				sBuffer.append("id:\"" + menu.getMenuId() + "\",");
				sBuffer.append("pId:\"" + rootid + "\",");
				
				sBuffer.append("open:"+(isopen?"true":"false")+",");// 根节点打开

				if (selectMenus != null)
					for (int i = 0; i < selectMenus.size(); i++) {
						if (selectMenus.get(i).getMenuId().equals(menu.getMenuId())) {
							sBuffer.append("checked:true,");// 选中
							break;
						}
					}

				sBuffer.append("name:\"" + menu.getMenuName() + "\",");

				sBuffer.append("remark:\"" + "" + "\"");
				// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
				// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

				sBuffer.append("}");
				lstTree.add(sBuffer.toString());

			}

			for (int i = 0; i < menus.size(); i++) {
				Permission query2 = new Permission();
				query2.setMenuParentid(menus.get(i).getMenuId());
				query2.setAvailable("1");
				List<Permission> all_menus = selectPermissionList(query2);

				for (Permission menu : all_menus) {
					StringBuffer sBuffer = new StringBuffer();
					sBuffer.append("{");
					sBuffer.append("id:\"" + menu.getMenuId() + "\",");
					sBuffer.append("pId:\"" + menus.get(i).getMenuId() + "\",");
					sBuffer.append("open:"+(isopen?"true":"false")+",");// 根节点打开

					sBuffer.append("name:\"" + menu.getMenuName() + "\",");

					if (selectMenus != null)
						for (int j = 0; j < selectMenus.size(); j++) {
							if (selectMenus.get(j).getMenuId().equals(menu.getMenuId())) {
								sBuffer.append("checked:true,");// 选中
								break;
							}
						}

					sBuffer.append("remark:\"" + "" + "\"");
					// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
					// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

					sBuffer.append("}");
					lstTree.add(sBuffer.toString());

				}
			}
			
			
			if(level>=3)
			{
			//三级菜单按钮
			
			Permission query2 = new Permission();
			query2.setType("3");
			query2.setAvailable("1");
			List<Permission> all_menus = selectPermissionList(query2);

			for (Permission menu : all_menus) {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append("{");
				sBuffer.append("id:\"" + menu.getMenuId() + "\",");
				sBuffer.append("pId:\"" + menu.getMenuParentid() + "\",");
				sBuffer.append("open:"+(isopen?"true":"false")+",");// 根节点打开

				sBuffer.append("name:\"" + menu.getMenuName() + "\",");

				if (selectMenus != null)
					for (int j = 0; j < selectMenus.size(); j++) {
						if (selectMenus.get(j).getMenuId().equals(menu.getMenuId())) {
							sBuffer.append("checked:true,");// 选中
							break;
						}
					}

				sBuffer.append("remark:\"" + "" + "\"");
				// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
				// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

				sBuffer.append("}");
				lstTree.add(sBuffer.toString());

			}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstTree;
	}

	/**
	 * 构造权限树数据
	 * 
	 * @param role_id
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<String> getPermissionTreeSecond(String role_id) {
		List<String> lstTree = new ArrayList<String>();

		try {

			lstTree = buildPTree(role_id, 2,false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstTree;

	}

	public JSONObject updateByPrimaryKeySelective(Permission record) {

		JSONObject rtn = new JSONObject();

		try {

			if (null == record || null == record.getMenuId()) {
				rtn.put("result", false);
				rtn.put("message", "id为空");
				return rtn;
			}
			permissionMapper.updateByPrimaryKeySelective(record);

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}

			return rtn;
		}

	}

	public int updateByPrimaryKey(Permission record) {
		return permissionMapper.updateByPrimaryKey(record);
	}

	/**
	 * 获取角色的所有权限
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<Permission> getRolePermissionList(Role role) {
		return permissionMapper.getRolePermissionList(role);
	}

	/**
	 * 条件查询
	 * 
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public List<Permission> selectPermissionList(Permission permission) {
		return permissionMapper.selectPermissionList(permission);
	}

	@Override
	public List<Permission> selectPermissionsByRoleId(String roleId) {
		return permissionMapper.selectPermissionsByRoleId(roleId);
	}

	@Override
	public List<Permission> selectPermissionsByManagerId(String mangerId) {
		return permissionMapper.selectPermissionsByManagerId(mangerId);
	}


}
