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
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.model.SysUserBean;

@Controller
@RequestMapping(value = "/kurl")
public class KurlController extends BaseController {

	@Autowired
	KurlService kurlService;

	/*
	 * @RequestMapping(value = "/") public ModelAndView GroupList() {
	 * ModelAndView view = new ModelAndView(); view.setViewName("/blog/blog");
	 * 
	 * return view; }
	 */

	@RequestMapping(value = "/getShowInfoList")
	public void getShowInfoList(HttpServletRequest request,
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

			String url_type = jsonIN.optString("name");
			// String dict_type = "url_type";

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");

			Kurl query = new Kurl();
			query.setPage(curPage);
			query.setPageCount(100);

			// query.setIp(ip);
			// query.setCity(city);
			// query.setUrl_type(dict_type);// (url_title);// (id);
			query.setUrl_name(url_type);// (url_name);

			List<Kurl> infos = kurlService.getKurlPageList(query);

			Map<String, List<Kurl>> datas = new HashMap<String, List<Kurl>>();
			String prepath = getImgHttpOutPath();
			for (int i = 0; i < infos.size(); i++) {

				Kurl u = infos.get(i);
				u.setVal2(prepath);
				List<Kurl> item = new ArrayList<Kurl>();

				if (datas.containsKey(u.getUrl_type())) {
					item = datas.get(u.getUrl_type());

				}
				item.add(u);
				datas.put(u.getUrl_type(), item);

			}

			Gson gs = new Gson();

			JSONArray ja = new JSONArray();
			for (List<Kurl> item : datas.values()) {
				JSONObject jo=new JSONObject();
				jo.put("name", item.get(0).getUrl_type());
				jo.put("val", gs.toJson(item));
				ja.put(jo);
			}

			int total = kurlService.getKurlPageListCount(query);

		//	String jsStr = gs.toJson(infos);

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

			String url_type = jsonIN.optString("name");
			// String dict_type = "url_type";

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");

			Kurl query = new Kurl();
			query.setPage(curPage);
			query.setPageCount(pageCount);

			// query.setIp(ip);
			// query.setCity(city);
			// query.setUrl_type(dict_type);// (url_title);// (id);
			query.setUrl_name(url_type);// (url_name);

			List<Kurl> infos = kurlService.getKurlPageList(query);
			int total = kurlService.getKurlPageListCount(query);
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
			String dic_name = jsonIN.optString("name");
			String dic_key = jsonIN.optString("key");
			String sort = jsonIN.optString("sort");
			String val1 = jsonIN.optString("val1");
			String desc_info = jsonIN.optString("desc_info");
			String utype = jsonIN.optString("utype");

			Kurl blog = new Kurl();
			blog.setId(recordid);// (accountid);

			if (recordid == 0) {
				Kurl query = new Kurl();
				// query.setUrl_type(utype);// (accountid);
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
			}

			if (recordid != 0) {
				blog = kurlService.getKurlInfoById(blog);
			}

			blog.setUrl_val(dic_key);// (title);// (pass);
			blog.setUrl_name(dic_name);// (tags);
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

}
