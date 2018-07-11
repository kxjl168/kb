package com.kxjl.tool.utils.wuliu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 快递查询接口，跟踪爱查快递html页面js， 模拟生成该页面查询请求。
 * 
 * @see https://www.ickd.cn/ <br/>
 *      一下为页面核心源码，当页面加密算法有变动时，本方法需要对应变更.
 * 
 * <pre>
 * 
 * 
 * 
 * </pre>
 * 
 * 
 * 
 *      顺丰查询
 * @author zj
 * @date 2018年7月10日
 * 
 */
public class K100Track extends AbsWuliuTrack {

	private static Logger logger = Logger.getLogger(K100Track.class);

	String url = "https://www.ickd.cn/";

	long[] g;
	String h = "edb";
	String i = "15900";
	long ck = -1;

	/**
	 * 自动识别快递类型
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2018-7-11
	 */
	private static String getType(String num) {

		String type = "shunfeng";
		String url = "https://www.kuaidi100.com/autonumber/autoComNum?resultv2=1&text="
				+ num;
		Calendar c2 = Calendar.getInstance();
		Long miniseonds2 = c2.getTimeInMillis();
		String minisecond2 = String.valueOf(miniseonds2);
		url += "&" + "_" + minisecond2 + "=";

		logger.debug(url);

		K100Track track = new K100Track();
		String rst = track.sendPost(url, null);

		try {
			JSONObject jtype = new JSONObject(rst);
			type = jtype.optJSONArray("auto").getJSONObject(0)
					.optString("comCode");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return type;

	}

	/**
	 * 获取物流信息
	 * 
	 * @param num
	 * @return
	 * @author zj
	 * @date 2018年7月11日
	 */
	public static String GetWuliu(String num) {

		if (num == null || num.equals(""))
			return "{}";

		K100Track track = new K100Track();

		Calendar c = Calendar.getInstance();
		Long miniseonds = c.getTimeInMillis();
		String minisecond = String.valueOf(miniseonds);

		String type = getType(num);

		String url = "http://www.kuaidi100.com/query?type=" + type + "&postid="
				+ num + "&id=1&valicode=&temp=0.16859480039596852";
		Calendar c2 = Calendar.getInstance();
		Long miniseonds2 = c.getTimeInMillis();
		String minisecond2 = String.valueOf(miniseonds2);
		url += "&" + "_" + minisecond2 + "=";

		logger.debug(url);

		String rst = track.sendPost(url, null);

		try {
			JSONObject ickJson = new JSONObject(rst);

			ickJson.put("ord", "DESC");
			if (type.equals("shunfeng"))
				ickJson.put("expTextName", "顺丰快递");
			else if (type.equals("shentong"))
				ickJson.put("expTextName", "申通快递");
			else if (type.equals("zhongtong"))
				ickJson.put("expTextName", "中通快递");
			else
				ickJson.put("expTextName", type);

			rst = ickJson.toString();

		} catch (Exception e) {
			// TODO: handle exception
		}

		logger.debug(rst);

		return rst;
	}

	public static void main(String[] args) {
		GetWuliu("456698803172");

	}

	public String getOrderTracesByJson(String expCode, String expNo)
			throws Exception {

		return "";
	}

	public String SendHttpData2(String url, String str) {

		// logger.info("HTTP Request URL:" + url + ",HTTP Request PARAM:" +
		// str);
		HttpClient client = new HttpClient();
		// client.getHostConfiguration().setProxy("10.41.70.8", 80);
		// client.getParams().setAuthenticationPreemptive(true);

		GetMethod httpPost = new GetMethod(url);
		InputStream is;
		try {
			is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
			client.setTimeout(60000);

			// httpPost.setRequestHeader("Content-type", "application/json");
			httpPost.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded; charset=UTF-8");

			httpPost.setRequestHeader("Accept", "application/json");
			httpPost.setRequestHeader("Connection", "close");
			// httpPost.setRequestHeader("Authorization", "Basic YWRtaW46MTIz");
			// httpPost.set(is);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String responseData = null;
		try {
			Exception exception = null;
			client.executeMethod(httpPost);
			int resStatusCode = httpPost.getStatusCode();
			if (resStatusCode == HttpStatus.SC_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						httpPost.getResponseBodyAsStream(), "utf-8"));
				// logger.info("HTTP Request CHARSET:" +
				// httpPost.getResponseCharSet());
				String res = null;
				StringBuffer sb = new StringBuffer();
				while ((res = br.readLine()) != null) {
					sb.append(res);
				}
				responseData = sb.toString();
			} else {
				// logger.error("http请求失败 " + resStatusCode + ":" +
				// httpPost.getStatusText());
				exception = new Exception("[SerialHttpSender] HttpErrorCode:"
						+ resStatusCode);
			}
			if (exception != null) {
				throw exception;
			}
		} catch (java.net.ConnectException ex) {
			ex.printStackTrace();
			// throw ex;
		} catch (IOException ex) {
			ex.printStackTrace();
			// org.apache.commons.httpclient.HttpRecoverableException:
			// java.net.SocketTimeoutException: Read timed out

			String message = ex.getMessage();
			if (message != null
					&& message.toLowerCase().indexOf("read timed") > -1) {
				// throw new Exception(ex.getMessage());
			} else {
				ex.printStackTrace();
				// throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			// throw ex;

		} finally {
			httpPost.releaseConnection();

		}

		// logger.info("HTTP Request Result:" + responseData);
		return responseData;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param params
	 *            请求的参数集合
	 * @return 远程资源的响应结果
	 */
	@SuppressWarnings("unused")
	private String sendPost(String url, Map<String, String> params) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// POST方法
			conn.setRequestMethod("POST");
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			Random r = new Random();

			conn.setRequestProperty(
					"Cookie",
					"Hm_lvt_39418dcb8e053c84230016438f4ac86c="
							+ r.nextInt(1000000)
							+ "; Hm_lpvt_39418dcb8e053c84230016438f4ac86c="
							+ r.nextInt(1000000));
			conn.setRequestProperty("Referer",
					"http://www.kuaidi100.com/all/sf.shtml");

			conn.connect();
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			if (params != null) {
				StringBuilder param = new StringBuilder();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					if (param.length() > 0) {
						param.append("&");
					}
					param.append(entry.getKey());
					param.append("=");
					param.append(entry.getValue());
					// System.out.println(entry.getKey()+":"+entry.getValue());
				}
				// System.out.println("param:"+param.toString());
				out.write(param.toString());
			}
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

}
