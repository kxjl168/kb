package com.kxjl.web.device.model;

import com.kxjl.web.system.model.base.BaseModel;

/**
 * 路由器设备
 * 
 * @param map
 * @return
 * @author zj
 * @date 2017-12-12
 */
public class Device extends BaseModel {

	
	
	/**
	 * 路由器分配标示
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public enum RouteFlag {

		NIL("",""),OFFLINE("-1","不在线"),	UNASSIGN("0","未分配"), ASSIGN_UNCONN("1","已分配暂未连接中转服务器"), CONNECTED("2","已连接中转服务器");

		private String value = "";
		private String desc = "";

		private RouteFlag(String val,String desc) {
			this.value = val;
			this.desc=desc;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return value.toString();
		}
		
		public  static RouteFlag parse(String val)
		{
			for (RouteFlag item : RouteFlag.values()) {
				if(item.value.equals(val))
					return item;
			}
			return RouteFlag.NIL;
		}

	}
	
	/**
	 * 路由器状态是否空闲
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public enum RouteFreeStaus {

		NIL("",""),Free("0","空闲"), UNFree("1","不空闲");

		private String value = "";
		private String desc = "";

		private RouteFreeStaus(String val,String desc) {
			this.value = val;
			this.desc=desc;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return value.toString();
		}
		
		public static RouteFreeStaus parse(String val)
		{
			for (RouteFreeStaus item : RouteFreeStaus.values()) {
				if(item.value.equals(val))
					return item;
			}
			return RouteFreeStaus.NIL;
		}

	}

	private Integer recordid; // id

	private String routeid;
	private String ip;

	private String city;
	private String link_url; // 链接url
	private String onlinetime; // 创建者
	private String offlinetime; // 创建时间

	private String flag;// varchar(32) DEFAULT '0' COMMENT '分配标记 0:未分配 1:已分配未连接
						// 、2:已连接中转服务器 ',
	private String free;// varchar(32) DEFAULT '0' COMMENT '是否空闲 0：空闲 1：不空闲',
	private String assigntime;// varchar(64) DEFAULT NULL COMMENT
								// '服务器分配中转服务器的时间，',

	private String proxyserver_id;// 已分配的中转服务器id

	
	//query
	private String showall; // 为true 查询全部；否则 过滤offline为空的
	private Integer timeoutMin; // 分配超时时间 （分钟）

	private String flagtype;
	private String freetype;
	
	public String getFlagtype()
	{
		return RouteFlag.parse(flag).desc;
	}
	public String getFreetype()
	{
		return RouteFreeStaus.parse(flag).desc;
	}
	

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
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

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getShowall() {
		return showall;
	}

	public void setShowall(String showall) {
		this.showall = showall;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
		this.flagtype= RouteFlag.parse(flag).desc;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
		this.freetype= RouteFreeStaus.parse(free).desc;
	}

	public String getAssigntime() {
		return assigntime;
	}

	public void setAssigntime(String assigntime) {
		this.assigntime = assigntime;
	}

	public String getProxyserver_id() {
		return proxyserver_id;
	}

	public void setProxyserver_id(String proxyserver_id) {
		this.proxyserver_id = proxyserver_id;
	}

	public Integer getTimeoutMin() {
		return timeoutMin;
	}

	public void setTimeoutMin(Integer timeoutMin) {
		this.timeoutMin = timeoutMin;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+routeid+"/city:"+city+" flag:"+flag+"/ free:"+free;
	}

}
