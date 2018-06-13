package com.kxjl.web.version.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.system.action.base.BaseController;


/**
 * 外部调用接口
 * 
 * @date 2016-3-4
 * @author zj
 * 
 */
@Controller
// @RequestMapping(value="/func1")
public class VController extends BaseController {

	

	// 日志记录对象
	private Logger logger = Logger.getLogger(VController.class);

	@RequestMapping(value = "/getVersionInfo")
	public void getVersionInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/version/getVersionInfo.action").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
