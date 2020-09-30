package com.kxjl.admin.persistence.adapter.dao;

import java.util.List;

import com.kxjl.admin.persistence.entity.KgDataAuditLog;
import com.kxjl.admin.persistence.entity.KgDataLog;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.dao.KgDataAuditLogMapper;


/**
* 自定义的sql接口
* @date 2020年08月05日 15:18:56
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgDataAuditLogMapperAdapter extends KgDataAuditLogMapper {

   
      
      
      /**
       * 
       * @param example
       * @return
       * @author:kxjl
       * @date 2020年6月9日
       */
      List<KgDataAuditLog> selectList(KgDataAuditLog example);
      
      /**
       * 根据审核结果id查询原始提交数据
       * @param item
       * @return
       * @author:kxjl
       * @date 2020年8月19日
       */
      KgEditData getEdataByAuditRstId(KgDataAuditLog item);
      
}
