package com.kxjl.web.system.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.system.dao.DictInfoDao;
import com.kxjl.web.system.dao.MenuInfoDao;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.service.DictInfoService;

@Service(value = "dictInfoService")
public class DictInfoServiceImpl implements DictInfoService {

	@Autowired
	private DictInfoDao dictInfoDao;

	/**
	 * 分页获取banner列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<DictInfo> getDictInfoPageList(DictInfo query) {
		return dictInfoDao.getDictInfoPageList(query);
		// return null;
	}

	/**
	 * 获取banner总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getDictInfoPageListCount(DictInfo query){
		return dictInfoDao.getDictInfoPageListCount(query);
	}

	/**
	 * 添加DictInfo
	 * 
	 * @param DictInfo
	 * @return
	 */
	public int addDictInfo(DictInfo DictInfo){
		return dictInfoDao.addDictInfo(DictInfo);
	}

	/**
	 * 删除DictInfo
	 * 
	 * @param id
	 * @return
	 */
	public int deleteDictInfo(@Param(value = "id") Integer id){
		return dictInfoDao.deleteDictInfo(id);
	}

	/**
	 * 更新DictInfo
	 * 
	 * @param DictInfo
	 * @return
	 */
	public int updateDictInfo(DictInfo DictInfo){
		return dictInfoDao.updateDictInfo(DictInfo);
	}

	/**
	 * 根据ID获取DictInfo信息
	 * 
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public DictInfo getDictInfoInfoById(DictInfo query){
		return dictInfoDao.getDictInfoInfoById(query);
	}

}
