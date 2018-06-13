package com.kxjl.web.system.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.system.dao.RequestInfoDao;
import com.kxjl.web.system.dao.MenuInfoDao;
import com.kxjl.web.system.model.RequestInfo;
import com.kxjl.web.system.service.RequestInfoService;

@Service(value = "requestInfoService")
public class RequestInfoServiceImpl implements RequestInfoService {

	@Autowired
	private RequestInfoDao requestInfoDao;

	/**
	 * 获取单位时间内该ip的指定请求类型访问总次数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRequestCountByCondition(RequestInfo query){
		return requestInfoDao.getRequestCountByCondition(query);
	}

	/**
	 * 添加RequestInfo
	 * 
	 * @param RequestInfo
	 * @return
	 */
	public int addRequestInfo(RequestInfo RequestInfo){
		return requestInfoDao.addRequestInfo(RequestInfo);
	}

	

}
