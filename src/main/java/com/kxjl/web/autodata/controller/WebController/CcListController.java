/*
 * @(#)CcListController.java
 * @author: zhangJ
 * @Date: 2019-01-22 14:55:15
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.kxjl.web.autodata.controller.WebController;


import com.github.pagehelper.Page;
//import com.ztgm.base.aopAspect.FunLogType;
//import com.ztgm.base.aopAspect.ManagerActionLog;
import com.kxjl.tool.utils.PageCondition;
import com.kxjl.web.generator.pojo.Message;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.service.MenuInfoService;
import com.kxjl.web.system.service.SysService;
import com.kxjl.tool.utils.PageUtil;
import com.kxjl.web.autodata.dao.CcListMapper;
import com.kxjl.web.autodata.pojo.CcList;
import com.kxjl.web.autodata.service.CcListService;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文章许可管理 CcListController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-01-22 14:55:15
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/cclist")
public class CcListController {
	@Autowired
	private CcListService cclistService;
	@Autowired
	MenuInfoService menuService;
	@Autowired
	SysService sysService;


	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		
		maps.putAll(sysService.getSysInfo());
		
		maps.put("menus", leftmenus);
		return "/page/cclist/index";
	}
	


	

	@RequestMapping("/cclistList")
	//@ManagerActionLog(operateDescribe="查询文章许可",operateFuncType=FunLogType.Query,operateModelClassName=CcListMapper.class)
	@ResponseBody
	public String cclistList( CcList item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<CcList> cclists = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		cclists = cclistService.selectCcListList(item);

		try {
			rst = PageUtil.packageTableData(page, cclists);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除文章许可",operateFuncType=FunLogType.Del,operateModelClassName=CcListMapper.class)
	@ResponseBody
	public Message delete( CcList item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = cclistService.deleteCcList(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		CcList cclists = cclistService.selectCcListById(id);
		return JSONObject.fromObject(cclists).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param cclist
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改文章许可",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=CcListMapper.class)
	@ResponseBody
	public String saveOrUpdate(CcList cclist) {

		JSONObject jsonObject = null;
		try {
			if (null == cclist.getId() || "".equals(cclist.getId())) {
				
				jsonObject = cclistService.saveCcList(cclist);

			} else {
				jsonObject = cclistService.updateCcList(cclist);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectcclist")
    @ResponseBody
    public List<CcList> selectcclist( CcList item) {
        return cclistService.selectCcListList(item);
    }


}