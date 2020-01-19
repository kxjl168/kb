package com.kxjl.tool.utils;

import static com.google.common.collect.FluentIterable.from;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.kxjl.tool.config.ConfigReader;

/**
 * 爬虫 header检查等
 * SpiderUtil.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月19日
* @revision zj 2020年1月19日
* @since 1.0.1
 */
public class SpiderUtil {

	public static List<String> getCrawlerUserAgents() {
		List<String> crawlerUserAgents = Lists.newArrayList("baiduspider", "facebookexternalhit", "twitterbot",
				"rogerbot", "linkedinbot", "embedly", "quora link preview", "showyoubo", "outbrain", "pinterest",
				"developers.google.com/+/web/snippet", "slackbot", "vkShare", "W3C_Validator", "redditbot", "Applebot");

		// kxjl
		final String moreAgents = ConfigReader.getInstance().getProperty("crawlerUserAgents");
		if (isNotBlank(moreAgents)) {
			crawlerUserAgents.addAll(Arrays.asList(moreAgents.trim().split(",")));
		}

		return crawlerUserAgents;
	}

	/**
	 * 是否为爬虫
	 * 
	 * @param userAgent
	 * @return
	 * @author zj
	 * @date 2018年10月18日
	 */
	public static boolean isInSearchUserAgent(final String userAgent) {
		return from(getCrawlerUserAgents()).anyMatch(new Predicate<String>() {
			@Override
			public boolean apply(String item) {
				return userAgent.toLowerCase().contains(item.toLowerCase());
			}
		});
	}

	/**
	 * 获取爬虫标识
	 * @param userAgent
	 * @return
	 * @author zj
	 * @date 2020年1月16日
	 */
	public static String getSearchSpiderFlag(final String userAgent) {
		FluentIterable<String> s = from(getCrawlerUserAgents()).filter(new Predicate<String>() {
			@Override
			public boolean apply(String item) {
				return userAgent.toLowerCase().contains(item.toLowerCase());
			}
		});
		String flag = "";
		if (s.size() > 0)
			flag = s.get(0);
		return flag;
	}
}
