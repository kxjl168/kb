package com.kxjl.web.translog.model;

import com.kxjl.web.system.model.base.BaseModel;


public class Translog  extends BaseModel{
	private Integer	recordid;    		//id

	
/*	 `phone_account_use_id` varchar(32) DEFAULT NULL COMMENT '手机账号',
	  `phone_ip` varchar(64) DEFAULT NULL COMMENT '手机ip',
	  `device_id` varchar(256) DEFAULT NULL COMMENT '出口路由器id',
	  `device_ip` varchar(256) DEFAULT NULL COMMENT '出口路由器ip',
	  `city` varchar(64) DEFAULT NULL COMMENT '出口城市',
	  `time` varchar(64) DEFAULT NULL COMMENT '时间',*/
	
	private String  phone_account_use_id;      
	private String 	phone_ip;     	
	
	private String 	route_id;  
	private String 	route_ip;	//链接url
	private String 	city;  	//创建者
	private String 	time;	//创建时间
	public Integer getRecordid() {
		return recordid;
	}
	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}
	public String getPhone_account_use_id() {
		return phone_account_use_id;
	}
	public void setPhone_account_use_id(String phone_account_use_id) {
		this.phone_account_use_id = phone_account_use_id;
	}
	public String getPhone_ip() {
		return phone_ip;
	}
	public void setPhone_ip(String phone_ip) {
		this.phone_ip = phone_ip;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getRoute_ip() {
		return route_ip;
	}
	public void setRoute_ip(String route_ip) {
		this.route_ip = route_ip;
	}
	
	
	
	
}
