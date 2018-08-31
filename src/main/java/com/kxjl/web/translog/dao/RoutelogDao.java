package com.kxjl.web.translog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.kxjl.web.translog.model.Routelog;

public interface RoutelogDao {

	
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Routelog> getRoutelogPageList(Routelog query);

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getRoutelogPageListCount(Routelog query);
	
	/*
	*//**
	 * 添加Routelog
	 * @param Routelog
	 * @return
	 *//*
	public int addRoutelog(Routelog Routelog);
	
	*//**
	 * 删除Routelog
	 * @param id
	 * @return
	 *//*
	public int deleteRoutelog(@Param(value="banner_id")Integer id);

	*//**
	 * 更新Routelog
	 * @param Routelog
	 * @return
	 *//*
	public int updateRoutelog(Routelog Routelog);
	
	*//**
	 *  获取Routelog列表
	 * @return
	 *//*
	public List<Routelog> getRoutelogList();



	*//**
	 * 根据ID获取Routelog信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 *//*
	public Routelog getRoutelogInfoById(@Param(value="banner_id")Integer banner_id);*/
}
