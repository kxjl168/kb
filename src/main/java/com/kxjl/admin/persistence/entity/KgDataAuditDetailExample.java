package com.kxjl.admin.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class KgDataAuditDetailExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public KgDataAuditDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdIsNull() {
            addCriterion("DATA_AUDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdIsNotNull() {
            addCriterion("DATA_AUDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdEqualTo(String value) {
            addCriterion("DATA_AUDIT_ID =", value, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdNotEqualTo(String value) {
            addCriterion("DATA_AUDIT_ID <>", value, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdGreaterThan(String value) {
            addCriterion("DATA_AUDIT_ID >", value, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_AUDIT_ID >=", value, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdLessThan(String value) {
            addCriterion("DATA_AUDIT_ID <", value, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdLessThanOrEqualTo(String value) {
            addCriterion("DATA_AUDIT_ID <=", value, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdLike(String value) {
            addCriterion("DATA_AUDIT_ID like", value, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdNotLike(String value) {
            addCriterion("DATA_AUDIT_ID not like", value, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdIn(List<String> values) {
            addCriterion("DATA_AUDIT_ID in", values, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdNotIn(List<String> values) {
            addCriterion("DATA_AUDIT_ID not in", values, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdBetween(String value1, String value2) {
            addCriterion("DATA_AUDIT_ID between", value1, value2, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataAuditIdNotBetween(String value1, String value2) {
            addCriterion("DATA_AUDIT_ID not between", value1, value2, "dataAuditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdIsNull() {
            addCriterion("DATA_EDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andDataEditIdIsNotNull() {
            addCriterion("DATA_EDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDataEditIdEqualTo(String value) {
            addCriterion("DATA_EDIT_ID =", value, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdNotEqualTo(String value) {
            addCriterion("DATA_EDIT_ID <>", value, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdGreaterThan(String value) {
            addCriterion("DATA_EDIT_ID >", value, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_EDIT_ID >=", value, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdLessThan(String value) {
            addCriterion("DATA_EDIT_ID <", value, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdLessThanOrEqualTo(String value) {
            addCriterion("DATA_EDIT_ID <=", value, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdLike(String value) {
            addCriterion("DATA_EDIT_ID like", value, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdNotLike(String value) {
            addCriterion("DATA_EDIT_ID not like", value, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdIn(List<String> values) {
            addCriterion("DATA_EDIT_ID in", values, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdNotIn(List<String> values) {
            addCriterion("DATA_EDIT_ID not in", values, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdBetween(String value1, String value2) {
            addCriterion("DATA_EDIT_ID between", value1, value2, "dataEditId");
            return (Criteria) this;
        }

        public Criteria andDataEditIdNotBetween(String value1, String value2) {
            addCriterion("DATA_EDIT_ID not between", value1, value2, "dataEditId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated do_not_delete_during_merge Wed Aug 05 15:19:24 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table KG_DATA_AUDIT_DETAIL
     *
     * @mbg.generated Wed Aug 05 15:19:24 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}