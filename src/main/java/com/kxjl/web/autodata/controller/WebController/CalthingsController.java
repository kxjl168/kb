/*
 * @(#)CalthingsController.java
 * @author: zhangJ
 * @Date: 2019-03-25 22:29:30
 * Copyright (C),2017-2018, kxjl 
 *  All Rights Reserved.
 * 
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
import com.kxjl.web.autodata.dao.CalthingsMapper;
import com.kxjl.web.autodata.pojo.Calthings;
import com.kxjl.web.autodata.service.CalthingsService;

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
 * 日历管理管理 CalthingsController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-03-25 22:29:30
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/calthings")
public class CalthingsController {
	@Autowired
	private CalthingsService calthingsService;
	@Autowired
	MenuInfoService menuService;


	/**
	 * 日历表格首页
	 * @param request
	 * @param model
	 * @param maps
	 * @return
	 * @author zj
	 * @date 2019年3月25日
	 */
	@RequestMapping("/calindex")
	public String calindex(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/cal/index";
	}
	
	
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/calthings/index";
	}
	


	

	@RequestMapping("/calthingsList")
	//@ManagerActionLog(operateDescribe="查询日历管理",operateFuncType=FunLogType.Query,operateModelClassName=CalthingsMapper.class)
	@ResponseBody
	public String calthingsList( Calthings item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<Calthings> calthingss = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		calthingss = calthingsService.selectCalthingsList(item);

		try {
			rst = PageUtil.packageTableData(page, calthingss);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除日历管理",operateFuncType=FunLogType.Del,operateModelClassName=CalthingsMapper.class)
	@ResponseBody
	public Message delete( Calthings item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = calthingsService.deleteCalthings(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		Calthings calthingss = calthingsService.selectCalthingsById(id);
		return JSONObject.fromObject(calthingss).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param calthings
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改日历管理",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=CalthingsMapper.class)
	@ResponseBody
	public String saveOrUpdate(Calthings calthings) {

		JSONObject jsonObject = null;
		try {
			if (null == calthings.getId() || "".equals(calthings.getId())) {
				
				jsonObject = calthingsService.saveCalthings(calthings);

			} else {
				jsonObject = calthingsService.updateCalthings(calthings);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectcalthings")
    @ResponseBody
    public List<Calthings> selectcalthings( Calthings item) {
        return calthingsService.selectCalthingsList(item);
    }


}