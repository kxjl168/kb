package com.kxjl.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kxjl.admin.persistence.entity.KgSvrFileInfo;





public interface KgSvrFileInfoService {
	
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
