package com.kxjl.admin.util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.service.KgPropertyService;
import com.kxjl.admin.util.Neo4jColInfo;
import com.kxjl.admin.util.Constants;

@Component
public class ExcelHelper {

	private static final String XLS = "xls";

	private static final String XLSX = "xlsx";

	private static final Logger logger = LoggerFactory.getLogger(ExcelHelper.class);

	private static Map<String, KgProperty> propsCaches = new HashMap();

	@Autowired
	private KgPropertyService kgPropertyService;

	/**
	 * 读取定义/类型/关系 xls
	 * 
	 * 
	 * @param excelFile
	 * @return
	 * 
	 *         <pre>
	 *         List<List> resultList = new ArrayList<>();
	 *         // 返回的工单对象集合
	 *         List<KgClass> calssList = new ArrayList<>();
	 *         List<KgRelation> relationList = new ArrayList<>();
	 *         // 操作日志集合
	 *         List<String> operateLogList = new ArrayList<>();
	 *         resultList.add(calssList);
	 *         resultList.add(relationList);
	 *         resultList.add(operateLogList);
	 * 
	 *         return resultList;
	 *         </pre>
	 * 
	 * @throws Exception
	 * @author:kxjl
	 * @date 2020年7月2日
	 */
	public List<List> transformFileToClsAndRelationData(LoginUser user,MultipartFile excelFile, String subGroupId) throws Exception {

		List<List> resultList = new ArrayList<>();
		// 返回的工单对象集合
		List<KgClass> calssList = new ArrayList<>();
		List<KgRelation> relationList = new ArrayList<>();
		// 操作日志集合
		List<String> operateLogList = new ArrayList<>();

		resultList.add(calssList);
		resultList.add(relationList);
		resultList.add(operateLogList);

		InputStream inputStream = excelFile.getInputStream();
		String fileName = excelFile.getOriginalFilename();

		// 根据文件后缀生成对象
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		Workbook workbook = null;
		if (suffix.equals(XLS)) {
			workbook = new HSSFWorkbook(inputStream);
		}
		if (suffix.equals(XLSX)) {
			workbook = new XSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheetAt(0);
		// 迭代sheet中的所有行
		for (Row row : sheet) {
			// 第一行为表头，不需要
			if (row.getRowNum() == 0) {

				String col1Name = "类名";

				String xlsCol1 = getCellValue(row.getCell(0));
				if (!xlsCol1.equals(col1Name)) {
					operateLogList.add("概念导入模板不匹配!");
					return resultList;
				}

				continue;
			}
			if (row != null) {

				KgClass kclass;

				try {

					kclass = packageClassInfo(user,row);
					if (kclass == null)
						continue;

					kclass.setDirId(subGroupId);
					calssList.add(kclass);

				} catch (Exception e) {
					logger.error("sheet1 " + row.getRowNum() + "行出现问题", e);
					operateLogList.add("sheet1 " + row.getRowNum() + "行出现问题：" + e.getMessage());
					continue;
				}

			}

		}

		Sheet sheet2 = workbook.getSheetAt(1);
		// 迭代sheet中的所有行
		for (Row row : sheet2) {
			// 第一行为表头，不需要
			if (row.getRowNum() == 0) {
				continue;
			}
			if (row != null) {

				KgRelation relation;

				try {

					relation = packageRelationInfo(user,row);

					if (relation == null)
						continue;

					relationList.add(relation);

				} catch (Exception e) {
					logger.error("sheet2 " + row.getRowNum() + "行出现问题", e);
					operateLogList.add("sheet2 " + row.getRowNum() + "行出现问题：" + e.getMessage());
					continue;
				}

			}

		}

		return resultList;

	}

	/**
	 * 读取实体-关系 xls
	 * 
	 * 
	 * @param excelFile
	 * @return
	 * 
	 *         <pre>
	 *         List<List> resultList = new ArrayList<>();
	 *         // 返回的工单对象集合
	 *         List<KgEntity> entityList = new ArrayList<>();
	 *         List<KgObjectToObject> relationList = new ArrayList<>();
	 *         // 操作日志集合
	 *         List<String> operateLogList = new ArrayList<>();
	 * 
	 *         resultList.add(entityList);
	 *         resultList.add(relationList);
	 *         resultList.add(operateLogList);
	 * 
	 *         return resultList;
	 *         </pre>
	 * 
	 * @throws Exception
	 * @author:kxjl
	 * @date 2020年7月2日
	 */
	public List<List> transformFileToEntityAndO2OData(LoginUser user,MultipartFile excelFile, String subGroupId) throws Exception {

		propsCaches=new HashMap<>();
		
		List<List> resultList = new ArrayList<>();
		// 返回的工单对象集合
		List<KgEntity> entityList = new ArrayList<>();
		List<KgObjectToObject> relationList = new ArrayList<>();
		// 操作日志集合
		List<String> operateLogList = new ArrayList<>();

		resultList.add(entityList);
		resultList.add(relationList);
		resultList.add(operateLogList);

		InputStream inputStream = excelFile.getInputStream();
		String fileName = excelFile.getOriginalFilename();

		// 根据文件后缀生成对象
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		Workbook workbook = null;
		if (suffix.equals(XLS)) {
			workbook = new HSSFWorkbook(inputStream);
		}
		if (suffix.equals(XLSX)) {
			workbook = new XSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheetAt(0);
		// 迭代sheet中的所有行
		for (Row row : sheet) {
			// 第一行为表头，不需要
			if (row.getRowNum() == 0) {

				String col1Name = "具体实体名（全名，唯一性）";

				String xlsCol1 = getCellValue(row.getCell(0));
				if (!xlsCol1.equals(col1Name)) {
					operateLogList.add("实体导入模板不匹配!");
					return resultList;
				}

				continue;
			}
			if (row != null) {

				KgEntity entity;

				try {

					String error = "";
					entity = packageEntityInfo(user,row, error);
					if (entity == null)
						continue;

					entity.setSubKgId(subGroupId);
					entityList.add(entity);

					if (!error.equals(""))
						operateLogList.add("sheet1 " + row.getRowNum() + "行出现问题：" + error);

				} catch (Exception e) {
					logger.error("sheet1 " + row.getRowNum() + "行出现问题", e);
					operateLogList.add("sheet1 " + row.getRowNum() + "行出现问题：" + e.getMessage());
					continue;
				}

			}

		}

		Sheet sheet2 = workbook.getSheetAt(1);
		// 迭代sheet中的所有行
		for (Row row : sheet2) {
			// 第一行为表头，不需要
			if (row.getRowNum() == 0) {
				continue;
			}
			if (row != null) {

				KgObjectToObject relation;

				try {

					String error = "";
					relation = packageObjectToObjectInfo(user,row, error);
					if (relation == null)
						continue;

					relationList.add(relation);

					if (!error.equals(""))
						operateLogList.add("sheet1 " + row.getRowNum() + "行出现问题：" + error);

				} catch (Exception e) {
					logger.error("sheet2 " + row.getRowNum() + "行出现问题", e);
					operateLogList.add("sheet2 " + row.getRowNum() + "行出现问题：" + e.getMessage());
					continue;
				}

			}

		}

		return resultList;

	}

	/**
	 * 读取neo4j导出的实体-关系 xls
	 * 
	 * 
	 * @param excelFile
	 * @return
	 * 
	 *         <pre>
	 *         List<List> resultList = new ArrayList<>();
	 *         // 返回的工单对象集合
	 *         List<KgEntity> entityList = new ArrayList<>();
	 *         List<KgObjectToObject> relationList = new ArrayList<>();
	 *         // 操作日志集合
	 *         List<String> operateLogList = new ArrayList<>();
	 * 
	 *         resultList.add(entityList);
	 *         resultList.add(relationList);
	 *         resultList.add(operateLogList);
	 * 
	 *         return resultList;
	 *         </pre>
	 * 
	 * @throws Exception
	 * @author:kxjl
	 * @date 2020年7月2日
	 */
	public List<List> transformNeo4jFileToEntityAndO2OData(LoginUser user,MultipartFile excelFile, String subGroupId)
			throws Exception {
		
		propsCaches=new HashMap<>();

		List<List> resultList = new ArrayList<>();
		// 返回的工单对象集合
		List<KgEntity> entityList = new ArrayList<>();
		List<KgObjectToObject> relationList = new ArrayList<>();
		// 操作日志集合
		List<String> operateLogList = new ArrayList<>();

		resultList.add(entityList);
		resultList.add(relationList);
		resultList.add(operateLogList);

		InputStream inputStream = excelFile.getInputStream();
		String fileName = excelFile.getOriginalFilename();

		// 根据文件后缀生成对象
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		Workbook workbook = null;
		if (suffix.equals(XLS)) {
			workbook = new HSSFWorkbook(inputStream);
		}
		if (suffix.equals(XLSX)) {
			workbook = new XSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheetAt(0);

		List<String> cols = new ArrayList<>();
		int colRelationStart = 0; // _start
		int colNodeId = 0;// 编码
		int col_id = 0;// _id
		int col_tags = 1;// _labels 标签 :xx:xxx
		int col_name = 0;// 名称

		Neo4jColInfo neo4jColinfo = new Neo4jColInfo();
		int rownum = 0;
		// 迭代sheet中的所有行
		for (Row row : sheet) {
			rownum++;
			// 第一行为表头，不需要
			if (row.getRowNum() == 0) {

				String col1Name = "_id";

				String xlsCol1 = getCellValue(row.getCell(0));
				if (!xlsCol1.equals(col1Name)) {
					operateLogList.add("模板错误!请使用Neo4j导出的csv文件导入!");
					return resultList;
				}
				String error = "";
				cols = packageNeo4jColsInfo(row, error);

				for (int i = 0; i < cols.size(); i++) {
					if (cols.get(i).equals("_start")) {
						colRelationStart = i;
						// break;
					}
					if (colNodeId < 5 && cols.get(i).equals("编码")) {
						colNodeId = i;
						// break;
					}
					if (col_name < 5 && cols.get(i).equals("名称")) {
						col_name = i;
						// break;
					}

				}

				neo4jColinfo.setColRelationStart(colRelationStart);
				neo4jColinfo.setColNodeId(colNodeId);
				neo4jColinfo.setCol_id(col_id);
				neo4jColinfo.setCol_tags(col_tags);
				neo4jColinfo.setCol_name(col_name);
				neo4jColinfo.setCol_last(cols.size());
				neo4jColinfo.setCols(cols);

				continue;
			}
			if (row != null) {

				String _id = getCellValue(row.getCell(0));
				String _startd = getCellValue(row.getCell(neo4jColinfo.getColRelationStart()));
				if (_id == null && _startd==null)
					break; // 结束

				try {

					String error = "";

					logger.debug("导入第" + rownum + "行...");

					String _start = getCellValue(row.getCell(colRelationStart));
					if (_start == null || _start.equals("")) {
						KgEntity entity;
						entity = packageNeo4jRowEntityInfo(user,row, neo4jColinfo, error);
						if (entity == null)
							continue;

						entity.setSubKgId(subGroupId);
						entityList.add(entity);
					} else {
						KgObjectToObject relation;
						relation = packageNeo4jRowO2OInfo(user,entityList, row, neo4jColinfo, error);
						if (relation == null)
							continue;

						relationList.add(relation);
					}

					if (!error.equals(""))
						operateLogList.add("sheet1 " + row.getRowNum() + "行出现问题：" + error);

				} catch (Exception e) {
					logger.error("sheet1 " + row.getRowNum() + "行出现问题", e);
					operateLogList.add("sheet1 " + row.getRowNum() + "行出现问题：" + e.getMessage());
					continue;
				}

			}

		}

		return resultList;

	}

	private KgClass packageClassInfo(LoginUser user,Row row) {

		Integer cellIndex = 0;

		KgClass kgClass = new KgClass();

		String clsName = getCellValue(row.getCell(cellIndex++));
		if (clsName == null || clsName.trim().equals(""))
			return null;

		String parentClsName = getCellValue(row.getCell(cellIndex++));

		String attrListName = getCellValue(row.getCell(cellIndex++));

		kgClass.setClsName(clsName);
		kgClass.setParentName(parentClsName);
		String proIds = "";
		if (attrListName != null && !attrListName.equals("")) {
			String[] attrs = attrListName.split(",");
			for (String attr : attrs) {

				try {

					String name = attr.substring(0, attr.indexOf('('));
					String dataTypeId = attr.substring(attr.indexOf('(') + 1, attr.indexOf(')'));

					KgProperty pro = new KgProperty();
					pro.setName(name);
					// pro.setDataTypeId(dataTypeId);
					pro.setDataTypeCode(dataTypeId);
					pro.setCannull("1");// 可空

					WZResponseEntity rst = kgPropertyService.saveOrmodify(user,pro);
					if (rst.getIsSuccess())
						proIds += rst.getBody().toString() + ",";

				} catch (Exception e) {
					continue;
				}

			}

			kgClass.setAttrs(proIds);
		}

		return kgClass;

	}

	private KgRelation packageRelationInfo(LoginUser user,Row row) {

		Integer cellIndex = 0;

		KgRelation data = new KgRelation();

		String rname = getCellValue(row.getCell(cellIndex++));
		if (rname == null || rname.trim().equals(""))
			return null;

		data.setName(rname);

		String attrListName = getCellValue(row.getCell(cellIndex++));
		String proIds = "";
		if (attrListName != null && !attrListName.equals("")) {
			String[] attrs = attrListName.split(",");
			for (String attr : attrs) {

				try {

					String name = attr;
					String dataTypeId = "1003";

					try {
						// 解析异常都当文本类型
						name = attr.substring(0, attr.indexOf('('));
						dataTypeId = attr.substring(attr.indexOf('(') + 1, attr.indexOf(')'));
					} catch (Exception e) {

					}

					KgProperty pro = new KgProperty();
					pro.setName(name);
					// pro.setDataTypeId(dataTypeId);
					pro.setDataTypeCode(dataTypeId);
					pro.setCannull("1");// 可空

					WZResponseEntity rst = kgPropertyService.saveOrmodify(user,pro);
					if (rst.getIsSuccess())
						proIds += rst.getBody().toString() + ",";

				} catch (Exception e) {
					continue;
				}

			}

			data.setAttrs(proIds);
		}

		return data;

	}

	private KgEntity packageEntityInfo(LoginUser user,Row row, String error) {

		Integer cellIndex = 0;

		KgEntity data = new KgEntity();

		String rname = getCellValue(row.getCell(cellIndex++));
		if (rname == null || rname.trim().equals(""))
			return null;

		data.setFullName(rname);
		data.setName(rname);

		String clsname = getCellValue(row.getCell(cellIndex++));
		if (clsname == null || clsname.trim().equals(""))
			return null;

		data.setClsName(clsname);

		// 默认clsName标签
		JSONArray jtags = new JSONArray();
		JSONObject jtag = new JSONObject();
		jtag.put("label", clsname);
		jtag.put("text", clsname);
		jtag.put("key", clsname);
		jtag.put("value", clsname);
		jtags.add(jtag);
		data.setTags(jtags.toJSONString());

		JSONArray jprops = new JSONArray();

		// 通用属性
		String attrListName = getCellValue(row.getCell(cellIndex++));
		buildEntityAttr(user,attrListName, jprops, error);

		// 额外属性
		String attrListNameExt = getCellValue(row.getCell(cellIndex++));
		buildEntityAttr(user,attrListNameExt, jprops, error);

		// 如果有名称属性，更新名称
		for (int i = 0; i < jprops.size(); i++) {

			JSONObject jattr = jprops.getJSONObject(i);
			if (jattr.getString("label") != null && jattr.getString("label").equals("名称")) {
				data.setName(jattr.getString("value"));
				break;
			}

		}

		data.setProperties(jprops.toString());

		return data;

	}

	/**
	 * 构造neo4j对象属性
	 * 
	 * @param attrListName
	 * @param jpropso
	 * @author:kxjl
	 * @date 2020年7月2日
	 */
	private JSONArray buildNeo4jAttr(LoginUser user,Row row, boolean isEntity, Neo4jColInfo colinfo, String error) {
		JSONArray jprops = new JSONArray();

		try {

			int cellIndex = 0;
			int last = colinfo.getColRelationStart();

			if (!isEntity) {
				cellIndex = colinfo.getColRelationStart();
				last = colinfo.getCol_last();
			}
			for (int i = cellIndex; i < last; i++) {

				String attr = getCellValue(row.getCell(i));

				try {

					String name = colinfo.getCols().get(i);
					String value = attr;

					if (attr != null && !attr.equals("")) {
						KgProperty pro = getOrCreateAttr(user,name);

						JSONObject entityAttr = new JSONObject();
						entityAttr.put("rule", pro.getDataTypeRule());
						entityAttr.put("label", name);
						entityAttr.put("value", value);

						jprops.add(entityAttr);
					}

				} catch (Exception e) {
					continue;
				}

			}

		} catch (Exception e) {
			error += "属性解析失败:" + e.getMessage();
		}

		return jprops;
	}

	/**
	 * 构造实体属性
	 * 
	 * @param attrListName
	 * @param jprops
	 * @author:kxjl
	 * @date 2020年7月2日
	 */
	private void buildEntityAttr(LoginUser user,String attrListName, JSONArray jprops, String error) {
		if (attrListName != null && !attrListName.equals("")) {

			try {

				JSONObject jattr = JSON.parseObject(attrListName);
				if (jattr != null)
					for (String attr : jattr.keySet()) {

						try {

							String name = attr;
							String value = jattr.get(attr).toString();

							KgProperty pro = getOrCreateAttr(user,name);
							if (pro != null) {
								JSONObject entityAttr = new JSONObject();
								entityAttr.put("rule", pro.getDataTypeRule());
								entityAttr.put("label", name);
								entityAttr.put("value", value);

								jprops.add(entityAttr);
							}

						} catch (Exception e) {
							continue;
						}

					}

			} catch (Exception e) {
				error += "属性解析失败:" + e.getMessage();
			}
		}
	}

	/**
	 * 查询或者新建文本属性
	 * 
	 * @param name
	 * @return
	 * @author:kxjl
	 * @date 2020年7月2日
	 */
	private KgProperty getOrCreateAttr(LoginUser user,String name) {
		KgProperty pro = null;

		pro = propsCaches.get(name);
		if (pro == null) {
			pro = kgPropertyService.getOneByName(name, Constants.DEFULT_VERSION);
			if (pro == null) {
				// 没有的属性，都新建属性
				String attrname = name;
				String dataTypeId = "1003";

				KgProperty proNew = new KgProperty();
				proNew.setName(name);
				// pro.setDataTypeId(dataTypeId);
				proNew.setDataTypeCode(dataTypeId);
				proNew.setCannull("1");// 可空

				WZResponseEntity rst = kgPropertyService.saveOrmodify(user,proNew);
				if (rst.getIsSuccess()) {
					pro = kgPropertyService.getOneByName(name, Constants.DEFULT_VERSION);
				}
			
			}
			propsCaches.put(name, pro);
		}
		return pro;
	}

	private KgObjectToObject packageObjectToObjectInfo(LoginUser user,Row row, String error) {

		Integer cellIndex = 0;
		KgObjectToObject data = new KgObjectToObject();

		// from name
		String fname = getCellValue(row.getCell(cellIndex++));
		if (fname == null || fname.trim().equals(""))
			return null;
		data.setFromEntityNameReal(fname.trim());
		data.setFromName(fname.trim());
		data.setFromType("1");// entity

		// relation name
		String rname = getCellValue(row.getCell(cellIndex++));
		if (rname == null || rname.trim().equals(""))
			return null;
		data.setLineNameReal(rname.trim());
		data.setRelationName(rname.trim());

		JSONArray jprops = new JSONArray();

		// 通用属性
		String attrListName = getCellValue(row.getCell(cellIndex++));
		buildEntityAttr(user,attrListName, jprops, error);
		data.setRelationProperties(jprops.toString());

		// toname
		String tname = getCellValue(row.getCell(cellIndex++));
		if (tname == null || tname.trim().equals(""))
			return null;
		data.setToEntityNameReal(tname.trim());
		data.setToType("1");// entity

		data.setToName(tname.trim());

		return data;

	}

	/**
	 * neo4j 导出数据，第一行列头
	 * 
	 * @param row
	 * @param error
	 * @return
	 * @author:kxjl
	 * @date 2020年7月6日
	 */
	private List<String> packageNeo4jColsInfo(Row row, String error) {

		Integer cellIndex = 0;

		List<String> cols = new ArrayList();
		String rname = "";
		do {
			rname = getCellValue(row.getCell(cellIndex++));
			if (rname != null && !rname.equals(""))
				cols.add(rname);
		} while (rname != null && !rname.equals(""));

		return cols;

	}

	/**
	 * 解析noe4j 导出的节点实体数据
	 * 
	 * @param row
	 * @param neo4jColInfo
	 *            表头
	 * @param error
	 * @return
	 * @author:kxjl
	 * @date 2020年7月6日
	 */
	private KgEntity packageNeo4jRowEntityInfo(LoginUser user,Row row, Neo4jColInfo neo4jColInfo, String error) {

		Integer cellIndex = 0;

		KgEntity data = new KgEntity();

		data.set_id(getCellValue(row.getCell(0)));

		String rtags = getCellValue(row.getCell(neo4jColInfo.getCol_tags()));
		String[] tgsArray = rtags.split(":");

		String rname = getCellValue(row.getCell(neo4jColInfo.getCol_name()));
		if (rname == null || rname.trim().equals(""))
			rname = tgsArray[1];// 没有名称，默认tag第一个

		//data.setFullName("neo4j:" + rname);
		data.setFullName("" + rname);
		data.setName(rname);

		// 默认clsName标签
		String clsname = tgsArray[1];
		data.setClsName(clsname);

		// tag
		JSONArray jtags = new JSONArray();
		for (int i = 0; i < tgsArray.length; i++) {
			String tag = tgsArray[i];
			if (tag.trim().equals(""))
				continue;

			JSONObject jtag = new JSONObject();
			jtag.put("label", tag);
			jtag.put("text", tag);
			jtag.put("key", tag);
			jtag.put("value", tag);
			jtags.add(jtag);
		}
		data.setTags(jtags.toJSONString());

		// 属性
		JSONArray jprops = buildNeo4jAttr(user,row, true, neo4jColInfo, error);

		data.setProperties(jprops.toString());

		return data;

	}

	/**
	 * 根据id获取名称
	 * 
	 * @param id
	 * @param entitys
	 * @return
	 * @author:kxjl
	 * @date 2020年7月6日
	 */
	private String getNeo4jNodeNameByid(String id, List<KgEntity> entitys) {
		for (KgEntity kgEntity : entitys) {
			if (kgEntity.get_id().equals(id))
				return kgEntity.getFullName();
		}
		return "";
	}

	private KgObjectToObject packageNeo4jRowO2OInfo(LoginUser user,List<KgEntity> entitys, Row row, Neo4jColInfo neo4jColInfo,
			String error) {

		Integer cellIndex = 0;
		KgObjectToObject data = new KgObjectToObject();

		// from name
		String frontnode_id = getCellValue(row.getCell(neo4jColInfo.getColRelationStart()));
		String fname = getNeo4jNodeNameByid(frontnode_id, entitys);

		if (fname == null || fname.trim().equals(""))
			return null;
		data.setFromEntityNameReal(fname.trim());
		data.setFromName(fname.trim());
		data.setFromType("1");// entity

		// relation name
		String rname = getCellValue(row.getCell(neo4jColInfo.getColRelationStart() + 2));
		if (rname == null || rname.trim().equals(""))
			return null;
		data.setLineNameReal(rname.trim());
		data.setRelationName(rname.trim());

		// 属性
		JSONArray jprops = buildNeo4jAttr(user,row, false, neo4jColInfo, error);

		// toname
		String tonode_id = getCellValue(row.getCell(neo4jColInfo.getColRelationStart() + 1));
		String tname = getNeo4jNodeNameByid(tonode_id, entitys);

		if (tname == null || tname.trim().equals(""))
			return null;
		data.setToEntityNameReal(tname.trim());
		data.setToType("1");// entity

		data.setToName(tname.trim());

		return data;

	}

	/**
	 * 根据cell类型获得对应类型的值
	 *
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell) {
		if (null == cell) {
			return null;
		}

		// 获取cellType的类型
		CellType cellType = cell.getCellTypeEnum();
		String cellValueString;
		if (cellType == CellType.BOOLEAN) {
			cellValueString = String.valueOf(cell.getBooleanCellValue());
		} else if (cellType == CellType.NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				cellValueString = getCellDateTime(cell);
			} else {
				double cellValue = cell.getNumericCellValue();
				DecimalFormat format = new DecimalFormat("#.##");
				cellValueString = format.format(cellValue);
			}
		} else if (cellType == CellType.FORMULA) {
			cellValueString = cell.getCellFormula();
		} else {
			cellValueString = cell.getStringCellValue();
		}
		return cellValueString;
	}

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getCellDateTime(Cell cell) {
		Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		String value = format.format(date);
		return value;
	}

}
