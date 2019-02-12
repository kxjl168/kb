package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.Money;

public interface MoneyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Money record);

    int insertSelective(Money record);

    Money selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Money record);

    int updateByPrimaryKey(Money record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<Money> selectList(Money item);
    
    /**
     * 月份统计
     * @param item
     * @return
     * @author zj
     * @date 2019年2月12日
     */
    List<Money> selectTotal(Money item);
    
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-02-12 16:45:42
     */
    int delete(Money item);
}