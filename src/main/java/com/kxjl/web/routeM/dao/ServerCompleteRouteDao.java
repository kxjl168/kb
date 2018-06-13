package com.kxjl.web.routeM.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;



import com.kxjl.web.routeM.model.ServerCompleteRoute;

public interface ServerCompleteRouteDao {

	
	/**
	 * 查询已服务完成的路由器
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<ServerCompleteRoute> getServerCompleteRouteList(ServerCompleteRoute query);

/*	*//**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 *//*
	public int getServerCompleteRoutePageListCount(ServerCompleteRoute query);
	*/
	/*
	*//**
	 * 添加ServerCompleteRoute
	 * @param ServerCompleteRoute
	 * @return
	 *//*
	public int addServerCompleteRoute(ServerCompleteRoute ServerCompleteRoute);
	
	*//**
	 * 删除ServerCompleteRoute
	 * @param id
	 * @return
	 *//*
	public int deleteServerCompleteRoute(@Param(value="banner_id")Integer id);

	*//**
	 * 更新ServerCompleteRoute
	 * @param ServerCompleteRoute
	 * @return
	 *//*
	public int updateServerCompleteRoute(ServerCompleteRoute ServerCompleteRoute);
	
	*//**
	 *  获取ServerCompleteRoute列表
	 * @return
	 *//*
	public List<ServerCompleteRoute> getServerCompleteRouteList();



	/**
	 * 根据ID获取ServerCompleteRoute信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	/*public ServerCompleteRoute getServerCompleteRouteInfoById(@Param(value="id")String id);*/
}
