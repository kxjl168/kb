package com.kxjl.web.blog.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.xslf.model.geom.Guide;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.stastic.model.ActionLog.StasticTypeOne;
import com.kxjl.web.stastic.service.StasticService;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.blog.service.BlogService;

@Controller
@RequestMapping(value = "/blog")
public class BlogController extends BaseController {

	@Autowired
	BlogService blogService;
	

	@RequestMapping(value = "/")
	public ModelAndView GroupList() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/blog/blog");

		return view;
	}

	/**
	 * 获取当前详细及前后关联的两个
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-26
	 */
	@RequestMapping(value = "/getDetailList")
	public void getDetailList(HttpServletRequest request,
			HttpServletResponse response) {
		String data = request.getParameter("data");

		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			jsonIN = new JSONObject(data);

			String imei = jsonIN.optString("i");
			Blog query = new Blog();
			query.setImei(imei);

			// cur ,next, pre
			List<Blog> detail = blogService.getBlogDetailPageList(query);

			String prepath = getImgHttpOutPath();
			for (Blog blog : detail) {
				blog.setBlog_type_url(prepath + blog.getBlog_type_url());
			}
			
			Gson gs = new Gson();
			String jsStr = gs.toJson(detail);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", detail.size());
			jsonOut.put("datalist", jsStr);
			
			saveStaticInfo(request,StasticTypeOne.DetailPage.toString(),detail.get(0).getBlog_type_name());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");
				jsonOut.put("total", 0);
				jsonOut.put("datalist", "");
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);
		
		
	}

	/**
	 * 页面-获取blog列表
	 * 
	 * @param clientType
	 * @param packageName
	 * @param curPage
	 *            当前页
	 * @param pageCount
	 *            每页多少条
	 * @return
	 */
	@RequestMapping(value = "/getInfoList")
	public void getInfoList(HttpServletRequest request,
			HttpServletResponse response) {
		// String blogid = request.getParameter("blogid");

		/*
		 * int pageCount = Integer.parseInt(request.getParameter("rows"));//
		 * request.getParameter("pageCount"); int curPage =
		 * Integer.parseInt(request.getParameter("page"));
		 */

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			jsonIN = new JSONObject(data);

			String blog_title = jsonIN.optString("blog_title");
			int blog_type = jsonIN.optInt("blog_type");
			String blog_tag = jsonIN.optString("blog_tag");
			String month = jsonIN.optString("month");

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");

			Blog query = new Blog();
			query.setPage(curPage);
			query.setPageCount(pageCount);
			
			query.setTags(blog_tag);

			// query.setIp(ip);
			// query.setCity(city);
			query.setTitle(blog_title);// (id);
			query.setBlog_type(blog_type);// (blog_name);
			query.setMonth(month);

			List<Blog> infos = blogService.getBlogPageList(query);
			int total = blogService.getBlogPageListCount(query);

			String prepath = getImgHttpOutPath();
			for (Blog blog : infos) {
				blog.setBlog_type_url(prepath + blog.getBlog_type_url());
			}

			Gson gs = new Gson();
			String jsStr = gs.toJson(infos);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", total);
			jsonOut.put("datalist", jsStr);
			
			saveStaticInfo(request,StasticTypeOne.HomePage.toString(),"index");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");
				jsonOut.put("total", 0);
				jsonOut.put("datalist", "");
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}
	
	
	
	@RequestMapping(value = "/getTgList")
	public void getTgList(HttpServletRequest request,
			HttpServletResponse response) {
	

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

		

			Blog query = new Blog();
			query.setPage(1);
			query.setPageCount(10000);


			List<Blog> infos = blogService.getBlogTags();
			


			Gson gs = new Gson();
			String jsStr = gs.toJson(infos);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", infos.size());
			jsonOut.put("datalist", jsStr);
			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");
				jsonOut.put("total", 0);
				jsonOut.put("datalist", "");
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}
	
	/**
	 * 页面-获取month列表
	 * 
	 * @param clientType
	 * @param packageName
	 * @param curPage
	 *            当前页
	 * @param pageCount
	 *            每页多少条
	 * @return
	 */
	@RequestMapping(value = "/getHList")
	public void getHList(HttpServletRequest request,
			HttpServletResponse response) {
	

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

		

			Blog query = new Blog();
			query.setPage(1);
			query.setPageCount(10000);


			List<Blog> infos = blogService.getBlogMonthGroup();
			


			Gson gs = new Gson();
			String jsStr = gs.toJson(infos);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", infos.size());
			jsonOut.put("datalist", jsStr);
			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");
				jsonOut.put("total", 0);
				jsonOut.put("datalist", "");
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}
	
	/**
	 * 分类列表
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-29
	 */
	@RequestMapping(value = "/getTpList")
	public void getTpList(HttpServletRequest request,
			HttpServletResponse response) {
	

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

		

			Blog query = new Blog();
			query.setPage(1);
			query.setPageCount(10000);


			List<Blog> infos = blogService.getBlogTypeGroups();
			String prepath = getImgHttpOutPath();
			for (Blog blog : infos) {
				blog.setBlog_type_url(prepath + blog.getBlog_type_url());
			}


			Gson gs = new Gson();
			String jsStr = gs.toJson(infos);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", infos.size());
			jsonOut.put("datalist", jsStr);
			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");
				jsonOut.put("total", 0);
				jsonOut.put("datalist", "");
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}

	/**
	 * 添加或者更新blog信息
	 * 
	 * @param request
	 * @param response
	 * @date 2016-6-22
	 * @author zj
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JSONObject jsonOut = new JSONObject();
		String data = request.getParameter("data");
		JSONObject jsonIN;

		try {

			jsonIN = new JSONObject(data);

			int recordid = jsonIN.optInt("recordid");
			String title = jsonIN.optString("title");
			int blog_type = jsonIN.optInt("blog_type");

			String content = jsonIN.optString("context");

			String tags = jsonIN.optString("tags");

			Blog blog = new Blog();
			blog.setRecordid(recordid);// (accountid);

			if (recordid != 0)
				blog = blogService.getBlogInfoById(blog);

			blog.setTitle(title);// (pass);
			blog.setTags(tags);

			blog.setBlog_type(blog_type);// (blog_name);
			blog.setContent(content);// (desc);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());

			String imei = blog.getImei();
			if (imei == null || imei.equals("")) {
				imei = UUID.randomUUID().toString();
				blog.setImei(imei);
				blog.setUpdate_date(time);
			}

			int rst = -1;

			if (recordid != 0) {
				rst = blogService.updateBlog(blog);

			} else {
				/*
				 * if (blog != null) blog.setCreator(blog.getName());
				 */
				blog.setCreate_date(time);

				rst = blogService.addBlog(blog);
			}

			if (rst > 0) {
				jsonOut.put("ResponseCode", 200);
				jsonOut.put("ResponseMsg", "OK");
			} else {
				jsonOut.put("ResponseCode", 201);
				jsonOut.put("ResponseMsg", "FAILED");
			}

		} catch (Exception e2) {
			System.out.println(e2.getMessage());
			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "FAILED");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}

	/**
	 * 删除blog
	 * 
	 * @param request
	 * @return
	 * @date 2016-8-22
	 */
	@RequestMapping(value = "/del")
	public @ResponseBody
	Map delBanner(HttpServletRequest request) {
		Map res = new HashMap();
		res.put("ResponseCode", "201");
		res.put("ResponseMsg", "删除失败");
		String data = request.getParameter("data");
		JSONObject jsonIN;

		try {

			jsonIN = new JSONObject(data);

			int recordid = jsonIN.optInt("recordid");

			if (blogService.deleteBlog(recordid) > 0) {
				res.put("ResponseCode", "200");
				res.put("ResponseMsg", "删除成功");
			} else {
				res.put("ResponseCode", "201");
				res.put("ResponseMsg", "删除失败");
			}
		} catch (Exception e) {

		}

		return res;
	}

}
