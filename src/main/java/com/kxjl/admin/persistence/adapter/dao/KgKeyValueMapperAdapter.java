package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgKeyValueMapper;
import com.kxjl.admin.persistence.entity.KgKeyValue;

import java.util.List;

/**
* @author Generator
* @description 自定义的sql接口
* @date 2020年06月12日 17:17:27
**/
public interface KgKeyValueMapperAdapter extends KgKeyValueMapper {

      List<KgKeyValue> selectList(KgKeyValue kgKeyValue);

}
