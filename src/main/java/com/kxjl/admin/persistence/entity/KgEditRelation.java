package com.kxjl.admin.persistence.entity;

import java.util.Date;

import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.CopyUtil;

public class KgEditRelation extends KgRelation {
	private String oriId;

	public String getOriId() {
		return oriId;
	}

	public void setOriId(String oriId) {
		this.oriId = oriId;
	}

	public KgEditRelation(){
		
	}
	
	public KgEditRelation(KgRelation group) {
	
		
		CopyUtil.fatherToChild(group,this);
		this.setOriId(group.getId());
		this.setVersionId(Constants.DEFULT_VERSION);

	}
}