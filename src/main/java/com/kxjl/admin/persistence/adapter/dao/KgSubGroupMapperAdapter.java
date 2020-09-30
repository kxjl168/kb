package com.kxjl.admin.persistence.adapter.dao;

import com.kxjl.admin.persistence.dao.KgSubGroupMapper;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgSubGroup;

import java.util.List;

/**
* @author Generator
* @description 自定义的sql接口
* @date 2020年06月12日 17:17:27
**/
public interface KgSubGroupMapperAdapter extends KgSubGroupMapper {

    
	  /**
     * 
     * @param example
     * @return
     * @author:kxjl
     * @date 2020年6月9日
     */
    List<KgSubGroup> selectList(KgSubGroup example);
    
    

}
