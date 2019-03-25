package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.Calthings;

public interface CalthingsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Calthings record);

    int insertSelective(Calthings record);

    Calthings selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Calthings record);

    int updateByPrimaryKey(Calthings record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<Calthings> selectList(Calthings item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-03-25 22:29:30
     */
    int delete(Calthings item);
}