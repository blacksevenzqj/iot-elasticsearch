package com.thinkgem.elclient.entity;

import java.util.Date;

/**
 * 水务局
 * 
 * @author sunjinpeng
 *
 */
public class WaterAuthorityInfo {
	private String id;

	private String waName;

	private String waNumber;

	private String waPhone;

	private String waDuty;

	private String provinceId;
	
	private String cityId;

	private String districtId;

	private String townId;

	private String villageId;

	private Integer delFlag;

	private Date createTime;
	
	private SysRegion province;

	private SysRegion city; // 市

	private SysRegion district; // 区

	private SysRegion town; // 镇

	private SysRegion village; // 村\

	private SysAccount account;

	public SysAccount getAccount() {
		return account;
	}

	public void setAccount(SysAccount account) {
		this.account = account;
	}

	public SysRegion getProvince() {
		return province;
	}

	public void setProvince(SysRegion province) {
		this.province = province;
	}

	public SysRegion getCity() {
		return city;
	}

	public void setCity(SysRegion city) {
		this.city = city;
	}

	public SysRegion getDistrict() {
		return district;
	}

	public void setDistrict(SysRegion district) {
		this.district = district;
	}

	public SysRegion getTown() {
		return town;
	}

	public void setTown(SysRegion town) {
		this.town = town;
	}

	public SysRegion getVillage() {
		return village;
	}

	public void setVillage(SysRegion village) {
		this.village = village;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getWaName() {
		return waName;
	}

	public void setWaName(String waName) {
		this.waName = waName == null ? null : waName.trim();
	}

	public String getWaNumber() {
		return waNumber;
	}

	public void setWaNumber(String waNumber) {
		this.waNumber = waNumber == null ? null : waNumber.trim();
	}

	public String getWaPhone() {
		return waPhone;
	}

	public void setWaPhone(String waPhone) {
		this.waPhone = waPhone == null ? null : waPhone.trim();
	}

	public String getWaDuty() {
		return waDuty;
	}

	public void setWaDuty(String waDuty) {
		this.waDuty = waDuty == null ? null : waDuty.trim();
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
		this.cityId = cityId == null ? null : cityId.trim();
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId == null ? null : districtId.trim();
	}

	public String getTownId() {
		return townId;
	}

	public void setTownId(String townId) {
		this.townId = townId == null ? null : townId.trim();
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId == null ? null : villageId.trim();
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
		return "WaterAuthorityInfo{" +
				"id='" + id + '\'' +
				", waName='" + waName + '\'' +
				", waNumber='" + waNumber + '\'' +
				", waPhone='" + waPhone + '\'' +
				", waDuty='" + waDuty + '\'' +
				", provinceId='" + provinceId + '\'' +
				", cityId='" + cityId + '\'' +
				", districtId='" + districtId + '\'' +
				", townId='" + townId + '\'' +
				", villageId='" + villageId + '\'' +
				", delFlag=" + delFlag +
				", createTime=" + createTime +
				", province=" + province +
				", city=" + city +
				", district=" + district +
				", town=" + town +
				", village=" + village +
				", account=" + account +
				'}';
	}
}