package com.thinkgem.elclient.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.utils.DateUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

//        String[] temp = new String[6];
//        DateFormat df = new SimpleDateFormat("yyyyMMdd");
//        Calendar calendar = Calendar.getInstance();
//        Date date = df.parse("20180423");
//        calendar.setTime(date);
//
//        temp[0] = df.format(calendar.getTime()).substring(0, 6);
//        for(int i = 1; i < 6; i++) {
//            calendar.add(Calendar.MONTH, -1);// 月份减1  ;
////            System.out.println(df.format(calendar.getTime()));
//            temp[i] = df.format(calendar.getTime()).substring(0, 6);
//        }
//
//        System.out.println("........................");
//
//        for(String s : temp){
//            System.out.println(s);
//        }
//
//        double d = 88.88;
//        long l = Math.round(d);
//        System.out.println(l);
//
//
//        double e = 1.532167413E12;
//        System.out.println(String.valueOf(e));
//        long ll = Math.round(e);
//        System.out.println(ll);
//
//        long ooo = (long)e;
//        System.out.println(ooo);
//
//        BigDecimal big = BigDecimal.valueOf(1.532167413E12);
//        String str = big.toString();
//        System.out.println(str);
//        System.out.println(big.longValue());
//        Date date2 = new Date(big.longValue());
//        System.out.println(date2);
//
//        System.out.println(DateUtils.getDateStrByDouble(e));


// 1533735045000
        // 1533706245000

//        String dateTimeStr = "2018-08-08 13:30:45";
//        DateTimeFormatter df111 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, df111);
//        long kkk = dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();   // 1533706245000
////        long kkk = dateTime.toInstant(ZoneOffset.of("+0")).toEpochMilli();
//        System.out.println(kkk);
//        System.out.println(new Date(kkk));


        // 原始的
        // 字符串 转 时区：
//        String dateTimeStr2 = "2018-08-08 13:30:45";
//        DateFormat df1112 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        df1112.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));    // 1533706245000
////        df1112.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));    // 1533735045000
//        Date date222 = df1112.parse(dateTimeStr2); // 默认按照系统时区写(东八区) 1533706245000
//        System.out.println(date222.getTime());
//        System.out.println(date222);
//
//        SimpleDateFormat sdf_8 = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss.SSSZ");
//        System.out.println(sdf_8.format(date222));
//        sdf_8.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//        System.out.println(sdf_8.format(date222));



//        String dateTimeStr2 = "2018-08-08 13:30:45";
//        System.out.println(dateTimeStr2);
//        DateFormat df1112 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        df1112.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));    // 1533706245000
//        df1112.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));      // 1533735045000
//        Date date222 = df1112.parse(dateTimeStr2); // 默认按照系统时区写(东八区) 1533706245000
//        System.out.println(date222.getTime());
//        System.out.println(date222);
//
//        SimpleDateFormat sdf_8 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf_8.format(date222));
//        sdf_8.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
//        System.out.println(sdf_8.format(date222));


        // Long值 转 时区：
//        Long date333 = 1533735045000L;  //UTC时区Long值
//        Long date333 = 1533706245000L;
//        Date date = new Date(date333);
//        System.out.println(date);
//
//        DateFormat df1112 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        df1112.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
//        String str = df1112.format(date);
//        System.out.println(str);


//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        df.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
//        Date date1 = new Date(1533513600000L);
//        Date date2 = new Date(1533484800000L);
//        System.out.println(df.format(date1));
//        System.out.println(df.format(date2));
//
//        BigDecimal utcBigDecimal = BigDecimal.valueOf(1533513600000L);
//        BigDecimal d8BigDecimal = BigDecimal.valueOf(1533484800000L);
//        BigDecimal result = utcBigDecimal.subtract(d8BigDecimal);
//        result = result.divide(BigDecimal.valueOf(1000 * 60 * 60));
//        System.out.println(result.toString() + "小时");


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
        Date date1 = new Date(1533513600000L);
        Date date2 = new Date(1533513600000L);
        System.out.println(df.format(date1));
        System.out.println(df.format(date2));

        BigDecimal utcBigDecimal = BigDecimal.valueOf(1533542400000L);
        BigDecimal d8BigDecimal = BigDecimal.valueOf(1533513600000L);
        BigDecimal result = utcBigDecimal.subtract(d8BigDecimal);
        result = result.divide(BigDecimal.valueOf(1000 * 60 * 60));
        System.out.println(result.toString() + "小时");

    }

}
