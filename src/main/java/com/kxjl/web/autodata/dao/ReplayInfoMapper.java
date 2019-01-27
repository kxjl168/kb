package com.kxjl.web.autodata.dao;
import java.util.List;

import com.kxjl.web.autodata.pojo.ReplayInfo;

public interface ReplayInfoMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(ReplayInfo record);

    int insertSelective(ReplayInfo record);

    ReplayInfo selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(ReplayInfo record);

    int updateByPrimaryKeyWithBLOBs(ReplayInfo record);

    int updateByPrimaryKey(ReplayInfo record);


	/**
     * 列表查询
     * @param item
     * @return
     * @author KAutoGenerator
     * @date 2019年1月7日
     */
    List<ReplayInfo> selectList(ReplayInfo item);
    
    /**
     * 删除
     * @param record
     * @return
     * @author KAutoGenerator
     * @date 2019-01-27 20:32:55
     */
    int delete(ReplayInfo item);
}