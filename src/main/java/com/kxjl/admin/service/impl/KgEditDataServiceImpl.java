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

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kxjl.admin.persistence.entity.KgDataAuditDetail;
import com.kxjl.admin.persistence.entity.KgDataAuditLog;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgSubGroup;
import com.kxjl.admin.persistence.entity.KgSubGroupKey;
import com.kxjl.admin.service.KgEditDataService;
import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.util.PageUtil;
import com.kxjl.admin.persistence.adapter.dao.KgDataAuditDetailMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgDataAuditLogMapperAdapter;
import com.kxjl.admin.persistence.adapter.dao.KgEditDataMapperAdapter;
import com.kxjl.admin.persistence.dao.KgDataAuditDetailMapper;

/**
 * 修改的草稿数据
 * 
 * @author kxjl
 * @date 2020年8月3日
 */
@Service
public class KgEditDataServiceImpl implements KgEditDataService {

	@Autowired
	KgEditDataMapperAdapter kgEditDataMapper;

	@Autowired
	KgDataAuditDetailMapperAdapter kgDataAuditDetailMapperAdapter;

	@Autowired
	KgDataAuditLogMapperAdapter kgDataAuditLogMapperAdapter;

	/**
	 * <p>
	 * New Info
	 * </p>
	 * 
	 * @param kgEditData
	 * @return
	 */
	@Transactional
	public Boolean add(KgEditData kgEditData) {

		if (kgEditData.getId() == null || kgEditData.getId().equals(""))
			kgEditData.setId(UUIDUtil.get32UUID());

		return kgEditDataMapper.insertSelective(kgEditData) > 0;
	}

	/**
	 * <p>
	 * Modify Info
	 * </p>
	 * 
	 * @param kgEditData
	 * @return
	 */
	@Transactional
	public Boolean modify(KgEditData kgEditData) {
		return kgEditDataMapper.updateByPrimaryKeySelective(kgEditData) > 0;
	}

	@Transactional
	public Boolean deleteByEditDataId(String id) {
		return kgEditDataMapper.deleteByEditDataId(id) > 0;
	}

	/**
	 * 根据真实id，用户id删除所有未提价的修改草稿
	 * 
	 * @param item
	 * @author:kxjl
	 * @date 2020年8月4日
	 */
	public Boolean deleteByOriDataIdAndUserId(KgEditData item) {
		return kgEditDataMapper.deleteByOriDataIdAndUserId(item) > 0;
	}

	/**
	 * <p>
	 * Delete
	 * </p>
	 * 
	 * @return
	 */
	@Transactional
	public Boolean delete(String id) {

		if (id.contains(",")) {
			String[] ids = id.split(",");
			for (String tid : ids) {

				kgEditDataMapper.deleteByPrimaryKey(tid);

			}
			return true;
		} else {

			return kgEditDataMapper.deleteByPrimaryKey(id) > 0;

		}
	}

	@Transactional
	public Boolean toaudit(String id) {

		if (id.contains(",")) {
			String[] ids = id.split(",");
			for (String tid : ids) {

				kgEditDataMapper.toaudit(tid);

			}
			return true;
		} else {

			return kgEditDataMapper.toaudit(id) > 0;

		}
	}
	
	public  Boolean tolocal(String id) {

		//单条操作草稿
		KgEditData cur = new KgEditData();
		cur.setId(id);
		cur.setAuditState("1");
		
		return kgEditDataMapper.updateByPrimaryKeySelective(cur)>-1;
		
		
	}


	/**
	 * <p>
	 * query info by id
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public KgEditData getOne(String id) {
		return kgEditDataMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * @param example
	 * @return
	 * @author:kxjl
	 * @date 2020年6月9日
	 */
	public Page<KgEditData> selectList(KgEditData query, Pagination pageCondition) {
		com.github.pagehelper.Page pageinfo = PageUtil.getPage(pageCondition);

		List<KgEditData> lst = kgEditDataMapper.selectList(query);

		Page<KgEditData> page = new Page<KgEditData>();
		page.setResults(lst);
		page.setPageNo(pageinfo.getPageNum());
		page.setPageSize(pageinfo.getPageSize());
		page.setTotalRecord((int) pageinfo.getTotal());

		return page;
	}

	/**
	 * 查询待审核数据，按实体分组，一个实体显示一条记录。 合并编辑用户，编辑数据ori_edit_id
	 * 
	 * @param example
	 * @return
	 * @author:kxjl
	 * @date 2020年8月5日
	 */
	public Page<KgEditData> selectToAuditList(KgEditData query, Pagination pageCondition) {
		com.github.pagehelper.Page pageinfo = PageUtil.getPage(pageCondition);

		List<KgEditData> lst = kgEditDataMapper.selectToAudit(query);

		Page<KgEditData> page = new Page<KgEditData>();
		page.setResults(lst);
		page.setPageNo(pageinfo.getPageNum());
		page.setPageSize(pageinfo.getPageSize());
		page.setTotalRecord((int) pageinfo.getTotal());

		return page;
	}
	
	public Page<KgDataAuditLog> listAuditDone(KgDataAuditLog query, Pagination pageCondition) {
		com.github.pagehelper.Page pageinfo = PageUtil.getPage(pageCondition);

		List<KgDataAuditLog> lst = kgDataAuditLogMapperAdapter.selectList(query);

		Page<KgDataAuditLog> page = new Page<KgDataAuditLog>();
		page.setResults(lst);
		page.setPageNo(pageinfo.getPageNum());
		page.setPageSize(pageinfo.getPageSize());
		page.setTotalRecord((int) pageinfo.getTotal());

		return page;
	}
	
	

	/**
	 * 审核数据-处理审核相关数据，各自的实体数据在各自service中处理<br>
	 * 处理 kg_edit_data表 原始草稿数据<br>
	 * 处理 kg_audit_data_log 记录审核<br>
	 * 处理 kg_audit_data_detail 关系表<br>
	 * 
	 * @param user
	 * @param editData
	 * @return
	 * @author:kxjl
	 * @date 2020年8月5日
	 */
	@Transactional
	public WZResponseEntity<?> audit(LoginUser user, KgEditData editData) {
		WZResponseEntity<?> rst = new WZResponseEntity<>();

		boolean pass = false;

		// 单实体的多个修改记录
		String editDataId = editData.getEditDataId();

		String[] ids = editData.getId().split(",");
		String info = editData.getAuditInfo();
		
		// 审核记录
		KgDataAuditLog log = new KgDataAuditLog();
		log.setId(UUIDUtil.get32UUID());
		log.setDataName(editData.getEditDataName() == null ? editData.getNewDataName() : editData.getEditDataName());
		log.setAuditInfo(info);
		log.setAuditState(editData.getAuditState());
		log.setAuditUser(user.getUserId());
		log.setEditOriDataId(editData.getEditOriDataId());
		log.setAuditRstId(editData.getAuditRstId());
		kgDataAuditLogMapperAdapter.insertSelective(log);

		
		for (String eid : ids) {

			//单条操作草稿
			KgEditData cur = new KgEditData();
			cur.setId(eid);
			cur.setAuditState(editData.getAuditState());
			cur.setAuditInfo(info);
			cur.setAuditDate(new Date());
			cur.setAuditUser(user.getUserId());
			cur.setAuditRstId(editData.getAuditRstId());
			kgEditDataMapper.updateByPrimaryKeySelective(cur);
			
			
			//审核详情
			KgDataAuditDetail detail=new KgDataAuditDetail();
			detail.setId(UUIDUtil.get32UUID());
			detail.setDataAuditId(log.getId());
			detail.setDataEditId(eid);
			kgDataAuditDetailMapperAdapter.insertSelective(detail);
		}

		

		return rst;
	}

}