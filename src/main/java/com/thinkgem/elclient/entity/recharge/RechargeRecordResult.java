package com.thinkgem.elclient.entity.recharge;

import com.thinkgem.elclient.utils.PageUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author guyuqiao
 */
public class RechargeRecordResult {
	
	 private Integer totalIdNum;
	//总充值金额
    private BigDecimal totalRechargeAmount;
    //总金额
    private BigDecimal totalBalance;
    
    private List<RechargeRecordVo> monthRechargeRecord;

    private PageUtils pageUtils;
    
    public Integer getTotalIdNum() {
		return totalIdNum;
	}

	public void setTotalIdNum(Integer totalIdNum) {
		this.totalIdNum = totalIdNum;
	}

    public BigDecimal getTotalRechargeAmount() {
        return totalRechargeAmount;
    }

    public void setTotalRechargeAmount(BigDecimal totalRechargeAmount) {
        this.totalRechargeAmount = totalRechargeAmount;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public List<RechargeRecordVo> getMonthRechargeRecord() {
        return monthRechargeRecord;
    }

    public void setMonthRechargeRecord(List<RechargeRecordVo> monthRechargeRecord) {
        this.monthRechargeRecord = monthRechargeRecord;
    }

    public PageUtils getPageUtils() {
        return pageUtils;
    }

    public void setPageUtils(PageUtils pageUtils) {
        this.pageUtils = pageUtils;
    }
}
