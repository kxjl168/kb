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

import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.persistence.entity.KgTags;
import com.kxjl.admin.persistence.entity.KgTags;
import com.kxjl.admin.service.KgTagsService;
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
@RequestMapping("/kg/kg-tags")
public class KgTagsController {  
    
    Logger logger=LoggerFactory.getLogger(KgTagsController.class);

    /**
     * <p>BussinessService</p>
     */
    @Autowired
    KgTagsService kgTagsService;
    
    /**
	 * <p>
	 * New Info
	 * </p>
	 * 
	 * @param kgSubGroup
	 * @return
	 */
	@PostMapping("/add")
	public WZResponseEntity<?> add( KgTags kgSubGroup) {
		logger.info("kg add kgSubGroup:{}", kgSubGroup);
		return new WZResponseEntity<>(kgTagsService.add(kgSubGroup));
	}
	
	
	/**
	 * 增加界面输入的自定义标签
	 * @param data
	 * @return
	 * @author:kxjl
	 * @date 2020年6月23日
	 */
	@PostMapping("/saveList")
	public WZResponseEntity<?> saveList( String data) {
	//	logger.info("kg add kgSubGroup:{}", kgSubGroup);
		return new WZResponseEntity<>(kgTagsService.SaveList(data));
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
	public WZResponseEntity<?> modify( KgTags kgSubGroup) {
		logger.info("kg modify kgSubGroup:{}", kgSubGroup);
		return new WZResponseEntity<>(kgTagsService.modify(kgSubGroup));
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
		
		
		
		return new WZResponseEntity<>(kgTagsService.delete(id,Constants.DEFULT_VERSION));
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
	public WZResponseEntity<KgTags> getOne(@PathVariable String id) {
		//logger.info("kg get kgSubGroup:{}", id);
		KgTags result = kgTagsService.getOne(id,Constants.DEFULT_VERSION);
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
	public WZResponseEntity<List<KgTags>> getListByPage( KgTags query, Pagination pageCondition) {
		//System.out.println(pageCondition.getPageSize());
		Page<KgTags> result = kgTagsService.selectList(query, pageCondition);
		WZResponseEntity<List<KgTags>> rst = new WZResponseEntity<>(result.getResults());
		rst.setPagination(result.getPageInfo());
		return rst;
	}
  
}