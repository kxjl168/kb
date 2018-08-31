package com.kxjl.web.translog.model;

import com.kxjl.web.system.model.base.BaseModel;

public class RouteCompletelog extends BaseModel {

	private String routeid;
	private String proxyserver_id;

	private String time;
	
	
	//query
	private String  proxyserver_ip;
	

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getProxyserver_id() {
		return proxyserver_id;
	}

	public void setProxyserver_id(String proxyserver_id) {
		this.proxyserver_id = proxyserver_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProxyserver_ip() {
		return proxyserver_ip;
	}

	public void setProxyserver_ip(String proxyserver_ip) {
		this.proxyserver_ip = proxyserver_ip;
	}

}
