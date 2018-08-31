package com.kxjl.web.autodata.dao;

import com.kxjl.web.autodata.model.LikeInfo;

public interface LikeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeInfo record);

    int insertSelective(LikeInfo record);

    LikeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeInfo record);

    int updateByPrimaryKey(LikeInfo record);
    
    
    /**
     * 是否已经点赞过
     * @param like
     * @return
     * @author zj
     * @date 2018年8月31日
     */
    LikeInfo selectByCookie(LikeInfo like);
    
    /**
     * 总暂数
     * @param imei
     * @return
     * @author zj
     * @date 2018年8月31日
     */
    int  getTotalLikeNum(String imei);
}