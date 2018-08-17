package com.thinkgem.elclient.controller;

import com.thinkgem.elclient.entity.Result;
import com.thinkgem.elclient.entity.swiping.SwipingCardRecordResult;
import com.thinkgem.elclient.service.SwipingCardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author zhaoqingjie
 * 刷卡记录
 */
@Controller
@RequestMapping("/swipingCardRecord/")
public class SwipingCardRecordConroller {

    @Autowired
    SwipingCardRecordService swipingCardRecordService;

    /**
     * 用水记录（月账单）
     * cardNo：为Null时，查询所有卡信息
     * selectDay：格式为 20180423
     */
    @ResponseBody
    @RequestMapping(value = "monthSelectRecordWater", method = RequestMethod.GET)
    public Result monthSelectRecordWater(String cardNo, String selectDay){
        SwipingCardRecordResult result = swipingCardRecordService.monthSelectRecordWater(cardNo, selectDay);
        return Result.success("result", result);
    }


    /**
     * 用水记录（水表清单）
     * cardNo：为Null时，查询所有卡信息
     * selectDay：格式为 20180423
     */
    @ResponseBody
    @RequestMapping(value = "listRecordWater", method = RequestMethod.GET)
    public Result listRecordWater(Integer pageNo, Integer pageSize, String cardNo, String selectDay){
        SwipingCardRecordResult result = swipingCardRecordService.listRecordWater(pageNo, pageSize, cardNo, selectDay);
        return Result.success("result", result);
    }


}
