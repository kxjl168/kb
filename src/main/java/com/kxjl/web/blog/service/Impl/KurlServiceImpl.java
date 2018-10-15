package com.kxjl.web.blog.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.blog.action.Kdata;
import com.kxjl.web.blog.dao.KurlDao;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;

@Service(value = "kurlService")
public class KurlServiceImpl implements KurlService {

	@Autowired
	private KurlDao dictInfoDao;

	
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
