package com.kxjl.admin.persistence.adapter.dao;

import java.util.List;

import com.kxjl.admin.persistence.entity.KgEditObjectToObject;
import com.kxjl.admin.persistence.dao.KgEditObjectToObjectMapper;


/**
* 自定义的sql接口
* @date 2020年08月03日 13:27:07
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgEditObjectToObjectMapperAdapter extends KgEditObjectToObjectMapper {

      List<KgEditObjectToObject> selectByCondition(KgEditObjectToObject kgEditObjectToObject);

      
      List<KgEditObjectToObject> selectList(KgEditObjectToObject kgObjectToObject);

      /**
       * 根据edit_data_id 删除
       * @param id
       * @return
       * @author:kxjl
       * @date 2020年8月17日
       */
      int deleteByEDataId(String id);
      
}
