package com.kxjl.web.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.kxjl.web.system.model.TokenBean;

public interface TokenDao {

	/**
	 * 插入token信息
	 * 
	 * @param bean
	 * @return
	 * @date 2016-6-29
	 * @author zj
	 */
	int insertTokenInfo(TokenBean bean);

	/**
	 * 删除token信息
	 * 
	 * @param token
	 * @return
	 * @date 2016-6-29
	 * @author zj
	 */
	int deleteTokenInfo(String token);

	/**
	 * 检查useid是否有token信息
	 * 
	 * @param userid
	 * @return
	 * @date 2016-6-29
	 * @author zj
	 */
	TokenBean hasTokenbyUserId(String userid);

	/**
	 * 检查tokenid是否有token信息
	 * 
	 * @param userid
	 * @return
	 * @date 2016-6-29
	 * @author zj
	 */
	TokenBean hasTokenbyTokenId(String tokenId);

	/**
	 * 更新token
	 * 
	 * @param bean
	 * @return
	 * @date 2016-6-29
	 * @author zj
	 */
	int updateTokenInfo(TokenBean bean);
}