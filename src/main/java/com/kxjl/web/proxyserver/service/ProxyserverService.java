package com.kxjl.web.proxyserver.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;



import com.kxjl.web.proxyserver.model.Proxyserver;



public interface ProxyserverService {

	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Proxyserver> getProxyserverPageList(Proxyserver query);

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getProxyserverPageListCount(Proxyserver query);
	
	/*
	*//**
	 * 添加Proxyserver
	 * @param Proxyserver
	 * @return
	 *//*
	public int addProxyserver(Proxyserver Proxyserver);
	
	*//**
	 * 删除Proxyserver
	 * @param id
	 * @return
	 *//*
	public int deleteProxyserver(@Param(value="banner_id")Integer id);

	*//**
	 * 更新Proxyserver
	 * @param Proxyserver
	 * @return
	 *//*
	public int updateProxyserver(Proxyserver Proxyserver);
	
	*//**
	 *  获取Proxyserver列表
	 * @return
	 *//*
	public List<Proxyserver> getProxyserverList();



	/**
	 * 根据ID获取Proxyserver信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public Proxyserver getProxyserverInfoById(Proxyserver Proxyserver);
	
}
