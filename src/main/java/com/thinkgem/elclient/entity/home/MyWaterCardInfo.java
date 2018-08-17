package com.thinkgem.elclient.entity.home;

import java.math.BigDecimal;

/**
 * @author zhaoqingjie
 */
public class MyWaterCardInfo {

    private String accountId;

    private String accountName;

    private String loginName;

    private Integer accountType;

    private String personnelId;

    private String cardId;

    private String cardNo;

    private BigDecimal balance;

    private BigDecimal rechargeAmount;

    private String rechargeTime;

    private BigDecimal consumptionStere;

    private BigDecimal consumptionMoney;

    private String startTime;

    private String endTime;
    
    private String peasantryAddr;


    public String getPeasantryAddr() {
		return peasantryAddr;
	}

	public void setPeasantryAddr(String peasantryAddr) {
		this.peasantryAddr = peasantryAddr;
	}

	public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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
        this.personnelId = personnelId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(String rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public BigDecimal getConsumptionStere() {
        return consumptionStere;
    }

    public void setConsumptionStere(BigDecimal consumptionStere) {
        this.consumptionStere = consumptionStere;
    }

    public BigDecimal getConsumptionMoney() {
        return consumptionMoney;
    }

    public void setConsumptionMoney(BigDecimal consumptionMoney) {
        this.consumptionMoney = consumptionMoney;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "MyWaterCard{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", accountType=" + accountType +
                ", personnelId='" + personnelId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", balance=" + balance +
                ", rechargeAmount=" + rechargeAmount +
                ", rechargeTime='" + rechargeTime + '\'' +
                ", consumptionStere=" + consumptionStere +
                ", consumptionMoney=" + consumptionMoney +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

}
