package com.kxjl.web.autodata.model;

public class VisitData {
    private String datetime;

    private Integer userVisitNum;

    private String pageVisitNum;

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

    public String getPageVisitNum() {
        return pageVisitNum;
    }

    public void setPageVisitNum(String pageVisitNum) {
        this.pageVisitNum = pageVisitNum == null ? null : pageVisitNum.trim();
    }
}