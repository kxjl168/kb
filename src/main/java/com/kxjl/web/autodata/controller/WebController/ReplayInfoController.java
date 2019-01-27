/*
 * @(#)ReplayInfoController.java
 * @author: zhangJ
 * @Date: 2019-01-27 20:32:55
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
import com.kxjl.web.autodata.dao.ReplayInfoMapper;
import com.kxjl.web.autodata.pojo.ReplayInfo;
import com.kxjl.web.autodata.service.ReplayInfoService;

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
 * 评论管理 ReplayInfoController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-01-27 20:32:55
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/replayinfo")
public class ReplayInfoController {
	@Autowired
	private ReplayInfoService replayinfoService;
	@Autowired
	MenuInfoService menuService;


	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/replayinfo/index";
	}
	


	

	@RequestMapping("/replayinfoList")
	//@ManagerActionLog(operateDescribe="查询评论",operateFuncType=FunLogType.Query,operateModelClassName=ReplayInfoMapper.class)
	@ResponseBody
	public String replayinfoList( ReplayInfo item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<ReplayInfo> replayinfos = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		replayinfos = replayinfoService.selectReplayInfoList(item);

		try {
			rst = PageUtil.packageTableData(page, replayinfos);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除评论",operateFuncType=FunLogType.Del,operateModelClassName=ReplayInfoMapper.class)
	@ResponseBody
	public Message delete( ReplayInfo item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = replayinfoService.deleteReplayInfo(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String recordid,HttpServletRequest request) {
	
		ReplayInfo replayinfos = replayinfoService.selectReplayInfoById(recordid);
		return JSONObject.fromObject(replayinfos).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param replayinfo
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改评论",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=ReplayInfoMapper.class)
	@ResponseBody
	public String saveOrUpdate(ReplayInfo replayinfo) {

		JSONObject jsonObject = null;
		try {
			if (replayinfo.getRecordid()==0) {
				
				jsonObject = replayinfoService.saveReplayInfo(replayinfo);

			} else {
				jsonObject = replayinfoService.updateReplayInfo(replayinfo);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectreplayinfo")
    @ResponseBody
    public List<ReplayInfo> selectreplayinfo( ReplayInfo item) {
        return replayinfoService.selectReplayInfoList(item);
    }


}