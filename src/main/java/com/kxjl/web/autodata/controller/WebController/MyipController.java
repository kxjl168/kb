/*
 * @(#)MyipController.java
 * @author: zhangJ
 * @Date: 2019-01-15 18:31:42
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
import com.kxjl.web.autodata.dao.MyipMapper;
import com.kxjl.web.autodata.pojo.Myip;
import com.kxjl.web.autodata.service.MyipService;

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
 * 我的IP地址管理 MyipController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-01-15 18:31:42
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/myip")
public class MyipController {
	@Autowired
	private MyipService myipService;
	@Autowired
	MenuInfoService menuService;


	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/myip/index";
	}
	


	

	@RequestMapping("/myipList")
	//@ManagerActionLog(operateDescribe="查询我的IP地址",operateFuncType=FunLogType.Query,operateModelClassName=MyipMapper.class)
	@ResponseBody
	public String myipList( Myip item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<Myip> myips = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		myips = myipService.selectMyipList(item);

		try {
			rst = PageUtil.packageTableData(page, myips);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除我的IP地址",operateFuncType=FunLogType.Del,operateModelClassName=MyipMapper.class)
	@ResponseBody
	public Message delete( Myip item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = myipService.deleteMyip(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		Myip myips = myipService.selectMyipById(id);
		return JSONObject.fromObject(myips).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param myip
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改我的IP地址",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=MyipMapper.class)
	@ResponseBody
	public String saveOrUpdate(Myip myip) {

		JSONObject jsonObject = null;
		try {
			if (null == myip.getId() || "".equals(myip.getId())) {
				
				jsonObject = myipService.saveMyip(myip);

			} else {
				jsonObject = myipService.updateMyip(myip);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectmyip")
    @ResponseBody
    public List<Myip> selectmyip( Myip item) {
        return myipService.selectMyipList(item);
    }


}