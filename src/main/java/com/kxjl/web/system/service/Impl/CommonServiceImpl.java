package com.kxjl.web.system.service.Impl;

import static com.google.common.collect.FluentIterable.from;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.blog.action.Kdata;
import com.kxjl.web.stastic.service.StasticService;
import com.kxjl.web.system.dao.CommonDao;
import com.kxjl.web.system.dao.DictInfoDao;
import com.kxjl.web.system.dao.MenuInfoDao;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.service.CommonService;
import com.kxjl.web.system.service.DictInfoService;

@Service(value = "commonService")
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private StasticService stasticService;

	private static Logger logger = Logger.getLogger(CommonServiceImpl.class);

	private static final String key = "black_iplist";

	/**
	 * 定时任务，清空ip缓存，重新计算
	 */
	public void resetBlackIPList() {

		Kdata.getInstance().cleanrCommonList(key);

		List<String> ips = commonDao.getBlackIPList();

		// 同步
		List<String> sips = Collections.synchronizedList(ips);

		final String morebackiplist = ConfigReader.getInstance().getProperty("blackiplist");
		if (isNotBlank(morebackiplist)) {
			sips.addAll(Arrays.asList(morebackiplist.trim().split(",")));
		}

		Kdata.getInstance().SavedCommonList(key, sips);

	}

	private List<String> getCrawlerUserAgents() {
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
	 * 禁止访问的爬虫
	 * 
	 * @return
	 * @author zj
	 * @date 2019年1月18日
	 */
	private List<String> getBlackCrawlerUserAgents() {
		List<String> crawlerUserAgents = Lists.newArrayList("");

		// kxjl
		final String moreAgents = ConfigReader.getInstance().getProperty("BlackcrawlerUserAgents");
		if (isNotBlank(moreAgents)) {
			crawlerUserAgents.addAll(Arrays.asList(moreAgents.trim().split(",")));
		}

		return crawlerUserAgents;
	}

	/**
	 * 根据agent标识 判断 是否为爬虫
	 * 
	 * @param userAgent
	 * @return
	 * @author zj
	 * @date 2018年10月18日
	 */
	public boolean isInSearchUserAgent(final String userAgent) {
		return from(getCrawlerUserAgents()).anyMatch(new Predicate<String>() {
			@Override
			public boolean apply(String item) {
				return userAgent.toLowerCase().contains(item.toLowerCase());
			}
		});
	}

	/**
	 * 根据agent标识 判断 是否在禁止访问的爬虫列表中
	 * 
	 * @param userAgent
	 * @return
	 * @author zj
	 * @date 2019年1月18日
	 */
	public boolean isInBlackSearchUserAgent(final String userAgent) {
		return from(getBlackCrawlerUserAgents()).anyMatch(new Predicate<String>() {
			@Override
			public boolean apply(String item) {
				if(!item.equals(""))
				return userAgent.toLowerCase().contains(item.toLowerCase());
				else return false;
			}
		});
	}

	/**
	 * 检测请求ip是否在黑名单
	 * 
	 * @param request
	 * @return
	 */
	public Boolean isInBlackIPList(HttpServletRequest request) {
		boolean inblack = false;

		try {

			List<String> ips = (List<String>) Kdata.getInstance().getCommonList(key);

			if (ips == null) {
				resetBlackIPList();
				ips = (List<String>) Kdata.getInstance().getCommonList(key);
			}

			final String ip = stasticService.getIpAddr(request);
			final String referer = request.getHeader("Referer");

			// System.out.println("ips:" + ips.size() + "ip:" + ip + "/referer:" + referer);

			// 总体判断是否ip段 黑名单
			inblack = from(ips).anyMatch(new Predicate<String>() {
				@Override
				public boolean apply(String regex) {
					final Pattern pattern = Pattern.compile(regex);
					return pattern.matcher(ip).matches()
							|| (!StringUtils.isBlank(referer) && pattern.matcher(referer).matches());
				}
			});

			String userAgent = request.getHeader("Pre-User-Agent");// prerender带过来的原始爬虫agent
			if (userAgent == null || userAgent.equals(""))
				userAgent = request.getHeader("User-Agent");
			
			if (inblack) {
				logger.warn(ip + " is in blackiplist ,request url:" + request.getRequestURI() + " ");

				// 在ip黑名单中，再过滤是否有爬虫标识
				// 将google ip段加入黑名单。 如果访问不带google 请求标识，直接屏蔽返回.
				if (isInSearchUserAgent(userAgent)) {
					// 爬虫

					// 是否为屏蔽爬虫标识
					if (isInBlackSearchUserAgent(userAgent)) {
						inblack = true;
					} else {
						// 即使ip段在黑名单中，但是带了爬虫标识的,并且不再爬虫黑名单中，可以访问.
						inblack = false;
					}
				}
			} else {
				// ip没有屏蔽，但是爬虫标识屏蔽了
				// 是否为屏蔽爬虫标识
				if (isInBlackSearchUserAgent(userAgent)) {
					inblack = true;
				}
			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return inblack;
	}

}
