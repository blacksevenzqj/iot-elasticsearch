package com.thinkgem.elclient.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统账户
 */
public class SysAccount {
    private String id;

    private String accountName;

    private String loginName;

    private String loginPwd;

    private Integer delFlag;

    private Integer accountType;

    private String personnelId;

    private Date createTime;

    List<Map<String, Object>> roles;
    
    

	public List<Map<String, Object>> getRoles() {
		return roles;
	}

	public void setRoles(List<Map<String, Object>> roles) {
		this.roles = roles;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId == null ? null : personnelId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}