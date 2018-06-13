package com.kxjl.web.version.dao.Impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kxjl.web.version.dao.VersionInfoDao;
import com.kxjl.web.version.model.IncrementalUpgrade;
import com.kxjl.web.version.model.UpgradePackage;
import com.kxjl.web.version.model.UpgradePackageVersionInfo;
import com.kxjl.web.version.model.UpgradeUser;
import com.kxjl.web.version.model.VersionInfo;


/**
 * 版本信息DaoImpl
 * @author kangyongji
 *
 */
@Repository("vDao")
public class VersionInfoDaoImpl implements VersionInfoDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 日志记录对象
	private Logger logger = Logger.getLogger(VersionInfoDaoImpl.class);
	@Override
	public List<VersionInfo> queryVersionInfo(VersionInfo param) {
		logger.info("根据客户端类型查询版本信息");
		List<VersionInfo> version = sqlSession.selectList("versionInfo.getVersionInfo",param);
		return version;
	}
	
	@Override
	public IncrementalUpgrade queryIncrementalPackage(IncrementalUpgrade upgrade) {
		logger.info("根据低版本号和客户端类型查询差异包");
		List<IncrementalUpgrade> incremental = sqlSession.selectList("versionInfo.getIncrementalUpgrade",upgrade);
		if (!incremental.isEmpty() && incremental.size() > 0) {
			return incremental.get(0);
		}
		return null;
	}
	
	@Override
	public UpgradePackage queryPackageInfo(int upgradePackageId) {
		logger.info("根据升级包编号查询升级包");
		List<UpgradePackage> upPackage = sqlSession.selectList("versionInfo.getUpgradePackage",upgradePackageId);
		if (!upPackage.isEmpty() && upPackage.size() > 0) {
			return upPackage.get(0);
		}
		return null;
	}
	
	@Override
	public List<UpgradeUser> queryUpgradeUser(UpgradeUser versionNum) {
		logger.info("根据版本号查询定向升级的用户");
		List<UpgradeUser> user = sqlSession.selectList("versionInfo.getUpgradeUser",versionNum);
		return user;
	}
	
	@Override
	public List<UpgradePackageVersionInfo> queryVersionAndPackageInfo(
			UpgradePackageVersionInfo param) {
		// TODO Auto-generated method stub
		logger.info("查询版本信息！");
		//添加分页
		int firstData = (param.getCurrentPage()-1) * param.getPageCount();
		param.setCurrentPage(firstData);
		List<UpgradePackageVersionInfo> versionInfos = sqlSession.selectList("versionInfo.getUpgradeVersion",param);
		return versionInfos;
	}
	
	@Override
	public Long queryVersionAndPackageCount(UpgradePackageVersionInfo param) {
		// TODO Auto-generated method stub
		logger.info("查询版本信息条数！");
		Long infoCount = (Long) sqlSession.selectOne("versionInfo.getUpgradeVersionCount",param);
		return infoCount;
	}

	@Override
	public Long saveVersionInfo(VersionInfo param) {
		// TODO Auto-generated method stub
		logger.info("保存版本信息！");
		Long res = (long) sqlSession.insert("versionInfo.saveVersionInfo",param);
		return res;
	}
	
	@Override
	public Long savePackageInfo(UpgradePackage param) {
		// TODO Auto-generated method stub
		logger.info("保存安装包信息！");
		Long res = (long) sqlSession.insert("versionInfo.savePackageInfo",param);
		return res;
	}
	
	@Override
	public Long saveIncrementalPackageInfo(IncrementalUpgrade param) {
		// TODO Auto-generated method stub
		logger.info("保存差异包信息！");
		Long res = (long) sqlSession.insert("versionInfo.saveIncrementalPackageInfo",param);
		return res;
	}
	
	@Override
	public Long saveUpgradeUserInfo(UpgradeUser param) {
		// TODO Auto-generated method stub
		logger.info("保存定向升级用户信息！");
		Long res = (long) sqlSession.insert("versionInfo.saveUpgradeUser",param);
		return res;
	}
	
	@Override
	public Long  delUpgradeUserInfo(String versionID) {
		// TODO Auto-generated method stub
		//logger.info("保存定向升级用户信息！");
		Long res = (long) sqlSession.update("versionInfo.delUpgradeUserInfo",versionID);
		return res;
	}


	@Override
	public List<UpgradePackageVersionInfo> queryUpgradePackageInfoByClient(
			UpgradePackageVersionInfo param) {
		// TODO Auto-generated method stub
		logger.info("根据客户端类型查询安装包记录！");
		List<UpgradePackageVersionInfo> versionInfos = sqlSession.selectList("versionInfo.getupgradepackageVer",param);
		return versionInfos;
	}

	@Override
	public VersionInfo queryVersionInfoByRecordId(int recordId) {
		// TODO Auto-generated method stub
		logger.info("根据recordId查询版本信息！");
		List<VersionInfo> version = sqlSession.selectList("versionInfo.getVersionInfoByRecordId",recordId);
		if (!version.isEmpty() && version.size() > 0) {
			return version.get(0);
		}
		return null;
	}
	
	
	/**
	 * 删除指定用户的定向升级信息
	 * @param userid
	 * @return
	 * @date 2016-3-8
	 * @author zj
	 */
	public Long delUpgradeUserByUserID(String userid)
	{
		return (long) sqlSession.delete("versionInfo.delUpgradeUserByUserID",userid);
	}
	
	/**
	 * 删除指定用户组的定向升级信息
	 * @param userid
	 * @return
	 * @date 2016-3-8
	 * @author zj
	 */
	public Long delUpgradeUserByDeptID(String deptid)
	{
		return (long) sqlSession.delete("versionInfo.delUpgradeUserByDeptID",deptid);
	}

	@Override
	public Long updateVersionInfo(VersionInfo param) {
		// TODO Auto-generated method stub
		logger.info("修改版本信息！");
		Long res = (long) sqlSession.update("versionInfo.updateVersionInfo",param);
		return res;
	}

	@Override
	public Long updateUpgradePackage(UpgradePackage param) {
		// TODO Auto-generated method stub
		logger.info("修改安装包信息！");
		Long res = (long) sqlSession.update("versionInfo.updateUpgradePackageInfo",param);
		return res;
	}

	@Override
	public Long updateUpgradeUser(UpgradeUser param) {
		// TODO Auto-generated method stub
		logger.info("根据定向升级用户信息！");
		Long res =0L;
		try {
			
		
		 res = (long) sqlSession.update("versionInfo.updateUpgradeUserfo",param);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return res;
	}

	@Override
	public Long deleteUpgradePackage(UpgradePackage param) {
		// TODO Auto-generated method stub
		logger.info("删除安装包信息！");
		Long res = (long) sqlSession.delete("versionInfo.deleteUpgradePackageInfo",param);
		return res;
	}

	@Override
	public Long deleteUpgradeUser(UpgradeUser user) {
		// TODO Auto-generated method stub
		logger.info("删除定向升级用户！");
		Long res = (long) sqlSession.delete("versionInfo.deleteUpgradeUser",user);
		return res;
	}

	@Override
	public Long deleteVersionInfo(VersionInfo param) {
		// TODO Auto-generated method stub
		logger.info("删除版本信息！");
		Long res = (long) sqlSession.delete("versionInfo.deleteVersionInfo",param);
		return res;
	}

	@Override
	public Long deleteIncrementalUpgrade(IncrementalUpgrade param) {
		// TODO Auto-generated method stub
		logger.info("删除差异包！");
		Long res = (long) sqlSession.delete("versionInfo.deleteIncrementalUpgrade",param);
		return res;
	}

	@Override
	public List<IncrementalUpgrade> getIncrementalUpgrade(
			IncrementalUpgrade param) {
		// TODO Auto-generated method stub
		logger.info("获得差异包数据！");
		List<IncrementalUpgrade> upgrade = sqlSession.selectList("versionInfo.getIncrementalUpgradeByParams",param);
		return upgrade;
	}

	@Override
	public VersionInfo queryVersionByClientAndVerNum(VersionInfo param) {
		// TODO Auto-generated method stub
		logger.info("根据版本号和客户端类型查询版本信息");
		List<VersionInfo> version = sqlSession.selectList("versionInfo.getVersionInfoByClientAndVerNum",param);
		if (!version.isEmpty() && version.size() > 0) {
			return version.get(0);
		}
		return null;
	}
}
