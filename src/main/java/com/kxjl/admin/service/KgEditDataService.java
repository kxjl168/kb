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
package com.kxjl.admin.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.persistence.entity.KgDataAuditLog;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgSubGroup;

/**
 * 个人修改的临时数据，待审核数据
 * @author kxjl
 * @date 2020年8月3日
 */
public interface KgEditDataService {


    /**
     * <p>New Info</p>
     * @param kgEditData
     * @return
     */
     Boolean add(KgEditData kgEditData) ;
    
    /**
     * <p>Modify Info</p>
     * @param kgEditData
     * @return
     */
     Boolean modify(KgEditData kgEditData);
    
    /**
     * <p>Delete</p>
     * @param id
     * @return
     */
     Boolean delete(String id);
     
     /**
      * 提交审核
      * @param id
      * @return
      * @author:kxjl
      * @date 2020年8月4日
      */
      Boolean toaudit(String id); 
      
      /**
       * 撤回审核，回到我的改动
       * @param id
       * @return
       * @author:kxjl
       * @date 2020年8月4日
       */
       Boolean tolocal(String id); 

      /**
       * 删除
       * @param id
       * @return
       * @author:kxjl
       * @date 2020年8月4日
       */
       Boolean deleteByEditDataId(String id);    
       
       /**
        * 根据真实id，用户id删除所有未提价的修改草稿
        * @param item
        * @author:kxjl
        * @date 2020年8月4日
        */
       Boolean deleteByOriDataIdAndUserId(KgEditData item);
     
    /**
     * <p>query info by id</p>
     * @param id
     * @return
     */
     KgEditData getOne(String id);

    
     /**
 	 * 
 	 * @param example
 	 * @return
 	 * @author:kxjl
 	 * @date 2020年6月9日
 	 */
 	 Page<KgEditData> selectList(KgEditData query, Pagination pageCondition); 
 	 

 	/**
 	 * 查询待审核数据，按实体分组，一个实体显示一条记录。 合并编辑用户，编辑数据ori_edit_id
 	 * 
 	 * @param example
 	 * @return
 	 * @author:kxjl
 	 * @date 2020年8月5日
 	 */
 	public Page<KgEditData> selectToAuditList(KgEditData query, Pagination pageCondition);
 	
 	/**
 	 * 已审核记录
 	 * @param query
 	 * @param pageCondition
 	 * @return
 	 * @author:kxjl
 	 * @date 2020年8月5日
 	 */
 	public Page<KgDataAuditLog> listAuditDone(KgDataAuditLog query, Pagination pageCondition); 
 	
 	
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
	public WZResponseEntity<?> audit(LoginUser user, KgEditData editData);
}
