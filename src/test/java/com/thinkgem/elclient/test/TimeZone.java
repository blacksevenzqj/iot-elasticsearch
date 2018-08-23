package com.thinkgem.elclient.test;

import com.thinkgem.elclient.utils.DateUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeZone {

    public static void main(String[] args) throws Exception{
//        normalStr();
        normalLong();
//        j8_Str();
        j8_Long();
    }


    private static void normalStr() throws Exception{

        /**
         * 1、以 慢的时区为 基准：
         * UTC时间2018-08-08 13:30:45对应的毫秒数是  1533735045000，
         * 东八区时间2018-08-08 13:30:45对应的毫秒数是 1533706245000，对应UTC时间其实是2018-08-08 05:30:45（因为东八区时间比UTC时间快8小时，所以东八区时间要减8个小时）
         * 减了8小时的UTC时间2018-08-08 05:30:45 自然比 UTC时间2018-08-08 13:30:45 少8 * 3600000毫秒。
         * 理解：东八区时间要走到2018-08-08 13:30:45，肯定要比 UTC时间少走8个小时。
         *
         * 2、以 快的时区为 基准：
         * 东八区时间2018-08-08 13:30:45对应的毫秒数是 1533706245000，
         * UTC时间2018-08-08 13:30:45对应的毫秒数是  1533735045000，对应东八区时间其实是2018-08-08 21:30:45（因为UTC时间比东八区时间晚8小时，所以UTC要加8个小时）
         * 加了8小时的东八区时间2018-08-08 21:30:45 自然比 东八区时间2018-08-08 13:30:45 多 8 * 3600000毫秒。
         * 理解：UTC时间要走到2018-08-08 13:30:45，肯定要比 东八区时间 多走8个小时。
         * */

        String timeStr = "2018-08-08 13:30:45"; // 字面时间

        // 1533706245000
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bjSdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+8:00"));
        Date bjDate = bjSdf.parse(timeStr);  // 解析
        System.out.println("字面时间: " + timeStr +",按东八区时间来解释:" + bjSdf.format(bjDate) + ", " + bjDate.getTime() + ", " + bjDate);
        SimpleDateFormat sdf_8 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        sdf_8.setTimeZone(java.util.TimeZone.getTimeZone("GMT+8:00"));
        System.out.println("时区时间: " + sdf_8.format(bjDate));

        // 1533735045000
        SimpleDateFormat tokyoSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // UTC
        tokyoSdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+0:00"));  // 设置UTC时区
        Date tokyoDate = tokyoSdf.parse(timeStr); // 解析
        System.out.println("字面时间: " + timeStr +",按UTC时间来解释:"  + tokyoSdf.format(tokyoDate) + ", " + tokyoDate.getTime() + ", " + tokyoDate);
        SimpleDateFormat sdf_utc = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        sdf_utc.setTimeZone(java.util.TimeZone.getTimeZone("GMT+0:00"));
        System.out.println("时区时间: " + sdf_utc.format(tokyoDate));

        // 相差8个小时
        BigDecimal utcBigDecimal = BigDecimal.valueOf(1533735045000L);
        BigDecimal d8BigDecimal = BigDecimal.valueOf(1533706245000L);
        BigDecimal result = utcBigDecimal.subtract(d8BigDecimal);
        result = result.divide(BigDecimal.valueOf(1000 * 60 * 60));
        System.out.println(result.toString() + "小时");
    }

    private static void normalLong() throws Exception{
        Long timeLong = 1533735045000L;  // UTC时区的Long值，相比东八区时区加了8个小时的 UTC Long值：相同对应 "2018-08-08 13:30:45"
//        Long timeLong = 1533706245000L;    // 东八区时区 Long值：相同对应 "2018-08-08 13:30:45"

        Date date = new Date(timeLong);

        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 东八区
        bjSdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+8:00"));  // 设置东八区时区
        System.out.println("毫秒数:" + date.getTime() + ", 东八区时间:" + bjSdf.format(date));

        SimpleDateFormat tokyoSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // UTC
        tokyoSdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+0:00"));  // 设置UTC时区
        System.out.println("毫秒数:" + date.getTime() + ", UTC时间:" + tokyoSdf.format(date));
    }


    private static void j8_Str() throws Exception{
        String timeStr = "2018-08-08 13:30:45";

        DateTimeFormatter df111 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(timeStr, df111);
        Long longTime = dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        ZonedDateTime zdt = dateTime.atZone(ZoneId.of("+8"));
        Date date = Date.from(zdt.toInstant());
        System.out.println("字面时间: " + timeStr +",按东八区时间来解释:" + dateTime.format(df111) + ", " + longTime + ", " + date);
        System.out.println("时区时间: " + zdt.toString() + "___" + zdt.format(df111) + "___" + zdt.toInstant());

        DateTimeFormatter df222 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime2 = LocalDateTime.parse(timeStr, df222);
        Long longTime2 = dateTime2.toInstant(ZoneOffset.of("+0")).toEpochMilli();
        ZonedDateTime zdt2 = dateTime2.atZone(ZoneId.of("+0"));
        Date date2 = Date.from(zdt2.toInstant());
        System.out.println("字面时间: " + timeStr +",按UTC时间来解释:" + dateTime2.format(df222) + ", " + longTime2 + ", " + date2);
        System.out.println("时区时间: " + zdt2.toString() + "___" + zdt2.format(df222) + "___" + zdt2.toInstant());
    }


    private static void j8_Long() throws Exception {
        Long timeLong = 1533735045000L;  // UTC时区的Long值，相比东八区时区加了8个小时的 UTC Long值：相同对应 "2018-08-08 13:30:45"
//        Long timeLong = 1533706245000L;    // 东八区时区 Long值：相同对应 "2018-08-08 13:30:45"

        Date date = new Date(timeLong);
        DateTimeFormatter df111 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Instant instant = date.toInstant();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("+8"));  // 设置东八区时区
        System.out.println("毫秒数:" + date.getTime() + ", 东八区时间:" + dateTime.format(df111));
        ZonedDateTime zdt = instant.atZone(ZoneId.of("+8"));
        System.out.println("ZonedDateTime毫秒数:" + date.getTime() + ", 东八区时间:" + zdt.format(df111));

        Instant instant2 = date.toInstant();
        LocalDateTime dateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.of("+0"));  // 设置UTC时区
        System.out.println("毫秒数:" + date.getTime() + ", UTC时间:" + dateTime2.format(df111));
        ZonedDateTime zdt2 = instant2.atZone(ZoneId.of("+0"));
        System.out.println("ZonedDateTime毫秒数:" + date.getTime() + ", UTC时间:" + zdt2.format(df111));


        System.out.println(DateUtils.getDateStrByUtcDouble(1533735045000L));

    }

}
