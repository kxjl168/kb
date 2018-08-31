package com.kxjl.web.translog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.kxjl.web.translog.model.Spiderlog;

public interface SpiderlogDao {

	
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
	
	
	
	/**//**
	 * 删除Spiderlog
	 * @param id
	 * @return
	 *//*
	public int deleteSpiderlog(@Param(value="banner_id")Integer id);

	*//**
	 * 更新Spiderlog
	 * @param Spiderlog
	 * @return
	 *//*
	public int updateSpiderlog(Spiderlog Spiderlog);
	
	*//**
	 *  获取Spiderlog列表
	 * @return
	 *//*
	public List<Spiderlog> getSpiderlogList();



	*//**
	 * 根据ID获取Spiderlog信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 *//*
	public Spiderlog getSpiderlogInfoById(@Param(value="banner_id")Integer banner_id);*/
}
