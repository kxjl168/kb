package com.kxjl.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.util.DateUtil;
import com.kxjl.admin.persistence.dao.KgSvrFileInfoMapper;
import com.kxjl.admin.persistence.entity.KgSvrFileInfo;
import com.kxjl.admin.service.KgSvrFileInfoService;


@Service
public class KgSvrFileInfoServiceImpl implements KgSvrFileInfoService {

	@Autowired
	private KgSvrFileInfoMapper fileDao;

	@Override
	public int SaveFileInfo(KgSvrFileInfo info) {

		

		if(info.getId()==null||info.getId().equals(""))
			info.setId(UUIDUtil.get32UUID());

		
		info.setSave_date(DateUtil.getNowStr(""));

		
		return fileDao.SaveFileInfo(info);
	}

	@Override
	public int DeleteFileInfo(KgSvrFileInfo info) {
		return fileDao.DeleteFileInfo(info);
	}

	@Override
	public int addFileDonwLoadNums(KgSvrFileInfo info) {
		return fileDao.addFileDonwLoadNums(info);
	}

	@Override
	public KgSvrFileInfo getFileInfo(KgSvrFileInfo info) {
		return fileDao.getFileInfo(info);
	}

}
