package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgObjectToObjectMapper;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.persistence.entity.KgSubGroup;

import java.util.List;

/**
* @author Generator
* @description 自定义的sql接口
* @date 2020年06月12日 17:17:27
**/
public interface KgObjectToObjectMapperAdapter extends KgObjectToObjectMapper {

      List<KgObjectToObject> selectList(KgObjectToObject kgObjectToObject);

      
      /**
  	 * 根据class查询一级关系的所有关系
  	 * @param kgClass
  	 * @return
  	 * @author:kxjl
  	 * @date 2020年6月16日
  	 */
  	public List<KgObjectToObject> getLevelOneRelation(KgClass kgClass);
  	/**
  	 * 根据class查询二级关系的所有关系
  	 * @param kgClass
  	 * @return
  	 * @author:kxjl
  	 * @date 2020年6月16日
  	 */
  	public List<KgObjectToObject> getLevelTwoRelation(KgClass kgClass);
  	/**
  	 * 根据class查询三级关系的所有关系
  	 * @param kgClass
  	 * @return
  	 * @author:kxjl
  	 * @date 2020年6月16日
  	 */
  	public List<KgObjectToObject> getLevelThreeRelation(KgClass kgClass);
  	
  	/**
  	 * 根据to,form ,relation 实际的实体名称查询是否存在关系。
  	 * @return
  	 * @author:kxjl
  	 * @date 2020年7月2日
  	 */
  	public KgObjectToObject selectByThreeName(KgObjectToObject query);
  	
  	/**
  	 * 删除cls出发的所有关系
  	 * @param query
  	 * @author:kxjl
  	 * @date 2020年7月8日
  	 */
  	 public void  deleteByFromClsId(KgObjectToObject query);
  	 
  	 
 	/**
 	 * 根据领域查询关系
 	 * @param group
 	 * @return
 	 * @author:kxjl
 	 * @date 2020年7月23日
 	 */
   	public List<KgObjectToObject> getSubKgRelation(KgSubGroup group);
   	
}
