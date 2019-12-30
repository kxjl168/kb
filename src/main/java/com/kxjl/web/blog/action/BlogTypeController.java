package com.kxjl.web.blog.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.privilege.model.Permission;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.action.base.OutApiAuthorization;
import com.kxjl.web.system.action.base.OutApiAuthorization.UrlType;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.service.DictInfoService;

@Controller
@RequestMapping(value = "/blogtype")
public class BlogTypeController extends BaseController {

	@Autowired
	DictInfoService blogService;

	/*
	 * @RequestMapping(value = "/") public ModelAndView GroupList() { ModelAndView
	 * view = new ModelAndView(); view.setViewName("/blog/blog");
	 * 
	 * return view; }
	 */

	/**
	 * 构造用户树数据
	 * 
	 * @param orgname
	 *            用户组或者用户名称
	 * @return
	 * @date 2016-3-3
	 * @author zj
	 */
	@RequestMapping(value = { "/getTree" }, method = RequestMethod.POST)
	public @ResponseBody List<String> getMenuTree(HttpServletRequest request) {

		String dict_type = parseStringParam(request, "dict_type");

		if (dict_type == null || dict_type.equals(""))
			dict_type = DictInfo.blog_type_str;

		// 获取分类树
		return blogService.getDictTreeSecond(dict_type);

	}

	/**
	 * 父分类
	 * 
	 * @param item
	 * @param request
	 * @param response
	 * @return
	 * @author zj
	 * @date 2019年2月11日
	 */
	@RequestMapping("/listparent")
	@ResponseBody
	public String listparent(DictInfo item, HttpServletRequest request, HttpServletResponse response) {

		String dict_type = parseStringParam(request, "dict_type");

		if (dict_type == null || dict_type.equals(""))
			dict_type = DictInfo.blog_type_str;

		// 文章分类
		item.setDict_type(dict_type);

		List<DictInfo> rst = blogService.getDictInfoPageList(item);

		/*
		 * DictInfo kong=new DictInfo(); kong.setId(-1); kong.setDict_name("无");
		 * rst.add(kong);
		 */

		Gson gs = new Gson();
		JSONObject data = new JSONObject();

		try {
			JSONArray rows = new JSONArray(gs.toJson(rst));
			data.put("total", rst.size());
			data.put("rows", rows);
		} catch (Exception e) {
			// TODO: handle exception
		}

		String datastr = data.toString();
		return datastr;// responseData(request, response, datastr);

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

			String blog_type = jsonIN.optString("name");

			String dict_type = jsonIN.optString("dict_type");
			if (dict_type == null || dict_type.equals(""))
				dict_type = DictInfo.blog_type_str;

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");
			String parent_id = jsonIN.optString("parent_id");

			DictInfo query = new DictInfo();
			query.setPage(curPage);
			query.setPageCount(pageCount);
			query.setParent_id(parent_id);

			// query.setIp(ip);
			// query.setCity(city);
			query.setDict_type(dict_type);// (blog_title);// (id);
			query.setDict_name(blog_type);// (blog_name);

			List<DictInfo> infos = blogService.getDictInfoPageList(query);
			int total = blogService.getDictInfoPageListCount(query);
			String prepath = getImgHttpOutPath();
			for (int i = 0; i < infos.size(); i++) {
				DictInfo d = infos.get(i);
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
	@OutApiAuthorization(uType=UrlType.NeedAdmin)
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

			String enable = jsonIN.optString("enable");
			String parent_id = jsonIN.optString("parent_id");
			String dict_level = jsonIN.optString("dict_level");

			DictInfo blog = new DictInfo();
			blog.setId(recordid);// (accountid);

			String dict_type = parseStringParam(request, "dict_type");
			if (dict_type == null || dict_type.equals(""))
				dict_type = DictInfo.blog_type_str;
			
			
			if (recordid == 0) {
				DictInfo query = new DictInfo();
				query.setDict_type(dict_type);// (accountid);
				query.setDict_key(dic_key);
				query.setPage(1);
				query.setPageCount(10);
				int num = blogService.getDictInfoPageListCount(query);
				if (num > 0) {
					// 一个type下的key是唯一的
					jsonOut.put("ResponseCode", 201);
					jsonOut.put("ResponseMsg", "已存在KEY为" + dic_key + "的类型！");
					JsonUtil.responseOutWithJson(response, jsonOut.toString());
					return;
				}
			}

			if (recordid != 0) {
				blog = blogService.getDictInfoInfoById(blog);
			}

			
			
			
			
			blog.setDict_key(dic_key);// (title);// (pass);
			blog.setDict_name(dic_name);// (tags);
			blog.setSort(sort);

			blog.setVal1(val1);
			blog.setDesc_info(desc_info);
			blog.setEnable(enable);
			blog.setParent_id(parent_id);
			blog.setDict_level(dict_level);

			blog.setDict_type(dict_type);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());

			int rst = -1;

			if (recordid != 0) {
				rst = blogService.updateDictInfo(blog);

			} else {

				rst = blogService.addDictInfo(blog);
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
	public @ResponseBody Map delBanner(HttpServletRequest request) {
		Map res = new HashMap();
		res.put("ResponseCode", "201");
		res.put("ResponseMsg", "删除失败");
		String data = request.getParameter("data");
		JSONObject jsonIN;

		try {

			jsonIN = new JSONObject(data);

			int recordid = jsonIN.optInt("recordid");

			if (blogService.deleteDictInfo(recordid) > 0) {
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
