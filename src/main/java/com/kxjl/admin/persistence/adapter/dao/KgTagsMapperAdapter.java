package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgTagsMapper;
import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.persistence.entity.KgTags;

import java.util.List;


/**
* 自定义的sql接口
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgTagsMapperAdapter extends KgTagsMapper {

      
      List<KgTags> selectList(KgTags KgTags);
      
      /**
       * 查询名称是否存在
       * @param kgTags
       * @return
       * @author:kxjl
       * @date 2020年6月23日
       */
      KgTags selectByName(KgTags kgTags);

}
