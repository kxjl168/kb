package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.SearchKeys;

public interface SearchKeysMapper {
    int deleteByPrimaryKey(String id);

    int insert(SearchKeys record);

    int insertSelective(SearchKeys record);

    SearchKeys selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SearchKeys record);

    int updateByPrimaryKeyWithBLOBs(SearchKeys record);

    int updateByPrimaryKey(SearchKeys record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<SearchKeys> selectList(SearchKeys item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2020-01-15 15:54:03
     */
    int delete(SearchKeys item);
}