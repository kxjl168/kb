package com.kxjl.web.autodata.pojo;

public class LinkRelation {
    private String id;

    private String fromurl;

    private String tourl;
    
    //query
    private String fullurlquery;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFromurl() {
        return fromurl;
    }

    public void setFromurl(String fromurl) {
        this.fromurl = fromurl == null ? null : fromurl.trim();
    }

    public String getTourl() {
        return tourl;
    }

    public void setTourl(String tourl) {
        this.tourl = tourl == null ? null : tourl.trim();
    }

	public String getFullurlquery() {
		return fullurlquery;
	}

	public void setFullurlquery(String fullurlquery) {
		this.fullurlquery = fullurlquery;
	}
}