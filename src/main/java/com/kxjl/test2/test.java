package com.kxjl.test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.json.JSONObject;

import com.kxjl.tool.httpPost.HttpSendPost;
import com.kxjl.tool.httpPost.SendPostRequest;
import com.kxjl.tool.utils.JEscape;

public class test {

	public static void main(String args[]) {
		// testLogin();

		// testLoginOut();

		// testRegister();

		// testPatter();

		// update();

		// testcity();

		// replaytest();

		// testGzip();

		// testhtml();

		// update2();

		// testl();

		// testlog4j();

		// avtest();

		// deltest();

		// testprint();

		// posttest();

		// replaytest();

		// update();

		// replaydeltest();

		// moneytest();

		testicon();
	}

	private static void moneytest() {

		String url = "http://127.0.0.1:8081/kb/manager/money/moneyList";

		// String url =
		// "http://10.204.37.192:8080/gserver/version/getVersionInfo.action?type=2";

		String data = "";

		String responsedata = "";
		try {

			JSONObject j = new JSONObject();
			j.put("id", "111");
			j.put("state", "1");

			int num = 2;
			for (int i = 0; i < num; i++) {
				// responsedata = sendHttpData(,"");

				String tpurl = url + "?data=" + URLEncoder.encode(j.toString());

				String heads = "JSESSIONID=676hkg13l28r6gqubamcsg5s; _ga=GA1.1.1047426687.1574055195; AdminCode=JUqn; _gid=GA1.1.1715164123.1577688926; _gat_gtag_UA_126706596_1=1";

				// String heads="JSESSIONID=ef3xmfrnj1q1gj4yozh8x4ao;
				// _ga=GA1.1.24081232.1577692939; _gid=GA1.1.1115868964.1577692939;
				// _gat_gtag_UA_126706596_1=1";

				HashMap<String, String> maps = new HashMap<String, String>();
				maps.put("Cookie", heads);

				responsedata = HttpSendPost.sendHttpJSONDataNoSSL(null, false, "", true, tpurl, "", maps, null);

				System.out.println(i + "返回:" + responsedata);
			}

			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	private static void replaydeltest() {

		String url = "http://127.0.0.1:8081/kb/replay/del.do";

		// String url =
		// "http://10.204.37.192:8080/gserver/version/getVersionInfo.action?type=2";

		String data = "";

		String responsedata = "";
		try {

			JSONObject j = new JSONObject();
			j.put("id", "111");
			j.put("state", "1");

			int num = 2;
			for (int i = 0; i < num; i++) {
				// responsedata = sendHttpData(,"");

				String tpurl = url + "?data=" + URLEncoder.encode(j.toString());

				String heads = "JSESSIONID=676hkg13l28r6gqubamcsg5s; _ga=GA1.1.1047426687.1574055195; AdminCode=JUqn; _gid=GA1.1.1715164123.1577688926; _gat_gtag_UA_126706596_1=1";

				HashMap<String, String> maps = new HashMap<String, String>();
				maps.put("Cookie", heads);

				responsedata = HttpSendPost.sendHttpJSONDataNoSSL(null, false, "", true, tpurl, "", maps, null);

				System.out.println(i + "返回:" + responsedata);
			}

			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	public static void posttest() {
		String url = "https://www.gravatar.com/avatar/ee2f5d239020af787b969557177d3df7?s=100";

		try {
			String responsedata = sendHttpData(url, "");
			System.out.println("返回:" + responsedata);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void testprint() {

		System.out.printf("%-20s    %-3.2f\n", "价格", 1.231);
		System.out.printf("%-25s    \n", "------------------");
		System.out.printf("%-20s    %-3.2f\n", "111", 12.231);
		System.out.printf("%-20s    %-3.2f\n", "apple", 1.231);
		System.out.printf("%-20s    %-3.2f\n", "苹果", 1.231);
		System.out.printf("%-20s    %-3.2f\n", "苹果顶顶顶顶", 1.231);
		System.out.printf("%-20s    %-3.2f\n", "苹1231", 1.231);

		System.out.printf("%-10s", "abc"); // 输出10列，左对齐(-号表示左对齐)
	}

	public static void avtest() {
		String url = "https://www.gravatar.com/avatar/ee2f5d239020af787b969557177d3df7?s=100";

		try {
			String responsedata = sendHttpData(url, "");
			System.out.println("返回:" + responsedata);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	static {
		BasicConfigurator.configure();
	}

	public static String getUnicode(String source) {
		String returnUniCode = null;
		String uniCodeTemp = null;
		for (int i = 0; i < source.length(); i++) {
			uniCodeTemp = "\\u" + Integer.toHexString((int) source.charAt(i));// 使用char类的charAt()的方法
			returnUniCode = returnUniCode == null ? uniCodeTemp : returnUniCode + uniCodeTemp;
		}
		System.out.print(source + " 's unicode = " + returnUniCode);
		return returnUniCode;// 返回一个字符的unicode的编码值
	}

	private static Logger logger = Logger.getLogger(test.class);

	public static String stringToUnicode(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			if (ch > 255)
				str += s.charAt(i) + ": " + "\\u" + Integer.toHexString(ch) + "\n";
			else
				str += s.charAt(i) + ": " + "\\u00" + Integer.toHexString(ch) + "\n";
		}
		return str;
	}

	private static final int CP_REGIONAL_INDICATOR = 0x1F1E7; // A-Z flag codes.

	/**
	 * Get the flag codes of two (or one) regional indicator symbols.
	 * 
	 * @param s
	 *            string starting with 1 or 2 regional indicator symbols.
	 * @return one or two ASCII letters for the flag, or null.
	 */
	public static String regionalIndicator(String s) {
		int cp0 = regionalIndicatorCodePoint(s);
		if (cp0 == -1) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append((char) (cp0 - CP_REGIONAL_INDICATOR + 'A'));
		int n0 = Character.charCount(cp0);
		int cp1 = regionalIndicatorCodePoint(s.substring(n0));
		if (cp1 != -1) {
			sb.append((char) (cp1 - CP_REGIONAL_INDICATOR + 'A'));
		}
		return sb.toString();
	}

	private static int regionalIndicatorCodePoint(String s) {
		if (s.isEmpty()) {
			return -1;
		}
		int cp0 = s.codePointAt(0);
		return CP_REGIONAL_INDICATOR > cp0 || cp0 >= CP_REGIONAL_INDICATOR + 26 ? -1 : cp0;
	}

	public static final char MIN_LOW_SURROGATE = '\uDC00';
	public static final char MAX_LOW_SURROGATE = '\uDFFF';

	public static final char MIN_HIGH_SURROGATE = '\uD800';
	public static final char MAX_HIGH_SURROGATE = '\uDBFF';

	/*
	 * public static int toCodePoint(char high, char low) { // Optimized form of: //
	 * return ((high - MIN_HIGH_SURROGATE) << 10) // + (low - MIN_LOW_SURROGATE) //
	 * + MIN_SUPPLEMENTARY_CODE_POINT; return ((high << 10) + low) +
	 * (MIN_SUPPLEMENTARY_CODE_POINT - (MIN_HIGH_SURROGATE << 10) -
	 * MIN_LOW_SURROGATE); }
	 */

	/**
	 * cool
	 * 
	 * @param toConvert
	 * @return
	 * @author zj
	 * @date 2018年12月19日
	 */
	public static String convert16to32(String toConvert) {
		for (int i = 0; i < toConvert.length();) {
			int codePoint = Character.codePointAt(toConvert, i);
			i += Character.charCount(codePoint);
			// System.out.printf("%x%n", codePoint);
			String utf32 = String.format("0x%x%n", codePoint);
			return utf32;
		}
		return null;
	}

	public static String stringToUnicode2(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			if (ch > 255)
				str += s.charAt(i) + ": " + "\\u" + Integer.toHexString(ch) + "\n";
			else
				str += s.charAt(i) + ": " + "\\u00" + Integer.toHexString(ch) + "\n";
		}
		return str;
	}

	private static void testlog4j() {
		String d = "🤟";

		int codePoint = Character.codePointAt(d, 0);

		System.out.println(stringToUnicode(d));
		System.out.println(JEscape.escape(d));

		String utf32 = String.format("0x%x%n", codePoint);

		System.out.println(utf32);
		if (true)
			return;
		System.out.println(convert16to32("\uD83D\uDE00"));

		long x = Long.parseLong("D83D", 16) << 8;
		long y = Long.parseLong("DE00", 16);
		System.out.println(Long.toHexString(x + y));

		System.out.println(JEscape.escape(d));

		String dd = JEscape.unescape("%uD83D%uDE00");
		System.out.println(dd);
		// "%3Cp%3E%uD83D%uDE00%26nbsp%3B%3C/p%3E"

		System.out.println(stringToUnicode(d));

		if (true)
			return;

		System.out.println(System.getProperty("user.home"));

		/*
		 * Properties properties=new Properties();
		 * properties.setProperty("log4j.root","TRACE,stdout,MyFile");
		 * properties.setProperty("log4j.rootCategory","TRACE");
		 * 
		 * properties.setProperty("log4j.appender.stdout",
		 * "org.apache.log4j.ConsoleAppender");
		 * properties.setProperty("log4j.appender.stdout.layout",
		 * "org.apache.log4j.PatternLayout"); properties.setProperty(
		 * "log4j.appender.stdout.layout.ConversionPattern","%d{YYYY/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n"
		 * );
		 * 
		 * properties.setProperty("log4j.appender.MyFile",
		 * "org.apache.log4j.RollingFileAppender");
		 * properties.setProperty("log4j.appender.MyFile.File", "my_example.log");
		 * properties.setProperty("log4j.appender.MyFile.MaxFileSize", "100KB");
		 * properties.setProperty("log4j.appender.MyFile.MaxBackupIndex", "1");
		 * properties.setProperty("log4j.appender.MyFile.layout",
		 * "org.apache.log4j.PatternLayout"); properties.setProperty(
		 * "log4j.appender.MyFile.layout.ConversionPattern","%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n"
		 * );
		 * 
		 * PropertyConfigurator.configure(properties);
		 * 
		 * // Logger logger2 = Logger.getLogger("MyFile");
		 * 
		 */

		logger.info("test info in test.java");
		logger.debug("debug info in test.java");

		logger.debug("Log4jExample: A Sample Debug Message");

		logger.info("Log4jExample: A Sample Info  Message");

		logger.warn("Log4jExample: A Sample Warn  Message");

		logger.error("Log4jExample: A Sample Error Message");

		logger.fatal("Log4jExample: A Sample Fatal Message");
	}

	private static void testl() {

		String url = "http://esss.4saas.com/JsonRequest";

		// String url =
		// "http://10.204.37.192:8080/gserver/version/getVersionInfo.action?type=2";

		String data = "";

		String responsedata = "";
		try {

			JSONObject j = new JSONObject();
			j.put("staff_name", "lyh");
			j.put("password", "123456");
			j.put("wechatid", "o7B4swRzGQfHwRg1Caa7nFrWMmRc");
			j.put("multi_user_token", "cpic");

			int num = 2;

			responsedata = sendHttpData(url, URLEncoder.encode(j.toString()));
			System.out.println("返回:" + responsedata);

			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	private static void update2() {

		String url = "http://www.256kb.cn/googleac882b1d52647127.html";

		// String url =
		// "http://10.204.37.192:8080/gserver/version/getVersionInfo.action?type=2";

		String data = "";

		String responsedata = "";
		try {
			responsedata = sendHttpData(url, data);

			System.out.println("返回:" + responsedata);
			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	public static void testhtml() {

		// update2();

		// if(true)
		// return;

		// http://127.0.0.1:8080/kb/public/detail/344f5834-fe93-49e8-835d-b07e1a1def96.html
		// http://127.0.0.1:8080/kb/html/detail/344f5834-fe93-49e8-835d-b07e1a1def96.html
		HttpClient httpClient = new HttpClient();
		// httpClient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
		// true);

		// String u1="http://256kb.cn/googleac882b1d52647127.html";

		// http://256kb.cn:8888/kb/googleac882b1d52647127.html
		// //http://256kb.cn/googleac882b1d52647127.html
		// String u1="http://127.0.0.1:8081/kb/1231/1232";
		// //http://127.0.0.1:8080/kb/googleac882b1d52647127.html
		// String
		// u1="https://www.256kb.cn/public/detail?i=0c4f3944-4355-40da-8096-05a32df1c8b9";
		// //http://127.0.0.1:8080/kb/googleac882b1d52647127.html
		String u1 = "https://biz.trace.ickd.cn/auto.php?mailNo=804446284517390475"; // http://127.0.0.1:8080/kb/googleac882b1d52647127.html

		// String
		// u1="http://256kb.cn/kb/public/html/2018/09/bdc8c0b5-44fb-4723-b2c5-fcf721c24089.html";
		// String u1="http://256kb.cn/public/index/";
		String u2 = "https://www.256kb.cn/public/search/";
		GetMethod getMethod = new GetMethod(u1);

		PostMethod pMethod = new PostMethod(u1);

		/*
		 * HttpMethodParams params = new HttpMethodParams();
		 * 
		 * params.setContentCharset("UTF-8");
		 * 
		 * getMethod.setParams(params);
		 */
		try {

			// getMethod.addRequestHeader("user-agent","Mozilla/4.0 (compatible; MSIE 6.0;
			// Windows NT 5.0; Alexa Toolbar; Maxthon 2.0)");
			// getMethod.addRequestHeader("user-agent","Mozilla/5.0 (compatible;google
			// *****Baiduspider/2.0; +http://www.baidu.com/search/spider.html +zj test
			// !!)");

			// getMethod.addRequestHeader("Pre-User-Agent2"," +zj test !!)");

			Date d1 = new Date();
			// int result = httpClient.executeMethod(getMethod);
			int result = httpClient.executeMethod(pMethod);
			Date d2 = new Date();
			if (result == 200) {

				System.out.println(getMethod.getResponseContentLength());
				String html = getMethod.getResponseBodyAsString();
				System.out.println(html);
				System.out.println("ms:" + (d2.getTime() - d1.getTime()));
				System.out.println(html.getBytes().length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
	}

	public static void testicon() {

		try {
			String url = "https://www.xiaoz.me/favicon.ico";

			List<BasicClientCookie> cks = new ArrayList<BasicClientCookie>();
			HashMap<String, String> headers = new HashMap<>();
			headers.put("user-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
			headers.put("accept-encoding", "gzip,deflate");
			headers.put("referer", url);
			headers.put("Content-type", "image/x-icon");
			headers.put("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
			byte[] picDats= HttpSendPost.sendHttpJSONDataNoSSLBackByte(null, true, "", true, url, "", headers, cks);

			File f = new File("C:\\Users\\admin\\Pictures\\test.png");//

			System.out.println("11");
			//byte[] b=iconStr.getBytes("utf-8");
			//for (byte c : picDats) {
			//	System.out.print(c);
			//}
			
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(picDats);
			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public static void testGzip() {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod("https://www.256kb.cn/js/plugin/jquery/jquery.v1.11.3.js");
		try {
			getMethod.addRequestHeader("accept-encoding", "gzip,deflate");
			getMethod.addRequestHeader("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; Alexa Toolbar; Maxthon 2.0)");
			int result = httpClient.executeMethod(getMethod);

			if (result == 200) {
				System.out.println(getMethod.getResponseContentLength());
				String html = getMethod.getResponseBodyAsString();
				System.out.println(html);
				System.out.println(html.getBytes().length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
	}

	private static void deltest() {

		String url = "http://127.0.0.1:8081/kb/blog/del.do?JSESSIONID=13mnil3xuid3jomixwj5hmaw1&_ga=GA1.1.207268850.1545051326&_gid=GA1.1.421672797.1550384139";

		// String url =
		// "http://10.204.37.192:8080/gserver/version/getVersionInfo.action?type=2";

		String data = "";

		String responsedata = "";
		try {

			JSONObject j = new JSONObject();
			j.put("recordid", "19");

			int num = 2;
			// for (int i = 0; i < num; i++) {
			responsedata = sendHttpData(url + "&data=" + URLEncoder.encode(j.toString()), "");
			System.out.println("返回:" + responsedata);
			// }

			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	private static void replaytest() {

		String url = "http://127.0.0.1:8081/kb/replay/addOrUpdate.do";

		// String url =
		// "http://10.204.37.192:8080/gserver/version/getVersionInfo.action?type=2";

		String data = "";

		String responsedata = "";
		try {

			JSONObject j = new JSONObject();
			j.put("imei", "2c502053-3740-4ed3-b2b0-2d04a5650c6d");
			j.put("userid", "test");
			j.put("context", "contextcontextcontext");

			int num = 2;
			for (int i = 0; i < num; i++) {
				// responsedata = sendHttpData(,"");

				String tpurl = url + "?data=" + URLEncoder.encode(j.toString());

				String heads = "JSESSIONID=1tilb1fvzqyfh1oqzgamw1fhgt; SSESSIONID=b3081d08-3e13-40ed-84f5-888184fa58ca; _ga=GA1.1.113603198.1571636541; _gid=GA1.1.559982730.1571636541";

				HashMap<String, String> maps = new HashMap<String, String>();
				maps.put("Cookie", heads);

				responsedata = HttpSendPost.sendHttpJSONDataNoSSL(null, false, "", true, tpurl, "", maps, null);

				System.out.println(i + "返回:" + responsedata);
			}

			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	private static void testcity() {
		String k = "\u5e7f\u5dde";
		System.err.println(k);

		String ip = "58.67.201.8";

		String url_taobao = "http://ip.taobao.com/service/getIpInfo.php?ip=";

		try {
			String rst = SendPostRequest.sendHttpData(url_taobao + ip, "");
			JSONObject js = new JSONObject(rst);
			if (js.optString("code").equals("0")) {
				String city = js.optJSONObject("data").optString("city");
				System.out.println(city);
			}
			System.out.println(rst);
			System.out.println("===========taobao end================");
			System.out.println("========================");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url_sina = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
		try {
			String rst = SendPostRequest.sendHttpData(url_sina + ip, "");
			/*
			 * JSONObject js = new JSONObject(rst); if (js.optString("code").equals("0")) {
			 * String city = js.optJSONObject("data").optString("city");
			 * System.out.println(city); }
			 */
			System.out.println(rst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testPatter() {
		String pattern = ".*/public/.*";

		// boolean isMatch = Pattern.matches(pattern, "/kb/public/index");
		// System.out.println(isMatch);

		String pattern2 = ".*/public/html/(\\d+/\\d+)/.*.html";
		String curl = "/kb/public/html/2018/04/86b49ac4-8616-41b9-862e-e022c111915e.html";

		Pattern p = Pattern.compile(pattern2);
		Matcher m = p.matcher(curl);
		if (m.matches()) {
			try {
				String d = m.group(1);
				System.out.println(d);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

	private static void update() {

		String url = "http://127.0.0.1:8081/kb/blog/addOrUpdate.action";

		// String url =
		// "http://10.204.37.192:8080/gserver/version/getVersionInfo.action?type=2";

		String data = "";

		String responsedata = "";
		try {

			String heads = "JSESSIONID=1tilb1fvzqyfh1oqzgamw1fhgt; SSESSIONID=b3081d08-3e13-40ed-84f5-888184fa58ca; _ga=GA1.1.113603198.1571636541; _gid=GA1.1.559982730.1571636541";

			HashMap<String, String> maps = new HashMap<String, String>();
			maps.put("Cookie", heads);

			responsedata = HttpSendPost.sendHttpJSONDataNoSSL(null, false, "", true, url, "", maps, null);

			// responsedata = sendHttpData(url, data);

			System.out.println("返回:" + responsedata);
			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	private static void testLoginOut() {

		String url = "http://127.0.0.1:8080/h264s/h/app/logout";

		String json = "{\"deviceid\": \"xxx2\",\"userid\": \"t1\"}";

		String data = json;

		String responsedata = "";
		try {
			responsedata = sendHttpData(url, data);

			System.out.println("返回:" + responsedata);
			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	private static void testRegister() {

		String url = "http://127.0.0.1:8080/h264s/h/app/register_device";

		String json = "{\"deviceid\": \"xxx2\",\"ip\": \"192.168.2.112\",\"port\": \"9998\"}";

		String data = json;

		String responsedata = "";
		try {
			responsedata = sendHttpData(url, data);

			System.out.println("返回:" + responsedata);
			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	/**
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-11-14
	 */

	private static void testLogin() {

		// String url = "http://127.0.0.1:8080/h264s/h/app/login";

		String url = "http://10.204.37.192:8080/h264/h/app/login";

		String json = "{\"userid\": \"t1\",\"pass\": \"123321\"}";

		String data = json;

		String responsedata = "";
		try {
			responsedata = sendHttpData(url, data);

			System.out.println("返回:" + responsedata);
			// System.out.println("解密:" + out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(responsedata);
		// ***********vmInstallApp*****************

	}

	public static String sendHttpData(String url, String str) throws Exception {

		logger.info("HTTP Request URL:" + url + ",HTTP Request PARAM:" + str);
		HttpClient client = new HttpClient();
		// client.getHostConfiguration().setProxy("10.41.70.8", 80);
		// client.getParams().setAuthenticationPreemptive(true);

		PostMethod httpPost = new PostMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		client.setTimeout(60000);

		// httpPost.setRequestHeader("Content-type", "application/json");
		httpPost.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setRequestHeader("Accept", "application/json");
		httpPost.setRequestHeader("Connection", "close");
		httpPost.setRequestHeader("User-Agent",
				"Mozilla/5.0 (Linux; Android 4.4.4; HM NOTE 1LTEW Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36 MicroMessenger/6.0.0.54_r849063.501 NetType/WIFI");
		// httpPost.setRequestHeader("Authorization", "Basic YWRtaW46MTIz");
		httpPost.setRequestBody(is);

		String responseData = null;
		try {
			Exception exception = null;
			client.executeMethod(httpPost);

			int resStatusCode = httpPost.getStatusCode();
			if (resStatusCode == HttpStatus.SC_OK) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpPost.getResponseBodyAsStream(), "utf-8"));
				logger.info("HTTP Request CHARSET:" + httpPost.getResponseCharSet());
				String res = null;
				StringBuffer sb = new StringBuffer();
				while ((res = br.readLine()) != null) {
					sb.append(res);
				}
				responseData = sb.toString();
			} else {
				logger.error("http请求失败 " + resStatusCode + ":" + httpPost.getStatusText());
				exception = new Exception("[SerialHttpSender] HttpErrorCode:" + resStatusCode);
			}
			if (exception != null) {
				throw exception;
			}
		} catch (java.net.ConnectException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (IOException ex) {
			ex.printStackTrace();
			// org.apache.commons.httpclient.HttpRecoverableException:
			// java.net.SocketTimeoutException: Read timed out

			String message = ex.getMessage();
			if (message != null && message.toLowerCase().indexOf("read timed") > -1) {
				throw new Exception(ex.getMessage());
			} else {
				ex.printStackTrace();
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			httpPost.releaseConnection();

		}

		logger.info("HTTP Request Result:" + responseData);
		return responseData;
	}
}
