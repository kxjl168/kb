package com.kxjl.web.system.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.common.Md5Encrypt;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.AESEncrypt;
import com.kxjl.tool.utils.CookieUtil;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.tool.utils.StringUtil;
import com.kxjl.web.autodata.dao.VisitDataMapper;
import com.kxjl.web.autodata.model.VisitData;
import com.kxjl.web.blog.action.Kdata;
import com.kxjl.web.blog.action.Kdata.DataType;
import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.dao.DictInfoDao;
import com.kxjl.web.system.dao.SvrFileInfoDao;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.model.SvrFileInfo;
import com.kxjl.web.system.model.SysParameter;
import com.kxjl.web.system.service.CommonService;
import com.kxjl.web.system.service.DictInfoService;
import com.kxjl.web.system.service.SysService;
import com.kxjl.web.system.service.SystemParamService;

/**
 * 系统公用基础相关
 * 
 * @author yexiufang
 * 
 */
@Controller
@RequestMapping(value = "/sysBaseInfo")
public class SysBaseInfoController extends BaseController {

	private Logger logger = Logger.getLogger(SysBaseInfoController.class);

	@Autowired
	private SystemParamService systemService;

	@Autowired
	private DictInfoDao dictInfoService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private SvrFileInfoDao fileInfoDao;

	@Autowired
	private VisitDataMapper visitDataMapper;

	@Autowired
	private SysService sysService;
	
	@Autowired
	private CookieUtil cookieUti;

	// 日志记录对象
	private Logger log = Logger.getLogger(SysBaseInfoController.class);

	@RequestMapping(value = "/setSysInfo")
	public void setSysInfo(HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			String sign = parseStringParam(request, "sign");

			DictInfo dsign = dictInfoService.getDictInfoInfoByKey("blog_sign");
			if (dsign == null)
				dsign = new DictInfo();

			dsign.setDict_key("blog_sign");
			dsign.setDict_type("blog_sign");
			dsign.setDict_name(sign);

			if (dsign.getId() != null)
				dictInfoService.updateDictInfo(dsign);
			else
				dictInfoService.addDictInfo(dsign);

			String imgs = request.getParameter("imgs");
			JSONArray jimgs = new JSONArray(imgs);
			String headimg = jimgs.optJSONObject(0).optString("val");
			String headimgid = jimgs.optJSONObject(0).optString("id");

			DictInfo blog_head = dictInfoService
					.getDictInfoInfoByKey("blog_head");
			if (blog_head == null)
				blog_head = new DictInfo();

			blog_head.setDict_key("blog_head");
			blog_head.setDict_type("blog_head");
			blog_head.setDict_name(headimgid);
			blog_head.setVal1(headimg);

			if (blog_head.getId() != null)
				dictInfoService.updateDictInfo(blog_head);
			else
				dictInfoService.addDictInfo(blog_head);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");

		} catch (Exception e) {
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
	
	@RequestMapping(value = "/getCookie")
	public void getCookie(HttpServletRequest request,
			HttpServletResponse response) {

		String uemail=cookieUti.getCookieByName(request,"uemail");
		String uname=cookieUti.getCookieByName(request,"uname");
		String usite=cookieUti.getCookieByName(request,"usite");

		JSONObject jsonOut=new JSONObject();
		jsonOut.put("uemail", uemail);
		jsonOut.put("uname", uname);
		jsonOut.put("usite", usite);
	
	
		
		jsonOut.put("ResponseCode", "200");
		jsonOut.put("ResponseMsg", "");
		String rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}

	@RequestMapping(value = "/getSysInfo")
	public void getSysInfo(HttpServletRequest request,
			HttpServletResponse response) {

		String localPath = ConfigReader.getInstance().getProperty(
				"LOCAL_HTML_PATH",
				"F:\\kxjl\\code\\kb\\WebContent\\public/html/");

		JSONObject jsonOut;
		jsonOut = sysService.getSysInfoJSON();
		String rst = jsonOut.toString();
		
		
		JsonUtil.responseOutWithJson(response, rst);

	}

	@RequestMapping(value = "/removeStaticHtml")
	public void removeStaticHtml(HttpServletRequest request,
			HttpServletResponse response) {

		String localPath = ConfigReader.getInstance().getProperty(
				"LOCAL_HTML_PATH",
				"F:\\kxjl\\code\\kb\\WebContent\\public/html/");

		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			File localFile = new File(localPath);

			
			Properties pros = System.getProperties();
			String os = (String) pros.get("os.name");

			String binname = "cmd.exe";
			ProcessBuilder builder = new ProcessBuilder();
			if (os.startsWith("Windows")) {// windows下调用系统命令
				doCmd(" cd " + localPath + " && f:  && rm -rf * ");
			
			} else if (os.startsWith("Linux")) {// Linux下调用系统命令
				doCmd(" cd " + localPath + "  && rm -rf * ");
			}
			
			

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");

		} catch (Exception e) {
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

	static boolean done = false;

	private void doCmd(String comman) throws IOException, InterruptedException {

		Properties pros = System.getProperties();
		String os = (String) pros.get("os.name");

		
		String localPath = ConfigReader.getInstance().getProperty(
				"LOCAL_HTML_PATH",
				"F:\\kxjl\\code\\kb\\WebContent\\public/html/");
		
		String binname = "cmd.exe";
		ProcessBuilder builder = new ProcessBuilder();
		if (os.startsWith("Windows")) {// windows下调用系统命令
			 builder.command("cmd.exe", "/c", comman);
			 builder.directory(new File(localPath));
		
		} else if (os.startsWith("Linux")) {// Linux下调用系统命令
			  builder.command("sh", "-c", comman);
				builder.directory(new File(System.getProperty("user.home")));
		}
		
	
		
	

		logger.info("Process :" + comman);
	
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		return;

	}

	@RequestMapping(value = "/getKdataList")
	public void getKdataList(HttpServletRequest request,
			HttpServletResponse response) {

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			jsonIN = new JSONObject(data);

			List<String> types = new ArrayList<String>();
			for (DataType item : Kdata.DataType.values()) {
				Kdata.DataType ci = item;
				ci.setNum(Kdata.getInstance().GetNumOfType(item));
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
	 * 清楚缓存
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2018-1-6
	 */
	@RequestMapping(value = "/cleanKdata")
	public void cleanKdata(HttpServletRequest request,
			HttpServletResponse response) {

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			jsonIN = new JSONObject(data);

			String type = jsonIN.optString("type");
			DataType t = DataType.parse(type);
			if (t == DataType.Common)
				Kdata.getInstance().cleanrCommonList("");
			if (t == DataType.Blog)
				Kdata.getInstance().cleanrBLogList("");
			else if (t == DataType.Menu)
				Kdata.getInstance().cleanrMenuInfoList("");
			else if (t == DataType.Replay)
				Kdata.getInstance().cleanrReplayList("");
			else if (t == DataType.BlackIPList) {
				commonService.resetBlackIPList();
			}

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");

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
	 * 初始化图标上传页面，填充文件服务器地址
	 * 
	 * @return
	 * @date 2016-6-23
	 * @author zj
	 */
	@RequestMapping(value = "/vToken")
	public void vToken(HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonOut = new JSONObject();

		String data = handleNoAresRequest(request);
		try {
			// String data = AESEncrypt.deCrypt("",
			// request.getParameter("data"),
			// Constant.FIRST_AES_KEY);
			// handleRequestNoKey(request);
			// 返回输出报文
			if (data != null && !data.isEmpty()) {

				JSONObject js = new JSONObject(data);

				String Token = js.optString("Token");
				String userID = js.optString("userID");

				Map<String, Object> validateRst = validateToken(Token, userID,
						"");

				String rst_msg = (String) validateRst.get("ResponseMsg");

				if (!String.valueOf(validateRst.get("ResponseCode")).equals(
						"200")) {
					jsonOut.put("ResponseCode", "201");
					jsonOut.put("ResponseMsg", rst_msg);

				} else {
					jsonOut.put("ResponseCode", "200");
					jsonOut.put("ResponseMsg", "OK");
				}

			} else {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "输入错误");

			}
		} catch (Exception e) {
			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", e.getMessage());
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}

		JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}

	/**
	 * 初始化图标上传页面，填充文件服务器地址
	 * 
	 * @return
	 * @date 2016-6-23
	 * @author zj
	 */
	@RequestMapping(value = "/initLogoUpload")
	public ModelAndView initLogoUpload() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/uploadFile/fileLogoUpload");

		return view;
	}

	@RequestMapping(value = "/getImgSvrPath")
	public void getImgSvrPath(HttpServletResponse response) {

		// 获取 文件服务器http地址图片前缀
		String HTTP_PATH = ConfigReader.getInstance().getProperty("HTTP_PATH");

		JsonUtil.responseOutWithJson(response, HTTP_PATH);
	}

	private JSONObject doGetDict(JSONObject params_json) {
		JSONObject jsonOut = new JSONObject();
		JSONArray jsApps = new JSONArray();
		try {

			String DictType = params_json.optString("DictType");
			String Name = params_json.optString("Name");

			String Index = params_json.optString("Index");
			String PageNum = params_json.optString("PageNum");

			int index = 1;
			int pageNum = 100;

			if (!StringUtil.isNull(Index))
				index = Integer.parseInt(Index);
			if (!StringUtil.isNull(PageNum))
				pageNum = Integer.parseInt(PageNum);

			DictInfo query = new DictInfo();
			query.setDict_type(DictType);

			query.setDict_name(Name);
			query.setPage(index);
			query.setPageCount(pageNum);

			List<DictInfo> infos = systemService.getDictInfoList(query);
			int total = systemService.getDictInfoCount(query);
			if (infos != null) {

				for (int i = 0; i < infos.size(); i++) {

					JSONObject japp = new JSONObject();
					japp.put("Name", infos.get(i).getDict_name().toString());
					japp.put("Value", infos.get(i).getDict_key().toString());
					japp.put("Sort", infos.get(i).getSort());

					jsApps.put(japp);
				}
			}

			jsonOut.put("Total", total);
			jsonOut.put("Dicts", jsApps);
			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "OK");

		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonOut;
	}

	/**
	 * 接口-获取字典信息
	 * 
	 * @param request
	 * @param response
	 * @date 2016-6-24
	 * @author zj
	 */
	@RequestMapping(value = "/getDictInfo_test")
	public void getDictInfo_test(HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonOut = new JSONObject();
		JSONArray jsApps = new JSONArray();
		try {

			// String params = handleNoAresRequest(request);
			String params = request.getParameter("data");

			logger.info("getDictInfo方法传入参数：" + params);

			JSONObject params_json = new JSONObject(params);

			jsonOut = doGetDict(params_json);

		} catch (Exception e) {
			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", e.getMessage());
				jsonOut.put("Dicts", jsApps);
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}

		// responseDataNoAllKey(request, response, jsonOut.toString());

		JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}

	/**
	 * 接口-获取字典信息
	 * 
	 * @param request
	 * @param response
	 * @date 2016-6-24
	 * @author zj
	 */
	@RequestMapping(value = "/getDictInfo")
	public void getDictInfo(HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonOut = new JSONObject();
		JSONArray jsApps = new JSONArray();
		try {

			// String params = handleNoAresRequest(request);
			String params = AESEncrypt.deCrypt("",
					request.getParameter("data"), Constant.FIRST_AES_KEY);

			logger.info("getDictInfo方法传入参数：" + params);

			JSONObject params_json = new JSONObject(params);

			jsonOut = doGetDict(params_json);

		} catch (Exception e) {
			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", e.getMessage());
				jsonOut.put("Dicts", jsApps);
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}

		responseDataNoAllKey(request, response, jsonOut.toString());

		// JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}

	/**
	 * 获取系统参数列表
	 * 
	 * @param map
	 * 
	 */
	@RequestMapping(value = "/querySysParam")
	public String querySysParam(ModelMap map) {

		return "sysInfo/sysInfo";
	}

	/**
	 * 初始化增加系统参数
	 * 
	 * @param map
	 * 
	 */
	@RequestMapping(value = "/init_addSysParam")
	public String init_addSysParam(ModelMap map) {
		return "sysInfo/sysInfo_add";
	}

	/**
	 * 增加系统参数
	 * 
	 * @param response
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/addSysParam")
	public @ResponseBody
	int addSysParam(String name, String value, String desc) throws IOException {
		log.info("name:" + name + "value:" + value + "desc" + desc);

		int result = 0;
		SysParameter sysParam = systemService.getOneSysParams(name);
		if (sysParam == null || sysParam.equals("")) {
			SysParameter add_sysParam = new SysParameter();
			add_sysParam.setParam_name(name);
			add_sysParam.setParam_value(value);
			add_sysParam.setParam_desc(desc);
			result = systemService.addOneSysParams(add_sysParam);
			log.info(result);
		} else {
			log.info("该参数名已经存在");
		}

		return result;
	}

	/**
	 * 初始化修改系统页面
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/init_updateSysParam")
	public String init_updateSysParam(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {

		try {
			request.setCharacterEncoding("UTF-8");
			String param_name = request.getParameter("param_name");
			String param_value = request.getParameter("param_value");
			String param_desc = request.getParameter("param_desc");
			param_name = new String(param_name.getBytes("ISO-8859-1"), "UTF-8");
			param_value = new String(param_value.getBytes("ISO-8859-1"),
					"UTF-8");
			param_desc = new String(param_desc.getBytes("ISO-8859-1"), "UTF-8");
			request.setAttribute("param_name", param_name);
			request.setAttribute("param_value", param_value);
			request.setAttribute("param_desc", param_desc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}

		return "sysInfo/sysInfo_update";
	}

	/**
	 * 删除系统设置参数
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteSysParam")
	public @ResponseBody
	int deleteSysParam(String name) throws IOException {
		log.info("name:" + name);

		int result = 0;
		result = systemService.deleteOneSysParams(name);
		if (result > 0) {
			log.info(result);
		} else {
			log.info("删除失败");
		}

		return result;
	}

	/**
	 * 修改系统页面
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateSysParam")
	public @ResponseBody
	int updateSysParam(String name, String value, String desc,
			HttpServletResponse response) {
		log.info("name:" + name + "value:" + value + "desc" + desc);

		SysParameter sysParam = systemService.getOneSysParams(name);
		sysParam.setParam_value(value);
		sysParam.setParam_desc(desc);
		int result = systemService.updateOneSysParams(sysParam);
		log.info(result);

		return result;
	}

	/**
	 * 修改系统页面
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateSysParamValue")
	public @ResponseBody
	int updateSysParam(String name, String value, HttpServletResponse response) {
		log.info("name:" + name + "value:" + value);

		SysParameter sysParam = systemService.getOneSysParams(name);
		sysParam.setParam_value(value);

		int result = systemService.updateOneSysParams(sysParam);
		log.info(result);

		return result;
	}

	@RequestMapping(value = "/getSysInfoContent")
	public String getSysInfoContent(ModelMap map) {
		List<HashMap<String, String>> list = systemService.getSysParams();
		log.info(list);
		map.addAttribute("sysList", list);
		return "sysInfo/sysInfoContent";
	}

	@RequestMapping(value = "/getWaterConfig")
	public String getWaterConfig(Model map, HttpServletRequest request,
			HttpServletRequest response) {
		SysParameter param = systemService.getOneSysParams("WaterMark");
		// log.info(list);

		map.addAttribute("waterMark", param);

		return "/sys/adminPass";
	}

	/**
	 * 判断字符串是否为空或为null
	 * 
	 * @param str字符串
	 */
	public static Boolean isEmpty(String str) {
		if (str == null || str.equals("")) {
			return false;
		} else {
			return true;
		}
	}

}
