package com.kxjl.web.spider;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kxjl.base.util.UUIDUtil;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.autodata.pojo.LinkRelation;
import com.kxjl.web.autodata.service.LinkRelationService;
import com.kxjl.web.spider.pipeLine.ListPipeLine;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 抓取地区区域列表 30w+ AreaListCompanySpider.java.
 * 
 * @author zj
 * @version 1.0.1 2019年10月15日
 * @revision zj 2019年10月15日
 * @since 1.0.1
 */

@Controller
@RequestMapping("/spider")
public class BlogLinkSpider implements PageProcessor {

	protected Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(140000);

	protected String realurlpre = "http://192.168.1.66:3000/";

	protected String starturl = "https://lms.im/links";

	protected Spider spider = null;

	@Autowired
	ListPipeLine listPipeLine;

	@Autowired
	LinkRelationService linkRelationService;

	@RequestMapping("/getbloglist")
	public void getbloglist(String url) {
		num = 0;
		String realurl = "";
		try {
			// realurl = URLEncoder.encode(starturl, "utf-8");
			realurl = starturl;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		realurlpre=ConfigReader.getInstance().getProperty("preurl", realurlpre);
		

		starturl="https://www.giuem.com/links/";
		
		if(url!=null&&!url.equals(""))
		starturl =url;// "https://www.giuem.com/links/";
		

		// domain标识一个爬虫uuid
		Site.me().setRetryTimes(2).setSleepTime(500).setTimeOut(300000);

		spider = Spider.create(this).setUUID(UUIDUtil.get32UUID()).addUrl(realurlpre + starturl)
				.addPipeline(listPipeLine).thread(4);

		spider.run();
	}

	@RequestMapping("/stopbloglist")
	public void stop() {
		try {
			if (spider != null)
				spider.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static int num = 0;

	// private void findMoreListPage(Page page) {
	//
	//
	// // 发现省份
	// List<String> provinceurls = page.getHtml().links().replace(poolurlpre,
	// realurlpre)
	// .regex("https://www\\.qichacha\\.com/g_([A-Z]+)\\.html").all();
	// for (String pro : provinceurls) {
	// String newurlpro = "";
	// try {
	//
	// //if (pro.equals("JS")) {
	// //先爬江苏的
	//
	// // newurl = URLEncoder.encode(url, "utf-8");
	// newurlpro = "https://www.qichacha.com/gongsi_area.html?prov=" + pro;//
	// newurlpro = poolurl + newurlpro;
	//
	// page.addTargetRequest(newurlpro);
	// //}
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// continue;
	// }
	// }
	//
	// if
	// (page.getUrl().regex("https://www\\.qichacha\\.com/gongsi_area\\.html\\?prov=([A-Z]+)$").match())
	// {
	// String Procode = page.getUrl().replace(poolurlpre, realurlpre)
	// .regex("https://www\\.qichacha\\.com/gongsi_area\\.html\\?prov=([A-Z]+)",
	// 1).toString();
	//
	// // System.out.println(page.getRawText());
	//
	// String Proname = page.getHtml().xpath("//a[@class=pills-item][@href=/g_" +
	// Procode + ".html]/text()")
	// .toString();
	//
	// // href="/g_BJ.html"
	//
	// if (Proname != null) {
	// // 发现地市
	// List<String> cityurls = page.getHtml().links().replace(poolurlpre,
	// realurlpre)
	// .regex("https://www\\.qichacha\\.com/g_" + Procode + "_(.*)\\.html").all();
	// for (String cityid : cityurls) {
	//
	// String cityname = page.getHtml().xpath("//a[@href=/g_" + Procode + "_" +
	// cityid + ".html]/text()")
	// .toString();
	//
	// ProCity pcity = new ProCity();
	// pcity.setCitycode(cityid);
	//
	// pcity.setProcode(Procode);
	//
	// pcity.setCityname(cityname);
	// pcity.setProname(Proname);
	// ProCity tcity = proCityService.selectProCityByCityAndPro(pcity);
	// if (tcity == null) {
	// proCityService.saveProCity(pcity);
	// }
	//
	// try {
	// // newurl = URLEncoder.encode(url, "utf-8");
	// String pageurls = "https://www.qichacha.com/gongsi_area.html?prov=" + Procode
	// + "&city="
	// + cityid;//
	//
	// for (int i = 1; i < 1000; i++) { // 1100页
	// // url full listpage
	// String newurl = "";
	//
	// try {
	// // newurl = URLEncoder.encode(url, "utf-8");
	// newurl = pageurls + "&p=" + i;//
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// continue;
	// }
	//
	// if (redisUtil.getParam(newurl) != null &&
	// !redisUtil.getParam(newurl).equals("")) {
	// // 已经有redis标记，
	// // page.setSkip(true);
	// // return;
	// }
	//
	// // &符号
	// newurl = newurl.replace("&", and);
	// newurl = poolurl + newurl;
	//
	// if (!newurl.equals("")) {
	//
	// // if (num <= 5) { // test one
	// page.addTargetRequest(newurl);
	// // num++;
	// // }
	// }
	//
	// }
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// continue;
	// }
	// }
	// }
	//
	// } else if
	// (page.getUrl().regex("(https://www\\.qichacha\\.com/gongsi_area\\.html\\?prov=.*\\%26city=.*%26p=.*)")
	// .match()) {
	// //findMoreDetailPage(page);
	// }
	// }

	public class KLinkData {
		public String href;
		public String name;
		public String iconurl;
		String type;// 1,page 2,link,

		public KLinkData(String h, String n) {
			this.href = h;
			this.name = n;
		}

	}

	public KLinkData getLink(String linktext) {
		Html link = new Html(linktext);
		String href = link.xpath("//a/@href").get();
		String name = link.xpath("//a/allText()").get();
		// System.out.println("href:" + href + " name:" + name);

		return new KLinkData(href, name);
	}

	public String addLinks(Page page, String linktxt, String iconurl) {

		String destUrl = "";

		String cururl = page.getUrl().get().replace(realurlpre, "");

		KLinkData ldata = getLink(linktxt);
		if (iconurl != null && !iconurl.equals("")) {
			ldata.iconurl = iconurl;
			String title = page.getHtml().xpath("//title/text()").get();
			ldata.name = title;
			ldata.href = page.getUrl().get().replace(realurlpre, "");
		}

		if (ldata != null && ldata.href != null && ldata.name != null && !ldata.name.equals("")) {
			if (!ldata.href.startsWith("http"))
				ldata.href = cururl + ldata.href;

			
			if(!isusefullurl(ldata))
				return "";
			
			page.addTargetRequest(realurlpre + ldata.href);
			
			System.out.println("targetUrl:"+ldata.href);

			char[] chars = ldata.href.toCharArray();
			int num = 0;
			for (char c : chars) {
				if (c == '/')
					num++;
			}
			// http://xxx.xx.com/ 最多3个 / 否则排除为非个人博客链接
			if (num < 3 && (ldata.name != null && !ldata.name.equals("")) && !ldata.href.contains("?")) {

				page.putField("data", ldata);
				destUrl = ldata.href;

				if (isblogurl(destUrl))
					System.out.println("新链接:" + ldata.href);
			} else if (num == 3 && ldata.href.endsWith("/")) {

				page.putField("data", ldata);
				destUrl = ldata.href;

				if (isblogurl(destUrl))
					System.out.println("新链接:" + ldata.href);
			} else {
				// System.out.println(ldata.href + " 可能为非博客链接,排除");
			}

		}

		return destUrl;

	}
	
	public boolean isusefullurl(KLinkData ldata)
	{
		String url=ldata.href;
		String name=ldata.name;
		//排除域名
		String otherurl="huaweicloud;danielbarenboim.com;it168.com;yahoo.cn;zhihu.com;seofh.com;wpkeji.com;mingzhugou.com;hiwannz.com;logosc.cn;sbol.cn;mrju.cn;zblogcn.com;icdsoft.com;mingzhugou.cn;jiaxunip.com;capbone.com;taobao.com;sarah-ooo.com;0769jw.com;baozoummm.com;bbs;feed;pate.pw;baidu.com;lusongsong.com;iis.net;blog.wyj.me;github.io;weibo.com;qq.com;aliyun.com;hexo.com;gov.cn;sina.com;wordpress;github.com;alibaba.com;typecho.org;wordpress.;upyun.;umeng.";
		String[] ourls=otherurl.split(";");
		for (String yumin : ourls) {
			if(url.contains(yumin))
				return false;
		}
		
//		
		String othername="工具;接口;解析;建站;故障;电脑;货币;信息;优惠;科技;大全;图库;域名;管理;中国;服务;小说;注册;考试;主机;备案;新闻;日报;运维;模板;答案;软件;人生;音乐;知识;主机;宅男;八字;作文;设备;教程;视频;医疗;剖腹;导航;管家;源码;加盟;教授;长生;官方;公司;网络;控制;营销;网赚;创业;财经;采集;我;SEO;Server;彩票;福利;福彩;结果;域名;购买;激情;WordPress";
		String[] onames=othername.split(";");
		for (String yumin : onames) {
			if(name.contains(yumin))
				return false;
		}
		
		return true;
	}

	public boolean isblogurl(String url) {
		
		//排除特定完整域名
		String nurl = "https://help.aliyun.com/;http://beian.miit.gov.cn;https://account.console.aliyun.com/;https://homenew.console.aliyun.com/;https://www.youku.com/;https://www.uc.cn/;https://www.alibaba.com/;https://beian.miit.gov.cn/;http://typecho.org/;https://anonradio.net/;https://kefu.upyun.com/;http://typecho.org;https://cli.github.com;https://cn.wordpress.org/;https://www.umeng.com/;https://kongtoudi.com;https://www.sitemaps.org/;https://help.upyun.com/;";
		String noblogurl = ConfigReader.getInstance().getProperty("noblogurl", nurl);
		if (noblogurl.contains(url)) {
			return false;
		}
		
		
	
		
		return true;
	}

	public String iconUrl(Page page) {
		Html html = page.getHtml();
		String siteurl = page.getUrl().get();
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

		return imgurl;

	}

	/**
	 * 查找友情链接，友链 关键字， dom parent 7层以内，获取所有 a link。
	 * 
	 * @param page
	 * @author:kxjl
	 * @date 2020年10月22日
	 */
	public void getFlink(Page page) {

		String[][] urls = { { "//a[@rel=\" nofollow ugc\"]", "" }, { "//a[@target=\"_blank\"]", "" },

				// 友情汇总页面
				{ "//a", ".*关于.*" },

				{ "//a", ".*链接|友链|友情链接|友人帐|朋友.*" },

		};

		// 目标blog链接
		//List<String> txts = page.getHtml().xpath("//a[@rel=\" nofollow ugc\"]").all();
		//List<String> txt2s = page.getHtml().xpath("//a[@target=\"_blank\"]").all();
		//List<String> txt3s = page.getHtml().xpath("//a").regex("关于").all();

		
		List<String> desturls = new ArrayList<String>();
		
		for (String[] udata : urls) {

			List<String> rst = new ArrayList();
			Selectable sb = null;
			sb = page.getHtml().xpath(udata[0]);
			if (!udata[1].equals(""))
				sb=sb.regex(udata[1]);
			

			rst = sb.all();
			

			if(rst!=null&&rst.size()>0)
			{
			for (String string : rst) {

				// System.out.println(string);
				String dest = addLinks(page, string, "");
				if (!dest.equals("") && !desturls.contains(dest))
					desturls.add(dest);

			}
			}
		}

		//
		// for (String string : txts) {
		//
		// // System.out.println(string);
		// String dest = addLinks(page, string, "");
		// if (!dest.equals("") && !desturls.contains(dest))
		// desturls.add(dest);
		//
		// }
		//
		// for (String string : txt2s) {
		// // System.out.println(string);
		// String dest = addLinks(page, string, "");
		// if (!dest.equals("") && !desturls.contains(dest))
		// desturls.add(dest);
		// }
		//
		// for (String string : links) {
		// // System.out.println(string);
		// String dest = addLinks(page, string, "");
		// if (!dest.equals("") && !desturls.contains(dest))
		// desturls.add(dest);
		// }
		//
		// for (String string : link2s) {
		// // System.out.println(string);
		// String dest = addLinks(page, string, "");
		// if (!dest.equals("") && !desturls.contains(dest))
		// desturls.add(dest);
		// }

		addLinks(page, page.getUrl().get(), iconUrl(page));

		// 存储发现关系
		String cururl = page.getUrl().get().replace(realurlpre, "");

		saveRelation(cururl, desturls);

	}

	private void saveRelation(String fromurl, List<String> tourl) {

		LinkRelation fdata = new LinkRelation();
		fdata.setFromurl(fromurl);
		linkRelationService.deleteByFromLink(fdata);

		for (String string : tourl) {

			LinkRelation data = new LinkRelation();
			data.setId(UUIDUtil.getUUID());
			data.setFromurl(fromurl);
			data.setTourl(string);
			linkRelationService.saveLinkRelation(data);

		}

	}

	@Override
	public void process(Page page) {

		// System.out.println(page.getRawText());

		getFlink(page);

	}

	// private void findMoreDetailPage(Page page) {
	// // 页面上的企业数据，详情页面链接
	// // https://www.qichacha.com/firm_xxxx.html -->
	// // https://www.qichacha.com/cbase_xxxx.html
	// List<String> detailurlalls = page.getHtml().links().replace(poolurlpre,
	// realurlpre)
	// .regex("https://www\\.qichacha\\.com/firm_(.*)\\.html").all();
	//
	// List<String> detailurls =
	// page.getHtml().xpath("//table[@class=m_srchList]//tr//td[2]/a/@href")
	// .regex(".*firm_(.*)\\.html").all();
	//
	// for (String url : detailurls) {
	// // url id
	// String newurl = "";
	// try {
	// // newurl = URLEncoder.encode(realurlpre + "cbase_" + url + ".html",
	// "utf-8");
	//
	// newurl = realurlpre + "cbase_" + url + ".html";
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// continue;
	// }
	//
	// String curname = page.getHtml()
	// .xpath("//table[@class=m_srchList]//tr//td[2]/a[@href=/firm_" + url +
	// ".html]/text()").toString();
	//
	//
	// }
	// }

	// pullContent

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {

		// BlogLinkSpider d=new BlogLinkSpider();
		// d.getbloglist();

		String html = "<!DOCTYPE html><html dir=\"ltr\" lang=\"zh-Hans\"><head>\r\n" + "<meta charset=\"utf-8\">\r\n"
				+ "<title>友链 - DTZ9.NET</title>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://dtz9.net/assets/forum-2ab71c7d.css\">\r\n"
				+ "<link rel=\"canonical\" href=\"https://dtz9.net/p/1\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1\">\r\n"
				+ "<meta name=\"description\" content=\"心灵博客哥斯拉GoaKay豆豆の鱼ZM沫 Mcoo–猫叔肥鱼Blog常阳时光俍注樗顾 林海草原木子博客青山绿水小蕾影集行者晓路 浮白载笔逆时针博客赫赫文王格子老师三毛的毛 生活的左上角梦幻辰风王志勇Debug客栈牧羊人 乌帮图风萧萧兮易水寒蓝天个人博客ChrAlpha 的幻想乡TSBBLOG 大缺缺Ray’s...\">\r\n"
				+ "<meta name=\"theme-color\" content=\"#9E9E9E\">\r\n"
				+ "<meta name=\"application-name\" content=\"DTZ9.NET\">\r\n"
				+ "<meta name=\"robots\" content=\"index, follow\">\r\n"
				+ "<meta name=\"twitter:card\" content=\"summary\">\r\n"
				+ "<meta name=\"twitter:image\" content=\"https://dtz9.net/assets/favicon-pioc58tx.png\">\r\n"
				+ "<meta name=\"article:published_time\" content=\"2020-09-19T15:01:47+00:00\">\r\n"
				+ "<meta name=\"twitter:url\" content=\"https://dtz9.net/p/1-links\">\r\n"
				+ "<meta name=\"twitter:description\" content=\"心灵博客哥斯拉GoaKay豆豆の鱼ZM沫 Mcoo–猫叔肥鱼Blog常阳时光俍注樗顾 林海草原木子博客青山绿水小蕾影集行者晓路 浮白载笔逆时针博客赫赫文王格子老师三毛的毛 生活的左上角梦幻辰风王志勇Debug客栈牧羊人 乌帮图风萧萧兮易水寒蓝天个人博客ChrAlpha 的幻想乡TSBBLOG 大缺缺Ray’s...\">\r\n"
				+ "<meta name=\"article:updated_time\" content=\"2020-09-27T13:21:21+00:00\">\r\n"
				+ "<link rel=\"shortcut icon\" href=\"https://dtz9.net/assets/favicon-pioc58tx.png\">\r\n"
				+ "<meta property=\"og:site_name\" content=\"DTZ9.NET\">\r\n"
				+ "<meta property=\"og:type\" content=\"website\">\r\n"
				+ "<meta property=\"og:image\" content=\"https://dtz9.net/assets/favicon-pioc58tx.png\">\r\n"
				+ "<meta property=\"og:url\" content=\"https://dtz9.net/p/1-links\">\r\n"
				+ "<meta property=\"og:description\" content=\"心灵博客哥斯拉GoaKay豆豆の鱼ZM沫 Mcoo–猫叔肥鱼Blog常阳时光俍注樗顾 林海草原木子博客青山绿水小蕾影集行者晓路 浮白载笔逆时针博客赫赫文王格子老师三毛的毛 生活的左上角梦幻辰风王志勇Debug客栈牧羊人 乌帮图风萧萧兮易水寒蓝天个人博客ChrAlpha 的幻想乡TSBBLOG 大缺缺Ray’s...\">\r\n"
				+ "<script type=\"application/ld+json\">[{\"@context\":\"http:\\/\\/schema.org\",\"@type\":\"WebPage\",\"publisher\":{\"@type\":\"Organization\",\"name\":\"DTZ9.NET\",\"url\":\"https:\\/\\/dtz9.net\",\"description\":\"\",\"logo\":null},\"image\":\"https:\\/\\/dtz9.net\\/assets\\/favicon-pioc58tx.png\",\"text\":\"\\n\\u5fc3\\u7075\\u535a\\u5ba2\\u54e5\\u65af\\u62c9GoaKay\\u8c46\\u8c46\\u306e\\u9c7cZM\\u6cab\\nMcoo\\u2013\\u732b\\u53d4\\u80a5\\u9c7cBlog\\u5e38\\u9633\\u65f6\\u5149\\u4fcd\\u6ce8\\u6a17\\u987e\\n\\u6797\\u6d77\\u8349\\u539f\\u6728\\u5b50\\u535a\\u5ba2\\u9752\\u5c71\\u7eff\\u6c34\\u5c0f\\u857e\\u5f71\\u96c6\\u884c\\u8005\\u6653\\u8def\\n\\u6d6e\\u767d\\u8f7d\\u7b14\\u9006\\u65f6\\u9488\\u535a\\u5ba2\\u8d6b\\u8d6b\\u6587\\u738b\\u683c\\u5b50\\u8001\\u5e08\\u4e09\\u6bdb\\u7684\\u6bdb\\n\\u751f\\u6d3b\\u7684\\u5de6\\u4e0a\\u89d2\\u68a6\\u5e7b\\u8fb0\\u98ce\\u738b\\u5fd7\\u52c7Debug\\u5ba2\\u6808\\u7267\\u7f8a\\u4eba\\n\\u4e4c\\u5e2e\\u56fe\\u98ce\\u8427\\u8427\\u516e\\u6613\\u6c34\\u5bd2\\u84dd\\u5929\\u4e2a\\u4eba\\u535a\\u5ba2ChrAlpha \\u7684\\u5e7b\\u60f3\\u4e61TSBBLOG\\n\\u5927\\u7f3a\\u7f3aRay\\u2019s Blog\\u660e\\u5929\\u7684\\u4e4c\\u4e91M-x Chris-An-Emacser\\u6728\\u6cd5\\u4f20\\n\\u653b\\u57ce\\u72ee\\u7b14\\u8bb0LFhacks.com\\u4e0d\\u5410\\u4e0d\\u5feb\\u6d6e\\u6e38\\u7b14\\u8bb0\\u4e0d\\u4e00\\u6b62\\u96c6\\nMine4everBORBER \\u6d6e\\u767d\\u8f7d\\u7b14\",\"datePublished\":\"2020-09-19T15:01:47+00:00\",\"url\":\"https:\\/\\/dtz9.net\\/p\\/1-links\",\"description\":\"\\u5fc3\\u7075\\u535a\\u5ba2\\u54e5\\u65af\\u62c9GoaKay\\u8c46\\u8c46\\u306e\\u9c7cZM\\u6cab Mcoo\\u2013\\u732b\\u53d4\\u80a5\\u9c7cBlog\\u5e38\\u9633\\u65f6\\u5149\\u4fcd\\u6ce8\\u6a17\\u987e \\u6797\\u6d77\\u8349\\u539f\\u6728\\u5b50\\u535a\\u5ba2\\u9752\\u5c71\\u7eff\\u6c34\\u5c0f\\u857e\\u5f71\\u96c6\\u884c\\u8005\\u6653\\u8def \\u6d6e\\u767d\\u8f7d\\u7b14\\u9006\\u65f6\\u9488\\u535a\\u5ba2\\u8d6b\\u8d6b\\u6587\\u738b\\u683c\\u5b50\\u8001\\u5e08\\u4e09\\u6bdb\\u7684\\u6bdb \\u751f\\u6d3b\\u7684\\u5de6\\u4e0a\\u89d2\\u68a6\\u5e7b\\u8fb0\\u98ce\\u738b\\u5fd7\\u52c7Debug\\u5ba2\\u6808\\u7267\\u7f8a\\u4eba \\u4e4c\\u5e2e\\u56fe\\u98ce\\u8427\\u8427\\u516e\\u6613\\u6c34\\u5bd2\\u84dd\\u5929\\u4e2a\\u4eba\\u535a\\u5ba2ChrAlpha \\u7684\\u5e7b\\u60f3\\u4e61TSBBLOG \\u5927\\u7f3a\\u7f3aRay\\u2019s...\",\"dateModified\":\"2020-09-27T13:21:21+00:00\"},{\"@context\":\"http:\\/\\/schema.org\",\"@type\":\"WebSite\",\"url\":\"https:\\/\\/dtz9.net\\/\",\"potentialAction\":{\"@type\":\"SearchAction\",\"target\":\"https:\\/\\/dtz9.net\\/?q={search_term_string}\",\"query-input\":\"required name=search_term_string\"}}]</script>\r\n"
				+ "<link rel=\"alternate\" type=\"application/atom+xml\" title=\"论坛活动\" href=\"https://dtz9.net/atom\">\r\n"
				+ "<link rel=\"alternate\" type=\"application/atom+xml\" title=\"论坛新帖\" href=\"https://dtz9.net/atom/d\">\r\n"
				+ "\r\n" + "</head>\r\n" + "<body class=\"no-touch\">\r\n"
				+ "<base href=\"https://dtz9.net\" target=\"_blank\">\r\n"
				+ "<div id=\"app\" class=\"App App--page\">\r\n"
				+ "<div id=\"app-navigation\" class=\"App-navigation\"><div class=\"Navigation ButtonGroup App-backControl\"><a class=\"Button Navigation-back Button--icon hasIcon\" href=\"/\" title=\"返回主题列表\" active=\"false\" type=\"button\" aria-label=\"返回主题列表\"><i class=\"icon fas fa-chevron-left Button-icon\"></i></a></div></div>\r\n"
				+ "<div id=\"drawer\" class=\"App-drawer\">\r\n" + "<header id=\"header\" class=\"App-header\">\r\n"
				+ "<div id=\"header-navigation\" class=\"Header-navigation\"><div class=\"Navigation ButtonGroup \"><a class=\"Button Navigation-back Button--icon hasIcon\" href=\"/\" title=\"返回主题列表\" active=\"false\" type=\"button\" aria-label=\"返回主题列表\"><i class=\"icon fas fa-chevron-left Button-icon\"></i></a></div></div>\r\n"
				+ "<div class=\"container\">\r\n" + "<h1 class=\"Header-title\">\r\n"
				+ "<a href=\"https://dtz9.net\" id=\"home-link\">\r\n" + "DTZ9.NET\r\n" + "</a>\r\n" + "</h1>\r\n"
				+ "<div id=\"header-primary\" class=\"Header-primary\"><ul class=\"Header-controls\"><li class=\"item-link1\"><a class=\"LinksButton Button Button--link\" target=\"_blank\" href=\"https://ditou.org/\" title=\"博客\">博客</a></li><li class=\"item-link2\"><a class=\"LinksButton Button Button--link active\" target=\"\" href=\"https://dtz9.net/p/1-links\" title=\"友链\">友链</a></li></ul></div>\r\n"
				+ "<div id=\"header-secondary\" class=\"Header-secondary\"><ul class=\"Header-controls\"><li class=\"item-search\"><div class=\"Search \"><div class=\"Search-input\"><input class=\"FormControl\" type=\"search\" placeholder=\"搜索\"></div><ul class=\"Dropdown-menu Search-results\"></ul></div></li><li class=\"item-locale\"><div class=\"ButtonGroup Dropdown dropdown  Dropdown--select Dropdown--select itemCount2\"><button class=\"Dropdown-toggle Button Button--link\" data-toggle=\"dropdown\"><span class=\"Button-label\">简体中文</span><i class=\"icon fas fa-sort Button-caret\"></i></button><ul class=\"Dropdown-menu dropdown-menu \"><li class=\"\"><button active=\"true\" class=\" hasIcon\" type=\"button\" title=\"简体中文\"><i class=\"icon fas fa-check Button-icon\"></i><span class=\"Button-label\">简体中文</span></button></li><li class=\"\"><button active=\"false\" class=\" hasIcon\" type=\"button\" title=\"English\"><span class=\"Button-label\">English</span></button></li></ul></div></li><li class=\"item-signUp\"><button class=\"Button Button--link\" type=\"button\" title=\"注册\"><span class=\"Button-label\">注册</span></button></li><li class=\"item-logIn\"><button class=\"Button Button--link\" type=\"button\" title=\"登录\"><span class=\"Button-label\">登录</span></button></li></ul></div>\r\n"
				+ "</div>\r\n" + "</header>\r\n" + "</div>\r\n" + "<main class=\"App-content\">\r\n"
				+ "<div id=\"content\"><div class=\"Pages\"><div class=\"Pages-page\"><header class=\"Hero PageHero\"><div class=\"container\"><ul class=\"PageHero-items\"><li class=\"item-title\"><h2 class=\"PageHero-title\"><a href=\"/p/1-links\">友链</a></h2></li></ul></div></header><div class=\"Pages-container container\"><div class=\"Post-body\"><table><thead><tr><th style=\"text-align:center\"></th><th style=\"text-align:center\"></th><th style=\"text-align:center\"></th><th style=\"text-align:center\"></th><th style=\"text-align:center\"></th></tr></thead>\r\n"
				+ "<tbody><tr><td style=\"text-align:center\"><a href=\"http://blog.dngz.net/\" rel=\" nofollow ugc\">心灵博客</a></td><td style=\"text-align:center\"><a href=\"http://gojira.net/\" rel=\" nofollow ugc\">哥斯拉</a></td><td style=\"text-align:center\"><a href=\"https://www.goakay.com/\" rel=\" nofollow ugc\">GoaKay</a></td><td style=\"text-align:center\"><a href=\"http://www.midousir.com/\" rel=\" nofollow ugc\">豆豆の鱼</a></td><td style=\"text-align:center\"><a href=\"http://zmmio.com/\" rel=\" nofollow ugc\">ZM沫</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://www.mcoo.cc/\" rel=\" nofollow ugc\">Mcoo–猫叔</a></td><td style=\"text-align:center\"><a href=\"https://www.feiyuyu.net/\" rel=\" nofollow ugc\">肥鱼Blog</a></td><td style=\"text-align:center\"><a href=\"https://cyhour.com/\" rel=\" nofollow ugc\">常阳时光</a></td><td style=\"text-align:center\"><a href=\"http://oneinf.com/\" rel=\" nofollow ugc\">俍注</a></td><td style=\"text-align:center\"><a href=\"https://novcu.com/\" rel=\" nofollow ugc\">樗顾</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://lhcy.org/\" rel=\" nofollow ugc\">林海草原</a></td><td style=\"text-align:center\"><a href=\"https://muuzi.cn/\" rel=\" nofollow ugc\">木子博客</a></td><td style=\"text-align:center\"><a href=\"https://www.huhexian.com/\" rel=\" nofollow ugc\">青山绿水</a></td><td style=\"text-align:center\"><a href=\"https://xiaolei.blog/\" rel=\" nofollow ugc\">小蕾影集</a></td><td style=\"text-align:center\"><a href=\"http://stuit.cn/Xiaolu/\" rel=\" nofollow ugc\">行者晓路</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"http://www.winature.com/\" rel=\" nofollow ugc\">浮白载笔</a></td><td style=\"text-align:center\"><a href=\"http://www.mydes.top/\" rel=\" nofollow ugc\">逆时针博客</a></td><td style=\"text-align:center\"><a href=\"https://kqh.me/\" rel=\" nofollow ugc\">赫赫文王</a></td><td style=\"text-align:center\"><a href=\"https://manman.qian.lu/\" rel=\" nofollow ugc\">格子老师</a></td><td style=\"text-align:center\"><a href=\"https://www.seoyx.cn/\" rel=\" nofollow ugc\">三毛的毛</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://bwskyer.com/\" rel=\" nofollow ugc\">生活的左上角</a></td><td style=\"text-align:center\"><a href=\"https://www.mhcf.net/\" rel=\" nofollow ugc\">梦幻辰风</a></td><td style=\"text-align:center\"><a href=\"http://www.auiou.com/\" rel=\" nofollow ugc\">王志勇</a></td><td style=\"text-align:center\"><a href=\"https://www.debuginn.cn/\" rel=\" nofollow ugc\">Debug客栈</a></td><td style=\"text-align:center\"><a href=\"https://www.shephe.com/\" rel=\" nofollow ugc\">牧羊人</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://wbt5.com/\" rel=\" nofollow ugc\">乌帮图</a></td><td style=\"text-align:center\"><a href=\"https://www.fiisi.com/\" rel=\" nofollow ugc\">风萧萧兮易水寒</a></td><td style=\"text-align:center\"><a href=\"http://www.along168.cn/\" rel=\" nofollow ugc\">蓝天个人博客</a></td><td style=\"text-align:center\"><a href=\"https://chralpha.com/\" rel=\" nofollow ugc\">ChrAlpha 的幻想乡</a></td><td style=\"text-align:center\"><a href=\"https://tsb2blog.com/\" rel=\" nofollow ugc\">TSBBLOG</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://www.daqueque.com/\" rel=\" nofollow ugc\">大缺缺</a></td><td style=\"text-align:center\"><a href=\"https://raycoder.me/\" rel=\" nofollow ugc\">Ray’s Blog</a></td><td style=\"text-align:center\"><a href=\"https://tmr.js.org/\" rel=\" nofollow ugc\">明天的乌云</a></td><td style=\"text-align:center\"><a href=\"https://chriszheng.science/\" rel=\" nofollow ugc\">M-x Chris-An-Emacser</a></td><td style=\"text-align:center\"><a href=\"https://www.mofazhuan.com/\" rel=\" nofollow ugc\">木法传</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"http://qumac.com/\" rel=\" nofollow ugc\">攻城狮笔记</a></td><td style=\"text-align:center\"><a href=\"https://www.lfhacks.com/\" rel=\" nofollow ugc\">LFhacks.com</a></td><td style=\"text-align:center\"><a href=\"https://mianao.info/\" rel=\" nofollow ugc\">不吐不快</a></td><td style=\"text-align:center\"><a href=\"https://fffou.com/\" rel=\" nofollow ugc\">浮游笔记</a></td><td style=\"text-align:center\"><a href=\"http://buyivi.xyz/\" rel=\" nofollow ugc\">不一止集</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://cxcat.cn/\" rel=\" nofollow ugc\">Mine4ever</a></td><td style=\"text-align:center\"><a href=\"https://borber.cn/\" rel=\" nofollow ugc\">BORBER</a></td><td style=\"text-align:center\"><a href=\"http://www.winature.com/\" rel=\" nofollow ugc\"> 浮白载笔</a></td><td style=\"text-align:center\"></td><td style=\"text-align:center\"></td></tr></tbody></table></div></div></div></div></div>\r\n"
				+ "<div id=\"flarum-loading\" style=\"display: none;\">\r\n" + "正在加载...\r\n" + "</div>\r\n"
				+ "<noscript>\r\n" + "    <div class=\"Alert\">\r\n" + "        <div class=\"container\">\r\n"
				+ "            请使用更现代的浏览器并启用 JavaScript 以获得最佳浏览体验。\r\n" + "        </div>\r\n" + "    </div>\r\n"
				+ "</noscript>\r\n" + "<div id=\"flarum-loading-error\" style=\"display: none\">\r\n"
				+ "<div class=\"Alert\">\r\n" + "<div class=\"container\">\r\n" + "加载论坛时出错，请强制刷新页面重试。\r\n"
				+ "</div>\r\n" + "</div>\r\n" + "</div>\r\n" + "<noscript id=\"flarum-content\">\r\n"
				+ "    <div class=\"container\">\r\n" + "    <h2>友链</h2>\r\n" + "\r\n" + "    <div>\r\n"
				+ "        <table><thead><tr><th style=\"text-align:center\"></th><th style=\"text-align:center\"></th><th style=\"text-align:center\"></th><th style=\"text-align:center\"></th><th style=\"text-align:center\"></th></tr></thead>\r\n"
				+ "<tbody><tr><td style=\"text-align:center\"><a href=\"http://blog.dngz.net/\" rel=\" nofollow ugc\">心灵博客</a></td><td style=\"text-align:center\"><a href=\"http://gojira.net/\" rel=\" nofollow ugc\">哥斯拉</a></td><td style=\"text-align:center\"><a href=\"https://www.goakay.com/\" rel=\" nofollow ugc\">GoaKay</a></td><td style=\"text-align:center\"><a href=\"http://www.midousir.com/\" rel=\" nofollow ugc\">豆豆の鱼</a></td><td style=\"text-align:center\"><a href=\"http://zmmio.com/\" rel=\" nofollow ugc\">ZM沫</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://www.mcoo.cc/\" rel=\" nofollow ugc\">Mcoo–猫叔</a></td><td style=\"text-align:center\"><a href=\"https://www.feiyuyu.net/\" rel=\" nofollow ugc\">肥鱼Blog</a></td><td style=\"text-align:center\"><a href=\"https://cyhour.com/\" rel=\" nofollow ugc\">常阳时光</a></td><td style=\"text-align:center\"><a href=\"http://oneinf.com/\" rel=\" nofollow ugc\">俍注</a></td><td style=\"text-align:center\"><a href=\"https://novcu.com/\" rel=\" nofollow ugc\">樗顾</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://lhcy.org/\" rel=\" nofollow ugc\">林海草原</a></td><td style=\"text-align:center\"><a href=\"https://muuzi.cn/\" rel=\" nofollow ugc\">木子博客</a></td><td style=\"text-align:center\"><a href=\"https://www.huhexian.com/\" rel=\" nofollow ugc\">青山绿水</a></td><td style=\"text-align:center\"><a href=\"https://xiaolei.blog/\" rel=\" nofollow ugc\">小蕾影集</a></td><td style=\"text-align:center\"><a href=\"http://stuit.cn/Xiaolu/\" rel=\" nofollow ugc\">行者晓路</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"http://www.winature.com/\" rel=\" nofollow ugc\">浮白载笔</a></td><td style=\"text-align:center\"><a href=\"http://www.mydes.top/\" rel=\" nofollow ugc\">逆时针博客</a></td><td style=\"text-align:center\"><a href=\"https://kqh.me/\" rel=\" nofollow ugc\">赫赫文王</a></td><td style=\"text-align:center\"><a href=\"https://manman.qian.lu/\" rel=\" nofollow ugc\">格子老师</a></td><td style=\"text-align:center\"><a href=\"https://www.seoyx.cn/\" rel=\" nofollow ugc\">三毛的毛</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://bwskyer.com/\" rel=\" nofollow ugc\">生活的左上角</a></td><td style=\"text-align:center\"><a href=\"https://www.mhcf.net/\" rel=\" nofollow ugc\">梦幻辰风</a></td><td style=\"text-align:center\"><a href=\"http://www.auiou.com/\" rel=\" nofollow ugc\">王志勇</a></td><td style=\"text-align:center\"><a href=\"https://www.debuginn.cn/\" rel=\" nofollow ugc\">Debug客栈</a></td><td style=\"text-align:center\"><a href=\"https://www.shephe.com/\" rel=\" nofollow ugc\">牧羊人</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://wbt5.com/\" rel=\" nofollow ugc\">乌帮图</a></td><td style=\"text-align:center\"><a href=\"https://www.fiisi.com/\" rel=\" nofollow ugc\">风萧萧兮易水寒</a></td><td style=\"text-align:center\"><a href=\"http://www.along168.cn/\" rel=\" nofollow ugc\">蓝天个人博客</a></td><td style=\"text-align:center\"><a href=\"https://chralpha.com/\" rel=\" nofollow ugc\">ChrAlpha 的幻想乡</a></td><td style=\"text-align:center\"><a href=\"https://tsb2blog.com/\" rel=\" nofollow ugc\">TSBBLOG</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://www.daqueque.com/\" rel=\" nofollow ugc\">大缺缺</a></td><td style=\"text-align:center\"><a href=\"https://raycoder.me/\" rel=\" nofollow ugc\">Ray’s Blog</a></td><td style=\"text-align:center\"><a href=\"https://tmr.js.org/\" rel=\" nofollow ugc\">明天的乌云</a></td><td style=\"text-align:center\"><a href=\"https://chriszheng.science/\" rel=\" nofollow ugc\">M-x Chris-An-Emacser</a></td><td style=\"text-align:center\"><a href=\"https://www.mofazhuan.com/\" rel=\" nofollow ugc\">木法传</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"http://qumac.com/\" rel=\" nofollow ugc\">攻城狮笔记</a></td><td style=\"text-align:center\"><a href=\"https://www.lfhacks.com/\" rel=\" nofollow ugc\">LFhacks.com</a></td><td style=\"text-align:center\"><a href=\"https://mianao.info/\" rel=\" nofollow ugc\">不吐不快</a></td><td style=\"text-align:center\"><a href=\"https://fffou.com/\" rel=\" nofollow ugc\">浮游笔记</a></td><td style=\"text-align:center\"><a href=\"http://buyivi.xyz/\" rel=\" nofollow ugc\">不一止集</a></td></tr>\r\n"
				+ "<tr><td style=\"text-align:center\"><a href=\"https://cxcat.cn/\" rel=\" nofollow ugc\">Mine4ever</a></td><td style=\"text-align:center\"><a href=\"https://borber.cn/\" rel=\" nofollow ugc\">BORBER</a></td><td style=\"text-align:center\"><a href=\"http://www.winature.com/\" rel=\" nofollow ugc\"> 浮白载笔</a></td><td style=\"text-align:center\"></td><td style=\"text-align:center\"></td></tr></tbody></table>\r\n"
				+ "    </div>\r\n" + "</div>\r\n" + "\r\n" + "</noscript>\r\n" + "<div class=\"App-composer\">\r\n"
				+ "<div class=\"container\">\r\n"
				+ "<div id=\"composer\"><div class=\"Composer \" style=\"height: 200px; display: none; bottom: -200px;\"><div class=\"Composer-handle\" style=\"cursor: row-resize;\"></div><ul class=\"Composer-controls\"><li class=\"item-minimize App-backControl\"><button title=\"最小化\" itemclassname=\"App-backControl\" class=\"Button Button--icon Button--link hasIcon\" type=\"button\" aria-label=\"最小化\"><i class=\"icon fas fa-minus minimize Button-icon\"></i></button></li><li class=\"item-fullScreen\"><button title=\"全屏\" class=\"Button Button--icon Button--link hasIcon\" type=\"button\" aria-label=\"全屏\"><i class=\"icon fas fa-expand Button-icon\"></i></button></li><li class=\"item-close\"><button title=\"关闭\" class=\"Button Button--icon Button--link hasIcon\" type=\"button\" aria-label=\"关闭\"><i class=\"icon fas fa-times Button-icon\"></i></button></li></ul><div class=\"Composer-content\"></div></div></div>\r\n"
				+ "</div>\r\n" + "</div>\r\n" + "</main>\r\n" + "</div>\r\n"
				+ "<div id=\"modal\"><div class=\"ModalManager modal fade\"></div></div>\r\n"
				+ "<div id=\"alerts\"><div class=\"AlertManager\"></div></div>\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "\r\n" + "\r\n" + "<a href=\"#\" class=\"scroll-up\" style=\"display: none;\">\r\n"
				+ "<i class=\"fa fa-chevron-up\"></i>\r\n" + "</a>\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "<div><div id=\"CustomFooter\" class=\"false\"><div class=\"container\"><div class=\"Footer--Content\" style=\"height: 0px;\">© 2020 DTZ9.NET</div><div class=\"Footer--Icons\"><i class=\"Footer--Show fa fa-info-circle false\"></i><i class=\"Footer--Hide fa fa-caret-down hidden\"></i></div></div></div></div></body></html>\r\n"
				+ "";

		Html htmlobj = new Html(html);

		try {

			List<String> txts = htmlobj.xpath("//a[@rel=\" nofollow ugc\"]").all();

			List<String> txt2s = htmlobj.xpath("//a[@target=\"_blank\"]").all();

			List<String> links = htmlobj.xpath("//a[@href]").all();
			List<String> link2s = htmlobj.xpath("//a[@href]").regex(".*友情链接.*").all();

			for (String string : txts) {
				System.out.println(string);

				Html link = new Html(string);
				String href = link.xpath("//a/@href").get();
				String name = link.xpath("//a/text()").get();
				System.out.println("href:" + href + " name:" + name);
			}

			for (String string : txt2s) {
				System.out.println(string);
			}

			for (String string : links) {
				System.out.println(string);
			}

			for (String string : link2s) {
				System.out.println(string);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		int a = 1;
		a++;
		int b = a;

		/*
		 * Spider.create(new
		 * GithubRepoPageProcessor()).addUrl("https://github.com/code4craft")
		 * .addPipeline(new MongoPipeLine()).thread(5).run();
		 */
	}

}
