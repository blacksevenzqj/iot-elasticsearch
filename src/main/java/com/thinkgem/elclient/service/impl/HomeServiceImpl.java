package com.thinkgem.elclient.service.impl;

import com.thinkgem.elclient.dao.HomeDao;
import com.thinkgem.elclient.entity.home.MyWaterCardInfo;
import com.thinkgem.elclient.entity.home.RechargeMonth;
import com.thinkgem.elclient.entity.home.SwipingCardMonth;
import com.thinkgem.elclient.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoqingjie
 */
@Service(value = "homeService")
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeDao homeDao;

    @Override
    public List<MyWaterCardInfo> waterCardInfo(String loginName) {
        return homeDao.waterCardInfo(loginName);
    }

    @Override
    public List<RechargeMonth> threeMonthRecharge(String cardNo, String selectDay) {
    	List<RechargeMonth> list = homeDao.threeMonthRecharge(cardNo, selectDay);
        if(list.size() > 0){
            String[] temp = new String[3];
            try {
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                Calendar calendar = Calendar.getInstance();
                Date date = df.parse(selectDay);
                calendar.setTime(date);
                temp[2] = df.format(calendar.getTime()).substring(0, 6);
                for (int i = 1; i >= 0; i--) {
                    calendar.add(Calendar.MONTH, -1);// 月份减1  ;
                    temp[i] = df.format(calendar.getTime()).substring(0, 6);
                }
                RechargeMonth[] scms = new RechargeMonth[3];
                for(int i = 0; i < 3; i++){
                    for(RechargeMonth scm : list){
                        if(temp[i].equals(scm.getYearMonths())){
                            scms[i] = scm;
                        }
                    }
                }
                list = Arrays.asList(scms);
            }catch (Exception e){
                return null;
            }
        }
        return list;
    }

    @Override
    public List<SwipingCardMonth> threeMonthSwipingCard(String cardNo, String selectDay) {
        List<SwipingCardMonth> list = homeDao.threeMonthSwipingCard(cardNo, selectDay);
        if(list.size() > 0){
            String[] temp = new String[3];
            try {
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                Calendar calendar = Calendar.getInstance();
                Date date = df.parse(selectDay);
                calendar.setTime(date);
                temp[2] = df.format(calendar.getTime()).substring(0, 6);
                for (int i = 1; i >= 0; i--) {
                    calendar.add(Calendar.MONTH, -1);// 月份减1  ;
                    temp[i] = df.format(calendar.getTime()).substring(0, 6);
                }
                SwipingCardMonth[] scms = new SwipingCardMonth[3];
                for(int i = 0; i < 3; i++){
                    for(SwipingCardMonth scm : list){
                        if(temp[i].equals(scm.getYearMonths())){
                            scms[i] = scm;
                        }
                    }
                }
                list = Arrays.asList(scms);
            }catch (Exception e){
                return null;
            }
        }
        return list;
    }
}
