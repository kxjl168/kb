package com.kxjl.tool.httpPost;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.ProxyHost;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class SendPostRequest {

	private static Logger logger = Logger.getLogger(SendPostRequest.class);

	public static String sendHttpData(String url, String str) throws Exception {

		return sendHttpData(url, str, null, 0);
	}

	public static String sendHttpXMLData(String url, String str, String proxyIP, int proPort) throws Exception {

		logger.info("HTTP Request URL:" + url + ",HTTP Request PARAM:" + str);
		HttpClient client = new HttpClient();
		// client.getHostConfiguration().setProxy("10.41.70.8", 80);
		// client.getParams().setAuthenticationPreemptive(true);

		// 打开和URL之间的连接
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();

		if (proxyIP != null && !proxyIP.equals("")) {
			client.getHostConfiguration().setProxy(proxyIP, proPort);
			// client.getParams().setAuthenticationPreemptive(true);
		}

		PostMethod httpPost = new PostMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		client.setTimeout(60000);

		httpPost.setRequestHeader("Content-type", "text/xml;charset=UTF-8");
		httpPost.setRequestHeader("Accept", "application/json");
		httpPost.setRequestHeader("Connection", "close");
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

	public static String sendHttpData(String url, String str, String proxyIP, int proPort) throws Exception {

		// logger.info("HTTP Request URL:" + url + ",HTTP Request PARAM:" + str);
		HttpClient client = new HttpClient();
		// client.getHostConfiguration().setProxy("10.41.70.8", 80);
		// client.getParams().setAuthenticationPreemptive(true);

		// 打开和URL之间的连接
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();

		if (proxyIP != null && !proxyIP.equals("")) {
			client.getHostConfiguration().setProxy(proxyIP, proPort);
			client.getParams().setAuthenticationPreemptive(true);
		}

		PostMethod httpPost = new PostMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		client.setTimeout(60000);

		httpPost.setRequestHeader("Content-type", "application/json");
		httpPost.setRequestHeader("Accept", "application/json");
		httpPost.setRequestHeader("Connection", "close");
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
				logger.debug("HTTP Request CHARSET:" + httpPost.getResponseCharSet());
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

		logger.debug("HTTP Request Result:" + responseData);
		return responseData;
	}

	private static CloseableHttpClient getHttpClinet() {

		SSLConnectionSocketFactory socketFactory = null;
		try {
			socketFactory = getSocketFactory();
		} catch (UnrecoverableKeyException | KeyManagementException | KeyStoreException | CertificateException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 创建 CloseableHttpClient 对象
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

		return httpclient;
	}

	// 忽略服务器端证书链的认证
	private static TrustManager getTrustManagers() {
		return new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		};
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 * @author zj
	 * @date 2019年8月29日
	 */
	private static SSLConnectionSocketFactory getSocketFactory() throws IOException, KeyStoreException,
			CertificateException, UnrecoverableKeyException, KeyManagementException {
		SSLContext sslContext;
		try {
			// keyStore 用来存放客户端证书
			/*
			 * KeyStore keyStore = KeyStore.getInstance("PKCS12"); FileInputStream instream
			 * = new FileInputStream(new File("d:\\test.p12")); try {
			 * keyStore.load(instream, "passwd".toCharArray()); } finally {
			 * instream.close(); }
			 */

			// 加载客户端证书，并设置HTTPS的安全协议为 TLSv1.2
			// sslContext = SSLContexts.custom().loadKeyMaterial(keyStore,
			// "passwd".toCharArray()).useProtocol("TLSv1.2").build();
			sslContext = SSLContexts.custom().build();
		} catch (Exception e) {
			return null;
		}

		try {
			sslContext.init(null, new TrustManager[] { getTrustManagers() }, new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			return null;
		}
		return new SSLConnectionSocketFactory(sslContext);
	}

	/**
	 * 
	 *信任所有https
	 * */
	public static String sendHttpGetRssDataWithHeaderNoSSL(String url, String str, HashMap<String, String> headers)
			throws Exception {

		// HttpClient client = new HttpClient();

		CloseableHttpClient client = getHttpClinet();

		HttpGet get = new HttpGet(url);

		// get.setHeader(header);

		// GetMethod httpPost = new GetMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		// client.setTimeout(5000);
		byte[] aa = null;

		// httpPost.setHeader(name, value);
		get.setHeader("user-agent",
				"rssReader:256kb.cn ,Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.96 Safari/537.36");
		// httpPost.setRequestHeader("Content-type", "application/json");
		get.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		// httpPost.setRequestHeader("X-Auth-Token", token);
		// httpPost.setRequestBody(is);

		for (String key : headers.keySet()) {
			get.setHeader(key, headers.get(key));
		}

		String responseData = null;
		CloseableHttpResponse response = null;
		try {
			Exception exception = null;
			response = client.execute(get);

			int resStatusCode = response.getStatusLine().getStatusCode();
			System.out.println("resStatusCode:" + resStatusCode);
			if (resStatusCode == HttpStatus.SC_OK || resStatusCode == 202) // 九洲云创建虚拟机接口返回202也是正确的
			{

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseData = EntityUtils.toString(entity, "utf-8");
					// return resultStr;
				} else {
					// return null;
				}

			} else {
				// System.out.println(httpPost.getStatusText());
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
			if (response != null) {
				try {
					response.close();// 最后关闭response
				} catch (IOException e) {
					logger.error("httpPost method IOException handle -- > " + e);
				}
			}

		}

		// System.out.println("return:" + responseData);
		return responseData;
	}

	/**
	 * 获取rss页面数据
	 * 
	 */
	public static String sendHttpGetRssDataWithHeader(String url, String str, HashMap<String, String> headers)
			throws Exception {

		HttpClient client = new HttpClient();

		// HttpClient client =getHttpClinet();

		GetMethod httpPost = new GetMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		client.setTimeout(5000);
		byte[] aa = null;
		httpPost.setRequestHeader("user-agent",
				"rssReader:256kb.cn ,Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.96 Safari/537.36");
		// httpPost.setRequestHeader("Content-type", "application/json");
		httpPost.setRequestHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		// httpPost.setRequestHeader("X-Auth-Token", token);
		// httpPost.setRequestBody(is);

		for (String key : headers.keySet()) {
			httpPost.setRequestHeader(key, headers.get(key));
		}

		String responseData = null;
		try {
			Exception exception = null;
			client.executeMethod(httpPost);
			int resStatusCode = httpPost.getStatusCode();
			System.out.println("resStatusCode:" + resStatusCode);
			if (resStatusCode == HttpStatus.SC_OK || resStatusCode == 202) // 九洲云创建虚拟机接口返回202也是正确的
			{
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpPost.getResponseBodyAsStream(), "utf-8"));
				System.out.println(httpPost.getResponseCharSet());
				String res = null;
				StringBuffer sb = new StringBuffer();

				while ((res = br.readLine()) != null) {

					sb.append(res + "\n");
				}
				responseData = sb.toString();
				// System.out.println("br.readLine()=" + responseData);
			} else {
				// System.out.println(httpPost.getStatusText());
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

		// System.out.println("return:" + responseData);
		return responseData;
	}

	public static String sendHttpDataWithToken(String url, String str, String token) throws Exception {

		logger.info("url:" + url + ",strParam:" + str + ",token:" + token);
		HttpClient client = new HttpClient();
		// client.getHostConfiguration().setProxy("10.41.70.8", 80);
		client.getParams().setAuthenticationPreemptive(true);
		// 如果代理需要密码验证，这里设置用户名密码
		// client.getState().setCredentials(new AuthScope("http://10.75.72.229",
		// 8080, null), new UsernamePasswordCredentials("admin",
		// "admin"));//10.75.72.229不是本机
		// client.getState().setProxyCredentials(AuthScope.ANY, new
		// UsernamePasswordCredentials("llying.iteye.com","llying"));
		PostMethod httpPost = new PostMethod(url);
		InputStream is = new java.io.ByteArrayInputStream(str.getBytes("utf-8"));
		client.setTimeout(5000);
		byte[] aa = null;
		httpPost.setRequestHeader("Content-type", "application/json");
		httpPost.setRequestHeader("Accept", "application/json");
		httpPost.setRequestHeader("X-Auth-Token", token);
		httpPost.setRequestBody(is);

		String responseData = null;
		try {
			Exception exception = null;
			client.executeMethod(httpPost);
			int resStatusCode = httpPost.getStatusCode();
			System.out.println("resStatusCode:" + resStatusCode);
			if (resStatusCode == HttpStatus.SC_OK || resStatusCode == 202) // 九洲云创建虚拟机接口返回202也是正确的
			{
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpPost.getResponseBodyAsStream(), "utf-8"));
				System.out.println(httpPost.getResponseCharSet());
				String res = null;
				StringBuffer sb = new StringBuffer();

				while ((res = br.readLine()) != null) {
					sb.append(res);
				}
				responseData = sb.toString();
				System.out.println("br.readLine()=" + responseData);
			} else {
				// System.out.println(httpPost.getStatusText());
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

		System.out.println("return:" + responseData);
		return responseData;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// String VNCParams = "{\"os-getVNCConsole\": {\"type\": \"novnc\"}}";
		// jsonStr = AESEncrypt.enCrypt("", jsonStr,"123321");
		String url = "http://58.247.117.134:5000/v2.0/tokens";
		// String str =
		// "{\"LoginId\":\"admin\",\"Password\":\"C8837B23FF8AAA8A2DDE915473CE0991\"}";
		// String str =
		// "{\"auth\": {\"tenantName\": \"admin\", \"passwordCredentials\":
		// {\"username\": \"admin\", \"password\": \"admin\"}}}";
		// String str =
		// "{\"auth\": {\"tenantName\": \"admin\", \"passwordCredentials\":
		// {\"username\": \"admin\", \"password\": \"admin\"}}}";
		String str = "{\"auth\": {\"tenantName\": \"admin\", \"passwordCredentials\": {\"username\": \"admin\", \"password\": \"admin\" }}}";
		sendHttpData(url, str, null, 0);
	}

}