package com.kxjl.web.stastic.service.Impl;

import static com.google.common.collect.FluentIterable.from;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.schema.StscChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.util.logging.resources.logging;

import com.kxjl.web.blog.action.Kdata;
import com.kxjl.web.stastic.dao.StasticDao;
import com.kxjl.web.stastic.model.ActionLog;
import com.kxjl.web.stastic.service.StasticService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.DateUtil;
import com.kxjl.tool.utils.IPUtils;

import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.web.system.model.DictInfo;

@Service
public class StasticServiceImpl implements StasticService {

	private Logger logger = Logger.getLogger(StasticServiceImpl.class);

	@Autowired
	StasticDao stasticDao;

	/**
	 * 查询人的访问信息（非爬虫）
	 * 
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年10月18日
	 */
	public List<ActionLog> GetUserVisitDetailList(ActionLog query) {
		// Page<ActionLog> p = PageHelper.startPage(query.getPage(),
		// query.getPageCount());

		return stasticDao.GetUserVisitDetailList(query);

	}

	/**
	 * 单用户的访问足迹
	 * 
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年10月18日
	 */
	public List<ActionLog> GetUserVisitAllList(ActionLog query) {
		// Page<ActionLog> p = PageHelper.startPage(query.getPage(),
		// query.getPageCount());

		return stasticDao.GetUserVisitAllList(query);
	}

	/**
	 * 解析代理获取ip
	 * 
	 * @param request
	 * @return
	 * @author zj
	 * @date 2018年9月8日
	 */
	public String getIpAddr(HttpServletRequest request) {

		String ipAddress = request.getHeader("X-Forwarded-For");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Real-IP");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			// prerender ip
			ipAddress = request.getParameter("X-Forwarded-For");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	public void saveStaticInfo(HttpServletRequest request, String type1, String type2, String arctileId,
			Boolean ispider) {
		final HttpServletRequest rt = request;
		final String t1 = type1;
		final String t2 = type2;
		final String blogimei = arctileId;

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					ActionLog log = new ActionLog();

					// 计算ip
					String ip = "";
					try {
						ip = getIpAddr(request);// rt.getRemoteAddr();
					} catch (Exception e) {

					}

					// logger.info("***saveStaticInfo***type1:"+type1+" type2:"+type2+" ip:"+ip+"
					// arctileId:"+arctileId);

					log.setUserid(ip);

					String city = IPUtils.getCityByIP(ip);
					log.setBlog_id(blogimei);
					log.setCity(city);
					log.setType_first(t1);
					log.setType_second(t2);
					log.setSpider_flag(ispider.toString());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = sdf.format(new Date());
					log.setAction_date(time);
					addActionLog(log);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}).run();
	}

	/**
	 * 记录访问统计原始数据
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-28
	 */
	public void saveStaticInfo(HttpServletRequest request, String type1, String type2, String arctileId) {

		String userAgent = request.getHeader("Pre-User-Agent");// prerender带过来的原始爬虫agent
		if (userAgent == null || userAgent.equals(""))
			userAgent = request.getHeader("User-Agent");

		// 无用户信息，爬虫
		boolean isspider = isInSearchUserAgent(userAgent);
		
		System.out.println("**********************");
		System.out.println("userAgent:"+userAgent);

		saveStaticInfo(request, type1, type2, arctileId, isspider);
	}
	
	public List<String> getCrawlerUserAgents() {
		//prerender都是爬虫
		List<String> crawlerUserAgents = Lists.newArrayList("baiduspider", "facebookexternalhit", "twitterbot",
				"rogerbot", "linkedinbot", "embedly", "quora link preview", "showyoubo", "outbrain", "pinterest",
				"developers.google.com/+/web/snippet", "slackbot", "vkShare", "W3C_Validator", "redditbot", "Applebot","prerender");

		// kxjl
		final String moreAgents = ConfigReader.getInstance().getProperty("crawlerUserAgents");
		if (isNotBlank(moreAgents)) {
			crawlerUserAgents.addAll(Arrays.asList(moreAgents.trim().split(",")));
		}

		return crawlerUserAgents;
	}

	/**是否为爬虫
	 * 
	 * @param userAgent
	 * @return
	 * @author zj
	 * @date 2018年10月18日
	 */
	private boolean isInSearchUserAgent(final String userAgent) {
		return from(getCrawlerUserAgents()).anyMatch(new Predicate<String>() {
			@Override
			public boolean apply(String item) {
				return userAgent.toLowerCase().contains(item.toLowerCase());
			}
		});
	}


	/**
	 * 记录访问统计原始数据
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-28
	 */
	public void saveStaticInfo(HttpServletRequest request, String type1, String type2) {

		saveStaticInfo(request, type1, type2, "");
	}

	/**
	 * 获取统计项
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-28
	 */
	public List<DictInfo> GetStaticTypeList() {
		return stasticDao.GetStaticTypeList();
	}

	/**
	 * 用户关注APP情况，根据传入的APP名称及ID列表，动态返回字段
	 * 
	 * @param name
	 * @param sex
	 * @param age1
	 * @param age2
	 * @param appNames
	 * @param appIds
	 * @return
	 * @date 2016-10-14
	 * @author zj
	 */
	public List<HashMap<Object, Object>> GetUserFocusAppList(@Param(value = "name") String name,
			@Param(value = "sex") String sex, @Param(value = "age1") String age1, @Param(value = "age2") String age2,
			@Param(value = "appNames") String[] appNames, @Param(value = "appIds") String[] appIds, int start,
			int pageCount) {

		String caseblock = "";
		String inblock = "";

		// max(case app_name when '公积金' then stat else 0 end) '公积金',
		// max(case app_name when '成绩查询' then stat else 0 end) '成绩查询',
		// max(case app_name when '说客英语' then stat else 0 end) '说客英语',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷'
		for (int i = 0; i < appNames.length; i++) {
			if (appNames[i].trim().equals(""))
				continue;
			caseblock += " max(case app_name when '" + appNames[i] + "' then stat else 0 end) '" + appNames[i] + "',";
		}
		caseblock = caseblock.substring(0, caseblock.length() - 1);

		// 1,4,5,6,7,8,9,10
		for (int i = 0; i < appIds.length; i++) {
			if (appIds[i].trim().equals(""))
				continue;
			inblock += appIds[i] + ",";
		}
		inblock = inblock.substring(0, inblock.length() - 1);

		return stasticDao.GetUserFocusAppList(name, sex, age1, age2, caseblock, inblock, start, pageCount);

	}

	/**
	 * 查询用户使用APP的次数统计
	 * 
	 * @param name
	 * @param sex
	 * @param age1
	 * @param age2
	 * @param appNames
	 * @param time_type
	 * @param time1
	 * @param time2
	 * @param dateblock
	 * @param start
	 * @param pageCount
	 * @return
	 * @date 2016-10-14
	 * @author zj
	 */
	public List<HashMap<Object, Object>> GetUserAppuseRecondList(@Param(value = "name") String name,
			@Param(value = "sex") String sex, @Param(value = "age1") String age1, @Param(value = "age2") String age2,
			@Param(value = "appNames") String[] appNames, @Param(value = "time_type") String time_type,
			@Param(value = "time1") String time1, @Param(value = "time2") String time2,
			@Param(value = "start") Integer start, @Param(value = "pageCount") Integer pageCount

	) {

		String caseblock = "";
		String dateblock = "";

		// max(case app_name when '公积金' then stat else 0 end) '公积金',
		// max(case app_name when '成绩查询' then stat else 0 end) '成绩查询',
		// max(case app_name when '说客英语' then stat else 0 end) '说客英语',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷'
		for (int i = 0; i < appNames.length; i++) {
			if (appNames[i].trim().equals(""))
				continue;
			caseblock += " max(case app_name when '" + appNames[i] + "' then stat else 0 end) '" + appNames[i] + "',";
		}
		caseblock = caseblock.substring(0, caseblock.length() - 1);

		// str_to_date(action_date,'%Y-%m-%d %H:%i:%s') BETWEEN str_to_date('2016-10-01
		// 08','%Y-%m-%d %H')
		// and str_to_date('2016-10-14','%Y-%m-%d %H')
		// dateblock= " str_to_date(action_date,'%Y-%m-%d %H:%i:%s') BETWEEN ";
		String dateFormat = "%Y-%m-%d %H";
		if (time_type.equals("HOUR"))
			dateFormat = "%Y-%m-%d %H";
		else if (time_type.equals("DAY"))
			dateFormat = "%Y-%m-%d";
		else if (time_type.equals("MONTH"))
			dateFormat = "%Y-%m";

		// dateblock+=" str_to_date('"+time1+"','"+dateFormat+"') and
		// str_to_date('"+time2+"','"+dateFormat+"') ";

		return stasticDao.GetUserAppuseRecondList(name, sex, age1, age2, caseblock, dateFormat, time1, time2, start,
				pageCount);
	}

	public List<HashMap<Object, Object>> GetUserVisitRecondList(@Param(value = "name") String name,
			@Param(value = "sex") String sex, @Param(value = "age1") String age1, @Param(value = "age2") String age2,
			@Param(value = "appNames") String[] appNames, @Param(value = "time_type") String time_type,
			@Param(value = "time1") String time1, @Param(value = "time2") String time2,
			@Param(value = "start") Integer start, @Param(value = "pageCount") Integer pageCount

	) {

		String caseblock = "";
		String dateblock = "";

		// max(case app_name when '公积金' then stat else 0 end) '公积金',
		// max(case app_name when '成绩查询' then stat else 0 end) '成绩查询',
		// max(case app_name when '说客英语' then stat else 0 end) '说客英语',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷'
		for (int i = 0; i < appNames.length; i++) {
			if (appNames[i].trim().equals(""))
				continue;
			caseblock += " max(case app_name when '" + appNames[i] + "' then stat else 0 end) '" + appNames[i] + "',";
		}
		caseblock = caseblock.substring(0, caseblock.length() - 1);

		// str_to_date(action_date,'%Y-%m-%d %H:%i:%s') BETWEEN str_to_date('2016-10-01
		// 08','%Y-%m-%d %H')
		// and str_to_date('2016-10-14','%Y-%m-%d %H')
		// dateblock= " str_to_date(action_date,'%Y-%m-%d %H:%i:%s') BETWEEN ";
		String dateFormat = "%Y-%m-%d %H";
		if (time_type.equals("HOUR"))
			dateFormat = "%Y-%m-%d %H";
		else if (time_type.equals("DAY"))
			dateFormat = "%Y-%m-%d";
		else if (time_type.equals("MONTH"))
			dateFormat = "%Y-%m";

		// dateblock+=" str_to_date('"+time1+"','"+dateFormat+"') and
		// str_to_date('"+time2+"','"+dateFormat+"') ";

		return stasticDao.GetUserVisitRecondList(name, sex, age1, age2, caseblock, dateFormat, time1, time2, start,
				pageCount);
	}

	/**
	 * 添加点击日志
	 * 
	 * @param appinfo
	 * @return
	 * @date 2016-6-21
	 * @author zj
	 */
	public int addActionLog(ActionLog log) {
		return stasticDao.addActionLog(log);
	}

	/**
	 * 获取指定分类的具体数据
	 * 
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年6月14日
	 */
	public List<ActionLog> GetActionList(ActionLog query) {
		return stasticDao.GetActionList(query);
	}

	/**
	 * 获取指定分类的具体数据
	 * 
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年6月14日
	 */
	public int GetActionListCount(ActionLog query) {
		return stasticDao.GetActionListCount(query);
	}

	/**
	 * 获取具体点击数据
	 * 
	 * @param query
	 * @return
	 * @date 2016-7-14
	 * @author zj
	 */
	public List<ActionLog> GetDetailList(ActionLog query) {
		return stasticDao.GetDetailList(query);
	}

	/**
	 * 获取小时数据
	 * 
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetHourDetailList(ActionLog query) {
		return stasticDao.GetHourDetailList(query);
	}

	/**
	 * 获取通用的小时统计数据
	 * 
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetNormalHourDetailList(ActionLog query) {
		return stasticDao.GetNormalHourDetailList(query);
	}

	/**
	 * 获取天数据
	 * 
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetDayDetailList(ActionLog query) {
		return stasticDao.GetDayDetailList(query);
	}

	/**
	 * 获取具体点击数据
	 * 
	 * @param query
	 * @return
	 * @date 2016-7-14
	 * @author zj
	 */
	public int GetDetailListCount(ActionLog query) {
		return stasticDao.GetDetailListCount(query);
	}

	/**
	 * 获取天数据
	 * 
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetMonthDetailList(ActionLog query) {
		return stasticDao.GetMonthDetailList(query);
	}

	/**
	 * 从日志表中查找之前的地市记录
	 * 
	 * @param ip
	 * @return
	 * @author zj
	 * @date 2018年8月31日
	 */
	public String GetCityFromLogData(@Param(value = "ip") String ip) {

		// 缓存查找，存储
		String city = String.valueOf(Kdata.getInstance().getCommonList(ip));
		if (city != null && !city.equals("") && !city.equals("null"))
			return city;
		else {
			city = stasticDao.GetCityFromLogData(ip);
			if (city != null && !city.equals("") && !city.equals("null")) {
				Kdata.getInstance().SavedCommonList(ip, city);
			}
			return city;
		}
	}

}
