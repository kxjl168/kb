package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.Emoji;

public interface EmojiMapper {
    int deleteByPrimaryKey(String id);

    int insert(Emoji record);

    int insertSelective(Emoji record);

    Emoji selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Emoji record);

    int updateByPrimaryKey(Emoji record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<Emoji> selectList(Emoji item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-01-23 17:10:55
     */
    int delete(Emoji item);
}