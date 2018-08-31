package com.kxjl.tool.utils;

public class MapResult {

    private String ResponseCode;
    private String ResponseMsg;
    private Integer total;
    private Object datalist;
    

    public MapResult(){ }

    public MapResult(String code, String message, Object data,Integer num) {
        this.ResponseCode = code;
        this.ResponseMsg = message;
        this.datalist = data;
        this.total=num;
    }

	public String getResponseCode() {
		return ResponseCode;
	}

	public void setResponseCode(String responseCode) {
		ResponseCode = responseCode;
	}

	public String getResponseMsg() {
		return ResponseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		ResponseMsg = responseMsg;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getDatalist() {
		return datalist;
	}

	public void setDatalist(Object datalist) {
		this.datalist = datalist;
	}


}
