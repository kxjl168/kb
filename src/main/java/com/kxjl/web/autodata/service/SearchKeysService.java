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

import com.kxjl.web.autodata.pojo.SearchKeys;

/**
 * 小区
 * CommunityService.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月11日
* @revision zj 2018年12月11日
* @since 1.0.1
 */
public interface SearchKeysService {


    /**
     * 新增
     */
    JSONObject saveSearchKeys(SearchKeys query);

    /**
     * 更新信息
     */
    JSONObject updateSearchKeys(SearchKeys query);


    List<SearchKeys> selectSearchKeysList(SearchKeys query);

    int deleteSearchKeys(SearchKeys query);
    
    SearchKeys selectSearchKeysById(String id);


    /**
     * 过滤授权嘛
     * @param key
     * @return
     * @author zj
     * @date 2020年1月15日
     */
    SearchKeys selectSearchKeysByKey(String key);

    /**
     * 可用次数减一
     * @param key
     * @return
     * @author zj
     * @date 2020年1月15日
     */
    int  reduceUseTimeByKey(String key);


}
