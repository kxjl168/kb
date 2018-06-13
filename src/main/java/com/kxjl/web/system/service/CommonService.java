package com.kxjl.web.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;




import com.kxjl.web.system.model.DictInfo;


public interface CommonService {
	
	
	/**
	 * 定时任务，清空ip缓存，重新计算
	 */
	public void resetBlackIPList();
	/**
	 * 检测请求ip是否在黑名单
	 * @param request
	 * @return
	 */
	public Boolean isInBlackIPList(HttpServletRequest request);

	
	
}
