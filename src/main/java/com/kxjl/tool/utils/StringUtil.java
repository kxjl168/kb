package com.kxjl.tool.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class StringUtil {

	public static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	/**
	 * String email = "someone@somewhere.com"; String hash = md5Hex(email);
	 * 
	 * @param message
	 * @return
	 * @author zj
	 * @date 2019年1月13日
	 */
	public static String md5Hex(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return hex(md.digest(message.getBytes("CP1252")));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	// 将字符串转为MD5
	public static String strToMd5(String str) {
		String md5Str = null;
		if (str != null && str.length() != 0) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				byte b[] = md.digest();
				int i;
				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				// 32位
				md5Str = buf.toString();
				// 16位
				// md5Str = buf.toString().substring(8, 24);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return md5Str;
	}

	/**
	 * 处理null字符串，返回非null
	 * 
	 * @param str
	 * @return
	 * @author zj
	 * @date 2015-12-17 下午5:02:24 *
	 */
	public static String getString(Object str) {
		if (str == null)
			return "";
		else
			return String.valueOf(str);
	}

	/**
	 * 生成在两数之间的随机数，包含两边
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @date 2016-3-29
	 * @author zj
	 */
	public static int random(Random r, int min, int max) {

		// System.err.println(min+"/"+max);
		int val = 0;
		do {

			val = r.nextInt(max + 1);
		} while (val < min);

		return val;
	}

	/**
	 * 判断是否为空串
	 * 
	 * @param str
	 * @return
	 * @date 2016-6-23
	 * @author zj
	 */
	public static Boolean isNull(String str) {
		if (str == null)
			return true;
		else {
			if (str.trim().equals(""))
				return true;
			else
				return false;
		}
	}

	/**
	 * 返回指定长度的随机字符串
	 * 
	 * @param length
	 * @return
	 * @date 2016-3-29
	 * @author zj
	 */
	public static String getRandomString(int length) {

		StringBuffer rst = new StringBuffer();
		String Strdate = String.valueOf(new Date().getTime());

		Strdate = Strdate.substring(5, Strdate.length() - 1);

		int[][] char_int = { { 48, 57 }, { 65, 90 }, { 97, 122 } }; // 0-9,A-Z,a-z
		Random r = new Random(Long.parseLong(Strdate));

		for (int i = 0; i < length; i++) {

			int index = r.nextInt(3);
			int vl = random(r, char_int[index][0], char_int[index][1]);
			// System.out.print(vl + ",");
			rst.append((char) vl);
		}
		// System.out.println("");

		return rst.toString();
	}

	public static void main(String[] args) throws InterruptedException {
		// for (int i = 0; i < 255; i++) {
		// System.err.println(i + "==========>" + (char) i);
		// }

		/*
		 * for (int i = 0; i < 10; i++) { System.out.println(getRandomString(16));
		 * Thread.currentThread().sleep(100); }
		 */

		System.out.println(strToMd5("kxjl168@foxmail.com"));

	}

}
