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

import com.kxjl.admin.persistence.entity.KgEntityExample;
import com.kxjl.admin.persistence.entity.KgEntityKey;
import com.kxjl.admin.persistence.entity.KgKeyValueKey;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgDataLog;
import com.kxjl.admin.persistence.entity.KgEditClass;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgEditEntity;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.service.KgEditDataService;
import com.kxjl.admin.service.KgEditEntityService;
import com.kxjl.admin.service.KgEntityService;
import com.kxjl.admin.service.KgObjectSubGroupService;
import com.kxjl.admin.service.KgObjectToObjectService;
import com.kxjl.admin.service.KgPropertyService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.O2oUtil;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.util.PageUtil;
import com.kxjl.admin.persistence.adapter.dao.KgClassMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgDataLogMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditClassMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditDataMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditEntityMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEntityMapperAdapter;
import com.kxjl.admin.persistence.dao.KgEntityMapper;

/**
 * autoGenerated
 * 
 * @date 2020年06月08日 11:50:55
 * @author Generator
 */
@Service("kgEntityService")
public class KgEntityServiceImpl implements KgEntityService {

	@Autowired
	KgEntityMapperAdapter kgEntityMapper;

	@Autowired
	KgClassMapperAdapter kgClassMapper;

	@Autowired
	KgObjectToObjectService kgObjectToObjectService;

	@Autowired
	KgPropertyService kgPropertyService;

	@Autowired
	KgObjectSubGroupService kgObjectSubGroupService;
	@Autowired
	KgEditDataMapperAdapter kgEditDataMapperAdapter;

	@Autowired
	KgEditEntityMapperAdapter kgEditEntityMapperAdapter;
	@Autowired
	KgEditDataService kgEditDataService;
	@Autowired
	KgDataLogMapperAdapter kgDataLogMapperAdapter;

	@Autowired
	KgEditEntityService kgEditEntityService;

	/**
	 * <p>
	 * New Info
	 * </p>
	 * 
	 * @param kgEntity
	 * @return
	 */
	@Transactional
	public WZResponseEntity<?> add(LoginUser user, KgEntity kgEntity) {
		WZResponseEntity<?> rst = new WZResponseEntity<>();
		rst.setIsSuccess(false);

		if (kgEntity.getId() == null || kgEntity.getId().equals(""))
			kgEntity.setId(UUIDUtil.get32UUID());

		if (kgEntity.getVersionId() == null || kgEntity.getVersionId().equals(""))
			kgEntity.setVersionId("1.0.0");

		KgEntity tp = kgEntityMapper.selectByName(kgEntity);
		if (tp != null) {
			rst.setIsSuccess(false);
			rst.setErrorMsg("名称重复!");
		} else {

			if (user != null && user.getRoleId().contains(Constants.DEFAULT_ADMIN_ROLEID)) {

				kgEntity.setSubIds(kgEntity.getSubKgId());
				kgEntity.setSubKgId("");
				// 处理领域
				kgObjectSubGroupService.resetObjectSubGroupAttr(kgEntity.getId(), kgEntity.getSubIds(), "2");

				addNameAndCodePros(kgEntity);

				boolean isok = kgEntityMapper.insertSelective(kgEntity) > 0;
				rst.setIsSuccess(isok);

			} else {
				KgEditEntity data = new KgEditEntity(kgEntity);
				// data.setId(UUIDUtil.get32UUID());
				data.setId(kgEntity.getId());
				return kgEditEntityService.add(user, data);
			}

		}
		return rst;
	}

	/**
	 * 属性json数据中补充默认的名称，编码
	 * 
	 * @param kgEntity
	 * @author:kxjl
	 * @date 2020年7月14日
	 */
	@Transactional
	private void addNameAndCodePros(KgEntity kgEntity) {
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

	@Transactional
	public WZResponseEntity<?> saveOrmodify(LoginUser user, KgEntity item) {
		WZResponseEntity<?> rst = new WZResponseEntity<>();
		rst.setIsSuccess(false);

		KgEntity tp = kgEntityMapper.selectByName(item);
		if (tp != null) {
			item.setId(tp.getId());

			item.setVersionId(tp.getVersionId());
			rst = modify(user, item);
		} else {

			// 查询clsId
			KgClass cquery = new KgClass();
			cquery.setClsName(item.getClsName());
			KgClass kclass = kgClassMapper.selectByName(cquery);
			if (kclass != null) {
				item.setClsId(kclass.getId());
			} else {
				// 无所属概念
				// 新建默认

				try {

					// 直接指定默认
					KgClass dquery = new KgClass();
					dquery.setClsName("概念");
					KgClass kclassdefault = kgClassMapper.selectByName(dquery);
					item.setClsId(kclassdefault.getId());

				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			rst = add(user, item);
		}
		return rst;
	}

	/**
	 * 根据名称创建默认entity,默认归属 clsnam 概念
	 * 
	 * @param name
	 * @return
	 * @author:kxjl
	 * @date 2020年7月3日
	 */
	public KgEntity createByName(String name) {
		KgEntity item = new KgEntity();

		// 直接指定默认 cls

		String clsName = "概念";

		KgClass dquery = new KgClass();
		dquery.setClsName(clsName);
		KgClass kclassdefault = kgClassMapper.selectByName(dquery);
		item.setClsId(kclassdefault.getId());

		item.setId(UUIDUtil.get32UUID());
		item.setName(name);
		item.setFullName(name);

		item.setProperties("[]");
		item.setTags("[{\"label\":\"" + clsName + "\",\"value\":\"" + clsName + "\",\"key\":\"" + clsName + "\"}]");
		item.setVersionId(Constants.DEFULT_VERSION);

		kgEntityMapper.insertSelective(item);

		return item;
	}

	/**
	 * <p>
	 * Modify Info
	 * </p>
	 * 
	 * @param kgEntity
	 * @return
	 */
	@Transactional
	public WZResponseEntity<?> modify(LoginUser user, KgEntity kgEntity) {

		WZResponseEntity<?> rst = new WZResponseEntity<>();

		if (user != null && user.getRoleId().contains(Constants.DEFAULT_ADMIN_ROLEID)) {

			// KgEntity tp = kgEntityMapper.selectByName(kgEntity);
			// if (tp != null && !tp.getId().equals(kgEntity.getId())) {
			// rst.setIsSuccess(false);
			// rst.setErrorMsg("名称重复!");
			// } else {

			if (kgEntity.getSubKgId() != null&&!kgEntity.getSubKgId().equals("")) {
				kgEntity.setSubIds(kgEntity.getSubKgId());
				kgEntity.setSubKgId("");
				// 处理领域
				kgObjectSubGroupService.resetObjectSubGroupAttr(kgEntity.getId(), kgEntity.getSubIds(), "2");
			}

			addNameAndCodePros(kgEntity);

			boolean isok = kgEntityMapper.updateByPrimaryKeySelective(kgEntity) > 0;
			rst.setIsSuccess(isok);
			// }

		} else {
			return kgEditEntityService.modify(user, new KgEditEntity(kgEntity));
		}

		return rst;
	}

	/**
	 * <p>
	 * Delete
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Boolean delete(LoginUser user, String id, String version) {

		if (user != null && user.getRoleId().contains(Constants.DEFAULT_ADMIN_ROLEID)) {
			return deleteData(id, version);

		} else {
			return kgEditEntityService.delete(user, id);
		}

	}

	private Boolean deleteData(String id, String version) {
		if (id.contains(",")) {
			String[] ids = id.split(",");
			for (String tid : ids) {
				KgEntity key = new KgEntity();
				key.setId(tid);
				key.setDeleted("1");
				key.setVersionId(version);
				kgEntityMapper.updateByPrimaryKeySelective(key);
				deleteRelation(tid);
			}

			return true;

		} else {
			KgEntity key = new KgEntity();
			key.setId(id);
			key.setDeleted("1");
			key.setVersionId(version);

			deleteRelation(id);

			return kgEntityMapper.updateByPrimaryKeySelective(key) > 0;
		}
	}

	// 删除关系，此删除关系暂不能同步至neo4j
	private void deleteRelation(String id) {

		KgClass source = new KgClass();
		source.setId(id);
		// 合并概念关系
		// 替换所有sourceid-》targetId
		List<KgObjectToObject> relations = kgObjectToObjectService.getObjRelationByLevel(source, 1);
		for (int i = 0; i < relations.size(); i++) {

			KgObjectToObject relation = relations.get(i);
			relation.setDeleted("1");

			kgObjectToObjectService.modify(relation);
		}
	}

	/**
	 * <p>
	 * query info by id
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public KgEntity getOne(String id, String version) {
		KgEntity key = new KgEntity();
		key.setId(id);
		key.setVersionId(version);

		List<KgEntity> lst = kgEntityMapper.selectList(key);
		if (lst != null && lst.size() > 0)
			return lst.get(0);
		else {
			KgEditEntity editOne = kgEditEntityService.getOne(id);
			if (editOne != null)
			{
				//编辑中
				editOne.setMyEdit("true");
				return editOne;
				
			}

			return null;

		}

		// return kgEntityMapper.selectByPrimaryKey(key);
	}

	/**
	 * <p>
	 * query all info
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<KgEntity> getAll() {
		KgEntityExample example = new KgEntityExample();
		return kgEntityMapper.selectByExample(example);
	}

	/**
	 * 增加查询列表改动标记
	 * 
	 * @param user
	 * @param lst
	 * @author:kxjl
	 * @date 2020年8月6日
	 */
	private void addDiffFlag(LoginUser user, List<KgEntity> lst) {
		// 增加修改标记
		KgEditData equery = new KgEditData();
		equery.setEditUser(user.getUserId());
		equery.setAuditState("1");// 未提交
		List<KgEditData> edatas = kgEditDataMapperAdapter.selectList(equery);

		KgEditData equery2 = new KgEditData();
		equery2.setEditUser(user.getUserId());
		equery2.setAuditState("2");// 未提交
		List<KgEditData> edataAudits = kgEditDataMapperAdapter.selectList(equery2);

		for (KgEntity data : lst) {

			JSONArray edits = new JSONArray();

			for (KgEditData eitem : edatas) {
				JSONObject jedit = new JSONObject();

				if (data.getId().equals(eitem.getEditOriDataId()) || data.getId().equals(eitem.getEditDataId())

						|| (data.getId()).equals(O2oUtil.getOriId(eitem.getEditOriDataId()))

				) {

					jedit.put("editAction", eitem.getEditAction());
					jedit.put("auditState", "1");
					jedit.put("editDataId", eitem.getEditDataId());
					jedit.put("id", eitem.getId());
					jedit.put("dataType", eitem.getDataType());// 区分是修改了实体，还是修改了实体关系

					edits.add(jedit);

					// data.setMyEdit(eitem.getEditAction() + ":" + eitem.getEditDataId());
				}
			}

			for (KgEditData eitem : edataAudits) {
				JSONObject jedit = new JSONObject();
				if (data.getId().equals(eitem.getEditOriDataId()) || data.getId().equals(eitem.getEditDataId())
						|| (data.getId()).equals(O2oUtil.getOriId(eitem.getEditOriDataId()))

				) {
					jedit.put("editAction", eitem.getEditAction());
					jedit.put("auditState", "2");
					jedit.put("editDataId", eitem.getEditDataId());
					jedit.put("id", eitem.getId());
					jedit.put("dataType", eitem.getDataType());// 区分是修改了实体，还是修改了实体关系
					edits.add(jedit);
				}

				// data.setMyEdit("4:" + eitem.getEditDataId());
			}

			data.setMyEdit(edits.toJSONString());

		}

	}

	/**
	 * 根据实体id查询 指定用户编辑中的对应Edit实体数据<br>
	 * 或者根据编辑edit实体id直接查询编辑实体id,<br>
	 * 或者根据实体id查询实体<br>
	 * 
	 * @param entityid
	 * @return 指定实体id对应的当前编辑数据/指定编辑id对应的编辑数据/指定实体id对应的原始数据
	 * @author:kxjl
	 * @date 2020年8月19日
	 */
	public KgEntity getOneByIdInAudit(String userid, String entityid) {

		KgEntity tp = null;

		KgEditData q = new KgEditData();
		q.setEditUser(userid);
		q.setEditOriDataId(entityid);
		q.setQueryStates(new String[] { "1", "2" });
		// q.setAuditState("1");//待提交的 或者已提交
		List<KgEditData> edatas = kgEditDataMapperAdapter.selectList(q);
		if (edatas != null && edatas.size() > 0) {
			tp = kgEditEntityService.getOne(edatas.get(0).getEditDataId());
			if (tp != null)
				tp.setDataFromType("2");
		}

		if (tp == null) {

			KgEditData q2 = new KgEditData();
			q2.setEditUser(userid);
			q2.setEditDataId(entityid);
			q2.setQueryStates(new String[] { "1", "2" });
			// q.setAuditState("1");//待提交的 或者已提交
			List<KgEditData> edatas2 = kgEditDataMapperAdapter.selectList(q2);
			if (edatas2 != null && edatas2.size() > 0) {

				tp = kgEditEntityService.getOne(edatas2.get(0).getEditDataId());
				if (tp != null)
					tp.setDataFromType("2");
			}
		}
		if (tp == null) {
			tp = getOne(entityid, Constants.DEFULT_VERSION);
			if (tp != null)
				tp.setDataFromType("1");
		}

		return tp;

	}

	/**
	 * 
	 * @param example
	 * @return
	 * @author:kxjl
	 * @date 2020年6月9日
	 */
	public Page<KgEntity> selectList(LoginUser user, KgEntity query, Pagination pageCondition) {
		com.github.pagehelper.Page pageinfo = PageUtil.getPage(pageCondition);

		// 查询全部
		if (query.getClsId() != null && query.getClsId().equals("0"))
			query.setClsId(null);

		query.setCurUser(user.getUserId());
		List<KgEntity> lst = kgEntityMapper.selectList(query);

		// if (pageinfo.getTotal() > pageinfo.getPageSize()) {
		// 一页没显示完.
		if (query.getFullNameFirst() != null) {
			if (query.getFullName() == null)
				query.setFullName(query.getName());
			KgEntity kfullName = kgEntityMapper.selectByName(query);
			if (kfullName != null) {

				// 增加其他相关查询
				kfullName = getOne(kfullName.getId(), kfullName.getVersionId());
				// 存在指定名称

				// 数据排序到第一，或者添加到第一位
				int index = 0;
				boolean find = false;
				for (int i = 0; i < lst.size(); i++) {
					if (lst.get(i).getId().equals(kfullName.getId())) {
						index = i;
						find = true;
						break;
					}
				}

				if (index != 0 && find) {
					lst.add(0, lst.remove(index));
				}

				if (!find) {
					lst.add(0, kfullName);
				}

			}
		}
		// }

		addDiffFlag(user, lst);

		Page<KgEntity> page = new Page<KgEntity>();
		page.setResults(lst);
		page.setPageNo(pageinfo.getPageNum());
		page.setPageSize(pageinfo.getPageSize());
		page.setTotalRecord((int) pageinfo.getTotal());

		return page;
	}

	/**
	 * 查询实体 所属概念上的属性
	 * 
	 * @param kgRelation
	 * @return
	 * @author:kxjl
	 * @date 2020年6月19日
	 */
	public List<KgProperty> getProperty(KgEntity kgRelation) {
		return kgEntityMapper.getProperty(kgRelation);
	}

	@Transactional
	public WZResponseEntity<?> audit(LoginUser user, KgEntity kgItem, KgEditData editData) {

		WZResponseEntity<?> rst = new WZResponseEntity<>();

		String auditRstId = "";// 当前审核合并后的数据id,也存到 对应的edit表中.
		if (editData.getAuditState().equals("5")) // 不通过
		{

		} else if (editData.getAuditState().equals("3") || editData.getAuditState().equals("4")) // 通过
		{

			KgEntity olddata = getOne(kgItem.getId(), Constants.DEFULT_VERSION);

			// 多人修改,并且多个人全部为删除操作，则最后删除
			if (editData.getEditAction().contains(",")) {
				String[] actions = editData.getEditAction().split(",");
				boolean alldelete = true;
				for (String ac : actions) {
					if (!ac.equals("3")) {
						alldelete = false;
						break;
					}
				}
				if (alldelete) {
					deleteData(editData.getEditOriDataId(), Constants.DEFULT_VERSION);
				}

			}

			// 修改真实数据
			if (editData.getEditAction().equals("3"))// 单人删除
			{
				deleteData(editData.getEditOriDataId(), Constants.DEFULT_VERSION);
			} else if (editData.getEditAction().equals("1"))// 单人新增
			{

				rst = add(user, kgItem);
				if (!rst.getIsSuccess())
					return rst;
			} else // 合并修改，只要有一个人不删除，均按修改操作
			{
				rst = modify(user, kgItem);
				if (!rst.getIsSuccess())
					return rst;
			}

			// 保存当前审核结果数据备份，包括删除的
			KgEntity auditRst = getOne(kgItem.getId(), Constants.DEFULT_VERSION);
			if (auditRst != null) {

				KgEditEntity edata = new KgEditEntity(auditRst);
				auditRstId = UUIDUtil.get32UUID();
				edata.setId(auditRstId);
				kgEditEntityService.addtionData(user, edata);
				kgEditEntityMapperAdapter.insertSelective(edata);

			} else {
				// 保存删除的快照
				KgEditEntity eolddata = new KgEditEntity(olddata);
				auditRstId = UUIDUtil.get32UUID();
				eolddata.setId(auditRstId);
				eolddata.setDeleted("0");
				kgEditEntityService.addtionData(user, eolddata);
				kgEditEntityMapperAdapter.insertSelective(eolddata);
			}

			// 保存快照数据id
			editData.setAuditRstId(auditRstId);

			String idold = "";
			if (olddata != null) {
				// 保存编辑前数据备份-》edit表
				KgEditEntity eolddata = new KgEditEntity(olddata);
				idold = UUIDUtil.get32UUID();
				eolddata.setId(idold);

				kgEditEntityService.addtionData(user, eolddata);
				kgEditEntityMapperAdapter.insertSelective(eolddata);
			}

			// 记录数据修改日志
			KgDataLog dlog = new KgDataLog();
			dlog.setId(UUIDUtil.get32UUID());
			dlog.setAuditUser(user.getUserId());
			dlog.setDataId(kgItem.getId());
			dlog.setDataType("6");
			dlog.setEditAction(editData.getEditAction());
			dlog.setEditUser(editData.getEditUser());
			dlog.setEditUserName(editData.getEditUserName());

			// 记录对应备份的数据id
			dlog.setEditDataPre(idold);
			dlog.setEditDataNext(auditRstId);

			kgDataLogMapperAdapter.insertSelective(dlog);

		}

		// 修改审核数据
		kgEditDataService.audit(user, editData);

		rst.setIsSuccess(true);

		return rst;
	}

}