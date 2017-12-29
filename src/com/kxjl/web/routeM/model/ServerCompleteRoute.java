package com.kxjl.web.routeM.model;

/***
 * 已为中转服务器服务完成的路由器 ,连接次数已达上限
 * 
 * @param map
 * @return
 * @author zj
 * @date 2017-12-13
 */
public class ServerCompleteRoute {

	private String proxyserver_id;// ` varchar(128) NOT NULL COMMENT '服务器id',
	private String route_id;// ` varchar(64) DEFAULT NULL COMMENT '路由器id',
	private String time;// ` int(11) DEFAULT '0' COMMENT '结束时间',

	public String getProxyserver_id() {
		return proxyserver_id;
	}

	public void setProxyserver_id(String proxyserver_id) {
		this.proxyserver_id = proxyserver_id;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
