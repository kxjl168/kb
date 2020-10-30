package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.LinkRelation;

public interface LinkRelationMapper {
    int deleteByPrimaryKey(String id);

    int insert(LinkRelation record);

    int insertSelective(LinkRelation record);

    LinkRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LinkRelation record);

    int updateByPrimaryKey(LinkRelation record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<LinkRelation> selectList(LinkRelation item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2020-10-23 09:16:55
     */
    int delete(LinkRelation item);
    
    void deleteByFromLink(LinkRelation item);
}