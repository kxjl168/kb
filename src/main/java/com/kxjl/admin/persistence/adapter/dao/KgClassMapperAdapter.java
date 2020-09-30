package com.kxjl.admin.persistence.adapter.dao;

import java.util.List;

import com.kxjl.admin.persistence.dao.KgClassMapper;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgEntity;

import java.util.List;

/**
* @author Generator
* @description 自定义的sql接口
* @date 2020年06月12日 17:17:27
**/
public interface KgClassMapperAdapter extends KgClassMapper {


	  /**
     * 
     * @param example
     * @return
     * @author:kxjl
     * @date 2020年6月9日
     */
    List<KgClass> selectList(KgClass example);
    
    
    /**
     * 获取指定cls的下级cls
     * @param node
     * @return
     * @author:kxjl
     * @date 2020年6月18日
     */
    List<KgClass> getTreeData(KgClass node);
    
    /**
     * 查询名称是否存在
     * @param kgTags
     * @return
     * @author:kxjl
     * @date 2020年6月23日
     */
    KgClass selectByName(KgClass kgTags);
    
    /**
     * 查询详情，包括属性列表，领域列表
     * @param kgClass
     * @return
     * @author:kxjl
     * @date 2020年7月14日
     */
    KgClass selectDetailById(KgClass kgClass);

}
