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

import com.kxjl.admin.persistence.entity.KgPropertyExample;
import com.kxjl.admin.persistence.entity.KgPropertyKey;
import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.persistence.entity.KgRelationKey;
import com.kxjl.admin.persistence.entity.KgSubGroup;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgDataLog;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgEditProperty;
import com.kxjl.admin.persistence.entity.KgEditSubGroup;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgPropertyDatatype;
import com.kxjl.admin.persistence.entity.KgPropertyDatatypeKey;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.service.KgEditDataService;
import com.kxjl.admin.service.KgEditPropertyService;
import com.kxjl.admin.service.KgPropertyService;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.util.PageUtil;
import com.kxjl.admin.persistence.adapter.dao.KgDataLogMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditDataMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditPropertyMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgPropertyDatatypeMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgPropertyMapperAdapter;
import com.kxjl.admin.persistence.dao.KgPropertyDatatypeMapper;
import com.kxjl.admin.persistence.dao.KgPropertyMapper;

/**
 * autoGenerated
 * 
 * @date 2020年06月08日 13:12:16
 * @author Generator
 */
@Service("kgPropertyService")
public class KgPropertyServiceImpl implements KgPropertyService {

	@Autowired
	KgPropertyMapperAdapter kgPropertyMapper;

	@Autowired
	KgPropertyDatatypeMapperAdapter kgPropertyDatatypeMapper;

	@Autowired
	KgEditPropertyService kgEditPropertyService;

	@Autowired
	KgEditPropertyMapperAdapter kgEditPropertyMapperAdapter;

	@Autowired
	KgEditDataMapperAdapter kgEditDataMapperAdapter;

	@Autowired
	KgEditDataService kgEditDataService;

	@Autowired
	KgDataLogMapperAdapter kgDataLogMapperAdapter;

	/**
	 * <p>
	 * New Info
	 * </p>
	 * 
	 * @param kgProperty
	 * @return
	 */
	@Transactional
	public WZResponseEntity<?> add(LoginUser user, KgProperty kgProperty) {

		WZResponseEntity rst = new WZResponseEntity<>();

		if (kgProperty.getId() == null || kgProperty.getId().equals(""))
			kgProperty.setId(UUIDUtil.get32UUID());

		if (kgProperty.getVersionId() == null || kgProperty.getVersionId().equals(""))
			kgProperty.setVersionId("1.0.0");

		KgProperty tp = kgPropertyMapper.selectByName(kgProperty);
		if (tp != null) {
			rst.setIsSuccess(false);
			rst.setErrorMsg("名称重复!");
		} else {

			if (user != null && user.getRoleId().contains(Constants.DEFAULT_ADMIN_ROLEID)) {

				SetDataRule(kgProperty);

				boolean isok = kgPropertyMapper.insertSelective(kgProperty) > 0;
				rst.setBody(kgProperty.getId());

				rst.setIsSuccess(isok);
			} else {
				KgEditProperty data = new KgEditProperty(kgProperty);
				//data.setId(UUIDUtil.get32UUID());
				data.setId(kgProperty.getId());
				return kgEditPropertyService.add(user, data);
			}

		}
		return rst;
	}

	/**
	 * 填充数据属性规则信息，是否可以为空，长度等，==》json数据
	 * 
	 * @param kgProperty
	 * @author:kxjl
	 * @date 2020年6月17日
	 */
	private void SetDataRule(KgProperty kgProperty) {
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

	@Transactional
	public WZResponseEntity<?> saveOrmodify(LoginUser user, KgProperty kgProperty) {
		WZResponseEntity<?> rst = new WZResponseEntity<>();

		KgProperty tp = kgPropertyMapper.selectByName(kgProperty);
		if (tp != null) {
			kgProperty.setId(tp.getId());
			kgProperty.setVersionId(tp.getVersionId());
			rst = modify(user, kgProperty);
		} else {
			rst = add(user, kgProperty);
		}
		return rst;
	}

	/**
	 * <p>
	 * Modify Info
	 * </p>
	 * 
	 * @param kgProperty
	 * @return
	 */
	@Transactional
	public WZResponseEntity modify(LoginUser user, KgProperty kgProperty) {

		WZResponseEntity rst = new WZResponseEntity<>();

		KgProperty tp = kgPropertyMapper.selectByName(kgProperty);
		if (tp != null && !tp.getId().equals(kgProperty.getId())) {
			rst.setIsSuccess(false);
			rst.setErrorMsg("名称重复!");
		} else {

			if (user != null && user.getRoleId().contains(Constants.DEFAULT_ADMIN_ROLEID)) {

				SetDataRule(kgProperty);

				boolean isok = kgPropertyMapper.updateByPrimaryKeySelective(kgProperty) > 0;
				rst.setIsSuccess(isok);
				rst.setBody(kgProperty.getId());
			} else {
				KgEditProperty data = new KgEditProperty(kgProperty);

				return kgEditPropertyService.modify(user, data);
			}

		}

		return rst;

	}

	/**
	 * 操作正式表数据
	 * 
	 * @param id
	 * @param version
	 * @return
	 * @author:kxjl
	 * @date 2020年8月5日
	 */
	private Boolean deleteData(String id, String version) {
		if (id.contains(",")) {
			String[] ids = id.split(",");
			for (String tid : ids) {

				KgProperty tp = getOne(tid, version);
				tp.setDeleted("1");// 1删除
				kgPropertyMapper.updateByPrimaryKey(tp);

			}
			return true;
		} else {

			KgProperty tp = getOne(id, version);
			tp.setDeleted("1");// 1删除
			return kgPropertyMapper.updateByPrimaryKey(tp) > 0 ? true : false;

		}
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
		// return kgPropertyMapper.deleteByPrimaryKey(id) > 0;

		if (user != null && user.getRoleId().contains(Constants.DEFAULT_ADMIN_ROLEID)) {

			return deleteData(id, version);

		} else {
			return kgEditPropertyService.delete(user, id);
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
	public KgProperty getOne(String id, String version) {
		KgPropertyKey key = new KgPropertyKey();
		key.setId(id);
		key.setVersionId(version);
		return kgPropertyMapper.selectByPrimaryKey(key);
	}

	/**
	 * <p>
	 * query all info
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<KgProperty> getAll() {
		KgPropertyExample example = new KgPropertyExample();
		return kgPropertyMapper.selectByExample(example);
	}

	/**
	 * 增加查询列表改动标记
	 * 
	 * @param user
	 * @param lst
	 * @author:kxjl
	 * @date 2020年8月6日
	 */
	private void addDiffFlag(LoginUser user, List<KgProperty> lst) {
		// 增加修改标记
		KgEditData equery = new KgEditData();
		equery.setEditUser(user.getUserId());
		equery.setAuditState("1");// 未提交
		List<KgEditData> edatas = kgEditDataMapperAdapter.selectList(equery);

		KgEditData equery2 = new KgEditData();
		equery2.setEditUser(user.getUserId());
		equery2.setAuditState("2");// 未提交
		List<KgEditData> edataAudits = kgEditDataMapperAdapter.selectList(equery2);

		JSONObject jedit = new JSONObject();

		for (KgProperty data : lst) {
			for (KgEditData eitem : edatas) {
				if (data.getId().equals(eitem.getEditOriDataId())||data.getId().equals(eitem.getEditDataId())) {

					jedit.put("editAction", eitem.getEditAction());
					jedit.put("auditState", "1");
					jedit.put("editDataId", eitem.getEditDataId());
					data.setMyEdit(jedit.toJSONString());
				}
			}

			for (KgEditData eitem : edataAudits) {
				if (data.getId().equals(eitem.getEditOriDataId())||data.getId().equals(eitem.getEditDataId())) {
					jedit.put("editAction", eitem.getEditAction());
					jedit.put("auditState", "2");
					jedit.put("editDataId", eitem.getEditDataId());
					data.setMyEdit(jedit.toJSONString());
				}
				// data.setMyEdit("4:" + eitem.getEditDataId());
			}

		}

	}

	/**
	 * 
	 * @param example
	 * @return
	 * @author:kxjl
	 * @date 2020年6月9日
	 */
	public Page<KgProperty> selectList(LoginUser user, KgProperty query, Pagination pageCondition) {
		com.github.pagehelper.Page pageinfo = PageUtil.getPage(pageCondition);

		query.setCurUser(user.getUserId());

		List<KgProperty> lst = kgPropertyMapper.selectList(query);
		addDiffFlag(user, lst);

		for (KgProperty kgProperty2 : lst) {

			try {
				
			KgPropertyDatatype ptype = JSONObject.parseObject(kgProperty2.getDataTypeRule(), KgPropertyDatatype.class);
			if (ptype != null) {
				kgProperty2.setCannull(ptype.getEnableEmpty());
				kgProperty2.setDataTypeId(ptype.getId());
			}

			} catch (Exception e) {
			continue;
			}
		}

		Page<KgProperty> page = new Page<KgProperty>();
		page.setResults(lst);
		page.setPageNo(pageinfo.getPageNum());
		page.setPageSize(pageinfo.getPageSize());
		page.setTotalRecord((int) pageinfo.getTotal());

		return page;
	}

	/**
	 * <p>
	 * query info by name
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public KgProperty getOneByName(String name, String versionId) {
		KgProperty q = new KgProperty();
		q.setName(name);
		q.setVersionId(versionId);
		return kgPropertyMapper.selectByName(q);
	}

	@Transactional
	public WZResponseEntity<?> audit(LoginUser user, KgProperty item, KgEditData editData) {

		WZResponseEntity<?> rst = new WZResponseEntity<>();

		String auditRstId = "";// 当前审核合并后的数据id,也存到 对应的edit表中.
		if (editData.getAuditState().equals("5")) // 不通过
		{

		} else if (editData.getAuditState().equals("3") || editData.getAuditState().equals("4")) // 通过
		{

			KgProperty olddata = getOne(item.getId(), Constants.DEFULT_VERSION);

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
				
				add(user,item);
			} else // 合并修改，只要有一个人不删除，均按修改操作
			{
				modify(user,item);
			}

			// 保存当前审核结果数据备份，包括删除的
			KgProperty auditRst = getOne(item.getId(), Constants.DEFULT_VERSION);
			if (auditRst != null) {

				KgEditProperty edata = new KgEditProperty(auditRst);
				auditRstId = UUIDUtil.get32UUID();
				edata.setId(auditRstId);
				kgEditPropertyService.SetDataRule(edata);
				kgEditPropertyMapperAdapter.insertSelective(edata);

			} else {
				// 保存删除的快照
				KgEditProperty eolddata = new KgEditProperty(olddata);
				auditRstId = UUIDUtil.get32UUID();
				eolddata.setId(auditRstId);
				eolddata.setDeleted("0");
				kgEditPropertyService.SetDataRule(eolddata);
				kgEditPropertyMapperAdapter.insertSelective(eolddata);
			}

			// 保存快照数据id
			editData.setAuditRstId(auditRstId);

			String idold = "";
			if (olddata != null) {
				// 保存编辑前数据备份-》edit表
				KgEditProperty eolddata = new KgEditProperty(olddata);
				idold = UUIDUtil.get32UUID();
				eolddata.setId(idold);
				kgEditPropertyService.SetDataRule(eolddata);
				kgEditPropertyMapperAdapter.insertSelective(eolddata);
			}

			// 记录数据修改日志
			KgDataLog dlog = new KgDataLog();
			dlog.setId(UUIDUtil.get32UUID());
			dlog.setAuditUser(user.getUserId());
			dlog.setDataId(item.getId());
			dlog.setDataType("4");
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