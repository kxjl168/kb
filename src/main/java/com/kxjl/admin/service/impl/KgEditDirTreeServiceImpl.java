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

import com.kxjl.admin.persistence.entity.KgDirTree;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgEditDirTree;
import com.kxjl.admin.persistence.entity.KgEditDirTree;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.service.KgDirTreeService;
import com.kxjl.admin.service.KgEditDataService;
import com.kxjl.admin.service.KgEditDirTreeService;
import com.kxjl.admin.service.KgObjectSubGroupService;
import com.kxjl.admin.service.KgObjectToObjectService;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.persistence.adapter.dao.KgEditDirTreeMapperAdapter;

/**
 * 具体开发人员请在此补充上类描述说明
 * 
 * @date 2020年08月03日 13:27:07
 * @author 具体开发人员请在此补充上本人名称拼音
 */
@Service
public class KgEditDirTreeServiceImpl implements KgEditDirTreeService {

	@Autowired
	KgEditDirTreeMapperAdapter kgEditItemMapper;
	@Autowired
	KgDirTreeService kgItemService;

	@Autowired
	KgEditDataService kgEditDataService;

	@Autowired
	KgObjectSubGroupService kgObjectSubGroupService;

	/**
	 * <p>
	 * 保存新的草稿及 编辑数据
	 * </p>
	 * 
	 * @param kgEditItem
	 * @return
	 */
	@Transactional
	public WZResponseEntity<?> add(LoginUser user, KgEditDirTree kgEditItem) {
		WZResponseEntity<?> rst = new WZResponseEntity<>();
		rst.setIsSuccess(false);

		if (kgEditItem.getId() == null || kgEditItem.getId().equals(""))
			kgEditItem.setId(UUIDUtil.get32UUID());

		kgEditDataService.add(new KgEditData(kgEditItem, user.getUserId()));

		addtionData(kgEditItem);

		boolean isok = kgEditItemMapper.insertSelective(kgEditItem) > 0;

		rst.setIsSuccess(isok);
		return rst;

	}

	public void addtionData(KgEditDirTree kgDirTree) {

		if (kgDirTree.getDirId() != null && !kgDirTree.getDirId().equals("")) {
			kgDirTree.setSubIds(kgDirTree.getDirId());
			kgDirTree.setDirId("");
		}
		// 处理领域
		kgObjectSubGroupService.resetObjectSubGroupAttr(kgDirTree.getId(), kgDirTree.getSubIds(), "3");

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
	public WZResponseEntity<?> modify(LoginUser user, KgEditDirTree kgEditItem) {

		WZResponseEntity<?> rst = new WZResponseEntity<>();
		rst.setIsSuccess(false);

		if (kgEditItem.getId() == null || kgEditItem.getId().equals("")) {
			kgEditItem.setId(UUIDUtil.get32UUID());

			KgEditData edata = new KgEditData(kgEditItem, user.getUserId());
			edata.setEditAction("2");
			kgEditDataService.add(edata);

			kgEditItem.setCreatedUser(user.getUserId());

			addtionData(kgEditItem);
			boolean isok = kgEditItemMapper.insertSelective(kgEditItem) > 0;

			rst.setIsSuccess(isok);
			return rst;
		}

		addtionData(kgEditItem);
		boolean isok = kgEditItemMapper.updateByPrimaryKeySelective(kgEditItem) > 0;

		rst.setIsSuccess(isok);
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

		KgDirTree group = kgItemService.getOne(id, Constants.DEFULT_VERSION);
		if (group != null) {

			KgEditDirTree kgEditItem = new KgEditDirTree(group);
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
			KgEditDirTree esubgroup = kgEditItemMapper.selectByPrimaryKey(id);
			if (esubgroup != null) {
				// 删除草稿

				// 撤销新增
				kgEditDataService.deleteByEditDataId(id);

				return kgEditItemMapper.deleteByPrimaryKey(id) > 0;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * query info by id
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public KgEditDirTree getOne(String id) {
		
		KgEditDirTree cur = null;

		KgEditDirTree key = new KgEditDirTree();
		key.setId(id);
	
		List<KgEditDirTree> lst = kgEditItemMapper.selectList(key);
		if (lst != null && lst.size() > 0)
			cur = lst.get(0);

		return cur;
		
//		return kgEditItemMapper.selectByPrimaryKey(id);
	}

}