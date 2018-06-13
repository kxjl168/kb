package com.kxjl.web.proxyserver.model;

import com.kxjl.web.system.model.base.BaseModel;

/**
 * 代理服务器
 * 
 * @param map
 * @return
 * @author zj
 * @date 2017-12-12
 */
public class Proxyserver extends BaseModel {
	private Integer recordid; // id

	/*
	 * `recordid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号', `id`
	 * varchar(32) NOT NULL COMMENT 'id', `ip` varchar(128) DEFAULT NULL COMMENT
	 * 'ip', `port` varchar(32) DEFAULT NULL COMMENT '端口', `maxphones`
	 * varchar(64) DEFAULT NULL COMMENT '可分配最大手机数', `assignphones` varchar(64)
	 * DEFAULT NULL COMMENT '已分配手机数',
	 */

	private String id;
	private String ip;

	private String port;
	private String maxphones; // 链接url
	private String assignphones; // 创建者

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getMaxphones() {
		return maxphones;
	}

	public void setMaxphones(String maxphones) {
		this.maxphones = maxphones;
	}

	public String getAssignphones() {
		return assignphones;
	}

	public void setAssignphones(String assignphones) {
		this.assignphones = assignphones;
	}

}
