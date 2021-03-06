/*
 * @(#)CommunityService.java
 * @author: zhangJ
 * @Date: 2018/12/11 09:02
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.kxjl.web.autodata.service;



import net.sf.json.JSONObject;

import java.util.List;

import com.kxjl.web.autodata.pojo.Money;
import com.kxjl.web.autodata.pojo.YearMoney;

/**
 * 小区
 * CommunityService.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月11日
* @revision zj 2018年12月11日
* @since 1.0.1
 */
public interface MoneyService {

    /**
     *  支出统计，饼图
     * @param item
     * @return
     * @author zj
     * @date 2019年2月13日
     */
    List<Money> selectZhichuStastic(Money item);
    
	
	   /**
     * 月份统计
     * @param item
     * @return
     * @author zj
     * @date 2019年2月12日
     */
    List<Money> selectTotal(Money item);
    /**
     * 新增
     */
    JSONObject saveMoney(Money query);

    /**
     * 更新信息
     */
    JSONObject updateMoney(Money query);


    List<Money> selectMoneyList(Money query);

    int deleteMoney(Money query);
    
    Money selectMoneyById(String id);



    /**
     * 年度月份统计
     * @param item
     * @return
     * @author zj
     * @date 2019年2月17日
     */
    List<YearMoney> selectYearList(Money item);
    
}
