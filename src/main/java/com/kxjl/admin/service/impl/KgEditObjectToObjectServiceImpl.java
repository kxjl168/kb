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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgDataAuditLog;
import com.kxjl.admin.persistence.entity.KgEditClass;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgEditEntity;
import com.kxjl.admin.persistence.entity.KgEditObjectToObject;
import com.kxjl.admin.persistence.entity.KgEditRelation;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.persistence.entity.KgObjectToObjectKey;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.service.KgClassService;
import com.kxjl.admin.service.KgDataAuditLogService;
import com.kxjl.admin.service.KgEditDataService;
import com.kxjl.admin.service.KgEditEntityService;
import com.kxjl.admin.service.KgEditObjectToObjectService;
import com.kxjl.admin.service.KgEntityService;
import com.kxjl.admin.service.KgObjectToObjectService;
import com.kxjl.admin.service.KgRelationService;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.O2oUtil;
import com.kxjl.admin.util.Page;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.persistence.adapter.dao.KgClassMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgDataAuditLogMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditClassMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditDataMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditEntityMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditObjectToObjectMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditRelationMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEntityMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgObjectToObjectMapperAdapter;

/**
 * 具体开发人员请在此补充上类描述说明
 * 
 * @date 2020年08月03日 13:27:07
 * @author 具体开发人员请在此补充上本人名称拼音
 */
@Service
public class KgEditObjectToObjectServiceImpl implements KgEditObjectToObjectService {

	@Autowired
	KgEditObjectToObjectMapperAdapter kgEditItemMapper;

	@Autowired
	KgObjectToObjectService kgItemService;

	@Autowired
	KgEditDataService kgEditDataService;

	@Autowired
	KgObjectToObjectMapperAdapter kgObjectToObjectMapper;

	@Autowired
	KgEntityService kgEntityService;

	@Autowired
	KgClassService kgClassService;

	@Autowired
	KgRelationService kgRelationService;

	@Autowired
	KgEntityMapperAdapter kgEntityMapperAdapter;

	@Autowired
	KgClassMapperAdapter kgClassMapperAdapter;

	@Autowired
	KgObjectToObjectService kgObjectToObjectService;

	@Autowired
	KgEditEntityMapperAdapter kgEditEntityMapperAdapter;

	@Autowired
	KgEditEntityService kgEditEntityService;

	@Autowired
	KgEditClassMapperAdapter kgEditClassMapperAdapter;

	@Autowired
	KgDataAuditLogMapperAdapter kgDataAuditLogMapperAdapter;

	@Autowired
	KgEditRelationMapperAdapter kgEditRelationMapperAdapter;

	@Autowired
	KgEditDataMapperAdapter kgEditDataMapperAdapter;

	/**
	 * 
	 * @param user
	 * @param data
	 * @param snapshoot
	 * @return
	 * @author:kxjl
	 * @date 2020年8月25日
	 */
	public Boolean saveontology(LoginUser user, String data, Boolean snapshoot) {
		JSONObject jdata = JSONObject.parseObject(data);

		JSONArray jarrayNodes = jdata.getJSONArray("nodes");

		JSONArray jarrayEdges = jdata.getJSONArray("edges");

		// UI 删除的关系
		JSONArray jarrayDelEdges = jdata.getJSONArray("delete");

		// 正常保存对应 edit_data表id， 审核保存快照时对应 edit_ori_id
		String oriId = jdata.getString("oriId");

		// oriId=oriId.startsWith("r:")?oriId:"r:"+oriId;

		// 多次编辑
		// 先清空realtion数据
		kgEditItemMapper.deleteByEDataId(oriId);

		boolean neo4j = jdata.getBoolean("neo4j");

		String realoriId = oriId;// oriId.startsWith("r:")?oriId.substring(2):oriId;

		// if(oriId.contains(":"))
		// {
		// realoriId= oriId.split(":")[1];
		// }
		// realoriId=O2oUtil.getOriId(oriId);

		String editAction = ""; // entity, cls
		KgClass cls = kgClassService.getOne(realoriId, Constants.DEFULT_VERSION);
		if (cls != null)
			editAction = "1";// 1 cls
		KgEntity eentity = kgEntityService.getOne(realoriId, Constants.DEFULT_VERSION);
		if (eentity != null)
			editAction = "2";// 2 entity

		// Driver driver = neo4jHelper.createDrive();

		// try (Session session = driver.session();) {

		KgEditData eddatanew = kgEditDataService.getOne(oriId);
		if (eddatanew == null) {
			KgEditObjectToObject o2oData = new KgEditObjectToObject();
			o2oData.setId(UUIDUtil.get32UUID());

			String generiId = O2oUtil.generateOriId(oriId);

			o2oData.setOriId(generiId);// "r:+ 原始编辑cls 或者entityid。 区分实体/cls

			eddatanew = new KgEditData(o2oData, user.getUserId());
			eddatanew.setId(UUIDUtil.get32UUID());
			eddatanew.setEditAction(editAction);

			// 非保存审核快照才新增
			if (!snapshoot)
				kgEditDataService.add(eddatanew);
			else
				eddatanew.setId(oriId);
		}

		// 编辑中删除的关系，逻辑删除
		if (jarrayDelEdges != null)
			for (int i = 0; i < jarrayDelEdges.size(); i++) {

				if (jarrayDelEdges.getString(i) != null) {
					KgObjectToObject kquery = new KgObjectToObject();
					kquery.setId(jarrayDelEdges.getString(i));
					kquery.setVersionId("1.0.0");
					KgObjectToObject o2o = kgObjectToObjectMapper.selectByPrimaryKey(kquery);

					KgEditObjectToObject deleo2o = kgEditItemMapper.selectByPrimaryKey(jarrayDelEdges.getString(i));
					if (deleo2o != null) {
						deleo2o.setDeleted("1");
						kgEditItemMapper.updateByPrimaryKey(deleo2o);
					} else if (o2o != null) {

						// 保存
						KgEditObjectToObject eo = new KgEditObjectToObject(o2o);// 关联原始oid
						eo.setEditDataId(eddatanew.getId());// 关联edit id
						eo.setDeleted("1"); // 删除标记

						add(user, eo);
					}
				}
			}

		// 处理节点
		for (int i = 0; i < jarrayNodes.size(); i++) {
			JSONObject node = jarrayNodes.getJSONObject(i);
			String nodetype = node.getString("nodetype");
			String label = node.getString("label");
			String nodeid = node.getString("nodeid");

			// 如果编辑实体，保存实体上的属性修改
			if (nodetype != null && nodetype.equals("1")) {
				KgEntity entity = new KgEntity();
				entity.setId(nodeid);
				entity.setVersionId(Constants.DEFULT_VERSION);
				entity.setName(label);

				try {

					// 实体属性
					JSONArray jattr = node.getJSONArray("attrs");
					if (jattr != null)
						entity.setProperties(jattr.toJSONString());

					// 实体标签
					JSONArray tags = node.getJSONArray("tags");
					if (tags != null)
						entity.setTags(tags.toJSONString());
				} catch (Exception e) {

				}

				if (nodeid == null || nodeid.equals(""))
					entity.setId(UUIDUtil.get32UUID());

				// 正式，包括修改，审核中的，检查是否为编辑界面新建的实体
				KgEntity tp = kgEntityService.getOneByIdInAudit(user.getUserId(), entity.getId());

				if (tp != null) {
					try {

						// 检查是否有编辑中数据
						KgEditEntity etp = kgEditEntityMapperAdapter.selectByPrimaryKey(tp.getId());

						if (etp != null && (tp.getDataFromType().equals("2"))) {

							etp.setTags(entity.getTags());
							etp.setProperties(entity.getProperties());

							// 已有编辑状态 更新
							kgEditEntityService.modify(user, etp);
						} else {
							// 增加 已有数据的本地编辑状态

							// (tp.getTags()!=null&&!tp.getTags().equals(entity.getTags()) ||
							// 修改了属性才算修改

							String change = checkNodeChanged(tp, entity);

							if (!change.equals("")) {
								// 标签，属性有修改
								tp.setTags(entity.getTags());
								tp.setProperties(entity.getProperties());
								// tp.setSubIds(tp.getDirId());

								kgEditEntityService.modify(user, new KgEditEntity(tp));

							}

						}

					} catch (Exception e) {
						System.out.println(e);
					}
				} else {
					// 新增数据

					kgEntityService.add(user, entity);
				}

			}

		}

		if (jarrayEdges != null)
			for (int i = 0; i < jarrayEdges.size(); i++) {

				JSONObject edge = jarrayEdges.getJSONObject(i);

				KgObjectToObject kgObjectToObject = new KgObjectToObject();

				String o2oid = edge.getString("o2oid");

				kgObjectToObject.setRelationId(edge.getString("lineid"));
				kgObjectToObject.setId(o2oid);// id
				kgObjectToObject.setRelationName(edge.getString("label"));
				kgObjectToObject.setFromId(getNodeAttr(jarrayNodes, edge.getString("source"), "nodeid"));
				kgObjectToObject.setFromType(getNodeAttr(jarrayNodes, edge.getString("source"), "nodetype")); // 1实体
																												// 2class

				kgObjectToObject.setToId(getNodeAttr(jarrayNodes, edge.getString("target"), "nodeid"));

				kgObjectToObject.setToType(getNodeAttr(jarrayNodes, edge.getString("target"), "nodetype")); // 1实体
																											// 2class

				if (kgObjectToObject.getToId().equals("") || kgObjectToObject.getFromId().equals("")) {
					// 失败！
					return false;
				}

				kgObjectToObject.setVersionId("1.0.0");

				kgObjectToObject.setFromData("{\"x\":\"" + getNodeAttr(jarrayNodes, edge.getString("source"), "x")
						+ "\"," + "\"y\":\"" + getNodeAttr(jarrayNodes, edge.getString("source"), "y") + "\"}");
				kgObjectToObject.setToData("{\"x\":\"" + getNodeAttr(jarrayNodes, edge.getString("target"), "x") + "\","
						+ "\"y\":\"" + getNodeAttr(jarrayNodes, edge.getString("target"), "y") + "\"}");

				kgObjectToObject.setFromName(getNodeAttr(jarrayNodes, edge.getString("source"), "label"));
				kgObjectToObject.setToName(getNodeAttr(jarrayNodes, edge.getString("target"), "label"));

				JSONArray jattr = edge.getJSONArray("attrs");
				if (jattr != null)
					kgObjectToObject.setRelationProperties(jattr.toJSONString());

				if (o2oid == null || o2oid.equals("")) {
					o2oid = UUIDUtil.get32UUID();
					kgObjectToObject.setId(o2oid);
				}

				// OriID放置真真存在的o2o对象id;

				// 保存
				KgEditObjectToObject eo = new KgEditObjectToObject(kgObjectToObject);// 关联原始oid
				eo.setEditDataId(eddatanew.getId());// 关联edit id

				add(user, eo);

			}
		// }

		// driver.close();

		return true;

	}

	private KgEntity getEntityDetail(String id, String userId) {

		KgEntity data = kgEntityService.getOneByIdInAudit(userId, id);

		// 审核快照使用，快照数据没有edit_data关联，直接查询
		if (data == null)
			data = kgEditEntityService.getOne(id);
		return data;
	}

	private KgClass getClsDetail(String id, String userId) {
		KgClass data = kgClassService.getOneByIdInAudit(userId, id);

		// 审核快照使用，快照数据没有edit_data关联，直接查询
		if (data == null)
			data = kgClassService.getOne(id, Constants.DEFULT_VERSION);

		return data;
	}

	private KgRelation getRelationDetail(String id, String userId) {
		KgRelation data = kgRelationService.getOneByIdInAudit(userId, id);

		return data;
	}

	/**
	 * 检查节点数据是否修改
	 * 
	 * @param old
	 *            原始数据或者Edit_数据
	 * @param cur
	 *            当前界面数据
	 * @return
	 * @author:kxjl
	 * @date 2020年8月28日
	 */
	private String checkNodeChanged(KgEntity old, KgEntity cur) {
		String change = "";//

		if (old.getDataFromType().equals("2")) {
			change = "节点属性变更";
			return change;
		}

		try {
			// 连线上的属性修改
			if (!old.getProperties().equals(cur.getProperties())) {
				change = "节点属性变更";
				return change;
			}
		} catch (Exception e) {
			change = "节点属性变更";
			return change;
		}

		try {

			if (old.getTags() == null) {
				// 原始标签为空.
			} else {

				JSONArray otags = JSONArray.parseArray(old.getTags());

				JSONArray ntags = JSONArray.parseArray(cur.getTags());

				if (otags.size() != ntags.size()) {
					change = "节点标签变更";
					return change;
				}

				List<String> strotags = new ArrayList<>();
				List<String> strctags = new ArrayList<>();

				for (int i = 0; i < otags.size(); i++) {
					JSONObject jo = otags.getJSONObject(i);
					strotags.add(jo.getString("label"));
				}

				for (int i = 0; i < ntags.size(); i++) {
					JSONObject jo = ntags.getJSONObject(i);
					strctags.add(jo.getString("label"));
				}

				Collections.sort(strotags);
				Collections.sort(strctags);

				if (!strotags.equals(strctags)) {
					change = "节点标签变更";
					return change;
				}

			}
		} catch (Exception e) {
			change = "节点标签变更";
			return change;
		}

		return change;
	}

	/**
	 * 检查连线是否有修改，连线目标，来源是否修改，连线本身类型，属性是否有修改
	 * 
	 * @param eo2o
	 *            当前编辑中o2o
	 * @param re
	 *            relation对象，本地或者编辑数据
	 * @return
	 * @author:kxjl
	 * @date 2020年8月26日
	 */
	private String checkLineChanged(KgEditObjectToObject eo2o, KgRelation re) {
		String change = "";//

		// 检查连线对象本身是否有修改
		if (re.getDataFromType().equals("2")) {
			change = "关系类型修改";
			return change;
		}

		// 原始连线关系对象
		KgObjectToObject o2o = kgObjectToObjectService.getOne(eo2o.getOriId(), Constants.DEFULT_VERSION);
		if (o2o == null) {
			// 新增连线
			change = "新增关系";
			return change;
		}

		try {
			// 连线上的属性修改
			if (!o2o.getRelationProperties().equals(eo2o.getRelationProperties())) {
				change = "关系属性变更";
				return change;
			}
		} catch (Exception e) {
			change = "关系属性变更";
			return change;
		}

		// 连线起始/结束是否修改
		if ((!o2o.getFromId().equals(eo2o.getFromId())) || (!o2o.getToId().equals(eo2o.getToId()))) {
			change = "关系起始/结束节点变更";
			return change;
		}
		try {

			// 检查坐标是否修改
			if ((!o2o.getFromData().equals(eo2o.getFromData())) || (!o2o.getToData().equals(eo2o.getToData()))) {
				change = "关系起始/结束节点坐标变更";
				return change;
			}
		} catch (Exception e) {
			change = "关系起始/结束节点坐标变更";
			return change;
		}

		return change;
	}

	public String getGraDataByLevel(String id, int level) {

		// 根据editdata_id，查询关联的修改关系
		KgEditObjectToObject equery = new KgEditObjectToObject();
		equery.setEditDataId(id);
		// equery.setDeleted("0");// 未删除的
		List<KgEditObjectToObject> O2o = kgEditItemMapper.selectList(equery);

		String userid = "";
		KgEditData edta = kgEditDataService.getOne(id);
		if (edta != null) // 自己查看
			userid = edta.getEditUser();
		else {
			// 审核人员查看
			KgDataAuditLog lquery = new KgDataAuditLog();
			lquery.setAuditRstId(id);
			edta = kgDataAuditLogMapperAdapter.getEdataByAuditRstId(lquery);
			if (edta != null)
				userid = edta.getEditUser();
		}

		//
		// List<KgClass> nodes= getClassByLevel(kgClass, level);
		// List<KgRelation> edges= getRelationByLevel(kgClass, level);

		List<String> nodeids = new ArrayList<>();

		JSONArray jarrayNodes = new JSONArray();

		JSONArray jarrayEdges = new JSONArray();

		/* 编辑中删除的 */
		JSONArray jarrayDeletes = new JSONArray();

		for (int i = 0; i < O2o.size(); i++) {

			if (O2o.get(i).getDeleted().equals("1")) {
				// 删除的
				jarrayDeletes.add(O2o.get(i).getOriId());
				continue;
			}

			JSONObject jnodef = new JSONObject();

			// from
			jnodef.put("nodeid", O2o.get(i).getFromId());
			jnodef.put("id", O2o.get(i).getFromId());
			jnodef.put("nodetype", O2o.get(i).getFromType());
			if (O2o.get(i).getFromType().equals("1"))// entity
			{

				KgEntity entity = getEntityDetail(O2o.get(i).getFromId(), userid);
				if (entity != null) {
					// 实体要么是entity id,要么是 edit_entity id
					jnodef.put("label", entity.getName());
					jnodef.put("attrs", entity.getProperties());
					jnodef.put("tags", entity.getTags());

					jnodef.put("nodeid", entity.getId());
					// 图像显示本地已修改
					if (entity.getDataFromType() != null && entity.getDataFromType().equals("2"))
						jnodef.put("localmodify", "true");
				} else {

					// System.out.println("1");
				}
			}

			else {
				// 概念属性
				KgClass cls = getClsDetail(O2o.get(i).getFromId(), userid);
				if (cls != null) {
					jnodef.put("label", cls.getClsName());
					jnodef.put("attrNames", cls.getAttrNames());
					jnodef.put("nodeid", cls.getId());

					// 图像显示本地已修改
					if (cls.getDataFromType() != null && cls.getDataFromType().equals("2")) {
						jnodef.put("localmodify", "true");

						// cls在保存过程不修改，加载关系时加载原clsid.

						// 检查是否有编辑中数据
						KgEditClass etp = kgEditClassMapperAdapter.selectByPrimaryKey(cls.getId());

						if (etp != null) {

							KgEditData eq = new KgEditData();
							eq.setEditDataId(etp.getId());
							List<KgEditData> lst = kgEditDataMapperAdapter.selectList(eq);
							if (lst != null && lst.size() > 0) {
								KgEditData editData = lst.get(0);

								jnodef.put("nodeid", editData.getEditOriDataId());
							}

						}
					}
				}
			}

			try {

				JSONObject fromdataxy = JSONObject.parseObject(O2o.get(i).getFromData());
				jnodef.put("x", (fromdataxy != null) ? fromdataxy.getDouble("x") : 100);
				jnodef.put("y", (fromdataxy != null) ? fromdataxy.getDouble("y") : 100);

			} catch (Exception e) {
				jnodef.put("x", 100);
				jnodef.put("y", 100);

			}

			// to
			JSONObject jnodet = new JSONObject();

			jnodet.put("nodeid", O2o.get(i).getToId());
			jnodet.put("id", O2o.get(i).getToId());
			jnodet.put("nodetype", O2o.get(i).getToType());

			if (O2o.get(i).getFromType().equals("1"))// entity
			{

				KgEntity entity = getEntityDetail(O2o.get(i).getToId(), userid);
				if (entity != null) {
					jnodet.put("label", entity.getName());
					jnodet.put("attrs", entity.getProperties());
					jnodet.put("tags", entity.getTags());
					jnodet.put("nodeid", entity.getId());

					// 图像显示本地已修改
					if (entity.getDataFromType() != null && entity.getDataFromType().equals("2"))
						jnodet.put("localmodify", "true");
				}
			}

			else {
				// 概念属性
				KgClass cls = getClsDetail(O2o.get(i).getToId(), userid);
				if (cls != null) {
					jnodet.put("label", cls.getClsName());
					jnodet.put("attrNames", cls.getAttrNames());
					jnodet.put("nodeid", cls.getId());
					// 图像显示本地已修改
					if (cls.getDataFromType() != null && cls.getDataFromType().equals("2")) {
						jnodet.put("localmodify", "true");

						// cls在保存过程不修改，加载关系时加载原clsid.
						// 检查是否有编辑中数据
						KgEditClass etp = kgEditClassMapperAdapter.selectByPrimaryKey(cls.getId());

						if (etp != null) {

							KgEditData eq = new KgEditData();
							eq.setEditDataId(etp.getId());
							List<KgEditData> lst = kgEditDataMapperAdapter.selectList(eq);
							if (lst != null && lst.size() > 0) {
								KgEditData editData = lst.get(0);

								jnodet.put("nodeid", editData.getEditOriDataId());
							}

						}
					}

				}
			}

			try {
				JSONObject todataxy = JSONObject.parseObject(O2o.get(i).getToData());
				jnodet.put("x", (todataxy != null) ? todataxy.getDouble("x") : 100);
				jnodet.put("y", (todataxy != null) ? todataxy.getDouble("y") : 100);
			} catch (Exception e) {
				jnodet.put("x", 100);
				jnodet.put("y", 100);
			}

			KgRelation re = getRelationDetail(O2o.get(i).getRelationId(), edta.getEditUser());

			// relation
			JSONObject jnoder = new JSONObject();
			jnoder.put("label", re.getName());
			jnoder.put("id", O2o.get(i).getId());
			jnoder.put("o2oid", O2o.get(i).getOriId());// id 原始id
			jnoder.put("lineid", re.getId());
			jnoder.put("source", O2o.get(i).getFromId());
			jnoder.put("target", O2o.get(i).getToId());
			jnoder.put("fromData", O2o.get(i).getFromData());
			jnoder.put("toData", O2o.get(i).getToData());

			jnoder.put("attrs", O2o.get(i).getRelationProperties());

			String linechaged = checkLineChanged(O2o.get(i), re);
			jnoder.put("localmodify", linechaged);

			if (!nodeids.contains(O2o.get(i).getFromId())) {
				nodeids.add(O2o.get(i).getFromId());
				jarrayNodes.add(jnodef);
			}
			if (!nodeids.contains(O2o.get(i).getToId())) {
				nodeids.add(O2o.get(i).getToId());
				jarrayNodes.add(jnodet);
			}

			jarrayEdges.add(jnoder);

		}

		// 检查一对节点多个连线问题，分别连接不同节点
		// targetAnchor
		jarrayEdges = kgObjectToObjectService.CheckOverlapRelation(jarrayEdges);

		if (O2o.size() == 0) {

		}

		JSONObject jrst = new JSONObject();
		jrst.put("nodes", jarrayNodes);
		jrst.put("edges", jarrayEdges);
		jrst.put("delete", jarrayDeletes);

		return jrst.toJSONString();
	}

	public KgObjectToObject getOne(String id, String version) {
		KgObjectToObjectKey key = new KgObjectToObjectKey();
		key.setId(id);
		key.setVersionId(version);
		return kgObjectToObjectMapper.selectByPrimaryKey(key);
	}

	private String getNodeAttr(JSONArray jarrayNodes, String nodeid, String attrName) {

		String name = "";
		for (int i = 0; i < jarrayNodes.size(); i++) {
			JSONObject edge = jarrayNodes.getJSONObject(i);
			if (edge.getString("id").equals(nodeid)) {
				name = edge.getString(attrName);
				break;
			}
		}

		return name;

	}

	/**
	 * <p>
	 * 保存新的草稿及 编辑数据
	 * </p>
	 * 
	 * @param kgEditEntity
	 * @return
	 */
	@Transactional
	public Boolean add(LoginUser user, KgEditObjectToObject kgEditEntity) {

		if (kgEditEntity.getId() == null || kgEditEntity.getId().equals(""))
			kgEditEntity.setId(UUIDUtil.get32UUID());

		// kgEditDataService.add(new KgEditData(kgEditEntity, user.getUserId()));

		return kgEditItemMapper.insertSelective(kgEditEntity) > 0;
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
	public Boolean modify(LoginUser user, KgEditObjectToObject kgEditEntity) {

		if (kgEditEntity.getId() == null || kgEditEntity.getId().equals("")) {
			kgEditEntity.setId(UUIDUtil.get32UUID());
			//
			// KgEditData edata = new KgEditData(kgEditEntity, user.getUserId());
			// edata.setEditAction("2");
			// kgEditDataService.add(edata);
			//
			// kgEditEntity.setCreatedUser(user.getUserId());

			return kgEditItemMapper.insertSelective(kgEditEntity) > 0;
		}

		return kgEditItemMapper.updateByPrimaryKeySelective(kgEditEntity) > 0;
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
		KgEditObjectToObject esubgroup = getOne(id);
		if (esubgroup != null) {
			// 删除草稿

			// 撤销新增
			kgEditDataService.deleteByEditDataId(id);

			return kgEditItemMapper.deleteByPrimaryKey(id) > 0;
		} else {
			// 删除真是数据，记录审核

			KgObjectToObject group = kgItemService.getOne(id, Constants.DEFULT_VERSION);
			if (group != null) {

				KgEditObjectToObject kgEditEntity = new KgEditObjectToObject(group);
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
				return kgEditItemMapper.insertSelective(kgEditEntity) > 0;
			}
			return true;
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
	public KgEditObjectToObject getOne(String id) {
		return kgEditItemMapper.selectByPrimaryKey(id);
	}

}