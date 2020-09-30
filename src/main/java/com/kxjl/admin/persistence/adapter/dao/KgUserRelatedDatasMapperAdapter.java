package com.kxjl.admin.persistence.adapter.dao;

import java.util.List;

import com.kxjl.admin.persistence.entity.KgUserRelatedDatas;
import com.kxjl.admin.persistence.dao.KgUserRelatedDatasMapper;


/**
* 自定义的sql接口
* @date 2020年07月27日 10:48:05
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgUserRelatedDatasMapperAdapter extends KgUserRelatedDatasMapper {

      List<KgUserRelatedDatas> selectByCondition(KgUserRelatedDatas kgUserRelatedDatas);

      
      /**
       * 最近使用的关系数据
       * @param kgUserRelatedDatas
       * @return
       * @author:kxjl
       * @date 2020年7月27日
       */
      List<KgUserRelatedDatas> selectRelationList(KgUserRelatedDatas kgUserRelatedDatas);
}
