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

import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.aopAspect.CurrentUser;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.util.PageUtil;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.service.KgClassService;

/**
 * autoGenerated
 * 
 * @date 2020年06月08日 11:50:55
 * @author Generator
 */
@RestController
@RequestMapping("/kg/kg-class")
public class KgClassController {

	Logger logger = LoggerFactory.getLogger(KgClassController.class);

	/**
	 * <p>
	 * BussinessService
	 * </p>
	 */
	@Autowired
	KgClassService kgClassService;

	/**
	 * <p>
	 * New Info
	 * </p>
	 * 
	 * @param kgClass
	 * @return
	 */
	@PostMapping("/add")
	public WZResponseEntity<?> add(@CurrentUser LoginUser user, KgClass kgClass) {
		logger.info("kg add kgClass:{}", kgClass);
		return kgClassService.add(user,kgClass);
	}

	/**
	 * <p>
	 * Modify Info
	 * </p>
	 * 
	 * @param kgClass
	 * @return
	 */
	@PutMapping("/modify")
	public WZResponseEntity<?> modify(@CurrentUser LoginUser user, KgClass kgClass) {
		logger.info("kg modify kgClass:{}", kgClass);
		return kgClassService.modify(user,kgClass);
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
	public WZResponseEntity<?> delete(@CurrentUser LoginUser user,@PathVariable String id) {
		logger.info("kg delete kgClass:{}", id);
		return new WZResponseEntity<>(kgClassService.delete(user,id, Constants.DEFULT_VERSION));
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
	public WZResponseEntity<KgClass> getOne(@PathVariable String id) {
		logger.info("kg get kgClass:{}", id);
		KgClass result = kgClassService.getOne(id, Constants.DEFULT_VERSION);
		if (result == null) {
			return new WZResponseEntity<>();
		}
		return new WZResponseEntity<>(result);
	}

	/**
	 * 查询全部cls属性json数据
	 * 
	 * @param id
	 * @return
	 * @author:kxjl
	 * @date 2020年7月9日
	 */
	@RequestMapping("/getClsProperties/{id}")
	public WZResponseEntity<?> getClsProperties(@PathVariable String id) {
		logger.info("kg get kgClass:{}", id);
		return kgClassService.getClsAttrs(id, Constants.DEFULT_VERSION);

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
	public WZResponseEntity<List<KgClass>> getListByPage(@CurrentUser LoginUser user, KgClass query, Pagination pageCondition) {
		// System.out.println(pageCondition.getPageSize());
		
		
		if(query.getDirId()!=null&&!query.getDirId().equals(""))
		{
			String[] ids=query.getDirId().split(",");
			query.setQuerySubIds(ids);
		}
		
		Page<KgClass> result = kgClassService.selectList(user,query, pageCondition);
		WZResponseEntity<List<KgClass>> rst = new WZResponseEntity<>(result.getResults());
		rst.setPagination(result.getPageInfo());
		return rst;
	}

	/**
	 * 概念树，单层查询
	 * 
	 * @param query
	 * @return
	 * @author:kxjl
	 * @date 2020年6月18日
	 */
	@RequestMapping("/getTreeData")
	@ResponseBody
	public WZResponseEntity<List<KgClass>> getTreeData(@CurrentUser LoginUser user,KgClass query) {

		List<KgClass> nodes = kgClassService.getTreeData(user,query);
		WZResponseEntity<List<KgClass>> rst = new WZResponseEntity<>(nodes);
		return rst;
	}

	/**
	 * 合并概念及概念上的关系，并删除源概念
	 * @param sourceId
	 * @param targetId
	 * @param coverTarget 是否覆盖目标属性
	 * @param pageCondition
	 * @return
	 * @author:kxjl
	 * @date 2020年7月14日
	 */
	@RequestMapping("/mergeCls")
	@ResponseBody
	public WZResponseEntity<?> mergeCls(@CurrentUser LoginUser user,String sourceId, String targetId, String coverTarget,
			Pagination pageCondition) {
		// System.out.println(pageCondition.getPageSize());

		boolean cover = false;
		if (coverTarget != null && coverTarget.equals("true"))
			cover = true;

		WZResponseEntity<?> rst = kgClassService.mergeCls(user,sourceId, targetId, cover);

		return rst;
	}
	
	/**
	 * 审核数据
	 * @param user
	 * @param query
	 * @param editdata
	 * @return
	 * @author:kxjl
	 * @date 2020年8月5日
	 */
	@RequestMapping("/audit")
	@ResponseBody
	public WZResponseEntity<?> audit(@CurrentUser LoginUser user, KgClass query,String editDataStr) {
		
		 KgEditData editdata=JSONObject.parseObject(editDataStr,KgEditData.class);
		
		return kgClassService.audit(user,query, editdata);
		
	}

}