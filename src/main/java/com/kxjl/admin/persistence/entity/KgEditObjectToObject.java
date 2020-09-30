package com.kxjl.admin.persistence.entity;

import java.util.Date;

import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.CopyUtil;

public class KgEditObjectToObject extends KgObjectToObject {
	private String oriId;
	
	
	
	
	
	private String editDataId;

	public String getOriId() {
		return oriId;
	}

	public void setOriId(String oriId) {
		this.oriId = oriId;
	}

	public KgEditObjectToObject(){
		
	}
	
	public KgEditObjectToObject(KgObjectToObject group) {
	
		
		CopyUtil.fatherToChild(group,this);
		this.setOriId(group.getId());
		this.setVersionId(Constants.DEFULT_VERSION);

	}

	public String getEditDataId() {
		return editDataId;
	}

	public void setEditDataId(String editDataId) {
		this.editDataId = editDataId;
	}
}