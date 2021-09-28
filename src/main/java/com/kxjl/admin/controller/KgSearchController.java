
package com.kxjl.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.Page;
import com.kxjl.base.aopAspect.NoNeedAuthorization;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.web.autodata.pojo.LinkRelation;
import com.kxjl.web.autodata.service.LinkRelationService;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;
import com.kxjl.web.search.action.SearchController;
import com.kxjl.web.spider.BlogLinkSpider;
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
 * 通用查询接口
 * 
 * @author kxjl
 * @date 2020年10月12日
 */
@RestController
@RequestMapping("/kg/kg-search")
public class KgSearchController {

	Logger logger = LoggerFactory.getLogger(KgSearchController.class);

	/**
	 * <p>
	 * BussinessService
	 * </p>
	 */
	@Autowired
	KgEntityService kgEntityService;

	@Autowired
	KgClassService kgClassService;

	@Autowired
	KgTagsService kgTagsService;

	@Autowired
	SearchController searchController;

	@Autowired
	KurlService kurlService;

	@Autowired
	KgPropertyService kgPropertyService;

	@Autowired
	BlogLinkSpider blogLinkSpider;

	/**
	 * 开始扫描blog
	 * 
	 * @author:kxjl
	 * @date 2020年10月23日
	 */
	@RequestMapping("/spider")
	@NoNeedAuthorization // 下拉提示
	public void spider() {
		blogLinkSpider.getbloglist("");
	}

	@RequestMapping("/stopspider")
	@NoNeedAuthorization // 下拉提示
	public void stopspider() {
		blogLinkSpider.stop();
	}

	@Autowired
	LinkRelationService linkRelationService;

	@Autowired
	KgObjectToObjectService kgObjectToObjectService;

	/**
	 * <p>
	 * blog 发现关系重建
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/rebuildBlogRelation")
	@ResponseBody
	@NoNeedAuthorization // 下拉提示
	public void rebuildBlogRelation() {

		LoginUser user = new LoginUser();
		user.setRoleId(Constants.DEFAULT_ADMIN_ROLEID);

		kgEntityService.rebuildEntityAndRelationByUrl(null);

	}


	/**
	 * <p>
	 * query info by id
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/searchtip")
	@ResponseBody
	@NoNeedAuthorization // 下拉提示
	public WZResponseEntity<?> searchtip(String keyword) {
		logger.info(" searchController searchtip :{}", keyword);

		WZResponseEntity<List> rst = new WZResponseEntity<>();

		Map data = new HashMap<String, Object>();

		LoginUser user = new LoginUser();
		user.setUserId("");

		KgClass cquery = new KgClass();
		cquery.setClsName(keyword);

		Pagination pageCondition = new Pagination();
		pageCondition.setPageNo(1);
		pageCondition.setPageSize(20);

		// 同名概念
		List<KgClass> kclasslst = kgClassService.selectList(user, cquery, pageCondition).getResults();

		KgEntity equery = new KgEntity();
		equery.setName(keyword);
		List<KgEntity> entitylst = kgEntityService.selectList(user, equery, pageCondition).getResults();

		KgTags tquery = new KgTags();
		tquery.setName(keyword);
		List<KgTags> taglst = kgTagsService.selectList(tquery, pageCondition).getResults();

		List<String> tips = new ArrayList<String>();

		if (entitylst != null) {
			for (KgEntity entity : entitylst) {
				if (!tips.contains(entity.getName())) {
					tips.add(entity.getName());
				}
			}
		}
		if (kclasslst != null) {
			for (KgClass item : kclasslst) {
				if (!tips.contains(item.getClsName())) {
					tips.add(item.getClsName());
				}
			}
		}
		if (taglst != null) {
			for (KgTags item : taglst) {
				if (!tips.contains(item.getName())) {
					tips.add(item.getName());
				}
			}
		}

		rst.setIsSuccess(true);
		rst.setBody(tips);

		return rst;
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 * @author:kxjl
	 * @date 2020年10月15日
	 */
	@RequestMapping("/searchContext")
	@ResponseBody
	@NoNeedAuthorization // 网页查询
	public WZResponseEntity<?> searchContext(String keyword, String key, String url, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(" searchController searchContext keyword:/key {},{},{}", keyword, key, url);

		WZResponseEntity<String> rst = new WZResponseEntity<>();

		String data = searchController.normalrealSearch("https://www.baidu.com", keyword, key, url, request, response);

		rst.setIsSuccess(true);
		rst.setBody(data);

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
	@RequestMapping("/search")
	@ResponseBody
	@NoNeedAuthorization // 通用查询
	public WZResponseEntity<?> search(String keyword) {
		logger.info(" searchController search keyword:{}", keyword);

		WZResponseEntity<Map> rst = new WZResponseEntity<>();

		Map data = new HashMap<String, Object>();

		LoginUser user = new LoginUser();
		user.setUserId("");

		KgClass cquery = new KgClass();
		cquery.setClsName(keyword);

		Pagination pageCondition = new Pagination();
		pageCondition.setPageNo(1);
		pageCondition.setPageSize(20);

		// 同名概念
		KgClass kclass = kgClassService.selectByName(cquery);

		// 概念实体集合
		if (kclass != null) {

			KgEntity equery = new KgEntity();
			equery.setClsId(kclass.getId());
			List<KgEntity> result = kgEntityService.selectList(user, equery, pageCondition).getResults();

			if (result != null && result.size() > 0) {
				// 实体集合
				data.put("entitylst", result);
				// 同名概念
				data.put("cls", kclass);
			}
		} else {
			// 标签查询

			KgEntity equery = new KgEntity();
			equery.setTags(keyword);
			List<KgEntity> result = kgEntityService.selectList(user, equery, pageCondition).getResults();

			if (result != null && result.size() > 0) {
				// 实体集合
				data.put("entitylst", result);

				kclass = new KgClass();
				kclass.setClsName(keyword);
				data.put("cls", kclass);
			}

		}

		// 同名实体

		KgEntity equery2 = new KgEntity();
		equery2.setFullName(keyword);
		equery2.setName(keyword);
		KgEntity entity = kgEntityService.selectByName(equery2);
		if (entity != null) {
			entity = kgEntityService.getOne(entity.getId(), Constants.DEFULT_VERSION);

		} else {
			List<KgEntity> entitys = kgEntityService.selectList(user, equery2, pageCondition).getResults();
			for (int i = 0; i < entitys.size(); i++) {
				if (entitys.get(i).getName().equals(keyword)) {
					entity = entitys.get(i);
					break;
				}
			}

		}

		if (entity != null)
			// 实体集合
			data.put("entity", entity);

		rst.setIsSuccess(true);
		rst.setBody(data);

		return rst;
	}

}