package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.CcList;

public interface CcListMapper {
    int deleteByPrimaryKey(String id);

    int insert(CcList record);

    int insertSelective(CcList record);

    CcList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CcList record);

    int updateByPrimaryKey(CcList record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<CcList> selectList(CcList item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-01-22 14:55:15
     */
    int delete(CcList item);
}