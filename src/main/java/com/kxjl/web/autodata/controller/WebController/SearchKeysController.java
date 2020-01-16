/*
 * @(#)SearchKeysController.java
 * @author: zhangJ
 * @Date: 2020-01-15 15:54:03
 * Copyright (C),2017-2018, kxjl 
 *  All Rights Reserved.
 * 
 */
package com.kxjl.web.autodata.controller.WebController;


import com.github.pagehelper.Page;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.sendmail.MailUtil;
import com.kxjl.tool.utils.DateUtil;
import com.kxjl.tool.utils.JEscape;
//import com.ztgm.base.aopAspect.FunLogType;
//import com.ztgm.base.aopAspect.ManagerActionLog;
import com.kxjl.tool.utils.PageCondition;
import com.kxjl.web.generator.pojo.Message;
import com.kxjl.web.system.action.base.OutApiAuthorization;
import com.kxjl.web.system.action.base.OutApiAuthorization.UrlType;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.service.MenuInfoService;
import com.kxjl.tool.utils.PageUtil;
import com.kxjl.web.autodata.dao.SearchKeysMapper;
import com.kxjl.web.autodata.pojo.SearchKeys;
import com.kxjl.web.autodata.service.SearchKeysService;

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
 * google搜索码管理 SearchKeysController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2020-01-15 15:54:03
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/searchkeys")
public class SearchKeysController {
	@Autowired
	private SearchKeysService searchkeysService;
	@Autowired
	MenuInfoService menuService;


	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/searchkeys/index";
	}
	


	

	@OutApiAuthorization(uType=UrlType.NeedAdmin)
	@RequestMapping("/searchkeysList")
	//@ManagerActionLog(operateDescribe="查询google搜索码",operateFuncType=FunLogType.Query,operateModelClassName=SearchKeysMapper.class)
	@ResponseBody
	public String searchkeysList( SearchKeys item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<SearchKeys> searchkeyss = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		searchkeyss = searchkeysService.selectSearchKeysList(item);

		try {
			rst = PageUtil.packageTableData(page, searchkeyss);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@OutApiAuthorization(uType=UrlType.NeedAdmin)
	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除google搜索码",operateFuncType=FunLogType.Del,operateModelClassName=SearchKeysMapper.class)
	@ResponseBody
	public Message delete( SearchKeys item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = searchkeysService.deleteSearchKeys(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@OutApiAuthorization(uType=UrlType.NeedAdmin)
	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		SearchKeys searchkeyss = searchkeysService.selectSearchKeysById(id);
		return JSONObject.fromObject(searchkeyss).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param searchkeys
	 * @return
	 */
	@OutApiAuthorization(uType=UrlType.NeedAdmin)
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改google搜索码",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=SearchKeysMapper.class)
	@ResponseBody
	public String saveOrUpdate(SearchKeys searchkeys) {

		JSONObject jsonObject = null;
		try {
			if (null == searchkeys.getId() || "".equals(searchkeys.getId())) {
				
				jsonObject = searchkeysService.saveSearchKeys(searchkeys);

			} else {
				jsonObject = searchkeysService.updateSearchKeys(searchkeys);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}
	
	/**
	 * 发送确认通知邮件
	 * @param searchkeys
	 * @return
	 * @author zj
	 * @date 2020年1月15日
	 */
	@OutApiAuthorization(uType=UrlType.NeedAdmin)
	@RequestMapping("/done")
	//@ManagerActionLog(operateDescribe="保存修改google搜索码",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=SearchKeysMapper.class)
	@ResponseBody
	public String done(SearchKeys searchkeys) {

		JSONObject jsonObject = null;
		try {
			searchkeys=searchkeysService.selectSearchKeysById(searchkeys.getId());
			
			emailNotify(searchkeys);
			
			
			jsonObject = new JSONObject();
			jsonObject.put("bol", true);
			jsonObject.put("msg", "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}
	private void emailNotify(SearchKeys skey) {
		// 访客留言
	
		final String title = "KxのBook [您的Google试用Key已通过审批]";
		
		String ucontent = JEscape.unescape(skey.getContext2());
		// final String message = "From " + uid + ":\r\n<br>" + ucontent + "";
		final String recvmail = JEscape.unescape(skey.getEmail());

		String time=skey.getUseTime();
		if(time.equals("-1"))
			time="无限制";
		
		 String nickname=JEscape.unescape(skey.getNickname());
		String name=nickname;
		if(name==null||name.equals(""))
			name=recvmail;
		
	
		final String action=name+" 您好:<br>感谢您的申请。您在KxのBook 上的Google search 授权码为:"+skey.getGkey()+",使用次数限制为["+time+"],请尽情使用吧!<br>";

		new Thread(new Runnable() {

			@Override
			public void run() {
				
				//MailUtil.sendMail(recvmail, title, message);
				
				boolean isOk=MailUtil.sendMailCode(recvmail, title,action);
				if(isOk)
				{
					skey.setIs_send_mail("1");//已发送
					skey.setUpdateDate(DateUtil.getNowStr(""));
					searchkeysService.updateSearchKeys(skey);
				}
				
			}
		}).start();
	}


	@OutApiAuthorization(uType=UrlType.NeedAdmin)
    @RequestMapping("/selectsearchkeys")
    @ResponseBody
    public List<SearchKeys> selectsearchkeys( SearchKeys item) {
        return searchkeysService.selectSearchKeysList(item);
    }


}