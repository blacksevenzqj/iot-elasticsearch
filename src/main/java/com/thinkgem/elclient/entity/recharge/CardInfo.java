package com.thinkgem.elclient.entity.recharge;

import java.math.BigDecimal;

public class CardInfo {
	private String id;
	private String peasantryId;
	private String cardNo;
	private BigDecimal balance;
	private Integer cardStatus;
	private Integer delFlag;
	private Integer bindingStatus;
	private Integer cardType;
	private String cardmf1sn;
	private String managerName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPeasantryId() {
		return peasantryId;
	}
	public void setPeasantryId(String peasantryId) {
		this.peasantryId = peasantryId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Integer getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getBindingStatus() {
		return bindingStatus;
	}
	public void setBindingStatus(Integer bindingStatus) {
		this.bindingStatus = bindingStatus;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getCardmf1sn() {
		return cardmf1sn;
	}
	public void setCardmf1sn(String cardmf1sn) {
		this.cardmf1sn = cardmf1sn;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	@Override
	public String toString() {
		return "CardInfo [id=" + id + ", peasantryId=" + peasantryId + ", cardNo=" + cardNo + ", balance=" + balance
				+ ", cardStatus=" + cardStatus + ", delFlag=" + delFlag + ", bindingStatus=" + bindingStatus
				+ ", cardType=" + cardType + ", cardmf1sn=" + cardmf1sn + ", managerName=" + managerName + "]";
	}
	
}
