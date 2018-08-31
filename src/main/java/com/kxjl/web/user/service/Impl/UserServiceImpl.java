package com.kxjl.web.user.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.tool.common.Md5Encrypt;
import com.kxjl.web.privilege.dao.ManagerRoleDao;
import com.kxjl.web.privilege.model.ManagerRole;
import com.kxjl.web.privilege.service.PrivilegeService;
import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.web.user.dao.UserDao;
import com.kxjl.web.user.model.User;
import com.kxjl.web.user.service.UserService;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao companyDao;

	@Autowired
	SystemParamsDao sysDao;

	@Autowired
	PrivilegeService privilegeService;

	/**
	 * 分页获取banner列表
	 * 
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
	 * 
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public User getUserInfoByAccountAndPass(@Param(value = "id") String id, @Param(value = "pass") String pass) {
		
		String md5password = Md5Encrypt.MD5(pass);
	
		return companyDao.getUserInfoByAccountAndPass(id, md5password);
	}

	/**
	 * 获取banner总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public int getUserPageListCount(User query) {
		return companyDao.getUserPageListCount(query);
	}

	@Override
	public int addUser(User User, String roleids) {
		
		String md5password = Md5Encrypt.MD5(User.getPass());
		User.setPass(md5password);
		companyDao.addUser(User);
		privilegeService.updateUserRoleList(String.valueOf(User.getRecordid()), roleids);
		return 1;
	}

	@Override
	public int deleteUser(@Param("id") Integer id) {
		return companyDao.deleteUser(id);
	}

	@Override
	public int updateUser(User User, String roleids) {
		companyDao.updateUser(User);

		privilegeService.updateUserRoleList(String.valueOf(User.getRecordid()), roleids);

		return 1;

	}

	/**
	 * 根据accountID获取User信息
	 * 
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public User getUserInfoByAccountId(@Param(value = "id") String id) {
		return companyDao.getUserInfoByAccountId(id);
	}

	/**
	 * 根据ID获取User信息
	 * 
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public User getUserInfoById(@Param(value = "id") Integer id) {
		return companyDao.getUserInfoById(id);
	}

}
