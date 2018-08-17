package com.thinkgem.elclient.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    }


}
