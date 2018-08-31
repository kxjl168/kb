package com.kxjl.web.version.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.httpPost.FormFieldKeyValuePair;
import com.kxjl.tool.httpPost.HttpPostEmulator;
import com.kxjl.tool.httpPost.UploadFileItem;
import com.kxjl.tool.utils.IPUtils;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.version.model.IncrementalUpgrade;
import com.kxjl.web.version.model.UpgradePackage;
import com.kxjl.web.version.model.UpgradePackageVersionInfo;
import com.kxjl.web.version.model.UpgradeUser;
import com.kxjl.web.version.model.VersionInfo;
import com.kxjl.web.version.model.VersionNumFormatTool;
import com.kxjl.web.version.service.VersionInfoService;



/**
 * 版本信息 Controller
 * 
 * @author rongtai
 * 
 */
@Controller
@RequestMapping(value = "/version")
public class VersionInfoController extends BaseController {

	@Autowired
	private VersionInfoService vService;

	private SysUserBean userBean = new SysUserBean();

	// 日志记录对象
	private Logger logger = Logger.getLogger(VersionInfoController.class);

	/**
	 * 初始化图标上传页面，填充文件服务器地址
	 * 
	 * @return
	 * @date 2016-6-23
	 * @author zj
	 */
	@RequestMapping(value = "/initUpload")
	public ModelAndView initUpload() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/versionmanager/fileUpload");

		// 获取 文件服务器地址
		String fileSvrPath = ConfigReader.getInstance().getProperty(
				"FILE_SVR_PATH");

		view.addObject("fileSvrPath", fileSvrPath);
		return view;
	}

	/**
	 * 获得版本信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getVersionInfo", method = RequestMethod.POST)
	public void getVersionInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {

		

			String key = "";// get16AesKey(userBean.getPassword());

			// 获得请求参数
			String type = request.getParameter("type");// 版本
														// handleRequest(request,
														// key);

			int itype = 0;//

			try {
				itype = Integer.parseInt(type);
			} catch (Exception e) {

			}

			int pageCount = 3; // Integer.parseInt(request.getParameter("rows"));//
								// request.getParameter("pageCount");
			int curPage = 1; // Integer.parseInt(request.getParameter("page"));

			UpgradePackageVersionInfo param = new UpgradePackageVersionInfo();
			param.setClientType(itype);
			param.setUpgradepackageName("");
			param.setCurrentPage(curPage);
			param.setPageCount(pageCount);
			List<UpgradePackageVersionInfo> infos = vService
					.queryVersionAndPackageInfo(param);

			JSONObject jrst = new JSONObject();

			String urlpre=ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
			
			if (infos.size() > 0) {
				UpgradePackageVersionInfo info = infos.get(0);// 版本号最大的一条

				jrst.put("vnum", info.getVersionNum());
				jrst.put("url", urlpre+info.getUpgradepackagePath());

			}

			JsonUtil.responseOutWithJson(response, jrst.toString());

		} catch (JSONException e) {
			e.printStackTrace();
			logger.error(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	// /**
	// * 获得版本信息-test,使用url连接测试接口
	// *
	// http://127.0.0.1:8080/WebSVR/getVersionInfoG.do?data={"UserId":"phone001","ClientType":"1","CurrentVer":"1.0.2","HasPackage":"0"}&UserId=phone001
	// * @param request
	// * @param response
	// */
	// @RequestMapping(value="/getVersionInfoG")
	// public void getVersionInfoG(HttpServletRequest request,
	// HttpServletResponse response){
	// try {
	// //获得请求参数
	// String reqData =request.getParameter("data");
	// //获得UserId参数
	// String loginId = request.getParameter("UserId");
	// //判断是手机或邮箱或userId登录的
	// if (Pattern.matches(phoneRegex,loginId)) {
	// userBean = userService.getUserInfoByTelphone(loginId);
	// }else if (Pattern.matches(emailRegex,loginId)) {
	// userBean = userService.getUserInfoByEmail(loginId);
	// }else {
	// userBean = userService.getUserInfoByLoginId(loginId);
	// }
	// VersionInfoControllerImpl impl = new
	// VersionInfoControllerImpl(vService,userBean);
	// JSONObject outData = impl.handleData(reqData);
	// //返回数据
	// responseData(request,response, outData.toString(),userService);
	// } catch (JSONException e) {
	// e.printStackTrace();
	// logger.error(e.toString());
	// } catch (Exception e) {
	// e.printStackTrace();
	// logger.error(e.toString());
	// }
	// }

	/**
	 * 查询宿主机列表
	 * 
	 * @param request
	 * @param response
	 * @date 2016-3-9
	 * @author zj
	 */
	@RequestMapping(value = "/init_versionAdd")
	public String init_versionAdd(HttpServletRequest request,
			HttpServletResponse response) {
		return "/versionmanager/versionAdd";
	}

	/**
	 * ios版本升级页面
	 */
	@RequestMapping(value = { "/getPackageUrlByVersionNum" }, method = RequestMethod.GET)
	public ModelAndView getPackageUrlByVersionNum(ModelMap map,
			HttpServletRequest request) {
		String versionNum = request.getParameter("VersionNum");
		String clientType = request.getParameter("ClientType");
		String packageUrl = "";

		VersionInfo param = new VersionInfo();
		VersionNumFormatTool vnft = new VersionNumFormatTool();
		param.setClienttype(Integer.valueOf(clientType));
		param.setVersionnum(Double.parseDouble(vnft.FormatTo10(versionNum)));
		VersionInfo info = vService.queryVersionByClientAndVerNum(param);
		if (null != info) {
			UpgradePackage packageInfo = vService.queryPackageInfo(info
					.getUpgradepackageid());
			if (null != packageInfo) {
				packageUrl = packageInfo.getUpgradepackagepath();
			}
		}

		ModelAndView view = new ModelAndView();
		view.addObject("packageUrl", packageUrl);
		view.setViewName("/version/yundun_ios_upgrade");
		return view;
	}

	/**
	 * 查询版本信息
	 * 
	 * @param clientType
	 * @param packageName
	 * @param curPage
	 *            当前页
	 * @param pageCount
	 *            每页多少条
	 * @return
	 */
	@RequestMapping(value = "/getVersionInfoList")
	public void getVersionInfoList(HttpServletRequest request,
			HttpServletResponse response, int clientType, String packageName) {

		int pageCount = Integer.parseInt(request.getParameter("rows"));// request.getParameter("pageCount");
		int curPage = Integer.parseInt(request.getParameter("page"));

		UpgradePackageVersionInfo param = new UpgradePackageVersionInfo();
		param.setClientType(clientType);
		param.setUpgradepackageName(packageName);
		param.setCurrentPage(curPage);
		param.setPageCount(pageCount);
		List<UpgradePackageVersionInfo> infos = vService
				.queryVersionAndPackageInfo(param);
		List<UpgradePackageVersionInfo> res = new ArrayList<UpgradePackageVersionInfo>();
		VersionNumFormatTool vnft = new VersionNumFormatTool();
		
		
		String urlpre=ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
		
		for (UpgradePackageVersionInfo info : infos) {
			info.setVersionNumStr(vnft.FormatToNomarlVersionNum(info
					.getVersionNum().intValue() + ".0"));
			info.setClientTypeStr(info.getClientType() == 0 ? "Linux" : "Android");
			//info.setClientTypeStr(String.valueOf( info.getClientType()));
			info.setIsforceupgradeStr(info.getIsforceupgrade() == 0 ? "非强制升级"
					: "强制升级");
			info.setEffectdateStr(new SimpleDateFormat("yyyy-MM-dd")
					.format(info.getEffectdate()));
			
			info.setUpgradepackagePath(urlpre+info.getUpgradepackagePath());
			
			res.add(info);
		}

		Long total = this.getVersionInfoCount(clientType, packageName);

		Gson gs = new Gson();
		String jsStr = gs.toJson(res);

		String rst = "{\"ResponseCode\":200,\"total\":" + total + ",\"rows\":"
				+ jsStr + "}";

		JsonUtil.responseOutWithJson(response, rst);

		// return res;
	}

	/**
	 * 获得版本信息条数
	 * 
	 * @param clientType
	 * @param packageName
	 * @return
	 */
	@RequestMapping(value = "/getVersionInfoCount")
	public @ResponseBody
	Long getVersionInfoCount(int clientType, String packageName) {
		UpgradePackageVersionInfo param = new UpgradePackageVersionInfo();
		param.setClientType(clientType);
		param.setUpgradepackageName(packageName);
		return vService.queryVersionAndPackageCount(param);
	}

	/**
	 * 添加版本信息
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/versionInfoAdd")
	public @ResponseBody
	Map versionInfoAdd(HttpServletRequest request) {
		Map res = new HashMap();
		VersionNumFormatTool vnft = new VersionNumFormatTool();
		String upgradepackageName = request.getParameter("upgradepackageName");
		String upgradepackagePath = request.getParameter("upgradepackagePath");
		String clientType = request.getParameter("clientType");
		String isforceupgrade = request.getParameter("isforceupgrade");
		String versionNum = request.getParameter("versionNum");
		String effectDate = request.getParameter("effectdateStr");
		// String updateDepart = request.getParameter("updateDepart");
		String md5 = request.getParameter("md5");
		String annotation = request.getParameter("annotation");
		// 安装包信息
		UpgradePackage upackage = new UpgradePackage();
		upackage.setUpgradepackagename(upgradepackageName);
		upackage.setUpgradepackagepath(upgradepackagePath);
		upackage.setAnnotation(annotation);
		upackage.setCreateby("");
		upackage.setMd5(md5);
		upackage.setCreatedate(new Date());

		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		// QRCode code = new QRCode();
		// String qrPath =
		// code.createQRCode(realPath+upgradepackagePath+upgradepackageName,
		// 200, 200,realPath);
		// logger.info("生成二维码！");
		// 新增安装包信息
		Long pres = vService.savePackageInfo(upackage);
		if (pres <= 0) {
			res.put("ResponseCode", "500");
			res.put("ResponseMsg", "保存版本信息异常");
			return res;
		}
		// 新增安装包后返回安装包ID
		int recordId = upackage.getRecordid();
		if (recordId != 0) {
			// 版本信息
			VersionInfo version = new VersionInfo();
			version.setUpgradepackageid(recordId);
			version.setClienttype(Integer.parseInt(clientType));
			version.setIsforceupgrade(Integer.parseInt(isforceupgrade));

			version.setIsallowall(1);
			//
			// if (updateDepart != null && updateDepart.equals("all")) {
			// version.setIsallowall(1);
			// } else {
			// version.setIsallowall(0);
			// }
			version.setVersionnum(Double.parseDouble(vnft
					.FormatTo10(versionNum)));
			// 判断版本是否已存在
			VersionInfo infoParam = new VersionInfo();
			infoParam.setClienttype(Integer.parseInt(clientType));
			infoParam.setVersionnum(Double.parseDouble(vnft
					.FormatTo10(versionNum)));
			VersionInfo versionInfo = vService
					.queryVersionByClientAndVerNum(infoParam);
			if (versionInfo != null) {
				res.put("ResponseCode", "202");
				res.put("ResponseMsg", "版本已存在！");
				return res;
			}
			try {
				version.setEffectdate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(effectDate));
			} catch (ParseException e) {
				logger.error(e.toString());
			}
			// version.setQrcodeurl(qrPath);
			version.setCreatedate(new Date());
			version.setCreator("");
			version.setAnnotation(annotation);
			// 新增版本信息
			Long vres = vService.saveVersionInfo(version);
			if (vres <= 0) {
				res.put("ResponseCode", "500");
				res.put("ResponseMsg", "保存版本信息异常");
				return res;
			}
			int vrecordId = version.getRecordid();
			// // 定向升级部门信息
			// if (vrecordId != 0) {
			//
			// if (updateDepart != null && !updateDepart.equals("all")) {
			//
			// UpgradeUser user = new UpgradeUser();
			// String[] departs = updateDepart.split(",");
			// for (int i = 0; i < departs.length; i++) {
			// String data = departs[i];
			//
			// String departIDorUserID = data.split("%")[0];
			// String type = departs[i].split("%")[1];
			//
			// // 部门ID or userId
			// user.setId(departIDorUserID);
			// // Id类型 1部门 2用户
			// user.setIdType(Integer.parseInt(type));
			// user.setVersionId(vrecordId);
			// Long ures = vService.saveUpgradeUserInfo(user);
			// if (ures <= 0) {
			// res.put("ResponseCode", "500");
			// res.put("responseMsg", "保存版本信息异常");
			// return res;
			// }
			// }
			// }
			// }

			// // 本地生成差异包信息
			// vService.add_diff(clientType, vnft.FormatTo10(versionNum),
			// upgradepackagePath);

			// 远程生成差异包
			String serverUrl = ConfigReader.getInstance().getProperty(
					"FILE_SVR_PATH")
					+ "version/add_diff.action";
			ArrayList<FormFieldKeyValuePair> fds = new ArrayList<FormFieldKeyValuePair>();

			FormFieldKeyValuePair p1 = new FormFieldKeyValuePair("clientType",
					clientType);
			FormFieldKeyValuePair p2 = new FormFieldKeyValuePair("versionNum",
					versionNum);
			FormFieldKeyValuePair p3 = new FormFieldKeyValuePair(
					"upgradepackagePath", upgradepackagePath);
			fds.add(p1);
			fds.add(p2);
			fds.add(p3);

			ArrayList<UploadFileItem> files = new ArrayList<UploadFileItem>();

			try {
				String responsedata = HttpPostEmulator.sendHttpPostRequest(
						serverUrl, fds, files);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		String resCode = (String) res.get("ResponseCode");
		if (null == resCode || resCode.isEmpty()) {
			res.put("ResponseCode", "200");
			res.put("responseMsg", "保存版本信息成功");
		}
		return res;
	}

	@RequestMapping(value = "/add_diff")
	public void add_diff(HttpServletRequest request) {
		String clientType = request.getParameter("clientType");
		String versionNum = request.getParameter("versionNum");
		String upgradepackagePath = request.getParameter("upgradepackagePath");

		logger.info("add_diff:***************");
		VersionNumFormatTool vnft = new VersionNumFormatTool();
		vService.add_diff_inFileSvr(clientType, vnft.FormatTo10(versionNum),
				upgradepackagePath);
	}

	/**
	 * 修改版本信息
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/versionInfoUpdate")
	public @ResponseBody
	Map versionInfoUpdate(HttpServletRequest request) {
		Map res = new HashMap();
		VersionNumFormatTool vnft = new VersionNumFormatTool();
		String upgradepackageName = request.getParameter("upgradepackageName");
		String upgradepackagePath = request.getParameter("upgradepackagePath");
		String clientType = request.getParameter("clientType");
		String isforceupgrade = request.getParameter("isforceupgrade");
		String versionNum = request.getParameter("versionNum");
		String effectDate = request.getParameter("effectdateStr");
		// String updateDepart = request.getParameter("updateDepart");
		String md5 = request.getParameter("md5");
		String annotation = request.getParameter("annotation");
		String recordid = request.getParameter("recordid");

		// 获得版本信息
		VersionInfo vinfo = vService.queryVersionInfoByRecordId(Integer
				.parseInt(recordid));

		// // 获得定向升级用户信息
		// UpgradeUser puser = new UpgradeUser();
		// puser.setVersionId(vinfo.getRecordid());
		// List<UpgradeUser> uuser = vService.queryUpgradeUser(puser);
		// 获得安装包信息
		UpgradePackage pack = vService.queryPackageInfo(vinfo
				.getUpgradepackageid());
		UpgradePackage upackage = pack;

		// 判断版本是否已存在
		VersionInfo infoParam = new VersionInfo();
		infoParam.setClienttype(Integer.parseInt(clientType));
		infoParam
				.setVersionnum(Double.parseDouble(vnft.FormatTo10(versionNum)));
		String URLNew = upgradepackagePath;
		if ("" != URLNew && !"".equals(URLNew) && URLNew.indexOf("/") != -1) {
			URLNew = upgradepackagePath.substring(upgradepackagePath
					.lastIndexOf("/") + 1);
		}
		// String qrPath = vinfo.getQrcodeurl();

		// 更新安装包
		upackage.setUpgradepackagename(upgradepackageName);
		upackage.setUpgradepackagepath(upgradepackagePath);
		upackage.setAnnotation(annotation);
		upackage.setUpdatedby("");
		upackage.setMd5(md5);
		upackage.setUpdateddate(new Date());
		// 修改安装包信息
		Long pres = vService.updateUpgradePackage(upackage);
		if (pres <= 0) {
			res.put("ResponseCode", "500");
			res.put("responseMsg", "保存版本信息异常");
			return res;
		}
		// 判断安装包是否一致
		if (pack != null && !md5.equals(pack.getMd5())) {

			// // 差异包信息
			// vService.add_diff(clientType, vnft.FormatTo10(versionNum),
			// upgradepackagePath);

			// 远程生成差异包
			String serverUrl = ConfigReader.getInstance().getProperty(
					"FILE_SVR_PATH")
					+ "version/add_diff.action";
			ArrayList<FormFieldKeyValuePair> fds = new ArrayList<FormFieldKeyValuePair>();

			FormFieldKeyValuePair p1 = new FormFieldKeyValuePair("clientType",
					clientType);
			FormFieldKeyValuePair p2 = new FormFieldKeyValuePair("versionNum",
					versionNum);
			FormFieldKeyValuePair p3 = new FormFieldKeyValuePair(
					"upgradepackagePath", upgradepackagePath);
			fds.add(p1);
			fds.add(p2);
			fds.add(p3);

			ArrayList<UploadFileItem> files = new ArrayList<UploadFileItem>();

			try {
				String responsedata = HttpPostEmulator.sendHttpPostRequest(
						serverUrl, fds, files);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 安装包ID
		int recordId = upackage.getRecordid();
		if (recordId != 0) {
			// 版本信息
			VersionInfo version = vinfo;
			version.setUpgradepackageid(recordId);
			version.setClienttype(Integer.parseInt(clientType));
			version.setIsforceupgrade(Integer.parseInt(isforceupgrade));

			version.setIsallowall(1);
			//
			// if (updateDepart != null && updateDepart.equals("all")) {
			// version.setIsallowall(1);
			// } else {
			// version.setIsallowall(0);
			// }
			version.setVersionnum(Double.parseDouble(vnft
					.FormatTo10(versionNum)));
			try {
				version.setEffectdate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(effectDate));
			} catch (ParseException e) {
				logger.error(e.toString());
			}
			// version.setQrcodeurl(qrPath);
			version.setUpdateddate(new Date());
			SysUserBean u = (SysUserBean) request.getSession().getAttribute(
					Constant.SESSION_USER);
			version.setUpdatedby(u.getUserid());
			version.setAnnotation(annotation);
			// 修改版本信息
			Long vres = vService.updateVersionInfo(version);
			if (vres <= 0) {
				res.put("ResponseCode", "500");
				res.put("responseMsg", "保存版本信息异常");
				return res;
			}
			int vrecordId = version.getRecordid();

			// // 删除原有升级部门信息
			// // 重新添加
			// if (vrecordId != 0) {
			//
			// // 删除该版本对应的部门信息
			// vService.delUpgradeUserInfo(String.valueOf(vrecordId));
			//
			// if (updateDepart != null && !updateDepart.equals("all")) {
			//
			// UpgradeUser user = new UpgradeUser();
			// String[] departs = updateDepart.split(",");
			// for (int i = 0; i < departs.length; i++) {
			//
			// String data = departs[i];
			//
			// String departIDorUserID = data.split("%")[0];
			// String type = departs[i].split("%")[1];
			//
			// // 部门ID or userId
			// user.setId(departIDorUserID);
			// // Id类型 1部门 2用户
			// user.setIdType(Integer.parseInt(type));
			// user.setVersionId(vrecordId);
			// Long ures = vService.saveUpgradeUserInfo(user);
			// if (ures <= 0) {
			// res.put("ResponseCode", "500");
			// res.put("responseMsg", "保存版本信息异常");
			// return res;
			// }
			// }
			// }
			// }

		}

		String resCode = (String) res.get("ResponseCode");
		if (null == resCode || resCode.isEmpty()) {
			res.put("ResponseCode", "200");
			res.put("responseMsg", "修改版本信息成功");
		}
		return res;
	}

	/**
	 * 根据recordId查询版本信息
	 * 
	 * @param recordid
	 * @return
	 */
	@RequestMapping(value = "/versionInfoShow")
	public String versionInfoShow(ModelMap map, HttpServletRequest request,
			int recordid) {

		UpgradePackageVersionInfo info = getVersionInfoDetail(request, recordid);
		map.addAttribute("info", info);
		return "/versionmanager/versionShow";

		// return info;
	}

	/**
	 * 根据recordId查询版本信息
	 * 
	 * @param recordid
	 * @return
	 */
	@RequestMapping(value = "/versionUpdate")
	public String versionUpdate(ModelMap map, HttpServletRequest request,
			int recordid) {

		UpgradePackageVersionInfo info = getVersionInfoDetail(request, recordid);
		map.addAttribute("info", info);
		return "/versionmanager/versionUpdate";

		// return info;
	}

	private UpgradePackageVersionInfo getVersionInfoDetail(
			HttpServletRequest request, int recordid) {
		VersionNumFormatTool vnft = new VersionNumFormatTool();
		UpgradePackageVersionInfo info = new UpgradePackageVersionInfo();
		// 获得版本信息
		VersionInfo vinfo = vService.queryVersionInfoByRecordId(recordid);
		info.setRecordid(vinfo.getRecordid());
		info.setClientType(vinfo.getClienttype());
		info.setIsforceupgrade(vinfo.getIsforceupgrade());
		info.setVersionNumStr(vnft.FormatToNomarlVersionNum(new Double(vinfo
				.getVersionnum()).intValue() + ".0"));
		info.setEffectdateStr(new SimpleDateFormat("yyyy-MM-dd").format(vinfo
				.getEffectdate()));
		info.setAnnotation(vinfo.getAnnotation());

		info.setUpdateDepart("all");
		// // 获得定向升级用户信息
		// UpgradeUser user = new UpgradeUser();
		// user.setVersionId(vinfo.getRecordid());
		// List<UpgradeUser> uuser = vService.queryUpgradeUser(user);
		// if (uuser != null && uuser.size() != 0) {
		// String departId = "";
		// String departName = "";
		// for (UpgradeUser upgradeUser : uuser) {
		// try {
		//
		// if (upgradeUser.getIdType() == 1) {
		// // DeptInfo di = deptService
		// // .queryDepartmentByDempID(upgradeUser.getId());
		// // departName += di.getOrgname() + ",";
		// // departId += upgradeUser.getId() + "%1,";
		// } else if (upgradeUser.getIdType() == 2) {
		// UserBean usr = userService
		// .getUserInfoByLoginId(upgradeUser.getId());
		// departName += usr.getName() + ",";
		// departId += upgradeUser.getId() + "%2,";
		//
		// }
		// } catch (Exception e) {
		// continue;
		// }
		// }
		//
		// info.setUpdateDepart(departId.substring(0, departId.length() - 1));
		// info.setUpdateDepartName(departName.substring(0,
		// departName.length() - 1));
		// } else {
		// if (vinfo.getIsallowall() == 1) // 全选
		// {
		// info.setUpdateDepart("all");
		//
		// // DeptInfo Dept = deptService.queryDepartmentByDempID("1");
		// // if (Dept != null)
		// // info.setUpdateDepartName(Dept.getOrgname());
		// } else // 一个都不选
		// {
		// info.setUpdateDepart("");
		// info.setUpdateDepartName("");
		// }
		// }
		// 获得安装包信息
		UpgradePackage pack = vService.queryPackageInfo(vinfo
				.getUpgradepackageid());
		info.setUpgradepackageName(pack.getUpgradepackagename());
		info.setUpgradepackagePath(pack.getUpgradepackagepath());
		info.setMd5(pack.getMd5());

		return info;
	}

	/**
	 * 版本信息删除
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/versionInfoDel")
	public @ResponseBody
	Map deleteVersion(HttpServletRequest request) {

		String recordid = request.getParameter("recordid");
		// 获得版本信息
		VersionInfo vinfo = vService.queryVersionInfoByRecordId(Integer
				.parseInt(recordid));

		// // 获得定向升级用户信息
		// UpgradeUser user = new UpgradeUser();
		// user.setVersionId(vinfo.getRecordid());
		// List<UpgradeUser> uuser = vService.queryUpgradeUser(user);

		// 获得安装包信息
		UpgradePackage pack = vService.queryPackageInfo(vinfo
				.getUpgradepackageid());
		// 删除安装包
		vService.deleteUpgradePackage(pack);
		String realPath = request.getSession().getServletContext()
				.getRealPath("/");

		String packPath = pack.getUpgradepackagepath();
		//
		// 删除版本信息
		vService.deleteVersionInfo(vinfo);
		IncrementalUpgrade param = new IncrementalUpgrade();
		param.setClienttype(vinfo.getClienttype());
		param.setCurrentVersion(vinfo.getVersionnum());
		// 删除差异包记录
		List<IncrementalUpgrade> increment = vService
				.getIncrementalUpgrade(param);
		for (IncrementalUpgrade incrementalUpgrade : increment) {
			vService.deleteIncrementalUpgrade(incrementalUpgrade);
			// upload.deleteFile(incrementalUpgrade.getDiffPath(), realPath);
		}
		Map res = new HashMap();
		res.put("ResponseCode", "200");
		res.put("ResponseMsg", "修改版本信息成功");
		return res;
	}

	/**
	 * 复制Android升级包
	 * 
	 * @param fileName
	 *            文件名 添加人：fengsz 添加时间：2014-6-26
	 */
	public void copyAndroidPackage(String fileName, HttpServletRequest request) {
		try {
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ("apk".equals(suffix.toLowerCase())) {
				String separator = File.separator;
				String dirPath = "gaga_files" + separator + "fileupload"
						+ separator + "upgradepackagefile" + separator;
				String realPath = request.getSession().getServletContext()
						.getRealPath("/");
				String realPath_tymp = realPath.substring(0,
						realPath.lastIndexOf(separator, realPath.length()));
				realPath = realPath_tymp.substring(
						0,
						realPath_tymp.lastIndexOf(separator,
								realPath_tymp.length()))
						+ separator;
				String saveDir = realPath + dirPath;
				System.out.println("saveDir:" + saveDir);
				File f = new File(saveDir + "ixin.apk");
				if (f.exists()) {
					f.delete();
				}
				File file2Server = new File(saveDir, fileName);
				FileInputStream fis = new FileInputStream(file2Server);
				BufferedInputStream bis = new BufferedInputStream(fis);
				FileOutputStream fos = new FileOutputStream(f);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				int d = -1;

				while ((d = bis.read()) != -1) {
					bos.write(d);
				}

				System.out.println("复制完毕");
				bos.close();
				bis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
