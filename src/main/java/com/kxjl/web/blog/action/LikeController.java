package com.kxjl.web.blog.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.autodata.dao.LikeInfoMapper;
import com.kxjl.web.autodata.model.LikeInfo;
import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.blog.service.BlogService;
import com.kxjl.web.system.action.SysBaseInfoController;
import com.kxjl.web.system.action.base.BaseController;

@Controller
@RequestMapping(value = "/bloglike")
public class LikeController extends BaseController {

	@Autowired
	LikeInfoMapper likemaper;
	@Autowired
	BlogService blogService;


	/**
	 * 点赞
	 * 
	 * @param request
	 * @param response
	 * @author zj
	 * @date 2018年8月31日
	 */
	@RequestMapping(value = "/good")
	public void good(HttpServletRequest request, LikeInfo like, HttpServletResponse response) {
		// String data = request.getParameter("data");

		// JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();
		List<Blog> detail = new ArrayList<Blog>();
		String rst = "";
		try {

			LikeInfo tmp = likemaper.selectByCookie(like);
			if (tmp != null) {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "您已经点赞过了!要不打赏一下?");
				jsonOut.put("total", 0);
			} else {

				like.setIp(request.getRemoteAddr());
				likemaper.insertSelective(like);

				int total = likemaper.getTotalLikeNum(like.getImei());

				jsonOut.put("ResponseCode", "200");
				jsonOut.put("ResponseMsg", "");
				jsonOut.put("total", total);
			}

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
	 * 点赞
	 * 
	 * @param request
	 * @param response
	 * @author zj
	 * @date 2018年8月31日
	 */
	@RequestMapping(value = "/goodnum")
	public void goodnum(HttpServletRequest request, HttpServletResponse response) {
		// String data = request.getParameter("data");

		// JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();
		List<Blog> detail = new ArrayList<Blog>();
		String rst = "";
		try {

			// jsonIN = new JSONObject(data);

			String imei = parseStringParam(request, "i");

			int total = likemaper.getTotalLikeNum(imei);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", total);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");
				jsonOut.put("total", 0);

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}

}