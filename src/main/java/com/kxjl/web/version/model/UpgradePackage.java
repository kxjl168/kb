package com.kxjl.web.version.model;

import java.util.Date;

/**
 * �汾��װ����Ϣ
 * @author kangyongji
 *
 */
public class UpgradePackage {

	private int recordid;  //���������
	private String upgradepackagename;  //����������
	private String upgradepackagepath;  //������·��
	private String md5;  //MD5
	private String annotation; //��ע
	private String createby;  //������
	private Date createdate;  //��������
	private String updatedby;  //�޸���
	private Date updateddate;  //�޸�����
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getUpgradepackagename() {
		return upgradepackagename;
	}
	public void setUpgradepackagename(String upgradepackagename) {
		this.upgradepackagename = upgradepackagename;
	}
	public String getUpgradepackagepath() {
		return upgradepackagepath;
	}
	public void setUpgradepackagepath(String upgradepackagepath) {
		this.upgradepackagepath = upgradepackagepath;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
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
}
