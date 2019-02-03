package com.kxjl.web.autodata.service.impl;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.kxjl.tool.utils.UUIDUtil;
import com.kxjl.web.autodata.dao.RssPageListMapper;
import com.kxjl.web.autodata.pojo.RssPageList;
import com.kxjl.web.autodata.service.RssPageListService;

import java.util.*;

@Service
public class RssPageListServiceImpl implements RssPageListService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RssPageListMapper itemMapper;

	/**
	 * @param item
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject saveRssPageList(RssPageList item) {
		JSONObject rtn = new JSONObject();

		/*
		 * if (null == item || null == item.getPassword() || null ==
		 * item.getTelephone()) { rtn.put("bol", false); rtn.put("message",
		 * "手机号码或者密码为空"); return rtn; }
		 */
		try {

			item.setId(UUIDUtil.getUUID());

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
	public JSONObject updateRssPageList(RssPageList item) {
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
	 * 保持或者修改
	 * 
	 * @param item
	 * @return
	 * @author zj
	 * @date 2019年1月29日
	 */
	public JSONObject SaveOrUpdateRssPageList(RssPageList item) {
		JSONObject rtn = new JSONObject();

		if (null == item || null == item.getId()) {
			rtn.put("bol", false);
			rtn.put("message", "id为空");
			return rtn;
		}

		try {

			RssPageList pageimte = itemMapper.selectByPrimaryKey(item.getId());
			if (pageimte != null)
				itemMapper.updateByPrimaryKeySelective(item);
			else
				itemMapper.insertSelective(item);

			rtn.put("bol", true);

			return rtn;
		} catch (Exception e) {

			log.error("更新出错", e);
			rtn.put("bol", false);
			rtn.put("message", "更新出错");
			return rtn;
		}
	}

	@Override
	public List<RssPageList> selectRssPageListList(RssPageList item) {
		List<RssPageList> itemList = new ArrayList<>();
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
	public int deleteRssPageList(RssPageList item) {
		int result = 0;
		try {

			result = itemMapper.delete(item);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除出错");

		}
		return result;
	}

	@Override
	public RssPageList selectRssPageListById(String id) {
		RssPageList data = null;

		RssPageList query = new RssPageList();
		query.setId(id);

		List<RssPageList> datas = selectRssPageListList(query);
		if (datas != null && datas.size() > 0) {
			data = datas.get(0);

		}
		return data;

	}

	@Override
	public void readAllRss(String id) {
		itemMapper.readAllRss(id);

	}

	/**
	 * 删除所有该站点订阅文章
	 * 
	 * @param id
	 * @author zj
	 * @date 2019年2月3日
	 */
	public void delAllRssByMid(String id) {
		itemMapper.delAllRssByMid(id);
	}

}
