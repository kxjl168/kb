package com.kxjl.web.blog.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;




import com.kxjl.web.blog.model.Blog;


public interface BlogService {
	
	public void updateBlogSpiderTime(Blog query) ;
	/**
	 * 文章tag总数列表
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-29
	 */
	public List<Blog> getBlogTags();
	
	/**
	 * 文章月分类总数列表
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-29
	 */
	public List<Blog> getBlogMonthGroup();
	
	/**
	 * 文章分类总数列表-新
	 * 包括父级分类及总数
	 * @return
	 * @author zj
	 * @date 2019年3月9日
	 */
	public List<Blog> getBlogTypeGroupsNew();
	
	/**
	 * 文章分类总数列表
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-29
	 */
	public List<Blog> getBlogTypeGroups();
	
	/**
	 * 获取文章及前后文章
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年8月4日
	 */
	public List<Blog> getBlogDetailPageList(Blog query);
	
	
	/**
	 * 计数加1
	 * @param query
	 */
	public void updateBlogReadTime(Blog query) ;
	
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Blog> getBlogPageList(Blog query);

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getBlogPageListCount(Blog query);
	
	/**
	 * 添加Blog
	 * @param Blog
	 * @return
	 */
	public int addBlog(Blog Blog);
	
	/**
	 * 删除Blog
	 * @param id
	 * @return
	 */
	public int deleteBlog(@Param(value="id")Integer id);

	/**
	 * 更新Blog
	 * @param Blog
	 * @return
	 */
	public int updateBlog(Blog Blog);
	
	

	
	/**
	 * 根据ID获取Blog信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public Blog getBlogInfoById(Blog query);
	
	/**
	 * 关联文章
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年8月30日
	 */
	public List<Blog> getRelatedBlogs(Blog query);
	
}
