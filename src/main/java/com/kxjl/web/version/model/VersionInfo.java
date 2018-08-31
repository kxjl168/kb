package com.kxjl.web.version.model;

import java.util.Date;

/**
 * 版本信息实体类
 * @author kangyongji
 *
 */
public class VersionInfo {

	private int recordid; //主键
	private int upgradepackageid; //升级包编号
	private int clienttype;	//客户端类型
	private int isforceupgrade; //是否强制升级
	private int isallowall;	//是否全用户升级
	private double versionnum; //版本号
	private Date effectdate;  //升级包生效时间
	private String qrcodeurl;  //二维码URL
	private String creator;  //创建者
	private Date createdate;  //创建时间 
	private String updatedby;  //修改者
	private Date updateddate;  //修改时间
	private String annotation;  //备注
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public int getUpgradepackageid() {
		return upgradepackageid;
	}
	public void setUpgradepackageid(int upgradepackageid) {
		this.upgradepackageid = upgradepackageid;
	}
	public int getClienttype() {
		return clienttype;
	}
	public void setClienttype(int clienttype) {
		this.clienttype = clienttype;
	}
	public int getIsforceupgrade() {
		return isforceupgrade;
	}
	public void setIsforceupgrade(int isforceupgrade) {
		this.isforceupgrade = isforceupgrade;
	}
	public int getIsallowall() {
		return isallowall;
	}
	public void setIsallowall(int isallowall) {
		this.isallowall = isallowall;
	}
	public double getVersionnum() {
		return versionnum;
	}
	public void setVersionnum(double versionnum) {
		this.versionnum = versionnum;
	}
	public Date getEffectdate() {
		return effectdate;
	}
	public void setEffectdate(Date effectdate) {
		this.effectdate = effectdate;
	}
	public String getQrcodeurl() {
		return qrcodeurl;
	}
	public void setQrcodeurl(String qrcodeurl) {
		this.qrcodeurl = qrcodeurl;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public Date getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
}
