package com.kxjl.tool.utils;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.kxjl.web.autodata.pojo.RssManager;
import com.kxjl.web.autodata.pojo.RssPageList;

public class RssUtil {

	public static Document parse(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
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

		try {

			Node noderssTitle = document.selectSingleNode("//channel/title");
			Node node_remark = document.selectSingleNode("//channel/description");
			rmanager.setName(noderssTitle.getText());
			rmanager.setRemark(node_remark.getText());
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

			pageitem.setId(node_link.getText().substring(node_link.getText().lastIndexOf("/"),
					node_link.getText().length() - 1));
			
			
			if(node_content!=null)
			{
			String content= node_content.getText();
			content=content.replaceAll("src=\"/", "src=\""+link+"/");
			pageitem.setContext(content);
			}
			else
			{
				pageitem.setContext("未提供内容订阅");
			}
			
			
			
			SimpleDateFormat fm = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss Z", Locale.ENGLISH);
			try {
				Date pdate = fm.parse(node_pubDate.getText());
				pageitem.setUpdateDate(DateUtil.getDateStr(pdate, ""));
			} catch (ParseException e) {
				try {
					fm = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss Z", Locale.CHINESE);
					Date pdate = fm.parse(node_pubDate.getText());
					pageitem.setUpdateDate(DateUtil.getDateStr(pdate, ""));
				} catch (Exception e2) {
					// TODO: handle exception
				}
				//e.printStackTrace();
			}

			pagelist.add(pageitem);
		}

		return pagelist;
	}

}
