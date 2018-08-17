package com.thinkgem.elclient.entity.swiping;

import java.math.BigDecimal;

/**
 * @author zhaoqingjie
 */
public class SwipingCardRecordVo {

    private String cardNo;

    private Integer year;

    private Integer month;

    private String yearMonths;

    private BigDecimal consumptionStere;

    private BigDecimal consumptionMoney;

    private Integer useNum;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public Integer getUseNum() {
        return useNum;
    }
    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public String getYearMonths() {
        return yearMonths;
    }

    public void setYearMonths(String yearMonths) {
        this.yearMonths = yearMonths;
    }

    @Override
    public String toString() {
        return "SwipingCardRecordVo{" +
                "cardNo='" + cardNo + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", yearMonths='" + yearMonths + '\'' +
                ", consumptionStere=" + consumptionStere +
                ", consumptionMoney=" + consumptionMoney +
                ", useNum=" + useNum +
                '}';
    }
}
