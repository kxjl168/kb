package com.kxjl.web.blog.action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.blog.model.Replay;
import com.kxjl.web.device.model.Device.RouteFlag;
import com.kxjl.web.system.model.MenuInfo;

/**
 * 简单缓存系统
 * 
 * @param map
 * @return
 * @author zj
 * @date 2018-1-6
 */
public class Kdata {
	/**
	 * 缓存类型
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2018-1-6
	 */
	public enum DataType {

		NIL("", ""), Blog("-1", "blog"), Replay("0", "Replay"), Menu("1",
				"Menu");

		private String value = "";
		private String desc = "";
		private Integer num = 0;

		private DataType(String val, String desc) {
			this.value = val;
			this.desc = desc;
			this.num=0;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			JSONObject j = new JSONObject();
			try {
				j.put("value", value);
				j.put("desc", desc);
				j.put("num", num);

			} catch (Exception e) {
				// TODO: handle exception
			}

			return j.toString();
		}

		public static DataType parse(String val) {
			for (DataType item : DataType.values()) {
				if (item.value.equals(val))
					return item;
			}
			return DataType.NIL;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
		}

	}

	// 日志缓存
	private static ConcurrentHashMap<String, List<Blog>> savedBlogList = new ConcurrentHashMap<String, List<Blog>>();

	private static ConcurrentHashMap<String, List<Replay>> savedReplayList = new ConcurrentHashMap<String, List<Replay>>();

	private static ConcurrentHashMap<String, List<MenuInfo>> savedMenuList = new ConcurrentHashMap<String, List<MenuInfo>>();

	public int GetNumOfType(DataType type) {
		int num = 0;
		if (type == DataType.Blog)
			num = savedBlogList.size();
		else if (type == DataType.Replay)
			num = savedReplayList.size();
		else if (type == DataType.Menu)
			num = savedMenuList.size();
		return num;
	}

	private Kdata() {

	}

	private static Kdata instance = null;

	public synchronized static Kdata getInstance() {
		if (instance == null)
			instance = new Kdata();

		return instance;

	}

	public List<Blog> getBlogList(String key) {
		return savedBlogList.get(key);
	}

	public void SavedBlogList(String key, List<Blog> list) {
		this.savedBlogList.put(key, list);// = savedBlogList;
	}

	public void cleanrBLogList(String key) {
		if (key != null && !key.equals("")) {
			if (savedBlogList.containsKey(key))
				savedBlogList.remove(key);
		} else
			savedBlogList.clear();
	}

	public List<Replay> getReplayList(String key) {
		return savedReplayList.get(key);
	}

	public void SavedReplayList(String key, List<Replay> list) {
		this.savedReplayList.put(key, list);// = savedReplayList;
	}

	public void cleanrReplayList(String key) {
		if (key != null && !key.equals("")) {
			if (savedReplayList.containsKey(key))
				savedReplayList.remove(key);
		} else
			savedReplayList.clear();
	}

	public List<MenuInfo> getMenuList(String key) {
		return savedMenuList.get(key);
	}

	public void SavedMenuList(String key, List<MenuInfo> list) {
		this.savedMenuList.put(key, list);// = savedMenuList;
	}

	public void cleanrMenuInfoList(String key) {
		if (key != null && !key.equals("")) {
			if (savedMenuList.containsKey(key))
				savedMenuList.remove(key);
		} else
			savedMenuList.clear();
	}

}
