package com.thinkgem.elclient.controller;


import com.thinkgem.elclient.entity.Result;
import com.thinkgem.elclient.entity.recharge.RechargeRecordResult;
import com.thinkgem.elclient.service.RechargeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author guyuqaio
 * 充值卡记录
 */
@Controller
@RequestMapping("/RechargeRecord")
public class RechargeRecordConroller {

    @Autowired
    RechargeRecordService rechargeCardRecordService;

    /**
     * 充值记录（月账单）
     * cardNo：为Null时，查询所有卡信息
     * selectDay：格式为 20180423
     */
    @ResponseBody
    @RequestMapping(value = "/monthSelectRechargeRecord", method = { RequestMethod.GET,RequestMethod.POST })
    public Result monthSelectRechargeRecord(String cardNo, String selectDay){
    	RechargeRecordResult result = rechargeCardRecordService.monthRechargeRecord(cardNo, selectDay);
        return Result.success("result", result);
    }


    /**
     * 充值记录（充值清单）
     * cardNo：为Null时，查询所有卡信息
     * selectDay：格式为 20180423
     */
    @ResponseBody
    @RequestMapping(value = "/listRechargeRecord", method = { RequestMethod.GET,RequestMethod.POST })
    public Result listRechargeRecord(Integer pageNo, Integer pageSize, String cardNo, String selectDay){
    	RechargeRecordResult result = rechargeCardRecordService.listRechargeRecord(pageNo, pageSize, cardNo, selectDay);
        return Result.success("result", result);
    }


}
