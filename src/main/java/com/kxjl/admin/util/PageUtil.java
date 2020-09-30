package com.kxjl.admin.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.kxjl.admin.common.Pagination;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * 
 * @author kxjl
 * @date 2020年6月9日
 */
public class PageUtil {

	public static final int DEFAULT_PAGE_NO = 1;

	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final int DEFAULT_OFFSET = 0;

	public static Page getPage(Pagination pageCondition) {
		// String offset = pageCondition.getOffset();
		int pgSize = pageCondition.getPageSize();
		int pageNo = pageCondition.getPageNo();

		if (pageNo == 0)
			pageNo = DEFAULT_PAGE_NO;

		if (pgSize == 0)
			pageNo = DEFAULT_PAGE_SIZE;

		return PageHelper.startPage(pageNo, pgSize);

	}

	public static JSONObject packageJSONData(Page page, List dataList) throws JSONException {
		String resultString = "";
		Gson gs = new Gson();
		JSONObject data = new JSONObject();
		JSONArray rows = new JSONArray(gs.toJson(dataList));
		data.put("total", page.getTotal());
		data.put("rows", rows);

		return data;
	}

	public static <T> String packageTableData(Page page, List<T> dataList) throws JSONException {
		String resultString = "";
		Gson gs = new Gson();
		JSONObject data = new JSONObject();
		JSONArray rows = new JSONArray(gs.toJson(dataList));
		data.put("total", page.getTotal());
		data.put("rows", rows);
		resultString = data.toString();
		return resultString;
	}
}
