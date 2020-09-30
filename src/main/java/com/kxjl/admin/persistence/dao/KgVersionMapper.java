package com.kxjl.admin.persistence.dao;

import com.kxjl.admin.persistence.entity.KgVersion;
import com.kxjl.admin.persistence.entity.KgVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KgVersionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    long countByExample(KgVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int deleteByExample(KgVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int insert(KgVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int insertSelective(KgVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    List<KgVersion> selectByExample(KgVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    KgVersion selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByExampleSelective(@Param("record") KgVersion record, @Param("example") KgVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByExample(@Param("record") KgVersion record, @Param("example") KgVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByPrimaryKeySelective(KgVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_VERSION
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByPrimaryKey(KgVersion record);
}