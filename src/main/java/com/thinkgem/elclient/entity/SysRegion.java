package com.thinkgem.elclient.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 地址
 * @author sunjinpeng
 *
 */
public class SysRegion implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String parentId;

    private String regionName;

    private BigDecimal plowlandAcreage;

    private String regionAddress;

    private Integer regionType;

    private String zipcode;

    private String remark;
    
    private Integer delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public BigDecimal getPlowlandAcreage() {
        return plowlandAcreage;
    }

    public void setPlowlandAcreage(BigDecimal plowlandAcreage) {
        this.plowlandAcreage = plowlandAcreage;
    }

    public String getRegionAddress() {
        return regionAddress;
    }

    public void setRegionAddress(String regionAddress) {
        this.regionAddress = regionAddress == null ? null : regionAddress.trim();
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
    
}