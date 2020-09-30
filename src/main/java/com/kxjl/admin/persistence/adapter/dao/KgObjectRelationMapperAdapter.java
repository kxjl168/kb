package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgObjectRelationMapper;
import com.kxjl.admin.persistence.entity.KgObjectRelation;

import java.util.List;


/**
* 自定义的sql接口
* @date 2020年06月16日 16:31:39
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgObjectRelationMapperAdapter extends KgObjectRelationMapper {

      List<KgObjectRelation> selectByCondition(KgObjectRelation kgObjectRelation);

}
