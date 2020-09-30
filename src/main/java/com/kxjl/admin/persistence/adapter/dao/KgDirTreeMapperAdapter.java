package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgDirTreeMapper;
import com.kxjl.admin.persistence.entity.KgDirTree;

import java.util.List;

/**
* @author Generator
* @description 自定义的sql接口
* @date 2020年06月12日 17:17:27
**/
public interface KgDirTreeMapperAdapter extends KgDirTreeMapper {

      List<KgDirTree> selectList(KgDirTree kgDirTree);
      
      
      public List<KgDirTree> getTreeData(KgDirTree node);
      

}
