package com.kxjl.tool.utils;

public class MapResultUtil {

	public static final String no_login_code = "00";
	public static final String no_login_message = "no_login";
	public static final String no_user_code = "01";
	public static final String no_user_message = "no this user";

	public static final String success_code = "200";
	public static final String success_message = "success";
	public static final String fail_code = "201";
	public static final String fail_message = "fail";

	public static MapResult success() {
		MapResult rs = new MapResult();
		rs.setResponseCode(success_code);
		rs.setResponseMsg(success_message);
		rs.setDatalist(null);
		return rs;
	}

	public static MapResult success(String data, Integer num) {
		MapResult rs = new MapResult();
		rs.setResponseCode(success_code);

		rs.setResponseMsg(success_message);
		rs.setDatalist(data);
		rs.setTotal(num);
		return rs;
	}

	public static MapResult fail() {
		MapResult rs = new MapResult();
		rs.setResponseCode(fail_code);
		rs.setResponseMsg(fail_message);
		rs.setDatalist(null);
		return rs;
	}

	public static MapResult fail(String msg) {
		MapResult rs = new MapResult();
		rs.setResponseCode(fail_code);
		rs.setResponseMsg(msg);
		rs.setDatalist(null);
		return rs;

	}
}
