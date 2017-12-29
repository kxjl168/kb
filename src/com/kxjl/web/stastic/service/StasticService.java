package com.kxjl.web.stastic.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kxjl.web.stastic.model.ActionLog;

import com.kxjl.web.system.model.DictInfo;

public interface StasticService {

	
	
	/**
	 * 用户关注APP情况，根据传入的APP名称及ID列表，动态返回字段
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
			@Param(value="name") String name
			,@Param(value="sex") String sex
			,@Param(value="age1") String age1
			,@Param(value="age2") String age2
			,@Param(value="appNames") String[] appNames
			,@Param(value="appIds") String[] appIds,
			int start,
			int pageCount
			);
		
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
			
			);
	
	/**
	 * 查询用户全局访问统计数据
	 * @param name
	 * @param sex
	 * @param age1
	 * @param age2
	 * @param appNames
	 * @param time_type
	 * @param time1
	 * @param time2
	 * @param start
	 * @param pageCount
	 * @return
	 * @date 2016-10-14
	 * @author zj
	 */
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
			
			);

	
	/**
	 * 添加点击日志
	 * 
	 * @param appinfo
	 * @return
	 * @date 2016-6-21
	 * @author zj
	 */
	public int addActionLog(ActionLog log);

	
	/**
	 * 获取具体点击数据
	 * @param query
	 * @return
	 * @date 2016-7-14
	 * @author zj
	 */
	public int GetDetailListCount(ActionLog query);
	
	/**
	 * 获取具体点击数据
	 * @param query
	 * @return
	 * @date 2016-7-14
	 * @author zj
	 */
	public List<ActionLog> GetDetailList(ActionLog query);
	/**
	 * 获取统计项
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-28
	 */
	public List<DictInfo> GetStaticTypeList();
	
	
	/**
	 * 获取小时数据
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetHourDetailList(ActionLog query);
	
	/**
	 * 获取通用的小时统计数据
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetNormalHourDetailList(ActionLog query);
	
	
	
	/**
	 * 获取天数据
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetDayDetailList(ActionLog query);
		
	/**
	 * 获取天数据
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetMonthDetailList(ActionLog query);
		
}
