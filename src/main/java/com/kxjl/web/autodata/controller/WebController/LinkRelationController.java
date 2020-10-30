/*
 * @(#)LinkRelationController.java
 * @author: zhangJ
 * @Date: 2020-10-23 09:16:55
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
import com.kxjl.web.autodata.dao.LinkRelationMapper;
import com.kxjl.web.autodata.pojo.LinkRelation;
import com.kxjl.web.autodata.service.LinkRelationService;

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
 * 链接发现关系管理 LinkRelationController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2020-10-23 09:16:55
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/linkrelation")
public class LinkRelationController {
	@Autowired
	private LinkRelationService linkrelationService;
	@Autowired
	MenuInfoService menuService;


	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/linkrelation/index";
	}
	


	

	@RequestMapping("/linkrelationList")
	//@ManagerActionLog(operateDescribe="查询链接发现关系",operateFuncType=FunLogType.Query,operateModelClassName=LinkRelationMapper.class)
	@ResponseBody
	public String linkrelationList( LinkRelation item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<LinkRelation> linkrelations = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		linkrelations = linkrelationService.selectLinkRelationList(item);

		try {
			rst = PageUtil.packageTableData(page, linkrelations);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除链接发现关系",operateFuncType=FunLogType.Del,operateModelClassName=LinkRelationMapper.class)
	@ResponseBody
	public Message delete( LinkRelation item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = linkrelationService.deleteLinkRelation(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		LinkRelation linkrelations = linkrelationService.selectLinkRelationById(id);
		return JSONObject.fromObject(linkrelations).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param linkrelation
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改链接发现关系",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=LinkRelationMapper.class)
	@ResponseBody
	public String saveOrUpdate(LinkRelation linkrelation) {

		JSONObject jsonObject = null;
		try {
			if (null == linkrelation.getId() || "".equals(linkrelation.getId())) {
				
				jsonObject = linkrelationService.saveLinkRelation(linkrelation);

			} else {
				jsonObject = linkrelationService.updateLinkRelation(linkrelation);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectlinkrelation")
    @ResponseBody
    public List<LinkRelation> selectlinkrelation( LinkRelation item) {
        return linkrelationService.selectLinkRelationList(item);
    }


}