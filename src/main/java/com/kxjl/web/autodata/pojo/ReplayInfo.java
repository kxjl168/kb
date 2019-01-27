package com.kxjl.web.autodata.pojo;

public class ReplayInfo {
    private Integer recordid;

    private String blogimei;

    private Integer replayRecordid;

    private String userid;

    private String createDate;

    private String userBlog;

    private Integer ppid;

    private String state;

    private String isvalid;

    private String email;

    private String ip;

    private String icon;

    private String content;
    
    //query
    
    private String title;

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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
        this.blogimei = blogimei == null ? null : blogimei.trim();
    }

    public Integer getReplayRecordid() {
        return replayRecordid;
    }

    public void setReplayRecordid(Integer replayRecordid) {
        this.replayRecordid = replayRecordid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getUserBlog() {
        return userBlog;
    }

    public void setUserBlog(String userBlog) {
        this.userBlog = userBlog == null ? null : userBlog.trim();
    }

    public Integer getPpid() {
        return ppid;
    }

    public void setPpid(Integer ppid) {
        this.ppid = ppid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}