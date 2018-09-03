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
import org.json.JSONException;
import org.json.JSONObject;
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
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.model.SvrFileInfo;
import com.kxjl.web.system.service.DictInfoService;
import com.kxjl.web.system.service.SvrFileInfoService;

@Controller
@RequestMapping(value = "/filemanager")
public class FileManagerController extends BaseController {

	@Autowired
	DictInfoService blogService;

	@Autowired
	SvrFileInfoService svrFileInfoService;

	@RequestMapping(value = "/index")
	public ModelAndView GroupList() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/page/filemanager/main");

		return view;
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
	public void getInfoList(HttpServletRequest request, HttpServletResponse response, SvrFileInfo info) {

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			//jsonIN = new JSONObject(data);

		//	String blog_type = jsonIN.optString("name");
		//	String dict_type = "blog_type";

			int pageCount = parseIntegerParam(request, "rows");// request.getParameter("pageCount");
			int curPage = parseIntegerParam(request, "page");

			Page<Object> page = PageHelper.startPage(curPage, pageCount);

			List<SvrFileInfo> files = svrFileInfoService.getFileList(info);

			String prepath = getImgHttpOutPath();

			Gson gs = new Gson();
			String jsStr = gs.toJson(files);

			jsonOut.put("prepath", prepath);
			
			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", page.getTotal());
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
	 * 删除blog
	 * 
	 * @param request
	 * @return
	 * @date 2016-8-22
	 */
	@RequestMapping(value = "/del")
	public @ResponseBody Map delfile(HttpServletRequest request, SvrFileInfo info) {
		Map res = new HashMap();
		res.put("ResponseCode", "201");
		res.put("ResponseMsg", "删除失败");

		try {

			if (svrFileInfoService.DeleteFileInfo(info) > 0) {
				res.put("ResponseCode", "200");
				res.put("ResponseMsg", "删除成功");
			} else {
				res.put("ResponseCode", "201");
				res.put("ResponseMsg", "删除失败");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			res.put("ResponseCode", "201");
			res.put("ResponseMsg", "删除失败" + e.getMessage());
		}

		return res;
	}

}
