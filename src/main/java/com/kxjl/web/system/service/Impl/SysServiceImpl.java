package com.kxjl.web.system.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.autodata.dao.VisitDataMapper;
import com.kxjl.web.autodata.model.VisitData;
import com.kxjl.web.system.dao.DictInfoDao;
import com.kxjl.web.system.dao.MenuInfoDao;
import com.kxjl.web.system.dao.SvrFileInfoDao;
import com.kxjl.web.system.model.DictInfo;
import com.kxjl.web.system.model.SvrFileInfo;
import com.kxjl.web.system.service.CommonService;
import com.kxjl.web.system.service.DictInfoService;
import com.kxjl.web.system.service.SysService;
import com.kxjl.web.system.service.SystemParamService;

@Service
public class SysServiceImpl implements SysService {

	@Autowired
	private DictInfoDao dictInfoDao;

	@Autowired
	private SystemParamService systemService;

	@Autowired
	private DictInfoDao dictInfoService;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private SvrFileInfoDao fileInfoDao;

	@Autowired
	private VisitDataMapper visitDataMapper;

	
	public Map getSysInfo(){
		Map jsonOut = new HashMap<String,Object>();

		String rst = "";
		try {

			Gson gs=new Gson();
			DictInfo q = new DictInfo();
			q.setDict_type("blog_head");
			List<DictInfo> dhead = dictInfoService.getDictInfoPageList(q);
			if (dhead != null && dhead.size() != 0&&dhead.get(0).getDict_name()!=null) {
				
				try {
					SvrFileInfo info=new SvrFileInfo();
					info.setId(Integer.parseInt( dhead.get(0).getDict_name()));
					SvrFileInfo finfo=fileInfoDao.getFileInfo(info);
					jsonOut.put("fileinfo",finfo);	
				} catch (Exception e) {
					jsonOut.put("fileinfo", "");
				}
			}
			else
				jsonOut.put("fileinfo", "");

			q = new DictInfo();
			q.setDict_type("blog_sign");
			dhead = dictInfoService.getDictInfoPageList(q);
			if (dhead != null && dhead.size() != 0) {
				
				
				jsonOut.put("sign", dhead.get(0).getDict_name());
			}
			else
				jsonOut.put("sign", "");

			VisitData sum = visitDataMapper.selectSum();

			String HTTP_PATH = ConfigReader.getInstance().getProperty(
					"FILE_SVR_HTTP_OUTER_PATH");
		
			
			jsonOut.put("visitData",sum);
			jsonOut.put("httppath", HTTP_PATH);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		return jsonOut;
	}
	
	public JSONObject getSysInfoJSON(){
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			Gson gs=new Gson();
			DictInfo q = new DictInfo();
			q.setDict_type("blog_head");
			List<DictInfo> dhead = dictInfoService.getDictInfoPageList(q);
			if (dhead != null && dhead.size() != 0&&dhead.get(0).getDict_name()!=null) {
				
				try {
					SvrFileInfo info=new SvrFileInfo();
					info.setId(Integer.parseInt( dhead.get(0).getDict_name()));
					SvrFileInfo finfo=fileInfoDao.getFileInfo(info);
					jsonOut.put("fileinfo",new JSONObject( gs.toJson(finfo)));	
				} catch (Exception e) {
					jsonOut.put("fileinfo", "");
				}
			}
			else
				jsonOut.put("fileinfo", "");

			q = new DictInfo();
			q.setDict_type("blog_sign");
			dhead = dictInfoService.getDictInfoPageList(q);
			if (dhead != null && dhead.size() != 0) {
				
				
				jsonOut.put("sign", dhead.get(0).getDict_name());
			}
			else
				jsonOut.put("sign", "");

			VisitData sum = visitDataMapper.selectSum();

			String HTTP_PATH = ConfigReader.getInstance().getProperty(
					"FILE_SVR_HTTP_OUTER_PATH");
		
			
			jsonOut.put("visitData",new JSONObject(gs.toJson(sum)) );
			jsonOut.put("httppath", HTTP_PATH);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		return jsonOut;
	}
	
}
