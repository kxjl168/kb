package com.kxjl.web.routeM.action;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.device.model.Device;
import com.kxjl.web.device.model.Device.RouteFlag;
import com.kxjl.web.device.model.Device.RouteFreeStaus;
import com.kxjl.web.device.service.DeviceService;
import com.kxjl.web.proxyserver.service.ProxyserverService;
import com.kxjl.web.routeM.model.ProxyserverRoute;
import com.kxjl.web.routeM.service.ProxyserverRouteService;
import com.kxjl.web.system.action.base.BaseController;

/**
 * 后台动态调整 中转服务器上的 预设路由器数量
 * 
 * @param map
 * @return
 * @author zj
 * @date 2017-12-12
 */

@Controller
@RequestMapping(value = "/tick")
public class TickController extends BaseController {

	@Autowired
	private DeviceService routeService;

	@Autowired
	private ProxyserverRouteService psvrRouteService;

	private static final long serialVersionUID = -6250653812905764989L;

	private static Logger logger = Logger.getLogger(TickController.class);

	/**
	 * 定时任务、调整中转服务器上的 预设路由器数量,根据手机预计连接数计算
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-13
	 */
	public void adjustPlanRouteNum() {

		
		String  ENABLE_ADJUST_PLAN= ConfigReader.getInstance().getProperty("ENABLE_ADJUST_PLAN");
		if(ENABLE_ADJUST_PLAN.equalsIgnoreCase("false"))
			return;
		
		
		logger.info("***tick****: adjustPlanRouteNum start****");
		psvrRouteService.adjustPlanRouteNum();
		logger.info("***tick****: adjustPlanRouteNum  end****");
		

	}

	/**
	 * 定时任务
	 * 检查路由器分配 超时
	 * 
	 * 
	 * 1、检查路由器表中状态为已分配未连接的路由器，检查时间是否超时，超时则修改状态为空闲、标记为未分配，等待再次分配
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2017-12-12
	 */
	public void checkOverTime() {

		
		String  ENABLE_CHECK_TIMEOUT= ConfigReader.getInstance().getProperty("ENABLE_CHECK_TIMEOUT");
		if(ENABLE_CHECK_TIMEOUT.equalsIgnoreCase("false"))
			return;
		
		logger.info("------------ checkOverTime start------------");
		List<Device> timeoutRoutes = routeService.getTimeOutDeviceList();
		logger.info("timeoutRoutes size "+timeoutRoutes.size());
		for (int i = 0; i < timeoutRoutes.size(); i++) {
			try {

				Device d = timeoutRoutes.get(i);
				d.setProxyserver_id("");
				d.setFlag(RouteFlag.UNASSIGN.toString());
				d.setFree(RouteFreeStaus.Free.toString());
				d.setAssigntime("");
				routeService.updateOrSaveDevice(d);
			} catch (Exception e) {
				logger.error(e.getMessage());
				continue;
			}
		}
		
		logger.info("------------ checkOverTime end------------");

	}

}
