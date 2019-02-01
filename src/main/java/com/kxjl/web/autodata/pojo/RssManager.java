package com.kxjl.web.autodata.pojo;

public class RssManager {
    private String id;

    private String name;

    private String link;

    private String icon;

    private String remark;

    private String autoRss;

    private String hasError;

    private String createDate;

    private String updateDate;

    private String lastRssDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAutoRss() {
        return autoRss;
    }

    public void setAutoRss(String autoRss) {
        this.autoRss = autoRss == null ? null : autoRss.trim();
    }

    public String getHasError() {
        return hasError;
    }

    public void setHasError(String hasError) {
        this.hasError = hasError == null ? null : hasError.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public String getLastRssDate() {
        return lastRssDate;
    }

    public void setLastRssDate(String lastRssDate) {
        this.lastRssDate = lastRssDate == null ? null : lastRssDate.trim();
    }
}