package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgObjectPropertyMapper;
import com.kxjl.admin.persistence.entity.KgObjectProperty;

import java.util.List;


/**
* 自定义的sql接口
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgObjectPropertyMapperAdapter extends KgObjectPropertyMapper {

      List<KgObjectProperty> selectList(KgObjectProperty kgObjectProperty);
      
      /**
       * 根据clsid清空属性关系
       * @param kgObjectProperty
       * @return
       * @author:kxjl
       * @date 2020年6月17日
       */
      Boolean deleteByClsId(KgObjectProperty kgObjectProperty);
      
      /**
       * 根据relationdi 清空属性关系
       * @param kgObjectProperty
       * @return
       * @author:kxjl
       * @date 2020年6月17日
       */
      Boolean deleteByRelationId(KgObjectProperty kgObjectProperty);

}
