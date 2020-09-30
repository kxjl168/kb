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
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.persistence.entity.KgProperty;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgEntityService {


    /**
     * <p>New Info</p>
     * @param kgEntity
     * @return
     */
	WZResponseEntity<?> add(LoginUser user,KgEntity kgEntity) ;
    
    /**
     * <p>Modify Info</p>
     * @param kgEntity
     * @return
     */
	WZResponseEntity<?> modify(LoginUser user,KgEntity kgEntity);
    
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
     KgEntity getOne(String id, String versionId);

     
     
	   /**
   * 根据实体id查询  指定用户编辑中的对应Edit实体数据<br>
   * 或者根据编辑edit实体id直接查询编辑实体id,<br>
   * 或者根据实体id查询实体<br>
   * 
   * @param entityid  实体id/编辑实体id
   * @return 指定实体id对应的当前编辑数据/指定编辑id对应的编辑数据/指定实体id对应的原始数据
   * @author:kxjl
   * @date 2020年8月19日
   */
	public KgEntity getOneByIdInAudit(String userid,String entityid);
     
     
    
    /**
     * <p>query all info</p>
     * @return
     */
    List<KgEntity> getAll();
    
    /**
   	 * 
   	 * @param example
   	 * @return
   	 * @author:kxjl
   	 * @date 2020年6月9日
   	 */
   	 Page<KgEntity> selectList(LoginUser user,KgEntity query, Pagination pageCondition); 
   	 
   	 /**
      * 查询实体 所属概念上的属性
      * @param kgRelation
      * @return
      * @author:kxjl
      * @date 2020年6月19日
      */
      List<KgProperty> getProperty(KgEntity kgRelation);
    
       WZResponseEntity<?> saveOrmodify(LoginUser user, KgEntity kgProperty);
       
       /**
   	 * 根据名称创建默认entity,默认归属 clsnam 概念
   	 * @param name
   	 * @return
   	 * @author:kxjl
   	 * @date 2020年7月3日
   	 */
   	public KgEntity createByName(String name);
   	

	
	public WZResponseEntity<?> audit(LoginUser user, KgEntity kgItem, KgEditData editData);
}
