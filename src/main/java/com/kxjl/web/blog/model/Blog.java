package com.kxjl.web.blog.model;

import java.util.List;

import com.kxjl.tool.utils.DateUtil;
import com.kxjl.web.blog.action.Kdata;
import com.kxjl.web.device.model.Device.RouteFlag;
import com.kxjl.web.system.model.base.BaseModel;

public class Blog extends BaseModel {

	private Integer recordid; // id

	/*
	 * `recordid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号', `title`
	 * varchar(256) DEFAULT NULL, `blog_type` int(11) DEFAULT '0' COMMENT '分类',
	 * `content` text, `view_nums` int(11) DEFAULT '0' COMMENT '查看次数', `replay_nums`
	 * int(11) DEFAULT NULL COMMENT '评论次数', `create_date` datetime DEFAULT NULL,
	 * `update_date` datetime DEFAULT NULL,
	 */
	private String title;
	private String blog_type;

	private String content;
	private Integer view_nums; // 链接url
	private Integer replay_nums; // 创建者

	private String create_date;
	private String update_date;
	private String tags;
	private String imei;

	private String showflag; //

	private String[] tagStrs;

	// query

	// 静态链接日期前缀url
	private String showdate;// /2018/01/ .html

	private String blog_type_name;
	private String blog_type_url;
	private String month;
	private String showdesc;

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBlog_type() {
		return blog_type;
	}

	public void setBlog_type(String blog_type) {
		this.blog_type = blog_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getView_nums() {
		return view_nums;
	}

	public void setView_nums(Integer view_nums) {
		this.view_nums = view_nums;
	}

	public Integer getReplay_nums() {
		return replay_nums;
	}

	public void setReplay_nums(Integer replay_nums) {
		this.replay_nums = replay_nums;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = DateUtil.getDateStr(DateUtil.getDate(create_date, DateUtil.defaultFormat),
				DateUtil.defaultFormat);

		if (this.update_date != null)
			this.update_date = DateUtil.getDateStr(DateUtil.getDate(update_date, DateUtil.defaultFormat),
					DateUtil.defaultFormat);
		else
			this.update_date = DateUtil.getDateStr(DateUtil.getDate(create_date, DateUtil.defaultFormat),
					DateUtil.defaultFormat);

		String showdate = DateUtil.getDateStr(DateUtil.getDate(this.getCreate_date(), "yyyy-MM-dd"), "yyyy/MM");
		this.showdate = showdate;

		String month = DateUtil.getDateStr(DateUtil.getDate(this.getCreate_date(), "yyyy-MM-dd"), "yyyy-MM");
		this.month = month;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		if (this.update_date != null)
			this.update_date = DateUtil.getDateStr(DateUtil.getDate(update_date, DateUtil.defaultFormat),
					DateUtil.defaultFormat);
		else
			this.update_date = DateUtil.getDateStr(DateUtil.getDate(create_date, DateUtil.defaultFormat),
					DateUtil.defaultFormat);
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tag) {
		this.tags = tag;
		if (tags != null && !tags.equals("")) {
			tagStrs = tags.split(",");
		}
	}

	public String getBlog_type_name() {
		return blog_type_name;
	}

	public void setBlog_type_name(String blog_type_name) {
		this.blog_type_name = blog_type_name;
	}

	public String[] getTagStrs() {
		return tagStrs;
	}

	public void setTagStrs(String[] tagStrs) {
		this.tagStrs = tagStrs;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;

	}

	public String getBlog_type_url() {
		return blog_type_url;
	}

	public void setBlog_type_url(String blog_type_url) {
		this.blog_type_url = blog_type_url;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getShowdesc() {
		return showdesc;
	}

	public void setShowdesc(String showdesc) {
		this.showdesc = showdesc;

	}

	public String getShowflag() {
		return showflag;
	}

	public void setShowflag(String showflag) {
		this.showflag = showflag;
		this.showdesc = Kdata.Enable.parse(showflag).desc;
	}

	public String getShowdate() {
		return showdate;
	}

	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}

}
