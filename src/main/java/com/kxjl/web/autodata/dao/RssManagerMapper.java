package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.RssManager;

public interface RssManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(RssManager record);

    int insertSelective(RssManager record);

    RssManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RssManager record);

    int updateByPrimaryKey(RssManager record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<RssManager> selectList(RssManager item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-01-28 22:50:54
     */
    int delete(RssManager item);
}