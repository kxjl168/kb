package com.kxjl.web.blog.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.blog.action.Kdata;
import com.kxjl.web.blog.dao.KurlDao;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;
import com.kxjl.web.system.model.DictInfo;

@Service(value = "kurlService")
public class KurlServiceImpl implements KurlService {

	@Autowired
	private KurlDao dictInfoDao;

	
	
	/**
	 * 通过所有blog
	 * 
	 * @author:kxjl
	 * @date 2020年10月29日
	 */
	public void passallBlogKurl()
	{
		dictInfoDao.passallBlogKurl();
	}
	
	
	/**
	 * 获取链接分类数据
	 * @param query
	 * @return
	 * @author zj
	 * @date 2018年10月15日
	 */
	public 	Map<String, List<Kurl>>  getKurlItemPageList(Kurl query) {
		
		List<Kurl> infos = getKurlPageList(query);
		
		Map<String, List<Kurl>> datas = new HashMap<String, List<Kurl>>();
		String HTTP_PATH = ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH");
		String prepath = HTTP_PATH;
		for (int i = 0; i < infos.size(); i++) {

			Kurl u = infos.get(i);
			u.setVal2(prepath);
			List<Kurl> item = new ArrayList<Kurl>();

			if (datas.containsKey(u.getUrl_type())) {
				item = datas.get(u.getUrl_type());

			}
			item.add(u);
			datas.put(u.getUrl_type(), item);

		}
		
		
		
		return datas;
	}
	
	public List<String> getUrlTreeSelectSecond(Kurl query) {

		List<String> rst=new ArrayList<>();
		
		Map<String, List<Kurl>>  items=getKurlItemPageList(query);
		 Iterator<String> keys= items.keySet().iterator();
		// 一级分类
		 List<Kurl> infos = new ArrayList<>();
		 while(keys.hasNext())
		{
			Kurl parent=new Kurl();
			parent.setUrl_name(keys.next());
			infos.add(parent);
		}
		

		Gson gs = new Gson();

		for (int i = 0; i < infos.size(); i++) {
			Kurl parent=infos.get(i);
			JSONObject jsObj = new JSONObject();
		
			String pInfoStr = gs.toJson(parent);
			jsObj = new JSONObject(pInfoStr);
			
			

			List<Kurl> all_child = items.get(parent.getUrl_name());

			String level2list = gs.toJson(all_child);

		
			jsObj.put("child", level2list);
			jsObj.put("httppath", 	ConfigReader.getInstance().getProperty("FILE_SVR_HTTP_OUTER_PATH"));

			rst.add(jsObj.toString());

		}

		return rst;

	}
	
	
	/**
	 * 分页获取banner列表
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Kurl> getKurlPageList(Kurl query) {
		
		
		List<Kurl> infos =	 dictInfoDao.getKurlPageList(query);
		
		
		return infos;
		// return null;
	}

	/**
	 * 获取banner总条数
	 * 
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getKurlPageListCount(Kurl query) {
		return dictInfoDao.getKurlPageListCount(query);
	}

	/**
	 * 添加Kurl
	 * 
	 * @param Kurl
	 * @return
	 */
	public int addKurl(Kurl Kurl) {
		return dictInfoDao.addKurl(Kurl);
	}

	/**
	 * 删除Kurl
	 * 
	 * @param id
	 * @return
	 */
	public int deleteKurl(@Param(value = "id") Integer id) {
		return dictInfoDao.deleteKurl(id);
	}

	/**
	 * 更新Kurl
	 * 
	 * @param Kurl
	 * @return
	 */
	public int updateKurl(Kurl Kurl) {
		return dictInfoDao.updateKurl(Kurl);
	}

	/**
	 * 根据ID获取Kurl信息
	 * 
	 * @param bannerID
	 * @return
	 * @date 2016-8-4
	 */
	public Kurl getKurlInfoById(Kurl query) {
		return dictInfoDao.getKurlInfoById(query);
	}

}
