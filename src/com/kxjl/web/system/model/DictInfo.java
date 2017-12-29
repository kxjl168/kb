package com.kxjl.web.system.model;

import com.kxjl.web.system.model.base.BaseModel;

/**
 * 系统字典表
 * @date 2016-7-25
 * @author zj
 *
 */
public class DictInfo extends BaseModel {
	
	public static String  blog_type_str="blog_type";
	
	
	private Integer id ;// int(11) NOT NULL,
	private String dict_type ;//` varchar(50) DEFAULT NULL COMMENT '字典类型',
	private String dict_key ;// varchar(50) DEFAULT NULL COMMENT '字典key',
	private String dict_name  ;// varchar(100) DEFAULT NULL COMMENT '字典value',
	private String sort  ;// int(11) DEFAULT NULL COMMENT '字典排序',
	
	private String desc_info;
	private String val1;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDict_type() {
		return dict_type;
	}
	public void setDict_type(String dict_type) {
		this.dict_type = dict_type;
	}
	public String getDict_key() {
		return dict_key;
	}
	public void setDict_key(String dict_key) {
		this.dict_key = dict_key;
	}
	public String getDict_name() {
		return dict_name;
	}
	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}

	public String getVal1() {
		return val1;
	}
	public void setVal1(String val1) {
		this.val1 = val1;
	}
	public String getDesc_info() {
		return desc_info;
	}
	public void setDesc_info(String desc_info) {
		this.desc_info = desc_info;
	}
	
	
}
