package com.kxjl.web.blog.model;

import com.kxjl.web.system.model.base.BaseModel;

/**
 * 链接管理
 * 
 * @param map
 * @return
 * @author zj
 * @date 2018-1-24
 */
public class Kurl extends BaseModel {

	private Integer id;// int(11) NOT NULL,
	private String url_type;// ` varchar(50) DEFAULT NULL COMMENT '字典类型',
	private String url_val;// varchar(50) DEFAULT NULL COMMENT '字典key',
	private String url_name;// varchar(100) DEFAULT NULL COMMENT '字典value',
	private String sort;// int(11) DEFAULT NULL COMMENT '字典排序',

	private String desc_info;
	private String val1;
	
	//query
	private String val2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl_type() {
		return url_type;
	}

	public void setUrl_type(String url_type) {
		this.url_type = url_type;
	}

	public String getUrl_val() {
		return url_val;
	}

	public void setUrl_val(String url_val) {
		this.url_val = url_val;
	}

	public String getUrl_name() {
		return url_name;
	}

	public void setUrl_name(String url_name) {
		this.url_name = url_name;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDesc_info() {
		return desc_info;
	}

	public void setDesc_info(String desc_info) {
		this.desc_info = desc_info;
	}

	public String getVal1() {
		return val1;
	}

	public void setVal1(String val1) {
		this.val1 = val1;
	}

	public String getVal2() {
		return val2;
	}

	public void setVal2(String val2) {
		this.val2 = val2;
	}

}
