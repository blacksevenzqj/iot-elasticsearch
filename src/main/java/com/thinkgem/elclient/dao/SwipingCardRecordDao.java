package com.thinkgem.elclient.dao;

import com.thinkgem.elclient.entity.swiping.SwipingCardRecordList;
import com.thinkgem.elclient.entity.swiping.SwipingCardRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoqingjie
 */
public interface SwipingCardRecordDao {

    SwipingCardRecordVo monthRecordWater(@Param("cardNo") String cardNo, @Param("selectDay") String selectDay);

    SwipingCardRecordVo monthAllRecordWater(@Param("selectDay") String selectDay);

    List<SwipingCardRecordVo> monthSelectRecordWater(@Param("cardNo") String cardNo, @Param("selectDay") String selectDay);

    List<SwipingCardRecordVo> monthSelectAllRecordWater(@Param("selectDay") String selectDay);

    List<SwipingCardRecordList> listRecordWater(@Param("cardNo")String cardNo, @Param("selectDay") String selectDay);

    List<SwipingCardRecordList> listAllRecordWater(@Param("selectDay") String selectDay);


}
