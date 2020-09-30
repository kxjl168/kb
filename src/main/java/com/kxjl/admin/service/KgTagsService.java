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
import com.kxjl.admin.util.Page;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgTags;

import java.util.List;

/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgTagsService {


    /**
     * <p>New Info</p>
     * @param kgTags
     * @return
     */
     Boolean add(KgTags kgTags) ;
    
    /**
     * <p>Modify Info</p>
     * @param kgTags
     * @return
     */
     Boolean modify(KgTags kgTags);
    
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
     KgTags getOne(String id, String versionId);

    
    /**
     * <p>query all info</p>
     * @return
     */
    List<KgTags> getAll();
    
    
    /**
     * 检查返回的tag中，是否有新增数据，直接新增tag
     * @param data
     * @return
     * @author:kxjl
     * @date 2020年6月23日
     */
    Boolean SaveList(String data);
    
    /**
  	 * 
  	 * @param example
  	 * @return
  	 * @author:kxjl
  	 * @date 2020年6月9日
  	 */
  	 Page<KgTags> selectList(KgTags query, Pagination pageCondition) ;
  	 
  	 /**
  	  * 新增同名tag
  	  * @param kgClass
  	  * @return
  	  * @author:kxjl
  	  * @date 2020年6月24日
  	  */
  	 Boolean addSameNameTag(KgClass kgClass);
}
