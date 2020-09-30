package com.kxjl.admin.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.CopyUtil;

public class KgEditClass  extends KgClass{
	private String oriId;

	  private String curUser;//当前查询用户

	
	public String getOriId() {
		return oriId;
	}

	public void setOriId(String oriId) {
		this.oriId = oriId;
	}

	public KgEditClass(){
		
	}
	
	public KgEditClass(KgClass group) {
	
		
		CopyUtil.fatherToChild(group,this);
		this.setOriId(group.getId());
		this.setVersionId(Constants.DEFULT_VERSION);

	}

	public String getCurUser() {
		return curUser;
	}

	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}
}