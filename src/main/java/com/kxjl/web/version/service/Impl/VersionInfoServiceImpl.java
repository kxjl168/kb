package com.kxjl.web.version.service.Impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.version.dao.VersionInfoDao;
import com.kxjl.web.version.model.IncrementalUpgrade;
import com.kxjl.web.version.model.UpgradePackage;
import com.kxjl.web.version.model.UpgradePackageVersionInfo;
import com.kxjl.web.version.model.UpgradeUser;
import com.kxjl.web.version.model.VersionInfo;
import com.kxjl.web.version.service.VersionInfoService;


/**
 * 版本信息ServiceImpl
 * @author kangyongji
 *
 */
@Service("vService")
public class VersionInfoServiceImpl implements VersionInfoService {

	// 日志记录对象
	private Logger logger = Logger.getLogger(VersionInfoServiceImpl.class);
	
	@Autowired
	private VersionInfoDao vDao;
	@Override
	public List<VersionInfo> queryVersionInfo(VersionInfo param) {
		// TODO Auto-generated method stub
		return vDao.queryVersionInfo(param);
	}
	@Override
	public IncrementalUpgrade queryIncrementalPackage(IncrementalUpgrade upgrade) {
		// TODO Auto-generated method stub
		return vDao.queryIncrementalPackage(upgrade);
	}
	@Override
	public UpgradePackage queryPackageInfo(int upgradePackageId) {
		// TODO Auto-generated method stub
		return vDao.queryPackageInfo(upgradePackageId);
	}
	@Override
	public List<UpgradeUser> queryUpgradeUser(UpgradeUser versionNum) {
		// TODO Auto-generated method stub
		return vDao.queryUpgradeUser(versionNum);
	}
	@Override
	public List<UpgradePackageVersionInfo> queryVersionAndPackageInfo(
			UpgradePackageVersionInfo param) {
		// TODO Auto-generated method stub
		return vDao.queryVersionAndPackageInfo(param);
	}
	@Override
	public Long queryVersionAndPackageCount(UpgradePackageVersionInfo param) {
		int firstData = (param.getCurrentPage()-1) * param.getPageCount();
		param.setCurrentPage(firstData);
		return vDao.queryVersionAndPackageCount(param);
	}
	@Override
	public Long saveVersionInfo(VersionInfo param) {
		// TODO Auto-generated method stub
		return vDao.saveVersionInfo(param);
	}
	@Override
	public Long savePackageInfo(UpgradePackage param) {
		// TODO Auto-generated method stub
		return vDao.savePackageInfo(param);
	}
	@Override
	public Long saveUpgradeUserInfo(UpgradeUser param) {
		// TODO Auto-generated method stub
		return vDao.saveUpgradeUserInfo(param);
	}
	
	
	public Long delUpgradeUserInfo(String versionID)
	{
		return vDao.delUpgradeUserInfo(versionID);
	}
	
	
	@Override
	public Long saveIncrementalPackageInfo(IncrementalUpgrade param) {
		// TODO Auto-generated method stub
		return vDao.saveIncrementalPackageInfo(param);
	}
	
	/**
	 * 生成差异包
	 * @param clientType
	 * @param versionNum
	 * @param package_path
	 */
	@Override
	public void add_diff(final String clientType,final String versionNum,final String package_path){
		new Thread(){
			public void run(){
					UpgradePackageVersionInfo info = new UpgradePackageVersionInfo();
					info.setClientType(Integer.parseInt(clientType));
					info.setVersionNum(Double.parseDouble(versionNum));
					List<UpgradePackageVersionInfo> list = vDao.queryUpgradePackageInfoByClient(info);
					
					if(null != list){
						String[] cmd = null;
//							StringBuffer cmd = null;
						String curr_package_name = package_path.substring(package_path.lastIndexOf("/")+1,package_path.length());
						logger.info("当前包的名称:"+curr_package_name);
						String prev_package_name = "";
						String prev_package_path = "";
						UpgradePackageVersionInfo packageVersionInfo = null;
						SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
						StringBuffer newName = null;
						String md5 = "";
						Double low_version;
						//zj.160301 配置获取方式修改
						String dir = ConfigReader.getInstance().getProperty("UPLOADFILE_PATH");//   Config.getUPLOADFILE_PATH();
						//String http_dir = Config.getHTTP_DIR();
						for (int i = 0; i < list.size(); i++) {
							packageVersionInfo = list.get(i);
							newName = new StringBuffer();
							prev_package_path = packageVersionInfo.getUpgradepackagePath();
							prev_package_name = prev_package_path.substring(prev_package_path.lastIndexOf("/")+1,prev_package_path.length());
							low_version = packageVersionInfo.getVersionNum();
							try {
								//生成差异化名称
								newName.append("diff_").append(format.format(new Date()));
								Random rod=new Random();
								for(int j=0;j<3;j++){
									newName.append(rod.nextInt(10));
								}
								cmd = new String[] {"/usr/local/bsdiff-4.3/bsdiff", dir+prev_package_path,dir+package_path, dir+"diff/"+newName.append(".apk")};
								System.out.println("执行差异化命令:"+cmd[0]+"---------"+cmd[1]+"-------"+cmd[2]+"------"+cmd[3]);
								logger.info("执行差异化命令:"+cmd[0]+"---------"+cmd[1]+"-------"+cmd[2]+"------"+cmd[3]);
								Process proc = Runtime.getRuntime().exec(cmd);
								proc.waitFor();
								
//								md5 = new Md5EncryptFile().getMD5(new File(dir+newName));
					//			theUpgradePackageVersionInfoDao.insertIncrementalUpgrade(clientType,versionNum,low_version,http_dir+""+newName,md5);
//								theUpgradePackageVersionInfoDao.insertIncrementalUpgrade(clientType,versionNum,low_version,http_dir+Config.getUPLOAD_FILE_URL()+newName,md5);
								IncrementalUpgrade increment = new IncrementalUpgrade();
								increment.setClienttype(Integer.parseInt(clientType));
								increment.setCurrentVersion(Double.parseDouble(versionNum));
								increment.setDiffPath("diff/"+newName.toString());
								increment.setLowVersion(low_version);
								vDao.saveIncrementalPackageInfo(increment);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								logger.error(e.toString());
								continue;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								logger.error(e.toString());
								continue;
							}catch(Exception ex){
								logger.error(ex.toString());
								continue;
							}
						}
					}
				}
		}.start();
	}
	
	

	/**
	 * 在文件服务器上生成差异包
	 * @param clientType
	 * @param versionNum
	 * @param package_path
	 */
	@Override
	public void add_diff_inFileSvr(final String clientType,final String versionNum,final String package_path){
		new Thread(){
			public void run(){
					UpgradePackageVersionInfo info = new UpgradePackageVersionInfo();
					info.setClientType(Integer.parseInt(clientType));
					info.setVersionNum(Double.parseDouble(versionNum));
					List<UpgradePackageVersionInfo> list = vDao.queryUpgradePackageInfoByClient(info);
					
					if(null != list){
						String[] cmd = null;
//							StringBuffer cmd = null;
						String curr_package_name = package_path.substring(package_path.lastIndexOf("/")+1,package_path.length());
						logger.info("当前包的名称:"+curr_package_name);
						String prev_package_name = "";
						String prev_package_path = "";
						UpgradePackageVersionInfo packageVersionInfo = null;
						SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
						StringBuffer newName = null;
						String md5 = "";
						Double low_version;
						//zj.160301 配置获取方式修改 -文件服务器存储更目录
						String dir = ConfigReader.getInstance().getProperty("FILE_SAVE_PATH");//   Config.getUPLOADFILE_PATH();
						//String http_dir = Config.getHTTP_DIR();
						for (int i = 0; i < list.size(); i++) {
							packageVersionInfo = list.get(i);
							newName = new StringBuffer();
							prev_package_path = packageVersionInfo.getUpgradepackagePath();
							prev_package_name = prev_package_path.substring(prev_package_path.lastIndexOf("/")+1,prev_package_path.length());
							low_version = packageVersionInfo.getVersionNum();
							try {
								//生成差异化名称
								newName.append("diff_").append(format.format(new Date()));
								Random rod=new Random();
								for(int j=0;j<3;j++){
									newName.append(rod.nextInt(10));
								}
								cmd = new String[] {"/usr/local/bsdiff-4.3/bsdiff", dir+prev_package_path,dir+package_path, dir+"diff/"+newName.append(".apk")};
								System.out.println("执行差异化命令:"+cmd[0]+"---------"+cmd[1]+"-------"+cmd[2]+"------"+cmd[3]);
								logger.info("执行差异化命令:"+cmd[0]+"---------"+cmd[1]+"-------"+cmd[2]+"------"+cmd[3]);
								Process proc = Runtime.getRuntime().exec(cmd);
								proc.waitFor();
								
//								md5 = new Md5EncryptFile().getMD5(new File(dir+newName));
					//			theUpgradePackageVersionInfoDao.insertIncrementalUpgrade(clientType,versionNum,low_version,http_dir+""+newName,md5);
//								theUpgradePackageVersionInfoDao.insertIncrementalUpgrade(clientType,versionNum,low_version,http_dir+Config.getUPLOAD_FILE_URL()+newName,md5);
								IncrementalUpgrade increment = new IncrementalUpgrade();
								increment.setClienttype(Integer.parseInt(clientType));
								increment.setCurrentVersion(Double.parseDouble(versionNum));
								increment.setDiffPath("diff/"+newName.toString());
								increment.setLowVersion(low_version);
								vDao.saveIncrementalPackageInfo(increment);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								logger.error(e.toString());
								continue;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								logger.error(e.toString());
								continue;
							}catch(Exception ex){
								logger.error(ex.toString());
								continue;
							}
						}
					}
				}
		}.start();
	}
	
	
	@Override
	public VersionInfo queryVersionInfoByRecordId(int recordId) {
		// TODO Auto-generated method stub
		return vDao.queryVersionInfoByRecordId(recordId);
	}
	@Override
	public Long updateVersionInfo(VersionInfo param) {
		// TODO Auto-generated method stub
		return vDao.updateVersionInfo(param);
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
		return vDao.delUpgradeUserByUserID(userid);
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
		return vDao.delUpgradeUserByDeptID(deptid);
	}
	
	@Override
	public Long updateUpgradePackage(UpgradePackage param) {
		// TODO Auto-generated method stub
		return vDao.updateUpgradePackage(param);
	}
	@Override
	public Long updateUpgradeUser(UpgradeUser user) {
		// TODO Auto-generated method stub
		return vDao.updateUpgradeUser(user);
	}
	@Override
	public Long deleteUpgradePackage(UpgradePackage param) {
		// TODO Auto-generated method stub
		return vDao.deleteUpgradePackage(param);
	}
	@Override
	public Long deleteUpgradeUser(UpgradeUser user) {
		// TODO Auto-generated method stub
		return vDao.deleteUpgradeUser(user);
	}
	@Override
	public Long deleteVersionInfo(VersionInfo param) {
		// TODO Auto-generated method stub
		return vDao.deleteVersionInfo(param);
	}
	@Override
	public Long deleteIncrementalUpgrade(IncrementalUpgrade param) {
		// TODO Auto-generated method stub
		return vDao.deleteIncrementalUpgrade(param);
	}
	@Override
	public List<IncrementalUpgrade> getIncrementalUpgrade(
			IncrementalUpgrade param) {
		// TODO Auto-generated method stub
		return vDao.getIncrementalUpgrade(param);
	}
	@Override
	public VersionInfo queryVersionByClientAndVerNum(VersionInfo param) {
		// TODO Auto-generated method stub
		return vDao.queryVersionByClientAndVerNum(param);
	}
}
