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
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.blog.action.Kdata;
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

			final String ip = request.getRemoteAddr();
			final String referer = request.getHeader("Referer");

			

			//System.out.println("ips:" + ips.size() + "ip:" + ip + "/referer:" + referer);

			inblack = from(ips).anyMatch(new Predicate<String>() {
				@Override
				public boolean apply(String regex) {
					final Pattern pattern = Pattern.compile(regex);
					return pattern.matcher(ip).matches()
							|| (!StringUtils.isBlank(referer) && pattern.matcher(referer).matches());
				}
			});

			if (inblack) {
				logger.warn(ip + " is in blackiplist ,request url:" + request.getRequestURI() + " ");
			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return inblack;
	}

}
