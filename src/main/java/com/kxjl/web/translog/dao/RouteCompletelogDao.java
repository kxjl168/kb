package com.kxjl.web.translog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.kxjl.web.translog.model.RouteCompletelog;

public interface RouteCompletelogDao {

	
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<RouteCompletelog> getRouteCompletelogPageList(RouteCompletelog query);

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRouteCompletelogPageListCount(RouteCompletelog query);
	
	/*
	*//**
	 * 添加RouteCompletelog
	 * @param RouteCompletelog
	 * @return
	 *//*
	public int addRouteCompletelog(RouteCompletelog RouteCompletelog);
	
	*//**
	 * 删除RouteCompletelog
	 * @param id
	 * @return
	 *//*
	public int deleteRouteCompletelog(@Param(value="banner_id")Integer id);

	*//**
	 * 更新RouteCompletelog
	 * @param RouteCompletelog
	 * @return
	 *//*
	public int updateRouteCompletelog(RouteCompletelog RouteCompletelog);
	
	*//**
	 *  获取RouteCompletelog列表
	 * @return
	 *//*
	public List<RouteCompletelog> getRouteCompletelogList();



	*//**
	 * 根据ID获取RouteCompletelog信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 *//*
	public RouteCompletelog getRouteCompletelogInfoById(@Param(value="banner_id")Integer banner_id);*/
}
