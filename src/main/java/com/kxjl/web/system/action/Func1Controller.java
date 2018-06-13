package com.kxjl.web.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.system.action.base.BaseController;


/*

 * @date 2016-2-23
 * @author zj
 */
@Controller
//@RequestMapping(value="/func1")
public class Func1Controller extends BaseController {

	
	
	@RequestMapping(value="test1")
	public String test1()
	{
		return "";
	}
	
	@RequestMapping(value="versionManager1")
	public String test2()
	{
		return "/view/test/grid";
	}
	
	
	
	@RequestMapping(value="test2")
	public void  test1(HttpServletRequest request, HttpServletResponse response)
	{
		String json="{\"name\":\"test\"}";
		JsonUtil.responseOutWithJson(response, json);
	}
	
}
