package com.kxjl.base.interceptor;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.kxjl.admin.util.DateUtil;

import com.kxjl.tool.utils.UUIDUtil;
import com.kxjl.web.system.model.SysUserBean;


@Component
public class TokeUtils {

//	@Autowired
//	RedisUtil redisUtil;
	
	private ConcurrentMap<String, Map<String,String>> localUtils=new ConcurrentHashMap<>();

	public final static String uidRdisKey = "uid";
	public final static String expireRdisKey = "expireTime";
	public final static Integer expireMin = 30;

	/**
	 * 检查登陆用户token是否过期，过期重新生成，否则使用原有token ，此方法兼容多web登陆
	 * @param oldtoken
	 * @return
	 * @author zj
	 * @date 2020年3月12日
	 */
	public String refreshOrgenerateNewToken(String oldtoken) {

		boolean isExpire = checkIsExpireToken(oldtoken);

		if (isExpire) {
			String ntoken = UUIDUtil.getUUID();

			Map<String, String> datas = new HashMap<String, String>();
			datas.put(uidRdisKey, ntoken);
			datas.put(expireRdisKey,
					DateUtil.getDateStr(DateUtil.addByNum(new Date(), Calendar.MINUTE, expireMin), ""));

			//redisUtil.setMap(ntoken, datas);
			localUtils.put(ntoken, datas);
			return ntoken;
		} else
			return oldtoken;
	}

	/**
	 * 直接生成新token， 使用此方法可以进行web端单点登陆
	 * @param token
	 * @return
	 * @author zj
	 * @date 2020年3月12日
	 */
	public boolean generateNewToken(String token) {

		String ntoken = UUIDUtil.getUUID();

		Map<String, String> datas = new HashMap<String, String>();
		datas.put(uidRdisKey, token);
		datas.put(expireRdisKey, DateUtil.getDateStr(DateUtil.addByNum(new Date(), Calendar.MINUTE, expireMin), ""));

		//redisUtil.setMap(token, datas);
		localUtils.put(ntoken, datas);
		return false;
	}

	/**
	 * 检查token 是否超时，true 返回超时，false正常
	 * 
	 * @param token
	 * @return
	 * @author zj
	 * @date 2020年1月14日
	 */
	public boolean checkIsExpireToken(String token) {

		//Map<String, String> datas = redisUtil.getMap(token);// , datas);
		
		Map<String, String> datas = localUtils.get(token);// , datas);
		
		if (datas == null||datas.get(expireRdisKey)==null)
		{
			//没有
			generateNewToken(token);
			return false;
		}
		else {
			
			Date expireDate = DateUtil.getDate(datas.get(expireRdisKey), "");
			if (!expireDate.after(new Date())) {

				//redisUtil.remove(token);
				localUtils.remove(token);
				return true;
			}

			// 更新时间
			freshToken(token);
			return false;
		}
	}

	/**
	 * 刷新token过期时间
	 * 
	 * @param token
	 * @return
	 * @author zj
	 * @date 2020年1月14日
	 */
	public boolean freshToken(String token) {
		//Map<String, String> datas = redisUtil.getMap(token);
		Map<String, String> datas = localUtils.get(token);// , datas);

		datas.put(expireRdisKey, DateUtil.getDateStr(DateUtil.addByNum(new Date(), Calendar.MINUTE, expireMin), ""));

		//redisUtil.setMap(token, datas);
		localUtils.put(token, datas);
		return false;
	}

	private static ThreadLocal<SysUserBean> currentUser = new ThreadLocal<>();

	public static SysUserBean getCurrentUser() {
		return currentUser.get();
	}

	public static void setCurrentUser(SysUserBean curUser) {
		currentUser.set(curUser);
	}

}
