package com.kxjl.admin.persistence.adapter.dao;

import java.util.List;

import com.kxjl.admin.persistence.entity.KgEditClass;
import com.kxjl.admin.persistence.entity.KgEditRelation;
import com.kxjl.admin.persistence.dao.KgEditClassMapper;


/**
* 自定义的sql接口
* @date 2020年08月03日 13:27:07
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgEditClassMapperAdapter extends KgEditClassMapper {

      List<KgEditClass> selectByCondition(KgEditClass kgEditClass);

      
      
      List<KgEditClass> selectList(KgEditClass kgEditClass);

}
