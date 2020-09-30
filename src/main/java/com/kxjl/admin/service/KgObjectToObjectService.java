/**
 * Copyright (c) 2019 kxjl All Rights Reserved.
 * 
 * This software license agreement (the "Agreement") is a legal agreement between the user 
 * ("You" or the "User") and kxjl ("kxjl") 
 * for the software products (the "Software") and related services (the "Service") that 
 * accompanies this Agreement, as may be updated or replaced by feature enhancements, 
 * updates or maintenance releases and any services that may be provided by kxjl under this Agreement. 
 * You are not allowed to download, install or use the Software or to use Services unless 
 * you accept all the terms and conditions of this Agreement. Your downloading, 
 * installation and use of the Software shall be regarded as your acceptance of the Agreement 
 * and your agreement to be bound by all the terms and conditions of this Agreement.
 * 
 * The above notice shall be included in all copies or substantial portions of the Software.
 * 
 * The software is provided "as is", without warranty of any kind, express or implied, 
 * including but not limited to the warranties of merchantability, fitness for a particular 
 * purpose and noninfringement. In no event shall the authors or copyright holders be 
 * liable for any claim, damages or other liability, whether in an action of contract, 
 * tort or otherwise, arising from, out of or in connection with the software or the use 
 * or other dealings in the software.
 */
package com.kxjl.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.kxjl.admin.common.Pagination;
import com.kxjl.admin.common.WZResponseEntity;
import com.kxjl.admin.common.LoginUser;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.persistence.entity.KgPropertyDatatype;

import java.util.List;

/**
* 具体开发人员请在此补充上类描述说明
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgObjectToObjectService {


    /**
     * <p>New Info</p>
     * @param kgObjectToObject
     * @return
     */
     Boolean add(KgObjectToObject kgObjectToObject) ;
    
    /**
     * <p>Modify Info</p>
     * @param kgObjectToObject
     * @return
     */
     Boolean modify(KgObjectToObject kgObjectToObject);
    
    /**
     * <p>Delete</p>
     * @param id
     * @return
     */
     Boolean delete(String id, String versionId);


    /**
     * <p>query info by id</p>
     * @param id
     * @return
     */
     KgObjectToObject getOne(String id, String versionId);

    
    /**
     * <p>query all info</p>
     * @return
     */
    List<KgObjectToObject> getAll();
    /**
   	 * 
   	 * @param example
   	 * @return
   	 * @author:kxjl
   	 * @date 2020年6月9日
   	 */
   	 Page<KgObjectToObject> selectList(KgObjectToObject query, Pagination pageCondition); 
   	 
   	/**
 	 * 保存本体关系数据
 	 * @param data  GGEditor 保存json数据  <pre>
 	 * {
     "nodes": [
         {
             "type": "node",
             "size": "60*60",
             "shape": "custom-node-rect",
             "stroke": "#ff0000",
             "color": "#FA8C16",
             "stroke_left": "#0000ff",
             "nodeid": "",
             "nodetype": "",
             "label": "绩效事实",
             "labelOffsetY": 20,
             "icon": "//img.alicdn.com/tfs/TB1gXH2ywHqK1RjSZFPXXcwapXa-200-200.svg",
             "x": 20,
             "y": 20,
             "index": 0,
             "id": "4c414b9b"
         },
         {
             "type": "node",
             "size": "60*60",
             "shape": "custom-node-rect",
             "stroke": "#ff0000",
             "color": "#FA8C16",
             "stroke_left": "#0000ff",
             "nodeid": "",
             "nodetype": "",
             "label": "中国城市",
             "labelOffsetY": 20,
             "icon": "//img.alicdn.com/tfs/TB1gXH2ywHqK1RjSZFPXXcwapXa-200-200.svg",
             "x": 200,
             "y": 200,
             "index": 2,
             "id": "30f18e09"
         }
     ],
     "edges": [
         {
             "source": "4c414b9b",
             "sourceAnchor": 3,
             "target": "30f18e09",
             "id": "51c90445",
             "label": "测试",
             "index": 1
         }
     ]
 }
 	 * </pre> 
 	 * @return
 	 * @param editUser 审核通过时使用，原始修改用户id
 	 * @author:kxjl
 	 * @date 2020年6月16日
 	 */
 	public Boolean saveontology(LoginUser user,String data,String editUser) ;
 	
 	/**
 	 * 根据class查询一二三级关系的所有GGEditor需要json格式 {@link saveontology}
 	 * @param kgClass
 	 * @param level  1,2,3
 	 * @return
 	 * @author:kxjl
 	 * @date 2020年6月16日
 	 */
 	public String getGraDataByLevel(LoginUser user,KgClass kgClass,int level,boolean showEdit);
 	
 	public List<KgObjectToObject> getObjRelationByLevel(KgClass kgClass, int level) ;

 	
 	public WZResponseEntity<?> saveOrmodify(KgObjectToObject item) ;
 	
 	/**
 	 * 删除出发关系
 	 * @param query
 	 * @author:kxjl
 	 * @date 2020年7月8日
 	 */
 	void  deleteByFromClsId(KgObjectToObject query);
 	
 	/**
	 * 查询领域关系数量
	 * @param subKgId
	 * @return
	 * @author:kxjl
	 * @date 2020年7月24日
	 */
	public List<KgObjectToObject> getSubKgRelation(String subKgId);
	
	/**
	 * neo4j连接测试
	 * @param dburl
	 * @param username
	 * @param pass
	 * @return
	 * @author:kxjl
	 * @date 2020年7月24日
	 */
	public WZResponseEntity<?> TestDb(String dburl,String username,String pass);
 	
 	/**
	 * 按领域同步数据，只要是领域下的实体存在关系均同步
	 * 
	 * @param subkgId
	 * @return
	 * @author:kxjl
	 * @date 2020年7月23日
	 */
	public WZResponseEntity<?> DataToNeo4j(LoginUser user,String dburl,String username,String pass,String subkgId);
	
	/**
	 * 检查重叠的线，来源/目标相同，连接至不同的连接点
	 * 
	 * @param jarrayEdges
	 * @author:kxjl
	 * @date 2020年7月20日
	 */
	public JSONArray CheckOverlapRelation(JSONArray jarrayEdges);
	
	public WZResponseEntity<?> audit(LoginUser user, String data) ;
}
