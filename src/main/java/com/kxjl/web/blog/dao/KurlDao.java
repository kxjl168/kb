package com.kxjl.web.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kxjl.web.blog.model.Kurl;





public interface KurlDao {

	
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Kurl> getKurlPageList(Kurl query);

	
	/**
	 * 通过所有blog
	 * 
	 * @author:kxjl
	 * @date 2020年10月29日
	 */
	public void passallBlogKurl();
	
	
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
	 * 隐藏，设置标识
	 * @param id
	 * @return
	 * @author zj
	 * @date 2018年12月28日
	 */
	public int hideKurl(@Param(value="id")Integer id);

	
	
	/**
	 * 更新Kurl
	 * @param Kurl
	 * @return
	 */
	public int updateKurl(Kurl Kurl);
	
	
	
	/**
	 * 根据ID获取Kurl信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public Kurl getKurlInfoById(Kurl blog);
}
