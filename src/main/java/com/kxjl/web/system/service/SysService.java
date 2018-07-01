package com.kxjl.web.system.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;




import com.kxjl.web.system.model.DictInfo;


public interface SysService {
	
	public Map getSysInfo();
	
	public JSONObject getSysInfoJSON();
	
}
