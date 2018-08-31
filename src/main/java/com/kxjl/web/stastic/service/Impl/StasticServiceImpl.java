package com.kxjl.web.stastic.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.kxjl.web.stastic.dao.StasticDao;
import com.kxjl.web.stastic.model.ActionLog;
import com.kxjl.web.stastic.service.StasticService;
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
	 * 记录访问统计原始数据
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-28
	 */
	public void saveStaticInfo(HttpServletRequest request, String type1,
			String type2,String arctileId ) {
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
						ip = rt.getRemoteAddr();
					} catch (Exception e) {

					}

					log.setUserid(ip);

					String city = IPUtils.getCityByIP(ip);
					log.setBlog_id(blogimei);
					log.setCity(city);
					log.setType_first(t1);
					log.setType_second(t2);
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
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
	public void saveStaticInfo(HttpServletRequest request, String type1,
			String type2) {

		
		saveStaticInfo(request,type1,type2,"");
	}
	/**
	 * 获取统计项
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-28
	 */
	public List<DictInfo> GetStaticTypeList()
	{
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
	public List<HashMap<Object, Object>> GetUserFocusAppList(
			@Param(value = "name") String name,
			@Param(value = "sex") String sex,
			@Param(value = "age1") String age1,
			@Param(value = "age2") String age2
			,@Param(value="appNames") String[] appNames
			,@Param(value="appIds") String[] appIds
			,int start,
			int pageCount) {

		String caseblock = "";
		String inblock = "";

		// max(case app_name when '公积金' then stat else 0 end) '公积金',
		// max(case app_name when '成绩查询' then stat else 0 end) '成绩查询',
		// max(case app_name when '说客英语' then stat else 0 end) '说客英语',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷'
		for (int i = 0; i < appNames.length; i++) {
			if(appNames[i].trim().equals(""))
				continue;
			caseblock += " max(case app_name when '" + appNames[i]
					+ "' then stat else 0 end) '" + appNames[i] + "',";
		}
		caseblock = caseblock.substring(0, caseblock.length() - 1);

		// 1,4,5,6,7,8,9,10
		for (int i = 0; i < appIds.length; i++) {
			if(appIds[i].trim().equals(""))
				continue;
			inblock += appIds[i] + ",";
		}
		inblock = inblock.substring(0, inblock.length() - 1);

		return stasticDao.GetUserFocusAppList(name, sex, age1, age2, caseblock,
				inblock, start,
				 pageCount);

	}
	
	/**
	 * 查询用户使用APP的次数统计
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
	public List<HashMap<Object, Object>> GetUserAppuseRecondList(
			@Param(value="name") String name
			,@Param(value="sex") String sex
			,@Param(value="age1") String age1
			,@Param(value="age2") String age2
			,@Param(value="appNames") String[] appNames
			,@Param(value="time_type") String time_type
			,@Param(value="time1") String time1
			,@Param(value="time2") String time2	
			,@Param(value="start") Integer start
			,@Param(value="pageCount") Integer pageCount
			
			)
			{
		
		String caseblock = "";
		String dateblock = "";

		// max(case app_name when '公积金' then stat else 0 end) '公积金',
		// max(case app_name when '成绩查询' then stat else 0 end) '成绩查询',
		// max(case app_name when '说客英语' then stat else 0 end) '说客英语',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷'
		for (int i = 0; i < appNames.length; i++) {
			if(appNames[i].trim().equals(""))
				continue;
			caseblock += " max(case app_name when '" + appNames[i]
					+ "' then stat else 0 end) '" + appNames[i] + "',";
		}
		caseblock = caseblock.substring(0, caseblock.length() - 1);

		
//		str_to_date(action_date,'%Y-%m-%d %H:%i:%s') BETWEEN str_to_date('2016-10-01 08','%Y-%m-%d %H')
//		and str_to_date('2016-10-14','%Y-%m-%d %H')
		//dateblock= " str_to_date(action_date,'%Y-%m-%d %H:%i:%s') BETWEEN ";
		String dateFormat="%Y-%m-%d %H";
		if(time_type.equals("HOUR"))
			 dateFormat="%Y-%m-%d %H";
		else if(time_type.equals("DAY"))
			 dateFormat="%Y-%m-%d";
		else  if(time_type.equals("MONTH"))
			 dateFormat="%Y-%m";
		
		//dateblock+="  str_to_date('"+time1+"','"+dateFormat+"')	and str_to_date('"+time2+"','"+dateFormat+"') ";
		
		
		return stasticDao.GetUserAppuseRecondList(name, sex, age1, age2, caseblock,
				dateFormat,time1,time2, start,
				 pageCount);
			}
	
	
	public List<HashMap<Object, Object>> GetUserVisitRecondList(
			@Param(value="name") String name
			,@Param(value="sex") String sex
			,@Param(value="age1") String age1
			,@Param(value="age2") String age2
			,@Param(value="appNames") String[] appNames
			,@Param(value="time_type") String time_type
			,@Param(value="time1") String time1
			,@Param(value="time2") String time2	
			,@Param(value="start") Integer start
			,@Param(value="pageCount") Integer pageCount
			
			)
			{
		
		String caseblock = "";
		String dateblock = "";

		// max(case app_name when '公积金' then stat else 0 end) '公积金',
		// max(case app_name when '成绩查询' then stat else 0 end) '成绩查询',
		// max(case app_name when '说客英语' then stat else 0 end) '说客英语',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷',
		// max(case app_name when '教育谷' then stat else 0 end) '教育谷'
		for (int i = 0; i < appNames.length; i++) {
			if(appNames[i].trim().equals(""))
				continue;
			caseblock += " max(case app_name when '" + appNames[i]
					+ "' then stat else 0 end) '" + appNames[i] + "',";
		}
		caseblock = caseblock.substring(0, caseblock.length() - 1);

		
//		str_to_date(action_date,'%Y-%m-%d %H:%i:%s') BETWEEN str_to_date('2016-10-01 08','%Y-%m-%d %H')
//		and str_to_date('2016-10-14','%Y-%m-%d %H')
		//dateblock= " str_to_date(action_date,'%Y-%m-%d %H:%i:%s') BETWEEN ";
		String dateFormat="%Y-%m-%d %H";
		if(time_type.equals("HOUR"))
			 dateFormat="%Y-%m-%d %H";
		else if(time_type.equals("DAY"))
			 dateFormat="%Y-%m-%d";
		else  if(time_type.equals("MONTH"))
			 dateFormat="%Y-%m";
		
		//dateblock+="  str_to_date('"+time1+"','"+dateFormat+"')	and str_to_date('"+time2+"','"+dateFormat+"') ";
		
		
		return stasticDao.GetUserVisitRecondList(name, sex, age1, age2, caseblock,
				dateFormat,time1,time2, start,
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
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年6月14日
	 */
	public List<ActionLog> GetActionList(ActionLog query)
	{
		return stasticDao.GetActionList(query);
	}

	/**
	 * 获取指定分类的具体数据
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
	 * @param query
	 * @return
	 * @date 2016-7-14
	 * @author zj
	 */
	public int GetDetailListCount(ActionLog query)
	{
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

}
