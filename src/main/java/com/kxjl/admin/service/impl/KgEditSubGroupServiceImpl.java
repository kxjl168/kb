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
import com.kxjl.admin.persistence.entity.KgEditSubGroup;
import com.kxjl.admin.persistence.entity.KgSubGroup;
import com.kxjl.admin.service.KgEditDataService;
import com.kxjl.admin.service.KgEditSubGroupService;
import com.kxjl.admin.service.KgSubGroupService;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.persistence.adapter.dao.KgEditSubGroupMapperAdapter;

/**
 * 具体开发人员请在此补充上类描述说明
 * 
 * @date 2020年08月03日 13:27:07
 * @author 具体开发人员请在此补充上本人名称拼音
 */
@Service
public class KgEditSubGroupServiceImpl implements KgEditSubGroupService {

	@Autowired
	KgSubGroupService kgSubGroupService;

	@Autowired
	KgEditSubGroupMapperAdapter kgEditSubGroupMapper;

	@Autowired
	KgEditDataService kgEditDataService;

	/**
	 * <p>
	 * 保存新的草稿及 编辑数据
	 * </p>
	 * 
	 * @param kgEditSubGroup
	 * @return
	 */
	@Transactional
	public Boolean add(LoginUser user, KgEditSubGroup kgEditSubGroup) {

		if (kgEditSubGroup.getId() == null || kgEditSubGroup.getId().equals(""))
			kgEditSubGroup.setId(UUIDUtil.get32UUID());

		kgEditDataService.add(new KgEditData(kgEditSubGroup, user.getUserId()));

		return kgEditSubGroupMapper.insertSelective(kgEditSubGroup) > 0;
	}

	/**
	 * <p>
	 * 修改实际数据，或者修改本地草稿数据
	 * </p>
	 * 
	 * @param kgEditSubGroup
	 * @return
	 */
	@Transactional
	public Boolean modify(LoginUser user, KgEditSubGroup kgEditSubGroup) {

		if (kgEditSubGroup.getId() == null || kgEditSubGroup.getId().equals("")) {
			kgEditSubGroup.setId(UUIDUtil.get32UUID());

			KgEditData edata = new KgEditData(kgEditSubGroup, user.getUserId());
			edata.setEditAction("2");
			kgEditDataService.add(edata);

			kgEditSubGroup.setCreatedUser(user.getUserId());

			return kgEditSubGroupMapper.insertSelective(kgEditSubGroup) > 0;
		}

		return kgEditSubGroupMapper.updateByPrimaryKeySelective(kgEditSubGroup) > 0;
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

		KgSubGroup group = kgSubGroupService.getOne(id, Constants.DEFULT_VERSION);
		if (group != null) {

			KgEditSubGroup kgEditSubGroup = new KgEditSubGroup(group);
			kgEditSubGroup.setId(UUIDUtil.get32UUID());

			// 删除前面所有的本地修改记录
			KgEditData q = new KgEditData();
			q.setEditOriDataId(id);
			q.setEditUser(user.getUserId());
			kgEditDataService.deleteByOriDataIdAndUserId(q);

			// 增加删除记录
			KgEditData edata = new KgEditData(kgEditSubGroup, user.getUserId());
			edata.setEditAction("3");
			kgEditDataService.add(edata);

			// 添加原始数据
			return kgEditSubGroupMapper.insertSelective(kgEditSubGroup) > 0;
		} else {
			KgEditSubGroup esubgroup = kgEditSubGroupMapper.selectByPrimaryKey(id);
			if (esubgroup != null) {
				// 删除草稿

				// 撤销新增
				kgEditDataService.deleteByEditDataId(id);

				return kgEditSubGroupMapper.deleteByPrimaryKey(id) > 0;
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
	public KgEditSubGroup getOne(String id) {
		return kgEditSubGroupMapper.selectByPrimaryKey(id);
	}

}