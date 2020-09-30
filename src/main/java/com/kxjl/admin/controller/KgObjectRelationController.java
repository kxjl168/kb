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
package com.kxjl.admin.controller;

import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.persistence.entity.KgObjectRelation;
import com.kxjl.admin.service.KgObjectRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
@RestController
@RequestMapping("/wz-test/kg-object-relation")
public class KgObjectRelationController {  
    
    Logger logger=LoggerFactory.getLogger(KgObjectRelationController.class);

    /**
     * <p>BussinessService</p>
     */
    @Autowired
    KgObjectRelationService kgObjectRelationService;
    
    /**
     * <p>New Info</p>
     * @param kgObjectRelation
     * @return
     */
    @PostMapping
    public WZResponseEntity<?> add(@RequestBody KgObjectRelation kgObjectRelation) {
    	logger.info("wz-test add kgObjectRelation:{}", kgObjectRelation);
    	return new WZResponseEntity<>(kgObjectRelationService.add(kgObjectRelation));
    }
    
    /**
     * <p>Modify Info</p>
     * @param kgObjectRelation
     * @return
     */
    @PutMapping
    public WZResponseEntity<?> modify(@RequestBody KgObjectRelation kgObjectRelation) {
    	logger.info("wz-test modify kgObjectRelation:{}", kgObjectRelation);
    	return new WZResponseEntity<>(kgObjectRelationService.modify(kgObjectRelation));
    }

    /**
     * <p>Delete</p>
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    public WZResponseEntity<?> delete(@PathVariable String id) {
    	logger.info("wz-test delete kgObjectRelation:{}", id);
        boolean result  = true;
        // to modify
        // result = kgObjectRelationService.delete(id);
    	return new WZResponseEntity<>(result);
    }
    
    
    /**
     * <p>query info by id</p>
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public WZResponseEntity<KgObjectRelation> getOne(@PathVariable String id) {
    	logger.info("wz-test get kgObjectRelation:{}", id);
       KgObjectRelation result = null;
        // to modify
       // KgObjectRelation result = kgObjectRelationService.getOne(id);
        if (result == null) {
           return new WZResponseEntity<>();
        }
		return  new WZResponseEntity<>(result);
    }
    
    /**
     * <p>query all info</p>
     * @return
     */
    @GetMapping
    public WZResponseEntity<List<KgObjectRelation>> getAll() {
    	logger.info("wz-test get all kgObjectRelation");
    	List<KgObjectRelation> result = kgObjectRelationService.getAll();
    	return new WZResponseEntity<>(result);
    }
}