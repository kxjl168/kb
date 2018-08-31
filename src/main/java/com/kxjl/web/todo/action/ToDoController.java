package com.kxjl.web.todo.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.tool.utils.MapResult;
import com.kxjl.tool.utils.MapResultUtil;
import com.kxjl.tool.utils.PageCondition;
import com.kxjl.tool.utils.PageUtil;
import com.kxjl.web.autodata.model.ToDo;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.todo.service.ToDoService;

@Controller
@RequestMapping(value = "/todo")
public class ToDoController extends BaseController {

	@Autowired
	ToDoService todoService;

	@RequestMapping(value = "/index")
	public ModelAndView GroupList() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/page/todo/index");

		return view;
	}

	/**
	 * 页面-获取banner列表
	 * 
	 * @param clientType
	 * @param packageName
	 * @param curPage
	 *            当前页
	 * @param pageCount
	 *            每页多少条
	 * @return
	 */
	@RequestMapping(value = "/getInfoList")
	public void getInfoList(ToDo bean, PageCondition pageCondition, HttpServletRequest request,
			HttpServletResponse response) {

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			Page page = PageUtil.getPage(pageCondition);
			List<ToDo> infos = todoService.getTodoList(bean);

			Gson gs =new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String jsStr = gs.toJson(infos);

		
			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", page.getTotal());
			jsonOut.put("rows",new org.json.JSONArray(jsStr));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");
				jsonOut.put("total", 0);
				jsonOut.put("rows", "");
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}

	

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(ToDo bean, HttpServletRequest request) {

		MapResult msg = new MapResult();
		try {
			int result = todoService.deleteByPrimaryKey(bean.getId());
			if (result >0) {
				msg = MapResultUtil.success();
			}
			else
				msg = MapResultUtil.fail();
		} catch (Exception e) {
			msg = MapResultUtil.fail();
		}
		Gson gs=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String rstr=gs.toJson(msg);
		return rstr;
	}

	/**
	 * 
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(ToDo item, HttpServletRequest request) {
		MapResult msg = new MapResult();
		int rst = -1;
		try {
			if(item.getTitle()==null)
				msg=MapResultUtil.fail("事项不能为空");
			else
			{

			if (null == item.getId() || item.getId() == 0) {

				item.setCreateDate(new Date());
				rst = todoService.insertSelective(item);
			} else {
				rst = todoService.updateByPrimaryKeySelective(item);
			}

			if (rst > 0) {
				msg = MapResultUtil.success();
			}
			else
				msg=MapResultUtil.fail();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gs=new Gson();
		String rstr=gs.toJson(msg);
		return  rstr;
	}

}
