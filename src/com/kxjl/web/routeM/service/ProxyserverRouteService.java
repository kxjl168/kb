package com.kxjl.web.routeM.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;



import com.kxjl.web.routeM.model.ProxyserverRoute;
import com.kxjl.web.routeM.model.ServerCompleteRoute;

public interface ProxyserverRouteService {

	
	/**
	 * 查询中转服务器上的路由器分配情况
	 * 根据   中转服务器计划分配数-当前在线数   缺口大到小排序
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<ProxyserverRoute> getProxyserverRouteListOrderbyGap(ProxyserverRoute query);
	
	/**
	 * 调整中转服务器预设 路由器大小
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public void adjustPlanRouteNum();
	
	
	/**
	 * 更新ProxyserverRoute  id和city必须要
	 * @param ProxyserverRoute
	 * @return
	 */
	public int updateProxyserverRoute(ProxyserverRoute ProxyserverRoute);
	
	/**
	 * 查询已服务完成的路由
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public List<ServerCompleteRoute> getServerCompleteRoutePageList(ServerCompleteRoute query);
	
	
	

	/*
	*//**
	 * 添加ProxyserverRoute
	 * @param ProxyserverRoute
	 * @return
	 *//*
	public int addProxyserverRoute(ProxyserverRoute ProxyserverRoute);
	
	*//**
	 * 删除ProxyserverRoute
	 * @param id
	 * @return
	 *//*
	public int deleteProxyserverRoute(@Param(value="banner_id")Integer id);

	*//**
	 * 更新ProxyserverRoute
	 * @param ProxyserverRoute
	 * @return
	 *//*
	public int updateProxyserverRoute(ProxyserverRoute ProxyserverRoute);
	
	*//**
	 *  获取ProxyserverRoute列表
	 * @return
	 *//*
	public List<ProxyserverRoute> getProxyserverRouteList();
*/


	/**
	 * 分页获取列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<ProxyserverRoute> getProxyserverRoutePageList(ProxyserverRoute query);

	/**
	 * 获取总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getProxyserverRoutePageListCount(ProxyserverRoute query);
	
	

}
