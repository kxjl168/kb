package com.kxjl.admin.persistence.adapter.dao;

import java.util.List;

import com.kxjl.admin.persistence.entity.KgEditData;
import com.kxjl.admin.persistence.entity.KgSubGroup;
import com.kxjl.admin.persistence.dao.KgEditDataMapper;


/**
* 自定义的sql接口
* @date 2020年08月03日 13:27:07
* @author 具体开发人员请在此补充上本人名称拼音
*/
public interface KgEditDataMapperAdapter extends KgEditDataMapper {

      List<KgEditData> selectByCondition(KgEditData kgEditData);

      
	  /**
       * 
       * @param example
       * @return
       * @author:kxjl
       * @date 2020年6月9日
       */
      List<KgEditData> selectList(KgEditData example);
      
      /**
       * 查询待审核数据，按实体分组，一个实体显示一条记录。 合并编辑用户，编辑数据ori_edit_id
       * @param example
       * @return
       * @author:kxjl
       * @date 2020年8月5日
       */
      List<KgEditData> selectToAudit(KgEditData example);
      
      
      
      
      
      /**
       * 提交审核
       * @param item
       * @return
       * @author:kxjl
       * @date 2020年8月3日
       */
      int toaudit(String id);
      
      
      /**
       * 完全审核通过
       * @param item
       * @return
       * @author:kxjl
       * @date 2020年8月3日
       */
      int auditfullpass(KgEditData item);
      
      
      /**
       * 合并审核通过
       * @param item
       * @return
       * @author:kxjl
       * @date 2020年8月3日
       */
      int auditpass(KgEditData item);
      
      
      /**
       * 审核失败
       * @param item
       * @return
       * @author:kxjl
       * @date 2020年8月3日
       */
      int auditfail(KgEditData item);
      
      
      /**
       * 根据数据id删除
       * @param id
       * @return
       * @author:kxjl
       * @date 2020年8月4日
       */
        int deleteByEditDataId(String id);
      
      
        /**
         * 根据真实id，用户id删除所有未提价的修改草稿
         * @param item
         * @author:kxjl
         * @date 2020年8月4日
         */
        int deleteByOriDataIdAndUserId(KgEditData item);
      
      
}
