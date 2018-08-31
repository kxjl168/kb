package com.kxjl.web.stastic.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kxjl.web.stastic.model.ActionLog;
import com.kxjl.web.system.model.DictInfo;




public interface StasticDao {

	
	
	/**
	 * 
	 * 

select name,userid,
max(case app_name when '公积金' then stat else 0 end) '公积金',
max(case app_name when '成绩查询' then stat else 0 end) '成绩查询',
max(case app_name when '说客英语' then stat else 0 end) '说客英语',
max(case app_name when '教育谷' then stat else 0 end) '教育谷',
max(case app_name when '教育谷' then stat else 0 end) '教育谷'
from
(
-- 纵表转横表

select u.name,u.userid,stat,app.app_name,app.app_id from
(
-- 不关注的
select u.userid,name,account_code,'nofoucs' stat from user u left join pp_account_user_never_focus n
on u.userid=n.userid
where account_code is not null
union
-- 全部关注的
select * FROM(
select u.userid,name,app_id account_code,'foucs' stat from user u  join app_service_info n
) tmp 
where not exists(
select * from user u left join pp_account_user_never_focus n
on u.userid=n.userid
where account_code is not null
and u.userid=tmp.userid and account_code=tmp.account_code
)
) tmp2 left join app_service_info app on tmp2.account_code=app.app_id
left join user u on tmp2.userid=u.userid
-- 过滤条件
where app.app_name is not null
and u.name like '%t%'
and u.sex =1
and app.app_id in (1,4,5,6,7,8,9,10)
) tmp3
group by userid
	 * 
	 * 
	 * 
	 * 
	 * @param name
	 * @param sex
	 * @param age1
	 * @param age2
	 * @param caseblock   sql语句 横表转纵表 动态
	 * @param inblock  in过滤语句
	 * @return
	 * @date 2016-10-14
	 * @author zj
	 */
	public List<HashMap<Object, Object>> GetUserFocusAppList(
			@Param(value="name") String name
			,@Param(value="sex") String sex
			,@Param(value="age1") String age1
			,@Param(value="age2") String age2
			,@Param(value="caseblock") String caseblock
			,@Param(value="inblock") String inblock
			,@Param(value="start") Integer start
			,@Param(value="pageCount") Integer pageCount
			
			);
		
	
	/**
	 * 用户APP使用次数统计

//
//select name,hour,
//max(case app_name when '公积金' then stat else 0 end) '公积金',
//max(case app_name when '成绩查询' then stat else 0 end) '成绩查询',
//max(case app_name when '说客英语' then stat else 0 end) '说客英语',
//max(case app_name when '教育谷' then stat else 0 end) '教育谷'
//from (
//select name,hour,app_name,count(*)  stat
//from (
//select tmp.*,u.name,app.app_name
//,date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d') hour
// from user_action_log tmp
// left join app_service_info app on tmp.type_second=app.app_id
//left join user u on tmp.userid=u.userid
//where tmp.type_first='应用服务'
//and  
//str_to_date(action_date,'%Y-%m-%d %H:%i:%s') BETWEEN str_to_date('2016-10-01 08','%Y-%m-%d %H')
//and str_to_date('2016-10-14','%Y-%m-%d %H')
//and u.name like '%测试%'
//-- and u.age >5
//order by  str_to_date(action_date,'%Y-%m-%d %H %i %s'),tmp.userid desc  limit 50
//) tmp2 group by hour,name,app_name
//) tmp3 group by name,hour limit start,pageCount
	
	 * @param name
	 * @param sex
	 * @param age1
	 * @param age2
	 * @param caseblock
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
			,@Param(value="caseblock") String caseblock
			,@Param(value="dateFormat") String dateFormat
			,@Param(value="time1") String time1
			,@Param(value="time2") String time2	
			,@Param(value="start") Integer start
			,@Param(value="pageCount") Integer pageCount
			
			);
	
	
/*
 * 
 * 
select name,hour,
max(case app_name when '公积金' then stat else 0 end) '公积金',
max(case app_name when '成绩查询' then stat else 0 end) '成绩查询',
max(case app_name when '说客英语' then stat else 0 end) '说客英语',
max(case app_name when '教育谷' then stat else 0 end) '教育谷'
from (
select name,hour,app_name,count(*)  stat
from (

select * from (
(select tmp.*,u.name,app.name app_name
,date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d') hour
 from user_action_log tmp
 left join user_action_log_template app on  tmp.type_first=app.type_first
left join user u on tmp.userid=u.userid
where tmp.type_first  in('应用服务','办事指南')
)
union
(
select tmp.*,u.name,app.name app_name
,date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d') hour
 from user_action_log tmp
 left join user_action_log_template app on tmp.type_second=app.type_second and tmp.type_first=app.type_first
left join user u on tmp.userid=u.userid
where tmp.type_first not in('应用服务','办事指南','svr_error')
)
) innertmp where 1=1
and  
str_to_date(innertmp.action_date,'%Y-%m-%d %H:%i:%s') BETWEEN str_to_date('2016-10-01 08','%Y-%m-%d %H')
and str_to_date('2016-10-14','%Y-%m-%d %H')
and name like '%测试%'
-- and u.age >5

) tmp2 group by hour,name,app_name
) tmp3 group by name,hour
limit 0,50
	
* @param name
* @param sex
* @param age1
* @param age2
* @param caseblock
* @param dateblock
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
			,@Param(value="caseblock") String caseblock
			,@Param(value="dateFormat") String dateFormat
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
	 * 获取指定分类的具体数据
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年6月14日
	 */
	public int GetActionListCount(ActionLog query);
	/**
	 * 获取指定分类的具体数据
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年6月14日
	 */
	public List<ActionLog> GetActionList(ActionLog query);
	/**
	 * 获取具体点击数据
	 * @param query
	 * @return
	 * @date 2016-7-14
	 * @author zj
	 */
	public List<ActionLog> GetDetailList(ActionLog query);
	
	/**
	 * 获取具体点击数据
	 * @param query
	 * @return
	 * @date 2016-7-14
	 * @author zj
	 */
	public int GetDetailListCount(ActionLog query);
	
	
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
	 * 获取通用的小时统计数据
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetNormalHourDetailList(ActionLog query);
	
	
	/**
	 * 获取小时数据
	 * @param query
	 * @return
	 * @date 2016-9-13
	 * @author zj
	 */
	public List<ActionLog> GetHourDetailList(ActionLog query);
		
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
	 * @author zjGetDayDetailList
	 */
	public List<ActionLog> GetMonthDetailList(ActionLog query);
		
	
}
