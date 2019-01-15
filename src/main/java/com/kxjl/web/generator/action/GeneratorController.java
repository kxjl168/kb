package com.kxjl.web.generator.action;

import com.github.pagehelper.Page;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.PageCondition;
import com.kxjl.tool.utils.PageUtil;
import com.kxjl.tool.utils.generator.GeneratorUtils;
import com.kxjl.tool.utils.generator.TableNameUtil;
import com.kxjl.web.blog.model.Kurl;
import com.kxjl.web.blog.service.KurlService;
import com.kxjl.web.generator.pojo.AField;
import com.kxjl.web.generator.pojo.CtrollerMapperBean;
import com.kxjl.web.generator.pojo.Generator;
import com.kxjl.web.generator.pojo.Message;
import com.kxjl.web.generator.service.GeneratorService;
import com.kxjl.web.generator.service.KGenerateService;
import com.kxjl.web.system.model.MenuInfo;
import com.kxjl.web.system.service.MenuInfoService;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/generator")
public class GeneratorController {
	@Autowired
	private GeneratorService generatorService;

	@Autowired
	private KGenerateService kgenerateService;

	// 模板文件目录
	//@Value("${kauto.databaseSchema}")
	private String databaseSchema;

	//@Value("${kauto.mysqljarPath}")
	private String mysqljarPath;

	//@Value("${kauto.javaScanPath}")
	private String javaScanPath;

	//@Value("${kauto.pageScanPath}")
	private String pageScanPath;

	//@Value("${kauto.basePacakge}")
	private String basePacakge;

	@Autowired
	KurlService kurlService;
	@Autowired
	MenuInfoService menuService;

	
	public GeneratorController() {
		refreshConfig();
	}
	
	private void refreshConfig() {
		databaseSchema = ConfigReader.getInstance().getProperty("databaseSchema");

		mysqljarPath = ConfigReader.getInstance().getProperty("mysqljarPath");

		javaScanPath = ConfigReader.getInstance().getProperty("javaScanPath");

		pageScanPath = ConfigReader.getInstance().getProperty("pageScanPath");

		basePacakge = ConfigReader.getInstance().getProperty("basePacakge");
	}

	
	private void setLeftUrls(Map<String, Object> maps, boolean showAll) {

		Kurl query = new Kurl();
		query.setPage(1);
		query.setPageCount(10000);
		query.setVal1("1");
		if (showAll)
			query.setIsshow("");
		else
			query.setIsshow("1");

		query.setUrl_name("");// (url_name);
		Map<String, List<Kurl>> datas = kurlService.getKurlItemPageList(query);

		List<MenuInfo> urls = new ArrayList<>();
		for (List<Kurl> item : datas.values()) {
			MenuInfo urlmenu = new MenuInfo();
			urlmenu.setMenuName(item.get(0).getUrl_type());

			urls.add(urlmenu);

		}

		maps.put("menus", urls);

	}
	
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model,Map<String, Object> maps) {

		request.setAttribute("basePacakge", basePacakge);
		// model.ad basePacakge

		List<MenuInfo> leftmenus = menuService.getLeftMenuTree(request.getSession(), request);

		maps.put("menus", leftmenus);
		
		return "/page/generator/index";
	}

	/**
	 * 查询表字段
	 * 
	 * @param pageCondition
	 * @param g
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @author zj
	 * @date 2019年1月8日
	 */
	@RequestMapping("/listCols")
	@ResponseBody
	public String listCols(PageCondition pageCondition, Generator g) throws IOException, ClassNotFoundException {

		String rst = "";
		List<AField> fields;
		pageCondition.setPageSize("20");
		Page page = PageUtil.getPage(pageCondition);

		g.setTableSchema(databaseSchema);

		fields = generatorService.selectTableColList(g);
		CtrollerMapperBean data = new CtrollerMapperBean();
		data.setBasepackageName("com.ztgm.base");

		try {
			rst = PageUtil.packageTableData(page, fields);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rst;
	}

	/**
	 * 全部生成对应数据库表的 pojo,mapper,service,controller,page,js并添加对应系统菜单及权限
	 * 
	 * @param request
	 * @param data
	 * @return
	 * @author zj
	 * @date 2019年1月10日
	 */
	@RequestMapping("/createAll")
	@ResponseBody
	public Message createAll(HttpServletRequest request, CtrollerMapperBean data) {
		Message msg = new Message();
		msg.setBol(false);
		msg.setMessage("fail");
		try {
			
			refreshConfig();

			data.setJarPath(mysqljarPath);

			// 呈现字段
			List<AField> filedCols = new ArrayList<>();
			String cols = request.getParameter("cols");
			JSONArray jimgs = new JSONArray(cols);
			for (int i = 0; i < jimgs.length(); i++) {
				AField fd = new AField();
				fd.setDisplayName(jimgs.optJSONObject(i).optString("displayName"));
				fd.setSqlcolname(jimgs.optJSONObject(i).optString("sqlcolname"));
				fd.setColType(jimgs.optJSONObject(i).optString("colType"));
				filedCols.add(fd);
			}
			data.setColFields(filedCols);

			// 条件查询字段
			List<AField> qfiledCols = new ArrayList<>();
			String qcols = request.getParameter("qcols");
			JSONArray qjimgs = new JSONArray(qcols);
			for (int i = 0; i < qjimgs.length(); i++) {
				AField fd = new AField();
				fd.setDisplayName(qjimgs.optJSONObject(i).optString("displayName"));
				fd.setSqlcolname(qjimgs.optJSONObject(i).optString("sqlcolname"));
				qfiledCols.add(fd);
			}
			data.setQueryFields(qfiledCols);

			data.setTableName(data.getTableName());

			msg = kgenerateService.generatePojoDao(data);
			if (!msg.isBol())
				return msg;
			msg = kgenerateService.generateService(data);
			if (!msg.isBol())
				return msg;
			msg = kgenerateService.generateController(data);
			if (!msg.isBol())
				return msg;
			msg = kgenerateService.generatePageAndJs(data);
			if (!msg.isBol())
				return msg;
			msg = kgenerateService.generateMenuAndAsgin(request, data);

		} catch (Exception e) {
			e.printStackTrace();
			msg.setBol(false);
			msg.setMessage(e.getMessage());
		}
		return msg;
		/*
		 * CtrollerMapperBean data = new CtrollerMapperBean();
		 * data.setBasepackageName("com.ztgm.demo"); data.setModelClass("Ktest");
		 * data.setLogName("测试分类"); data.setCtrollerModelMapping("ktest");
		 */

	}

	/**
	 * 查询表字段
	 * 
	 * @param pageCondition
	 * @param g
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String GeneratorList(PageCondition pageCondition, Generator g) throws IOException, ClassNotFoundException {

		String rst = "";
		List<Generator> generators;
		Page page = PageUtil.getPage(pageCondition);

		g.setTableSchema(databaseSchema);

		generators = generatorService.selectGeneratorList(g);
		CtrollerMapperBean data = new CtrollerMapperBean();
		// List
		// list=GeneratorUtils.getClasses("D:\\eclipse-workspace\\shop\\svn\\ZTGM-FreightManagementSystem\\src\\server\\ZBase\\src\\main\\");

		List<String> mapperList = GeneratorUtils.getFileNames(javaScanPath, "Mapper");
		List<String> serviceList = GeneratorUtils.getFileNames(javaScanPath, "Service");
		List<String> controllerList = GeneratorUtils.getFileNames(javaScanPath, "Controller");
		List<String> jsList = GeneratorUtils.getFileNames(pageScanPath, "");

		for (Generator generator : generators) {

			String pojoName = TableNameUtil.getTFName(generator.getTableName());
			String mapperName = pojoName + "Mapper";
			String serviceName = pojoName + "Service";
			String sontrollerName = pojoName + "Controller";
			String jsName = TableNameUtil.getCtrollerMappingName(generator.getTableName());

			// 匹配pojo
			if (GeneratorUtils.match(mapperName, mapperList)) {
				generator.setCreatePojo(true);
			} else {
				generator.setCreatePojo(false);
			}
			// 匹配controller
			if (GeneratorUtils.match(serviceName, serviceList)) {
				generator.setCreateController(true);
			} else {
				generator.setCreateController(false);
			}
			// 匹配service
			if (GeneratorUtils.match(sontrollerName, controllerList)) {
				generator.setCreateService(true);
			} else {
				generator.setCreateService(false);
			}
			// 匹配pageAndJs
			if (GeneratorUtils.match(jsName, jsList)) {
				generator.setCreatePageAndJs(true);
			} else {
				generator.setCreatePageAndJs(false);
			}

		}
		try {
			rst = PageUtil.packageTableData(page, generators);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rst;
	}

	/**
	 * 生成所有
	 *
	 * @param choose
	 * @param tableName
	 */
	@RequestMapping("/create")
	@ResponseBody
	public Message GeneratorCreate(HttpServletRequest request, CtrollerMapperBean data) throws Exception {

		Message msg = new Message();
		msg.setBol(false);
		msg.setMessage("fail");
		try {

			data.setJarPath(mysqljarPath);

			int choose = Integer.parseInt(request.getParameter("choose"));

			// 页面生成，需要对应字段配置
			if (choose == 4) {
				// 呈现字段
				List<AField> filedCols = new ArrayList<>();
				String cols = request.getParameter("cols");
				JSONArray jimgs = new JSONArray(cols);
				for (int i = 0; i < jimgs.length(); i++) {
					AField fd = new AField();
					fd.setDisplayName(jimgs.optJSONObject(i).optString("displayName"));
					fd.setSqlcolname(jimgs.optJSONObject(i).optString("sqlcolname"));
					fd.setColType(jimgs.optJSONObject(i).optString("colType"));
					filedCols.add(fd);
				}
				data.setColFields(filedCols);

				// 条件查询字段
				List<AField> qfiledCols = new ArrayList<>();
				String qcols = request.getParameter("qcols");
				JSONArray qjimgs = new JSONArray(qcols);
				for (int i = 0; i < qjimgs.length(); i++) {
					AField fd = new AField();
					fd.setDisplayName(qjimgs.optJSONObject(i).optString("displayName"));
					fd.setSqlcolname(qjimgs.optJSONObject(i).optString("sqlcolname"));
					qfiledCols.add(fd);
				}
				data.setQueryFields(qfiledCols);

			}

			// 基础配置，表名称，modelname,requestMapping,均通过数据库表名称来确定
			data.setTableName(data.getTableName());

			if (choose == 1) {
				msg = kgenerateService.generatePojoDao(data);

			} else if (choose == 2) {
				msg = kgenerateService.generateService(data);

			} else if (choose == 3) {

				msg = kgenerateService.generateController(data);
			} else if (choose == 4) {
				msg = kgenerateService.generatePageAndJs(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setBol(false);
			msg.setMessage(e.getMessage());
		}
		return msg;
	}

}