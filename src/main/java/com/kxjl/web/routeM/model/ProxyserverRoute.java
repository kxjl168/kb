package com.kxjl.web.routeM.model;

import com.kxjl.web.system.model.base.BaseModel;

/***
 * 中转服务器路由器关系，路由分配情况
 * 
 * @param map
 * @return
 * @author zj
 * @date 2017-12-13
 */
public class ProxyserverRoute extends BaseModel {
	/**
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	
	
	 private String proxyserver_id ;//` varchar(128) NOT NULL COMMENT '服务器id',
	 private String city ;//` varchar(64) DEFAULT NULL COMMENT '出口城市',
	 private Integer online_route_num=0 ;//` int(11) DEFAULT '0' COMMENT '在线路由数',   从路由器表中查询
	 private Integer plan_route_num=0 ;//` int(11) DEFAULT '0' COMMENT '计划路由数',   根据需要连接的手机数量动态计算
	 private Integer phone_num=0;//连接到该中转服务器上，需要指定出口位置的手机数量
	 
	 //query
	 
	 private String proxyserver_ip;//关联的中转服务器ip
	 private String proxyserver_port;//关联的中转服务器port



	public String getProxyserver_id() {
		return proxyserver_id;
	}



	public void setProxyserver_id(String proxyserver_id) {
		this.proxyserver_id = proxyserver_id;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public Integer getOnline_route_num() {
		return online_route_num;
	}



	public void setOnline_route_num(Integer online_route_num) {
		this.online_route_num = online_route_num;
	}



	public Integer getPlan_route_num() {
		return plan_route_num;
	}



	public void setPlan_route_num(Integer plan_route_num) {
		this.plan_route_num = plan_route_num;
	}



	public Integer getPhone_num() {
		return phone_num;
	}



	public void setPhone_num(Integer phone_num) {
		this.phone_num = phone_num;
	}



	public String getProxyserver_ip() {
		return proxyserver_ip;
	}



	public void setProxyserver_ip(String proxyserver_ip) {
		this.proxyserver_ip = proxyserver_ip;
	}



	public String getProxyserver_port() {
		return proxyserver_port;
	}



	public void setProxyserver_port(String proxyserver_port) {
		this.proxyserver_port = proxyserver_port;
	}
	 
	  
	  
	  
}
