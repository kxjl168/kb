package com.kxjl.web.translog.model;

import com.kxjl.web.system.model.base.BaseModel;


public class Spiderlog  extends BaseModel{
	private Integer	recordid;    		//id

	

	private String  spider_head;      
	private String 	request_ip;     	
	
	private String 	request_city;  
	private String 	route_ip;	//链接url
	private String 	request_url;  	//创建者
	private String 	time;	//创建时间
	
	
	//query 
	private String starttime;
	private String endtime;
	private String dateFormat;
	
	public Integer getRecordid() {
		return recordid;
	}
	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}
	public String getSpider_head() {
		return spider_head;
	}
	public void setSpider_head(String spider_head) {
		this.spider_head = spider_head;
	}
	public String getRequest_ip() {
		return request_ip;
	}
	public void setRequest_ip(String request_ip) {
		this.request_ip = request_ip;
	}
	public String getRequest_city() {
		return request_city;
	}
	public void setRequest_city(String request_city) {
		this.request_city = request_city;
	}
	public String getRoute_ip() {
		return route_ip;
	}
	public void setRoute_ip(String route_ip) {
		this.route_ip = route_ip;
	}
	public String getRequest_url() {
		return request_url;
	}
	public void setRequest_url(String request_url) {
		this.request_url = request_url;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	
	
	
}
