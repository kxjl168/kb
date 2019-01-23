/*
 * @(#)BlackIpListController.java
 * @author: zhangJ
 * @Date: 2019-01-23 21:47:23
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
import com.kxjl.tool.utils.PageUtil;
import com.kxjl.web.autodata.dao.BlackIpListMapper;
import com.kxjl.web.autodata.pojo.BlackIpList;
import com.kxjl.web.autodata.service.BlackIpListService;

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
 * 黑名单管理 BlackIpListController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-01-23 21:47:23
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/blackiplist")
public class BlackIpListController {
	@Autowired
	private BlackIpListService blackiplistService;
	@Autowired
	MenuInfoService menuService;


	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/blackiplist/index";
	}
	


	

	@RequestMapping("/blackiplistList")
	//@ManagerActionLog(operateDescribe="查询黑名单",operateFuncType=FunLogType.Query,operateModelClassName=BlackIpListMapper.class)
	@ResponseBody
	public String blackiplistList( BlackIpList item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<BlackIpList> blackiplists = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		blackiplists = blackiplistService.selectBlackIpListList(item);

		try {
			rst = PageUtil.packageTableData(page, blackiplists);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除黑名单",operateFuncType=FunLogType.Del,operateModelClassName=BlackIpListMapper.class)
	@ResponseBody
	public Message delete( BlackIpList item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = blackiplistService.deleteBlackIpList(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		BlackIpList blackiplists = blackiplistService.selectBlackIpListById(id);
		return JSONObject.fromObject(blackiplists).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param blackiplist
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改黑名单",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=BlackIpListMapper.class)
	@ResponseBody
	public String saveOrUpdate(BlackIpList blackiplist) {

		JSONObject jsonObject = null;
		try {
			if (null == blackiplist.getId() || "".equals(blackiplist.getId())) {
				
				jsonObject = blackiplistService.saveBlackIpList(blackiplist);

			} else {
				jsonObject = blackiplistService.updateBlackIpList(blackiplist);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectblackiplist")
    @ResponseBody
    public List<BlackIpList> selectblackiplist( BlackIpList item) {
        return blackiplistService.selectBlackIpListList(item);
    }


}