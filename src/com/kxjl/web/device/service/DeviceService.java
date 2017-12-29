package com.kxjl.web.device.service;

import java.util.List;



import com.kxjl.web.device.model.Device;


public interface DeviceService {
	/**
	 * 分页获取route列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Device> getDevicePageList(Device query);
	

	
	/**
	 * 根据条件获取空闲的的路由
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public List<Device> getFreeDeviceList();
	
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
	public List<Device> getDeviceList(Device query);
	
	/**
	 * 获取分配超时的路由
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public List<Device> getTimeOutDeviceList();
	

	/**
	 * 获取banner总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getDevicePageListCount(Device query);
	
	
	/**
	 * 更新Device
	 * @param Device
	 * @return
	 */
	public int updateOrSaveDevice(Device Device);
	
}
