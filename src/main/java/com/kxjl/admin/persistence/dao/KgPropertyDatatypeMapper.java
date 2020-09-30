package com.kxjl.admin.persistence.dao;

import com.kxjl.admin.persistence.entity.KgPropertyDatatype;
import com.kxjl.admin.persistence.entity.KgPropertyDatatypeExample;
import com.kxjl.admin.persistence.entity.KgPropertyDatatypeKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KgPropertyDatatypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    long countByExample(KgPropertyDatatypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    int deleteByExample(KgPropertyDatatypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    int deleteByPrimaryKey(KgPropertyDatatypeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    int insert(KgPropertyDatatype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    int insertSelective(KgPropertyDatatype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    List<KgPropertyDatatype> selectByExample(KgPropertyDatatypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    KgPropertyDatatype selectByPrimaryKey(KgPropertyDatatypeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    int updateByExampleSelective(@Param("record") KgPropertyDatatype record, @Param("example") KgPropertyDatatypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    int updateByExample(@Param("record") KgPropertyDatatype record, @Param("example") KgPropertyDatatypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    int updateByPrimaryKeySelective(KgPropertyDatatype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_PROPERTY_DATATYPE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    int updateByPrimaryKey(KgPropertyDatatype record);
}