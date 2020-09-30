package com.kxjl.admin.persistence.dao;

import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgClassExample;
import com.kxjl.admin.persistence.entity.KgClassKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KgClassMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    long countByExample(KgClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int deleteByExample(KgClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int deleteByPrimaryKey(KgClassKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int insert(KgClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int insertSelective(KgClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    List<KgClass> selectByExample(KgClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    KgClass selectByPrimaryKey(KgClassKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByExampleSelective(@Param("record") KgClass record, @Param("example") KgClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByExample(@Param("record") KgClass record, @Param("example") KgClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByPrimaryKeySelective(KgClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_CLASS
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    int updateByPrimaryKey(KgClass record);
}