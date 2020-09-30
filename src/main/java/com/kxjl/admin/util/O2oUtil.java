package com.kxjl.admin.util;

import com.kxjl.tool.utils.UUIDUtil;

public class O2oUtil {

	/**
	 * 根据实体/class Id生成 KgEditObject2Object oriId,<br>
	 * 使不同用户编辑同一实体的关系审核数据 分开显示
	 * 
	 * @param oriEntityId
	 * @return
	 * @author:kxjl
	 * @date 2020年8月25日
	 */
	public static String generateOriId(String oriEntityId) {
		String rstId = UUIDUtil.getUUID() +":" +oriEntityId;
		
		return rstId;
	}

	/**
	 * 获取原始 entity/class id
	 * 
	 * @param date
	 * @return
	 */
	public static String getOriId(String generateOriId) {

		String realoriId = generateOriId;
		// 每一次编辑关系都独立显示，即使多人同时编辑同一关系
		if (generateOriId.contains(":")) {
			realoriId = generateOriId.split(":")[1];
		}

		return realoriId;

	}

}
