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

import com.kxjl.admin.persistence.adapter.dao.KgVersionMapperAdapter;
import com.kxjl.admin.persistence.entity.KgVersion;
import com.kxjl.admin.persistence.entity.KgVersionExample;
import com.kxjl.admin.service.KgVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
@Service
public class KgVersionServiceImpl implements KgVersionService {

	@Autowired
    KgVersionMapperAdapter kgVersionMapper;
	
    /**
     * <p>New Info</p>
     * @param kgVersion
     * @return
     */
    @Transactional
    public Boolean add(KgVersion kgVersion) {
        return kgVersionMapper.insertSelective(kgVersion) > 0;
	}

    /**
     * <p>Modify Info</p>
     * @param kgVersion
     * @return
     */
    @Transactional
    public Boolean modify(KgVersion kgVersion) {
		return kgVersionMapper.updateByPrimaryKeySelective(kgVersion) > 0;
	}

   /**
    * <p>Delete</p>
    * @return
    */
    @Transactional
    public Boolean delete(String id) {
       return kgVersionMapper.deleteByPrimaryKey(id) > 0;
    }


    /**
     * <p>query info by id</p>
     * @return
     */
    @Transactional(readOnly = true)
    public KgVersion getOne(String id) {
       return kgVersionMapper.selectByPrimaryKey(id);
    }


    /**
     * <p>query all info</p>
     * @return
     */
    @Transactional(readOnly = true)
    public List<KgVersion> getAll() {
         KgVersionExample example = new KgVersionExample();
         return kgVersionMapper.selectByExample(example);
	}
}