package com.kxjl.web.routeM.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.device.model.Device;
import com.kxjl.web.device.service.Impl.DeviceServiceImpl;
import com.kxjl.web.phoneaccount.dao.PhoneAccountDao;
import com.kxjl.web.proxyserver.dao.ProxyserverDao;
import com.kxjl.web.proxyserver.model.Proxyserver;
import com.kxjl.web.routeM.dao.ProxyserverRouteDao;
import com.kxjl.web.routeM.dao.ServerCompleteRouteDao;
import com.kxjl.web.routeM.model.ProxyserverRoute;
import com.kxjl.web.routeM.model.ServerCompleteRoute;
import com.kxjl.web.routeM.service.ProxyserverRouteService;
import com.kxjl.web.system.dao.SystemParamsDao;

@Service(value = "ProxyserverRouteService")
public class ProxyserverRouteServiceImpl implements ProxyserverRouteService {

	@Autowired
	ProxyserverRouteDao psvrouteDao;

	@Autowired
	ProxyserverDao psvrDao;

	@Autowired
	ServerCompleteRouteDao svrcpltRoutevDao;

	/**
	 * 查询中转服务器上的路由器分配情况 根据 中转服务器计划分配数-当前在线数 缺口大到小排序
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<ProxyserverRoute> getProxyserverRouteListOrderbyGap(
			ProxyserverRoute query) {
		return psvrouteDao.getProxyserverRouteListOrderbyGap(query);
	}

	private static Logger logger = Logger
			.getLogger(ProxyserverRouteServiceImpl.class);

	/**
	 * 调整中转服务器预设 路由器大小
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public void adjustPlanRouteNum() {

		try {
			
			
			//TODO 是否要清空原数据，或者每天清空一次。？
			

			// 手机、路由器预设比例
			Integer n = ConfigReader.getInstance().getIntProperty("PRENUM", 3);

			// 计算中转服务器上的手机数量
			// 根据 指定路由器 地市
			ProxyserverRoute query = new ProxyserverRoute();
			List<ProxyserverRoute> psvrRoutes = psvrouteDao
					.getProxyserverRouteListOrderbyGap(query);

			logger.info("ProxyserverRoute size:" + psvrRoutes.size());
			for (int i = 0; i < psvrRoutes.size(); i++) {

				try {

					ProxyserverRoute csroute = psvrRoutes.get(i);

					int routeNum = csroute.getPhone_num() / n + 1;

					csroute.setPlan_route_num(routeNum);
					// 调整中转服务器 路由器分配预设值
					updateProxyserverRoute(csroute);
				} catch (Exception e) {
					continue;
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 更新ProxyserverRoute
	 * 
	 * @param ProxyserverRoute
	 * @return
	 */
	public int updateProxyserverRoute(ProxyserverRoute proxyserverRoute) {
		logger.debug("update updateProxyserverRoute");
		ProxyserverRoute d = psvrouteDao
				.getProxyserverRouteInfo(proxyserverRoute);
		int rst = -1;
		if (d != null)
			rst = psvrouteDao.updateProxyserverRoute(proxyserverRoute);
		else
			rst = psvrouteDao.addProxyserverRoute(proxyserverRoute);

		// 更新 中转服务器已分配手机数
		List<ProxyserverRoute> rts = psvrouteDao.getProxyserverGroupInfo();
		for (int i = 0; i < rts.size(); i++) {

			ProxyserverRoute pr = rts.get(i);
			Proxyserver psvr = new Proxyserver();
			psvr.setId(pr.getProxyserver_id());
			psvr.setAssignphones(String.valueOf(pr.getPhone_num()));

			Proxyserver tppsvr = psvrDao.getProxyserverInfoById(psvr);

			if (tppsvr != null)
				psvrDao.updateProxyserver(psvr);
			/*else
				psvrDao.addProxyserver(psvr);*/
		}

		return rst;
	}

	/**
	 * 查询已服务完成的路由
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public List<ServerCompleteRoute> getServerCompleteRoutePageList(
			ServerCompleteRoute query) {
		return svrcpltRoutevDao.getServerCompleteRouteList(query);
	}

	/*
	*//**
	 * 添加ProxyserverRoute
	 * 
	 * @param ProxyserverRoute
	 * @return
	 */
	/*
	 * public int addProxyserverRoute(ProxyserverRoute ProxyserverRoute);
	 *//**
	 * 删除ProxyserverRoute
	 * 
	 * @param id
	 * @return
	 */
	/*
	 * public int deleteProxyserverRoute(@Param(value="banner_id")Integer id);
	 *//**
	 * 更新ProxyserverRoute
	 * 
	 * @param ProxyserverRoute
	 * @return
	 */
	/*
	 * public int updateProxyserverRoute(ProxyserverRoute ProxyserverRoute);
	 *//**
	 * 获取ProxyserverRoute列表
	 * 
	 * @return
	 */
	/*
	 * public List<ProxyserverRoute> getProxyserverRouteList();
	 * 
	 * 
	 * 
	 * /** 根据ID获取ProxyserverRoute信息
	 * 
	 * @param bannerID
	 * 
	 * @return
	 * 
	 * @date 2016-8-4
	 */

	/**
	 * 分页获取列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<ProxyserverRoute> getProxyserverRoutePageList(
			ProxyserverRoute query) {
		return psvrouteDao.getProxyserverRoutePageList(query);
	}

	/**
	 * 获取总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getProxyserverRoutePageListCount(ProxyserverRoute query) {
		return psvrouteDao.getProxyserverRoutePageListCount(query);
	}

}
