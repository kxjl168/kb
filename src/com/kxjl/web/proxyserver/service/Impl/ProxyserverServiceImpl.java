package com.kxjl.web.proxyserver.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.web.proxyserver.dao.ProxyserverDao;
import com.kxjl.web.proxyserver.model.Proxyserver;
import com.kxjl.web.proxyserver.service.ProxyserverService;
import com.kxjl.web.system.dao.SystemParamsDao;

@Service(value = "ProxyserverService")
public class ProxyserverServiceImpl implements ProxyserverService {

	@Autowired
	ProxyserverDao bannerDao;

	@Autowired
	SystemParamsDao sysDao;

	/**
	 * 分页获取banner列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public List<Proxyserver> getProxyserverPageList(Proxyserver query) {
		return bannerDao.getProxyserverPageList(query);
	}

	/**
	 * 获取banner总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public int getProxyserverPageListCount(Proxyserver query) {
		return bannerDao.getProxyserverPageListCount(query);
	}

	@Override
	public Proxyserver getProxyserverInfoById(Proxyserver Proxyserver) {
		return bannerDao.getProxyserverInfoById(Proxyserver);
	}

}
