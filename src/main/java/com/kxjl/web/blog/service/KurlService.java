package com.kxjl.web.blog.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kxjl.web.blog.model.Kurl;




public interface KurlService {
	
	
	/**
	 * 链接select2 group
	 * @param query
	 * @return
	 * @author zj
	 * @date 2019年8月30日
	 */
	public List<String> getUrlTreeSelectSecond(Kurl query) ;
	
	/**
	 * 链接分类数据
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年10月15日
	 */
	public 	Map<String, List<Kurl>>  getKurlItemPageList(Kurl query) ;
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Kurl> getKurlPageList(Kurl query);

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getKurlPageListCount(Kurl query);
	
	/**
	 * 添加Kurl
	 * @param Kurl
	 * @return
	 */
	public int addKurl(Kurl Kurl);
	
	/**
	 * 删除Kurl
	 * @param id
	 * @return
	 */
	public int deleteKurl(@Param(value="id")Integer id);

	/**
	 * 更新Kurl
	 * @param Kurl
	 * @return
	 */
	public int updateKurl(Kurl Kurl);
	
	
	/**
	 * 通过所有blog
	 * 
	 * @author:kxjl
	 * @date 2020年10月29日
	 */
	public void passallBlogKurl();
	
	/**
	 * 根据ID获取Kurl信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public Kurl getKurlInfoById(Kurl query);
	
}
