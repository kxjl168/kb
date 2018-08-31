package com.kxjl.web.autodata.dao;

import com.kxjl.web.autodata.model.VisitData;

public interface VisitDataMapper {
    int deleteByPrimaryKey(String datetime);

    int insert(VisitData record);

    int insertSelective(VisitData record);

    VisitData selectByPrimaryKey(String datetime);

    int updateByPrimaryKeySelective(VisitData record);

    int updateByPrimaryKey(VisitData record);
    
    /**
     * 总计
     * @return
     * @author zj
     * @date 2018年6月13日
     */
    VisitData selectSum();
    
    
}