package com.thinkgem.elclient.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.utils.DateUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception{

//        System.out.println("BillClient".length());
//
//
//        System.out.println(LocalDate.now().toString());
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//
//        System.out.println(LocalDate.parse("2018-04-23").isEqual(LocalDate.now()));
//
//        LoginResponse loginResponse = new LoginResponse();
//        BaseResponse baseResponse = BaseResponseUtil.success(loginResponse);
//        System.out.println(JSON.toJSON(baseResponse).toString());


//        BigDecimal totalConsumptionStere = new BigDecimal(0.00);
//        BigDecimal totalConsumptionMoney = new BigDecimal(0.00);
//        totalConsumptionStere = totalConsumptionStere.add(new BigDecimal(3.30));
//        System.out.println(totalConsumptionStere);
        String[] temp = new String[6];
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date date = df.parse("20180423");
        calendar.setTime(date);

        temp[0] = df.format(calendar.getTime()).substring(0, 6);
        for(int i = 1; i < 6; i++) {
            calendar.add(Calendar.MONTH, -1);// 月份减1  ;
//            System.out.println(df.format(calendar.getTime()));
            temp[i] = df.format(calendar.getTime()).substring(0, 6);
        }

        System.out.println("........................");

        for(String s : temp){
            System.out.println(s);
        }

        double d = 88.88;
        long l = Math.round(d);
        System.out.println(l);


        double e = 1.532167413E12;
        System.out.println(String.valueOf(e));
        long ll = Math.round(e);
        System.out.println(ll);

        long ooo = (long)e;
        System.out.println(ooo);

        BigDecimal big = BigDecimal.valueOf(1.532167413E12);
        String str = big.toString();
        System.out.println(str);
        System.out.println(big.longValue());
        Date date2 = new Date(big.longValue());
        System.out.println(date2);

        System.out.println(DateUtils.getDateStrByDouble(e));


        Date date3 = new Date(1532167413000L);
        System.out.println(date3);

    }

}
