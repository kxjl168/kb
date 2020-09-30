package com.kxjl.admin.persistence.adapter.dao;

import java.util.List;

import com.kxjl.admin.persistence.entity.KgDataAuditDetail;
import com.kxjl.admin.persistence.dao.KgDataAuditDetailMapper;


/**
* 自定义的sql接口
* @date 2020年08月05日 15:18:56
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgDataAuditDetailMapperAdapter extends KgDataAuditDetailMapper {

      List<KgDataAuditDetail> selectByCondition(KgDataAuditDetail kgDataAuditDetail);

}
