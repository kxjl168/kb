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


import com.kxjl.admin.persistence.entity.KgDataLog;
import com.kxjl.admin.persistence.entity.KgDirTree;
import com.kxjl.admin.service.KgDataLogService;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.util.PageUtil;
import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.persistence.adapter.dao.KgDataLogMapperAdapter;


/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年08月03日 13:27:07
* @author 具体开发人员请在此补充上本人名称拼音
*/
@Service
public class KgDataLogServiceImpl implements KgDataLogService {

	@Autowired
	KgDataLogMapperAdapter kgDataLogMapper;
	
    /**
     * <p>New Info</p>
     * @param kgDataLog
     * @return
     */
    @Transactional
    public Boolean add(KgDataLog kgDataLog) {
    	
    	if (kgDataLog.getId() == null || kgDataLog.getId().equals(""))
    		kgDataLog.setId(UUIDUtil.get32UUID());

    	
        return kgDataLogMapper.insertSelective(kgDataLog) > 0;
	}

    /**
     * <p>Modify Info</p>
     * @param kgDataLog
     * @return
     */
    @Transactional
    public Boolean modify(KgDataLog kgDataLog) {
		return kgDataLogMapper.updateByPrimaryKeySelective(kgDataLog) > 0;
	}

   /**
    * <p>Delete</p>
    * @return
    */
    @Transactional
    public Boolean delete(String id) {
       return kgDataLogMapper.deleteByPrimaryKey(id) > 0;
    }


    /**
     * <p>query info by id</p>
     * @return
     */
    @Transactional(readOnly = true)
    public KgDataLog getOne(String id) {
       return kgDataLogMapper.selectByPrimaryKey(id);
    }
    
    public Page<KgDataLog> selectList(LoginUser user, KgDataLog query, Pagination pageCondition) {
		com.github.pagehelper.Page pageinfo = PageUtil.getPage(pageCondition);

	
		List<KgDataLog> lst = kgDataLogMapper.selectList(query);
		
		Page<KgDataLog> page = new Page<KgDataLog>();
		page.setResults(lst);
		page.setPageNo(pageinfo.getPageNum());
		page.setPageSize(pageinfo.getPageSize());
		page.setTotalRecord((int) pageinfo.getTotal());

		return page;
	}



}