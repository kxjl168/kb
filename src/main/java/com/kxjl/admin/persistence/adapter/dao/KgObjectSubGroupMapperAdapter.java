package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgObjectSubGroupMapper;
import com.kxjl.admin.persistence.entity.KgObjectProperty;
import com.kxjl.admin.persistence.entity.KgObjectSubGroup;

import java.util.List;


/**
* 自定义的sql接口
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgObjectSubGroupMapperAdapter extends KgObjectSubGroupMapper {

     
      /**
       * 根据objid清空关系
       * @param kgObjectProperty
       * @return
       * @author:kxjl
       * @date 2020年6月17日
       */
      Boolean deleteByObjectId(KgObjectSubGroup item);
      
}
