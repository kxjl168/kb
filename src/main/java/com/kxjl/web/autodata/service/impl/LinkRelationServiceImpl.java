package com.kxjl.web.autodata.service.impl;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.kxjl.tool.utils.UUIDUtil;
import com.kxjl.web.autodata.dao.LinkRelationMapper;
import com.kxjl.web.autodata.pojo.LinkRelation;
import com.kxjl.web.autodata.service.LinkRelationService;

import java.util.*;

@Service
public class LinkRelationServiceImpl implements LinkRelationService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LinkRelationMapper itemMapper;

	/**
	 * @param item
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject saveLinkRelation(LinkRelation item) {
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
	public JSONObject updateLinkRelation(LinkRelation item) {
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
	public List<LinkRelation> selectLinkRelationList(LinkRelation item) {
		List<LinkRelation> itemList = new ArrayList<>();
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
	public int deleteLinkRelation(LinkRelation item) {
		int result = 0;
		try {

			result = itemMapper.delete(item);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除出错");

		}
		return result;
	}

	public void deleteByFromLink(LinkRelation query) {
		itemMapper.deleteByFromLink(query);
	}

	@Override
	public LinkRelation selectLinkRelationById(String id) {
		LinkRelation data = null;

		LinkRelation query = new LinkRelation();
		query.setId(id);

		List<LinkRelation> datas = selectLinkRelationList(query);
		if (datas != null && datas.size() > 0) {
			data = datas.get(0);

		}
		return data;

	}

}
