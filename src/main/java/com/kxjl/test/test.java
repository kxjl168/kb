package com.kxjl.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpException;
import org.apache.http.client.params.ClientPNames;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.kxjl.tool.httpPost.SendPostRequest;

public class test {

	public static void main(String args[]) {
		// testLogin();

		// testLoginOut();

		// testRegister();

		// testPatter();

		// update();

		//testcity();
		
		//replaytest();
		
		//testGzip();
		
		//testhtml();
		
		testl();
	}
	
	
	
	private static void testl() {

		
		String url = "http://esss.4saas.com/JsonRequest";

		// String url =
		// "http://10.204.37.192:8080/gserver/version/getVersionInfo.action?type=2";

		
	
		String data = "";

		String responsedata = "";
		try {
			
			JSONObject j=new JSONObject();
			j.put("staff_name", "lyh");
			j.put("password", "123456");
			j.put("wechatid", "o7B4swRzGQfHwRg1Caa7nFrWMmRc");
			j.put("multi_user_token", "cpic");
			
			
			int num=2;
		
				responsedata = sendHttpData(url,URLEncoder.encode(j.toString()));
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
		// http://127.0.0.1:8080/kb/public/detail/344f5834-fe93-49e8-835d-b07e1a1def96.html
		// http://127.0.0.1:8080/kb/html/detail/344f5834-fe93-49e8-835d-b07e1a1def96.html
		  HttpClient httpClient = new HttpClient();  
		 // httpClient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		  
				  String u1="http://256kb.cn//public/html/2018/05/f00c6b4e-8c17-4dbe-aa85-901aca96bb3b.html";
		  String u2="http://256kb.cn/public/detail/?i=f00c6b4e-8c17-4dbe-aa85-901aca96bb3b";
	        GetMethod getMethod = new GetMethod(u1);  
	        try {  
	                getMethod.addRequestHeader("accept-encoding", "gzip,deflate");  
	                getMethod.addRequestHeader("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; Alexa Toolbar; Maxthon 2.0)");
	                Date d1=new Date();
	                int result = httpClient.executeMethod(getMethod); 
	                Date d2=new Date();
	                if (result == 200) {
	                	
	                        System.out.println(getMethod.getResponseContentLength());  
	                        String html = getMethod.getResponseBodyAsString();  
	                        System.out.println(html);
	                        System.out.println("ms:"+(d2.getTime()-d1.getTime()));
	                        System.out.println(html.getBytes().length);  
	                }  
	        } catch (Exception e) {  
	                e.printStackTrace();  
	        }  finally {  
	                getMethod.releaseConnection();  
	        } 
	}
	
	public static void testGzip() {  
        HttpClient httpClient = new HttpClient();  
        GetMethod getMethod = new GetMethod("http://www.256kb.cn/js/plugin/jquery/jquery.v1.11.3.js");  
        try {  
                getMethod.addRequestHeader("accept-encoding", "gzip,deflate");  
                getMethod.addRequestHeader("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; Alexa Toolbar; Maxthon 2.0)");  
                int result = httpClient.executeMethod(getMethod); 
                
                if (result == 200) {  
                        System.out.println(getMethod.getResponseContentLength());  
                        String html = getMethod.getResponseBodyAsString();  
                        System.out.println(html);  
                        System.out.println(html.getBytes().length);  
                }  
        } catch (Exception e) {  
                e.printStackTrace();  
        }  finally {  
                getMethod.releaseConnection();  
        }  
}  
	
	private static void replaytest() {

		String url = "http://127.0.0.1:8080/kb/replay/addOrUpdate.do";

		// String url =
		// "http://10.204.37.192:8080/gserver/version/getVersionInfo.action?type=2";

		
	
		String data = "";

		String responsedata = "";
		try {
			
			JSONObject j=new JSONObject();
			j.put("imei","479d6e1f-ed72-4a9b-9cf0-38cd05360b1b");
			j.put("userid","test");
			j.put("context","contextcontextcontext");
			
			int num=2;
			for (int i = 0; i < num; i++) {
				responsedata = sendHttpData(url+"?data="+URLEncoder.encode(j.toString()),"");
				System.out.println(i+"返回:" + responsedata);
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
			/*JSONObject js = new JSONObject(rst);
			if (js.optString("code").equals("0")) {
				String city = js.optJSONObject("data").optString("city");
				System.out.println(city);
			}*/
			System.out.println(rst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	private static void testPatter() {
		String pattern = ".*/public/.*";

//		boolean isMatch = Pattern.matches(pattern, "/kb/public/index");
//		System.out.println(isMatch);
		
		
		
		String pattern2 = ".*/public/html/(\\d+/\\d+)/.*.html";
		String curl="/kb/public/html/2018/04/86b49ac4-8616-41b9-862e-e022c111915e.html";
		
		Pattern p= Pattern.compile(pattern2);
		Matcher m=p.matcher(curl);
		if(m.matches())
		{
			try {
				String d=m.group(1);
				System.out.println(d);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		
		
	}

	private static void update() {

		String url = "http://127.0.0.1:8080/gserver/version/getVersionInfo.action?type=2";

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

	private static Logger logger = Logger.getLogger(test.class);

	public static String sendHttpData(String url, String str) throws Exception {

		logger.info("HTTP Request URL:" + url + ",HTTP Request PARAM:" + str);
		HttpClient client = new HttpClient();
		// client.getHostConfiguration().setProxy("10.41.70.8", 80);
		// client.getParams().setAuthenticationPreemptive(true);

		PostMethod httpPost = new PostMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		client.setTimeout(60000);

		httpPost.setRequestHeader("Content-type", "application/json");
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
				BufferedReader br = new BufferedReader(new InputStreamReader(
						httpPost.getResponseBodyAsStream(), "utf-8"));
				logger.info("HTTP Request CHARSET:"
						+ httpPost.getResponseCharSet());
				String res = null;
				StringBuffer sb = new StringBuffer();
				while ((res = br.readLine()) != null) {
					sb.append(res);
				}
				responseData = sb.toString();
			} else {
				logger.error("http请求失败 " + resStatusCode + ":"
						+ httpPost.getStatusText());
				exception = new Exception("[SerialHttpSender] HttpErrorCode:"
						+ resStatusCode);
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
			if (message != null
					&& message.toLowerCase().indexOf("read timed") > -1) {
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
