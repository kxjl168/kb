package com.kxjl.tool.common;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 * @author kangyongji
 *
 */
public class Md5Encrypt {
	
	public final static String MD5(String s) {
		return MD5(s, true);
	}
	
	/**
	 * 
	 * @param s
	 * @param upCase
	 *            是否大写字母
	 * @return
	 * @author zj
	 * @date 2018年6月19日
	 */
	public final static String MD5(String s, boolean upCase) {

		char DigitsA[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char Digitsa[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		char hexDigits[] = DigitsA;

		if (!upCase) {
			hexDigits = Digitsa;
		}
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5("72a4eb9f5b90451393cdc9c361920cf5特配版",false));
	}
}
