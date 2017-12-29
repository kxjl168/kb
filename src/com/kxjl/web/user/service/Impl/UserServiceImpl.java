package com.kxjl.web.user.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.web.user.dao.UserDao;
import com.kxjl.web.user.model.User;
import com.kxjl.web.user.service.UserService;

@Service(value="UserService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao companyDao;
	
	@Autowired
	SystemParamsDao sysDao;

	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public List<User> getUserPageList(User query) {
		return companyDao.getUserPageList(query);
	}
	
	/**
	 * 登录
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public User getUserInfoByAccountAndPass(@Param(value="id")String id,@Param(value="pass")String pass)
	{
		return companyDao.getUserInfoByAccountAndPass(id, pass);
	}
	

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public int getUserPageListCount(User query) {
		return companyDao.getUserPageListCount(query);
	}

	@Override
	public int addUser(User User) {
		return companyDao.addUser(User);
	}

	@Override
	public int deleteUser(@Param("id") Integer id) {
		return companyDao.deleteUser(id);
	}

	@Override
	public int updateUser(User User) {
		return companyDao.updateUser(User);
	}
	/**
	 * 根据accountID获取User信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public User getUserInfoByAccountId(@Param(value="id")String id)
	{
		return companyDao.getUserInfoByAccountId(id);
	}
	
	/**
	 * 根据ID获取User信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public User getUserInfoById(@Param(value="id")Integer id)
	{
		return companyDao.getUserInfoById(id);
	}

	
}
