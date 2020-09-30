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

import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgEditProperty;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgPropertyDatatype;
import com.kxjl.admin.persistence.entity.KgPropertyDatatypeKey;
import com.kxjl.admin.service.KgEditDataService;
import com.kxjl.admin.service.KgEditPropertyService;
import com.kxjl.admin.service.KgPropertyService;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.persistence.adapter.dao.KgEditPropertyMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgPropertyDatatypeMapperAdapter;

/**
 * 具体开发人员请在此补充上类描述说明
 * 
 * @date 2020年08月03日 13:27:07
 * @author 具体开发人员请在此补充上本人名称拼音
 */
@Service
public class KgEditPropertyServiceImpl implements KgEditPropertyService {

	@Autowired
	KgEditPropertyMapperAdapter kgEditPropertyMapper;

	@Autowired
	KgPropertyService kgPropertyService;

	@Autowired
	KgEditPropertyService kgEditPropertyService;

	@Autowired
	KgEditDataService kgEditDataService;

	@Autowired
	KgPropertyDatatypeMapperAdapter kgPropertyDatatypeMapper;

	/**
	 * <p>
	 * 保存新的草稿及 编辑数据
	 * </p>
	 * 
	 * @param kgEditProperty
	 * @return
	 */
	@Transactional
	public WZResponseEntity<?> add(LoginUser user, KgEditProperty kgEditProperty) {

		WZResponseEntity rst = new WZResponseEntity<>();

		if (kgEditProperty.getId() == null || kgEditProperty.getId().equals(""))
			kgEditProperty.setId(UUIDUtil.get32UUID());

		kgEditDataService.add(new KgEditData(kgEditProperty, user.getUserId()));

		SetDataRule(kgEditProperty);

		boolean isok = kgEditPropertyMapper.insertSelective(kgEditProperty) > 0;
		rst.setBody(kgEditProperty.getId());

		rst.setIsSuccess(isok);

		return rst;
	}

	/**
	 * 填充数据属性规则信息，是否可以为空，长度等，==》json数据
	 * 
	 * @param kgProperty
	 * @author:kxjl
	 * @date 2020年6月17日
	 */
	public void SetDataRule(KgEditProperty kgProperty) {
		
		if(kgProperty.getDataTypeId()!=null)
		{
		KgPropertyDatatypeKey ptypeKey = new KgPropertyDatatypeKey();
		ptypeKey.setId(kgProperty.getDataTypeId());
		ptypeKey.setVersionId(Constants.DEFULT_VERSION);
		KgPropertyDatatype ptype = kgPropertyDatatypeMapper.selectByPrimaryKey(ptypeKey);

		if (ptype == null) {
			// 兼容导入 获取类型数据
			// dataTypeCode
			KgPropertyDatatype q = new KgPropertyDatatype();
			q.setCode(kgProperty.getDataTypeCode());
			q.setVersionId(Constants.DEFULT_VERSION);
			List<KgPropertyDatatype> rst = kgPropertyDatatypeMapper.selectList(q);
			if (rst != null && rst.size() > 0)
				ptype = rst.get(0);

		}

		ptype.setEnableEmpty(kgProperty.getCannull());

		JSONObject jobj = JSONObject.parseObject(JSONObject.toJSONString(ptype));
		jobj.put("clsId", kgProperty.getClsId());// 选择实体类型时的，限定cls类型

		kgProperty.setDataTypeRule(jobj.toJSONString());
		}
	}

	/**
	 * <p>
	 * 修改实际数据，或者修改本地草稿数据
	 * </p>
	 * 
	 * @param kgEditProperty
	 * @return
	 */
	@Transactional
	public WZResponseEntity modify(LoginUser user, KgEditProperty kgEditProperty) {

		WZResponseEntity rst = new WZResponseEntity<>();

		if (kgEditProperty.getId() == null || kgEditProperty.getId().equals("")) {
			kgEditProperty.setId(UUIDUtil.get32UUID());

			KgEditData edata = new KgEditData(kgEditProperty, user.getUserId());
			edata.setEditAction("2");
			kgEditDataService.add(edata);

			kgEditProperty.setCreatedUser(user.getUserId());
			SetDataRule(kgEditProperty);

			boolean isok = kgEditPropertyMapper.insertSelective(kgEditProperty) > 0;
			rst.setIsSuccess(isok);
			rst.setBody(kgEditProperty.getId());

			return rst;
		}

		SetDataRule(kgEditProperty);

		boolean isok = kgEditPropertyMapper.updateByPrimaryKeySelective(kgEditProperty) > 0;
		rst.setIsSuccess(isok);
		rst.setBody(kgEditProperty.getId());

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

		KgProperty group = kgPropertyService.getOne(id, Constants.DEFULT_VERSION);
		if (group != null) {

			KgEditProperty kgEditProperty = new KgEditProperty(group);
			kgEditProperty.setId(UUIDUtil.get32UUID());

			// 删除前面所有的本地修改记录
			KgEditData q = new KgEditData();
			q.setEditOriDataId(id);
			q.setEditUser(user.getUserId());
			kgEditDataService.deleteByOriDataIdAndUserId(q);

			// 增加删除记录
			KgEditData edata = new KgEditData(kgEditProperty, user.getUserId());
			edata.setEditAction("3");
			kgEditDataService.add(edata);

			SetDataRule(kgEditProperty);
			// 添加原始数据
			return kgEditPropertyMapper.insertSelective(kgEditProperty) > 0;
		}

		else {
			KgEditProperty esubgroup = kgEditPropertyMapper.selectByPrimaryKey(id);
			if (esubgroup != null) {
				// 删除草稿

				// 撤销新增
				kgEditDataService.deleteByEditDataId(id);

				return kgEditPropertyMapper.deleteByPrimaryKey(id) > 0;
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
	public KgEditProperty getOne(String id) {
		return kgEditPropertyMapper.selectByPrimaryKey(id);
	}

}