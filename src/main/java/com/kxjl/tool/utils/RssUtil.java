package com.kxjl.tool.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.kxjl.tool.httpPost.SendPostRequest;
import com.kxjl.web.autodata.pojo.RssManager;
import com.kxjl.web.autodata.pojo.RssPageList;

public class RssUtil {

	public static Document parse(URL url) throws Exception {
		SAXReader reader = new SAXReader();

		String rssStr = callUrlByGet(url, "utf-8");
		
		
		
		
		
		//剔除最前面的空行等.
		if(rssStr.indexOf("<?xml")>0)
		rssStr=rssStr.substring( rssStr.indexOf("<?xml"));

		//white space 统一使用byte读取
		ByteArrayInputStream stringReader = new ByteArrayInputStream(rssStr.getBytes("utf-8"));
		InputSource inputSource = new InputSource(stringReader);

		Document document = reader.read(inputSource);
		// Document document = reader.read(url);

		if (rssStr.contains("<entry>"))
		{
			//atom格式
			return parseAtom(rssStr);
		}

		return document;
	}

	private static String callUrlByGet(URL url, String charset) {

		String rssData = "";
		try {
			
			HashMap<String, String> headers=new HashMap<>();
			 rssData=SendPostRequest.sendHttpGetRssDataWithHeader(url.toString(), "", headers);
			
			if(rssData.contains("<?xml")
					||rssData.contains("<feed xmlns")
					)
			{
				return rssData;
			}
			else {
				
				//此网站特殊处理- -！，验证key~
				if(url.toString().contains("helingqi.com"))
				{
				//请求数据异常
				if(rssData.contains("document.cookie"))
				{
					int varIndex=rssData.indexOf("var ");
					int varEndIndex=rssData.indexOf(";");
					//var aj=12, r=0.7;
					String valparam=rssData.substring(varIndex+("var ".length()), varEndIndex);
					String[] val1= valparam.split(",")[0].split("=");
					String[] val2= valparam.split(",")[1].trim().split("=");
					
					int cookieStartIndex=rssData.indexOf("document.cookie='");
					String cookie=rssData.substring(cookieStartIndex+("document.cookie='".length()), rssData.indexOf(";",cookieStartIndex));
					
					int urlIndex=rssData.indexOf("window.location.href='")+("window.location.href='").length();
					
					String newurl=rssData.substring(urlIndex, rssData.indexOf("'",urlIndex));
					newurl+=Double.parseDouble(val1[1])+ Double.parseDouble(val2[1]);
					if(cookie!=null&&!cookie.equals(""))
					{
						try {
							//cookie=cookie.split("=")[1];
							headers.put("cookie", cookie);
							rssData=SendPostRequest.sendHttpGetRssDataWithHeader(newurl, "", headers);
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}
				}
				}
				//document.cookie='spm_key=3a79335d30b57ad70b688a07077bbe62;
				
			}
					
		
			/*URLConnection connection = url.openConnection();
			
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = reader.readLine()) != null) {
				result += line;
				result += "\n";
			}
			*/
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return rssData;
	}

	public static Document parseAtom(URL url) throws DocumentException {
		HashMap nsMap = new HashMap();
		nsMap.put("ns", "http://www.w3.org/2005/Atom");

		SAXReader reader = new SAXReader();

		reader.getDocumentFactory().setXPathNamespaceURIs(nsMap);

		Document document = reader.read(url);
		return document;
	}

	public static Document parseAtom(String rssStr) throws Exception {
		HashMap nsMap = new HashMap();
		nsMap.put("ns", "http://www.w3.org/2005/Atom");

		SAXReader reader = new SAXReader();
		reader.getDocumentFactory().setXPathNamespaceURIs(nsMap);

		//white space 统一使用byte读取
				ByteArrayInputStream stringReader = new ByteArrayInputStream(rssStr.getBytes("utf-8"));
				InputSource inputSource = new InputSource(stringReader);
				
	
		Document document = reader.read(inputSource);

		return document;
	}

	public static void main(String[] args) {
		String atomurl = "https://bwskyer.com/feed/atom";

		try {
			Document dt = parse(new URL(atomurl));
			RssManager rssm = rssParse(dt);

			rssAtomPagesParse(dt);
			// RssManager rssm =rssAtomParse(atomurl);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 解析rss基本数据
	 * 
	 * @param document
	 * @return
	 * @author zj
	 * @date 2019年1月28日
	 */
	public static RssManager rssAtomParse(String url) {

		HashMap nsMap = new HashMap();
		nsMap.put("ns", "http://www.w3.org/2005/Atom");

		SAXReader reader = new SAXReader();

		reader.getDocumentFactory().setXPathNamespaceURIs(nsMap);

		RssManager rmanager = new RssManager();
		try {
			Document document = reader.read(new URL(url));

			Node noderssTitle = document.selectSingleNode("/feed/ns:title");

			Node node_remark = document.selectSingleNode("//channel/description");
			rmanager.setName(noderssTitle.getText());
			rmanager.setRemark(node_remark.getText());
		} catch (Exception e) {
			System.out.println(e);
		}

		return rmanager;
	}

	/**
	 * 解析rss基本数据
	 * 
	 * @param document
	 * @return
	 * @author zj
	 * @date 2019年1月28日
	 */
	public static RssManager rssParse(Document document) {

		/*
		 * <channel> <title>破袜子</title> <atom:link href="https://pewae.com/feed"
		 * rel="self" type="application/rss+xml" /> <link>https://pewae.com</link>
		 * <description>一个脱离不了低级趣味的人</description>
		 */

		RssManager rmanager = new RssManager();
		rmanager.setRtype("rss");
		try {

			Node noderssTitle = document.selectSingleNode("//channel/title");

			if (noderssTitle == null) {
				noderssTitle = document.selectSingleNode("/feed/ns:title");
				rmanager.setRtype("atom");
			}

			Node node_remark = document.selectSingleNode("//channel/description");
			if (node_remark == null)
				node_remark = document.selectSingleNode("/feed/ns:subtitle");
			
			Node node_lastdate = document.selectSingleNode("//channel/lastBuildDate");
			if (node_lastdate == null)
			{
				node_lastdate = document.selectSingleNode("/feed/ns:updated");
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.ENGLISH);

				try {
					String date = node_lastdate.getText().replace("T", " ").replace("Z", " UTC");
					Date pdate = fm.parse(date);
					rmanager.setLastRssPageDate(DateUtil.getDateStr(pdate, ""));
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
			else {

				SimpleDateFormat fm = new SimpleDateFormat("EEE,d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
				try {
					Date pdate = fm.parse(node_lastdate.getText().replaceAll("\n\t", ""));
					rmanager.setLastRssPageDate(DateUtil.getDateStr(pdate, ""));
				} catch (ParseException e) {
					
				}
			}

			
			Node node_link = document.selectSingleNode("//channel/link");
			String page_link="";
			if (node_link == null)
			{
				page_link = ((Element) document.selectSingleNode("/feed/ns:link")).attributeValue("href");
			}
			else {
				page_link=node_link.getText();
			}
				
			
			rmanager.setName(noderssTitle.getText());
			if(node_remark!=null)
			rmanager.setRemark(node_remark.getText());
			rmanager.setPage_link(page_link);
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return rmanager;
	}

	/**
	 * 解析rss文章数据
	 * 
	 * @param document
	 * @return
	 * @author zj
	 * @date 2019年1月28日
	 */
	public static List<RssPageList> rssPagesParse(Document document) {

		/*
		 * <item> <title>没有态度的态度</title>
		 * <link>https://pewae.com/2019/01/my-attitude-is-no-attitude.html</link>
		 * <comments>https://pewae.com/2019/01/my-attitude-is-no-attitude.html#comments<
		 * /comments> <pubDate>Tue, 22 Jan 2019 05:32:29 +0000</pubDate>
		 * 
		 * <item> <title>组装一个聚合物锂电池充电宝</title>
		 * <link>https://xsinger.me/diy/794.html</link>
		 * <guid>https://xsinger.me/diy/794.html</guid> <pubDate>Sat, 26 Jan 2019
		 * 22:55:00 +0800</pubDate> <dc:creator>山小炮</dc:creator>
		 * <description><![CDATA[老婆的iPhone背夹充电宝的充电插头断了（如下图1），然后在网上买了个新的给她，后来发现蠢了，
		 * 其实网上有卖这种背夹充电宝的主板的，买回来直接替换就行，一般价格也就10多块钱，但你买一个全新的背...]]></description>
		 * <content:encoded xml:lang="zh-CN"><![CDATA[
		 */

		List<RssPageList> pagelist = new ArrayList<>();
		List<Node> itemlist = document.selectNodes("//channel/item");
		String link = document.selectSingleNode("//channel/link").getText();
		for (Node node : itemlist) {

			RssPageList pageitem = new RssPageList();

			Node noderssTitle = ((Element) node).element("title");

			Node node_link = ((Element) node).element("link");
			Node node_pubDate = ((Element) node).element("pubDate");

			Node node_content = ((Element) node).element("encoded");

			pageitem.setTitle(noderssTitle.getText());
			pageitem.setLink(node_link.getText());

			pageitem.setId(node_link.getText());

			if (node_content != null) {
				String content = node_content.getText();
				content = content.replaceAll("src=\"/", "src=\"" + link + "/");
				pageitem.setContext(content);
			} else {
				pageitem.setContext("未提供内容订阅");
			}

			SimpleDateFormat fm = new SimpleDateFormat("EEE,d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			try {
				Date pdate = fm.parse(node_pubDate.getText());
				pageitem.setUpdateDate(DateUtil.getDateStr(pdate, ""));
			} catch (ParseException e) {
				try {
					fm = new SimpleDateFormat("EEE,d MMM yyyy HH:mm:ss Z", Locale.CHINESE);
					Date pdate = fm.parse(node_pubDate.getText());
					pageitem.setUpdateDate(DateUtil.getDateStr(pdate, ""));
				} catch (Exception e2) {
					pageitem.setTitle(pageitem.getTitle() + "(pubDate错误)");
					pageitem.setUpdateDate(DateUtil.getNowStr(""));
				}
				// e.printStackTrace();
			}

			pagelist.add(pageitem);
		}

		return pagelist;
	}

	/**
	 * 解析rss atom文章数据
	 * 
	 * @param document
	 * @return
	 * @author zj
	 * @date 2019年1月28日
	 */
	public static List<RssPageList> rssAtomPagesParse(Document document) {

		/*
		 * <item> <title>没有态度的态度</title>
		 * <link>https://pewae.com/2019/01/my-attitude-is-no-attitude.html</link>
		 * <comments>https://pewae.com/2019/01/my-attitude-is-no-attitude.html#comments<
		 * /comments> <pubDate>Tue, 22 Jan 2019 05:32:29 +0000</pubDate>
		 * 
		 * <item> <title>组装一个聚合物锂电池充电宝</title>
		 * <link>https://xsinger.me/diy/794.html</link>
		 * <guid>https://xsinger.me/diy/794.html</guid> <pubDate>Sat, 26 Jan 2019
		 * 22:55:00 +0800</pubDate> <dc:creator>山小炮</dc:creator>
		 * <description><![CDATA[老婆的iPhone背夹充电宝的充电插头断了（如下图1），然后在网上买了个新的给她，后来发现蠢了，
		 * 其实网上有卖这种背夹充电宝的主板的，买回来直接替换就行，一般价格也就10多块钱，但你买一个全新的背...]]></description>
		 * <content:encoded xml:lang="zh-CN"><![CDATA[
		 */

		List<RssPageList> pagelist = new ArrayList<>();
		List<Node> itemlist = document.selectNodes("//ns:entry");
		String link = ((Element) document.selectSingleNode("/feed/ns:link")).attributeValue("href");
		for (Node node : itemlist) {

			RssPageList pageitem = new RssPageList();

			Node noderssTitle = ((Element) node).element("title");

			Node node_link = ((Element) node).element("link");
			Node node_pubDate = ((Element) node).element("updated");

			Node node_content = ((Element) node).element("content");
			Node node_summary = ((Element) node).element("summary");

			pageitem.setTitle(noderssTitle.getText());
			String url = ((Element) node_link).attributeValue("href");
			pageitem.setLink(url);

			pageitem.setId(url);
			String content ="";
			if (node_content != null) {
				 content = node_content.getText();
				 if(content.contains("src=\"//"))
				 {
					 //图片  双斜杠，子域名
					 content = content.replaceAll("src=\"//", "src=\"" + "http:" + "//");
				 }
				 else
				content = content.replaceAll("src=\"/", "src=\"" + link + "/");
				pageitem.setContext(content);
			} else {
				if(node_summary!=null)
				{
					content = node_summary.getText();
					 if(content.contains("src=\"//"))
					 {
						 //图片  双斜杠，子域名
						 content = content.replaceAll("src=\"//", "src=\"" + "http:" + "//");
					 }
					 else
					content = content.replaceAll("src=\"/", "src=\"" + link + "/");
					pageitem.setContext(content);
				}
				else
				pageitem.setContext("未提供内容订阅");
			}

			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.ENGLISH);

			try {
				String date = node_pubDate.getText().replace("T", " ").replace("Z", " UTC");
				Date pdate = fm.parse(date);
				pageitem.setUpdateDate(DateUtil.getDateStr(pdate, ""));
			} catch (ParseException e) {
				pageitem.setTitle(pageitem.getTitle() + "(pubDate错误)");
				pageitem.setUpdateDate(DateUtil.getNowStr(""));
				// e.printStackTrace();
			}

			pagelist.add(pageitem);
		}

		return pagelist;
	}

}
