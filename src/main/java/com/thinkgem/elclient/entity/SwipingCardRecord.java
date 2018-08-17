package com.thinkgem.elclient.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 刷卡记录
 */
public class SwipingCardRecord {
    private String id;

    private String cardNo;

    private Date startTime;

    private Date endTime;

    private BigDecimal balance;

    private Date recordTime;

    private BigDecimal consumptionStere;

    private BigDecimal consumptionMoney;

    private String valveName;
    
    private String cardId;
    
    private String valveId;
    
    private String peasantryId;

    private String peasantryName;
    
    private BigDecimal baseNumber;
    
    
    public BigDecimal getBaseNumber() {
		return baseNumber;
	}

	public void setBaseNumber(BigDecimal baseNumber) {
		this.baseNumber = baseNumber;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
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

    public String getValveName() {
        return valveName;
    }

    public void setValveName(String valveName) {
        this.valveName = valveName == null ? null : valveName.trim();
    }

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getValveId() {
		return valveId;
	}

	public void setValveId(String valveId) {
		this.valveId = valveId;
	}

	public String getPeasantryId() {
		return peasantryId;
	}

	public void setPeasantryId(String peasantryId) {
		this.peasantryId = peasantryId;
	}

    public String getPeasantryName() {
        return peasantryName;
    }

    public void setPeasantryName(String peasantryName) {
        this.peasantryName = peasantryName;
    }
}