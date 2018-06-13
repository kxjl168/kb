package com.kxjl.web.routeM.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.routeM.model.ProxyserverRoute;
import com.kxjl.web.routeM.service.ProxyserverRouteService;
import com.kxjl.web.system.action.base.BaseController;




@Controller
@RequestMapping(value = "/proxyserverroute")
public class ProxyserverRouteController extends BaseController {

	@Autowired
	ProxyserverRouteService proxyserverService;

	@RequestMapping(value = "/banner/bannerList")
	public ModelAndView GroupList() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/banner/banner");

		return view;
	}

	/**
	 * 页面-获取banner列表
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
		// String proxyserverid = request.getParameter("proxyserverid");

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

			String proxyserverid = jsonIN.optString("id");
			String ip = jsonIN.optString("ip");
			String city = jsonIN.optString("city");
			
		

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");

			ProxyserverRoute query = new ProxyserverRoute();
			query.setProxyserver_ip(ip);
			query.setCity(city);
			
			query.setPage(curPage);
			query.setPageCount(pageCount);

			query.setProxyserver_id(proxyserverid);
		

			List<ProxyserverRoute> infos = proxyserverService.getProxyserverRoutePageList(query);
			int total = proxyserverService.getProxyserverRoutePageListCount(query);

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

}
