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

import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.persistence.entity.KgSubGroup;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgRelationService {


    /**
     * <p>New Info</p>
     * @param kgRelation
     * @return
     */
	WZResponseEntity<?> add(LoginUser user,KgRelation kgRelation) ;
    
    /**
     * <p>Modify Info</p>
     * @param kgRelation
     * @return
     */
	WZResponseEntity<?> modify(LoginUser user,KgRelation kgRelation);
    
    /**
     * <p>Delete</p>
     * @param id
     * @return
     */
     Boolean delete(LoginUser user,String id, String versionId);


    /**
     * <p>query info by id</p>
     * @param id
     * @return
     */
     KgRelation getOne(String id, String versionId);

    
    /**
     * <p>query all info</p>
     * @return
     */
    List<KgRelation> getAll();
    
    /**
   	 * 
   	 * @param example
   	 * @return
   	 * @author:kxjl
   	 * @date 2020年6月9日
   	 */
   	 Page<KgRelation> selectList(LoginUser user,KgRelation query, Pagination pageCondition); 
   	 
   	 
     /**
      * 根据实体id查询  指定用户编辑中的对应Edit实体数据,或者实体
      * @param entityid
      * @return
      * @author:kxjl
      * @date 2020年8月19日
      */
 	public KgRelation getOneByIdInAudit(String userid,String entityid);
   	 
     /**
      * 查询连线上的属性
      * @param kgRelation
      * @return
      * @author:kxjl
      * @date 2020年6月19日
      */
     List<KgProperty> getProperty(KgRelation kgRelation);
     
 	public WZResponseEntity<?> saveOrmodify(LoginUser user,KgRelation kgClass) ;
    
 	public KgRelation selectByName(KgRelation kgRelation);
 	
	
	public WZResponseEntity<?> audit(LoginUser user, KgRelation kgItem, KgEditData editData);
}
