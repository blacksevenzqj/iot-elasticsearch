package com.thinkgem.elclient.entity.home;

import java.math.BigDecimal;

public class RechargeMonth extends RecordF{

    private BigDecimal rechargeAmount;

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }
}
