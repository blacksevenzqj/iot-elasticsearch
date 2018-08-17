package com.thinkgem.elclient.entity.swiping;

import com.thinkgem.elclient.utils.PageUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhaoqingjie
 */
public class SwipingCardRecordResult {

    private Integer totalUseNum;

    private BigDecimal totalConsumptionStere;

    private BigDecimal totalConsumptionMoney;

    private List<SwipingCardRecordVo> monthRecordWater;

    private PageUtils pageUtils;

    public Integer getTotalUseNum() {
        return totalUseNum;
    }
    public void setTotalUseNum(Integer totalUseNum) {
        this.totalUseNum = totalUseNum;
    }

    public BigDecimal getTotalConsumptionStere() {
        return totalConsumptionStere;
    }

    public void setTotalConsumptionStere(BigDecimal totalConsumptionStere) {
        this.totalConsumptionStere = totalConsumptionStere;
    }

    public BigDecimal getTotalConsumptionMoney() {
        return totalConsumptionMoney;
    }

    public void setTotalConsumptionMoney(BigDecimal totalConsumptionMoney) {
        this.totalConsumptionMoney = totalConsumptionMoney;
    }

    public List<SwipingCardRecordVo> getMonthRecordWater() {
        return monthRecordWater;
    }

    public void setMonthRecordWater(List<SwipingCardRecordVo> monthRecordWater) {
        this.monthRecordWater = monthRecordWater;
    }

    public PageUtils getPageUtils() {
        return pageUtils;
    }

    public void setPageUtils(PageUtils pageUtils) {
        this.pageUtils = pageUtils;
    }
}
