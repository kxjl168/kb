package com.kxjl.web.system.service;

import static com.google.common.collect.FluentIterable.from;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.google.common.base.Predicate;
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

	
	
	/**
	 * 根据agent标识 判断 是否为爬虫
	 * 
	 * @param userAgent
	 * @return
	 * @author zj
	 * @date 2018年10月18日
	 */
	public boolean isInSearchUserAgent(final String userAgent);

	/**
	 * 根据agent标识 判断 是否在禁止访问的爬虫列表中
	 * 
	 * @param userAgent
	 * @return
	 * @author zj
	 * @date 2019年1月18日
	 */
	public boolean isInBlackSearchUserAgent(final String userAgent);

	
}
