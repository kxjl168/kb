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
import com.kxjl.admin.persistence.entity.KgObjectToObject;


import java.util.List;

/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgClassService {


    /**
     * <p>New Info</p>
     * @param kgClass
     * @return
     */
	WZResponseEntity<?> add(LoginUser user,KgClass kgClass) ;
    
    /**
     * <p>Modify Info</p>
     * @param kgClass
     * @return
     */
	WZResponseEntity<?> modify(LoginUser user,KgClass kgClass);
    /**
     * 查询名称是否存在
     * @param item
     * @return
     * @author:kxjl
     * @date 2020年6月23日
     */
    KgClass selectByName(KgClass item);
    
    
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
     KgClass getOne(String id, String versionId);

    
	   /**
   * 根据实体id查询  指定用户编辑中的对应Edit实体数据,或者实体
   * @param entityid
   * @return
   * @author:kxjl
   * @date 2020年8月19日
   */
	public KgClass getOneByIdInAudit(String userid,String entityid) ;

    /**
     * <p>query all info</p>
     * @return
     */
    List<KgClass> getAll();
    
    /**
   	 * 
   	 * @param example
   	 * @return
   	 * @author:kxjl
   	 * @date 2020年6月9日
   	 */
   	 Page<KgClass> selectList(LoginUser user,KgClass query, Pagination pageCondition); 
   	 
     
     /**
      * 获取指定cls的下级cls
      * @param node
      * @return
      * @author:kxjl
      * @date 2020年6月18日
      */
     List<KgClass> getTreeData(LoginUser user,KgClass node);
     
     /**
      * 
      * @param kgClass
      * @return
      * @author:kxjl
      * @date 2020年7月2日
      */
 	public WZResponseEntity<?> saveOrmodify(LoginUser user,KgClass kgClass);
 	
 	/**
 	 * 获取cls上的全部属性
 	 * @param id
 	 * @param version
 	 * @return
 	 * @author:kxjl
 	 * @date 2020年7月9日
 	 */
 	public WZResponseEntity getClsAttrs(String id, String version);



 	
 	/**
 	 * 合并概念属性
 	 * @param sourceId
 	 * @param targetId
 	 * @param coverTarget 同名自定义属性，是否覆盖目标属性
 	 * @return
 	 * @author:kxjl
 	 * @date 2020年7月14日
 	 */
 	public WZResponseEntity<?> mergeCls(LoginUser user,String sourceId, String targetId, Boolean coverTarget);
 	
 	public WZResponseEntity<?> audit(LoginUser user, KgClass kgItem, KgEditData editData) ;
}
