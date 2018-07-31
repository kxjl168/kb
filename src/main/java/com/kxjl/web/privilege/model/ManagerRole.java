package com.kxjl.web.privilege.model;

import com.kxjl.web.system.model.base.BaseModel;

/**
 * 后台管理员-角色
 * @date 2016-10-14
 * @author zj
 * 
 */
public class ManagerRole extends BaseModel {

	private String role_id; // 标题
	private String manager_id;
	
	private String user_type;//用户类型，1：后台管理员 2：普通用户
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	


}
