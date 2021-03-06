package com.kxjl.admin.persistence.entity;

import java.util.Date;

public class KgEntity extends KgEntityKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.CLS_ID
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String clsId;
    
    private String _id;//neo4j _id
    
    //query 只查询概念的直接实体
    private String onlyOneLevel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.CLS_NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String clsName;
    
    //query in所有子概念
    private String[] queryClsIds;
    
    private String myEdit;//自己是否有修改过
    
    private String curUser;//当前查询用户

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.FULL_NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String fullName;
    
    private String fullNameFirst;//查询中第一个为全名数据

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.SUB_KG_ID
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String subKgId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.SUB_KG_NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String subKgName;
    
    private String subIds;//领域ids
    
    private String subNames;//领域names
    
    /**
     * 数据审核中当前数据来源，"1:正式数据,2:编辑中的数据"
     */
    private String dataFromType;//数据审核中当前数据来源，"1:正式数据,2:编辑中的数据"

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.PROPERTIES
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String properties;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.EXT_PROPERTIES
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String extProperties;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.TAGS
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String tags;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.DIR_ID
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String dirId;
    
    private String dirName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.DIR_CODE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String dirCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.ENABLED
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.DELETED
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.CREATED_USER
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String createdUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.CREATED_TIME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private Date createdTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.UPDATED_USER
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private String updatedUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_ENTITY.UPDATED_TIME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    private Date updatedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.CLS_ID
     *
     * @return the value of KG_ENTITY.CLS_ID
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getClsId() {
        return clsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.CLS_ID
     *
     * @param clsId the value for KG_ENTITY.CLS_ID
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setClsId(String clsId) {
        this.clsId = clsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.CLS_NAME
     *
     * @return the value of KG_ENTITY.CLS_NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getClsName() {
        return clsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.CLS_NAME
     *
     * @param clsName the value for KG_ENTITY.CLS_NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.NAME
     *
     * @return the value of KG_ENTITY.NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.NAME
     *
     * @param name the value for KG_ENTITY.NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.FULL_NAME
     *
     * @return the value of KG_ENTITY.FULL_NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.FULL_NAME
     *
     * @param fullName the value for KG_ENTITY.FULL_NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.SUB_KG_ID
     *
     * @return the value of KG_ENTITY.SUB_KG_ID
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getSubKgId() {
        return subKgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.SUB_KG_ID
     *
     * @param subKgId the value for KG_ENTITY.SUB_KG_ID
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setSubKgId(String subKgId) {
        this.subKgId = subKgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.SUB_KG_NAME
     *
     * @return the value of KG_ENTITY.SUB_KG_NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getSubKgName() {
        return subKgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.SUB_KG_NAME
     *
     * @param subKgName the value for KG_ENTITY.SUB_KG_NAME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setSubKgName(String subKgName) {
        this.subKgName = subKgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.PROPERTIES
     *
     * @return the value of KG_ENTITY.PROPERTIES
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getProperties() {
        return properties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.PROPERTIES
     *
     * @param properties the value for KG_ENTITY.PROPERTIES
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setProperties(String properties) {
        this.properties = properties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.EXT_PROPERTIES
     *
     * @return the value of KG_ENTITY.EXT_PROPERTIES
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getExtProperties() {
        return extProperties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.EXT_PROPERTIES
     *
     * @param extProperties the value for KG_ENTITY.EXT_PROPERTIES
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setExtProperties(String extProperties) {
        this.extProperties = extProperties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.TAGS
     *
     * @return the value of KG_ENTITY.TAGS
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.TAGS
     *
     * @param tags the value for KG_ENTITY.TAGS
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.DIR_ID
     *
     * @return the value of KG_ENTITY.DIR_ID
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getDirId() {
        return dirId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.DIR_ID
     *
     * @param dirId the value for KG_ENTITY.DIR_ID
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setDirId(String dirId) {
        this.dirId = dirId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.DIR_CODE
     *
     * @return the value of KG_ENTITY.DIR_CODE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getDirCode() {
        return dirCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.DIR_CODE
     *
     * @param dirCode the value for KG_ENTITY.DIR_CODE
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setDirCode(String dirCode) {
        this.dirCode = dirCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.ENABLED
     *
     * @return the value of KG_ENTITY.ENABLED
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.ENABLED
     *
     * @param enabled the value for KG_ENTITY.ENABLED
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.DELETED
     *
     * @return the value of KG_ENTITY.DELETED
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.DELETED
     *
     * @param deleted the value for KG_ENTITY.DELETED
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.CREATED_USER
     *
     * @return the value of KG_ENTITY.CREATED_USER
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.CREATED_USER
     *
     * @param createdUser the value for KG_ENTITY.CREATED_USER
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.CREATED_TIME
     *
     * @return the value of KG_ENTITY.CREATED_TIME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.CREATED_TIME
     *
     * @param createdTime the value for KG_ENTITY.CREATED_TIME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.UPDATED_USER
     *
     * @return the value of KG_ENTITY.UPDATED_USER
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.UPDATED_USER
     *
     * @param updatedUser the value for KG_ENTITY.UPDATED_USER
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_ENTITY.UPDATED_TIME
     *
     * @return the value of KG_ENTITY.UPDATED_TIME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_ENTITY.UPDATED_TIME
     *
     * @param updatedTime the value for KG_ENTITY.UPDATED_TIME
     *
     * @mbg.generated Mon Jun 22 09:29:11 CST 2020
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getSubIds() {
		return subIds;
	}

	public void setSubIds(String subIds) {
		this.subIds = subIds;
	}

	public String getSubNames() {
		return subNames;
	}

	public void setSubNames(String subNames) {
		this.subNames = subNames;
	}

	public String[] getQueryClsIds() {
		return queryClsIds;
	}

	public void setQueryClsIds(String[] queryClsIds) {
		this.queryClsIds = queryClsIds;
	}

	public String getOnlyOneLevel() {
		return onlyOneLevel;
	}

	public void setOnlyOneLevel(String onlyOneLevel) {
		this.onlyOneLevel = onlyOneLevel;
	}

	public String getFullNameFirst() {
		return fullNameFirst;
	}

	public void setFullNameFirst(String fullNameFirst) {
		this.fullNameFirst = fullNameFirst;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getMyEdit() {
		return myEdit;
	}

	public void setMyEdit(String myEdit) {
		this.myEdit = myEdit;
	}

	public String getCurUser() {
		return curUser;
	}

	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}

	/**
	 * 数据审核中当前数据来源，"1:正式数据,2:编辑中的数据"
	 * @return
	 * @author:kxjl
	 * @date 2020年8月20日
	 */
	public String getDataFromType() {
		return dataFromType;
	}

	/**
	 * 数据审核中当前数据来源，"1:正式数据,2:编辑中的数据"
	 * @param dataFromType
	 * @author:kxjl
	 * @date 2020年8月20日
	 */
	public void setDataFromType(String dataFromType) {
		this.dataFromType = dataFromType;
	}


}