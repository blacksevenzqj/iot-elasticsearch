package com.thinkgem.elclient.utils;

import java.io.File;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static Date getDate(){
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static String getDateStr() {
        String separator = File.separator;
        String forMat = "yyyy" + separator + "MM" + separator + "dd";
        DateTimeFormatter dfor = DateTimeFormatter.ofPattern(forMat);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return zdt.format(dfor);
    }

    public static Date getDateByDouble(double value) {
        BigDecimal big = BigDecimal.valueOf(value);
        Date date = new Date(big.longValue());
        return date;
    }

    public static String getDateStrByDouble(double value) {
        BigDecimal big = BigDecimal.valueOf(value);
        Date date = new Date(big.longValue());

        String forMat = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dfor = DateTimeFormatter.ofPattern(forMat);
        ZoneId zoneId = ZoneId.systemDefault();

        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(zoneId);
        return zdt.format(dfor);
    }

    public static Long getTimeDifference(Date startTime, Date endTime){
        if(startTime != null && endTime != null) {

            ZoneId zoneId = ZoneId.systemDefault();

            Instant instantStart = startTime.toInstant();
            LocalDateTime localDateTimeStart = instantStart.atZone(zoneId).toLocalDateTime();

            Instant instantEnd = endTime.toInstant();
            LocalDateTime localDateTimeEnd = instantEnd.atZone(zoneId).toLocalDateTime();

            Duration duration = Duration.between(localDateTimeStart, localDateTimeEnd);
            return duration.toMillis();
        }else{
            return null;
        }
    }


}
