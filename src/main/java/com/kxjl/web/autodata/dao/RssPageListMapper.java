package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.RssPageList;

public interface RssPageListMapper {
    int deleteByPrimaryKey(String id);

    int insert(RssPageList record);

    int insertSelective(RssPageList record);

    RssPageList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RssPageList record);

    int updateByPrimaryKeyWithBLOBs(RssPageList record);

    int updateByPrimaryKey(RssPageList record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<RssPageList> selectList(RssPageList item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-01-28 22:51:27
     */
    int delete(RssPageList item);
}