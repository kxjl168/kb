package com.kxjl.admin.persistence.entity;

import java.util.Date;

public class KgObjectToObject extends KgObjectToObjectKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.FROM_ID
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String fromId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.FROM_NAME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String fromName;

    
    private String fromClsNameReal;//cls FROM_NAME_REAL
    private String toClsNameReal;//cls
    
    private String fromEntityNameReal;//cls FROM_NAME_REAL
    private String toEntityNameReal;//cls
    
    
    private String fromNodePros;//实体节点属性
    private String toNodePros;//实体节点属性
    
    private String fromNodeTags;
    private String toNodeTags;
    
    private String myEdit;//自己是否有修改过
    
    private String curUser;//当前查询用户
    
    
   private String lineNameReal;
    
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.FROM_TYPE
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String fromType;
    
    private String fromData;
    private String toData;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.TO_ID
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String toId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.TO_NAME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String toName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.TO_TYPE
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String toType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.RELATION_ID
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String relationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.RELATION_NAME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String relationName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.RELATION_PROPERTIES
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String relationProperties;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.ENABLED
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.DELETED
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.CREATED_USER
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String createdUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.CREATED_TIME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private Date createdTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.UPDATED_USER
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private String updatedUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_OBJECT_TO_OBJECT.UPDATED_TIME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    private Date updatedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.FROM_ID
     *
     * @return the value of KG_OBJECT_TO_OBJECT.FROM_ID
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getFromId() {
        return fromId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.FROM_ID
     *
     * @param fromId the value for KG_OBJECT_TO_OBJECT.FROM_ID
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.FROM_NAME
     *
     * @return the value of KG_OBJECT_TO_OBJECT.FROM_NAME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.FROM_NAME
     *
     * @param fromName the value for KG_OBJECT_TO_OBJECT.FROM_NAME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.FROM_TYPE
     *
     * @return the value of KG_OBJECT_TO_OBJECT.FROM_TYPE
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getFromType() {
        return fromType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.FROM_TYPE
     *
     * @param fromType the value for KG_OBJECT_TO_OBJECT.FROM_TYPE
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.TO_ID
     *
     * @return the value of KG_OBJECT_TO_OBJECT.TO_ID
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getToId() {
        return toId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.TO_ID
     *
     * @param toId the value for KG_OBJECT_TO_OBJECT.TO_ID
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setToId(String toId) {
        this.toId = toId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.TO_NAME
     *
     * @return the value of KG_OBJECT_TO_OBJECT.TO_NAME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getToName() {
        return toName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.TO_NAME
     *
     * @param toName the value for KG_OBJECT_TO_OBJECT.TO_NAME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setToName(String toName) {
        this.toName = toName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.TO_TYPE
     *
     * @return the value of KG_OBJECT_TO_OBJECT.TO_TYPE
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getToType() {
        return toType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.TO_TYPE
     *
     * @param toType the value for KG_OBJECT_TO_OBJECT.TO_TYPE
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setToType(String toType) {
        this.toType = toType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.RELATION_ID
     *
     * @return the value of KG_OBJECT_TO_OBJECT.RELATION_ID
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getRelationId() {
        return relationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.RELATION_ID
     *
     * @param relationId the value for KG_OBJECT_TO_OBJECT.RELATION_ID
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.RELATION_NAME
     *
     * @return the value of KG_OBJECT_TO_OBJECT.RELATION_NAME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getRelationName() {
        return relationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.RELATION_NAME
     *
     * @param relationName the value for KG_OBJECT_TO_OBJECT.RELATION_NAME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.RELATION_PROPERTIES
     *
     * @return the value of KG_OBJECT_TO_OBJECT.RELATION_PROPERTIES
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getRelationProperties() {
        return relationProperties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.RELATION_PROPERTIES
     *
     * @param relationProperties the value for KG_OBJECT_TO_OBJECT.RELATION_PROPERTIES
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setRelationProperties(String relationProperties) {
        this.relationProperties = relationProperties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.ENABLED
     *
     * @return the value of KG_OBJECT_TO_OBJECT.ENABLED
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.ENABLED
     *
     * @param enabled the value for KG_OBJECT_TO_OBJECT.ENABLED
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.DELETED
     *
     * @return the value of KG_OBJECT_TO_OBJECT.DELETED
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.DELETED
     *
     * @param deleted the value for KG_OBJECT_TO_OBJECT.DELETED
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.CREATED_USER
     *
     * @return the value of KG_OBJECT_TO_OBJECT.CREATED_USER
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.CREATED_USER
     *
     * @param createdUser the value for KG_OBJECT_TO_OBJECT.CREATED_USER
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.CREATED_TIME
     *
     * @return the value of KG_OBJECT_TO_OBJECT.CREATED_TIME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.CREATED_TIME
     *
     * @param createdTime the value for KG_OBJECT_TO_OBJECT.CREATED_TIME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.UPDATED_USER
     *
     * @return the value of KG_OBJECT_TO_OBJECT.UPDATED_USER
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.UPDATED_USER
     *
     * @param updatedUser the value for KG_OBJECT_TO_OBJECT.UPDATED_USER
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_OBJECT_TO_OBJECT.UPDATED_TIME
     *
     * @return the value of KG_OBJECT_TO_OBJECT.UPDATED_TIME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_OBJECT_TO_OBJECT.UPDATED_TIME
     *
     * @param updatedTime the value for KG_OBJECT_TO_OBJECT.UPDATED_TIME
     *
     * @mbg.generated Tue Jun 16 17:20:09 CST 2020
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

	public String getFromData() {
		return fromData;
	}

	public void setFromData(String fromData) {
		this.fromData = fromData;
	}

	public String getToData() {
		return toData;
	}

	public void setToData(String toData) {
		this.toData = toData;
	}

	public String getLineNameReal() {
		return lineNameReal;
	}

	public void setLineNameReal(String lineNameReal) {
		this.lineNameReal = lineNameReal;
	}

	public String getFromNodePros() {
		return fromNodePros;
	}

	public void setFromNodePros(String fromNodePros) {
		this.fromNodePros = fromNodePros;
	}

	public String getToNodePros() {
		return toNodePros;
	}

	public void setToNodePros(String toNodePros) {
		this.toNodePros = toNodePros;
	}

	public String getFromClsNameReal() {
		return fromClsNameReal;
	}

	public void setFromClsNameReal(String fromClsNameReal) {
		this.fromClsNameReal = fromClsNameReal;
	}

	public String getToClsNameReal() {
		return toClsNameReal;
	}

	public void setToClsNameReal(String toClsNameReal) {
		this.toClsNameReal = toClsNameReal;
	}

	public String getFromEntityNameReal() {
		return fromEntityNameReal;
	}

	public void setFromEntityNameReal(String fromEntityNameReal) {
		this.fromEntityNameReal = fromEntityNameReal;
	}

	public String getToEntityNameReal() {
		return toEntityNameReal;
	}

	public void setToEntityNameReal(String toEntityNameReal) {
		this.toEntityNameReal = toEntityNameReal;
	}

	public String getFromNodeTags() {
		return fromNodeTags;
	}

	public void setFromNodeTags(String fromNodeTags) {
		this.fromNodeTags = fromNodeTags;
	}

	public String getToNodeTags() {
		return toNodeTags;
	}

	public void setToNodeTags(String toNodeTags) {
		this.toNodeTags = toNodeTags;
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
	

}