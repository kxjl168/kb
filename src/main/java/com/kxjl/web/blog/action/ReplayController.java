package com.kxjl.web.blog.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.drew.lang.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.config.configWarthDog;
import com.kxjl.tool.sendmail.MailUtil;
import com.kxjl.tool.utils.CookieUtil;
import com.kxjl.tool.utils.IPUtils;
import com.kxjl.tool.utils.JEscape;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.RequestInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.SysUserBean.UserType;
import com.kxjl.web.system.service.RequestInfoService;
import com.kxjl.web.blog.model.Blog;
import com.kxjl.web.blog.model.Replay;
import com.kxjl.web.blog.service.BlogService;
import com.kxjl.web.blog.service.ReplayService;
import com.kxjl.web.stastic.service.StasticService;

@Controller
@RequestMapping(value = "/replay")
public class ReplayController extends BaseController {

	private static Logger logger = Logger.getLogger(ReplayController.class);
	@Autowired
	ReplayService replayService;

	@Autowired
	BlogService blogService;
	
	@Autowired
	StasticService stasticService;

	@Autowired
	CookieUtil cookieUtil;

	@RequestMapping(value = "/")
	public ModelAndView GroupList() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/replay/replay");

		return view;
	}

	/**
	 * 页面-获取replay列表
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
	public void getInfoList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// String replayid = request.getParameter("replayid");

		/*
		 * int pageCount = Integer.parseInt(request.getParameter("rows"));//
		 * request.getParameter("pageCount"); int curPage =
		 * Integer.parseInt(request.getParameter("page"));
		 */

		String data = request.getParameter("data");
		JSONObject jsonIN;
		JSONObject jsonOut = new JSONObject();

		String rst = "";
		try {

			jsonIN = new JSONObject(data);

			String imei = jsonIN.optString("imei");

			int pageCount = jsonIN.optInt("rows");// request.getParameter("pageCount");
			int curPage = jsonIN.optInt("page");

			Replay query = new Replay();
			query.setPage(curPage);
			query.setPageCount(pageCount);

			// query.setIp(ip);
			// query.setCity(city);
			query.setBlogimei(imei);// (replay_title);// (id);
			// query.setReplay_type(replay_type);// (replay_name);

			SysUserBean user = (SysUserBean) session.getAttribute(Constant.SESSION_USER);

			boolean isRoot = false;
			if (user != null && (user.getUtype() == UserType.Root || user.getUtype() == UserType.Admin))
				isRoot = true;

			query.setState(""); // 查看全部
			if (!isRoot)
				query.setState("1"); // 只能看审核通过的

			String key = "replay_getInfoList" + "_" + imei + "_" + query.getState();
			List<Replay> binfos = Kdata.getInstance().getReplayList(key);
			List<Replay> infos = new ArrayList<Replay>();
			if (binfos == null || binfos.size() == 0) {

				infos = replayService.getReplayPageList(query);
				binfos = new ArrayList<Replay>();

				for (Replay replay : infos) {
					if(replay.getEmail()!=null&&!replay.getEmail().equals("")&&!replay.getEmail().equals("undefined"))
					{
						if(replay.getIcon()==null||replay.getIcon().equals(""))
						{
							replay.setIcon(com.kxjl.tool.utils.StringUtil.md5Hex(replay.getEmail()));
						}
						
					}
				}
				
				for (Replay replay : infos) {
					
					
					
					
					if (replay.getReplay_recordid() == 0) {
						List<Replay> childs = new ArrayList<Replay>();
						for (Replay replay2 : infos) {
							if (replay2.getPpid() != 0 && replay2.getPpid() == replay.getRecordid()) {
								childs.add(replay2);
							}
						}
						replay.setReback(childs);
						binfos.add(replay);
					}

				}

				Kdata.getInstance().SavedReplayList(key, binfos);
			}

			int total = 0;// replayService.getReplayPageListCount(query);

			/*
			 * String prepath = getImgHttpOutPath(); for (Replay replay : infos) {
			 * replay.setReplay_type_url(prepath + replay.getReplay_type_url()); }
			 */

			Gson gs = new Gson();
			String jsStr = gs.toJson(binfos);

			jsonOut.put("ResponseCode", "200");
			jsonOut.put("ResponseMsg", "");
			jsonOut.put("total", total);
			jsonOut.put("datalist", jsStr);
			jsonOut.put("isRoot", isRoot);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "");
				jsonOut.put("total", 0);
				jsonOut.put("datalist", "");
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		rst = jsonOut.toString();
		JsonUtil.responseOutWithJson(response, rst);

	}

	/**
	 * 添加或者更新replay信息
	 * 
	 * @param request
	 * @param response
	 * @date 2016-6-22
	 * @author zj
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject jsonOut = new JSONObject();
		String data = request.getParameter("data");
		JSONObject jsonIN;

		String actiontype = "replay_addOrUpdate";
		try {

			SysUserBean user = (SysUserBean) session.getAttribute(Constant.SESSION_USER);

			boolean isRoot = false;
			if (user != null && (user.getUtype() == UserType.Root || user.getUtype() == UserType.Admin))
				isRoot = true;

			if (!isRoot) {

				// 计算ip
				String ip = "";
				try {
					ip = request.getRemoteAddr();
				} catch (Exception e) {

				}

				// 提交次数/单位时间限制
				RequestInfo rquery = new RequestInfo();
				rquery.setAction_type(actiontype);
				rquery.setIp(ip);

				// 可以再次评论的时间间隔
				int sec = ConfigReader.getInstance().getIntProperty("replay_cando_sec", 10);
				rquery.setSec(sec);

				int rqstnum = requestInfoService.getRequestCountByCondition(rquery);
				// 单位时间内允许评论的次数
				int maxnum = ConfigReader.getInstance().getIntProperty("replay_maxnum_p_sec", 1);
				if (rqstnum >= maxnum) {
					jsonOut.put("ResponseCode", 201);
					jsonOut.put("ResponseMsg", "您的操作过快,请稍等10秒后再次评论！");
					JsonUtil.responseOutWithJson(response, jsonOut.toString());
					return;
				}
			}

			jsonIN = new JSONObject(data);

			String imei = jsonIN.optString("imei");
			int pid = jsonIN.optInt("pid");
			int ppid = jsonIN.optInt("ppid");
			String userid = jsonIN.optString("userid");
			String ublog = jsonIN.optString("ublog");

			String content = jsonIN.optString("context");
			String email = jsonIN.optString("email");

			// 评论文章
			Blog bq = new Blog();
			bq.setImei(imei);
			Blog blog = blogService.getBlogInfoById(bq);
			if (!imei.equals("about")) {
				if (blog == null) {
					jsonOut.put("ResponseCode", 201);
					jsonOut.put("ResponseMsg", "文章不存在！");
					JsonUtil.responseOutWithJson(response, jsonOut.toString());
					return;
				}
			}

			Replay replay = new Replay();
			replay.setBlogimei(imei);// (recordid);// (accountid);
			replay.setReplay_recordid(pid);

			if (!ublog.startsWith("http"))
				ublog = "http://" + ublog;

			replay.setUser_blog(ublog);
			replay.setUserid(userid);
			replay.setPpid(ppid);
			replay.setIp(stasticService.getIpAddr(request));
			if(email!=null&&!email.equals("")&&!email.equals("undefined")) {
			replay.setEmail(email);
			replay.setIcon(com.kxjl.tool.utils.StringUtil.md5Hex(email));
			}

			replay.setContent(content);// (desc);

			if (isRoot) {
				replay.setUser_blog("http://www.256kb.cn");
				replay.setUserid("野生的喵喵");
				replay.setState("1");
			} else {
				replay.setState("0");
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());

			int rst = -1;

			replay.setCreate_date(time);
			
			
			
			
			//有重复提交数据,不处理
			//已发布评论
			 if(String.valueOf(Kdata.getInstance().getCommonList(imei+"_"+userid+"_"+content)).equals("true"))
			 {
				System.out.println(content+"评论重复~");
				jsonOut.put("ResponseCode", 200);
				jsonOut.put("ResponseMsg", "重复数据！");
				JsonUtil.responseOutWithJson(response, jsonOut.toString());
				
				
				Kdata.getInstance().cleanrCommonList(imei+"_"+userid);
				
				return ;
			}
				
			
			

			rst = replayService.addReplay(replay);

			if (rst > 0) {
				
				//已发布评论
				 Kdata.getInstance().SavedCommonList(imei+"_"+userid+"_"+content, "true");
				
				

				Kdata.getInstance().cleanrBLogList("");

				// String key = "replay_getInfoList" + "_" + imei + "_";
				Kdata.getInstance().cleanrReplayList("");

				jsonOut.put("ResponseCode", 200);
				jsonOut.put("ResponseMsg", "OK");

				saveRequestInfo(request, actiontype, "");

				if (isRoot) {
					// 更新文章评论数
					if (!imei.equals("about")) {
						Replay rqq = new Replay();
						rqq.setBlogimei(imei);
						int replay_nums = replayService.getReplayPageListCount(rqq);
						//blog.setReplay_nums(replay_nums + 1);
						blogService.updateBlog(blog);
					}
				}

				String enableMail = ConfigReader.getInstance().getProperty("EnableMail", "false");
				if (enableMail.equalsIgnoreCase("true")) {

					if (!isRoot) {
						// 访客留言
						String rtitle = "关于";
						if (!imei.equals("about"))
							rtitle = blog.getTitle();
						final String title = "KxのBook上的[" + rtitle + "]有了新的评论";
						String uid = xssEncode(JEscape.unescape(userid));
						String ucontent = xssEncode(JEscape.unescape(content));
						// final String message = "From " + uid + ":\r\n<br>" + ucontent + "";
						final String recvmail = ConfigReader.getInstance().getProperty("AdminMail");

						String url = "";
						if (!imei.equals("about"))
							url = ConfigReader.getInstance().getProperty("domain", "http://www.256kb.cn")
									+ "/public/html/" + blog.getShowdate() + "/" + blog.getImei() + ".html";
						else
							url = ConfigReader.getInstance().getProperty("domain", "http://www.256kb.cn")
									+ "/public/about/";

						final String message = " 《 " + rtitle + "》收到了来至" + uid + "(email:" + replay.getEmail()
								+ "/blog:" + replay.getUser_blog() + ")的新回复.\r\n<br>" + ucontent + "<a href=\"" + url
								+ "\">点击这里查看</a>" + "+\r\n<br><div>若上述链接无法打开,请将该地址复制到浏览器地址栏中访问<a href=\"" + url + "\">"
								+ url + "</a></div>";

						final String nickname="喵主子";
						final String action=uid+" 回复了你 在256kb.cn 上的文章,在";
						final String pageurl=url;
						final String pagetitle=rtitle;
						final String replayurl=url+"#f"+replay.getRecordid();
						
						new Thread(new Runnable() {

							@Override
							public void run() {
								logger.debug("mail send to " + recvmail + "/" + message);
								//MailUtil.sendMail(recvmail, title, message);
								
								MailUtil.sendMail2(recvmail,title,"",nickname, action, pageurl, pagetitle, ucontent,replayurl);
								
							}
						}).start();
					} else {
						// 答复
						if (replay.getReplay_recordid() != 0) {
							Replay rqq = new Replay();
							rqq.setRecordid(replay.getReplay_recordid());
							Replay toReplay = replayService.getReplay(rqq);
							String uid = xssEncode(JEscape.unescape(toReplay.getUserid()));
							String ucontent = xssEncode(JEscape.unescape(replay.getContent()));
							if (toReplay.getEmail() != null && !toReplay.getEmail().equals("")) {
								// 访客留言
								String rtitle = "关于";
								if (!imei.equals("about"))
									rtitle = blog.getTitle();
								final String title = "你在KxのBook的评论有了新的回复";

								String url = "";
								if (!imei.equals("about"))
									url = ConfigReader.getInstance().getProperty("domain", "http://www.256kb.cn")
											+ "/public/html/" + blog.getShowdate() + "/" + blog.getImei()
											+ ".html";
								else
									url = ConfigReader.getInstance().getProperty("domain", "http://www.256kb.cn")
											+ "/public/about/";

								final String message = uid + "您好! 您在《 " + rtitle
										+ "》中的评论收到了来至 KxのBook[野生的喵喵]的新回复.<a href=\"" + url + "\">点击这里查看</a>"
										+ "+\r\n<br><div>若上述链接无法打开,请将该地址复制到浏览器地址栏中访问<a href=\"" + url + "\">" + url
										+ "</a></div>";
								final String recvmail = toReplay.getEmail();

								final String nickname=uid;
								final String action="野生的喵喵 回复了你 在256kb.cn 上的文章,在:";
								final String pageurl=url;
								final String pagetitle=rtitle;
								final String replayurl=url+"#f"+replay.getRecordid();
								
								new Thread(new Runnable() {

									@Override
									public void run() {
										logger.debug("mail send to " + recvmail + "/" + message);
									//	MailUtil.sendMail(recvmail, title, message);
										
										MailUtil.sendMail2(recvmail,title,"",nickname, action, pageurl, pagetitle, ucontent,replayurl);
									}
								}).start();
							}

						}

					}

				}

				if (!isRoot) {

					cookieUtil.editCookie(request, response, "uemail", replay.getEmail(), 3600 * 24 * 15);// 15Day
					cookieUtil.editCookie(request, response, "uname", replay.getUserid(), 3600 * 24 * 15);// 15Day
					cookieUtil.editCookie(request, response, "usite", replay.getUser_blog(), 3600 * 24 * 15);// 15Day

				}

			} else {
				jsonOut.put("ResponseCode", 201);
				jsonOut.put("ResponseMsg", "FAILED");
			}

		} catch (Exception e2) {
			System.out.println(e2.getMessage());
			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "FAILED");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}

	/**
	 * 更新评论审核状态
	 * 
	 * @param request
	 * @param response
	 * @date 2016-6-22
	 * @author zj
	 */
	@RequestMapping(value = "/updateState")
	public void updateState(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject jsonOut = new JSONObject();
		String data = request.getParameter("data");
		JSONObject jsonIN;

		String actiontype = "replay_addOrUpdate";
		try {

			jsonIN = new JSONObject(data);

			int recordid = jsonIN.optInt("id");

			Replay query = new Replay();
			query.setRecordid(recordid);
			Replay rp = replayService.getReplay(query);

			String state = jsonIN.optString("state");

			Replay r = new Replay();
			r.setRecordid(recordid);
			r.setState(state);

			if (replayService.updateReplay(r) > 0) {

			/*	SysUserBean user = (SysUserBean) session.getAttribute(Constant.SESSION_USER);

				boolean isRoot = false;
				if (user != null && (user.getUtype() == UserType.Root || user.getUtype() == UserType.Admin))
					isRoot = true;

				if (!isRoot)*/
				updateBlogCount(rp, true);

				jsonOut.put("ResponseCode", "200");
				jsonOut.put("ResponseMsg", "操作成功");
			} else {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "操作失败");
			}

		} catch (Exception e2) {
			System.out.println(e2.getMessage());
			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "FAILED");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}

	private String xssEncode(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			/*
			 * case '\'': sb.append('‘');// 全角单引号 break; case '\"': sb.append('“');// 全角双引号
			 * break; case '&': sb.append('＆');// 全角 break; case '\\': sb.append('＼');//
			 * 全角斜线 break; case '#': sb.append('＃');// 全角井号 break;
			 */
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	private void updateBlogCount(Replay rp, boolean isadd) {
		if (!rp.getBlogimei().equals("about")) {
			Replay rqq = new Replay();
			rqq.setBlogimei(rp.getBlogimei());
			int replay_nums = replayService.getReplayPageListCount(rqq);

			Blog bq = new Blog();
			bq.setImei(rp.getBlogimei());
			Blog blog = blogService.getBlogInfoById(bq);

			blog.setReplay_nums(replay_nums);

			blogService.updateBlog(blog);
		}

		Kdata.getInstance().cleanrBLogList("");

		String key = "replay_getInfoList" + "_" + rp.getBlogimei() + "_";
		Kdata.getInstance().cleanrReplayList(key);

		key = "replay_getInfoList" + "_" + rp.getBlogimei() + "_1";
		Kdata.getInstance().cleanrReplayList(key);
	}

	/**
	 * 删除replay
	 * 
	 * @param request
	 * @return
	 * @date 2016-8-22
	 */
	@RequestMapping(value = "/del")
	public @ResponseBody Map delBanner(HttpServletRequest request) {
		Map res = new HashMap();
		res.put("ResponseCode", "201");
		res.put("ResponseMsg", "删除失败");
		String data = request.getParameter("data");
		JSONObject jsonIN;

		try {

			jsonIN = new JSONObject(data);

			int recordid = jsonIN.optInt("id");

			Replay query = new Replay();
			query.setRecordid(recordid);
			Replay rp = replayService.getReplay(query);

			if (replayService.deleteReplay(recordid) > 0) {

				updateBlogCount(rp, false);

				res.put("ResponseCode", "200");
				res.put("ResponseMsg", "删除成功");
			} else {
				res.put("ResponseCode", "201");
				res.put("ResponseMsg", "删除失败");
			}
		} catch (Exception e) {

		}

		return res;
	}

}
