package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgEntityMapper;
import com.kxjl.admin.persistence.entity.KgEntity;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.persistence.entity.KgTags;

import java.util.List;

/**
* @author Generator
* @description 自定义的sql接口
* @date 2020年06月12日 17:17:27
**/
public interface KgEntityMapperAdapter extends KgEntityMapper {

      List<KgEntity> selectList(KgEntity kgEntity);

      
      /**
       * 查询实体 所属概念上的属性
       * @param kgRelation
       * @return
       * @author:kxjl
       * @date 2020年6月19日
       */
      List<KgProperty> getProperty(KgEntity kgRelation);
      
      /**
       * 查询名称是否存在
       * @param kgTags
       * @return
       * @author:kxjl
       * @date 2020年6月23日
       */
      KgEntity selectByName(KgEntity kgTags);
      
}
