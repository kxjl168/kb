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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.base.aopAspect.CurrentUser;
import com.kxjl.base.aopAspect.NoNeedAuthorization;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.util.PageUtil;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.service.KgClassService;
import com.kxjl.admin.service.KgObjectToObjectService;

/**
 * 图形关系
 * 
 * @author kxjl
 * @date 2020年6月16日
 */
@RestController
@RequestMapping("/kg/kg-graphic")
public class KgGraphicController {

	Logger logger = LoggerFactory.getLogger(KgGraphicController.class);

	/**
	 * <p>
	 * BussinessService
	 * </p>
	 */
	@Autowired
	KgClassService kgClassService;

	@Autowired
	KgObjectToObjectService kgObjectToObjectService;

	
	
	/**
	 * 查询领域关系数量
	 * @param subKgId
	 * @return
	 * @author:kxjl
	 * @date 2020年7月24日
	 */
	@PostMapping("/getSubKgRelation")
	public WZResponseEntity<?> getSubKgRelation(String id) {

		WZResponseEntity rst=new WZResponseEntity<>();
		
		List<KgObjectToObject> lst= kgObjectToObjectService.getSubKgRelation( id);
		rst.setBody(lst.size());
		rst.setIsSuccess(true);

		return rst;
	}
	
	/**
	 * 测试Neo4j数据库连接
	 * @param subKgId
	 * @return
	 * @author:kxjl
	 * @date 2020年7月24日
	 */
	@PostMapping("/testDbConncet")
	public WZResponseEntity<?> testDbConncet(String dburl,String username,String pass) {

		WZResponseEntity rst=new WZResponseEntity<>();
		
		rst= kgObjectToObjectService.TestDb(dburl, username, pass);
	
		return rst;
	}
	
	
	/**
	 * 本体/实体关系存储
	 * 
	 * @param kgClass
	 * @return
	 * @author:kxjl
	 * @date 2020年6月16日
	 */
	@PostMapping("/dataToNeo4j")
	@ResponseBody
	public WZResponseEntity<?> dataToNeo4j(@CurrentUser LoginUser user,String wid,String dburl,String username,String pass,String id) {

		//if(user==null)
	//	{
			user=new LoginUser();
			user.setUserId(wid==null?"1":wid);
		//}
		
		WZResponseEntity rst= kgObjectToObjectService.DataToNeo4j(user,dburl,username,pass, id);

		return rst;
	}
	
	/**
	 * 本体/实体关系存储
	 * 
	 * @param kgClass
	 * @return
	 * @author:kxjl
	 * @date 2020年6月16日
	 */
	@PostMapping("/saveontology")
	@ResponseBody
	public WZResponseEntity<?> saveontology(@CurrentUser LoginUser user,String data) {

		boolean srst = kgObjectToObjectService.saveontology(user,data,null);

		WZResponseEntity<List<KgClass>> rst = new WZResponseEntity<>(srst);

		return rst;
	}
	
	@RequestMapping("/audit")
	@ResponseBody
	public WZResponseEntity<?> audit(@CurrentUser LoginUser user,String data) {
		
		 return kgObjectToObjectService.audit(user, data);
		
		
	}


	/**
	 * 本体/实体关系查询
	 * 
	 * @param kgClass
	 * @return
	 * @author:kxjl
	 * @date 2020年6月16日
	 */
	@NoNeedAuthorization
	@PostMapping("/getontology")
	@ResponseBody
	public WZResponseEntity<String> getontology(@CurrentUser LoginUser user,KgClass data, Integer level,String showEdit) {

		
		boolean showedit=false;
		if(showEdit!=null&&showEdit.equals("true"))
			showedit=true;
		
		String srst = kgObjectToObjectService.getGraDataByLevel(user,data, level,showedit);

		WZResponseEntity<String> rst = new WZResponseEntity<String>();
		rst.setBody(srst);
		rst.setIsSuccess(true);

		return rst;
	}

	
}