package com.kxjl.web.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;




import com.kxjl.web.system.model.RequestInfo;


public interface RequestInfoService {
	/**
	 * 获取单位时间内该ip的指定请求类型访问总次数
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
