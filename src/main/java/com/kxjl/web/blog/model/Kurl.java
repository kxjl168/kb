package com.kxjl.web.blog.model;

import java.util.ArrayList;
import java.util.List;

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
	private String val1;   //val1=1;普通 // val1=2 友情链接
	
	private String icon;//图标 相对路径
	private String isshow;// 是否显示 1：显示 ，， 0 不显示
	
	private String level;//level
	private String tags;//tag
	
	//query
	private String val2;
	private List<Kurl> childs=new ArrayList<>();

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public List<Kurl> getChilds() {
		return childs;
	}

	public void setChilds(List<Kurl> childs) {
		this.childs = childs;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}



}
