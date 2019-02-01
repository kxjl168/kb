/*
 * @(#)RssPageListController.java
 * @author: zhangJ
 * @Date: 2019-01-28 22:51:27
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
import com.kxjl.web.autodata.dao.RssPageListMapper;
import com.kxjl.web.autodata.pojo.RssPageList;
import com.kxjl.web.autodata.service.RssPageListService;

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
 * Rss文章管理 RssPageListController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-01-28 22:51:27
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/rsspagelist")
public class RssPageListController {
	@Autowired
	private RssPageListService rsspagelistService;
	@Autowired
	MenuInfoService menuService;


	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/rsspagelist/index";
	}
	


	

	@RequestMapping("/rsspagelistList")
	//@ManagerActionLog(operateDescribe="查询Rss文章",operateFuncType=FunLogType.Query,operateModelClassName=RssPageListMapper.class)
	@ResponseBody
	public String rsspagelistList( RssPageList item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<RssPageList> rsspagelists = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		rsspagelists = rsspagelistService.selectRssPageListList(item);

		try {
			rst = PageUtil.packageTableData(page, rsspagelists);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除Rss文章",operateFuncType=FunLogType.Del,operateModelClassName=RssPageListMapper.class)
	@ResponseBody
	public Message delete( RssPageList item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = rsspagelistService.deleteRssPageList(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		RssPageList rsspagelists = rsspagelistService.selectRssPageListById(id);
		rsspagelists.setIsRead("1");//已读
		rsspagelistService.updateRssPageList(rsspagelists);
		return JSONObject.fromObject(rsspagelists).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param rsspagelist
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改Rss文章",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=RssPageListMapper.class)
	@ResponseBody
	public String saveOrUpdate(RssPageList rsspagelist) {

		JSONObject jsonObject = null;
		try {
			if (null == rsspagelist.getId() || "".equals(rsspagelist.getId())) {
				
				jsonObject = rsspagelistService.saveRssPageList(rsspagelist);

			} else {
				jsonObject = rsspagelistService.updateRssPageList(rsspagelist);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectrsspagelist")
    @ResponseBody
    public List<RssPageList> selectrsspagelist( RssPageList item) {
        return rsspagelistService.selectRssPageListList(item);
    }


}