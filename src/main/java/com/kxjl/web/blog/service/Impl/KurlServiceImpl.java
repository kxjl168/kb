package com.kxjl.web.blog.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.blog.dao.KurlDao;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;

@Service(value = "kurlService")
public class KurlServiceImpl implements KurlService {

	@Autowired
	private KurlDao dictInfoDao;

	/**
	 * 分页获取banner列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Kurl> getKurlPageList(Kurl query) {
		return dictInfoDao.getKurlPageList(query);
		// return null;
	}

	/**
	 * 获取banner总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getKurlPageListCount(Kurl query) {
		return dictInfoDao.getKurlPageListCount(query);
	}

	/**
	 * 添加Kurl
	 * 
	 * @param Kurl
	 * @return
	 */
	public int addKurl(Kurl Kurl) {
		return dictInfoDao.addKurl(Kurl);
	}

	/**
	 * 删除Kurl
	 * 
	 * @param id
	 * @return
	 */
	public int deleteKurl(@Param(value = "id") Integer id) {
		return dictInfoDao.deleteKurl(id);
	}

	/**
	 * 更新Kurl
	 * 
	 * @param Kurl
	 * @return
	 */
	public int updateKurl(Kurl Kurl) {
		return dictInfoDao.updateKurl(Kurl);
	}

	/**
	 * 根据ID获取Kurl信息
	 * 
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public Kurl getKurlInfoById(Kurl query) {
		return dictInfoDao.getKurlInfoById(query);
	}

}
