/**
 * Copyright (c) 2019 kxjl All Rights Reserved.
 * 
 * This software license agreement (the "Agreement") is a legal agreement between the user 
 * ("You" or the "User") and kxjl ("kxjl") 
 * for the software products (the "Software") and related services (the "Service") that 
 * accompanies this Agreement, as may be updated or replaced by feature enhancements, 
 * updates or maintenance releases and any services that may be provided by kxjl under this Agreement. 
 * You are not allowed to download, install or use the Software or to use Services unless 
 * you accept all the terms and conditions of this Agreement. Your downloading, 
 * installation and use of the Software shall be regarded as your acceptance of the Agreement 
 * and your agreement to be bound by all the terms and conditions of this Agreement.
 * 
 * The above notice shall be included in all copies or substantial portions of the Software.
 * 
 * The software is provided "as is", without warranty of any kind, express or implied, 
 * including but not limited to the warranties of merchantability, fitness for a particular 
 * purpose and noninfringement. In no event shall the authors or copyright holders be 
 * liable for any claim, damages or other liability, whether in an action of contract, 
 * tort or otherwise, arising from, out of or in connection with the software or the use 
 * or other dealings in the software.
 */
package com.kxjl.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgEditClass;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgEditClass;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.service.KgClassService;
import com.kxjl.admin.service.KgEditClassService;
import com.kxjl.admin.service.KgEditDataService;
import com.kxjl.admin.service.KgObjectPropertyService;
import com.kxjl.admin.service.KgObjectSubGroupService;
import com.kxjl.admin.service.KgObjectToObjectService;
import com.kxjl.admin.service.KgPropertyService;
import com.kxjl.admin.service.KgTagsService;
import com.kxjl.admin.util.Constants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.persistence.adapter.dao.KgEditClassMapperAdapter;

/**
 * 具体开发人员请在此补充上类描述说明
 * 
 * @date 2020年08月03日 13:27:07
 * @author 具体开发人员请在此补充上本人名称拼音
 */
@Service
public class KgEditClassServiceImpl implements KgEditClassService {

	@Autowired
	KgEditClassMapperAdapter kgEditItemMapper;

	@Autowired
	KgClassService kgItemService;

	@Autowired
	KgEditDataService kgEditDataService;

	@Autowired
	KgObjectPropertyService kgObjectPropertyService;

	@Autowired
	KgObjectSubGroupService kgObjectSubGroupService;

	@Autowired
	KgTagsService kgTagsService;

	@Autowired
	KgPropertyService kgPropertyService;

	/**
	 * <p>
	 * 保存新的草稿及 编辑数据
	 * </p>
	 * 
	 * @param kgEditItem
	 * @return
	 */
	@Transactional
	public WZResponseEntity<?> add(LoginUser user, KgEditClass kgEditItem) {

		WZResponseEntity<?> rst = new WZResponseEntity<>();
		rst.setIsSuccess(false);

		if (kgEditItem.getId() == null || kgEditItem.getId().equals(""))
			kgEditItem.setId(UUIDUtil.get32UUID());

		kgEditDataService.add(new KgEditData(kgEditItem, user.getUserId()));

		addtionData(user, kgEditItem);

		boolean isok = kgEditItemMapper.insertSelective(kgEditItem) > 0;
		rst.setIsSuccess(isok);

		return rst;
	}

	public void addtionData(LoginUser user, KgEditClass kgClass) {
		// 处理attrs
		kgObjectPropertyService.resetClsAttr(user, kgClass.getId(), kgClass.getAttrs());

		if (kgClass.getDirId() != null && !kgClass.getDirId().equals("")) {
			kgClass.setSubIds(kgClass.getDirId());
			kgClass.setDirId("");
		}
		// 处理领域
		kgObjectSubGroupService.resetObjectSubGroupAttr(kgClass.getId(), kgClass.getSubIds(), "1");

		if (kgClass.getParentId() == null || kgClass.getParentId().equals(""))
			kgClass.setParentId("0");

		// 添加对应 tag
		kgTagsService.addSameNameTag(kgClass);

		// 名称，编码
		addNameAndCodePros(kgClass);

	}

	/**
	 * 属性json数据中补充默认的名称，编码
	 * 
	 * @param kgEntity
	 * @author:kxjl
	 * @date 2020年7月14日
	 */
	@Transactional
	private void addNameAndCodePros(KgEditClass item) {
		String jsPros = item.getProperties();

		KgProperty proName = kgPropertyService.getOneByName("名称", Constants.DEFULT_VERSION);
		KgProperty proCode = kgPropertyService.getOneByName("编码", Constants.DEFULT_VERSION);

		JSONArray jarray = new JSONArray();

		if (jsPros == null || jsPros.equals("") || jsPros.equals("[]")) {

			JSONObject jname = new JSONObject();
			jname.put("rule", proName.getDataTypeRule());
			jname.put("name", proName.getName());
			jname.put("value", item.getClsName());
			jname.put("label", proName.getName());
			jname.put("id", proName.getId());

			JSONObject jcode = new JSONObject();
			jcode.put("rule", proCode.getDataTypeRule());
			jcode.put("name", proCode.getName());
			jcode.put("value", item.getId());
			jcode.put("label", proCode.getName());
			jcode.put("id", proCode.getId());

			jarray.add(jname);
			jarray.add(jcode);

		} else {
			jarray = JSONArray.parseArray(jsPros);

			boolean hasName = false;
			boolean hasCode = false;
			for (int i = 0; i < jarray.size(); i++) {
				JSONObject ja = jarray.getJSONObject(i);
				if (ja.getString("label").equals(proName.getName())) {
					ja.put("value", item.getClsName());
					hasName = true;
				}
				if (ja.getString("label").equals(proCode.getName())) {
					ja.put("value", item.getId());
					hasCode = true;
				}
			}

			if (!hasName) {

				JSONObject jname = new JSONObject();
				jname.put("rule", proName.getDataTypeRule());
				jname.put("name", proName.getName());
				jname.put("value", item.getClsName());
				jname.put("label", proName.getName());
				jname.put("id", proName.getId());
				jarray.add(jname);
			}
			if (!hasCode) {

				JSONObject jcode = new JSONObject();
				jcode.put("rule", proCode.getDataTypeRule());
				jcode.put("name", proCode.getName());
				jcode.put("value", item.getId());
				jcode.put("label", proCode.getName());
				jcode.put("id", proCode.getId());
				jarray.add(jcode);

			}

		}

		item.setProperties(jarray.toJSONString());
	}

	/**
	 * <p>
	 * 修改实际数据，或者修改本地草稿数据
	 * </p>
	 * 
	 * @param kgEditItem
	 * @return
	 */
	@Transactional
	public WZResponseEntity<?> modify(LoginUser user, KgEditClass kgEditItem) {

		WZResponseEntity<?> rst = new WZResponseEntity<>();
		rst.setIsSuccess(false);

		if (kgEditItem.getId() == null || kgEditItem.getId().equals("")) {
			kgEditItem.setId(UUIDUtil.get32UUID());

			KgEditData edata = new KgEditData(kgEditItem, user.getUserId());
			edata.setEditAction("2");
			kgEditDataService.add(edata);

			kgEditItem.setCreatedUser(user.getUserId());

			addtionData(user, kgEditItem);

			boolean isOk = kgEditItemMapper.insertSelective(kgEditItem) > 0;
			rst.setIsSuccess(isOk);
			return rst;
		}

		addtionData(user, kgEditItem);
		boolean isOk = kgEditItemMapper.updateByPrimaryKeySelective(kgEditItem) > 0;
		rst.setIsSuccess(isOk);
		return rst;
	}

	/**
	 * <p>
	 * 删除实际数据，增加删除的数据进入修改数据集
	 * </p>
	 * 
	 * @return
	 */
	@Transactional
	public Boolean delete(LoginUser user, String id) {

		if (id.contains(",")) {
			String[] ids = id.split(",");
			for (String tid : ids) {
				deleteon(user, tid);
			}
			return true;
		} else {
			return deleteon(user, id);
		}

	}

	@Transactional
	private boolean deleteon(LoginUser user, String id) {

		// 删除真是数据，记录审核

		KgClass group = kgItemService.getOne(id, Constants.DEFULT_VERSION);
		if (group != null) {

			KgEditClass kgEditItem = new KgEditClass(group);
			kgEditItem.setId(UUIDUtil.get32UUID());

			// 删除前面所有的本地修改记录
			KgEditData q = new KgEditData();
			q.setEditOriDataId(id);
			q.setEditUser(user.getUserId());
			kgEditDataService.deleteByOriDataIdAndUserId(q);

			// 增加删除记录
			KgEditData edata = new KgEditData(kgEditItem, user.getUserId());
			edata.setEditAction("3");
			kgEditDataService.add(edata);

			// 添加原始数据
			return kgEditItemMapper.insertSelective(kgEditItem) > 0;
		} else {

			KgEditClass esubgroup = kgEditItemMapper.selectByPrimaryKey(id);
			if (esubgroup != null) {
				// 删除草稿

				// 撤销新增
				kgEditDataService.deleteByEditDataId(id);

				return kgEditItemMapper.deleteByPrimaryKey(id) > 0;
			}
		}
		return true;

	}

	/**
	 * 递归所有父类属性
	 * 
	 * @param version
	 * @param cur
	 * @param attr
	 * @author:kxjl
	 * @date 2020年7月7日
	 */
	private void buildParentAttr(KgEditClass cur, Map<String, String> attr) {
		if (cur.getParentId() != null && !cur.getParentId().equals("0")) {
			KgEditClass ParentCls = getOne(cur.getParentId());
			buildAllAttr(ParentCls, attr);

			buildParentAttr(ParentCls, attr);
		}
	}

	private void buildAllAttr(KgEditClass cur, Map<String, String> attr) {
		if (cur.getAttrs() != null && cur.getAttrNames() != null) {
			String[] attrids = cur.getAttrs().split(",");
			String[] attrnames = cur.getAttrNames().split(",");

			for (int i = 0; i < attrids.length; i++) {
				attr.put(attrids[i], attrnames[i]);
			}

		}
	}

	/**
	 * <p>
	 * query info by id
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public KgEditClass getOne(String id) {
		// return kgEditItemMapper.selectByPrimaryKey(id);

		KgEditClass key = new KgEditClass();
		key.setId(id);

		List<KgEditClass> lst = kgEditItemMapper.selectList(key);
		if (lst != null && lst.size() > 0) {
			KgEditClass cur = lst.get(0);

			Map<String, String> attr = new HashMap<>();

			// 自身属性
			buildAllAttr(cur, attr);

			// if(showAllParentAttr)
			// 补充 所有属性，包括所有迭代父类属性。
			buildParentAttr(cur, attr);

			String attrids = "";
			String attrnames = "";
			for (String attrid : attr.keySet()) {
				attrids += attrid + ",";
				attrnames += attr.get(attrid) + ",";
			}
			if (attrids.endsWith(","))
				attrids = attrids.substring(0, attrids.length() - 1);
			if (attrnames.endsWith(","))
				attrnames = attrnames.substring(0, attrnames.length() - 1);

			cur.setAttrs(attrids);
			cur.setAttrNames(attrnames);

			return cur;
		} else
			return null;

	}

}