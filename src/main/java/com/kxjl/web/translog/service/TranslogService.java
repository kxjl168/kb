package com.kxjl.web.translog.service;

import java.util.List;



import com.kxjl.web.translog.model.RouteCompletelog;
import com.kxjl.web.translog.model.RouteUselog;
import com.kxjl.web.translog.model.Routelog;
import com.kxjl.web.translog.model.Spiderlog;



public interface TranslogService {
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Spiderlog> getSpiderlogPageList(Spiderlog query);

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getSpiderlogPageListCount(Spiderlog query);
	
	/**
	 * 添加Spiderlog
	 * @param Spiderlog
	 * @return
	 */
	public int addSpiderlog(Spiderlog Spiderlog);
	
	
	
	/**
	 * 分页获取RouteCompletelog列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<RouteCompletelog> getRouteCompletelogPageList(RouteCompletelog query);

	/**
	 * 获取RouteCompletelog总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRouteCompletelogPageListCount(RouteCompletelog query);
	
	
	/**
	 * 分页获取Routelog列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Routelog> getRoutelogPageList(Routelog query);

	/**
	 * 获取Routelog总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRoutelogPageListCount(Routelog query);
	
	
	
	/**
	 * 分页获取RouteUselog列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<RouteUselog> getRouteUselogPageList(RouteUselog query);

	/**
	 * 获取RouteUselog总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRouteUselogPageListCount(RouteUselog query);
	
}
