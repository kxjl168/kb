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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.persistence.entity.KgUserRelatedDatas;
import com.kxjl.admin.persistence.entity.KgUserRelatedDatas;
import com.kxjl.admin.service.KgUserRelatedDatasService;
import com.kxjl.admin.service.KgUserRelatedDatasService;


/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年07月27日 10:58:45
* @author 具体开发人员请在此补充上本人名称拼音
*/
@RestController
@RequestMapping("/kg/kg-user-related-datas")
public class KgUserRelatedDatasController {  
    
    Logger logger=LoggerFactory.getLogger(KgUserRelatedDatasController.class);

    /**
     * <p>BussinessService</p>
     */
    @Autowired
    KgUserRelatedDatasService kgUserRelatedDatasService;
    
    /**
	 * <p>
	 * New Info
	 * </p>
	 * 
	 * @param kgSubGroup
	 * @return
	 */
	@PostMapping("/add")
	public WZResponseEntity<?> add( KgUserRelatedDatas kgSubGroup) {
		//logger.info("kg add kgSubGroup:{}", kgSubGroup);
		return new WZResponseEntity<>(kgUserRelatedDatasService.add(kgSubGroup));
	}
	
	
	/**
	 * <p>
	 * Modify Info
	 * </p>
	 * 
	 * @param kgSubGroup
	 * @return
	 */
	@PutMapping("/modify")
	public WZResponseEntity<?> modify( KgUserRelatedDatas kgSubGroup) {
		//logger.info("kg modify kgSubGroup:{}", kgSubGroup);
		return new WZResponseEntity<>(kgUserRelatedDatasService.modify(kgSubGroup));
	}

	/**
	 * <p>
	 * Delete
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/del/{id}")
	public WZResponseEntity<?> delete(@PathVariable String id) {
		logger.info("kg delete kgSubGroup:{}", id);
		
		
		
		return new WZResponseEntity<>(kgUserRelatedDatasService.delete(id));
	}

	/**
	 * <p>
	 * query info by id
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public WZResponseEntity<KgUserRelatedDatas> getOne(@PathVariable String id) {
		//logger.info("kg get kgSubGroup:{}", id);
		KgUserRelatedDatas result = kgUserRelatedDatasService.getOne(id);
		if (result == null) {
			return new WZResponseEntity<>();
		}
		return new WZResponseEntity<>(result);
	}

	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param pageCondition
	 * @return
	 * @author:kxjl
	 * @date 2020年6月9日
	 */
	@RequestMapping("/list")
	@ResponseBody
	public WZResponseEntity<List<KgUserRelatedDatas>> getListByPage( KgUserRelatedDatas query, Pagination pageCondition) {
		//System.out.println(pageCondition.getPageSize());
		Page<KgUserRelatedDatas> result = kgUserRelatedDatasService.selectRelationList(query, pageCondition);
		WZResponseEntity<List<KgUserRelatedDatas>> rst = new WZResponseEntity<>(result.getResults());
		rst.setPagination(result.getPageInfo());
		return rst;
	}
}