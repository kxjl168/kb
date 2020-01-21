package com.kxjl.tool.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.Icon;

import org.apache.http.impl.cookie.BasicClientCookie;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.httpPost.HttpSendPost;
import com.kxjl.web.system.action.FileUploadController;


import us.codecraft.webmagic.selector.Html;

/**
 * 图标处理-网站图标解析获取 IconUtil.java.
 * 
 * @author zj
 * @version 1.0.1 2020年1月20日
 * @revision zj 2020年1月20日
 * @since 1.0.1
 */
@Component
public class IconUtil {

	@Autowired
	FileUploadController fileUploadController;
	
	private static Logger log=LoggerFactory.getLogger(IconUtil.class);

	public static void main(String[] args) {
		IconUtil iu = new IconUtil();
		String siteurl = "https://xiaozhou.net";
		String siteurl1 = "https://boke112.com";
		String siteurl2 = "http://webmagic.io/docs";
		String siteurl3 = "https://stackoverflow.com";
		iu.getAndUploadSiteIcon(siteurl);
	}

	/***
	 * 
	 * 根据url站点根目录 获取站点icon图片
	 ***/
	public String getAndUploadSiteIcon(String siteurl) {

		String imgLocalPath = "";

		List<BasicClientCookie> cks = new ArrayList<BasicClientCookie>();
		HashMap<String, String> headers = new HashMap<>();
		headers.put("user-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
		headers.put("accept-encoding", "gzip,deflate");
		headers.put("referer", siteurl);
		headers.put("Content-type", "text/html; charset=UTF-8");
		headers.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");

		try {
			
			String prerederUrl= ConfigReader.getInstance().getProperty("preurl", "http://127.0.0.1:13333/");
			String prerenderSiteurl=prerederUrl+siteurl;
			
			if (ConfigReader.getInstance().getProperty("debug", "false").equals("true")) {
				log.info("site url:"+prerenderSiteurl);
			}
			
			//通过prerender加载完整的页面
			String htmlStr = HttpSendPost.sendHttpJSONDataNoSSL(null, true, "", true, prerenderSiteurl, "", headers, cks);

			Html html = new Html(htmlStr);
			List<String> imgurls = new ArrayList<>();
			String imgurl = "";
			try {
				// <link rel="shortcut icon"
				// href="//img.alicdn.com/tfs/TB1_ZXuNcfpK1RjSZFOXXa6nFXa-32-32.ico"
				// type="image/x-icon">
				// <link href="https://csdnimg.cn/public/favicon.ico" rel="SHORTCUT ICON">

				imgurl = html.xpath("//link/outerHtml()").regex("rel=\".*icon\"").toString();
				if (imgurl != null && !imgurl.equals("")) {

					Html html2 = new Html("<html><link " + imgurl + "</link></html>");
					imgurl = html2.xpath("//link/@href").toString();

				}
				if (imgurl == null || imgurl.equals("")) {
					imgurl = html.xpath("//link/outerHtml()").regex("rel=\".*SHORTCUT.*\"").toString();
					if (imgurl != null && !imgurl.equals("")) {

						Html html2 = new Html("<html><link " + imgurl + "</link></html>");
						imgurl = html2.xpath("//link/@href").toString();

					}
				}
				if (imgurl == null || imgurl.equals("")) {
					imgurl = html.xpath("//link/outerHtml()").regex("rel=\".*shortcut.*\"").toString();
					if (imgurl != null && !imgurl.equals("")) {

						Html html2 = new Html("<html><link " + imgurl + "</link></html>");
						imgurl = html2.xpath("//link/@href").toString();

					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

			if (imgurl == null || imgurl.equals(""))
				imgurl = siteurl + "/favicon.ico";
			else if (!imgurl.startsWith("http")) {

				String website = siteurl.substring(siteurl.indexOf(":") + 3);

				if (imgurl.contains(website)) {
					imgurl = siteurl.substring(0, siteurl.indexOf(":") + 3) + imgurl;
					imgurl = imgurl.replace("////", "//");
					imgurl = imgurl.replace("///", "//");
				} else if (imgurl.contains("common.cnblogs.com")) {
					imgurl = "https:" + imgurl;

				} else {
					if (!imgurl.startsWith("//"))
						imgurl = siteurl + "/" + imgurl;
					else
						imgurl = siteurl + imgurl;
				}
			}

			if (ConfigReader.getInstance().getProperty("debug", "false").equals("true")) {
				log.info("icon url:"+imgurl);
			}
			//System.out.println(imgurl);
			// String url = siteurl;// "https://www.xiaoz.me/favicon.ico";

			headers.put("Content-type", "image/x-icon");
			headers.put("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");

			byte[] picDats = HttpSendPost.sendHttpJSONDataNoSSLBackByte(null, true, "", true, imgurl, "", headers, cks);

			for (byte b : picDats) {
				// System.out.print(b + ",");
			}

			if (false) {

				File f = new File("C:\\Users\\admin\\Pictures\\test.png");//

				System.out.println("11");
				// byte[] b=iconStr.getBytes("utf-8");
				// for (byte c : picDats) {
				// System.out.print(c);
				// }

				FileOutputStream fos = new FileOutputStream(f);
				fos.write(picDats);
				fos.flush();
				fos.close();

				return "";
			}

			if (picDats != null && picDats.length > 0) {
				String jsondata = fileUploadController.uploadBytePng(picDats, "favicon.png", "siteurl");
				// 解析responsedate
				JSONObject jsonRes = new JSONObject(jsondata);
				if ((jsonRes.getString("ResponseCode")).equals("200")) {
					return jsonRes.getString("relativeURL");
				} else {
					String returnMsg = jsonRes.optString("ResponseMsg");
					return "fail:" + returnMsg;
				}
			} else
				return "fail";

		} catch (

		Exception e) {
			System.out.println(e.getMessage());
		}

		return imgLocalPath;

	}
}
