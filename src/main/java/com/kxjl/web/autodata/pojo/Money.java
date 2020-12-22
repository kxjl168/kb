package com.kxjl.web.autodata.pojo;

import java.math.BigDecimal;

public class Money {
    private String id;

    private String name;

    private String mType;

    private String mDate;

    private String inOut;

    private String spec;

    private String createUser;

    private String updateUser;

    private String remark;

    private String createDate;

    private String updateDate;

    private BigDecimal money;
    
    //query
    private String typeName;
    private String typeIcon;
    
    private String[] mDatelst;//多选年份统计
    
    
    private String month;
    private String year;
    //是否显示房屋相关记录
    private String showhouse;//true,false
    
    private BigDecimal in_money;
    public BigDecimal getIn_money() {
		return in_money;
	}

	public void setIn_money(BigDecimal in_money) {
		this.in_money = in_money;
	}

	public BigDecimal getOut_money() {
		return out_money;
	}

	public void setOut_money(BigDecimal out_money) {
		this.out_money = out_money;
	}

	private BigDecimal out_money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType == null ? null : mType.trim();
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate == null ? null : mDate.trim();
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut == null ? null : inOut.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeIcon() {
		return typeIcon;
	}

	public void setTypeIcon(String typeIcon) {
		this.typeIcon = typeIcon;
	}

	public String getShowhouse() {
		return showhouse;
	}

	public void setShowhouse(String showhouse) {
		this.showhouse = showhouse;
	}

	public String[] getmDatelst() {
		return mDatelst;
	}

	public void setmDatelst(String[] mDatelst) {
		this.mDatelst = mDatelst;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}