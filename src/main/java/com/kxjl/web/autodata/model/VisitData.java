package com.kxjl.web.autodata.model;

public class VisitData {
    private String datetime;

    private Integer userVisitNum;

    private Integer pageVisitNum;
    
    private Integer attackVisitNum;
    
    public Integer getAttackVisitNum() {
		return attackVisitNum;
	}

	public void setAttackVisitNum(Integer attackVisitNum) {
		this.attackVisitNum = attackVisitNum;
	}

	public Integer getAttackPageVisitNum() {
		return attackPageVisitNum;
	}

	public void setAttackPageVisitNum(Integer attackPageVisitNum) {
		this.attackPageVisitNum = attackPageVisitNum;
	}

	private Integer attackPageVisitNum;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime == null ? null : datetime.trim();
    }

    public Integer getUserVisitNum() {
        return userVisitNum;
    }

    public void setUserVisitNum(Integer userVisitNum) {
        this.userVisitNum = userVisitNum;
    }

    public Integer getPageVisitNum() {
        return pageVisitNum;
    }

    public void setPageVisitNum(Integer pageVisitNum) {
        this.pageVisitNum = pageVisitNum;
    }
}