package com.thinkgem.elclient.entity;

/**
 * @author guyuqiao
 */
import com.thinkgem.elclient.entity.recharge.CardInfo;
import com.thinkgem.elclient.entity.recharge.PeasantryInfo;

import java.math.BigDecimal;
import java.util.Date;

public class RechargeRecord {
	private String id;
    
    private Date rechargeTime;

    private Integer rechargeType;

    private BigDecimal rechargeAmount;

    private BigDecimal cardMoney;

    private Integer operationType;

    private String deviceId;

    private BigDecimal balance;

    private CardInfo cardInfo;	//水卡信息

    private Integer chargeStatus;

    private Integer cardStatus;
    
    private WaterAuthorityInfo waterAuthorityInfo;	//收银员信息

    private PeasantryInfo peasantryInfo;	//农户信息

    private String startTime;
    
    private String endTime;
    
    private String checkDate;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CardInfo getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
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
    
	public WaterAuthorityInfo getWaterAuthorityInfo() {
		return waterAuthorityInfo;
	}

	public void setWaterAuthorityInfo(WaterAuthorityInfo waterAuthorityInfo) {
		this.waterAuthorityInfo = waterAuthorityInfo;
	}

	public PeasantryInfo getPeasantryInfo() {
		return peasantryInfo;
	}

	public void setPeasantryInfo(PeasantryInfo peasantryInfo) {
		this.peasantryInfo = peasantryInfo;
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

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	
}
