
package com.kxjl.web.blog.action;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kxjl.admin.util.Constants;

import com.kxjl.admin.util.Page;
import com.kxjl.base.aopAspect.CurrentUser;
import com.kxjl.base.aopAspect.NoNeedAuthorization;
import com.kxjl.base.interceptor.TokeUtils;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.IconUtil;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.autodata.pojo.LinkRelation;
import com.kxjl.web.autodata.service.LinkRelationService;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;
import com.kxjl.web.search.action.SearchController;
import com.kxjl.web.spider.BlogLinkSpider;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.persistence.dao.KgEntityMapper;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgClassExample;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.persistence.entity.KgTags;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.service.KgClassService;
import com.kxjl.admin.service.KgEntityService;
import com.kxjl.admin.service.KgObjectToObjectService;
import com.kxjl.admin.service.KgPropertyService;
import com.kxjl.admin.service.KgTagsService;

/**
 * url查询 new
 * 
 * @author kxjl
 * @date 2020年10月26日
 */
@RestController
@RequestMapping("/kg/kurl")
public class KgUrlController {

	Logger logger = LoggerFactory.getLogger(KgUrlController.class);

	@Autowired
	KurlService kurlService;

	@Autowired
	IconUtil iconUtil;

	@Autowired
	TokeUtils tokeUtils;

	@Autowired
	KgEntityService kgEntityService;

	
	@Autowired
	BlogLinkSpider blogLinkSpider;

	/**
	 * 开始扫描blog
	 * 
	 * @author:kxjl
	 * @date 2020年10月23日
	 */
	@RequestMapping("/startspider")
	public void spider(String url_val) {
		blogLinkSpider.getbloglist(url_val);
	}

	@RequestMapping("/stopspider")
	public void stopspider() {
		blogLinkSpider.stop();
	}
	
	/**
	 * 同步单条图谱数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param queryData
	 * @return
	 * @author:kxjl
	 * @date 2020年10月28日
	 */
	@RequestMapping("/asyncEntityRelation")
	@ResponseBody
	public WZResponseEntity<?> asyncEntityRelation(@CurrentUser LoginUser user, HttpServletRequest request,
			HttpServletResponse response, String id) {

		WZResponseEntity<Map> rst = new WZResponseEntity<>();

		// String rst = "";
		try {

			Kurl queryData = null;
			if (id != null) {
				queryData = new Kurl();
				queryData.setId(Integer.parseInt(id));
			}

			kgEntityService.rebuildEntityAndRelationByUrl(queryData);
			rst.setIsSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rst;

	}

	/**
	 * 可见
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param queryData
	 * @return
	 * @author:kxjl
	 * @date 2020年10月28日
	 */
	@RequestMapping("/showurl")
	@ResponseBody
	public WZResponseEntity<?> showurl(@CurrentUser LoginUser user, HttpServletRequest request,
			HttpServletResponse response, String id) {

		WZResponseEntity<Map> rst = new WZResponseEntity<>();

		Map<String, Object> jsonOut = new HashMap<>();

		// String rst = "";
		try {
			Kurl queryData = new Kurl();
			queryData.setId(Integer.parseInt(id));

			queryData.setIsshow("1");// 外部显示.
			kurlService.updateKurl(queryData);
			rst.setIsSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rst;

	}
	
	/**
	 * 不可见
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param queryData
	 * @return
	 * @author:kxjl
	 * @date 2020年10月28日
	 */
	@RequestMapping("/passallurl")
	@ResponseBody
	public WZResponseEntity<?> passallurl(@CurrentUser LoginUser user, HttpServletRequest request,
			HttpServletResponse response, String id) {

		WZResponseEntity<Map> rst = new WZResponseEntity<>();

		Map<String, Object> jsonOut = new HashMap<>();

		// String rst = "";
		try {
			
			kurlService.passallBlogKurl();
			rst.setIsSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rst;

	}


	/**
	 * 不可见
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param queryData
	 * @return
	 * @author:kxjl
	 * @date 2020年10月28日
	 */
	@RequestMapping("/hideurl")
	@ResponseBody
	public WZResponseEntity<?> hideurl(@CurrentUser LoginUser user, HttpServletRequest request,
			HttpServletResponse response, String id) {

		WZResponseEntity<Map> rst = new WZResponseEntity<>();

		Map<String, Object> jsonOut = new HashMap<>();

		// String rst = "";
		try {
			Kurl queryData = new Kurl();
			queryData.setId(Integer.parseInt(id));

			queryData.setIsshow("2");// 外部不显示.
			kurlService.updateKurl(queryData);
			rst.setIsSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rst;

	}

	/**
	 * 更新 或新增
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param queryData
	 * @return
	 * @author:kxjl
	 * @date 2020年10月28日
	 */
	@RequestMapping("/updateurl")
	@ResponseBody
	public WZResponseEntity<?> updateurl(@CurrentUser LoginUser user, HttpServletRequest request, String id,
			String icon, String url_name, String url_val,String url_type, String desc_info, HttpServletResponse response) {

		WZResponseEntity<Map> rst = new WZResponseEntity<>();

		Map<String, Object> jsonOut = new HashMap<>();

		// String rst = "";
		try {
			Kurl queryData = new Kurl();

			queryData.setUrl_name(url_name);
			queryData.setUrl_val(url_val);
			queryData.setIcon(icon);
			queryData.setDesc_info(desc_info);
			queryData.setUrl_type(url_type);
			queryData.setVal1("1");
			
			

			if (id != null && !id.equals("")) {
				queryData.setId(Integer.parseInt(id));
				kurlService.updateKurl(queryData);
			} else {
				kurlService.addKurl(queryData);
			}

			rst.setIsSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rst;

	}

	/**
	 * 删除
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param queryData
	 * @return
	 * @author:kxjl
	 * @date 2020年10月28日
	 */
	@RequestMapping("/deleteurl")
	@ResponseBody
	public WZResponseEntity<?> deleteurl(@CurrentUser LoginUser user, HttpServletRequest request,
			HttpServletResponse response, String id) {

		WZResponseEntity<Map> rst = new WZResponseEntity<>();

		Map<String, Object> jsonOut = new HashMap<>();

		// String rst = "";
		try {

			kurlService.deleteKurl(Integer.parseInt(id));

			rst.setIsSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rst;

	}

	/**
	 * 下拉提示
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/searchtip")
	@ResponseBody
	@NoNeedAuthorization // 下拉提示
	public WZResponseEntity<?> searchtip(@CurrentUser LoginUser user, HttpServletRequest request,
			HttpServletResponse response, String data, Kurl queryData) {

		WZResponseEntity<List<Kurl>> rst = new WZResponseEntity<>();

		Map<String, Object> jsonOut = new HashMap<>();

		// String rst = "";
		try {

			Kurl query = new Kurl();
			query.setPage(1);
			query.setPageCount(20);
			query.setVal1("1");// 普通链接

			if (user != null && user.getRoleId().contains(UserType.Root.toString())) {

			} else
				query.setIsshow("1");// 普通用户显示

			// query.setIp(ip);
			// query.setCity(city);
			// query.setUrl_type(dict_type);// (url_title);// (id);
			query.setUrl_name(queryData.getUrl_name());// (url_name);

			List<Kurl> datas = kurlService.getKurlPageList(query);

			rst.setIsSuccess(true);
			rst.setBody(datas);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				rst.setIsSuccess(false);

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}

		return rst;
	}

	/**
	 * <p>
	 * query info by id
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/searchurl")
	@ResponseBody
	@NoNeedAuthorization // 下拉提示
	public WZResponseEntity<?> searchurl(@CurrentUser LoginUser user, HttpServletRequest request,
			HttpServletResponse response, String data, Kurl queryData) {
		// logger.info(" searchController searchtip :{}", keyword);

		// WZResponseEntity<List> rst = new WZResponseEntity<>();

		// Map data = new HashMap<String, Object>();

		WZResponseEntity<Map> rst = new WZResponseEntity<>();

		// SysUserBean m = tokeUtils.getCurrentUser();

		// String data = request.getParameter("data");
		// JSONObject jsonIN;
		Map<String, Object> jsonOut = new HashMap<>();

		// String rst = "";
		try {

			// jsonIN = new JSONObject(data);

			Kurl query = new Kurl();
			query.setPage(1);
			query.setPageCount(10000);
			query.setVal1("1");// 普通链接

			if (user != null && user.getRoleId().contains(UserType.Root.toString())) {

			} else
				query.setIsshow("1");// 普通用户显示

			// query.setIp(ip);
			// query.setCity(city);
			// query.setUrl_type(dict_type);// (url_title);// (id);
			query.setUrl_name(queryData.getUrl_name());// (url_name);

			String HTTP_PATH = ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
			String prepath = HTTP_PATH;
			Map<String, List<Kurl>> datas = kurlService.getKurlItemPageList(query);

			Gson gs = new Gson();

			JSONArray ja = new JSONArray();
			int blogindex = 0;
			int i = 0;
			for (List<Kurl> item : datas.values()) {
				JSONObject jo = new JSONObject();
				jo.put("name", item.get(0).getUrl_type());
				jo.put("id", "u" + UUIDUtil.getUUID());
				jo.put("val", gs.toJson(item));
				
				ja.put(jo);

				if (item.get(0).getUrl_type().equals("BLOG"))
					blogindex = i;

				i++;
			}
			
			if(ja.length()>0)
			{
			// blog放到第一位
			JSONObject blogDatas = (JSONObject) ja.get(blogindex);
			
			JSONObject zDatas = (JSONObject) ja.get(0);
			ja.put(0, blogDatas);
			ja.put(blogindex, zDatas);
			
			
			}

			int total = kurlService.getKurlPageListCount(query);

			// String jsStr = gs.toJson(infos);

			jsonOut.put("val2", prepath);
			//jsonOut.put("ResponseCode", "200");
			//jsonOut.put("ResponseMsg", "");
			//jsonOut.put("total", total);
			jsonOut.put("datalist", ja.toString());

			rst.setIsSuccess(true);
			rst.setBody(jsonOut);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				rst.setIsSuccess(false);

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}

		return rst;
	}

}