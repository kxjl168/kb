package com.kxjl.web.routeM.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;



import com.kxjl.web.proxyserver.model.Proxyserver;
import com.kxjl.web.routeM.model.ProxyserverRoute;

public interface ProxyserverRouteDao {

	
	/**
	 * 查询中转服务器上的路由器分配情况
	 * 根据   中转服务器计划分配数-当前在线数   缺口大到小排序
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<ProxyserverRoute> getProxyserverRouteListOrderbyGap(ProxyserverRoute query);
	

	
	/**
	 * 更新ProxyserverRoute
	 * 
	 * @param ProxyserverRoute
	 * @return
	 */
	public int updateProxyserverRoute(ProxyserverRoute ProxyserverRoute) ;
	
	/**
	 * 添加ProxyserverRoute
	 * @param ProxyserverRoute
	 * @return
	 */
	public int addProxyserverRoute(ProxyserverRoute ProxyserverRoute);
	
	
	/*
	*//**
	
	
	*//**
	 * 删除ProxyserverRoute
	 * @param id
	 * @return
	 *//*
	public int deleteProxyserverRoute(@Param(value="banner_id")Integer id);

	
	
	*//**
	 *  获取ProxyserverRoute列表
	 * @return
	 *//*
	public List<ProxyserverRoute> getProxyserverRouteList();



	/**
	 * 根据获取ProxyserverRoute信息 id和city
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public ProxyserverRoute getProxyserverRouteInfo(ProxyserverRoute ProxyserverRoute);
	
	
	
	
	
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
	
	/**
	 * 按proxyserver, sum(phonenum) 查询 各个中转服务器上的手机数
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-19
	 */
	public List<ProxyserverRoute> getProxyserverGroupInfo();
	
}
