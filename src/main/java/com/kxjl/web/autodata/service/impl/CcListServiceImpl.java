package com.kxjl.web.autodata.service.impl;



import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import com.kxjl.tool.utils.UUIDUtil;
import com.kxjl.web.autodata.dao.CcListMapper;
import com.kxjl.web.autodata.pojo.CcList;
import com.kxjl.web.autodata.service.CcListService;

import java.util.*;

@Service
public class CcListServiceImpl implements CcListService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CcListMapper itemMapper;

	/**
	 * @param item
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject saveCcList(CcList item) {
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
	public JSONObject updateCcList(CcList item) {
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
	public List<CcList> selectCcListList(CcList item) {
		List<CcList> itemList = new ArrayList<>();
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
	public int deleteCcList(CcList item) {
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
	public CcList selectCcListById(String id) {
		CcList data = null;

		CcList query = new CcList();
		query.setId(id);

		List<CcList> datas = selectCcListList(query);
		if (datas != null && datas.size() > 0) {
			data = datas.get(0);

		}
		return data;

	}

}
