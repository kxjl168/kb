package com.kxjl.web.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SvrFileInfo;





public interface SvrFileInfoService {
	
	/**
	 * 存储文件
	 * @param info
	 * @return
	 * @date 2016-7-12
	 * @author zj
	 */
	public int SaveFileInfo(SvrFileInfo info);
	
	
	/**
	 * 存储文件
	 * @param info
	 * @return
	 * @date 2016-7-12
	 * @author zj
	 */
	public int DeleteFileInfo(SvrFileInfo info);
	
	
	
	/**
	 * 文件下载+1
	 * @param info
	 * @return
	 * @date 2016-7-12
	 * @author zj
	 */
	public int addFileDonwLoadNums(SvrFileInfo info);
	
	/**
	 * 获取文件信息
	 * @param info
	 * @return
	 * @date 2016-7-12
	 * @author zj
	 */
	public SvrFileInfo getFileInfo(SvrFileInfo info);
	
	/**
	 * 界面查询附件列表 非图片的其他附件，展现下载次数
	 * @param info
	 * @return
	 * @author zj
	 * @date 2018年9月1日
	 */
	public List<SvrFileInfo> getFileList(SvrFileInfo info);

}
