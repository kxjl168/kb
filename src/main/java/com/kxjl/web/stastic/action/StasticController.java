package com.kxjl.web.stastic.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kxjl.web.autodata.dao.VisitDataMapper;
import com.kxjl.web.autodata.model.VisitData;
import com.kxjl.web.stastic.model.ActionLog;
import com.kxjl.web.stastic.service.StasticService;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.utils.AESEncrypt;
import com.kxjl.tool.utils.DateUtil;
import com.kxjl.tool.utils.FileUtil;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.tool.utils.StringUtil;

import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.DictInfo;



/**
 * 统计接口
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = "/statistics")
public class StasticController extends BaseController {
	@Autowired
	private StasticService stasticService;
	
	@Autowired
	private VisitDataMapper visitDao;

/*	@Autowired
	private ConsultingAppealingService complaintService;

	@Autowired
	private AppInfoService appInfoService;
*/
	private Logger logger = Logger.getLogger(StasticController.class);

	/**
	 * 客户端接口-上报点击操作
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/click")
	public void click(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = handleRequestNoKey(request);
		JSONObject jsonOut = new JSONObject();

		JSONObject params_json;
		try {
			params_json = new JSONObject(data);
			jsonOut = doClick(params_json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseDataNoKey(request, response, jsonOut.toString());
	}

	/**
	 * 操作
	 * 
	 * @param params_json
	 * @return
	 * @date 2016-8-10
	 * @author zj
	 */
	private JSONObject doClick(JSONObject params_json) {
		JSONObject jsonOut = new JSONObject();
		try {

			String Token = params_json.optString("Token");
			String LoginId = params_json.optString("LoginId");
			String DeviceId = params_json.optString("DeviceId");
			String MainType = params_json.optString("MainType");
			String SecondType = params_json.optString("SecondType");

			Map<String, Object> validateRst = validateToken(Token, LoginId,
					DeviceId);
			String rst_msg = (String) validateRst.get("ResponseMsg");

			if (!String.valueOf(validateRst.get("ResponseCode")).equals("200")) {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", rst_msg);

			} else {

				ActionLog log = new ActionLog();
				String userid = (LoginId == null || LoginId.equals("")) ? DeviceId
						: LoginId;
				log.setUserid(userid);
				log.setType_first(MainType);
				log.setType_second(SecondType);

				stasticService.addActionLog(log);

				jsonOut.put("ResponseCode", "200");
				jsonOut.put("ResponseMsg", "OK");
			}
		} catch (Exception e) {
			try {

				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", e.getMessage());
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return jsonOut;
	}

	/**
	 * 加载小时统计页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @date 2016-6-23
	 * @author zj
	 */
	@RequestMapping(value = "/initHourPage")
	public ModelAndView initHourPage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/stastic/hour");

		/*AppInfo query = new AppInfo();
		query.setPage(1);
		query.setPageCount(1000);
		List<AppInfo> apps = appInfoService.getAppInfoPageList(query);
		view.addObject("appInfos", apps);*/

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String cur_date = format.format(new Date());
		view.addObject("cur_date", cur_date);

		return view;
	}

	/**
	 * 加载应用服务统计页面-加载应用服务列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @date 2016-6-23
	 * @author zj
	 */
	@RequestMapping(value = "/initAppServiceStatic")
	public ModelAndView initAppServiceStatic(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/stastic/appservice_info");

		/*AppInfo query = new AppInfo();
		query.setPage(1);
		query.setPageCount(1000);
		List<AppInfo> apps = appInfoService.getAppInfoPageList(query);
		view.addObject("appInfos", apps);*/

		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// String cur_date = format.format(new Date());
		// view.addObject("cur_date", cur_date);

		return view;
	}

	

	
	
	/**
	 * 页面-获取用户全局访问统计
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/GetUserVisitList")
	public void GetUserVisitList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回输出报文
		List<HashMap<Object, Object>> infos = GetUserVisitData(request,
				false);

		List<HashMap<Object, Object>> infosTotal = GetUserVisitData(request,
				true);

		Gson gs = new Gson();
		String jsStr = gs.toJson(infos);

		String rst = "{\"total\":" + infosTotal.size() + ",\"rows\":" + jsStr
				+ "}";

		JsonUtil.responseOutWithJson(response, rst);

	}
	
	
	/**
	 * 获取用户使用全局统计情况
	 * 
	 * @param request
	 * @return
	 * @date 2016-10-13
	 * @author zj
	 */
	@RequestMapping(value = "/GetRecentVisitData")
	public void GetRecentVisitData(
			HttpServletRequest request,HttpServletResponse response ) {
	
		try {

			 Date now=new Date();
			 Date y= DateUtils.addDays(now,-1);
			// 返回输出报文
			String dateY=DateUtil.getDateStr(y, "yyyy-MM-dd");
			String dateN=DateUtil.getDateStr(now, "yyyy-MM-dd");
			VisitData vY= visitDao.selectByPrimaryKey(dateY);
			VisitData vN= visitDao.selectByPrimaryKey(dateN);
			
			Gson g=new Gson();
			String rst = "{\"ResponseCode\":200, \"y\":" +g.toJson(vY) + ",\"n\":" + g.toJson(vN)
					+ "}";

			JsonUtil.responseOutWithJson(response, rst);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	
	}
	
	
	
	/**
	 * 获取用户使用全局统计情况
	 * 
	 * @param request
	 * @return
	 * @date 2016-10-13
	 * @author zj
	 */
	private List<HashMap<Object, Object>> GetUserVisitData(
			HttpServletRequest request, boolean export) {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");

		String age = request.getParameter("age");
		String date_type = request.getParameter("date_type");
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");


		String age1 = "";
		String age2 = "";
		if (!age.equals("")) {
			age1 = age.substring(0, age.indexOf("_"));
			age2 = age.substring(age.indexOf("_") + 1, age.length());
		}

		String appNames = request.getParameter("appNames");
		//String appIDs = request.getParameter("appIDs");

		String[] StrAppNames = appNames.split(",");
		//String[] StrAppIDs = appIDs.split(",");

		int pageCount = Integer.parseInt(request.getParameter("rows"));// request.getParameter("pageCount");
		int curPage = Integer.parseInt(request.getParameter("page"));
		if (export) {
			// 导出查1000条
			pageCount = 1000;
			curPage = 1;
		}

		List<HashMap<Object, Object>> infos = new ArrayList<HashMap<Object, Object>>();
		try {

			// 返回输出报文

			infos = stasticService.GetUserVisitRecondList
					(name, sex, age1, age2, StrAppNames, date_type, date1, date2,(curPage - 1) * pageCount,pageCount);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return infos;

	}
	
	/**
	 * 用户访问统计
	 * 数据导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @date 2016-10-13
	 * @author zj
	 */
	@RequestMapping(value = "/exportUserVisitInfo")
	public void exportUserVisitInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<HashMap<Object, Object>> infos = GetUserVisitData(request, true);

		String appNames = request.getParameter("appNames");
		String[] StrAppNames = appNames.split(",");
		
		List<String[]> xlsInputs = new ArrayList<String[]>();
		String[] row_head = new String[StrAppNames.length+2];
		row_head[0]="用户";
		row_head[1]="时间";
		for (int i = 2; i < row_head.length; i++) {
			row_head[i]=StrAppNames[i-2];
		}
	
		xlsInputs.add(row_head);

		for (int i = 0; i < infos.size(); i++) {
			String[] row = new String[row_head.length];
			for (int j = 0; j < row_head.length; j++) {
				if(j==0)
				row[j]=String.valueOf( infos.get(i).get("name"));
				else if(j==1)
					row[j]=String.valueOf( infos.get(i).get("hour"));
				else
				row[j]=String.valueOf( infos.get(i).get(row_head[j]));
			}

			xlsInputs.add(row);
		}

		try {
			HSSFWorkbook wb = FileUtil.createPauseDataDetailExcel(xlsInputs);
			if (wb != null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/ms-excel");
				response.setHeader(
						"Content-Disposition",
						"attachment;filename=" + "useApp_"
								+ DateUtil.getNowStr("yyyyMMddHHmmss") + ".xls");// mod
																					// by

				ServletOutputStream out = response.getOutputStream();
				wb.write(out);
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 页面-获取用户使用服务情况
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/GetUserAppUseDataList")
	public void GetUserAppUseDataList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回输出报文
		List<HashMap<Object, Object>> infos = GetUserAppUseData(request,
				false);

		List<HashMap<Object, Object>> infosTotal = GetUserAppUseData(request,
				true);

		Gson gs = new Gson();
		String jsStr = gs.toJson(infos);

		String rst = "{\"total\":" + infosTotal.size() + ",\"rows\":" + jsStr
				+ "}";

		JsonUtil.responseOutWithJson(response, rst);

	}
	
	/**
	 * 获取用户使用应用服务情况
	 * 
	 * @param request
	 * @return
	 * @date 2016-10-13
	 * @author zj
	 */
	private List<HashMap<Object, Object>> GetUserAppUseData(
			HttpServletRequest request, boolean export) {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");

		String age = request.getParameter("age");
		String date_type = request.getParameter("date_type");
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");


		String age1 = "";
		String age2 = "";
		if (!age.equals("")) {
			age1 = age.substring(0, age.indexOf("_"));
			age2 = age.substring(age.indexOf("_") + 1, age.length());
		}

		String appNames = request.getParameter("appNames");
		//String appIDs = request.getParameter("appIDs");

		String[] StrAppNames = appNames.split(",");
		//String[] StrAppIDs = appIDs.split(",");

		int pageCount = Integer.parseInt(request.getParameter("rows"));// request.getParameter("pageCount");
		int curPage = Integer.parseInt(request.getParameter("page"));
		if (export) {
			// 导出查1000条
			pageCount = 1000;
			curPage = 1;
		}

		List<HashMap<Object, Object>> infos = new ArrayList<HashMap<Object, Object>>();
		try {

			// 返回输出报文

			infos = stasticService.GetUserAppuseRecondList
					(name, sex, age1, age2, StrAppNames, date_type, date1, date2,(curPage - 1) * pageCount,pageCount);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return infos;

	}
	
	/**
	 * 用户使用应用数据导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @date 2016-10-13
	 * @author zj
	 */
	@RequestMapping(value = "/exportUserAppUseInfo")
	public void exportUserAppUseInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<HashMap<Object, Object>> infos = GetUserAppUseData(request, true);

		String appNames = request.getParameter("appNames");
		String[] StrAppNames = appNames.split(",");
		
		List<String[]> xlsInputs = new ArrayList<String[]>();
		String[] row_head = new String[StrAppNames.length+2];
		row_head[0]="用户";
		row_head[1]="时间";
		for (int i = 2; i < row_head.length; i++) {
			row_head[i]=StrAppNames[i-2];
		}
	
		xlsInputs.add(row_head);

		for (int i = 0; i < infos.size(); i++) {
			String[] row = new String[row_head.length];
			for (int j = 0; j < row_head.length; j++) {
				if(j==0)
				row[j]=String.valueOf( infos.get(i).get("name"));
				else if(j==1)
					row[j]=String.valueOf( infos.get(i).get("hour"));
				else
				row[j]=String.valueOf( infos.get(i).get(row_head[j]));
			}

			xlsInputs.add(row);
		}

		try {
			HSSFWorkbook wb = FileUtil.createPauseDataDetailExcel(xlsInputs);
			if (wb != null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/ms-excel");
				response.setHeader(
						"Content-Disposition",
						"attachment;filename=" + "useApp_"
								+ DateUtil.getNowStr("yyyyMMddHHmmss") + ".xls");// mod
																					// by

				ServletOutputStream out = response.getOutputStream();
				wb.write(out);
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 页面-获取用户关注服务情况
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/GetUserFocusAppList")
	public void GetUserFocusAppList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 返回输出报文
		List<HashMap<Object, Object>> infos = GetUserFocusAppData(request,
				false);

		List<HashMap<Object, Object>> infosTotal = GetUserFocusAppData(request,
				true);

		Gson gs = new Gson();
		String jsStr = gs.toJson(infos);

		String rst = "{\"total\":" + infosTotal.size() + ",\"rows\":" + jsStr
				+ "}";

		JsonUtil.responseOutWithJson(response, rst);

	}

	/**
	 * 获取用户关注服务情况
	 * 
	 * @param request
	 * @return
	 * @date 2016-10-13
	 * @author zj
	 */
	private List<HashMap<Object, Object>> GetUserFocusAppData(
			HttpServletRequest request, boolean export) {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");

		String age = request.getParameter("age");

		String age1 = "";
		String age2 = "";
		if (!age.equals("")) {
			age1 = age.substring(0, age.indexOf("_"));
			age2 = age.substring(age.indexOf("_") + 1, age.length());
		}

		String appNames = request.getParameter("appNames");
		
		try {
			appNames=URLDecoder.decode(appNames,"ios-");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String appIDs = request.getParameter("appIDs");

		String[] StrAppNames = appNames.split(",");
		String[] StrAppIDs = appIDs.split(",");

		int pageCount = Integer.parseInt(request.getParameter("rows"));// request.getParameter("pageCount");
		int curPage = Integer.parseInt(request.getParameter("page"));
		if (export) {
			// 导出查1000条
			pageCount = 1000;
			curPage = 1;
		}

		List<HashMap<Object, Object>> infos = new ArrayList<HashMap<Object, Object>>();
		try {

			// 返回输出报文

			infos = stasticService.GetUserFocusAppList(name, sex, age1, age2,
					StrAppNames, StrAppIDs, (curPage - 1) * pageCount,
					pageCount);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return infos;

	}

	/**
	 * 用户关注应用数据导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @date 2016-10-13
	 * @author zj
	 */
	@RequestMapping(value = "/exportUserFoucsInfo")
	public void exportUserFoucsInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<HashMap<Object, Object>> infos = GetUserFocusAppData(request, true);

		String appNames = request.getParameter("appNames");
		
		try {
			appNames=URLDecoder.decode(appNames,"utf-8");
			logger.error("exportUserFoucsInfo:"+appNames);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] StrAppNames = appNames.split(",");
		
		List<String[]> xlsInputs = new ArrayList<String[]>();
		String[] row_head = new String[StrAppNames.length+1];
		row_head[0]="用户";
		for (int i = 1; i < row_head.length; i++) {
			row_head[i]=StrAppNames[i-1];
		}
	
		xlsInputs.add(row_head);

		for (int i = 0; i < infos.size(); i++) {
			String[] row = new String[row_head.length];
			for (int j = 0; j < row_head.length; j++) {
				if(j==0)
				row[j]=String.valueOf( infos.get(i).get("name"));
				else
				row[j]=String.valueOf( infos.get(i).get(row_head[j]));
			}

			xlsInputs.add(row);
		}

		try {
			HSSFWorkbook wb = FileUtil.createPauseDataDetailExcel(xlsInputs);
			if (wb != null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/ms-excel");
				response.setHeader(
						"Content-Disposition",
						"attachment;filename=" + "userFocus_"
								+ DateUtil.getNowStr("yyyyMMddHHmmss") + ".xls");// mod
																					// by

				ServletOutputStream out = response.getOutputStream();
				wb.write(out);
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	/**
	 * 投诉数据导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @date 2016-10-13
	 * @author zj
	 */
	@RequestMapping(value = "/exportComplaintInfo")
	public void exportComplaintInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//List<ConsultingAppealing> infos = getComplaintData(request, true);

		/*List<String[]> xlsInputs = new ArrayList<String[]>();
		String[] row_head = new String[11];
		row_head[0] = "查询编号";
		row_head[1] = "用户";
		row_head[2] = "发布时间";
		row_head[3] = "类型";
		row_head[4] = "受理部门";
		row_head[5] = "标题";
		row_head[6] = "内容";
		row_head[7] = "回复时间";
		row_head[8] = "回复部门";
		row_head[9] = "回复内容";
		row_head[10] = "用户满意度";

		xlsInputs.add(row_head);

		for (int i = 0; i < infos.size(); i++) {
			String[] row = new String[11];
			row[0] = infos.get(i).getRANDOMSERIAL();
			row[1] = infos.get(i).getName();
			row[2] = infos.get(i).getPub_date();
			row[3] = infos.get(i).getTypename();
			row[4] = infos.get(i).getDepartmentname();
			row[5] = infos.get(i).getTitle();
			row[6] = infos.get(i).getContent();
			row[7] = infos.get(i).getBack_date();
			row[8] = infos.get(i).getBack_name();
			row[9] = infos.get(i).getBack_content();
			
			String result= infos.get(i).getCp_result();
			if(result==null)
			row[10] = "";
			else if(result.equals("0"))
				row[10] = "满意";
			else if(result.equals("1"))
				row[10] = "一般";
			else if(result.equals("2"))
				row[10] = "不满意";
			xlsInputs.add(row);
		}

		try {
			HSSFWorkbook wb = FileUtil.createPauseDataDetailExcel(xlsInputs);
			if (wb != null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/ms-excel");
				response.setHeader(
						"Content-Disposition",
						"attachment;filename=" + "complaint_"
								+ DateUtil.getNowStr("yyyyMMddHHmmss") + ".xls");// mod
																					// by

				ServletOutputStream out = response.getOutputStream();
				wb.write(out);
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
*/
	}

	
	@RequestMapping(value = "/getTypeInfoList")
	public void getTypeInfoList(HttpServletRequest request,
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

			String blog_type = jsonIN.optString("name");
			String dict_type = "blog_type";

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");

	
			List<DictInfo> infos = stasticService.GetStaticTypeList();
					
			

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
	 * 时间段、点击详细（地市分布、数量）
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-29
	 */
	@RequestMapping(value = "/getDetailList")
	public void getDetailList(HttpServletRequest request,
			HttpServletResponse response) {
		// String blogid = request.getParameter("blogid");

		/*
		 * int pageCount = Integer.parseInt(request.getParameter("rows"));//
		 * request.getParameter("pageCount"); int curPage =
		 * Integer.parseInt(request.getParameter("page"));
		 */

		
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			

			String time_type = request.getParameter("date_type");
			String date1 = request.getParameter("date");
			//String date2 = request.getParameter("date2");

			String qName = request.getParameter("qName");
			String qType = request.getParameter("qType");

			String type1 = qType.substring(0, qType.lastIndexOf("_"));
			String type2 = qType.substring(qType.lastIndexOf("_") + 1,
					qType.length());

			int pageCount = Integer.parseInt(request.getParameter("rows"));// request.getParameter("pageCount");
			int curPage = Integer.parseInt(request.getParameter("page"));
	
			
			String dateFormat="%Y-%m-%d %H";
			if(time_type.equals("HOUR"))
				 dateFormat="%Y-%m-%d %H";
			else if(time_type.equals("DAY"))
				 dateFormat="%Y-%m-%d";
			else  if(time_type.equals("MONTH"))
				 dateFormat="%Y-%m";
			
			ActionLog aquery=new ActionLog();
			aquery.setTime1(date1);
			//aquery.setTime2(date2);
			aquery.setDateFormat(dateFormat);
			aquery.setType_first(type1);
			aquery.setType_second(type2);
			aquery.setPage(curPage);
			aquery.setPageCount(pageCount);
			
			
			List<ActionLog> infos = stasticService.GetDetailList(aquery);
			int total = stasticService.GetDetailListCount(aquery);
					
			

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
	
	
	
	/**
	 * 获取统计数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAppStaticData")
	public void getAppStaticData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String date_type = request.getParameter("date_type");
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");

		String qName = request.getParameter("qName");
		String qType = request.getParameter("qType");

		String type1 = qType.substring(0, qType.lastIndexOf("_"));
		String type2 = qType.substring(qType.lastIndexOf("_") + 1,
				qType.length());
		
		logger.debug("date_type:"+date_type+"/qType:"+qType+"/qName:"+qName+"/date1:"+date1+"/date1:"+date1);

		JSONObject jsonOut = new JSONObject();
		JSONObject jsonChartData = new JSONObject();

		try {

			// 返回输出报文
			List<ActionLog> infos = getStaticData(request);
			JSONArray japps = new JSONArray();
			if (infos != null && infos.size() != 0) {

				for (int i = 0; i < infos.size(); i++) {
					JSONObject japp = new JSONObject();
					japp.put("Name", qName);
					japp.put("typeName", infos.get(i).getAction_date());

					japp.put("pv", infos.get(i).getTotal_click());
					japp.put("uv", infos.get(i).getTotal_uv());

					japps.put(japp);
				}

			} else {
				jsonOut.put("ResponseMsg", "无查询结果");
				jsonOut.put("ChartData", jsonChartData);

			}
			// jsonChartData.put("Data", japps);

			jsonOut.put("rows", japps);
			jsonOut.put("ResponseCode", 200);

			jsonOut.put("total", infos.size());

		} catch (Exception e) {
			jsonOut.put("ResponseMsg", "参数异常");
			jsonOut.put("ChartData", "");
			jsonOut.put("ResponseCode", 201);
		}
		JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}

	/**
	 * 获取全局统计数据
	 * 
	 * @param request
	 * @return
	 * @date 2016-10-13
	 * @author zj
	 */
	private List<ActionLog> getStaticData(HttpServletRequest request) {
		String date_type = request.getParameter("date_type");
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");

	
		String qType = request.getParameter("qType");

		String type1 = qType.substring(0, qType.lastIndexOf("_"));
		String type2 = qType.substring(qType.lastIndexOf("_") + 1,
				qType.length());

		JSONObject jsonOut = new JSONObject();
		JSONObject jsonChartData = new JSONObject();
		List<ActionLog> infos = new ArrayList<ActionLog>();
		try {

			// 返回输出报文

			ActionLog query = new ActionLog();
			query.setType_first(type1);
			query.setType_second(type2);
			query.setAction_date(date1);
			query.setAction_date_end(date2);

			if (date_type.equals("HOUR"))
				infos = stasticService.GetHourDetailList(query);
			else if (date_type.equals("DAY"))
				infos = stasticService.GetDayDetailList(query);
			else if (date_type.equals("MONTH"))
				infos = stasticService.GetMonthDetailList(query);
		} catch (Exception e) {

		}

		return infos;

	}

	/*
	 * 批量导出所有用户信息
	 */
	@RequestMapping(value = "/exportXLSAppStaticData")
	public void exportUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String qName = request.getParameter("qName");

		List<ActionLog> infos = getStaticData(request);
		List<String[]> xlsInputs = new ArrayList<String[]>();
		String[] row_head = new String[4];
		row_head[0] = "统计选项";
		row_head[1] = "时间";

		String qType = request.getParameter("qType");
		if (qType.equals("complaint_all")) {
			row_head[2] = "咨询投诉提交数量";
			row_head[3] = "咨询投诉回复数量";
		} else if (qType.contains("svr_error_rate")) {
			row_head[2] = "累计异常率（pv）";
			row_head[3] = "24小时独立异常率（uv）";
		} else if (qType.contains("svr_error")) {
			row_head[2] = "累计异常数（pv）";
			row_head[3] = "24小时独立异常数（uv）";
		} else if (qType.contains("appfocus")) {
			row_head[2] = "订阅量";
			row_head[3] = "";
		} else {
			row_head[2] = "累计访问量（pv）";
			row_head[3] = "24小时独立访问量（uv）";
		}

		xlsInputs.add(row_head);

		for (int i = 0; i < infos.size(); i++) {
			String[] row = new String[4];
			row[0] = qName;
			row[1] = infos.get(i).getAction_date();

			if (qType.contains("svr_error_rate")) {
				row[2] = infos.get(i).getTotal_click().toString() + "%";
				row[3] = infos.get(i).getTotal_uv().toString() + "%";
			} else if (qType.contains("appfocus")) {
				row[2] = infos.get(i).getTotal_click().toString();
				row[3] = "";
			} else {
				row[2] = infos.get(i).getTotal_click().toString();
				row[3] = infos.get(i).getTotal_uv().toString();
			}

			xlsInputs.add(row);
		}

		try {
			HSSFWorkbook wb = FileUtil.createPauseDataDetailExcel(xlsInputs);
			if (wb != null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/ms-excel");
				response.setHeader(
						"Content-Disposition",
						"attachment;filename=" + "static_"
								+ DateUtil.getNowStr("yyyyMMddHHmmss") + ".xls");// mod
																					// by
				// pengqp at
				// 2012/8/29
				// 下载文件乱码
				ServletOutputStream out = response.getOutputStream();
				wb.write(out);
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取小时统计数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getNormalHourData")
	public void getNormalHourData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		String type1 = request.getParameter("type1");
		String type2 = request.getParameter("type2");

		JSONObject jsonOut = new JSONObject();
		JSONObject jsonChartData = new JSONObject();

		try {

			// 返回输出报文

			jsonChartData.put("Time", date1);

			ActionLog query = new ActionLog();
			query.setType_first(type1);
			query.setType_second(type2);
			query.setAction_date(date1);
			query.setAction_date_end(date2);

			List<ActionLog> infos = stasticService
					.GetNormalHourDetailList(query);

			JSONArray japps = new JSONArray();
			if (infos != null && infos.size() != 0) {

				for (int i = 0; i < infos.size(); i++) {
					JSONObject japp = new JSONObject();
					japp.put("typeName", infos.get(i).getType_name());
					japp.put("pv", infos.get(i).getTotal_click());
					japp.put("uv", infos.get(i).getTotal_uv());

					japps.put(japp);
				}

			} else {
				jsonOut.put("ResponseMsg", "无查询结果");
				jsonOut.put("ChartData", jsonChartData);

			}
			jsonChartData.put("Data", japps);

			jsonOut.put("ChartData", jsonChartData);
			jsonOut.put("ResponseCode", 200);
		} catch (Exception e) {
			jsonOut.put("ResponseMsg", "参数异常");
			jsonOut.put("ChartData", "");
			jsonOut.put("ResponseCode", 201);
		}
		JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}

	/**
	 * 获取小时统计数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getHourData")
	public void getHourData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String data = request.getParameter("data");
		String id = request.getParameter("id");

		JSONObject jsonOut = new JSONObject();
		JSONObject jsonChartData = new JSONObject();

		// 返回输出报文
		if (data != null && !data.isEmpty()) {

			jsonChartData.put("Time", data);

			ActionLog query = new ActionLog();
			query.setType_first("应用服务");
			query.setType_second(id);
			query.setAction_date(data);

			List<ActionLog> infos = stasticService.GetHourDetailList(query);

			JSONArray japps = new JSONArray();
			if (infos != null && infos.size() != 0) {

				for (int i = 0; i < infos.size(); i++) {
					JSONObject japp = new JSONObject();
					japp.put("typeName",
							infos.get(i).getAction_date().replace(data, ""));
					japp.put("pv", infos.get(i).getTotal_click());
					japp.put("uv", infos.get(i).getTotal_uv());

					japps.put(japp);
				}

			} else {
				jsonOut.put("ResponseMsg", "无查询结果");
				jsonOut.put("ChartData", jsonChartData);

			}
			jsonChartData.put("Data", japps);

			jsonOut.put("ChartData", jsonChartData);
			jsonOut.put("ResponseCode", 200);

		} else {

			jsonOut.put("ResponseMsg", "参数异常");
			jsonOut.put("ChartData", "");
			jsonOut.put("ResponseCode", 201);
		}
		JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}

	/**
	 * 获取小时统计数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDayData")
	public void getDayData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String data = request.getParameter("data");

		JSONObject jsonOut = new JSONObject();
		JSONObject jsonChartData = new JSONObject();

		// 返回输出报文
		if (data != null && !data.isEmpty()) {

			jsonChartData.put("Time", data);

			ActionLog query = new ActionLog();
			query.setType_first("应用服务");
			query.setAction_date(data);

			List<ActionLog> infos = stasticService.GetDayDetailList(query);

			JSONArray japps = new JSONArray();
			if (infos != null && infos.size() != 0) {

				for (int i = 0; i < infos.size(); i++) {
					JSONObject japp = new JSONObject();
					japp.put("typeName", infos.get(i).getType_name());
					japp.put("pv", infos.get(i).getTotal_click());
					japp.put("uv", infos.get(i).getTotal_uv());

					japps.put(japp);
				}

			} else {
				jsonOut.put("ResponseMsg", "无查询结果");
				jsonOut.put("ChartData", jsonChartData);

			}
			jsonChartData.put("Data", japps);

			jsonOut.put("ChartData", jsonChartData);
			jsonOut.put("ResponseCode", 200);

		} else {

			jsonOut.put("ResponseMsg", "参数异常");
			jsonOut.put("ChartData", "");
			jsonOut.put("ResponseCode", 201);
		}
		JsonUtil.responseOutWithJson(response, jsonOut.toString());
	}

}
