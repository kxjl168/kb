package com.kxjl.admin.persistence.entity;

public class KgClassKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_CLASS.ID
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column KG_CLASS.VERSION_ID
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    private String versionId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_CLASS.ID
     *
     * @return the value of KG_CLASS.ID
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    public String getId() {
        return id;
    }
    
    public String subName;
    

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_CLASS.ID
     *
     * @param id the value for KG_CLASS.ID
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column KG_CLASS.VERSION_ID
     *
     * @return the value of KG_CLASS.VERSION_ID
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column KG_CLASS.VERSION_ID
     *
     * @param versionId the value for KG_CLASS.VERSION_ID
     *
     * @mbg.generated Tue Jun 16 16:31:55 CST 2020
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
}