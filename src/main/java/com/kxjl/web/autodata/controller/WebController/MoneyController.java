/*
 * @(#)MoneyController.java
 * @author: zhangJ
 * @Date: 2019-02-12 16:45:42
 * Copyright (C),2017-2018, kxjl 
 *  All Rights Reserved.
 * 
 */
package com.kxjl.web.autodata.controller.WebController;


import com.github.pagehelper.Page;
import com.kxjl.tool.common.Constant;
//import com.ztgm.base.aopAspect.FunLogType;
//import com.ztgm.base.aopAspect.ManagerActionLog;
import com.kxjl.tool.utils.PageCondition;
import com.kxjl.web.generator.pojo.Message;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.service.MenuInfoService;
import com.kxjl.tool.utils.PageUtil;
import com.kxjl.web.autodata.dao.MoneyMapper;
import com.kxjl.web.autodata.pojo.Money;
import com.kxjl.web.autodata.service.MoneyService;

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
 * 收支管理管理 MoneyController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-02-12 16:45:42
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/money")
public class MoneyController {
	@Autowired
	private MoneyService moneyService;
	@Autowired
	MenuInfoService menuService;


	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/money/index";
	}
	


	

	@RequestMapping("/moneyList")
	//@ManagerActionLog(operateDescribe="查询收支管理",operateFuncType=FunLogType.Query,operateModelClassName=MoneyMapper.class)
	@ResponseBody
	public String moneyList( Money item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<Money> moneys = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		moneys = moneyService.selectMoneyList(item);

		try {
			rst = PageUtil.packageTableData(page, moneys);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除收支管理",operateFuncType=FunLogType.Del,operateModelClassName=MoneyMapper.class)
	@ResponseBody
	public Message delete( Money item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = moneyService.deleteMoney(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		Money moneys = moneyService.selectMoneyById(id);
		return JSONObject.fromObject(moneys).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param money
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改收支管理",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=MoneyMapper.class)
	@ResponseBody
	public String saveOrUpdate(Money money,HttpServletRequest request) {

		JSONObject jsonObject = null;
		try {
			
			SysUserBean user = (SysUserBean) request.getSession().getAttribute(Constant.SESSION_USER);
			
			
			if (null == money.getId() || "".equals(money.getId())) {
				
				money.setCreateUser(user.getUserid());
				jsonObject = moneyService.saveMoney(money);

			} else {
				money.setUpdateUser(user.getUserid());
				jsonObject = moneyService.updateMoney(money);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectmoney")
    @ResponseBody
    public List<Money> selectmoney( Money item) {
        return moneyService.selectMoneyList(item);
    }


}