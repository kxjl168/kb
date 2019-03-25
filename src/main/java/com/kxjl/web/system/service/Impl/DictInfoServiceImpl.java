package com.kxjl.web.system.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.privilege.model.Permission;
import com.kxjl.web.privilege.model.Role;
import com.kxjl.web.system.dao.DictInfoDao;
import com.kxjl.web.system.dao.MenuInfoDao;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.service.DictInfoService;

@Service(value = "dictInfoService")
public class DictInfoServiceImpl implements DictInfoService {

	@Autowired
	private DictInfoDao dictInfoDao;

	/**
	 * 分页获取banner列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<DictInfo> getDictInfoPageList(DictInfo query) {
		return dictInfoDao.getDictInfoPageList(query);
		// return null;
	}

	/**
	 * 获取banner总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getDictInfoPageListCount(DictInfo query) {
		return dictInfoDao.getDictInfoPageListCount(query);
	}

	/**
	 * 添加DictInfo
	 * 
	 * @param DictInfo
	 * @return
	 */
	public int addDictInfo(DictInfo DictInfo) {
		return dictInfoDao.addDictInfo(DictInfo);
	}

	/**
	 * 删除DictInfo
	 * 
	 * @param id
	 * @return
	 */
	public int deleteDictInfo(@Param(value = "id") Integer id) {
		return dictInfoDao.deleteDictInfo(id);
	}

	/**
	 * 更新DictInfo
	 * 
	 * @param DictInfo
	 * @return
	 */
	public int updateDictInfo(DictInfo DictInfo) {
		return dictInfoDao.updateDictInfo(DictInfo);
	}

	/**
	 * 根据ID获取DictInfo信息
	 * 
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public DictInfo getDictInfoInfoById(DictInfo query) {
		return dictInfoDao.getDictInfoInfoById(query);
	}

	/**
	 * 构造分类树
	 * 
	 * @param level
	 * @return
	 * @author zj
	 * @date 20190211
	 */
	private List<String> buildPTree(String dict_type, int level, boolean isopen) {
		List<String> lstTree = new ArrayList<String>();

		try {

			/*
			 * Role q=new Role(); q.setRole_en(role_id); Role bean =
			 * roleDao.getRoleInfoById(q);
			 */

			// 空
			List<DictInfo> selectMenus = new ArrayList<>();

			// 一级分类
			DictInfo query = new DictInfo();
			query.setDict_level("1");
			query.setEnable("1");
			query.setDict_type(dict_type);
			List<DictInfo> menus = getDictInfoPageList(query);

			String rootid = "0";
			for (DictInfo menu : menus) {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append("{");
				sBuffer.append("id:\"" + menu.getId() + "\",");
				sBuffer.append("pId:\"" + rootid + "\",");

				sBuffer.append("open:" + (isopen ? "true" : "false") + ",");// 根节点打开

				if (selectMenus != null)
					for (int i = 0; i < selectMenus.size(); i++) {
						if (selectMenus.get(i).getId().equals(menu.getId())) {
							sBuffer.append("checked:true,");// 选中
							break;
						}
					}

				sBuffer.append("name:\"" + menu.getDict_name() + "\",");

				sBuffer.append("remark:\"" + "" + "\"");
				// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
				// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

				sBuffer.append("}");
				lstTree.add(sBuffer.toString());

			}

			for (int i = 0; i < menus.size(); i++) {
				DictInfo query2 = new DictInfo();
				query2.setParent_id(String.valueOf(menus.get(i).getId()));
				query2.setEnable("1");
				query2.setDict_type(dict_type);
				List<DictInfo> all_menus = getDictInfoPageList(query2);

				for (DictInfo menu : all_menus) {
					StringBuffer sBuffer = new StringBuffer();
					sBuffer.append("{");
					sBuffer.append("id:\"" + menu.getId() + "\",");
					sBuffer.append("pId:\"" + menus.get(i).getId() + "\",");
					sBuffer.append("open:" + (isopen ? "true" : "false") + ",");// 根节点打开

					sBuffer.append("name:\"" + menu.getDict_name() + "\",");

					if (selectMenus != null)
						for (int j = 0; j < selectMenus.size(); j++) {
							if (selectMenus.get(j).getId().equals(menu.getId())) {
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
	public List<String> getDictTreeSecond(String dict_type) {
		List<String> lstTree = new ArrayList<String>();

		try {

			lstTree = buildPTree(dict_type, 2, false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstTree;

	}

	/**
	 * 构造 select2 group数据
	 * 
	 * @param dict_type
	 * @return
	 * @author zj
	 * @date 20190211
	 */
	public List<String> getDictTreeSelectSecond(String dict_type) {

		List<String> rst = new ArrayList<>();

		// 一级分类
		DictInfo query = new DictInfo();
		query.setDict_level("1");
		query.setEnable("1");
		query.setDict_type(dict_type);
		List<DictInfo> menus = getDictInfoPageList(query);

		Gson gs = new Gson();

		for (int i = 0; i < menus.size(); i++) {

			JSONObject jsObj = new JSONObject();

			DictInfo pInfo = menus.get(i);
			String pInfoStr = gs.toJson(pInfo);
			jsObj = new JSONObject(pInfoStr);

			DictInfo query2 = new DictInfo();
			query2.setParent_id(String.valueOf(menus.get(i).getId()));
			query2.setEnable("1");
			query2.setDict_type(dict_type);
			query2.setPageCount(30); //默认最多30个子分类
			List<DictInfo> all_menus = getDictInfoPageList(query2);

			String level2list = gs.toJson(all_menus);

			jsObj.put("child", level2list);
			jsObj.put("httppath", 	ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH"));

			rst.add(jsObj.toString());

		}

		return rst;

	}

}
