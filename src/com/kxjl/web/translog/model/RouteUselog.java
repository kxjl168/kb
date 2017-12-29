package com.kxjl.web.translog.model;

import com.kxjl.web.system.model.base.BaseModel;

public class RouteUselog extends BaseModel {

	private int recordid;
	private String routeid;
	private String phoneip;

	private String time;

	/*
	 * `recordid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号', `routeid`
	 * varchar(32) NOT NULL COMMENT '路由器id', `phoneip` varchar(128) DEFAULT NULL
	 * COMMENT '手机ip', `time` varchar(64) DEFAULT NULL COMMENT '时间',
	 */

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getRecordid() {
		return recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getPhoneip() {
		return phoneip;
	}

	public void setPhoneip(String phoneip) {
		this.phoneip = phoneip;
	}

}
