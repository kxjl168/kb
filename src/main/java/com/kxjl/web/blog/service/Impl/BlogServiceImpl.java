package com.kxjl.web.blog.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.blog.action.Kdata;
import com.kxjl.web.blog.dao.BlogDao;
import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.blog.service.BlogService;

@Service(value = "BlogService")
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogDao blogDao;

	@Autowired
	SystemParamsDao sysDao;

	/**
	 * 关联文章
	 * 
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年8月30日
	 */
	public List<Blog> getRelatedBlogs(Blog query) {
		return blogDao.getRelatedBlogs(query);
	}

	/**
	 * 文章tag总数列表
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-29
	 */
	public List<Blog> getBlogTags() {

		String key = "getTgList";
		List<Blog> infos = Kdata.getInstance().getBlogList(key);

		if (infos == null || infos.size() == 0) {

			List<Blog> rst = new ArrayList<Blog>();
			try {

				List<Blog> bgs = blogDao.getBlogTags();
				if (bgs != null && bgs.size() != 0) {
					Blog b = bgs.get(0);
					String[] tags = b.getTags().split(",");
					HashMap<String, Integer> ts = new HashMap<String, Integer>();

					for (String string : tags) {
						ts.put(string, ts.containsKey(string) ? (ts.get(string) + 1) : 1);
					}

					int max = ConfigReader.getInstance().getIntProperty("MaxTags", 100);

					for (String string : ts.keySet()) {
						if (rst.size() > max)
							break;
						else {
							Blog bg = new Blog();
							bg.setTags(string);
							bg.setPage(ts.get(string));
							rst.add(bg);

						}
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			infos = rst;

			Kdata.getInstance().SavedBlogList(key, infos);
		}

		return infos;
	}

	/**
	 * 文章月分类总数列表
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-29
	 */
	public List<Blog> getBlogMonthGroup() {
		
		
		String key = "getHList";
		List<Blog> infos = Kdata.getInstance().getBlogList(key);

		if (infos == null || infos.size() == 0) {
			infos = blogDao.getBlogMonthGroup();

			Kdata.getInstance().SavedBlogList(key, infos);

		}
		
		return infos;
	}

	/**
	 * 文章分类总数列表
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-29
	 */
	public List<Blog> getBlogTypeGroups() {
		
		String HTTP_PATH = ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
		String key = "getTpList";
		List<Blog> infos = Kdata.getInstance().getBlogList(key);

		if (infos == null || infos.size() == 0) {
			infos = blogDao.getBlogTypeGroups();
			String prepath =HTTP_PATH;
			for (Blog blog : infos) {
				blog.setBlog_type_url(prepath + blog.getBlog_type_url());
			}

			Kdata.getInstance().SavedBlogList(key, infos);
		}
		
		return infos;
	}

	/**
	 * 获取前后相关联的文章
	 */
	public List<Blog> getBlogDetailPageList(Blog query) {
		List<Blog> rsts = null;
		query.setPage(1);
		query.setPageCount(10);
		List<Blog> blogs = blogDao.getBlogPageList(query);

		if (blogs != null && blogs.size() != 0) {

			/*
			 * // 访问数+1 Blog b = blogs.get(0); b.setView_nums(b.getView_nums() + 1);
			 * updateBlog(b);
			 */

			query.setRecordid(blogs.get(0).getRecordid());
			rsts = blogDao.getBlogDetailPageList(query);
		}

		return rsts;
	}

	/**
	 * 计数加+1
	 * 
	 * @param query
	 */
	public void updateBlogReadTime(Blog query) {
		List<Blog> blogs = blogDao.getBlogPageList(query);

		if (blogs != null && blogs.size() != 0) {

			// 访问数+1
			Blog b = blogs.get(0);
			b.setView_nums(b.getView_nums() + 1);
			updateBlog(b);
		}

	}
	

	/**
	 * 爬取计数加+1
	 * 
	 * @param query
	 */
	public void updateBlogSpiderTime(Blog query) {
		List<Blog> blogs = blogDao.getBlogPageList(query);

		if (blogs != null && blogs.size() != 0) {

			// 访问数+1
			Blog b = blogs.get(0);
			b.setSpider_nums(b.getSpider_nums() + 1);
			updateBlog(b);
		}

	}


	/**
	 * 分页获取banner列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public List<Blog> getBlogPageList(Blog query) {
		return blogDao.getBlogPageList(query);
	}

	/**
	 * 获取banner总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public int getBlogPageListCount(Blog query) {
		return blogDao.getBlogPageListCount(query);
	}

	@Override
	public int addBlog(Blog Blog) {
		return blogDao.addBlog(Blog);
	}

	@Override
	public int deleteBlog(@Param("id") Integer id) {
		return blogDao.deleteBlog(id);
	}

	@Override
	public int updateBlog(Blog Blog) {
		return blogDao.updateBlog(Blog);
	}

	/**
	 * 根据ID获取Blog信息
	 * 
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public Blog getBlogInfoById(Blog query) {
		return blogDao.getBlogInfoById(query);
	}

}
