package com.kxjl.admin.persistence.entity;

import java.util.Date;

public class KgDataAuditLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_DATA_AUDIT_LOG.ID
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_DATA_AUDIT_LOG.DATA_NAME
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    private String dataName;
    private String editOriDataId;
	private String auditRstId;//审核完成后的数据快照id，对应edit_表 id
	
    
    
    //start 对应 edit_data数据表中的多人数据
	private String editUser;
	private String editUserName;
	private String editUserDate; //多人编辑时间
	
	private String dataType;

	/**
	 *
	 * COMMENT '编辑操作类型 1:新增，2:修改， 3:删除'
	 *
	 * @mbg.generated Mon Aug 03 13:27:33 CST 2020
	 */
	private String editAction;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column KG_EDIT_DATA.EDIT_DATA_ID
	 *
	 * @mbg.generated Mon Aug 03 13:27:33 CST 2020
	 */
	private String editDataId;//当前编辑数据id
	
	//end
	
    
    

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_DATA_AUDIT_LOG.AUDIT_DATE
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    private Date auditDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_DATA_AUDIT_LOG.AUDIT_STATE
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    private String auditState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_DATA_AUDIT_LOG.AUDIT_INFO
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    private String auditInfo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_DATA_AUDIT_LOG.AUDIT_USER
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    private String auditUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_DATA_AUDIT_LOG.ID
     *
     * @return the value of KG_DATA_AUDIT_LOG.ID
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_DATA_AUDIT_LOG.ID
     *
     * @param id the value for KG_DATA_AUDIT_LOG.ID
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_DATA_AUDIT_LOG.DATA_NAME
     *
     * @return the value of KG_DATA_AUDIT_LOG.DATA_NAME
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_DATA_AUDIT_LOG.DATA_NAME
     *
     * @param dataName the value for KG_DATA_AUDIT_LOG.DATA_NAME
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_DATA_AUDIT_LOG.AUDIT_DATE
     *
     * @return the value of KG_DATA_AUDIT_LOG.AUDIT_DATE
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public Date getAuditDate() {
        return auditDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_DATA_AUDIT_LOG.AUDIT_DATE
     *
     * @param auditDate the value for KG_DATA_AUDIT_LOG.AUDIT_DATE
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_DATA_AUDIT_LOG.AUDIT_STATE
     *
     * @return the value of KG_DATA_AUDIT_LOG.AUDIT_STATE
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public String getAuditState() {
        return auditState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_DATA_AUDIT_LOG.AUDIT_STATE
     *
     * @param auditState the value for KG_DATA_AUDIT_LOG.AUDIT_STATE
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_DATA_AUDIT_LOG.AUDIT_INFO
     *
     * @return the value of KG_DATA_AUDIT_LOG.AUDIT_INFO
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public String getAuditInfo() {
        return auditInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_DATA_AUDIT_LOG.AUDIT_INFO
     *
     * @param auditInfo the value for KG_DATA_AUDIT_LOG.AUDIT_INFO
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void setAuditInfo(String auditInfo) {
        this.auditInfo = auditInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_DATA_AUDIT_LOG.AUDIT_USER
     *
     * @return the value of KG_DATA_AUDIT_LOG.AUDIT_USER
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public String getAuditUser() {
        return auditUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_DATA_AUDIT_LOG.AUDIT_USER
     *
     * @param auditUser the value for KG_DATA_AUDIT_LOG.AUDIT_USER
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

	public String getEditUser() {
		return editUser;
	}

	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}

	public String getEditUserName() {
		return editUserName;
	}

	public void setEditUserName(String editUserName) {
		this.editUserName = editUserName;
	}

	public String getEditUserDate() {
		return editUserDate;
	}

	public void setEditUserDate(String editUserDate) {
		this.editUserDate = editUserDate;
	}

	public String getEditAction() {
		return editAction;
	}

	public void setEditAction(String editAction) {
		this.editAction = editAction;
	}

	public String getEditDataId() {
		return editDataId;
	}

	public void setEditDataId(String editDataId) {
		this.editDataId = editDataId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getEditOriDataId() {
		return editOriDataId;
	}

	public void setEditOriDataId(String editOriDataId) {
		this.editOriDataId = editOriDataId;
	}

	public String getAuditRstId() {
		return auditRstId;
	}

	public void setAuditRstId(String auditRstId) {
		this.auditRstId = auditRstId;
	}
}