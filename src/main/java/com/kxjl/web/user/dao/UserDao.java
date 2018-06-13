package com.kxjl.web.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kxjl.web.user.model.User;



public interface UserDao {

	
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<User> getUserPageList(User query);

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getUserPageListCount(User query);
	
	/**
	 * 添加User
	 * @param User
	 * @return
	 */
	public int addUser(User User);
	
	/**
	 * 删除User
	 * @param id
	 * @return
	 */
	public int deleteUser(@Param(value="id")Integer id);

	/**
	 * 更新User
	 * @param User
	 * @return
	 */
	public int updateUser(User User);
	
	/**
	 * 登录
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public User getUserInfoByAccountAndPass(@Param(value="id")String id,@Param(value="pass")String pass);
	
	/**
	 * 根据accountID获取User信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public User getUserInfoByAccountId(@Param(value="id")String id);
	
	/**
	 * 根据ID获取User信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public User getUserInfoById(@Param(value="id")Integer id);
}
