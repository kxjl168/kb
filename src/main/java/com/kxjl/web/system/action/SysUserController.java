package com.kxjl.web.system.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kxjl.tool.common.Constant;
import com.kxjl.tool.common.Md5Encrypt;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.AESEncrtptZteimweb;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.tool.utils.MsgSvrCall;
import com.kxjl.web.system.action.base.BaseController;
import com.kxjl.web.system.model.RequestInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.model.TokenBean;
import com.kxjl.web.system.model.SysUserBean.UserType;
import com.kxjl.web.system.service.SysUserService;
import com.kxjl.web.system.service.TokenService;
import com.kxjl.web.user.model.User;
import com.kxjl.web.user.service.UserService;





/**
 * 后台用户登录
 * 
 * @date 2016-7-29
 * @author zj
 * 
 */
@Controller(value = "/")
public class SysUserController extends BaseController {
	// 日志打印对象
	private Logger logger = Logger.getLogger(SysUserController.class);


	/**
	 * 登录
	 * 
	 * @return 登录页面跳转
	 */
	@RequestMapping(value = { "/loginin" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String loginGet() {
		return "/home/login";
	}

	@RequestMapping(value ="/main")
	public void main(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		SysUserBean user = (SysUserBean) session
				.getAttribute(Constant.SESSION_USER);
		request.setAttribute("loginUser", user.getName());
		
		
		
		//https 跳转，nginx https代理 http，防止页面跳回http zj 190711
		response.setStatus(302);//或者303,兼容http1.1
		response.setHeader("location", request.getContextPath()+"/home/index");
		
		
		//return "/home/index";
	}
	

	@Autowired
	private TokenService tokenService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private UserService userService;


	/**
	 * 后台登录请求,验证用户
	 * 
	 * @param request
	 *            获取请求
	 * @param response
	 *            响应请求
	 * @param userid
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	@RequestMapping(value = { "/exlogin" })
	public @ResponseBody
	Map<String, Object> exclogin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("username") String userid,
			@RequestParam("password") String password) {
		String msg = "";
		int sucess = 0;
		HashMap<String, Object> map = new HashMap();
		
		String actiontype="admin_login";
		
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
		int sec = ConfigReader.getInstance().getIntProperty(
				"login_cando_sec", 60);
		rquery.setSec(sec);

		int rqstnum = requestInfoService
				.getRequestCountByCondition(rquery);
		// 单位时间内允许评论的次数
		int maxnum = ConfigReader.getInstance().getIntProperty(
				"login_maxnum_p_sec", 10);
		if (rqstnum >= maxnum) {
			
			map.put("url", "/");
			
			map.put("sucess",0);
			
			map.put("msg", "非法操作!");
			saveRequestInfo(request, actiontype,"登陆次数过多");
			return map;
		}
		
		
		
		
		
		
		
		String md5password = Md5Encrypt.MD5(password);
		// 用户名不区分大小写
		userid = userid.toLowerCase();

		SysUserBean userInfo = new SysUserBean();
		userInfo.setUserid(userid);
		userInfo.setPassword(md5password);// (userid);

		SysUserBean usr = sysUserService.getUserInfoByUseridAndPwd(userInfo);

	
		if (usr == null) {

			//
			User cusr = userService.getUserInfoByAccountAndPass(
					userid, password);
			if (cusr == null) {
				msg = "用户名或密码错误";
				sucess = 0;
			} else {
				
				
				
				
				sucess = 1;
				usr = new SysUserBean();
				usr.setUserid(String.valueOf( cusr.getRecordid()));
				usr.setUtype(UserType.LoginUser);
				
//				if(cusr.getAccountid().equals("xsw"))
//					usr.setUtype(UserType.xiaohuoban);
				
				usr.setName(cusr.getUser_name());
				map.put("type", UserType.LoginUser.toString());
				session.setAttribute(Constant.SESSION_USER, usr);
				createToken(request, usr, "");
				
			}
		} else {
			msg = "";
			sucess = 1;

			map.put("userid", usr.getUserid());
			map.put("type", UserType.Admin.toString());

			usr.setUtype(UserType.Admin);
			
			createToken(request, usr, "");

			session.setAttribute(Constant.SESSION_USER, usr);
		}
		
		
		if(request.getContextPath().contains("/kb"))
			map.put("url", "/kb");
		else
		map.put("url", "/");
		
		map.put("sucess", Integer.valueOf(sucess));
		
		map.put("msg", msg);
	
		String info= sucess==1?"登录成功":"登录失败";
		
		saveRequestInfo(request, actiontype,info);
		
		return map;
	}
	
	/**
	 * 构造登录令牌
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 登录令牌
	 */
	private TokenBean constructTokenBean(HttpServletRequest request,
			SysUserBean bean) {
		TokenBean tokenBean = null;
		HttpSession session = request.getSession();
		String info = bean.getUserid() + "|" + "2";
		if (bean != null) {
			tokenBean = new TokenBean(session.getId(), bean.getUserid(),
					new Date(System.currentTimeMillis()), info);
		}
		return tokenBean;
	}

	/**
	 * 创建token， 如果之前第三方认证，有token返回，则使用返回的token, 否则使用sessionid生成token值返回给客户端
	 * 
	 * @param loginId
	 *            登录id
	 * @param terminalType
	 *            终端类型
	 * @param lang
	 *            客户端语言
	 * @param bean
	 *            用户信息
	 */

	private TokenBean createToken(HttpServletRequest request, SysUserBean bean,
			String token) {
		// logger.info("构造token并添加到内存和数据库中");
		// 构造token
		TokenBean tokenBean = constructTokenBean(request, bean);
		// logger.info("UserId:" + tokenBean.getUserId());
		if (token != null && !"".equals(token)) {// 如果之前的认证有token返回，则使用认证的token
			tokenBean.setToken(token);
		}
		logger.info("构造token成功:token:" + tokenBean.getToken());

		/**
		 * 写入token到数据库
		 */
		// logger.info("去数据库中查询是否有token记录");
		TokenBean hasBean = tokenService
				.hasTokenbyUserId(tokenBean.getUserId());
		/**
		 * 如果存在 则直接更新最新的Token记录
		 */
		if (hasBean != null) {
			logger.info("用户" + tokenBean.getUserId() + "在数据库中的token存在:"
					+ hasBean);
			
			tokenService.deleteTokenInfo( tokenBean.getToken());
			
			tokenService.updateTokenInfo(tokenBean);
			logger.info("用户" + tokenBean.getUserId() + "在数据库中的token值更新为:"
					+ tokenBean.getToken());
		} else {

			String tokens = tokenBean.getToken();
			
			tokenService.deleteTokenInfo(tokens);
		
			
			tokenService.insertTokenInfo(tokenBean);
		}
		/*logger.info(tokenBean.getUserId() + "-向客户端返回的token值为:"
				+ tokenBean.getToken());*/
		return tokenBean;

	}

	/**
	 * 后台用户 退出登录注销
	 * 
	 * @param session
	 *            session缓存对象
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public void logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		session.removeAttribute(Constant.SESSION_USER);
		
		
		//https 跳转，nginx https代理 http，防止页面跳回http zj 190711
				response.setStatus(302);//或者303,兼容http1.1
				response.setHeader("location", request.getContextPath()+"/");
				
		
		//return "redirect:/";
	}

	/**
	 * 管理员密码设置
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param userid
	 * @param opwd
	 * @param npwd
	 * @return
	 */
	@RequestMapping(value = "/adminPwdSet", method = RequestMethod.POST)
	public @ResponseBody
	String setAdminPwd(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String userid,
			String opwd, String npwd) {
		SysUserBean user = (SysUserBean) session
				.getAttribute(Constant.SESSION_USER);
		if (Md5Encrypt.MD5(opwd).equals(user.getPassword())) {
			user.setPassword(Md5Encrypt.MD5(npwd));
			
			
			SysUserBean data=new SysUserBean();
			data.setUserid(user.getUserid());
			data.setPassword(Md5Encrypt.MD5(npwd));
			
			int res = sysUserService.updateSysuer(data);
			if (res > 0) {
				logger.info(user.getName() + "密码修改成功！");

				session.removeAttribute(Constant.SESSION_USER);

				return "200";
			} else {
				logger.info("管理员密码修改失败！");
				return "201";
			}
		} else {
			logger.info("旧密码不正确！");
			return "202";
		}
	}

}
