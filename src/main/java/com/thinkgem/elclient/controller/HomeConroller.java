package com.thinkgem.elclient.controller;

import com.thinkgem.elclient.entity.Result;
import com.thinkgem.elclient.entity.home.MyWaterCardInfo;
import com.thinkgem.elclient.entity.home.RechargeMonth;
import com.thinkgem.elclient.entity.home.SwipingCardMonth;
import com.thinkgem.elclient.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

/**
 * @author zhaoqingjie
 * 首页
 */
@Controller
@RequestMapping("/homeConroller/")
public class HomeConroller {

    @Autowired
    HomeService homeService;

    /**
     * 首页（我的水卡）
     * loginName：农户登录账户
     */
    @ResponseBody
    @RequestMapping(value = "waterCardInfo", method = RequestMethod.GET)
    public Result waterCardInfo(String loginName){
        List<MyWaterCardInfo> list = homeService.waterCardInfo(loginName);
        return Result.success("result", list);
    }

    /**
     * 首页（近三月充值记录）
     * cardNo：水卡号
     * selectDay：格式为 20180423
     */
    @ResponseBody
    @RequestMapping(value = "threeMonthRecharge", method = RequestMethod.GET)
    public Result threeMonthRecharge(String cardNo, String selectDay){
        List<RechargeMonth> list = homeService.threeMonthRecharge(cardNo, selectDay);
        return Result.success("result", list);
    }

    /**
     * 首页（近三月用水记录）
     * cardNo：水卡号
     * selectDay：格式为 20180423
     */
    @ResponseBody
    @RequestMapping(value = "threeMonthSwipingCard", method = RequestMethod.GET)
    public Result threeMonthSwipingCard(String cardNo, String selectDay){
        List<SwipingCardMonth> list = homeService.threeMonthSwipingCard(cardNo, selectDay);
        return Result.success("result", list);
    }

}
