package com.kxjl.web.autodata.service.impl;



import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.kxjl.tool.utils.DateUtil;
import com.kxjl.tool.utils.UUIDUtil;
import com.kxjl.web.autodata.dao.MoneyMapper;
import com.kxjl.web.autodata.pojo.Money;
import com.kxjl.web.autodata.pojo.YearMoney;
import com.kxjl.web.autodata.service.MoneyService;

import java.util.*;

@Service
public class MoneyServiceImpl implements MoneyService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MoneyMapper itemMapper;

    /**
     *  支出统计，饼图
     * @param item
     * @return
     * @author zj
     * @date 2019年2月13日
     */
    public List<Money> selectZhichuStastic(Money item){
    	return itemMapper.selectZhichuStastic(item);
    }
    
    /**
     * 年度月份统计
     * @param item
     * @return
     * @author zj
     * @date 2019年2月17日
     */
    public List<YearMoney> selectYearList(Money item){
    	
    	String[] datas=item.getmDate().split(",");
		item.setmDatelst(datas);
		
		List<YearMoney> yeardatas=new ArrayList<YearMoney>();
		
		for (int i = 0; i < datas.length; i++) {
			Money yearQuery=item;
			yearQuery.setmDate(datas[i]);
					
			List<Money> monyes=itemMapper.selectYearList(yearQuery);
			
			YearMoney yMoney=new YearMoney();
			yMoney.setName(datas[i]);
			yMoney.setRows(monyes);
			
			yeardatas.add(yMoney);
		}
    	
    	
    	
    	
    	return yeardatas;
    }
    
	
    /**
     * 月份统计
     * @param item
     * @return
     * @author zj
     * @date 2019年2月12日
     */
    public List<Money> selectTotal(Money item){
    	return itemMapper.selectTotal(item);
    }
    
	
	/**
	 * @param item
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject saveMoney(Money item) {
		JSONObject rtn = new JSONObject();

		/*
		 * if (null == item || null == item.getPassword() || null ==
		 * item.getTelephone()) { rtn.put("bol", false); rtn.put("message",
		 * "手机号码或者密码为空"); return rtn; }
		 */
		try {

			item.setId(UUIDUtil.getUUID());
			
			item.setUpdateDate(DateUtil.getNowStr(""));

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
	public JSONObject updateMoney(Money item) {
		JSONObject rtn = new JSONObject();

		if (null == item || null == item.getId()) {
			rtn.put("bol", false);
			rtn.put("message", "id为空");
			return rtn;
		}

		try {
			
			item.setUpdateDate(DateUtil.getNowStr(""));
			
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
	public List<Money> selectMoneyList(Money item) {
		List<Money> itemList = new ArrayList<>();
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
	public int deleteMoney(Money item) {
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
	public Money selectMoneyById(String id) {
		Money data = null;

		Money query = new Money();
		query.setId(id);

		List<Money> datas = selectMoneyList(query);
		if (datas != null && datas.size() > 0) {
			data = datas.get(0);

		}
		return data;

	}

}
