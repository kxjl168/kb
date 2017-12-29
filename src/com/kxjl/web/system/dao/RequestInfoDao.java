package com.kxjl.web.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kxjl.web.system.model.RequestInfo;



public interface RequestInfoDao {

	
	
	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRequestCountByCondition(RequestInfo query);
	
	/**
	 * 添加RequestInfo
	 * @param RequestInfo
	 * @return
	 */
	public int addRequestInfo(RequestInfo RequestInfo);
	
	

}
