package com.kxjl.web.blog.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.base.aopAspect.NoNeedAuthorization;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.IconUtil;
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.action.base.OutApiAuthorization;
import com.kxjl.web.system.action.base.OutApiAuthorization.UrlType;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;

import sun.util.logging.resources.logging;

@Controller
@RequestMapping(value = "/kurl")
public class KurlController extends BaseController {

	@Autowired
	KurlService kurlService;

	@Autowired
	IconUtil iconUtil;

	/*
	 * @RequestMapping(value = "/") public ModelAndView GroupList() { ModelAndView
	 * view = new ModelAndView(); view.setViewName("/blog/blog");
	 * 
	 * return view; }
	 */

	/**
	 * select2 group查询
	 * 
	 * @param request
	 * @param response
	 * @author zj
	 * @date 2019年8月30日
	 */
	@RequestMapping(value = "/getSelectGroupInfoList")
	public void getSelectGroupInfoList(HttpServletRequest request, HttpServletResponse response, Kurl query) {
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

			query.setPageCount(10000);
			query.setVal1("1");
			// 计数
			SysUserBean user = (SysUserBean) request.getSession().getAttribute(Constant.SESSION_USER);
			if (user == null || (user.getUtype() != UserType.Root && user.getUtype() != UserType.Admin)) {
				query.setIsshow("1");
			}

			String HTTP_PATH = ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
			String prepath = HTTP_PATH;

			query.setStart(0);
			List<String> datas = kurlService.getUrlTreeSelectSecond(query);

			Gson gs = new Gson();
			rst = gs.toJson(datas);

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
		// rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}

	/**
	 * 查询接口
	 * 
	 * @param request
	 * @param response
	 * @author zj
	 * @date 2019年8月30日
	 */
	@RequestMapping(value = "/getShowInfoList")
	public void getShowInfoList(HttpServletRequest request, HttpServletResponse response) {
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

			String url_type = jsonIN.optString("name");
			// String dict_type = "url_type";

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");

			Kurl query = new Kurl();
			query.setPage(curPage);
			query.setPageCount(10000);
			query.setVal1("1");

			// query.setIp(ip);
			// query.setCity(city);
			// query.setUrl_type(dict_type);// (url_title);// (id);
			query.setUrl_name(url_type);// (url_name);

			String HTTP_PATH = ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
			String prepath = HTTP_PATH;
			Map<String, List<Kurl>> datas = kurlService.getKurlItemPageList(query);

			Gson gs = new Gson();

			JSONArray ja = new JSONArray();
			int blogindex = 0;
			int i = 0;
			for (List<Kurl> item : datas.values()) {
				JSONObject jo = new JSONObject();
				jo.put("name", item.get(0).getUrl_type());
				jo.put("val", gs.toJson(item));
				ja.put(jo);

				if (item.get(0).getUrl_type().equals("BLOG"))
					blogindex = i;

				i++;
			}
			// blog放到第一位
			JSONObject blogDatas = (JSONObject) ja.get(blogindex);
			ja.remove(blogindex);
			ja.put(0, blogDatas);

			int total = kurlService.getKurlPageListCount(query);

			// String jsStr = gs.toJson(infos);

			jsonOut.put("val2", prepath);
			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", total);
			jsonOut.put("datalist", ja.toString());

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
	 * select2 选择分类 初始列表查询
	 * 
	 * @param request
	 * @param response
	 * @author zj
	 * @date 2019年1月15日
	 */
	@RequestMapping(value = "/getSelectInfoList")
	public void getSelectInfoList(HttpServletRequest request, HttpServletResponse response, Kurl query) {
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

			/*
			 * String url_type = jsonIN.optString("name"); // String dict_type = "url_type";
			 * 
			 * int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			 * int curPage = jsonIN.optInt("page");
			 * 
			 * Kurl query = new Kurl(); query.setPage(curPage); query.setPageCount(10000);
			 * query.setVal1("1");
			 */

			// query.setIp(ip);
			// query.setCity(city);
			// query.setUrl_type(dict_type);// (url_title);// (id);
			// query.setUrl_name(url_type);// (url_name);

			query.setPageCount(10000);
			query.setVal1("1");
			// 计数
			SysUserBean user = (SysUserBean) request.getSession().getAttribute(Constant.SESSION_USER);
			if (user == null || (user.getUtype() != UserType.Root && user.getUtype() != UserType.Admin)) {
				query.setIsshow("1");
			}

			String HTTP_PATH = ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
			String prepath = HTTP_PATH;

			query.setStart(0);
			Map<String, List<Kurl>> datas = kurlService.getKurlItemPageList(query);

			Gson gs = new Gson();

			JSONArray ja = new JSONArray();
			int i = 0;
			for (List<Kurl> item : datas.values()) {
				JSONObject jo = new JSONObject();
				jo.put("name", item.get(0).getUrl_type());
				jo.put("id", i);
				ja.put(jo);
				i++;
			}

			int total = kurlService.getKurlPageListCount(query);

			// String jsStr = gs.toJson(infos);

			jsonOut.put("val2", prepath);
			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", total);
			jsonOut.put("rows", ja.toString());

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
	 * 友情链接-前台缓存
	 * 
	 * @param request
	 * @param response
	 * @author zj
	 * @date 2018年5月21日
	 */
	@RequestMapping(value = "/getYQList")
	public void getYQList(HttpServletRequest request, HttpServletResponse response) {

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			// jsonIN = new JSONObject(data);

			// String url_type = jsonIN.optString("name");
			// String dict_type = "url_type";

			String show = parseStringParam(request, "show");

			int pageCount = 0;// jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = 100;// jsonIN.optInt("page");

			Kurl query = new Kurl();
			query.setPage(1);
			query.setPageCount(100);
			query.setVal1("2");
			query.setIsshow(show);

			// query.setIp(ip);
			// query.setCity(city);
			// query.setUrl_type(dict_type);// (url_title);// (id);
			// query.setUrl_name(url_type);// (url_name);

			String key = "getYQList";

			List<Kurl> infos = (List<Kurl>) Kdata.getInstance().getCommonList(key);

			if (infos == null || infos.size() == 0) {
				try {

					infos = kurlService.getKurlPageList(query);

					Kdata.getInstance().SavedCommonList(key, infos);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			int total = kurlService.getKurlPageListCount(query);
			// int total = infos.size();

			String prepath = getImgHttpOutPath();
			for (int i = 0; i < infos.size(); i++) {
				Kurl d = infos.get(i);
				d.setVal2(prepath);

			}

			Gson gs = new Gson();
			String jsStr = gs.toJson(infos);

			jsonOut.put("val2", prepath);
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

	/**
	 * 友情链接-后台不缓存
	 * 
	 * @param request
	 * @param response
	 * @author zj
	 * @date 2018年5月21日
	 */
	@RequestMapping(value = "/getYQListBack")
	public void getYQListBack(HttpServletRequest request, HttpServletResponse response) {

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			// jsonIN = new JSONObject(data);

			// String url_type = jsonIN.optString("name");
			// String dict_type = "url_type";

			String show = parseStringParam(request, "show");

			int pageCount = parseIntegerParam(request, "rows");// request.getParameter("pageCount");
			int curPage = parseIntegerParam(request, "page");
			Kurl query = new Kurl();
			query.setPage(curPage);
			query.setPageCount(pageCount);
			query.setVal1("2");
			query.setIsshow(show);

			// query.setIp(ip);
			// query.setCity(city);
			// query.setUrl_type(dict_type);// (url_title);// (id);
			// query.setUrl_name(url_type);// (url_name);

			String key = "getYQList";

			List<Kurl> infos = kurlService.getKurlPageList(query);

			int total = kurlService.getKurlPageListCount(query);
			// int total = infos.size();

			String prepath = getImgHttpOutPath();
			for (int i = 0; i < infos.size(); i++) {
				Kurl d = infos.get(i);
				d.setVal2(prepath);

			}

			Gson gs = new Gson();
			String jsStr = gs.toJson(infos);

			jsonOut.put("val2", prepath);
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

			String url_type = jsonIN.optString("name");
			// String dict_type = "url_type";

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");

			String show = jsonIN.optString("show");

			Kurl query = new Kurl();
			query.setPage(curPage);
			query.setPageCount(10000);
			query.setVal1("1");

			query.setIsshow("1");

			// query.setUrl_type(dict_type);// (url_title);// (id);
			query.setUrl_name(url_type);// (url_name);

			String HTTP_PATH = ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
			String prepath = HTTP_PATH;
			Map<String, List<Kurl>> datas = kurlService.getKurlItemPageList(query);

			Gson gs = new Gson();

			JSONArray ja = new JSONArray();
			int blogindex = 0;
			int i = 0;
			for (List<Kurl> item : datas.values()) {
				JSONObject jo = new JSONObject();
				jo.put("name", item.get(0).getUrl_type());
				jo.put("val", gs.toJson(item));
				ja.put(jo);

				if (item.get(0).getUrl_type().equals("BLOG"))
					blogindex = i;

				i++;
			}
			// blog放到第一位
			JSONObject blogDatas = (JSONObject) ja.get(blogindex);
			ja.remove(blogindex);
			ja.put(0, blogDatas);

			int total = kurlService.getKurlPageListCount(query);

			// String jsStr = gs.toJson(infos);

			jsonOut.put("val2", prepath);
			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", total);
			jsonOut.put("datalist", ja.toString());

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
	@OutApiAuthorization(uType = UrlType.NeedAdmin)
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject jsonOut = new JSONObject();
		String data = request.getParameter("data");
		JSONObject jsonIN;

		try {

			jsonIN = new JSONObject(data);

			int recordid = jsonIN.optInt("recordid");
			String dic_name = jsonIN.optString("name");
			String dic_key = jsonIN.optString("key");
			String sort = jsonIN.optString("sort");
			String val1 = jsonIN.optString("val1");
			String desc_info = jsonIN.optString("desc_info");
			String utype = jsonIN.optString("utype");

			String show = jsonIN.optString("isshow");
			String icon = jsonIN.optString("icon");

			Kurl blog = new Kurl();
			blog.setId(recordid);// (accountid);

			if (recordid == 0) {
				Kurl query = new Kurl();
				// query.setUrl_type(utype);// (accountid);
				query.setVal1(val1);
				query.setUrl_val(dic_key);
				query.setPage(1);
				query.setPageCount(10);

				int num = kurlService.getKurlPageListCount(query);
				if (num > 0) {
					// 一个type下的key是唯一的
					jsonOut.put("ResponseCode", 201);
					jsonOut.put("ResponseMsg", "已存在KEY为" + dic_key + "的数据！");
					JsonUtil.responseOutWithJson(response, jsonOut.toString());
					return;
				}

				// 自动获取图标
				if (icon == null || icon.equals("")) {

					int third = dic_key.indexOf("/", 8);// 第三个/
					String realsiteurl = dic_key;
					if (third > 0)
						realsiteurl = dic_key.substring(0, third);

					String iconurl = iconUtil.getAndUploadSiteIcon(realsiteurl);
					blog.setIcon(iconurl);
				}

			}

			if (recordid != 0) {
				blog = kurlService.getKurlInfoById(blog);
			}

			blog.setUrl_val(dic_key);// (title);// (pass);
			blog.setUrl_name(dic_name);// (tags);
			blog.setIcon(icon);
			blog.setIsshow(show);
			blog.setSort(sort);

			blog.setVal1(val1);
			blog.setDesc_info(desc_info);

			blog.setUrl_type(utype);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());

			int rst = -1;

			if (recordid != 0) {
				rst = kurlService.updateKurl(blog);

			} else {

				rst = kurlService.addKurl(blog);
			}

			if (rst > 0) {

				Kdata.getInstance().cleanrCommonList("");

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
	@OutApiAuthorization(uType = UrlType.NeedAdmin)
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

			if (kurlService.deleteKurl(recordid) > 0) {
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

	/**
	 * 获取链接icon
	 * 
	 * @param request
	 * @return
	 * @author zj
	 * @date 2020年1月20日
	 */
	@OutApiAuthorization(uType = UrlType.NeedAdmin)
	@RequestMapping(value = "/getIcon")
	public @ResponseBody Map getIcon(HttpServletRequest request) {
		Map res = new HashMap();
		res.put("ResponseCode", "201");
		res.put("ResponseMsg", "获取失败");
		String siteurl = parseStringParam(request, "url");
		JSONObject jsonIN;

		try {
			if (siteurl == null || !siteurl.startsWith("http")) {
				res.put("ResponseCode", "201");
				res.put("ResponseMsg", "获取失败,url为空");

			} else {

				// http://xxxx.com.cn/xxx

				int third = siteurl.indexOf("/", 8);// 第三个/ 不是根目录
				if (third > 0)
					siteurl = siteurl.substring(0, third);

				String url = iconUtil.getAndUploadSiteIcon(siteurl);

				if (url != null) {
					if (url.contains("fail")) {
						res.put("ResponseCode", "201");
						res.put("ResponseMsg", url);
					} else if (url.equals("")) {
						res.put("ResponseCode", "201");
						res.put("ResponseMsg", "未获取到图标:"+url);
					} else {
						res.put("ResponseCode", "200");
						res.put("ResponseMsg", url);
					}
				} else {
					res.put("ResponseCode", "201");
					res.put("ResponseMsg", "获取失败");
				}
			}
		} catch (Exception e) {

		}

		return res;
	}

}
