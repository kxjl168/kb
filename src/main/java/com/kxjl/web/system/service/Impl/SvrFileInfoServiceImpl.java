package com.kxjl.web.system.service.Impl;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kxjl.tool.utils.DateUtil;
import com.kxjl.web.system.dao.MenuInfoDao;
import com.kxjl.web.system.dao.SvrFileInfoDao;
import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SvrFileInfo;
import com.kxjl.web.system.service.MenuInfoService;
import com.kxjl.web.system.service.SvrFileInfoService;



@Service
public class SvrFileInfoServiceImpl implements SvrFileInfoService{

	@Autowired
	private SvrFileInfoDao fileDao;
	
	@Autowired
	private SystemParamsDao sysDao;

	@Override
	public int SaveFileInfo(SvrFileInfo info) {
		
		Integer id= sysDao.getSeqNextVal("seq_svr_file");
		info.setId(id);
		info.setSave_date(DateUtil.getNowStr(""));
		
		return fileDao.SaveFileInfo(info);
	}

	@Override
	public int DeleteFileInfo(SvrFileInfo info) {
		
		//删除物理文件
		SvrFileInfo tp= fileDao.getFileInfo(info);
		
		File f=new File(tp.getFull_path());
		if(f.exists())
		{
			boolean isdone=f.delete();
			if(!isdone)
				return -1;
			
			int pindex =f.getAbsolutePath().lastIndexOf(".");

			String ofile= f.getAbsolutePath().substring(0,pindex)+"_orign"+f.getAbsolutePath().substring(pindex);
			
			File f2=new File(ofile);
			if(f2.exists())
				f2.delete();
			
		}
		
		return fileDao.DeleteFileInfo(info);
	}

	@Override
	public int addFileDonwLoadNums(SvrFileInfo info) {
		return fileDao.addFileDonwLoadNums(info);
	}

	@Override
	public SvrFileInfo getFileInfo(SvrFileInfo info) {
		return fileDao.getFileInfo(info);
	}
	
	/**
	 * 界面查询附件列表 非图片的其他附件，展现下载次数
	 * @param info
	 * @return
	 * @author zj
	 * @date 2018年9月1日
	 */
	public List<SvrFileInfo> getFileList(SvrFileInfo info){
		return fileDao.getFileList(info);
	}

}
