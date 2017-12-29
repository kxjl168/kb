package com.kxjl.web.blog.model;

import java.util.ArrayList;
import java.util.List;

import com.kxjl.web.system.model.base.BaseModel;

public class Replay extends BaseModel {
	private Integer recordid; // id

	/*
	  `recordid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `blogimei` varchar(256) DEFAULT NULL,
  `replay_recordid` int(11) DEFAULT '0' COMMENT '回复id号',
  `content` text,
  `userid` varchar(1000) DEFAULT NULL COMMENT 'yonghuid 串或者email',
  `create_date` datetime DEFAULT NULL,
	 */
	private String blogimei;
	private int replay_recordid;

	private String content;
	private String userid; //  'yonghuid 串或者email',
	private String user_blog; //  'yonghuid 串或者email',
	private String create_date;
	
	
	//query 回复
	private List<Replay> reback=new ArrayList<Replay>();
	private int ppid; //实际子分组
	private String tuid; //真实回复的uid
	private String tuser_blog; //真实回复的ublog
	

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public String getBlogimei() {
		return blogimei;
	}

	public void setBlogimei(String blogimei) {
		this.blogimei = blogimei;
	}

	public int getReplay_recordid() {
		return replay_recordid;
	}

	public void setReplay_recordid(int replay_recordid) {
		this.replay_recordid = replay_recordid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public List<Replay> getReback() {
		return reback;
	}

	public void setReback(List<Replay> reback) {
		this.reback = reback;
	}


	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid;
	}

	public int getPpid() {
		return ppid;
	}

	public void setPpid(int ppid) {
		this.ppid = ppid;
	}

	public String getUser_blog() {
		return user_blog;
	}

	public void setUser_blog(String user_blog) {
		this.user_blog = user_blog;
	}

	public String getTuser_blog() {
		return tuser_blog;
	}

	public void setTuser_blog(String tuser_blog) {
		this.tuser_blog = tuser_blog;
	}


	
	
	//query
	

	
}
