package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.BlackIpList;

public interface BlackIpListMapper {
    int deleteByPrimaryKey(String id);

    int insert(BlackIpList record);

    int insertSelective(BlackIpList record);

    BlackIpList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BlackIpList record);

    int updateByPrimaryKey(BlackIpList record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<BlackIpList> selectList(BlackIpList item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-01-22 15:28:15
     */
    int delete(BlackIpList item);
}