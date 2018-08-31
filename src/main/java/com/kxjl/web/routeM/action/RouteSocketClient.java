package com.kxjl.web.routeM.action;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.DateUtil;
import com.kxjl.web.device.dao.DeviceDao;
import com.kxjl.web.device.model.Device;
import com.kxjl.web.device.model.Device.RouteFlag;
import com.kxjl.web.device.model.Device.RouteFreeStaus;
import com.kxjl.web.device.service.DeviceService;
import com.kxjl.web.proxyserver.model.Proxyserver;
import com.kxjl.web.routeM.model.ProxyserverRoute;
import com.kxjl.web.routeM.model.ServerCompleteRoute;
import com.kxjl.web.routeM.service.ProxyserverRouteService;

/***
 * 集中处理路由器连接及相关分配、检测逻辑
 * 
 * @param map
 * @return
 * @author zj
 * @date 2017-12-12
 */
public class RouteSocketClient extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static RouteSocketClient instance = null;

	private static Logger logger = Logger.getLogger(RouteSocketClient.class);

	@Autowired
	private DeviceService routeService;

	@Autowired
	private ProxyserverRouteService psvrRouteService;

	private int vmAllNum = 0;// 虚拟机总数

	// 客户端连接信息HashMap
	private static ConcurrentHashMap<SocketChannel, Device> clientList = new ConcurrentHashMap<SocketChannel, Device>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		// autowire起作用
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
		// VMMachineInfo query = new VMMachineInfo();
		// vmAllNum = vmMachineDao.GetVmMachineCount(query);
		instance = this;
	}

	public static RouteSocketClient getInstance() {
		// 判断是否实例化
		if (instance == null) {
			instance = new RouteSocketClient();
		}
		return instance;
	}

	public synchronized void putSocketInfo(SocketChannel channel, Device pcInfo) {
		clientList.put(channel, pcInfo);
	}

	public synchronized Device getSocketInfo(SocketChannel channel) {
		return clientList.get(channel);
	}

	/**
	 * 路由器下线
	 * 
	 * 记录下线时间、更新数据库、队列移除连接
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-12
	 */
	public synchronized void disconnect(SocketChannel channel) {

		Device route = clientList.get(channel);
		if (route != null) {
			try {
				channel.close();
			} catch (Exception e) {

			}
			clientList.remove(channel);

			if (route.getRouteid() != null) {
				// 设置属性，存储
				route.setProxyserver_id("");
				route.setFlag(RouteFlag.OFFLINE.toString());
				route.setFree(RouteFreeStaus.Free.toString());
				route.setOfflinetime(DateUtil.getNowStr(""));

				routeService.updateOrSaveDevice(route);
			}
			logger.debug(channel.toString()+" conn cleaned done！");

		}

	}

	/**
	 * 给路由器分配中转服务器
	 * 
	 * 
	 * 分配中转服务器算法、 1、检查路由器位置， 比对中转服务器路由器关联表，检查指定城市的缺口中转服务器 2、排除位于路由器服务器结束表中的，
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-12
	 */
	public synchronized void assignTransServer(SocketChannel channel) {

		assignChannel(channel);
	}

	/**
	 * 立刻 从空闲路由器中 给指定中转服务器分 分配 出口位置为对应地区的路由器
	 * 
	 * 
	 * 分配中转服务器算法、 1、排除位于路由器服务器结束表中的，
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-12
	 */
	public synchronized void assignTransServer(Proxyserver pserver, String city) {
		assignChannel(pserver, city);
	}

	/**
	 * 从全部连接中寻找 指定出口位置 的空闲路由器（不在该中转服务器的已完成列表），连接至中转服务器
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	private void assignChannel(Proxyserver pserver, String city) {

		// 查询可用路由
		Device query = new Device();
		query.setFlag(RouteFlag.UNASSIGN.toString());
		query.setFree(RouteFreeStaus.Free.toString());
		query.setCity(city);
		// query.setProxyserver_id(pserver.getId()); // 不在中转服务器已完成服务列表
		List<Device> freeRoutes = routeService.getDeviceList(query);

		// 该中转服务器上已服务完成的路由器
		ServerCompleteRoute squery = new ServerCompleteRoute();
		squery.setProxyserver_id(pserver.getId());
		List<ServerCompleteRoute> complete_routes = psvrRouteService
				.getServerCompleteRoutePageList(squery);

		for (int i = 0; i < freeRoutes.size(); i++) {
			try {

				Device route = freeRoutes.get(i);

				boolean canassign = true;
				for (int j = 0; j < complete_routes.size(); j++) {
					if (route.getRouteid().equals(
							complete_routes.get(j).getRoute_id())) {
						canassign = false;
						break;
					}
				}
				if (!canassign)
					continue;

				// 查询连接中是否有当前路由
				SocketChannel sc = getSktChannelByRouteID(route);
				if (sc != null) {
					// 设置属性，存储
					route.setProxyserver_id(pserver.getId());
					route.setFlag(RouteFlag.ASSIGN_UNCONN.toString());
					route.setFree(RouteFreeStaus.UNFree.toString());
					route.setOfflinetime("");
					routeService.updateOrSaveDevice(route);
					clientList.put(sc, route);

					// 返回分配信息
					JSONObject rdata = new JSONObject();
					try {
						rdata.put("ip", pserver.getIp());
						rdata.put("port", pserver.getPort());
					} catch (JSONException e) {

						e.printStackTrace();
					}

					// 返回属性
					SendData(sc, new byte[] { 0x09, 0x02 }, rdata);

					// 只分配一个
					break;

				}

			} catch (Exception e) {
				continue;
			}
		}

	}

	/**
	 * 分配当前路由器连接 至 中转服务器
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	private void assignChannel(SocketChannel channel) {
		Device route = clientList.get(channel);

		String city = route.getCity();
		if (city == null || city.equals("")) {
			// ip获取地址

			// TODOo
		}
		if (city == null || city.equals("")) {
			if (route.getIp().contains("127.0.0.1")) {
				route.setCity("上海");
			}
		}

		// 根据 指定路由器 地市
		ProxyserverRoute query = new ProxyserverRoute();
		query.setCity(route.getCity());
		List<ProxyserverRoute> psvrRoutes = psvrRouteService
				.getProxyserverRouteListOrderbyGap(query);

		// 该路由器已服务完成的中转服务器
		ServerCompleteRoute squery = new ServerCompleteRoute();
		squery.setRoute_id(route.getRouteid());
		List<ServerCompleteRoute> complete_routes = psvrRouteService
				.getServerCompleteRoutePageList(squery);

		// 可分配的中转服务器
		ProxyserverRoute proute = null;

		for (int i = 0; i < psvrRoutes.size(); i++) {

			ProxyserverRoute cur_proute = psvrRoutes.get(i);
			boolean canassign = true;

			// 排除该路由器已完成服务器的中转服务器
			for (int j = 0; j < complete_routes.size(); j++) {
				if (cur_proute.getProxyserver_id().equals(
						complete_routes.get(j).getProxyserver_id())) {
					canassign = false;
					break;
				}
			}

			// 不在已完成服务列表，可以分配
			if (canassign) {

				// 检查该中转服务器是否需要路由器
				// 检查是否中转服务器已有足够路由器

				// 手机、路由器预设比例
				Integer n = ConfigReader.getInstance().getIntProperty("PRENUM",
						3);
				int routeNum = cur_proute.getPhone_num() / n + 1;
				if (cur_proute.getPlan_route_num() == 0) {
					cur_proute.setPlan_route_num(routeNum);
					// 更新、ProxyserverRoute记录
					psvrRouteService.updateProxyserverRoute(cur_proute);
				}
				if (cur_proute.getOnline_route_num() >= routeNum) {
					continue;
				}

				proute = cur_proute;
				break;
			}

		}

		if (proute == null) {
			logger.debug(route.getRouteid()+"no aviable trans server , set free");
			// 无可分配中转服务器
			// 设置路由器空闲
			// 设置属性，存储
			route.setProxyserver_id("");
			route.setFlag(RouteFlag.UNASSIGN.toString());
			route.setFree(RouteFreeStaus.Free.toString());
			routeService.updateOrSaveDevice(route);
			clientList.put(channel, route);
			
			//TODO 是否需要返回数据
			return;
		}

		// 有可分配设置属性，存储
		route.setProxyserver_id(proute.getProxyserver_id());
		route.setAssigntime(DateUtil.getNowStr(""));
		route.setFlag(RouteFlag.ASSIGN_UNCONN.toString());
		route.setFree(RouteFreeStaus.Free.toString()); // 还是free
		routeService.updateOrSaveDevice(route);
		clientList.put(channel, route);

		// 返回分配信息
		JSONObject rdata = new JSONObject();
		try {
			rdata.put("ip", proute.getProxyserver_ip());
			rdata.put("port", proute.getProxyserver_port());
		} catch (JSONException e) {

			e.printStackTrace();
		}

		// 返回属性
		SendData(channel, new byte[] { 0x09, 0x02 }, rdata);

	}

	/**
	 * 发送数据
	 * 
	 * @param key
	 *            socket
	 * @param btype
	 *            2字节, 0x09，0x02 报文类型
	 * @param jsonValue
	 *            报文数据 json串
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public void SendData(SocketChannel key, byte[] btype, JSONObject jsonValue) {

		// 发送数据
		String value = jsonValue.toString();
		byte[] v_datapath = value.getBytes(Charset.forName("UTF-8"));
		byte[] buffResponse1 = new byte[4 + v_datapath.length];
		buffResponse1[0] = btype[0];// 0x09;
		buffResponse1[1] = btype[1];// 0x02;
		buffResponse1[2] = (byte) ((v_datapath.length >> 8) & 0xFF);
		buffResponse1[3] = (byte) (v_datapath.length & 0xFF);
		for (int i = 0; i < v_datapath.length; i++) {
			buffResponse1[4 + i] = v_datapath[i];
		}
		ByteBuffer writeBuf = ByteBuffer.allocate(buffResponse1.length);
		writeBuf.put(buffResponse1);
		writeBuf.flip();
		try {
			key.write(writeBuf);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 在队列中根据路由获取已有连接,并根据传入数据更新内存中设备信息
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	private SocketChannel getSktChannelByRouteID(Device device) {
		SocketChannel sc = null;
		for (Map.Entry<SocketChannel, Device> item : clientList.entrySet()) {
			if (item.getValue().getRouteid().equals(device.getRouteid())) {
				sc = item.getKey();
				//根据数据库中记录，更新内存数据
				 clientList.put(item.getKey(), device);	
			
				
				break;
			}
		}

		return sc;
	}

	/**
	 * 检查 是否有空闲路由器， 重新分配
	 * 
	 * 
	 * 1、检查路由器服务结束表及 路由器中状态为空闲且未分配的路由器，，在当前所有连接中是否存在， 存在则重新分配中转服务器
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-12
	 */

	public synchronized void checkFreeRoute() {
		String ENABLE_CHECK_TIMEOUT = ConfigReader.getInstance().getProperty(
				"ENABLE_CHECK_FREE");
		if (ENABLE_CHECK_TIMEOUT.equalsIgnoreCase("false"))
			return;

		logger.debug("------------ checkFreeRoute start------------");

		List<Device> freeRoutes = routeService.getFreeDeviceList();

		for (int i = 0; i < freeRoutes.size(); i++) {
			try {

				Device d = freeRoutes.get(i);

				// 查询连接中是否有当前路由
				SocketChannel sc = getSktChannelByRouteID(d);
				if (sc != null) {
					logger.debug("free channel" + sc.toString() + "/ route:"
							+ d.toString());
					// 重新分配路由
					assignChannel(sc);
				}
			} catch (Exception e) {
				continue;
			}
		}
		logger.debug("------------ checkFreeRoute end------------");
	}

}