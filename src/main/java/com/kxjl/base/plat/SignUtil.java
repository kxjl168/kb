package com.kxjl.base.plat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.base.plat.SignConstants.SignType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class SignUtil {

	private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final Random RANDOM = new SecureRandom();

	

	



	/**
	 * 判断签名是否正确，必须包含sign字段，否则返回false。使用MD5签名。
	 *
	 * @param data
	 *            Map类型数据
	 * @param key
	 *            API密钥
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
		return isSignatureValid(data, key, SignType.MD5);
	}

	/**
	 * 判断签名是否正确，必须包含sign字段，否则返回false。
	 *
	 * @param data
	 *            Map类型数据
	 * @param key
	 *            API密钥
	 * @param signType
	 *            签名方式
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
		if (!data.containsKey(SignConstants.FIELD_SIGN)) {
			return false;
		}
		String sign = data.get(SignConstants.FIELD_SIGN);
		String localSign = generateSignature(data, key, signType);
		 SignUtil.getLogger().info("[SignUtil] key:"+key+", localSign=" + localSign);
		// SignUtil.getLogger().debug("[SignUtil] key:"+key+", localSign=" + localSign);

		boolean rst= localSign.equals(sign);
		if(!rst)
		{
			System.out.println("[SignUtil] key:"+key+", localSign=" + localSign);
		}
		return rst;
	}

	/**
	 * 生成签名 默认HMACSHA256加密
	 *
	 * @param data
	 *            待签名数据
	 * @param key
	 *            API密钥
	 * @return 签名
	 */
	public static String generateSignature(final Map<String, String> data, String key) throws Exception {
		return generateSignature(data, key, SignType.HMACSHA256);
	}

	/**
	 * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
	 *
	 * @param data
	 *            待签名数据
	 * @param key
	 *            API密钥
	 * @param signType
	 *            签名方式
	 * @return 签名
	 */
	public static String generateSignature(final Map<String, String> data, String key, SignType signType)
			throws Exception {
		Set<String> keySet = data.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		for (String k : keyArray) {
			if (k.equals(SignConstants.FIELD_SIGN)) {
				continue;
			}
			if (k.equals(SignConstants.FIELD_SDK_SINGTYPE)) {
				continue;
			}

			/*
			 * if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
			 * sb.append(k).append("=").append(data.get(k).trim()).append("&");
			 */

			if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
			{
				try {
					//JSONObject.parse(data.get(k).trim());
					sb.append(k).append("").append(data.get(k).trim().replace("+", "%20") ).append("");
				} catch (Exception e) {
					//sb.append(k).append("").append(URLEncoder.encode(data.get(k).trim(),"utf-8").replace("+", "%20").replace("%3A", ":") ).append("");
				}
				
				
			//	sb.append(k).append("").append(URLEncoder.encode(data.get(k).trim(),"utf-8").replace("+", "%20") ).append("");
				
			}
		}

		// sb.append("key=").append(key);

		System.out.println("[SignUtil] signString=" + sb.toString());
	 //SignUtil.getLogger().info("[SignUtil] signString=" + sb.toString());
		//SignUtil.getLogger().debug("[SignUtil] signString=" + sb.toString());

		if (SignType.MD5.equals(signType)) {
			return MD5(sb.toString()).toUpperCase();
		} else if (SignType.HMACSHA256.equals(signType)) {
			return HMACSHA256(sb.toString(), key);
		} else if (SignType.HMACSHA1.equals(signType)) {
			return HMACSHA1(sb.toString(), key);
		} else {
			throw new Exception(String.format("Invalid sign_type: %s", signType));
		}
	}

	/**
	 * 获取随机字符串 Nonce Str
	 *
	 * @return String 随机字符串
	 */
	public static String generateNonceStr() {
		char[] nonceChars = new char[32];
		for (int index = 0; index < nonceChars.length; ++index) {
			nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
		}
		return new String(nonceChars);
	}

	/**
	 * 生成 MD5
	 *
	 * @param data
	 *            待处理数据
	 * @return MD5结果
	 */
	public static String MD5(String data) throws Exception {
		java.security.MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 生成 HMACSHA256
	 * 
	 * @param data
	 *            待处理数据
	 * @param key
	 *            密钥
	 * @return 加密结果
	 * @throws Exception
	 */
	public static String HMACSHA256(String data, String key) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 生成 HMACSHA256
	 * 
	 * @param data
	 *            待处理数据
	 * @param key
	 *            密钥
	 * @return 加密结果
	 * @throws Exception
	 */
	public static String HMACSHA1(String data, String key) throws Exception {
		Mac sha1_HMAC = Mac.getInstance("HmacSHA1");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
		sha1_HMAC.init(secret_key);
		byte[] array = sha1_HMAC.doFinal(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 日志
	 * 
	 * @return
	 */
	public static Logger getLogger() {
		Logger logger = LoggerFactory.getLogger("signUtil");
		return logger;
	}

	/**
	 * 获取当前时间戳，单位秒
	 * 
	 * @return
	 */
	public static long getCurrentTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 获取当前时间戳，单位毫秒
	 * 
	 * @return
	 */
	public static long getCurrentTimestampMs() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {

		//AccessKeyIdtestzzconfiguration%5B%7B%22id%22%3A%22cddc86db-e322-4a0c-8551-f6bd904f3736%22%2C%22ruleType%22%3A%7B%22function%22%3A%22%E5%8F%91%E5%B8%83%E5%88%B0%E6%97%B6%E5%BA%8F%E6%95%B0%E6%8D%AE%E5%BA%93%22%2C%22id%22%3A%22InfluxDb%22%7D%2C%22dbdata%22%3A%7B%22id%22%3A%221%22%2C%22dbId%22%3A%22TESTDB%22%2C%22dbName%22%3A%22%E6%B5%8B%E8%AF%95InfuxDB%22%2C%22createTime%22%3A%222020-03-18%2009%3A23%3A06.0%22%2C%22dtype%22%3A%221%22%2C%22dburl%22%3A%22http%3A%2F%2F192.168.100.81%3A8086%22%2C%22dbbase%22%3A%22log_management%22%2C%22dbuser%22%3A%22username%22%2C%22dbpass%22%3A%22pass%22%2C%22dbtag%22%3A%22password%22%2C%22companyId%22%3A%220%22%7D%7D%5Ddevicename%2Bid426cbf1987f44bb7ba57e666276e0559nameyanshiproductKey6b87fa2b9a2e4b21848c216b02fbde43randomhz75j4z1i8rtype1ruleDesc1111selectdata*sign_typeHMAC-SHA256topic%2Fdev%2F6b87fa2b9a2e4b21848c216b02fbde43%2F%2B%2Fcore%2Fservice%2Fposttopic_function%E7%89%A9%E6%A8%A1%E5%9E%8B%E6%95%B0%E6%8D%AE%E4%B8%8A%E6%8A%A5topic_ori_path%2Fdev%2F%24%7BProductKey%7D%2F%24%7BdeviceName%7D%2Fcore%2Fservice%2Fpost
	//	AccessKeyIdtestzzconfiguration%5B%7B%22id%22%3A%22cddc86db-e322-4a0c-8551-f6bd904f3736%22%2C%22ruleType%22%3A%7B%22function%22%3A%22%E5%8F%91%E5%B8%83%E5%88%B0%E6%97%B6%E5%BA%8F%E6%95%B0%E6%8D%AE%E5%BA%93%22%2C%22id%22%3A%22InfluxDb%22%7D%2C%22dbdata%22%3A%7B%22id%22%3A%221%22%2C%22dbId%22%3A%22TESTDB%22%2C%22dbName%22%3A%22%E6%B5%8B%E8%AF%95InfuxDB%22%2C%22createTime%22%3A%222020-03-18+09%3A23%3A06.0%22%2C%22dtype%22%3A%221%22%2C%22dburl%22%3A%22http%3A%2F%2F192.168.100.81%3A8086%22%2C%22dbbase%22%3A%22log_management%22%2C%22dbuser%22%3A%22username%22%2C%22dbpass%22%3A%22pass%22%2C%22dbtag%22%3A%22password%22%2C%22companyId%22%3A%220%22%7D%7D%5Ddevicename%2Bid426cbf1987f44bb7ba57e666276e0559nameyanshiproductKey6b87fa2b9a2e4b21848c216b02fbde43randomhz75j4z1i8rtype1ruleDesc1111selectdata*sign_typeHMAC-SHA256topic%2Fdev%2F6b87fa2b9a2e4b21848c216b02fbde43%2F%2B%2Fcore%2Fservice%2Fposttopic_function%E7%89%A9%E6%A8%A1%E5%9E%8B%E6%95%B0%E6%8D%AE%E4%B8%8A%E6%8A%A5topic_ori_path%2Fdev%2F%24%7BProductKey%7D%2F%24%7BdeviceName%7D%2Fcore%2Fservice%2Fpost
		try {
			String s = HMACSHA256(
					"AccessKeyIdtestzzconfiguration%5B%7B%22id%22%3A%22cddc86db-e322-4a0c-8551-f6bd904f3736%22%2C%22ruleType%22%3A%7B%22function%22%3A%22%E5%8F%91%E5%B8%83%E5%88%B0%E6%97%B6%E5%BA%8F%E6%95%B0%E6%8D%AE%E5%BA%93%22%2C%22id%22%3A%22InfluxDb%22%7D%2C%22dbdata%22%3A%7B%22id%22%3A%221%22%2C%22dbId%22%3A%22TESTDB%22%2C%22dbName%22%3A%22%E6%B5%8B%E8%AF%95InfuxDB%22%2C%22createTime%22%3A%222020-03-18%2009%3A23%3A06.0%22%2C%22dtype%22%3A%221%22%2C%22dburl%22%3A%22http%3A%2F%2F192.168.100.81%3A8086%22%2C%22dbbase%22%3A%22log_management%22%2C%22dbuser%22%3A%22username%22%2C%22dbpass%22%3A%22pass%22%2C%22dbtag%22%3A%22password%22%2C%22companyId%22%3A%220%22%7D%7D%5Ddevicename%2Bid426cbf1987f44bb7ba57e666276e0559nameyanshiproductKey6b87fa2b9a2e4b21848c216b02fbde43randomhz75j4z1i8rtype1ruleDesc1111selectdata*sign_typeHMAC-SHA256topic%2Fdev%2F6b87fa2b9a2e4b21848c216b02fbde43%2F%2B%2Fcore%2Fservice%2Fposttopic_function%E7%89%A9%E6%A8%A1%E5%9E%8B%E6%95%B0%E6%8D%AE%E4%B8%8A%E6%8A%A5topic_ori_path%2Fdev%2F%24%7BProductKey%7D%2F%24%7BdeviceName%7D%2Fcore%2Fservice%2Fpost",
					"123456");
			System.out.println(s);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (true)
			return;

		try {
			// BCCE73216BCC9016C5887DC497B56948DE46D3A28CC023FA49D7C2F6F081CD45
			System.out.println(HMACSHA256(
					"AccessKeyIdtestcustomerId测试消费组1newsType1,3productName水浸传感器random912j9wafswtsign_typeHMAC-SHA256typeAMQP",
					"922233ce10fa47a1af8491d2fbd20ac6"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map<String, String> mdata = new HashMap<String, String>();
		mdata.put("user", "admin");
		mdata.put("password", "123456");
		// mdata.put(SignConstants.FIELD_SIGN_TYPE, SignConstants.HMACSHA256);
		//// String data="user=admin&password=123456";
		String key = "test";
		try {
			String loginKey2 = generateSignature(mdata, key, SignType.HMACSHA256);
			System.out.println(loginKey2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
