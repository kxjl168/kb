package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgPropertyMapper;
import com.kxjl.admin.persistence.entity.KgProperty;

import java.util.List;

/**
* @author Generator
* @description 自定义的sql接口
* @date 2020年06月12日 17:17:27
**/
public interface KgPropertyMapperAdapter extends KgPropertyMapper {

      List<KgProperty> selectList(KgProperty kgProperty);

      
      /**
       * 查询名称是否存在
       * @param kgTags
       * @return
       * @author:kxjl
       * @date 2020年6月23日
       */
      KgProperty selectByName(KgProperty kgTags);
}
