package com.kxjl.admin.persistence.adapter.dao;

import java.util.List;

import com.kxjl.admin.persistence.entity.KgDataLog;
import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.dao.KgDataLogMapper;


/**
* 自定义的sql接口
* @date 2020年08月03日 13:27:07
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgDataLogMapperAdapter extends KgDataLogMapper {

      List<KgDataLog> selectByCondition(KgDataLog kgDataLog);

      
      /**
       * 
       * @param example
       * @return
       * @author:kxjl
       * @date 2020年6月9日
       */
      List<KgDataLog> selectList(KgDataLog example);
      
}
