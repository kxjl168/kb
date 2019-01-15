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

import com.kxjl.web.autodata.pojo.BlackIpList;

/**
 * 小区
 * CommunityService.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月11日
* @revision zj 2018年12月11日
* @since 1.0.1
 */
public interface BlackIpListService {


    /**
     * 新增
     */
    JSONObject saveBlackIpList(BlackIpList query);

    /**
     * 更新信息
     */
    JSONObject updateBlackIpList(BlackIpList query);


    List<BlackIpList> selectBlackIpListList(BlackIpList query);

    int deleteBlackIpList(BlackIpList query);
    
    BlackIpList selectBlackIpListById(String id);




}
