/*
 * @(#)RssManagerController.java
 * @author: zhangJ
 * @Date: 2019-01-28 22:50:54
 * Copyright (C),2017-2018, kxjl 
 *  All Rights Reserved.
 * 
 */
package com.kxjl.web.autodata.controller.WebController;

import com.github.pagehelper.Page;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.IPUtils;
//import com.ztgm.base.aopAspect.FunLogType;
//import com.ztgm.base.aopAspect.ManagerActionLog;
import com.kxjl.tool.utils.PageCondition;
import com.kxjl.web.generator.pojo.Message;
import com.kxjl.web.stastic.model.ActionLog;
import com.kxjl.web.system.action.base.OutApiAuthorization;
import com.kxjl.web.system.action.base.OutApiAuthorization.UrlType;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.service.MenuInfoService;
import com.kxjl.tool.utils.PageUtil;
import com.kxjl.tool.utils.RssUtil;
import com.kxjl.web.autodata.dao.RssManagerMapper;
import com.kxjl.web.autodata.pojo.RssManager;
import com.kxjl.web.autodata.pojo.RssPageList;
import com.kxjl.web.autodata.service.RssManagerService;
import com.kxjl.web.autodata.service.RssPageListService;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Rss订阅管理 RssManagerController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2019-01-28 22:50:54
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/rssmanager")
public class RssManagerController {
	@Autowired
	private RssManagerService rssmanagerService;
	@Autowired
	MenuInfoService menuService;

	@Autowired
	RssPageListService rssListService;

	private Logger logger = LoggerFactory.getLogger(RssManagerController.class);

	@RequestMapping("/manager")
	public String manager(HttpServletRequest request, Model model, Map<String, Object> maps) {
		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);

		maps.put("menus", leftmenus);
		return "/page/rssmanager/index";
	}

	@RequestMapping("/rssmanagerList")
	// @ManagerActionLog(operateDescribe="查询Rss订阅",operateFuncType=FunLogType.Query,operateModelClassName=RssManagerMapper.class)
	@ResponseBody
	public String rssmanagerList(RssManager item, HttpServletRequest request, PageCondition pageCondition) {

		String rst = "";
		List<RssManager> rssmanagers = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		rssmanagers = rssmanagerService.selectRssManagerList(item);

		try {
			rst = PageUtil.packageTableData(page, rssmanagers);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	// @ManagerActionLog(operateDescribe="删除Rss订阅",operateFuncType=FunLogType.Del,operateModelClassName=RssManagerMapper.class)
	@ResponseBody
	public Message delete(RssManager item, HttpServletRequest request) {

		Message msg = new Message();

		int result = rssmanagerService.deleteRssManager(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load(@RequestParam String id, HttpServletRequest request) {

		RssManager rssmanagers = rssmanagerService.selectRssManagerById(id);
		return JSONObject.fromObject(rssmanagers).toString();
	}

	/**
	 * 新增/修改订阅
	 *
	 * @param rssmanager
	 * @return
	 */
	@OutApiAuthorization(uType = UrlType.NeedAdmin)
	@RequestMapping("/saveOrUpdate")
	// @ManagerActionLog(operateDescribe="保存修改Rss订阅",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=RssManagerMapper.class)
	@ResponseBody
	public String saveOrUpdate(RssManager rssmanager) {

		JSONObject jsonObject = null;
		try {

			// 解析
			jsonObject = rssmanagerService.refreshRssAndUpdateList(rssmanager);

		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}

	/**
	 * 刷新订阅
	 * 
	 * @param item
	 * @return
	 * @author zj
	 * @date 2019年2月1日
	 */
	@RequestMapping("/rssfresh")
	// @ManagerActionLog(operateDescribe="保存修改Rss订阅",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=RssManagerMapper.class)
	@ResponseBody
	public String rssfresh(RssManager item) {

		JSONObject jsonObject = null;
		try {

			RssManager rssmanager = rssmanagerService.selectRssManagerById(item.getId());

			jsonObject = rssmanagerService.refreshRssAndUpdateList(rssmanager);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// assert jsonObject != null;
		return jsonObject.toString();
	}

	/**
	 * 标记为已读
	 * 
	 * @param item
	 * @return
	 * @author zj
	 * @date 2019年2月2日
	 */
	@RequestMapping("/readAllRss")
	// @ManagerActionLog(operateDescribe="保存修改Rss订阅",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=RssManagerMapper.class)
	@ResponseBody
	public String readAllRss(RssManager item) {

		JSONObject jsonObject = new JSONObject();
		try {

			// RssManager rssmanager=rssmanagerService.selectRssManagerById(item.getId());

			rssListService.readAllRss(item.getId());

			jsonObject.put("bol", true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// assert jsonObject != null;
		return jsonObject.toString();
	}

	/**
	 * 刷新全部订阅
	 * 
	 * @param item
	 * @return
	 * @author zj
	 * @date 2019年2月2日
	 */
	@RequestMapping("/rssfreshAll")
	// @ManagerActionLog(operateDescribe="保存修改Rss订阅",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=RssManagerMapper.class)
	@ResponseBody
	public String rssfreshAll(RssManager item) {

		JSONObject jsonObject = new JSONObject();

		try {

			// RssManager rssmanager=rssmanagerService.selectRssManagerById(item.getId());

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {

						List<RssManager> rssMlist = rssmanagerService.selectRssManagerList(null);

						for (int i = 0; i < rssMlist.size(); i++) {

							// if (rssMlist.get(i).getAutoRss().equals("1")) {
							rssmanagerService.refreshRssAndUpdateList(rssMlist.get(i));
							if (jsonObject.optBoolean("bol")) {

							}
							// }
						}

					} catch (Exception e) {

					}
				}
			}).run();

			jsonObject.put("bol", true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// assert jsonObject != null;
		return jsonObject.toString();
	}

	/**
	 * 清理3个月以上的订阅数据，非收藏数据，直接删除内容
	 * 
	 * @param item
	 * @return
	 * @author zj
	 * @date 2019年3月14日
	 */
	@RequestMapping("/rssCleanAll")
	// @ManagerActionLog(operateDescribe="保存修改Rss订阅",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=RssManagerMapper.class)
	@ResponseBody
	public String rssCleanAll() {

		JSONObject jsonObject = new JSONObject();

		try {

			// RssManager rssmanager=rssmanagerService.selectRssManagerById(item.getId());

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {

						RssPageList query = new RssPageList();
						String days = ConfigReader.getInstance().getProperty("deldays", "90"); // 默认三个月
						rssListService.cleanAllRssByMonth(days);

					} catch (Exception e) {

					}
				}
			}).run();

			jsonObject.put("bol", true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// assert jsonObject != null;
		return jsonObject.toString();
	}

	/**
	 * 定时任务，刷新
	 * 
	 * @return
	 * @author zj
	 * @date 2019年2月2日
	 */
	public String rssfreshAllAuto() {

		JSONObject jsonObject = new JSONObject();
		try {

			if (ConfigReader.getInstance().getProperty("rssfreshAll", "false").equals("false"))
				return "";

			logger.error("定时刷新RSS开始...");
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {

						List<RssManager> rssMlist = rssmanagerService.selectRssManagerList(null);

						for (int i = 0; i < rssMlist.size(); i++) {

							try {

								if (rssMlist.get(i).getAutoRss().equals("1")) {
									rssmanagerService.refreshRssAndUpdateList(rssMlist.get(i));
									if (jsonObject.optBoolean("bol")) {

									}
								}
							} catch (Exception e) {
								logger.error(e.getMessage());
								continue;
							}
						}

						logger.error("定时刷新RSS完成...");

						if (ConfigReader.getInstance().getProperty("debug", "false").equals("true")) {
							logger.info("定时刷新RSS完成！");
						}

					} catch (Exception e) {

					}
				}
			}).run();

			jsonObject.put("bol", true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// assert jsonObject != null;
		return jsonObject.toString();
	}

	@RequestMapping("/selectrssmanager")
	@ResponseBody
	public List<RssManager> selectrssmanager(RssManager item) {
		return rssmanagerService.selectRssManagerList(item);
	}

}