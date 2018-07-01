package com.kxjl.web.blog.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.service.SysService;

import sun.util.logging.resources.logging;

@Controller
@RequestMapping(value = "/")
public class PublicController extends BaseController {

	@Autowired
	KurlService kurlService;

	@Autowired
	SysService sysService;

	
	@RequestMapping(value = "/public/index/{type}/{value}.html")
	public String listhtml(HttpServletRequest request,@PathVariable("type") String type,@PathVariable("value") String value) {

		return "forward:/public/index/?"+type+"="+value;
	}
	
	@RequestMapping(value = "/public/index/")
	public ModelAndView list(HttpServletRequest request) {

		ModelAndView view = getSysData();
		view.setViewName("/public/index/main");

		return view;
	}
	
	@RequestMapping(value = "/public/about/")
	public ModelAndView about(HttpServletRequest request) {

		ModelAndView view = getSysData();
		view.setViewName("/public/about/index");

		return view;
	}
	
	@RequestMapping(value = "/public/bx/")
	public ModelAndView bx(HttpServletRequest request) {

		ModelAndView view = getSysData();
		view.setViewName("/public/bx/index");

		return view;
	}

	private ModelAndView getSysData() {
		ModelAndView view = new ModelAndView();

		Map jsInfo = sysService.getSysInfo();

		view.addObject("httppath", jsInfo.get("httppath"));
		view.addObject("head", jsInfo.get("fileinfo"));
		view.addObject("sign", jsInfo.get("sign"));
		view.addObject("visitdata", jsInfo.get("visitData"));
		return view;
	}

	@RequestMapping(value = "/")
	public String index() {
		ModelAndView view = getSysData();
		view.setViewName("/public/index/");

		return "redirect:/public/index/";
	}

	@RequestMapping(value = "/public/search")
	public String search() {
		ModelAndView view = getSysData();
		view.setViewName("/public/index/");

		return "/public/search/index";
	}

	@RequestMapping(value = "/page/set")
	public String p_set(Map<String, Object> maps) {

		maps.putAll(sysService.getSysInfo());

		return "/page/set/main";
	}
	
	@RequestMapping(value = "/page/{url}")
	public String p_btype(Map<String, Object> maps,
			@PathVariable(name = "url") String url) {

		maps.putAll(sysService.getSysInfo());

		return "/page/" + url + "/index";
	}
	
	@RequestMapping(value = "/pown/{url}")
	public String pown_btype(Map<String, Object> maps,
			@PathVariable(name = "url") String url) {

		maps.putAll(sysService.getSysInfo());

		return "/pown/" + url + "/index";
	}

	@RequestMapping(value = "/public/cat")
	public String cat() {
		ModelAndView view = getSysData();
		view.setViewName("/public/index/");

		return "/public/cat/index";
	}

	@RequestMapping(value = "/public/detail/")
	public ModelAndView detail() {

		ModelAndView view = getSysData();
		view.setViewName("/public/detail/main");

		return view;
	}

}
