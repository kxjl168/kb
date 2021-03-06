package com.kxjl.web.device.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.kxjl.web.device.model.Device;

public interface DeviceDao {

	
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Device> getDevicePageList(Device query);

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
	public int updateDevice(Device Device);
	
	
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
	
	

	
	/*
	
	
	*//**
	 * 删除Device
	 * @param id
	 * @return
	 *//*
	public int deleteDevice(@Param(value="banner_id")Integer id);


	*//**
	 *  获取Device列表
	 * @return
	 *//*
	public List<Device> getDeviceList();
*/
	/**
	 * 添加Device
	 * @param Device
	 * @return
	 */
	public int addDevice(Device Device);

	/**
	 * 根据ID获取Device信息
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public Device getDeviceInfoById(@Param(value="routeid")String id);
}
