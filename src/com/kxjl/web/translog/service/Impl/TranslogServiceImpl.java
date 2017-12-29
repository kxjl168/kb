package com.kxjl.web.translog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.web.translog.dao.RouteCompletelogDao;
import com.kxjl.web.translog.dao.RouteUselogDao;
import com.kxjl.web.translog.dao.RoutelogDao;
import com.kxjl.web.translog.dao.TranslogDao;
import com.kxjl.web.translog.model.RouteCompletelog;
import com.kxjl.web.translog.model.RouteUselog;
import com.kxjl.web.translog.model.Routelog;
import com.kxjl.web.translog.model.Translog;
import com.kxjl.web.translog.service.TranslogService;

@Service(value = "TranslogService")
public class TranslogServiceImpl implements TranslogService {

	@Autowired
	TranslogDao bannerDao;

	@Autowired
	RouteUselogDao routeuselogDao;
	@Autowired
	RoutelogDao routelogDao;
	@Autowired
	RouteCompletelogDao routeCompletelogDao;

	@Autowired
	SystemParamsDao sysDao;

	/**
	 * 分页获取banner列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public List<Translog> getTranslogPageList(Translog query) {
		return bannerDao.getTranslogPageList(query);
	}

	/**
	 * 获取banner总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public int getTranslogPageListCount(Translog query) {
		return bannerDao.getTranslogPageListCount(query);
	}

	/**
	 * 分页获取RouteCompletelog列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<RouteCompletelog> getRouteCompletelogPageList(
			RouteCompletelog query) {
		return routeCompletelogDao.getRouteCompletelogPageList(query);
	}

	/**
	 * 获取RouteCompletelog总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRouteCompletelogPageListCount(RouteCompletelog query){
		return routeCompletelogDao.getRouteCompletelogPageListCount(query);
	}

	/**
	 * 分页获取Routelog列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Routelog> getRoutelogPageList(Routelog query){
		return routelogDao.getRoutelogPageList(query);
	}

	/**
	 * 获取Routelog总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRoutelogPageListCount(Routelog query){
		return routelogDao.getRoutelogPageListCount(query);
	}

	/**
	 * 分页获取RouteUselog列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<RouteUselog> getRouteUselogPageList(RouteUselog query){
		return routeuselogDao.getRouteUselogPageList(query);
	}

	/**
	 * 获取RouteUselog总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRouteUselogPageListCount(RouteUselog query){
		return routeuselogDao.getRouteUselogPageListCount(query);
	}

}
