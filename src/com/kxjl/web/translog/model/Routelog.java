package com.kxjl.web.translog.model;

import com.kxjl.web.system.model.base.BaseModel;

public class Routelog extends BaseModel {

	private Integer recordid; // id

	private String proxyserver_id;
	private String routeid;
	private String ip;

	private String city;
	private String link_url; // 链接url
	private String onlinetime; // 创建者
	private String offlinetime; // 创建时间

	private String offlinetype;// '下线类型标记 0：正常下线 1:异常下线',

	private String offlinetypeStr;// '下线类型标记 0：正常下线 1:异常下线',

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
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

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	public String getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}

	public String getOfflinetime() {
		return offlinetime;
	}

	public void setOfflinetime(String offlinetime) {
		this.offlinetime = offlinetime;
	}

	public String getOfflinetype() {
		return offlinetype;
	}

	public void setOfflinetype(String offlinetype) {
		this.offlinetype = offlinetype;
		if (this.offlinetype.equals("0"))
			offlinetypeStr = "正常下线";
		else
			offlinetypeStr = "异常下线";
	}

	public String getProxyserver_id() {
		return proxyserver_id;
	}

	public void setProxyserver_id(String proxyserver_id) {
		this.proxyserver_id = proxyserver_id;
	}

}
