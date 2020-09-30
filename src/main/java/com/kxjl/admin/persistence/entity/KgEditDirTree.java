package com.kxjl.admin.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.CopyUtil;

public class KgEditDirTree  extends KgDirTree{
	private String oriId;

	public String getOriId() {
		return oriId;
	}

	public void setOriId(String oriId) {
		this.oriId = oriId;
	}

	public KgEditDirTree(){
		
	}
	
	public KgEditDirTree(KgDirTree group) {
	
		
		CopyUtil.fatherToChild(group,this);
		this.setOriId(group.getId());
		this.setVersionId(Constants.DEFULT_VERSION);

	}
}