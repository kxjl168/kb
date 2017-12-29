package com.kxjl.web.version.service;

import java.util.List;

import com.kxjl.web.version.model.IncrementalUpgrade;
import com.kxjl.web.version.model.UpgradePackage;
import com.kxjl.web.version.model.UpgradePackageVersionInfo;
import com.kxjl.web.version.model.UpgradeUser;
import com.kxjl.web.version.model.VersionInfo;

/**
 * 版本信息Service
 * @author kangyongji
 *
 */
public interface VersionInfoService {

	
	/**
	 * 在远程文件服务上生成差异文件 （远程文件服务器链接的数据库与应用服务器一致）
	 * @param clientType
	 * @param versionNum
	 * @param package_path
	 * @date 2016-7-28
	 * @author zj
	 */
	public void add_diff_inFileSvr(final String clientType,final String versionNum,final String package_path);
	
	
	/**
	 * 根据客户端类型查询版本
	 * @param clientType
	 * @return
	 */
	public List<VersionInfo> queryVersionInfo(VersionInfo param);
	
	/**
	 * 根据升级包编号查询升级包
	 * @param upgradePackageId
	 * @return
	 */
	public UpgradePackage queryPackageInfo(int upgradePackageId);
	
	/**
	 * 根据低版本号和客户端类型查询差异包
	 * @param lowVersion
	 * @return
	 */
	public IncrementalUpgrade queryIncrementalPackage(IncrementalUpgrade upgrade);
	
	/**
	 * 根据版本号查询定向升级的用户
	 * @param versionNum
	 * @return
	 */
	public List<UpgradeUser> queryUpgradeUser(UpgradeUser versionNum);
	
	/**
	 * 查询版本信息返回给页面
	 * @param param
	 * @return
	 */
	public List<UpgradePackageVersionInfo> queryVersionAndPackageInfo(UpgradePackageVersionInfo param);
	
	/**
	 * 查询版本信息条数
	 * @param param
	 * @return
	 */
	public Long queryVersionAndPackageCount(UpgradePackageVersionInfo param);
	
	/**
	 * 保存版本信息
	 * @param param
	 * @return
	 */
	public Long saveVersionInfo(VersionInfo param);
	
	/**
	 * 保存安装包信息
	 * @param param
	 * @return
	 */
	public Long savePackageInfo(UpgradePackage param);
	
	/**
	 * 保存定向升级用户信息
	 * @param param
	 * @return
	 */
	public Long saveUpgradeUserInfo(UpgradeUser param);
	
	

	/**
	 * 删除指定版本对应的定向升级用户信息
	 * @param param
	 * @return
	 */
	public Long delUpgradeUserInfo(String versionID);
	
	
	/**
	 * 删除指定用户的定向升级信息
	 * @param userid
	 * @return
	 * @date 2016-3-8
	 * @author zj
	 */
	public Long delUpgradeUserByUserID(String userid);
	
	/**
	 * 删除指定用户组的定向升级信息
	 * @param userid
	 * @return
	 * @date 2016-3-8
	 * @author zj
	 */
	public Long delUpgradeUserByDeptID(String deptid);
	
	/**
	 * 保存差异包信息
	 * @param param
	 * @return
	 */
	public Long saveIncrementalPackageInfo(IncrementalUpgrade param);
	
	/**
	 * 生成差异包
	 * @param clientType
	 * @param versionNum
	 * @param package_path
	 */
	public void add_diff(final String clientType,final String versionNum,final String package_path);
	
	/**
	 * 根据recordId查询版本信息
	 * @param recordId
	 * @return
	 */
	public VersionInfo queryVersionInfoByRecordId(int recordId);
	
	/**
	 * 修改版本信息
	 * @param param
	 * @return
	 */
	public Long updateVersionInfo(VersionInfo param);
	
	/**
	 * 修改安装包信息
	 * @param param
	 * @return
	 */
	public Long updateUpgradePackage(UpgradePackage param);
	
	/**
	 * 修改定向升级用户
	 * @param user
	 * @return
	 */
	public Long updateUpgradeUser(UpgradeUser user);
	
	/**
	 * 删除安装包信息
	 * @param param
	 * @return
	 */
	public Long deleteUpgradePackage(UpgradePackage param);
	
	/**
	 * 删除定向升级用户
	 * @param user
	 * @return
	 */
	public Long deleteUpgradeUser(UpgradeUser user);
	
	/**
	 * 删除版本信息
	 * @param param
	 * @return
	 */
	public Long deleteVersionInfo(VersionInfo param);
	
	/**
	 * 删除差异包
	 * @param param
	 * @return
	 */
	public Long deleteIncrementalUpgrade(IncrementalUpgrade param);
	
	/**
	 * 获得差异包数据
	 * @param param
	 * @return
	 */
	public List<IncrementalUpgrade> getIncrementalUpgrade(IncrementalUpgrade param);
	
	/**
	 * 根据版本号和客户端类型查询版本信息
	 * @param param
	 * @return
	 */
	public VersionInfo queryVersionByClientAndVerNum(VersionInfo param);
}
