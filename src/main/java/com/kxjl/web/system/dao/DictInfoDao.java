package com.kxjl.web.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kxjl.web.system.model.DictInfo;



public interface DictInfoDao {

	
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<DictInfo> getDictInfoPageList(DictInfo query);

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getDictInfoPageListCount(DictInfo query);
	
	/**
	 * 添加DictInfo
	 * @param DictInfo
	 * @return
	 */
	public int addDictInfo(DictInfo DictInfo);
	
	/**
	 * 删除DictInfo
	 * @param id
	 * @return
	 */
	public int deleteDictInfo(@Param(value="id")Integer id);

	/**
	 * 更新DictInfo
	 * @param DictInfo
	 * @return
	 */
	public int updateDictInfo(DictInfo DictInfo);
	
	
	
	/**
	 * 根据ID获取DictInfo信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public DictInfo getDictInfoInfoById(DictInfo blog);
	
	/**
	 * key
	 * @param key
	 * @return
	 * @author zj
	 * @date 2018年6月13日
	 */
	public DictInfo getDictInfoInfoByKey(String key);
}
