package com.kxjl.web.generator.service.impl;

import net.sf.json.JSONObject;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.kxjl.tool.common.Constant;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.generator.FreeMarkUtil;
import com.kxjl.tool.utils.generator.TemplateFileUtil;
import com.kxjl.web.generator.MGeneratorUtil;
import com.kxjl.web.generator.pojo.CtrollerMapperBean;
import com.kxjl.web.generator.pojo.Message;
import com.kxjl.web.generator.service.KGenerateService;
import com.kxjl.web.privilege.model.Permission;
import com.kxjl.web.privilege.model.Role;
import com.kxjl.web.privilege.service.PermissionService;
import com.kxjl.web.privilege.service.PrivilegeService;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.model.SysUserBean;
import com.kxjl.web.system.service.MenuInfoService;
import com.kxjl.web.system.service.SysUserService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

@Service
public class KGenerateServiceImpl implements KGenerateService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	// 模板文件目录
	@Value("${kauto.templatePath}")
	private String templatePath;

	 public KGenerateServiceImpl() {
		 templatePath = ConfigReader.getInstance().getProperty("templatePath");
	}
	
	@Autowired
	FreeMarkUtil freeMarkUtil;
	@Autowired
	MGeneratorUtil mGeneratorUtil;

	@Autowired
	PermissionService permissionService;
	
	
	@Autowired
	SysUserService managerService;

	@Autowired
	PrivilegeService roleService;

	/**
	 * 生成默认菜单并分配给当前登陆用户
	 * 
	 * @param item
	 * @return
	 * @author zj
	 * @date 2019年1月9日
	 */
	@Transactional
	public Message generateMenuAndAsgin(HttpServletRequest request, CtrollerMapperBean data) {
		Message msg = new Message();
		msg.setBol(false);
		msg.setMessage("fail");
		try {
		
			// 创建菜单

			// 默认自动生成一级菜单菜单
			Permission recordp = permissionService.selectByPrimaryKey("menu_auto");
			if (recordp == null) {
				recordp = new Permission();
				recordp.setMenuId("menu_auto");
				recordp.setMenuName("自动生成菜单");
				recordp.setType("1");
				recordp.setMenuUrl("");
				//recordp.setPercode("menu_auto:view");
				recordp.setMenuParentid("0");
				recordp.setMenuOrderid("100");
				recordp.setAvailable("1");
				recordp.setMenuIco("fa fa-cogs");
				permissionService.insertSelective(recordp);

			}
			// 当前菜单
			boolean isExist=false;
			String newMenuId = "menu_" + data.getCtrollerModelMapping();
			Permission record = permissionService.selectByPrimaryKey(newMenuId);
			if(record==null)
				record=new Permission();
			else
				isExist=true;
			
			record.setMenuId(newMenuId);
			record.setMenuName(data.getPagetitle() + "管理");
			record.setType("2");
			record.setMenuUrl("manager/" + data.getCtrollerModelMapping() + "/manager");
			//record.setPercode(data.getCtrollerModelMapping() + ":view");
			record.setMenuParentid("menu_auto");
			record.setMenuOrderid("1");
			record.setAvailable("1");
			record.setMenuIco("fa fa-cog");
			
			if(isExist)
			permissionService.updateByPrimaryKeySelective(record);
			else
				permissionService.insertSelective(record);

			//Map principal = (Map) request.getAttribute("principal");
			SysUserBean m = (SysUserBean) request.getSession().getAttribute(Constant.SESSION_USER);
			

			// permissionService.get
			List<Role> roles = roleService.getManagerRoleList(m);

			List<Permission> permissions = permissionService.getRolePermissionList(roles.get(0));
			String permissionids = "";
			for (int i = 0; i < permissions.size(); i++) {
				permissionids += permissions.get(i).getMenuId() + ",";
			}
			if(!permissionids.contains(newMenuId))
			permissionids += newMenuId+",";
			if(!permissionids.contains("menu_auto"))
				permissionids += "menu_auto,";
			
			// 默认给当前登陆账号的第一个角色增加此菜单
			roleService.updateRoleMenuList(roles.get(0).getRole_en(), permissionids);

			msg.setBol(true);
			msg.setMessage("ok");
		} catch (Exception e) {
			e.printStackTrace();

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("更新权限菜单出错", e);

			msg.setBol(false);
			msg.setMessage(e.getMessage());
		}
		return msg;
	}

	@Override
	public Message generateController(CtrollerMapperBean data) {

		Message msg = new Message();
		msg.setBol(false);
		msg.setMessage("fail");
		try {
			String text = freeMarkUtil.getControllerContent(data);

			TemplateFileUtil.SaveControllerFile(text, data);

			msg.setBol(true);
			msg.setMessage("ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setBol(false);
			msg.setMessage(e.getMessage());
		}
		return msg;
	}

	@Override
	public Message generatePageAndJs(CtrollerMapperBean data) {

		Message msg = new Message();
		msg.setBol(false);
		msg.setMessage("fail");
		try {

			String textIndex = freeMarkUtil.getPageIndexContent(data);
			TemplateFileUtil.SaveIndexFile(textIndex, data, "jsp");

			String textForm = freeMarkUtil.getPageFormContent(data);
			TemplateFileUtil.SavePageFile(textForm, data, "jsp");

			String textJS = freeMarkUtil.getPageJSContent(data);
			TemplateFileUtil.SaveJSFile(textJS, data);

			msg.setBol(true);
			msg.setMessage("ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setBol(false);
			msg.setMessage(e.getMessage());
		}
		return msg;
	}

	@Override
	public Message generateService(CtrollerMapperBean data) {

		Message msg = new Message();
		msg.setBol(false);
		msg.setMessage("fail");
		try {

			String text = freeMarkUtil.getServiceContent(data);
			TemplateFileUtil.SaveServiceFile(text, data);

			String textImpl = freeMarkUtil.getServiceImplContent(data);
			TemplateFileUtil.SaveServiceImplFile(textImpl, data);

			msg.setBol(true);
			msg.setMessage("ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setBol(false);
			msg.setMessage(e.getMessage());
		}
		return msg;

	}

	@Override
	public Message UpdatePojoDao(CtrollerMapperBean data) {

		Message msg = new Message();
		msg.setBol(false);
		msg.setMessage("fail");
		try {

			String daoplus = freeMarkUtil.getDaoPlusContent(data);
			TemplateFileUtil.AddToDaoFile(daoplus, data);

			String mapperPlus = freeMarkUtil.getMapperPlusContent(data);
			TemplateFileUtil.AddToMapperFile(mapperPlus, data);

			msg.setBol(true);
			msg.setMessage("ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setBol(false);
			msg.setMessage(e.getMessage());
		}
		return msg;
	}

	@Override
	public Message generatePojoDao(CtrollerMapperBean data) {

		String cfgfileName = "mybatisgenerator-temp.xml";

		Message msg = new Message();
		msg.setBol(false);
		msg.setMessage("fail");
		try {
			mGeneratorUtil.generatoMybatisPojoMapper(cfgfileName, data);

			// 修改
			msg = UpdatePojoDao(data);

		} catch (Exception e) {
			e.printStackTrace();
			msg.setBol(false);
			msg.setMessage(e.getMessage());
		}

		return msg;
	}

}
