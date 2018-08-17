package com.thinkgem.elclient.service;

import com.thinkgem.elclient.entity.swiping.SwipingCardRecordResult;

/**
 * @author zhaoqingjie
 * 刷卡记录
 */
public interface SwipingCardRecordService {

    SwipingCardRecordResult monthSelectRecordWater(String cardNo, String selectDay);

    SwipingCardRecordResult listRecordWater(Integer pageNo, Integer pageSize, String cardNo,  String selectDay);

}
