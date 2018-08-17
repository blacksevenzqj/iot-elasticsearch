package com.thinkgem.elclient.entity.recharge;

/**
 * @author guyuqiao
 */
import java.util.Date;

public class RechargeRecordList extends RechargeRecordVo{
	private String peasantryName;
	private Integer rechargeType;
	private Date rechargeTime;
	
	
	public String getPeasantryName() {
		return peasantryName;
	}
	public void setPeasantryName(String peasantryName) {
		this.peasantryName = peasantryName;
	}
	public Integer getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}
	public Date getRechargeTime() {
		return rechargeTime;
	}
	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}
	@Override
	public String toString() {
		return "RechargeRecordList [ peasantryName=" + peasantryName + ", rechargeType="
				+ rechargeType + ", rechargeTime=" + rechargeTime + "]";
	}
	
}
