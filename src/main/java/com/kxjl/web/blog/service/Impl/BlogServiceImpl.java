package com.kxjl.web.blog.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.system.dao.SystemParamsDao;
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
	 * 文章tag总数列表
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-29
	 */
	public List<Blog> getBlogTags() {
		List<Blog> rst = new ArrayList<Blog>();
		try {

			List<Blog> bgs = blogDao.getBlogTags();
			if (bgs != null && bgs.size() != 0) {
				Blog b = bgs.get(0);
				String[] tags = b.getTags().split(",");
				HashMap<String, Integer> ts = new HashMap<String, Integer>();

				for (String string : tags) {
					ts.put(string,
							ts.containsKey(string) ? (ts.get(string) + 1) : 1);
				}
				

				for (String string : ts.keySet()) {
					if (rst.size() > 30)
						break;
					else {
						Blog bg = new Blog();
						bg.setTags(string);
						rst.add(bg);

					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rst;
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
		return blogDao.getBlogMonthGroup();
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
		return blogDao.getBlogTypeGroups();
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

			// 访问数+1
			Blog b = blogs.get(0);
			b.setView_nums(b.getView_nums() + 1);
			updateBlog(b);

			query.setRecordid(blogs.get(0).getRecordid());
			rsts = blogDao.getBlogDetailPageList(query);
		}

		return rsts;

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
