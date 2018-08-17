package com.thinkgem.elclient.service;


import com.thinkgem.elclient.entity.recharge.RechargeRecordResult;

/**
 * @author guyuqiao
 * 充值记录
 */
public interface RechargeRecordService {

    RechargeRecordResult monthRechargeRecord(String cardNo, String selectDay);

    RechargeRecordResult listRechargeRecord(Integer pageNo, Integer pageSize, String cardNo, String selectDay);

}
