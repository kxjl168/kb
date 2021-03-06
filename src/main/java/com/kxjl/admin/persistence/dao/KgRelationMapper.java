package com.kxjl.admin.persistence.dao;

import com.kxjl.admin.persistence.entity.KgRelation;
import com.kxjl.admin.persistence.entity.KgRelationExample;
import com.kxjl.admin.persistence.entity.KgRelationKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KgRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    long countByExample(KgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int deleteByExample(KgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int deleteByPrimaryKey(KgRelationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int insert(KgRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int insertSelective(KgRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    List<KgRelation> selectByExample(KgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    KgRelation selectByPrimaryKey(KgRelationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByExampleSelective(@Param("record") KgRelation record, @Param("example") KgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByExample(@Param("record") KgRelation record, @Param("example") KgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByPrimaryKeySelective(KgRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_RELATION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByPrimaryKey(KgRelation record);
}