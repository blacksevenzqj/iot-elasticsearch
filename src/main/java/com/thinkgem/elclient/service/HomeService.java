package com.thinkgem.elclient.service;

import com.thinkgem.elclient.entity.home.MyWaterCardInfo;
import com.thinkgem.elclient.entity.home.RechargeMonth;
import com.thinkgem.elclient.entity.home.SwipingCardMonth;

import java.util.List;

/**
 * @author zhaoqingjie
 */
public interface HomeService {

    List<MyWaterCardInfo> waterCardInfo(String loginName);

    List<RechargeMonth> threeMonthRecharge(String cardNo, String selectDay);

    List<SwipingCardMonth> threeMonthSwipingCard(String cardNo, String selectDay);

}
