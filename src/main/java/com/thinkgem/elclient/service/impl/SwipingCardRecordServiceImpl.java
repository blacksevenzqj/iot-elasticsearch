package com.thinkgem.elclient.service.impl;

import com.thinkgem.elclient.dao.SwipingCardRecordDao;
import com.thinkgem.elclient.entity.swiping.SwipingCardRecordList;
import com.thinkgem.elclient.entity.swiping.SwipingCardRecordResult;
import com.thinkgem.elclient.entity.swiping.SwipingCardRecordVo;
import com.thinkgem.elclient.service.SwipingCardRecordService;
import com.thinkgem.elclient.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoqingjie
 * 刷卡记录
 */
@Service(value = "swipingCardRecordService")
public class SwipingCardRecordServiceImpl implements SwipingCardRecordService {

    @Autowired
    SwipingCardRecordDao swipingCardRecordDao;


    // 按月累加，不指定 月份。
//    @Override
//    public SwipingCardRecordResult monthRecordWater(String cardNo, String year) {
//        SwipingCardRecordResult result = new SwipingCardRecordResult();
//        List<SwipingCardRecordVo> list = swipingCardRecordDao.monthRecordWater(cardNo, year, null);
//        if(list.size() > 0) {
//            SwipingCardRecordVo[] svs = new SwipingCardRecordVo[12];
//            BigDecimal totalConsumptionStere = new BigDecimal(0.00);
//            BigDecimal totalConsumptionMoney = new BigDecimal(0.00);
//            Integer useNum = 0;
//            for (SwipingCardRecordVo sv : list) {
//                svs[sv.getMonth() - 1] = sv;
//                totalConsumptionStere = totalConsumptionStere.add(sv.getConsumptionStere());
//                totalConsumptionMoney = totalConsumptionMoney.add(sv.getConsumptionMoney());
//                useNum += sv.getUseNum();
//            }
//            result.setTotalConsumptionStere(totalConsumptionStere);
//            result.setTotalConsumptionMoney(totalConsumptionMoney);
//            result.setTotalUseNum(useNum);
//            result.setMonthRecordWater(Arrays.asList(svs));
//        }
//        return result;
//    }


    // 按月累加：默认所有卡、指定卡
    @Override
    public SwipingCardRecordResult monthSelectRecordWater(String cardNo, String selectDay) {
        SwipingCardRecordResult result = new SwipingCardRecordResult();
        List<SwipingCardRecordVo> list = null;
        if(StringUtils.isNotBlank(cardNo)){
            list = swipingCardRecordDao.monthSelectRecordWater(cardNo, selectDay);
        }else{
            list = swipingCardRecordDao.monthSelectAllRecordWater(selectDay);
        }
        if(list.size() > 0) {
            String[] temp = new String[6];
            try {
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                Calendar calendar = Calendar.getInstance();
                Date date = df.parse(selectDay);
                calendar.setTime(date);
                temp[0] = df.format(calendar.getTime()).substring(0, 6);
                for(int i = 1; i < 6; i++) {
                    calendar.add(Calendar.MONTH, -1);// 月份减1  ;
                    temp[i] = df.format(calendar.getTime()).substring(0, 6);
                }
                SwipingCardRecordVo[] svs = new SwipingCardRecordVo[6];
                BigDecimal totalConsumptionStere = new BigDecimal(0.00);
                BigDecimal totalConsumptionMoney = new BigDecimal(0.00);
                Integer useNum = 0;
                for(int i =0; i < 6; i++){
                    for(SwipingCardRecordVo sv : list){
                        if(temp[i].equals(sv.getYearMonths())){
                            svs[i] = sv;
                            totalConsumptionStere = totalConsumptionStere.add(sv.getConsumptionStere());
                            totalConsumptionMoney = totalConsumptionMoney.add(sv.getConsumptionMoney());
                            useNum += sv.getUseNum();
                        }
                    }
                }
                result.setTotalConsumptionStere(totalConsumptionStere);
                result.setTotalConsumptionMoney(totalConsumptionMoney);
                result.setTotalUseNum(useNum);
                result.setMonthRecordWater(Arrays.asList(svs));
            }catch (Exception e){
                return result;
            }
        }
        return result;
    }


    @Override
    public SwipingCardRecordResult listRecordWater(Integer pageNo, Integer pageSize, String cardNo, String selectDay) {
        selectDay = selectDay.substring(0, 6);
        SwipingCardRecordResult result = new SwipingCardRecordResult();
        SwipingCardRecordVo sCrv;
        if(StringUtils.isNotBlank(cardNo)){
           sCrv = swipingCardRecordDao.monthRecordWater(cardNo, selectDay);
        }else{
            sCrv = swipingCardRecordDao.monthAllRecordWater(selectDay);
        }
        if(sCrv != null) {
            result.setTotalConsumptionStere(sCrv.getConsumptionStere());
            result.setTotalConsumptionMoney(sCrv.getConsumptionMoney());
            result.setTotalUseNum(sCrv.getUseNum());

            List<SwipingCardRecordList> list;
            pageNo = pageNo == null ? 1 : pageNo;
            pageSize = pageSize == null ? 10 : pageSize;
            PageHelper.startPage(pageNo, pageSize);
            if(StringUtils.isNotBlank(cardNo)){
                list = swipingCardRecordDao.listRecordWater(cardNo, selectDay);
            }else{
                list = swipingCardRecordDao.listAllRecordWater(selectDay);
            }
            if (list.size() > 0) {
                PageInfo<SwipingCardRecordList> pageInfo = new PageInfo<SwipingCardRecordList>(list);
                PageUtils<SwipingCardRecordList> pageUtils = new PageUtils<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
                result.setPageUtils(pageUtils);
            }
        }
        return result;
    }


}
