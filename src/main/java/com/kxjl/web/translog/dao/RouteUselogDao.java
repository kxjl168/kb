package com.kxjl.web.translog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.kxjl.web.translog.model.RouteUselog;

public interface RouteUselogDao {

	
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<RouteUselog> getRouteUselogPageList(RouteUselog query);

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRouteUselogPageListCount(RouteUselog query);
	
	/*
	*//**
	 * 添加RouteUselog
	 * @param RouteUselog
	 * @return
	 *//*
	public int addRouteUselog(RouteUselog RouteUselog);
	
	*//**
	 * 删除RouteUselog
	 * @param id
	 * @return
	 *//*
	public int deleteRouteUselog(@Param(value="banner_id")Integer id);

	*//**
	 * 更新RouteUselog
	 * @param RouteUselog
	 * @return
	 *//*
	public int updateRouteUselog(RouteUselog RouteUselog);
	
	*//**
	 *  获取RouteUselog列表
	 * @return
	 *//*
	public List<RouteUselog> getRouteUselogList();



	*//**
	 * 根据ID获取RouteUselog信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 *//*
	public RouteUselog getRouteUselogInfoById(@Param(value="banner_id")Integer banner_id);*/
}
