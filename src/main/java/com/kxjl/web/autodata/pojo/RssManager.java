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
    
    private String lastRssPageDate;//文章最后更新日期
    
    private String page_link;
    
    public String getPage_link() {
		return page_link;
	}

	public void setPage_link(String page_link) {
		this.page_link = page_link;
	}

	private String mName;
    private String mRemark;
    
    private String rtype;//rss,atom
    
    public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	//query
    private String read;
    
    private String noread;
    
   


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

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getNoread() {
		return noread;
	}

	public void setNoread(String noread) {
		this.noread = noread;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmRemark() {
		return mRemark;
	}

	public void setmRemark(String mRemark) {
		this.mRemark = mRemark;
	}

	public String getLastRssPageDate() {
		return lastRssPageDate;
	}

	public void setLastRssPageDate(String lastRssPageDate) {
		this.lastRssPageDate = lastRssPageDate;
	}
}