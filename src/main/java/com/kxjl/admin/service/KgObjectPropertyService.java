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

import com.kxjl.admin.common.LoginUser;
import com.kxjl.admin.persistence.entity.KgObjectProperty;

import java.util.List;

/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgObjectPropertyService {


    /**
     * <p>New Info</p>
     * @param kgObjectProperty
     * @return
     */
     Boolean add(KgObjectProperty kgObjectProperty) ;
    
    /**
     * <p>Modify Info</p>
     * @param kgObjectProperty
     * @return
     */
     Boolean modify(KgObjectProperty kgObjectProperty);
    
    /**
     * <p>Delete</p>
     * @param id
     * @return
     */
     Boolean delete(String id, String versionId);


    /**
     * <p>query info by id</p>
     * @param id
     * @return
     */
     KgObjectProperty getOne(String id, String versionId);

    
    /**
     * <p>query all info</p>
     * @return
     */
    List<KgObjectProperty> getAll();
    
    
    /**
     * 更新概念上的属性
     * @param id
     * @param propids   属性id
     * @return
     * @author:kxjl
     * @date 2020年6月17日
     */
     Boolean resetClsAttr(LoginUser user,String id, String propids);
     
     
     /**
      * 更新关系上的属性
      * @param id
      * @param propids   属性id
      * @return
      * @author:kxjl
      * @date 2020年6月17日
      */
      Boolean resetRelationAttr(String id, String propids);
      
      /**
       * 删除cls-attr关系
       * @param clsId
       * @param proId
       * @return
       * @author:kxjl
       * @date 2020年7月9日
       */
  	public Boolean deleteByClsAndPro(String clsId, String proId);
}
