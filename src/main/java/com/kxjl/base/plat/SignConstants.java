package com.kxjl.base.plat;

public class SignConstants {
	public enum SignType {
		MD5, HMACSHA256, HMACSHA1
	}

	
	public static final String HMACSHA256 = "HMAC-SHA256";
	public static final String HMAC_SHA1 = "HMAC-SHA251";
	public static final String HMACSHA1 = "hmacsha1";
	
	
	public static final String MD5 = "MD5";

	public static final String FAIL = "FAIL";
	public static final String SUCCESS = "SUCCESS";

	public static final String FIELD_SIGN = "sign";
	public static final String FIELD_SIGN_TYPE = "sign_type";

	public static final String SDK_VERSION = "ZTGMSDK/1.0.0";
	
	public static final String FIELD_KEY="AccessKeyId";
	
	public static final String FIELD_RANDOM="random";
	
	
	public static final String FIELD_productKey="productKey";
	public static final String FIELD_deviceName="deviceName";
	public static final String FIELD_SDK_SINGTYPE="signMethod";
	
}
