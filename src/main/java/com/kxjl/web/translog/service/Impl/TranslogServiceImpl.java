package com.kxjl.web.translog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.web.translog.dao.RouteCompletelogDao;
import com.kxjl.web.translog.dao.RouteUselogDao;
import com.kxjl.web.translog.dao.RoutelogDao;
import com.kxjl.web.translog.dao.SpiderlogDao;

import com.kxjl.web.translog.model.RouteCompletelog;
import com.kxjl.web.translog.model.RouteUselog;
import com.kxjl.web.translog.model.Routelog;
import com.kxjl.web.translog.model.Spiderlog;

import com.kxjl.web.translog.service.TranslogService;

@Service(value = "TranslogService")
public class TranslogServiceImpl implements TranslogService {

	@Autowired
	SpiderlogDao spiderlogDao;

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
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Spiderlog> getSpiderlogPageList(Spiderlog query){
		String time_type=query.getDateFormat();
		String dateFormat="%Y-%m-%d %H";
		if(time_type.equals("HOUR"))
			 dateFormat="%Y-%m-%d %H";
		else if(time_type.equals("DAY"))
			 dateFormat="%Y-%m-%d";
		else  if(time_type.equals("MONTH"))
			 dateFormat="%Y-%m";
		
		query.setDateFormat(dateFormat);
		
		return spiderlogDao.getSpiderlogPageList(query);
	}

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getSpiderlogPageListCount(Spiderlog query){
		return spiderlogDao.getSpiderlogPageListCount(query);
	}
	
	/**
	 * 添加Spiderlog
	 * @param Spiderlog
	 * @return
	 */
	public int addSpiderlog(Spiderlog Spiderlog){
		return spiderlogDao.addSpiderlog(Spiderlog);
				
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
