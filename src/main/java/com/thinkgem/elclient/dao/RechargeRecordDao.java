package com.thinkgem.elclient.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.elclient.entity.recharge.RechargeRecordVo;


public interface RechargeRecordDao {
	RechargeRecordVo monthRechargeRecord(@Param("cardNo") String cardNo, @Param("selectDay") String selectDay);

	RechargeRecordVo monthAllRechargeRecord(@Param("selectDay") String selectDay);

    List<RechargeRecordVo> monthSelectRechargeRecord(@Param("cardNo") String cardNo, @Param("selectDay") String selectDay);

    List<RechargeRecordVo> monthSelectAllRechargeRecord(@Param("selectDay") String selectDay);

    List<RechargeRecordVo> listRechargeRecord(@Param("cardNo")String cardNo, @Param("selectDay") String selectDay);

    List<RechargeRecordVo> listAllRechargeRecord(@Param("selectDay") String selectDay);
}
