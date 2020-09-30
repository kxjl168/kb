package com.kxjl.admin.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.neo4j.driver.AuthTokens;
//import org.neo4j.driver.Driver;
//import org.neo4j.driver.GraphDatabase;
//import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
//
//import org.neo4j.driver.AuthTokens;
//import org.neo4j.driver.Driver;
//import org.neo4j.driver.GraphDatabase;
//import org.neo4j.driver.Record;
//import org.neo4j.driver.Session;
//import org.neo4j.driver.Result;
//import org.neo4j.driver.Transaction;
//import org.neo4j.driver.TransactionWork;
//
//import static org.neo4j.driver.Values.parameters;

@Component
public class Neo4jHelperOneSession {

	/**
	 * 同步数据到neo4j中
	 * 
	 * @param datas
	 * @author:kxjl
	 * @date 2020年6月28日
	 */
	public static boolean saveToNeo4j(List<KgObjectToObject> datas) {

		boolean rst = false;

		return rst;
	}

	public static void main(String[] args) {
		// test();
		// save();
	}

	@Value("${neo4jDbUrl:bolt://192.168.1.194:7687}")
	private String neo4jDbUrl;
	@Value("${neo4jUser:test}")
	private String neo4jUser;
	@Value("${neo4jPass:admin}")
	private String neo4jPass;

	public void init(String dburl, String user, String pass) {
		// if(neo4jDbUrl!=null&&neo4jDbUrl.equals(""))
		this.neo4jDbUrl = dburl;
		this.neo4jUser = user;
		this.neo4jPass = pass;
	}

	/**
	 * 测试neo4j连接
	 * 
	 * @author:kxjl
	 * @date 2020年7月24日
	 */
	public boolean testDb() {
		boolean isOk = false;
		try {
//			Driver driver = createDrive();
//
//			String rst = "";
//			try {
//				Session session = driver.session();
//
//				String greeting = session.writeTransaction(new TransactionWork<String>() {
//					@Override
//					public String execute(Transaction tx) {
//						Result result = tx.run("match (n) where n.nodeid='" + "1" + "' return id(n) ");
//
//						return result.hasNext() ? "true" : "false";
//					}
//				});
//				rst = greeting;
//				isOk = true;
//				// rst=Boolean.parseBoolean(greeting);
//			} catch (Exception e) {
//				System.out.println(e);
//			}
//
//			driver.close();

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			System.err.println(1);
		}

		return isOk;

	}

//	public void CloseDriver(Driver driver) {
//		
//		try {
//			driver.close();
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//	
//	}
	
	
	
	

	public void test() {
		try {
//			Driver driver = createDrive();
//
//			try (Session session = driver.session()) {
//				String greeting = session.writeTransaction(new TransactionWork<String>() {
//					@Override
//					public String execute(Transaction tx) {
//						Result result = tx.run("CREATE (a:Greeting) " + "SET a.message = $message "
//								+ "RETURN a.message + ', from node ' + id(a)", parameters("message", "1111"));
//						return result.single().get(0).asString();
//					}
//				});
//				System.out.println(greeting);
//			}
//
//			driver.close();

		} catch (Exception e) {
			System.err.println(e);
		} finally {

		}

	}

	//public Driver createDrive() {
		
		//return GraphDatabase.driver(neo4jDbUrl, AuthTokens.basic(neo4jUser, neo4jPass));
	//}

	/**
	 * 寻找节点，更新
	 * 
	 * @param nodeid
	 * @return
	 * @author:kxjl
	 * @date 2020年6月28日
	 */
//	public boolean findNodeById(String nodeid,Session session) {
//		
//		Boolean rst = false;
//		try {
//			
//				String greeting = session.writeTransaction(new TransactionWork<String>() {
//					@Override
//					public String execute(Transaction tx) {
//						Result result = tx.run("match (n) where n.nodeid='" + nodeid + "' return id(n) ");
//
//						return result.hasNext() ? "true" : "false";
//					}
//				});
//				rst = Boolean.parseBoolean(greeting);
//			
//
//		} catch (Exception e) {
//			System.err.println(e);
//		}
//
//		return rst;
//	}

	/**
	 * 删除关系，再添加
	 * 
	 * @param id
	 * @author:kxjl
	 * @date 2020年6月28日
	 */
//	public void deleteRelationById(final String id,Session session) {
//		
//		try {
//		
//				String greeting = session.writeTransaction(new TransactionWork<String>() {
//					@Override
//					public String execute(Transaction tx) {
//						Result result = tx.run(" match(n)-[r]-(m) where r.lineInstanceId='" + id + "'  delete r");
//
//						return "ok";// result.single().get(0).asString();
//					}
//				});
//				System.out.println(greeting);
//		
//
//		} catch (Exception e) {
//			System.err.println(e);
//		}
//	}

	/**
	 * 更新节点
	 * 
	 * @param node
	 * @author:kxjl
	 * @date 2020年6月28日
	 */
//	public String updateNode(KgEntity node,Session session) {
//		
//		String rst = "";
//
//		try {
//		
//			String tagjson = node.getTags();
//			JSONArray jtags = new JSONArray();
//			if (tagjson != null) {
//				try {
//					jtags = JSON.parseArray(tagjson);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//
//			}
//			String tagstring = "";
//			for (int i = 0; i < jtags.size(); i++) {
//				JSONObject tag = jtags.getJSONObject(i);
//				tagstring += ":`" + tag.getString("label") + "`";
//			}
//			final String tstring = tagstring;
//
//			String attrjson = node.getProperties();
//			JSONArray jattrs = new JSONArray();
//			if (attrjson != null) {
//				try {
//					jattrs = JSON.parseArray(attrjson);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//
//			}
//
//			String attrstring = "";
//			for (int i = 0; i < jattrs.size(); i++) {
//				JSONObject attr = jattrs.getJSONObject(i);
//				if (attr.getString("value") != null && attr.getString("value").startsWith("[")
//						&& !attr.getString("value").contains("{"))
//					attrstring += "n.`" + attr.getString("label") + "`=" + attr.getString("value") + ",";
//				else
//					attrstring += "n.`" + attr.getString("label") + "`=\""
//							+ (attr.getString("value") == null ? "" : attr.getString("value")) + "\",";
//			}
//			if (jattrs.size() == 0) {
//				attrstring += " n.名称='" + node.getName() + "',"; // id
//			}
//
//			if (attrstring.endsWith(","))
//				attrstring = attrstring.substring(0, attrstring.length() - 1);
//			attrstring += "";
//			final String astring = attrstring;
//
//	
//				String greeting = session.writeTransaction(new TransactionWork<String>() {
//					@Override
//					public String execute(Transaction tx) {
//
//						Result labs = tx.run("match (n) where n.nodeid='" + node.getId() + "' return labels(n) ");
//						String exsitlabel = "";
//						for (Object lab : labs.single().get(0).asList()) {
//							exsitlabel += ":`" + lab.toString() + "`";
//						}
//						;
//						if (exsitlabel.endsWith(","))
//							exsitlabel.subSequence(0, exsitlabel.length() - 1);
//
//						if (!exsitlabel.equals(""))
//							tx.run("match (n) where n.nodeid='" + node.getId() + "' remove n" + exsitlabel); // 移除标签
//
//						if (!tstring.equals(""))
//							tx.run("match (n) where n.nodeid='" + node.getId() + "' set n " + tstring); // 添加标签
//
//						if (!astring.equals(""))
//							tx.run("match (n) where n.nodeid='" + node.getId() + "' set " + astring); // 更新属性
//						return "ok";// result.single().get(0).asString();
//					}
//				});
//				System.out.println("节点更新完成");
//				rst = node.getName() + "节点更新完成";
//			
//
//		} catch (Exception e) {
//			System.out.println(node.getName() + " 节点更新发生错误:" + e);
//			rst = node.getName() + " 节点更新发生错误:" + e;
//		}
//		return rst;
//	}

	/**
	 * 创建关系
	 * 
	 * @param relation
	 * @author:kxjl
	 * @date 2020年6月28日
	 */
//	public String createRelation(KgObjectToObject relation,Session session) {
//		String rst = "";
//
//		try {
//		
//
//			String attrjson = relation.getRelationProperties();
//			JSONArray jattrs = new JSONArray();
//			if (attrjson != null) {
//				try {
//					jattrs = JSON.parseArray(attrjson);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//
//			}
//
//			String attrstring = "{";
//			attrstring += " lineInstanceId:'" + relation.getId() + "',"; // 线id
//			for (int i = 0; i < jattrs.size(); i++) {
//				JSONObject attr = jattrs.getJSONObject(i);
//				attrstring += attr.getString("label") + ":'" + attr.getString("value") + "',";
//			}
//			if (attrstring.endsWith(","))
//				attrstring = attrstring.substring(0, attrstring.length() - 1);
//			attrstring += "}";
//			final String astring = attrstring;
//
//			
//				String greeting = session.writeTransaction(new TransactionWork<String>() {
//					@Override
//					public String execute(Transaction tx) {
//						Result result = tx.run("match (n) where n.nodeid='" + relation.getFromId()
//								+ "' match(m) where m.nodeid='" + relation.getToId() + "' create (n)-[r:`"
//								+ relation.getRelationName() + "` " + astring + "]->(m)  ");
//						return "ok";// result.single().get(0).asString();
//					}
//				});
//				System.out.println(relation.getRelationName() + " 创建完成");
//				rst = relation.getRelationName() + " 关系创建完成";
//	
//
//		} catch (Exception e) {
//			System.err.println(e);
//			rst = relation.getRelationName() + " 关系创建错误 " + e;
//		}
//		return rst;
//	}

	/**
	 * 创建节点
	 * 
	 * @param entity
	 * @author:kxjl
	 * @date 2020年6月28日
	 */
//	public String createNode(KgEntity entity,Session session) {
//		String rst = "";
//		try {
//		
//			String tagjson = entity.getTags();
//			JSONArray jtags = new JSONArray();
//			if (tagjson != null) {
//				try {
//					jtags = JSON.parseArray(tagjson);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//
//			}
//			String tagstring = "";
//			for (int i = 0; i < jtags.size(); i++) {
//				JSONObject tag = jtags.getJSONObject(i);
//				tagstring += ":`" + tag.getString("text") + "`";
//			}
//			final String tstring = tagstring;
//
//			String attrjson = entity.getProperties();
//			JSONArray jattrs = new JSONArray();
//			if (attrjson != null) {
//				try {
//					jattrs = JSON.parseArray(attrjson);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//
//			}
//
//			String attrstring = "{";
//			attrstring += " nodeid:'" + entity.getId() + "',"; // id
//		
//			for (int i = 0; i < jattrs.size(); i++) {
//				JSONObject attr = jattrs.getJSONObject(i);
//				if (attr.getString("value") != null && attr.getString("value").startsWith("[")
//						&& !attr.getString("value").contains("{"))
//					attrstring += "`" + attr.getString("label") + "`:" + attr.getString("value") + ",";
//				else
//					attrstring += "`" + attr.getString("label") + "`:\""
//							+ (attr.getString("value") == null ? "" : attr.getString("value")) + "\",";
//			}
//			if (jattrs.size() == 0) {
//				attrstring += " 名称:'" + entity.getName() + "',"; // id
//			}
//
//			if (attrstring.endsWith(","))
//				attrstring = attrstring.substring(0, attrstring.length() - 1);
//			attrstring += "}";
//			final String astring = attrstring;
//
//			System.out.println("CREATE (n" + tstring + " " + astring + ") ");
//
//		
//				String greeting = session.writeTransaction(new TransactionWork<String>() {
//					@Override
//					public String execute(Transaction tx) {
//						Result result = tx.run("CREATE (n" + tstring + " " + astring + ") ");
//						return "ok";// result.single().get(0).asString();
//					}
//				});
//
//				System.out.println(entity.getName() + " 创建完成");
//				rst = entity.getName() + " 节点创建完成";
//			
//		} catch (Exception e) {
//			System.err.println("create:" + e);
//			rst = entity.getName() + " 节点创建错误 " + e;
//		}
//		return rst;
//	}

	private void save() {
		try {
//			Driver driver = createDrive();
//
//			try (Session session = driver.session()) {
//				String greeting = session.writeTransaction(new TransactionWork<String>() {
//					@Override
//					public String execute(Transaction tx) {
//						Result result = tx.run("CREATE (a:" + "测试标签" + " {" + "attr1:1" + "}) return id(a)",
//								parameters("message", "1111"));
//						return "ok";// result.single().get(0).asString();
//					}
//				});
//				System.out.println(greeting);
//			}
//
//			driver.close();

		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
