package com.kxjl.web.device.service.Impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.device.dao.DeviceDao;
import com.kxjl.web.device.model.Device;
import com.kxjl.web.device.model.Device.RouteFlag;
import com.kxjl.web.device.model.Device.RouteFreeStaus;
import com.kxjl.web.device.service.DeviceService;
import com.kxjl.web.routeM.action.TickController;
import com.kxjl.web.system.dao.SystemParamsDao;

@Service(value = "DeviceService")
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceDao deviceDao;

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
	public List<Device> getDevicePageList(Device query) {
		return deviceDao.getDevicePageList(query);
	}
	private static Logger logger = Logger.getLogger(DeviceServiceImpl.class);

	/**
	 * 获取分配超时的路由
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public List<Device> getTimeOutDeviceList() {
		Device query = new Device();
		
		//路由器分配中转服务器后、一直未连接上的超时时间
		Integer timeMin = ConfigReader.getInstance().getIntProperty("ROUTE_TIME_OUT_MIN", 5);
		
		query.setTimeoutMin(timeMin);
		//query.setProxyserver_id("");
		query.setFlag(RouteFlag.ASSIGN_UNCONN.toString());
		return deviceDao.getDeviceList(query);
	}
	/**
	 * 获取路由信息
	 * 
	 * 根据条件获取空闲或者超时等
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public List<Device> getDeviceList(Device query)
	{
		return deviceDao.getDeviceList(query);
	}
	

	/**
	 * 获取空闲的的路由
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public List<Device> getFreeDeviceList() {
		Device query = new Device();
		query.setFlag(RouteFlag.UNASSIGN.toString());
		query.setFree(RouteFreeStaus.Free.toString());
		return deviceDao.getDeviceList(query);
	}

	/**
	 * 获取banner总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public int getDevicePageListCount(Device query) {
		return deviceDao.getDevicePageListCount(query);
	}

	/**
	 * 更新Device
	 * 
	 * @param Device
	 * @return
	 */
	public int updateOrSaveDevice(Device device) {

		Device d = deviceDao.getDeviceInfoById(device.getRouteid());
		if (d != null)
			return deviceDao.updateDevice(device);
		else
			return deviceDao.addDevice(device);
	}

}
