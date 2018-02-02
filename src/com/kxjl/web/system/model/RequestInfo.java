package com.kxjl.web.system.model;

import com.kxjl.web.system.model.base.BaseModel;

/**
 * 请求数记录
 * 
 * @param map
 * @return
 * @author zj
 * @date 2017-12-27
 */
public class RequestInfo extends BaseModel {

	/*
	 * `id` int(11) NOT NULL AUTO_INCREMENT, `sessionid` varchar(30) DEFAULT
	 * NULL, `uri` varchar(30) DEFAULT NULL, `createTime` varchar(30) DEFAULT
	 * NULL,
	 */

	private Integer id;// int(11) NOT NULL,
	private String ip;;// ` varchar(30) DEFAULT NULL ,
	private String city;// ` varchar(30) DEFAULT NULL ,
	private String sessionid;// ` varchar(50) DEFAULT NULL COMMENT '字典类型',
	private String uri;// varchar(50) DEFAULT NULL COMMENT '字典key',
	private String createTime;// varchar(100) DEFAULT NULL COMMENT '字典value',
	private String action_type;
	private String val1; //
	
	
	//query 查询时间间隔 秒
	private Integer sec=10;//  10s只能请求X次
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public Integer getSec() {
		return sec;
	}

	public void setSec(Integer sec) {
		this.sec = sec;
	}

	public String getVal1() {
		return val1;
	}

	public void setVal1(String val1) {
		this.val1 = val1;
	}

}
