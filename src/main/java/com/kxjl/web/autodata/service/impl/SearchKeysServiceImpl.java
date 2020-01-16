package com.kxjl.web.autodata.service.impl;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.kxjl.tool.utils.UUIDUtil;
import com.kxjl.web.autodata.dao.SearchKeysMapper;
import com.kxjl.web.autodata.pojo.SearchKeys;
import com.kxjl.web.autodata.service.SearchKeysService;

import java.util.*;

@Service
public class SearchKeysServiceImpl implements SearchKeysService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SearchKeysMapper itemMapper;

	/**
	 * @param item
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject saveSearchKeys(SearchKeys item) {
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
	public JSONObject updateSearchKeys(SearchKeys item) {
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

	@Override
	public List<SearchKeys> selectSearchKeysList(SearchKeys item) {
		List<SearchKeys> itemList = new ArrayList<>();
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
	public int deleteSearchKeys(SearchKeys item) {
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
	public SearchKeys selectSearchKeysById(String id) {
		SearchKeys data = null;

		SearchKeys query = new SearchKeys();
		query.setId(id);

		List<SearchKeys> datas = selectSearchKeysList(query);
		if (datas != null && datas.size() > 0) {
			data = datas.get(0);

		}
		return data;

	}

	public SearchKeys selectSearchKeysByKey(String key) {
		SearchKeys query = new SearchKeys();
		query.setGkey(key);
		List<SearchKeys> keys = selectSearchKeysList(query);
		if (keys != null && keys.size() == 1) {
			return keys.get(0);
		}
		return null;
	}

	public int reduceUseTimeByKey(String key) {
		SearchKeys gkey = selectSearchKeysByKey(key);
		if (gkey != null) {
			int curNum = Integer.parseInt(gkey.getUseTime());
			if (curNum > 0) {
				curNum--;
				gkey.setUseTime(String.valueOf(curNum));
				updateSearchKeys(gkey);
				return 1;
			}

		}
		return 0;
	}

}
