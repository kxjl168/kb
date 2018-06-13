package com.kxjl.web.version.model;

/**
 * �汾���������û�
 * @author kangyongji
 *
 */
public class UpgradeUser {

	private int recordId;  //���к�
	private int idType;  //ID����
	private String id;  //IDֵ
	private int versionId;  //�����İ汾ID
	private String idDesc;  //ID����
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	public String getIdDesc() {
		return idDesc;
	}
	public void setIdDesc(String idDesc) {
		this.idDesc = idDesc;
	}
}
