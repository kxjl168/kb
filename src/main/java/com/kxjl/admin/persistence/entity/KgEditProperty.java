package com.kxjl.admin.persistence.entity;

import java.util.Date;

import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.CopyUtil;

public class KgEditProperty  extends KgProperty{
	private String oriId;

	public String getOriId() {
		return oriId;
	}

	public void setOriId(String oriId) {
		this.oriId = oriId;
	}

	public KgEditProperty(){
		
	}
	
	public KgEditProperty(KgProperty group) {
	
		
		CopyUtil.fatherToChild(group,this);
		this.setOriId(group.getId());
		this.setVersionId(Constants.DEFULT_VERSION);

	}
}