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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kxjl.admin.persistence.entity.KgEditClass;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgEditEntity;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgEditEntity;
import com.kxjl.admin.persistence.entity.KgSubGroup;
import com.kxjl.admin.service.KgEditDataService;
import com.kxjl.admin.service.KgEditEntityService;
import com.kxjl.admin.service.KgEntityService;
import com.kxjl.admin.service.KgObjectSubGroupService;
import com.kxjl.admin.service.KgPropertyService;
import com.kxjl.admin.util.Constants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.persistence.adapter.dao.KgEditEntityMapperAdapter;

/**
 * 具体开发人员请在此补充上类描述说明
 * 
 * @date 2020年08月03日 13:27:07
 * @author 具体开发人员请在此补充上本人名称拼音
 */
@Service
public class KgEditEntityServiceImpl implements KgEditEntityService {

	@Autowired
	KgEditEntityMapperAdapter kgEditEntityMapper;

	@Autowired
	KgEntityService kgEntityService;

	@Autowired
	KgEditDataService kgEditDataService;

	@Autowired
	KgObjectSubGroupService kgObjectSubGroupService;

	@Autowired
	KgPropertyService kgPropertyService;

	/**
	 * <p>
	 * 保存新的草稿及 编辑数据
	 * </p>
	 * 
	 * @param kgEditEntity
	 * @return
	 */
	@Transactional
	public WZResponseEntity<?> add(LoginUser user, KgEditEntity kgEditEntity) {

		WZResponseEntity<?> rst = new WZResponseEntity<>();
		rst.setIsSuccess(false);

		if (kgEditEntity.getId() == null || kgEditEntity.getId().equals(""))
			kgEditEntity.setId(UUIDUtil.get32UUID());

		kgEditDataService.add(new KgEditData(kgEditEntity, user.getUserId()));

		addtionData(user, kgEditEntity);

		boolean isok = kgEditEntityMapper.insertSelective(kgEditEntity) > 0;
		rst.setIsSuccess(isok);
		return rst;
	}

	/**
	 * <p>
	 * 修改实际数据，或者修改本地草稿数据
	 * </p>
	 * 
	 * @param kgEditEntity
	 * @return
	 */
	@Transactional
	public WZResponseEntity<?> modify(LoginUser user, KgEditEntity kgEditEntity) {

		WZResponseEntity<?> rst = new WZResponseEntity<>();
		rst.setIsSuccess(false);

		if (kgEditEntity.getId() == null || kgEditEntity.getId().equals("")) {
			kgEditEntity.setId(UUIDUtil.get32UUID());

			KgEditData edata = new KgEditData(kgEditEntity, user.getUserId());
			edata.setEditAction("2");
			kgEditDataService.add(edata);

			kgEditEntity.setCreatedUser(user.getUserId());

			addtionData(user, kgEditEntity);

			boolean isok = kgEditEntityMapper.insertSelective(kgEditEntity) > 0;

			rst.setIsSuccess(isok);
			return rst;
		}

		addtionData(user, kgEditEntity);

		boolean isok = kgEditEntityMapper.updateByPrimaryKeySelective(kgEditEntity) > 0;
		rst.setIsSuccess(isok);
		return rst;
	}

	public void addtionData(LoginUser user, KgEditEntity kgEntity) {

		if (kgEntity.getSubKgId() != null && !kgEntity.getSubKgId().equals("")) {
			kgEntity.setSubIds(kgEntity.getSubKgId());
			kgEntity.setSubKgId("");
		}

		if (kgEntity.getSubIds() != null && !kgEntity.getSubIds().equals(""))
			// 处理领域
			kgObjectSubGroupService.resetObjectSubGroupAttr(kgEntity.getId(), kgEntity.getSubIds(), "2");

		addNameAndCodePros(kgEntity);

	}

	/**
	 * 属性json数据中补充默认的名称，编码
	 * 
	 * @param kgEntity
	 * @author:kxjl
	 * @date 2020年7月14日
	 */
	@Transactional
	private void addNameAndCodePros(KgEditEntity kgEntity) {
		String jsPros = kgEntity.getProperties();

		KgProperty proName = kgPropertyService.getOneByName("名称", Constants.DEFULT_VERSION);
		KgProperty proCode = kgPropertyService.getOneByName("编码", Constants.DEFULT_VERSION);

		JSONArray jarray = new JSONArray();

		if (jsPros == null || jsPros.equals("") || jsPros.equals("[]")) {

			JSONObject jname = new JSONObject();
			jname.put("rule", proName.getDataTypeRule());
			jname.put("name", proName.getName());
			jname.put("value", kgEntity.getName());
			jname.put("label", proName.getName());
			jname.put("id", proName.getId());

			JSONObject jcode = new JSONObject();
			jcode.put("rule", proCode.getDataTypeRule());
			jcode.put("name", proCode.getName());
			jcode.put("value", kgEntity.getId());
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
					ja.put("value", kgEntity.getName());
					hasName = true;
				}
				if (ja.getString("label").equals(proCode.getName())) {
					ja.put("value", kgEntity.getId());
					hasCode = true;
				}
			}

			if (!hasName) {

				JSONObject jname = new JSONObject();
				jname.put("rule", proName.getDataTypeRule());
				jname.put("name", proName.getName());
				jname.put("value", kgEntity.getName());
				jname.put("label", proName.getName());
				jname.put("id", proName.getId());
				jarray.add(jname);
			}
			if (!hasCode) {

				JSONObject jcode = new JSONObject();
				jcode.put("rule", proCode.getDataTypeRule());
				jcode.put("name", proCode.getName());
				jcode.put("value", kgEntity.getId());
				jcode.put("label", proCode.getName());
				jcode.put("id", proCode.getId());
				jarray.add(jcode);

			}

		}

		kgEntity.setProperties(jarray.toJSONString());
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

		KgEntity group = kgEntityService.getOne(id, Constants.DEFULT_VERSION);
		if (group != null) {

			KgEditEntity kgEditEntity = new KgEditEntity(group);
			kgEditEntity.setId(UUIDUtil.get32UUID());

			// 删除前面所有的本地修改记录
			KgEditData q = new KgEditData();
			q.setEditOriDataId(id);
			q.setEditUser(user.getUserId());
			kgEditDataService.deleteByOriDataIdAndUserId(q);

			// 增加删除记录
			KgEditData edata = new KgEditData(kgEditEntity, user.getUserId());
			edata.setEditAction("3");
			kgEditDataService.add(edata);

			// 添加原始数据
			return kgEditEntityMapper.insertSelective(kgEditEntity) > 0;
		} else {

			KgEditEntity esubgroup = kgEditEntityMapper.selectByPrimaryKey(id);
			if (esubgroup != null) {
				// 删除草稿

				// 撤销新增
				kgEditDataService.deleteByEditDataId(id);

				return kgEditEntityMapper.deleteByPrimaryKey(id) > 0;
			}
		}
		return true;

	}

	/**
	 * <p>
	 * query info by id
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public KgEditEntity getOne(String id) {
		// return kgEditEntityMapper.selectByPrimaryKey(id);

		KgEditEntity key = new KgEditEntity();
		key.setId(id);

		List<KgEditEntity> lst = kgEditEntityMapper.selectList(key);
		if (lst != null && lst.size() > 0)
			return lst.get(0);
		else
			return null;
	}

}