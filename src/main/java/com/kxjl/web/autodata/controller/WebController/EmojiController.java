/*
 * @(#)EmojiController.java
 * @author: zhangJ
 * @Date: 2019-01-23 21:36:21
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
import com.kxjl.web.autodata.dao.EmojiMapper;
import com.kxjl.web.autodata.pojo.Emoji;
import com.kxjl.web.autodata.service.EmojiService;

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
 * 表情管理 EmojiController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-01-23 21:36:21
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/emoji")
public class EmojiController {
	@Autowired
	private EmojiService emojiService;
	@Autowired
	MenuInfoService menuService;


	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model,Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);
		
		maps.put("menus", leftmenus);
		return "/page/emoji/index";
	}
	


	

	@RequestMapping("/emojiList")
	//@ManagerActionLog(operateDescribe="查询表情",operateFuncType=FunLogType.Query,operateModelClassName=EmojiMapper.class)
	@ResponseBody
	public String emojiList( Emoji item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<Emoji> emojis = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		emojis = emojiService.selectEmojiList(item);

		try {
			rst = PageUtil.packageTableData(page, emojis);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	//@ManagerActionLog(operateDescribe="删除表情",operateFuncType=FunLogType.Del,operateModelClassName=EmojiMapper.class)
	@ResponseBody
	public Message delete( Emoji item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = emojiService.deleteEmoji(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		Emoji emojis = emojiService.selectEmojiById(id);
		return JSONObject.fromObject(emojis).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param emoji
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	//@ManagerActionLog(operateDescribe="保存修改表情",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=EmojiMapper.class)
	@ResponseBody
	public String saveOrUpdate(Emoji emoji) {

		JSONObject jsonObject = null;
		try {
			if (null == emoji.getId() || "".equals(emoji.getId())) {
				
				jsonObject = emojiService.saveEmoji(emoji);

			} else {
				jsonObject = emojiService.updateEmoji(emoji);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectemoji")
    @ResponseBody
    public List<Emoji> selectemoji( Emoji item) {
        return emojiService.selectEmojiList(item);
    }


}