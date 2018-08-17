package com.thinkgem.elclient.entity.recharge;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author guyuqiao
 */
public class RechargeRecordVo {
	private String id;
	
	private Date rechargeTime;
	
	private Integer rechargeType;
	
	private String peasantryId;
	
	private BigDecimal rechargeAmount;
	
	private BigDecimal cardMoney;
	
	private Integer operationType;
	
	//private String sysAccountId;
	
	private String deviceId;
	
	private BigDecimal balance;
	
	private String cardId;
	
	private Integer chargeStatus;
	
	private Integer cardStatus;
	
	private String cashierId;
	
	private String peasantryName;
	
	private String cardNo;	
	
    private Integer year;

    private Integer month;
    
	private Integer idNum;
    
	private String yearMonths;
	
	

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/*public CardInfo getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}
*/
	/*public PeasantryInfo getPeasantryInfo() {
		return peasantryInfo;
	}

	public void setPeasantryInfo(PeasantryInfo peasantryInfo) {
		this.peasantryInfo = peasantryInfo;
	}
*/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public Integer getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getPeasantryId() {
		return peasantryId;
	}

	public void setPeasantryId(String peasantryId) {
		this.peasantryId = peasantryId;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public BigDecimal getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(BigDecimal cardMoney) {
		this.cardMoney = cardMoney;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Integer getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(Integer chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public Integer getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCashierId() {
		return cashierId;
	}

	public void setCashierId(String cashierId) {
		this.cashierId = cashierId;
	}

	public String getPeasantryName() {
		return peasantryName;
	}

	public void setPeasantryName(String peasantryName) {
		this.peasantryName = peasantryName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public String getYearMonths() {
		return yearMonths;
	}

	public void setYearMonths(String yearMonths) {
		this.yearMonths = yearMonths;
	}

	//@Override
	/*public String toString() {
		return "RechargeRecordVo [id=" + id + ", rechargeTime=" + rechargeTime + ", rechargeType=" + rechargeType
				+ ", peasantryId=" + peasantryId + ", rechargeAmount=" + rechargeAmount + ", cardMoney=" + cardMoney
				+ ", operationType=" + operationType + ", deviceId=" + deviceId + ", balance=" + balance + ", cardId="
				+ cardId + ", chargeStatus=" + chargeStatus + ", cardStatus=" + cardStatus + ", cashierId=" + cashierId
				+ ", peasantryName=" + peasantryName + ", cardInfo=" + cardInfo + ", peasantryInfo=" + peasantryInfo
				+ ", year=" + year + ", month=" + month + ", idNum=" + idNum + ", yearMonths=" + yearMonths + "]";
	}*/

	
	
}
