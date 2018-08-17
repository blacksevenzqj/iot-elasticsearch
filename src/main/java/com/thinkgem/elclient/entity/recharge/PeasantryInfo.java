package com.thinkgem.elclient.entity.recharge;

import java.math.BigDecimal;
import java.util.Date;

public class PeasantryInfo {
	private String id;
	private String peasantryName;
	private String idCard;
	private String peasantryNumber;
	private String peasantryPhone;
	private String peasantryHnumber;
	private Integer cropType;
	private String peasantryAddr;
	private String provinceId;
	private String cityId;
	private String districtId;
	private String townId;
	private String villageId;
	private String remark;
	private BigDecimal cardTotalBalance;
	private Integer delFlag;
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPeasantryName() {
		return peasantryName;
	}
	public void setPeasantryName(String peasantryName) {
		this.peasantryName = peasantryName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPeasantryNumber() {
		return peasantryNumber;
	}
	public void setPeasantryNumber(String peasantryNumber) {
		this.peasantryNumber = peasantryNumber;
	}
	public String getPeasantryPhone() {
		return peasantryPhone;
	}
	public void setPeasantryPhone(String peasantryPhone) {
		this.peasantryPhone = peasantryPhone;
	}
	public String getPeasantryHnumber() {
		return peasantryHnumber;
	}
	public void setPeasantryHnumber(String peasantryHnumber) {
		this.peasantryHnumber = peasantryHnumber;
	}
	public Integer getCropType() {
		return cropType;
	}
	public void setCropTtype(Integer cropType) {
		this.cropType = cropType;
	}
	public String getPeasantryAddr() {
		return peasantryAddr;
	}
	public void setPeasantryAddr(String peasantryAddr) {
		this.peasantryAddr = peasantryAddr;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getTownId() {
		return townId;
	}
	public void setTownId(String townId) {
		this.townId = townId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getCardTotalBalance() {
		return cardTotalBalance;
	}
	public void setCardTotalBalance(BigDecimal cardTotalBalance) {
		this.cardTotalBalance = cardTotalBalance;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "PeasantryInfo [id=" + id + ", peasantryName=" + peasantryName + ", idCard=" + idCard
				+ ", peasantryNumber=" + peasantryNumber + ", peasantryPhone=" + peasantryPhone + ", peasantryHnumber="
				+ peasantryHnumber + ", cropType=" + cropType + ", peasantryAddr=" + peasantryAddr + ", provinceId="
				+ provinceId + ", cityId=" + cityId + ", districtId=" + districtId + ", townId=" + townId
				+ ", villageId=" + villageId + ", remark=" + remark + ", cardTotalBalance=" + cardTotalBalance
				+ ", delFlag=" + delFlag + ", createTime=" + createTime + "]";
	}
	
	
}
