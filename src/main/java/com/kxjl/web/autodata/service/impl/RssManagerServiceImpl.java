package com.kxjl.web.autodata.service.impl;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.kxjl.tool.utils.DateUtil;
import com.kxjl.tool.utils.RssUtil;
import com.kxjl.tool.utils.UUIDUtil;
import com.kxjl.web.autodata.dao.RssManagerMapper;
import com.kxjl.web.autodata.pojo.RssManager;
import com.kxjl.web.autodata.pojo.RssPageList;
import com.kxjl.web.autodata.service.RssManagerService;
import com.kxjl.web.autodata.service.RssPageListService;

import java.net.URL;
import java.util.*;

@Service
public class RssManagerServiceImpl implements RssManagerService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RssManagerMapper itemMapper;

	@Autowired
	private RssPageListService rssListService;

	/**
	 * @param item
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject saveRssManager(RssManager item) {
		JSONObject rtn = new JSONObject();

		/*
		 * if (null == item || null == item.getPassword() || null ==
		 * item.getTelephone()) { rtn.put("bol", false); rtn.put("message",
		 * "手机号码或者密码为空"); return rtn; }
		 */
		try {

			item.setId(UUIDUtil.getUUID());

			item.setUpdateDate(DateUtil.getNowStr(""));
			item.setLastRssDate(DateUtil.getNowStr(""));

			itemMapper.insertSelective(item);

			rtn.put("bol", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("新增失败", e);
			rtn.put("bol", false);
			rtn.put("message", "新增失败");

			return rtn;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateRssManager(RssManager item) {
		JSONObject rtn = new JSONObject();

		if (null == item || null == item.getId()) {
			rtn.put("bol", false);
			rtn.put("message", "id为空");
			return rtn;
		}

		try {
			itemMapper.updateByPrimaryKeySelective(item);

			rtn.put("bol", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("更新出错", e);
			rtn.put("bol", false);
			rtn.put("message", "更新出错");
			return rtn;
		}
	}

	/**
	 * 刷新rss订阅数据
	 * 
	 * @param rssmanager
	 * @author zj
	 * @date 2019年2月1日
	 */
	public JSONObject refreshRssAndUpdateList(RssManager rssmanager) {

		JSONObject jsonObject = new JSONObject();

		boolean isok = false;
		try {

			if (null == rssmanager.getId() || "".equals(rssmanager.getId())) {
				RssManager rquery = new RssManager();
				rquery.setLink(rssmanager.getLink());
				List<RssManager> tps = selectRssManagerList(rquery);
				if (tps != null && tps.size() != 0) {
					rssmanager.setId(tps.get(0).getId());
				}
			}

			// 解析
			Document docrss = RssUtil.parse(new URL(rssmanager.getLink()));

			RssManager rsstp = RssUtil.rssParse(docrss);

			if (rsstp != null) {
				rssmanager.setName(rsstp.getName());
				rssmanager.setRemark(rsstp.getRemark());
			}

			List<RssPageList> plist = RssUtil.rssPagesParse(docrss);
			for (RssPageList rssPageList : plist) {
				try {
					rssPageList.setRssManagerId(rssmanager.getId());
					rssListService.SaveOrUpdateRssPageList(rssPageList);	
				} catch (Exception e) {
					rssPageList.setContext("内容存储错误-"+e.getMessage());
					rssListService.SaveOrUpdateRssPageList(rssPageList);
					continue;
				}
				
			}

			rssmanager.setLastRssDate(DateUtil.getNowStr(""));

			isok = true;

			jsonObject.put("bol", true);

		} catch (Exception e) {
			jsonObject.put("bol", false);
			log.error("error:" + e.getMessage());
			rssmanager.setRemark(e.getMessage());
			isok = false;
		}

		if (!isok)
			rssmanager.setHasError("1");
		else
			rssmanager.setHasError("0");

		if (null == rssmanager.getId() || "".equals(rssmanager.getId())) {

			jsonObject = saveRssManager(rssmanager);

		} else {
			jsonObject = updateRssManager(rssmanager);
		}

		return jsonObject;
	}

	@Override
	public List<RssManager> selectRssManagerList(RssManager item) {
		List<RssManager> itemList = new ArrayList<>();
		try {
			itemList = itemMapper.selectList(item);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询列表出错");

		}
		return itemList;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteRssManager(RssManager item) {
		int result = 0;
		try {
			//同时删除订阅文章列表
			rssListService.delAllRssByMid(item.getId());
			
			result = itemMapper.delete(item);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除出错");

		}
		return result;
	}

	@Override
	public RssManager selectRssManagerById(String id) {
		RssManager data = null;

		RssManager query = new RssManager();
		query.setId(id);

		List<RssManager> datas = selectRssManagerList(query);
		if (datas != null && datas.size() > 0) {
			data = datas.get(0);

		}
		return data;

	}

}
