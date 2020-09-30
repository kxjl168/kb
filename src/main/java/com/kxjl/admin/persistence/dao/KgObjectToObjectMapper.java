package com.kxjl.admin.persistence.dao;

import com.kxjl.admin.persistence.entity.KgObjectToObject;
import com.kxjl.admin.persistence.entity.KgObjectToObjectExample;
import com.kxjl.admin.persistence.entity.KgObjectToObjectKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KgObjectToObjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    long countByExample(KgObjectToObjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    int deleteByExample(KgObjectToObjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    int deleteByPrimaryKey(KgObjectToObjectKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    int insert(KgObjectToObject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    int insertSelective(KgObjectToObject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    List<KgObjectToObject> selectByExample(KgObjectToObjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    KgObjectToObject selectByPrimaryKey(KgObjectToObjectKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    int updateByExampleSelective(@Param("record") KgObjectToObject record, @Param("example") KgObjectToObjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    int updateByExample(@Param("record") KgObjectToObject record, @Param("example") KgObjectToObjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    int updateByPrimaryKeySelective(KgObjectToObject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_OBJECT_TO_OBJECT
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    int updateByPrimaryKey(KgObjectToObject record);
}