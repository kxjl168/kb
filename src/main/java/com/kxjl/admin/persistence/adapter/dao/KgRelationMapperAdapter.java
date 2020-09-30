package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgRelationMapper;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgProperty;
import com.kxjl.admin.persistence.entity.KgRelation;

import java.util.List;

/**
* @author Generator
* @description 自定义的sql接口
* @date 2020年06月12日 17:17:27
**/
public interface KgRelationMapperAdapter extends KgRelationMapper {

      List<KgRelation> selectList(KgRelation kgRelation);

      /**
       * 查询连线上的属性
       * @param kgRelation
       * @return
       * @author:kxjl
       * @date 2020年6月19日
       */
      List<KgProperty> getProperty(KgRelation kgRelation);
      /**
       * 查询名称是否存在
       * @param kgTags
       * @return
       * @author:kxjl
       * @date 2020年6月23日
       */
      KgRelation selectByName(KgRelation kgTags);

}
