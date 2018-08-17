package com.thinkgem.elclient.dao;

import com.thinkgem.elclient.entity.home.MyWaterCardInfo;
import com.thinkgem.elclient.entity.home.RechargeMonth;
import com.thinkgem.elclient.entity.home.SwipingCardMonth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoqingjie
 */
public interface HomeDao {

    List<MyWaterCardInfo> waterCardInfo(@Param("loginName") String loginName);

    List<RechargeMonth> threeMonthRecharge(@Param("cardNo") String cardNo, @Param("selectDay") String selectDay);

    List<SwipingCardMonth> threeMonthSwipingCard(@Param("cardNo") String cardNo, @Param("selectDay") String selectDay);

}
