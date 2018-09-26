
package com.kxjl.web.blog.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.html.FileProcessor;
import com.kxjl.tool.utils.DateUtil;
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.stastic.model.ActionLog.StasticTypeOne;
import com.kxjl.web.stastic.service.StasticService;
//import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;
import com.kxjl.web.blog.action.Kdata.DataType;
import com.kxjl.web.blog.action.Kdata.Enable;
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
	@RequestMapping(value = "/sabout")
	public void sabout(HttpServletRequest request, HttpServletResponse response) {
		// String data = request.getParameter("data");
		saveStaticInfo(request, StasticTypeOne.AboutPage.toString(), "","");

	}

	
	
	
	/**
	 * 关联文章 根据tag获取
	 * @param request
	 * @param response
	 * @author zj
	 * @date 2018年8月30日
	 */
	@RequestMapping(value = "/getRelatedList")
	public void getRelatedList(HttpServletRequest request, HttpServletResponse response) {
		// String data = request.getParameter("data");

		// JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();
		List<Blog> detail = new ArrayList<Blog>();
		String rst = "";
		try {

			// jsonIN = new JSONObject(data);

			String imei = parseStringParam(request, "i");
		
			
			
			Blog query = new Blog();
			query.setImei(imei);

			// cur ,next, pre
			Blog data = blogService.getBlogInfoById(query);
			//data.getTagStrs()
			
			Page<Object> pg=PageHelper.startPage(1, 10);
			detail=blogService.getRelatedBlogs(data);
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
	 * 获取当前详细及前后关联的两个
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-26
	 */
	@RequestMapping(value = "/getDetailList")
	public void getDetailList(HttpServletRequest request, HttpServletResponse response) {
		// String data = request.getParameter("data");

		// JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();
		List<Blog> detail = new ArrayList<Blog>();
		String rst = "";
		try {

			// jsonIN = new JSONObject(data);

			String imei = parseStringParam(request, "i");
			if (imei.equals("null") || imei.equals("")) {
				String url = request.getHeader("Referer");
				try {
					imei = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
				} catch (Exception e) {
					// TODO: handle exception
				}

				// http://127.0.0.1:8080/kb/public/detail/i/344f5834-fe93-49e8-835d-b07e1a1def96.html
			}

			Blog query = new Blog();
			query.setImei(imei);

			// cur ,next, pre
			detail = blogService.getBlogDetailPageList(query);
			
			//计数
			SysUserBean user = (SysUserBean) request.getSession().getAttribute(Constant.SESSION_USER);
			if (user.getUtype() != UserType.Root && user.getUtype() != UserType.Admin) {
				blogService.updateBlogReadTime(query);
			}

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

//		saveStaticInfo(request, StasticTypeOne.DetailPage.toString(), detail.get(0).getBlog_type_name(),
//				detail.get(0).getImei());

		Kdata.getInstance().cleanrBLogList("");

	}

	/**
	 * 后台详情
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-26
	 */
	@RequestMapping(value = "/getDetailInfo")
	public void getDetailInfo(HttpServletRequest request, HttpServletResponse response) {
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
			Blog detail = blogService.getBlogInfoById(query);

			Gson gs = new Gson();
			String jsStr = gs.toJson(detail);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", 1);
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
	 * 是否可见类型
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2018-1-18
	 */
	@RequestMapping(value = "/getEnableList")
	public void getKdataList(HttpServletRequest request, HttpServletResponse response) {

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			jsonIN = new JSONObject(data);

			List<String> types = new ArrayList<String>();
			for (Enable item : Kdata.Enable.values()) {
				Kdata.Enable ci = item;

				types.add(ci.toString());
			}

			Gson gs = new Gson();
			String jsStr = gs.toJson(types);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", types.size());
			jsonOut.put("datalist", jsStr);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");

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
	 * @param curPage     当前页
	 * @param pageCount   每页多少条
	 * @return
	 */
	@RequestMapping(value = "/getInfoList")
	public void getInfoList(HttpServletRequest request, HttpServletResponse response) {
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
			String blog_type = jsonIN.optString("blog_type");
			String blog_tag = jsonIN.optString("blog_tag");
			String month = jsonIN.optString("month");

			String show = jsonIN.optString("show");

			// 页面查询，为空，则默认查可见的
			if (show.equals(""))
				show = Kdata.Enable.Enable.value;

			// 后台管理传ni -1,查询全部
			if (show.equals(Kdata.Enable.NIL.value))
				show = "";

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");

			if (curPage == 0)
				curPage = 1;

			String key = "blog_getInfoList" + "_" + month + "_" + blog_type + "_" + blog_tag + "_" + pageCount + "_"
					+ curPage + "_" + blog_title + show;
			List<Blog> infos = Kdata.getInstance().getBlogList(key);

			Blog query = new Blog();
			query.setPage(curPage);
			query.setPageCount(pageCount);

			query.setTags(blog_tag);
			query.setShowflag(show);

			// query.setIp(ip);
			// query.setCity(city);
			query.setTitle(blog_title);// (id);
			query.setBlog_type(blog_type);// (blog_name);
			query.setMonth(month);

			Integer maxshownum = ConfigReader.getInstance().getIntProperty("maxshownum", 1000);

			if (infos == null || infos.size() == 0) {

				infos = blogService.getBlogPageList(query);
				String prepath = getImgHttpOutPath();
				for (Blog blog : infos) {
					blog.setBlog_type_url(prepath + blog.getBlog_type_url());

					// 剔除文章中的图片img内容，截取长度
					Pattern p = Pattern.compile("%3Cimg.*?%3E"); // %3Cimg %3E
																	// .*?最小匹配
																	// .*最大匹配
					Matcher ms = p.matcher(blog.getContent());

					String c = blog.getContent();
					// 截取超出部分、减少页面返回数据
					while (ms.find()) {

						String img = ms.group();
						String t = JEscape.unescape(img);
						// System.out.println(t);
						JEscape.unescape(blog.getContent());
						c = c.replace(img, "");

					}
					c = JEscape.unescape(c);

					if (c.length() > maxshownum) {
						try {
							String rc = c.substring(0, maxshownum);
							// System.out.println(rc);

							// jsoup 自动补全标签
							Document jd = Jsoup.parseBodyFragment(rc);

							c = jd.body().html();

						} catch (Exception e) {
							// TODO: handle exception
						}
					}

					blog.setContent(c);

				}
				Kdata.getInstance().SavedBlogList(key, infos);

			}

			int total = blogService.getBlogPageListCount(query);

			Gson gs = new Gson();
			String jsStr = gs.toJson(infos);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", total);
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

	@RequestMapping(value = "/getTgList")
	public void getTgList(HttpServletRequest request, HttpServletResponse response) {

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			Blog query = new Blog();
			query.setPage(1);
			query.setPageCount(10000);

		
			List<Blog> infos=blogService.getBlogTags();

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
	 * @param curPage     当前页
	 * @param pageCount   每页多少条
	 * @return
	 */
	@RequestMapping(value = "/getHList")
	public void getHList(HttpServletRequest request, HttpServletResponse response) {

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
	public void getTpList(HttpServletRequest request, HttpServletResponse response) {

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			Blog query = new Blog();
			query.setPage(1);
			query.setPageCount(10000);

			String key = "getTpList";
			List<Blog> infos =blogService.getBlogTypeGroups();


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
	public void addOrUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject jsonOut = new JSONObject();
		String data = request.getParameter("data");
		JSONObject jsonIN;

		try {

			jsonIN = new JSONObject(data);

			int recordid = jsonIN.optInt("recordid");
			String title = jsonIN.optString("title");
			String blog_type = jsonIN.optString("blog_type");

			String content = jsonIN.optString("context");

			String tags = jsonIN.optString("tags");
			String show = jsonIN.optString("show");

			Blog blog = new Blog();
			blog.setRecordid(recordid);// (accountid);

			if (recordid != 0)
				blog = blogService.getBlogInfoById(blog);

			blog.setTitle(title);// (pass);
			blog.setTags(tags);
			blog.setShowflag(show);

			blog.setBlog_type(blog_type);// (blog_name);

			// 过滤img 增加 class="img-responsive"

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
				blog.setUpdate_date(time);
				rst = blogService.updateBlog(blog);

			} else {
				/*
				 * if (blog != null) blog.setCreator(blog.getName());
				 */
				blog.setCreate_date(time);

				rst = blogService.addBlog(blog);
			}

			if (rst > 0) {

				// 本地缓存html文件删除
				String localPath = ConfigReader.getInstance().getProperty("LOCAL_HTML_PATH");
				String localFilePath = localPath + blog.getShowdate() + "/";

				String htmlName = imei + ".html";
				FileProcessor.deleteFile(localFilePath + htmlName);

				Kdata.getInstance().cleanrBLogList("");

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
	public @ResponseBody Map delBanner(HttpServletRequest request) {
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

				Kdata.getInstance().cleanrBLogList("");
			} else {
				res.put("ResponseCode", "201");
				res.put("ResponseMsg", "删除失败");
			}
		} catch (Exception e) {

		}

		return res;
	}

}
