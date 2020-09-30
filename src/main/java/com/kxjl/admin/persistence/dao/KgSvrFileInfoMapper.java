package com.kxjl.admin.persistence.dao;

import java.util.List;

import com.kxjl.admin.persistence.entity.KgSvrFileInfo;




/**
 * 文件服务器记录 Dao
 * @author zj
 *
 */
public interface KgSvrFileInfoMapper {
	
	KgSvrFileInfo selectByPrimaryKey(String id);

	
	/**
	 * 存储文件
	 * @param info
	 * @return
	 * @date 2016-7-12
	 * @author zj
	 */
	public int SaveFileInfo(KgSvrFileInfo info);
	
	
	/**
	 * 存储文件
	 * @param info
	 * @return
	 * @date 2016-7-12
	 * @author zj
	 */
	public int DeleteFileInfo(KgSvrFileInfo info);
	
	
	
	/**
	 * 文件下载+1
	 * @param info
	 * @return
	 * @date 2016-7-12
	 * @author zj
	 */
	public int addFileDonwLoadNums(KgSvrFileInfo info);
	
	/**
	 * 获取文件信息
	 * @param info
	 * @return
	 * @date 2016-7-12
	 * @author zj
	 */
	public KgSvrFileInfo getFileInfo(KgSvrFileInfo info);

}
