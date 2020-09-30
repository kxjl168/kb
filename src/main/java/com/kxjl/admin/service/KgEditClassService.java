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

import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.admin.persistence.entity.KgEditClass;

/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年08月03日 13:27:07
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgEditClassService {


    /**
     * <p>New Info</p>
     * @param kgEditClass
     * @return
     */
	 WZResponseEntity<?> add(LoginUser user,KgEditClass kgEditClass) ;
    
    /**
     * <p>Modify Info</p>
     * @param kgEditClass
     * @return
     */
	 WZResponseEntity<?> modify(LoginUser user,KgEditClass kgEditClass);
    
    /**
     * <p>Delete</p>
     * @param id
     * @return
     */
	 Boolean delete(LoginUser user,String id);

    
    /**
     * <p>query info by id</p>
     * @param id
     * @return
     */
     KgEditClass getOne(String id);

    
     public void addtionData(LoginUser user,KgEditClass kgClass);
}
