package com.kxjl.admin.persistence.dao;

import com.kxjl.admin.persistence.entity.KgTags;
import com.kxjl.admin.persistence.entity.KgTagsExample;
import com.kxjl.admin.persistence.entity.KgTagsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KgTagsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    long countByExample(KgTagsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int deleteByExample(KgTagsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int deleteByPrimaryKey(KgTagsKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int insert(KgTags record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int insertSelective(KgTags record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    List<KgTags> selectByExample(KgTagsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    KgTags selectByPrimaryKey(KgTagsKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByExampleSelective(@Param("record") KgTags record, @Param("example") KgTagsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByExample(@Param("record") KgTags record, @Param("example") KgTagsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByPrimaryKeySelective(KgTags record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_TAGS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByPrimaryKey(KgTags record);
}