package com.kxjl.web.version.action;

import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.version.model.IncrementalUpgrade;
import com.kxjl.web.version.model.UpgradePackage;
import com.kxjl.web.version.model.UpgradeUser;
import com.kxjl.web.version.model.VersionInfo;
import com.kxjl.web.version.model.VersionNumFormatTool;
import com.kxjl.web.version.service.VersionInfoService;
//import com.zteict.web.user.model.UserBean;



public class VersionInfoHelper {

	//private UserBean userBean = new UserBean();
	private VersionInfoService vService=null;
	// 日志记录对象
	private Logger logger = Logger.getLogger(VersionInfoHelper.class);
	/**
	 * 升级号格式转换对象
	 */
	private VersionNumFormatTool vnft = new VersionNumFormatTool();
//	public VersionInfoHelper(VersionInfoService vService, UserBean userBean){
//		this.userBean = userBean;
//		this.vService = vService;
//	}
	
	public VersionInfoHelper(VersionInfoService vService){
		//this.userBean = userBean;
		this.vService = vService;
	}
	
	/**
	 * 处理传入的参数 ,检查是否需要升级
	 * @param reqData
	 * @return
	 * @throws JSONException
	 * @author zj
	 * @date 2015-12-18 下午12:22:19 *
	 */
	public JSONObject handleData(String reqData) throws JSONException {
		JSONObject inputData = new JSONObject(reqData); //输入参数
		JSONObject outData = new JSONObject(); //返回参数
		JSONObject errorData = new JSONObject();
		JSONObject successData = new JSONObject();
		logger.info("获得输入参数：" + inputData);
		String userId = inputData.optString("UserId");
		String clientType = inputData.optString("ClientType");
		String curVersion = inputData.optString("CurrentVer");
		String hasPackage = inputData.optString("HasPackage");
		//验证传入的数据

			//把版本转换成数字
			curVersion = format(curVersion);
			curVersion  = vnft.FormatTo10(curVersion);
			Double currVersion = Double.parseDouble(curVersion);
			logger.info("输入参数版本号转换为整数后：" + currVersion.intValue());
			//获得版本信息
			VersionInfo v = new VersionInfo();
			v.setClienttype(Integer.parseInt(clientType));
			v.setVersionnum(currVersion);
			List<VersionInfo> vs = vService.queryVersionInfo(v);
			if(vs!=null&&vs.size()!=0)
			{
				VersionInfo version=vs.get(0); // queryVersionInfo 降序，获取第一条最新版本 ，zj
			//for (VersionInfo version : vs) {
				if (version != null) {
					Double vNum = version.getVersionnum();
					//判断最新的版本号是否大于当前版本
					if (vNum > currVersion) {
						queryVersion(successData, errorData, userId, Integer.parseInt(clientType), Integer.parseInt(hasPackage),
								currVersion, version, vNum);
					}else if (vNum <= currVersion) {
						logger.info("该用户使用的是最新版本！");
						successData.put("ResponseCode", "200");
						successData.put("ResponseMsg", "该用户使用的是最新版本！");
						successData.put("NeedUpdate", "3");
						successData.put("VersionNum", "");
						successData.put("PackageName", "");
						successData.put("PackageUrl", "");
						successData.put("Md5", "");
						successData.put("Annotation", "");
					}
				}else {
					logger.info("数据版本信息为Null！");
					successData.put("ResponseCode", "200");
					successData.put("ResponseMsg", "该用户使用的是最新版本！");
					successData.put("NeedUpdate", "3");
					successData.put("VersionNum", "");
					successData.put("PackageName", "");
					successData.put("PackageUrl", "");
					successData.put("Md5", "");
					successData.put("Annotation", "");
				}
			}
			else
			{
				logger.info("未查询到更高版本，该用户使用的是最新版本！");
				successData.put("ResponseCode", "200");
				successData.put("ResponseMsg", "该用户使用的是最新版本！");
				successData.put("NeedUpdate", "3");
				successData.put("VersionNum", "");
				successData.put("PackageName", "");
				successData.put("PackageUrl", "");
				successData.put("Md5", "");
				successData.put("Annotation", "");
			}
			
		
	
		String res = successData.optString("ResponseCode");
		if (null != res && res.equals("200")) {
			logger.info("查询升级包成功！");
			logger.info("升级包信息：------------------------安装包路径"+successData.optString("PackageUrl"));
			logger.info("升级包信息：------------------------是否需要升级"+successData.optString("NeedUpdate"));
			logger.info("升级包信息：------------------------升级版本"+successData.optString("VersionNum"));
			outData = successData;
		}else {
			logger.info("查询升级包失败！");
			outData = errorData;
		}
		return outData;
	}
	
	/**
	 * 查询 是否需要升级 ，并返回版本信息
	 * @param successData
	 * @param errorData
	 * @param userId
	 * @param clientType
	 * @param hasPackage
	 * @param currVersion
	 * @param version
	 * @param vNum
	 * @throws JSONException
	 * @author zj
	 * @date 2015-12-18 下午12:23:03 *
	 */
	private void queryVersion(JSONObject successData, JSONObject errorData, String userId,
			int clientType, int hasPackage, Double currVersion,
			VersionInfo version, Double vNum) throws JSONException {
		
		//文件服务器外网地址
		String downPath=ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
		
		//判断是否在升级范围内
		boolean isUpgrade = checkUpgradeUser(version.getIsallowall(),version.getRecordid());
		UpgradePackage upgradePackage = vService.queryPackageInfo(version.getUpgradepackageid());
		if (isUpgrade) {
			if (hasPackage == 0) {
			
				if (upgradePackage == null) {
					errorData.put("ResponseCode", "201");
					errorData.put("ResponseMsg", "升级包数据异常！");
					errorData.put("NeedUpdate", "");
					errorData.put("VersionNum", "");
					errorData.put("PackageName", "");
					errorData.put("PackageUrl", "");
					errorData.put("Md5", "");
					errorData.put("Annotation", version.getAnnotation());
					logger.info("获取完整包数据异常！");
				}else {
					successData.put("ResponseCode", "200");
					successData.put("ResponseMsg", "查询版本信息成功！");
					successData.put("NeedUpdate", version.getIsforceupgrade() == 1 ? "1" : "2");
					successData.put("VersionNum", vnft.FormatToNomarlVersionNum(vNum.intValue()+".0"));
					successData.put("PackageName", upgradePackage.getUpgradepackagename());
					successData.put("PackageUrl",downPath+ upgradePackage.getUpgradepackagepath());
					successData.put("Md5", upgradePackage.getMd5());
					successData.put("Annotation", version.getAnnotation());
					logger.info("获得完整包：包名：" + upgradePackage.getUpgradepackagename() + ",路径：" + upgradePackage.getUpgradepackagepath());
				}
			}else if(hasPackage == 1){
				IncrementalUpgrade upgrade = new IncrementalUpgrade();
				upgrade.setClienttype(clientType);
				upgrade.setLowVersion(currVersion);
				upgrade.setCurrentVersion(vNum);
				IncrementalUpgrade incremental = vService.queryIncrementalPackage(upgrade);
				if (incremental == null) {
					errorData.put("ResponseCode", "201");
					errorData.put("ResponseMsg", "获得差异包数据异常！");
					errorData.put("NeedUpdate", "");
					errorData.put("VersionNum", "");
					errorData.put("PackageName", "");
					errorData.put("PackageUrl", "");
					errorData.put("Md5", "");
					errorData.put("Annotation", version.getAnnotation());
					logger.info("获得差异包数据异常！");
				}else {
					
				
					successData.put("ResponseCode", "200");
					successData.put("ResponseMsg", "查询版本信息成功！");
					successData.put("NeedUpdate", version.getIsforceupgrade() == 1 ? "1" : "2");
					successData.put("VersionNum", vnft.FormatToNomarlVersionNum(vNum.intValue()+".0"));
					successData.put("PackageName", upgradePackage.getUpgradepackagename());
					successData.put("PackageUrl",downPath+incremental.getDiffPath());
					successData.put("Md5", "");
					successData.put("Annotation", version.getAnnotation());
					logger.info("获得差异包：路径：" + incremental.getDiffPath());
				}
			}
		}else {
			logger.info("该用户不在升级范围内！");
			errorData.put("ResponseCode", "200");
			errorData.put("ResponseMsg", "该用户不在升级范围内！");
			errorData.put("NeedUpdate", "");
			errorData.put("VersionNum", "");
			errorData.put("PackageName", "");
			errorData.put("PackageUrl", "");
			errorData.put("Md5", "");
			errorData.put("Annotation", "");
		}
	}
	/**
	 * 验证用户是否在升级范围内
	 * @param isAll
	 * @param versionNum
	 * @param userId
	 * @return
	 */
	private boolean checkUpgradeUser(int isAll, int versionNum){
		if (isAll == 0) {
//			UpgradeUser upUser = new UpgradeUser();
//			upUser.setVersionId(versionNum);
//			//指定多用户或部门
//			List<UpgradeUser> upgradeUser = vService.queryUpgradeUser(upUser);
//			for (UpgradeUser user : upgradeUser) {
//				if (null != user) {
//					if (user.getIdType() == 2) {
//						if (user.getId().equals(userId)) {
//							return true;
//						}
//					}else if (user.getIdType() == 1) {
//						//判断是否为部门用户
//						Long department = userBean.getDepartmentid();
//						if(Long.parseLong(user.getId()) == department){
//							return true;
//						}
//					}
//				}else {
//					return false;
//				}
//			}
		}else if (isAll == 1){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 验证传入的参数
	 * @param outData
	 * @param userId
	 * @param clientType
	 * @param curVersion
	 * @param hasPackage
	 * @throws JSONException
	 */
	private void checkInputData(JSONObject outData, String userId,
			int clientType, String curVersion, int hasPackage)
			throws JSONException {
		//验证UserId字段
		if (null != userId && userId.isEmpty()) {
			outData.put("ResponseCode", 201);
			outData.put("ResponseMsg", "UserId字段不能为空！");
			outData.put("NeedUpdate", "");
			outData.put("VersionNum", "");
			outData.put("PackageName", "");
			outData.put("PackageUrl", "");
			outData.put("Md5", "");
			outData.put("Annotation", "");
		}
		//验证ClientType字段
		if (clientType != 1 && clientType != 2) {
			outData.put("ResponseCode", 201);
			outData.put("ResponseMsg", "ClientType字段输入有误！");
			outData.put("NeedUpdate", "");
			outData.put("VersionNum", "");
			outData.put("PackageName", "");
			outData.put("PackageUrl", "");
			outData.put("Md5", "");
			outData.put("Annotation", "");
		}
		//验证CurrentVer字段
		if (null != curVersion && curVersion.isEmpty()) {
			outData.put("ResponseCode", 201);
			outData.put("ResponseMsg", "CurrentVer字段不能为空！");
			outData.put("NeedUpdate", "");
			outData.put("VersionNum", "");
			outData.put("PackageName", "");
			outData.put("PackageUrl", "");
			outData.put("Md5", "");
			outData.put("Annotation", "");
		}
		//验证HasPackage字段
		if(hasPackage != 0 && hasPackage != 1){
			outData.put("ResponseCode", 201);
			outData.put("ResponseMsg", "HasPackage字段输入有误！");
			outData.put("NeedUpdate", "");
			outData.put("VersionNum", "");
			outData.put("PackageName", "");
			outData.put("PackageUrl", "");
			outData.put("Md5", "");
			outData.put("Annotation", "");
		}
	}
	
	/**
	 * 4位(补位)
	 * @param vnTemp
	 * @return
	 */
	public String format(String vnTemp) {
		if (vnTemp.replace('.', '@').split("@").length < 3) {
			String tempStr = "";
			for (int i = 0; i < (3 - vnTemp.replace('.', '@').split("@").length); i++) {
				tempStr += ".0";
			}
			vnTemp += tempStr;
		}
		return vnTemp;
	}
}
