package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgPropertyDatatypeMapper;
import com.kxjl.admin.persistence.entity.KgPropertyDatatype;

import java.util.List;

/**
* @author Generator
* @description 自定义的sql接口
* @date 2020年06月12日 17:17:27
**/
public interface KgPropertyDatatypeMapperAdapter extends KgPropertyDatatypeMapper {

      List<KgPropertyDatatype> selectList(KgPropertyDatatype kgPropertyDatatype);

}
