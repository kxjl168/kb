package com.kxjl.web.version.model;

import java.util.Date;

/**
 * 版本升级页面实体类
 * @author kangyongji
 *
 */
public class UpgradePackageVersionInfo {

	private int recordid;	//记录Id
	private String upgradepackageName; //安装包名
	private String upgradepackagePath; //安装包路径
	private int clientType;	//客户端版本
	private int isforceupgrade; //是否强制升级
	private String annotation;	//备注
	private Date effectdate; //生效日期
	private Double versionNum; //版本号
	private String isforceupgradeStr;
	private String effectdateStr;
	private String clientTypeStr;
	private String versionNumStr;
	private int pageCount;//每页多少条数据
	private int currentPage;//当前第几页
	private int allPage;//共几页
	private String updateDepart; //升级部门
	private String updateDepartName;//升级部门名称
	private String md5;//Md5
	private String qrCodePath;//二维码路径
	
	public String getQrCodePath() {
		return qrCodePath;
	}
	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}
	public String getUpdateDepartName() {
		return updateDepartName;
	}
	public void setUpdateDepartName(String updateDepartName) {
		this.updateDepartName = updateDepartName;
	}
	public String getUpdateDepart() {
		return updateDepart;
	}
	public void setUpdateDepart(String updateDepart) {
		this.updateDepart = updateDepart;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getUpgradepackageName() {
		return upgradepackageName;
	}
	public void setUpgradepackageName(String upgradepackageName) {
		this.upgradepackageName = upgradepackageName;
	}
	public String getUpgradepackagePath() {
		return upgradepackagePath;
	}
	public void setUpgradepackagePath(String upgradepackagePath) {
		this.upgradepackagePath = upgradepackagePath;
	}
	public int getClientType() {
		return clientType;
	}
	public void setClientType(int clientType) {
		this.clientType = clientType;
	}
	public int getIsforceupgrade() {
		return isforceupgrade;
	}
	public void setIsforceupgrade(int isforceupgrade) {
		this.isforceupgrade = isforceupgrade;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public Date getEffectdate() {
		return effectdate;
	}
	public void setEffectdate(Date effectdate) {
		this.effectdate = effectdate;
	}
	public Double getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Double versionNum) {
		this.versionNum = versionNum;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	public String getIsforceupgradeStr() {
		return isforceupgradeStr;
	}
	public void setIsforceupgradeStr(String isforceupgradeStr) {
		this.isforceupgradeStr = isforceupgradeStr;
	}
	public String getEffectdateStr() {
		return effectdateStr;
	}
	public void setEffectdateStr(String effectdateStr) {
		this.effectdateStr = effectdateStr;
	}
	public String getClientTypeStr() {
		return clientTypeStr;
	}
	public void setClientTypeStr(String clientTypeStr) {
		this.clientTypeStr = clientTypeStr;
	}
	public String getVersionNumStr() {
		return versionNumStr;
	}
	public void setVersionNumStr(String versionNumStr) {
		this.versionNumStr = versionNumStr;
	}
}
