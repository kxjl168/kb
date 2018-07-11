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
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * 快递查询接口，跟踪爱查快递html页面js， 模拟生成该页面查询请求。
 * 
 * @see https://www.ickd.cn/ <br/>
 *      一下为页面核心源码，当页面加密算法有变动时，本方法需要对应变更.
 * 
 *      <pre>
 * 
 * $.jsonp({
 *     url: "//biz.trace.ickd.cn/" + com.spell + "/" + no,
 *     data: $("#queryForm").serialize() + "&tk=" + Query.sign(no + time) + "&tm=" + time,
 *     dataType: "jsonp",
 *     jsonp: "callback",
 *     callbackParameter: "callback",
 *     success: function(a, b) {
 *         THIS._afterQuery();
 *         if (a == "reTry") {
 *             return window.__reCallbackSuccess = arguments.callee
 *         }
 *         if ("success" == b) {
 *             THIS.showResult(a)
 *         }
 *     },
 *     error: function(a, b) {
 *         THIS._afterQuery();
 *         locakMask = false;
 *         if ("timeout" == b) {
 *             THIS.showError("查询超时，请重新查询。")
 *         } else if ("error" == b) {
 *             THIS.showError("系统发生错误，请稍后再试。")
 *         }
 *     },
 *     cache: !1,
 *     timeout: 18e3
 * })
 * 
 * 
 * Query.sign = function() {
 *     function a() {
 *         var a, b, c;
 *         for (b = 0; 256 > b; b += 1) {
 *             for (a = b,
 *             c = 0; 8 > c; c += 1)
 *                 1 & a ? a = j ^ a >>> 1 : a >>>= 1;
 *             g[b] = a >>> 0
 *         }
 *     }
 *     function b(a) {
 *         return Array.prototype.map.call(a, function(a) {
 *             return a.charCodeAt(0)
 *         })
 *     }
 *     function c(a) {
 *         var b, c, d, e, f = -1;
 *         for (b = 0,
 *         d = a.length; d > b; b += 1) {
 *             for (e = 255 & (f ^ a[b]),
 *             c = 0; 8 > c; c += 1)
 *                 1 === (1 & e) ? e = e >>> 1 ^ j : e >>>= 1;
 *             f = f >>> 8 ^ e
 *         }
 *         return -1 ^ f
 *     }
 *     function d(a, b) {
 *         var c, e, f;
 *         if ("undefined" != typeof d._x1l0o && b && a || (d._x1l0o = -1,
 *         a)) {
 *             for (c = d._x1l0o,
 *             e = 0,
 *             f = a.length; f > e; e += 1)
 *                 c = c >>> 8 ^ g[255 & (c ^ a[e])];
 *             return d._x1l0o = c,
 *             -1 ^ c
 *         }
 *     }
 *     function e(a) {
 *         var b, c, d, e, f, g = typeof a, h = 16, i = 0;
 *         if ("string" !== g && "number" !== g)
 *             return 0 / 0;
 *         if (a = (a + "").replace(/\s/g, "").split(".")[0],
 *         b = a.length,
 *         !b)
 *             return 0 / 0;
 *         if (h || (h = 10),
 *         "number" != typeof h || 2 > h || h > 36)
 *             return 0 / 0;
 *         for (c = a.split("").reverse(),
 *         d = 0; b > d; d++)
 *             e = c[d],
 *             f = e.charCodeAt(0),
 *             f >= 97 && (e = f - 87),
 *             i += Math.floor(e) * Math.pow(h, d);
 *         return i
 *     }
 *     var f, g = [], h = "edb", i = 15900, j = e(h + e(i));
 *     return a(),
 *     f = function(a, e) {
 *         var a = "string" == typeof a ? b(a + document.URL) : a
 *           , f = e ? c(a) : d(a);
 *         return (f >>> 0).toString(16)
 *     }
 *     ,
 *     f.direct = c,
 *     f.table = d,
 *     f
 * }();
 * 
 * </pre>
 * 
 * 
 *      综合查询
 * @author zj
 * @date 2018年7月10日
 * 
 */
public class ICKDWuliuTrack extends AbsWuliuTrack {

	private static Logger logger = Logger.getLogger(ICKDWuliuTrack.class);

	String url = "https://www.ickd.cn/";

	long[] g;
	String h = "edb";
	String i = "15900";
	long ck = -1;

	public ICKDWuliuTrack() {
		g = a();
	}

	public String sign(String num, String miniseconds) {

		String key = num + miniseconds + url;

		// key="2141182099981531273270897"+url;

		int[] a = b(key);

		long fval = d(a);
		// -4592476
		String v = Long.toHexString(fval);
		// 去除负数ff
		while (v.startsWith("f"))
			v = v.substring(1);

		if (v.length() < 7)
			v = "ff" + v;

		return v;
	}

	public int d(int[] a) {
		int c;
		int e, f;
		// c = ck;
		c = -1;
		e = 0;
		for (f = a.length; f > e; e += 1) {
			int tp = (int) c >>> 8;
			c = tp ^ (int) (g[(int) (255 & (c ^ a[e]))]);
		}

		ck = c;
		return -1 ^ c;

	}

	public static int[] b(String a) {
		int[] bdata = new int[a.length()];

		String[] ss = a.split("");
		for (int i = 0; i < ss.length; i++) {
			bdata[i] = ss[i].charAt(0);
		}

		return bdata;
	}

	public static long e(String a) {
		int b, d, f, h = 16;
		long i = 0;
		b = a.length();
		char e;
		int v;
		String[] c = (StringUtils.reverse(a)).split("");
		for (d = 0; b > d; d++) {
			e = c[d].charAt(0);
			f = c[d].charAt(0);

			if (f >= 97) {
				// e =(char)( f - 87);
				v = f - 87;
			} else
				v = Integer.parseInt(String.valueOf(e));

			i += Math.floor(v) * Math.pow(h, d);
		}
		return i;
	}

	/**
	 * 对应js中 g方法生成 g数组 a方法
	 * 
	 * @author zj
	 * @date 2018年7月10日
	 */
	public long[] a() {

		long j = e(h + e(i));

		long[] g = new long[256];

		long a;
		int b, c;
		for (b = 0; 256 > b; b++) {
			for (a = b, c = 0; 8 > c; c += 1) {
				if ((1 & a) > 0) {
					a = j ^ a >>> 1;
				} else {
					a = (a >>>= 1);
				}
				// 1 & a ? a = j ^ a >>> 1 : a >>>= 1;
			}
			g[b] = a >>> 0;
		}

		return g;
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

		ICKDWuliuTrack track = new ICKDWuliuTrack();

		Calendar c = Calendar.getInstance();
		Long miniseonds = c.getTimeInMillis();
		String minisecond = String.valueOf(miniseonds);

		String tk = track.sign(num, minisecond);

		logger.debug("tk=" + tk);

		String url = "https://biz.trace.ickd.cn/auto/" + num + "?mailNo=" + num
				+ "&tk=" + tk + "&tm=" + minisecond + "&callback=";
		Calendar c2 = Calendar.getInstance();
		Long miniseonds2 = c.getTimeInMillis();
		String minisecond2 = String.valueOf(miniseonds2);
		url += "&" + "_" + minisecond2 + "=";

		logger.debug(url);

		String rst = track.sendPost(url, null);

		try {
			JSONObject js = new JSONObject(rst);
			if (js.optString("errCode").equals("0")) {
				logger.debug(rst);
			} else {
				if (js.optString("errCode").equals("11")) {
					logger.debug(rst);
					if (js.optString("expSpellName").equals("yunda")) {
						// yunda的需要腾讯验证码，使用快递鸟查询
						// url+=
						// "&spellName=yunda&exp-textName=&ticket=t02mJkDAAj8czNCEvT7YXNQ1uqBXh554P4SiYXBZYQ0rEVFIs7F0Y_TC9qAof5t18D5JWGpR4koRRyz71J_TJrkVI_tzms8_C5lgN2yNieG5O9PM0e3asO9HA**&randstr=%40GGH";
						// rst = track.sendPost(url, null);
						rst = KdniaoTrackQueryAPI.GetYunDa(num);

					}// 基本是顺丰的
					else {
						rst = K100Track.GetWuliu(num);
					}
				} else {
					rst = K100Track.GetWuliu(num);
				}
				
				logger.debug(rst);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	

		return rst;
	}

	public static void main(String[] args) {

		/*
		 * long d=-1548591426; String k= Long.toHexString(d);
		 * while(k.startsWith("f")) k=k.substring(1); System.out.println(k);
		 * 
		 * if(true) return;
		 */

		ICKDWuliuTrack track = new ICKDWuliuTrack();

		Calendar c = Calendar.getInstance();
		Long miniseonds = c.getTimeInMillis();
		String minisecond = String.valueOf(miniseonds);
		// 1531213122344

		minisecond = "1531273683545";

		// tk=53cbd712&tm=1531271066526

		/*
		 * String v=Long.toHexString(-4592476); //去除负数ff
		 * while(v.startsWith("f")) v=v.substring(1);
		 */

		// System.out.println("ff"+v);

		// "mailNo=214118209998&spellName=&exp-textName=&tk=1711e02b&tm=1531273683545"

		String num = "70442311422394";
		String tk = track.sign(num, minisecond);

		System.out.println("tk=" + tk);

		String url = "https://biz.trace.ickd.cn/auto/" + num + "?mailNo=" + num
				+ "&spellName=&exp-textName=&tk=" + tk + "&tm=" + minisecond
				+ "&callback=";
		Calendar c2 = Calendar.getInstance();
		Long miniseonds2 = c.getTimeInMillis();
		String minisecond2 = String.valueOf(miniseonds2);
		url += "&" + "_" + minisecond2 + "=";

		System.out.println(url);

		// https://biz.trace.ickd.cn/auto/214118209998?mailNo=214118209998&spellName=&exp-textName=&tk=3d64c03&tm=1531201366376&callback=_jqjsp&_1531201366378=
		// String
		// url="https://biz.trace.ickd.cn/auto/214118209998?mailNo=214118209998&spellName=&exp-textName=&tk=c52997a7&tm=1531200583384&callback=_jqjsp&_1531200583384=";
		String rst = track.sendPost(url, null);
		// String rst = track.SendHttpData2(url+"?mailNo=" + num +
		// "&spellName=&exp-textName=&tk=" + tk
		// + "&tm=" + minisecond + "&callback=_jqjsp","");
		System.out.println(rst);
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

			/*
			 * conn.setRequestProperty("Cookie",
			 * "Hm_lvt_39418dcb8e053c84230016438f4ac86c=1531212321; Hm_lpvt_39418dcb8e053c84230016438f4ac86c=1531273461"
			 * );
			 */
			conn.setRequestProperty(
					"Cookie",
					"Hm_lvt_39418dcb8e053c84230016438f4ac86c="
							+ r.nextInt(1000000)
							+ "; Hm_lpvt_39418dcb8e053c84230016438f4ac86c="
							+ r.nextInt(1000000));
			conn.setRequestProperty("Referer", "https://www.ickd.cn/");

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
