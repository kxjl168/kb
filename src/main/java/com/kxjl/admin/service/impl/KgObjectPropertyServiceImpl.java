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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.persistence.adapter.dao.KgObjectPropertyMapperAdapter;
import com.kxjl.admin.persistence.entity.KgObjectProperty;
import com.kxjl.admin.persistence.entity.KgObjectPropertyExample;
import com.kxjl.admin.persistence.entity.KgObjectPropertyKey;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.service.KgObjectPropertyService;
import com.kxjl.admin.service.KgObjectToObjectService;
import com.kxjl.admin.service.KgPropertyService;
import com.kxjl.admin.service.KgRelationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 具体开发人员请在此补充上类描述说明
 * 
 * @date 2020年06月16日 16:31:39
 * @author 具体开发人员请在此补充上本人名称拼音
 */
@Service
public class KgObjectPropertyServiceImpl implements KgObjectPropertyService {

	@Autowired
	KgObjectPropertyMapperAdapter kgObjectPropertyMapper;

	@Autowired
	KgPropertyService kgPropertyService;

	@Autowired
	KgRelationService kgRelationService;

	@Autowired
	KgObjectToObjectService kgObjectToObjectService;

	/**
	 * <p>
	 * New Info
	 * </p>
	 * 
	 * @param kgObjectProperty
	 * @return
	 */
	@Transactional
	public Boolean add(KgObjectProperty kgObjectProperty) {

		if (kgObjectProperty.getId() == null || kgObjectProperty.getId().equals(""))
			kgObjectProperty.setId(UUIDUtil.get32UUID());

		if (kgObjectProperty.getVersionId() == null || kgObjectProperty.getVersionId().equals(""))
			kgObjectProperty.setVersionId(Constants.DEFULT_VERSION);

		return kgObjectPropertyMapper.insertSelective(kgObjectProperty) > 0;
	}

	/**
	 * <p>
	 * Modify Info
	 * </p>
	 * 
	 * @param kgObjectProperty
	 * @return
	 */
	@Transactional
	public Boolean modify(KgObjectProperty kgObjectProperty) {
		return kgObjectPropertyMapper.updateByPrimaryKeySelective(kgObjectProperty) > 0;
	}

	/**
	 * <p>
	 * Delete
	 * </p>
	 * 
	 * @return
	 */
	@Transactional
	public Boolean delete(String id, String versionId) {
		KgObjectPropertyKey key = new KgObjectPropertyKey();
		// 设置key值
		key.setId(id);
		key.setVersionId(versionId);
		return kgObjectPropertyMapper.deleteByPrimaryKey(key) > 0;
	}

	public Boolean deleteByClsAndPro(String clsId, String proId) {
		KgObjectProperty key = new KgObjectProperty();
		// 设置key值
		key.setClsId(clsId);
		key.setPropId(proId);
		List<KgObjectProperty> kgs = kgObjectPropertyMapper.selectList(key);

		KgObjectProperty p = null;
		if (kgs != null && kgs.size() > 0)
			p = kgs.get(0);

		if (p != null)
			delete(p.getId(), p.getVersionId());
		
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
	public KgObjectProperty getOne(String id, String versionId) {
		KgObjectPropertyKey key = new KgObjectPropertyKey();
		// 设置key值
		key.setId(id);
		key.setVersionId(versionId);
		return kgObjectPropertyMapper.selectByPrimaryKey(key);
	}

	/**
	 * <p>
	 * query all info
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<KgObjectProperty> getAll() {
		KgObjectPropertyExample example = new KgObjectPropertyExample();
		return kgObjectPropertyMapper.selectByExample(example);
	}

	/**
	 * 更新概念上的属性，包括作为关系的属性
	 * 
	 * 方向操作，更新图数据时更新cls attr在 {@link KgObjectToObjectServiceImpl#saveontology(String)}
	 * @param id
	 * @param propids
	 *            属性id
	 * @return
	 * @author:kxjl
	 * @date 2020年6月17日
	 */
	public Boolean resetClsAttr(LoginUser user,String id, String propids) {
		Boolean rst = false;

		KgObjectProperty query = new KgObjectProperty();
		query.setClsId(id);
		query.setVersionId(Constants.DEFULT_VERSION);
		kgObjectPropertyMapper.deleteByClsId(query);
		if (propids == null)
			propids = "";
		String[] ids = propids.split(",");

		// 删除cls出发的所有关系
		KgObjectToObject ko2oquery = new KgObjectToObject();
		ko2oquery.setFromId(id);
		kgObjectToObjectService.deleteByFromClsId(ko2oquery);

		for (String pid : ids) {

			KgObjectProperty data = new KgObjectProperty();

			data.setClsId(id);
			data.setPropId(pid);

			add(data);

			// 添加关系/ cls关系
			KgProperty pro = kgPropertyService.getOne(pid, Constants.DEFULT_VERSION);
			try {
				JSONObject jpro = JSON.parseObject(pro.getDataTypeRule());
				if (jpro.getString("id").equals("2")) // 实体
				{
					JSONObject jcls = jpro.getJSONObject("clsId");// 实体限定概念

					// 检查 属性同名关系
					KgRelation kgRelation = new KgRelation();
					kgRelation.setName(pro.getName());
					kgRelationService.saveOrmodify(user,kgRelation);

					KgRelation curRelation = kgRelationService.selectByName(kgRelation);

					// 增加cls relation

					KgObjectToObject o2o = new KgObjectToObject();

					o2o.setFromId(id);
					o2o.setFromType("2"); // cls

					o2o.setToId(jcls.getString("key"));
					o2o.setToType("2"); // cls
					
					Random r = new Random(new Date().getTime());
					JSONObject frompoint = new JSONObject();
					frompoint.put("x", String.valueOf((r.nextDouble() * 500.0)));
					frompoint.put("y", String.valueOf((r.nextDouble() * 500.0)));
					o2o.setFromData(frompoint.toJSONString());

					JSONObject topoint = new JSONObject();
					topoint.put("x", String.valueOf((r.nextDouble() * 500.0)));
					topoint.put("y", String.valueOf((r.nextDouble() * 500.0)));
					o2o.setToData(topoint.toJSONString());

					o2o.setRelationName(pro.getName());
					o2o.setRelationId(curRelation.getId());
					kgObjectToObjectService.add(o2o);

				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		return rst;

	}

	/**
	 * 更新关系上的属性
	 * 
	 * @param id
	 * @param propids
	 *            属性id
	 * @return
	 * @author:kxjl
	 * @date 2020年6月17日
	 */
	public Boolean resetRelationAttr(String id, String propids) {
		Boolean rst = false;

		KgObjectProperty query = new KgObjectProperty();
		query.setRelationId(id);
		query.setVersionId(Constants.DEFULT_VERSION);
		kgObjectPropertyMapper.deleteByRelationId(query);

		if (propids == null)
			propids = "";
		String[] ids = propids.split(",");

		for (String pid : ids) {

			KgObjectProperty data = new KgObjectProperty();

			data.setRelationId(id);
			data.setPropId(pid);

			add(data);

		}

		return rst;

	}

}
