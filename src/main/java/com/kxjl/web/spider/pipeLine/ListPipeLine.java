package com.kxjl.web.spider.pipeLine;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.httpPost.HttpSendPost;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;
import com.kxjl.web.spider.BlogLinkSpider.KLinkData;
import com.kxjl.web.system.action.FileUploadController;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Html;

@Component
public class ListPipeLine implements Pipeline {

	@Autowired
	FileUploadController fileUploadController;

	@Autowired
	KurlService kurlService;

	// ResultItems保存了抽取结果，它是一个Map结构，
	// 在page.putField(key,value)中保存的数据，可以通过ResultItems.get(key)获取
	public void process(ResultItems resultItems, Task task) {

		// String id = resultItems.get("id");

		KLinkData ldata = resultItems.get("data");

		if (ldata == null)
			return;

		String url = ldata.href;// resultItems.get("url");
		String name = ldata.name;// resultItems.get("name");

		if (url != null && !url.equals("")) {

			Kurl query = new Kurl();
			query.setUrl_val(url);
			List<Kurl> urls = kurlService.getKurlPageList(query);
			if (urls != null && urls.size() > 0) {
				Kurl existData = urls.get(0);

				
				if(name!=null)
				{
					if(name.contains("-"))
					{
						existData.setUrl_name(name.substring(0, name.indexOf('-')));// 最多留10个字	
					}
					else if(name.length()>20)
					{
						existData.setUrl_name(name.substring(0, 20));// 最多留10个字						
					}
					else
					{
						existData.setUrl_name(name);// 最多留10个字			
					}
				 
				}
				
				
				if (existData.getDesc_info()!=null) {
					if(!existData.getDesc_info().contains(name))
					// 描述添加到 desc中
					existData.setDesc_info(existData.getDesc_info() + " " + name);
				}
				else
				{
					existData.setDesc_info( name);
				}
				
				existData.setVal1("1");//分类链接

				if (existData.getIcon() == null || existData.getIcon().equals("")) {
					String relativeIconurl = "";
					if (ldata.iconurl != null)
						relativeIconurl = getAndSaveIcon(url, ldata.iconurl);

					if (!relativeIconurl.equals("") && !relativeIconurl.startsWith("fail"))
						existData.setIcon(relativeIconurl);
				}

				kurlService.updateKurl(existData);
			} else {

				Kurl udata = new Kurl();
				udata.setUrl_val(url);
				if(name!=null)
				{
					if(name.contains("-"))
					{
						udata.setUrl_name(name.substring(0, name.indexOf('-')));// 最多留10个字	
					}
					else if(name.length()>20)
					{
						udata.setUrl_name(name.substring(0, 20));// 最多留10个字						
					}
					else
					{
						udata.setUrl_name(name);// 最多留10个字			
					}
				 
				}
				
				udata.setUrl_type("BLOG");
				udata.setVal1("1");//分类链接
				udata.setDesc_info(name);

				String relativeIconurl = "";
				if (ldata.iconurl != null)
					relativeIconurl = getAndSaveIcon(url, ldata.iconurl);

				if (!relativeIconurl.equals("") && !relativeIconurl.startsWith("fail"))
					udata.setIcon(relativeIconurl);

				kurlService.addKurl(udata);
				
				
				System.out.println("*******链接第一次发现***保存成功***:"+ldata.href );
			}
		}

	}

	// 存储更新网站图标
	public String getAndSaveIcon(String siteurl, String imgurl) {
		List<BasicClientCookie> cks = new ArrayList<BasicClientCookie>();
		HashMap<String, String> headers = new HashMap<>();
		headers.put("user-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
		headers.put("accept-encoding", "gzip,deflate");
		headers.put("referer", siteurl);
		headers.put("Content-type", "text/html; charset=UTF-8");
		headers.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");

		headers.put("Content-type", "image/x-icon");
		headers.put("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");

		byte[] picDats = null;

		try {
			picDats = HttpSendPost.sendHttpJSONDataNoSSLBackByte(null, true, "", true, imgurl, "", headers, cks);

		} catch (Exception e) {
			return "fail:无图标";
		}

		// for (byte b : picDats) {
		// // System.out.print(b + ",");
		// }

		if (picDats == null) {

			// File f = new File("C:\\Users\\admin\\Pictures\\test.png");//
			//
			// FileOutputStream fos = new FileOutputStream(f);
			// fos.write(picDats);
			// fos.flush();
			// fos.close();

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
	}

}
