package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.Myip;

public interface MyipMapper {
    int deleteByPrimaryKey(String id);

    int insert(Myip record);

    int insertSelective(Myip record);

    Myip selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Myip record);

    int updateByPrimaryKey(Myip record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<Myip> selectList(Myip item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-01-15 18:31:42
     */
    int delete(Myip item);
}