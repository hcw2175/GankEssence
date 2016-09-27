/**
 * Copyright (c) 2015 - 广州小橙信息科技有限公司 
 * All rights reserved.
 *
 * Created on 2015-10-14
 */
package com.huchiwei.gankessence.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author CD826
 * @author hucw
 * @since 1.0.0
 */
public class DateUtil {
    /**
     * 从日期转换为整型数据
     *
     * @param date 目标日期
     * @return int
     */
    public static int toInt(Date date){
        return null==date ? 0 : (int) (date.getTime()/1000);
    }

    /**
     * 从整型转换为日期
     *
     * @param date 目标日期
     * @return date
     */
    public static Date toDate(int date){
        return date > 0 ? new Date(((long)date)*1000L) : null;
    }

    /**
     * 从整型转换为日期(字符串)
     *
     * @param date 目标日期
     * @return string
     */
    public static String toDateStr(int date){
        return date > 0 ? formatDate(toDate(date)) : "";
    }

    // constants ==============================================================
    // 定义时间单位
    public static final String TIMEUNIT_MINUTE      = "M";          // 时间单位:分钟
    public static final String TIMEUNIT_HOUR        = "H";          // 时间单位:小时
    public static final String TIMEUNIT_DAY         = "D";          // 时间单位:天

    // 定义周期单位
    public static final String PERIODUNIT_MINUTE    = "minute";     // 周期单位：分钟
    public static final String PERIODUNIT_HOUR      = "hour";       // 周期单位：小时
    public static final String PERIODUNIT_DAY       = "day";        // 周期单位：天
    public static final String PERIODUNIT_WEEK      = "week";       // 周期单位：周
    public static final String PERIODUNIT_MONTH     = "month";      // 周期单位：月
    public static final String PERIODUNIT_YEAR      = "year";       // 周期单位：年

    /**
     * 取得当前日期，不含时间
     * @return 当前日期，格式为：yyyy-MM-dd
     */
    public static String curDate() {
        return formatDate(new Date());
    }

    /**
     * 取得当前时间
     * @return 当前时间，格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String curDateTime() {
        return format(new Date());
    }

    /**
     * 取得当前时间
     * @return 当前时间，格式为：yyyy-MM-dd HH:mm
     */
    public static String curDateTime2Min() {
        return formatDateTime2Minute(new Date());
    }

    /**
     * 格式化日期的表示
     * @param date 所要格式化的日期
     * @return 返回以yyyy-MM-dd格式表示的日期
     */
    public static String formatDate(Date date) {
        if (null == date)
            return "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 格式化时间的表示
     * @param dateTime 所要格式化的时间
     * @return 返回以yyyy-MM-dd HH:mm:ss格式表示的时间
     */
    public static String format(Date dateTime) {
        return format(dateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化时间的表示，格式化为ISO 8601的表示方式
     * @param dateTime 所要格式化的时间
     * @return 返回格式为：yyyy-MM-ddTHH:mm:ssZ的格式，如：2008-03-01T13:00:00Z
     */
    public static String formatDateTime4ISO8601(Date dateTime){
        String date = format(dateTime, "yyyy-MM-dd");
        date += "T" + format(dateTime, "HH:mm:ss") + "Z";
        return date;
    }

    /**
     * 格式化时间的表示
     * @param dateTime 所要格式化的时间
     * @return 返回以HH:mm格式表示的时间
     */
    public static String formatTime(Date dateTime) {
        return format(dateTime, "HH:mm");
    }

    /**
     * 格式化时间的表示
     * @param dateTime 所要格式化的时间
     * @return 返回以yyyy-MM-dd HH:mm格式表示的时间
     */
    public static String formatDateTime2Minute(Date dateTime) {
        return format(dateTime, "yyyy-MM-dd HH:mm");
    }

    /**
     * 将时间格式为指定的格式
     * @param dateTime 所要格式化的时间
     * @param format 格式化的格式
     * @return 返回以指定格式表示的时间
     */
    public static String format(Date dateTime, String format) {
        if (null == dateTime)
            return "";
        DateFormat df = new SimpleDateFormat(format);
        return df.format(dateTime);
    }

    /**
     * 构建ISO8601的intervals
     * @param timeUnit 时间单位
     * @param interval 频度
     * @return
     */
    public static String buildIntervals4ISO8601(String timeUnit, int interval){
        String intervals = "P";
        if(PERIODUNIT_YEAR.equalsIgnoreCase(timeUnit)){
            intervals += String.valueOf(interval) + "Y";
        }else{
            intervals += "0Y";
        }
        if(PERIODUNIT_MONTH.equalsIgnoreCase(timeUnit)){
            intervals += String.valueOf(interval) + "M";
        }else{
            intervals += "0M";
        }
        if(PERIODUNIT_DAY.equalsIgnoreCase(timeUnit)){
            intervals += String.valueOf(interval) + "D";
        }else{
            intervals += "0D";
        }
        intervals += "T";
        if(PERIODUNIT_HOUR.equalsIgnoreCase(timeUnit)){
            intervals += String.valueOf(interval) + "H";
        }else{
            intervals += "0H";
        }
        if(PERIODUNIT_MINUTE.equalsIgnoreCase(timeUnit)){
            intervals += String.valueOf(interval) + "M";
        }else{
            intervals += "0M";
        }
        return intervals;
    }

    /**
     * 构建ISO8601的intervals
     * @param years
     * @param months
     * @param days
     * @param hours
     * @param minutes
     * @return
     */
    public static String buildIntervals4ISO8601(int years, int months, int days, int hours, int minutes){
        String intervals = "P";
        intervals += String.valueOf(years) + "Y";
        intervals += String.valueOf(months) + "M";
        intervals += String.valueOf(days) + "D";
        intervals += "T";
        intervals += String.valueOf(hours) + "H";
        intervals += String.valueOf(minutes) + "M";
        return intervals;
    }

    /**
     * 获取当前年度的值
     * @return 年度值
     */
    public static String curYear(){
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    /**
     * 获取年度的值
     * @return 年度值，如果时间为空则返回0
     */
    public static int year(Date dateTime){
        if (null == dateTime)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月度的值
     * @return 月度值
     */
    public static String curMonth(){
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.MONTH));
    }

    /**
     * 获取月度的值
     * @return 月度值，如果时间为空则返回0
     */
    public static int month(Date dateTime){
        if (null == dateTime)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取天数的值
     * @return 天数值，如果时间为空则返回0
     */
    public static int day(Date dateTime){
        if (null == dateTime)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取天数的值，在一年当中
     * @return 天数值，如果时间为空则返回0
     */
    public static int dayOfYear(Date dateTime){
        if (null == dateTime)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取天数的值，在一周当中
     * @return 天数值，如果时间为空则返回0
     */
    public static int dayOfWeek(Date dateTime){
        if (null == dateTime)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取本周的起始日期，周一为第一天，周日为最后一天
     * @return
     */
    public static Date[] curWeekDates(){
        Date[] dates = new Date[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            calendar.add(Calendar.DATE, -6);
        }else{
            calendar.add(Calendar.DATE, -calendar.get(Calendar.DAY_OF_WEEK) + 2);
        }
        dates[0] = calendar.getTime();
        calendar.add(Calendar.DATE, 6);
        dates[1] = calendar.getTime();
        return dates;
    }

    /**
     * 获取下周的起始日期，周一为第一天，周日为最后一天
     * @return
     */
    public static Date[] nextWeekDates(){
        return getNthWeekDates(1);
    }

    /**
     * 获取上周的起始日期，周一为第一天，周日为最后一天
     * @return
     */
    public static Date[] lastWeekDates(){
        return getNthWeekDates(-1);
    }

    /**
     * 获取距离本周N周的周起始日期及结束日期，周一为第一天，周日为最后一天
     * @return
     */
    public static Date[] getNthWeekDates(int offset){
        Date[] dates = curWeekDates();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dates[0]);
        calendar.add(Calendar.DATE, 7 * offset);
        dates[0] = calendar.getTime();
        calendar.setTime(dates[1]);
        calendar.add(Calendar.DATE, 7 * offset);
        dates[1] = calendar.getTime();
        return dates;
    }

    /**
     * 获取两个日期之间相差的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDiffDays(Date startDate, Date endDate){
        if (null == startDate || null == endDate)
            return 0;
        boolean isNegative = false;
        if(startDate.after(endDate)){
            isNegative = true;
            Date tmpDate = startDate;
            startDate = endDate;
            endDate = tmpDate;
        }
        long startTimes = startDate.getTime();
        long endTimes = endDate.getTime();
        long days = (endTimes - startTimes)/(24 * 60 * 60 * 1000);
        if((days * 24 * 60 * 60 * 1000) < (endTimes - startTimes))
            days++;

        if(isNegative)
            return -days;
        else
            return days;
    }

    /**
     * 获取两个日期之间相差时间的字符串，如：3天4小时5分23秒
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param daySymbol 天符号格式,默认为d
     * @param hourSymbol 小时符号格式,默认为h
     * @param minuteSymbol 分钟符号格式,默认为m
     * @param secondSymbol 秒符号格式,默认为s
     * @return
     */
    public static String getDiffString(Date startDate, Date endDate, String daySymbol,
                                       String hourSymbol, String minuteSymbol, String secondSymbol){
        if (null == startDate || null == endDate)
            return "";

        long startTimes = startDate.getTime();
        long endTimes = endDate.getTime();
        long diff = endTimes - startTimes;

        long day = diff/(24*60*60*1000);
        long hour = (diff/(60*60*1000)-day*24);
        long min = ((diff/(60*1000))-day*24*60-hour*60);
        long s = (diff/1000-day*24*60*60-hour*60*60-min*60);

        if(null == daySymbol || daySymbol.equalsIgnoreCase(""))
            daySymbol = "d";
        if(null == hourSymbol || hourSymbol.equalsIgnoreCase(""))
            hourSymbol = "h";
        if(null == minuteSymbol || minuteSymbol.equalsIgnoreCase(""))
            minuteSymbol = "m";
        if(null == secondSymbol || secondSymbol.equalsIgnoreCase(""))
            secondSymbol = "s";

        String diffStr = "";
        if(day != 0)
            diffStr  += day + daySymbol;
        if(day != 0 || hour != 0)
            diffStr  += hour + hourSymbol;
        if(day != 0 || hour != 0 || min != 0)
            diffStr  += min + minuteSymbol;
        diffStr  += s + secondSymbol;

        return diffStr;
    }

    /**
     * 取得指定时间中的小时数
     * @param dateTime 时间
     * @return 小时数，如果时间为空则返回0
     */
    public static int hour(Date dateTime) {
        if (null == dateTime)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 取得指定日期中的分钟数
     * @param dateTime 时间
     * @return 分钟数，如果时间为空则返回0
     */
    public static int minute(Date dateTime) {
        if (null == dateTime)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 指定的字符串转换成Date，转换时必须是一下格式，否则将转换失败并返回null<br>
     * <DIR>
     * 	<LI> yyyy-MM-dd
     *  <LI> yyyy-MM-dd HH:mm
     *  <LI> yyyy-MM-dd HH:mm:ss
     * </DIR>
     * @param dateTime 所要转换的时间
     */
    public static Date parseDate(String dateTime) {
        if(null == dateTime || dateTime.length() == 0)
            return null;
        DateFormat df = null;
        if(dateTime.length() == "yyyy-MM-dd HH:mm:ss.S".length()){
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        }else if(dateTime.length() == "yyyy-MM-dd HH:mm:ss".length()){
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }else if(dateTime.length() == "yyyyMMddHHmmss".length()){
            df = new SimpleDateFormat("yyyyMMddHHmmss");
        }else if(dateTime.length() == "yyyy-MM-dd HH:mm".length()){
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }else if(dateTime.length() == "yyyy-MM-dd".length()){
            df = new SimpleDateFormat("yyyy-MM-dd");
        }else if(dateTime.length() == "yyyyMMdd".length()){
            df = new SimpleDateFormat("yyyyMMdd");
        }else{
            df = new SimpleDateFormat("yyyy-M-d");
        }

        try {
            return df.parse(dateTime);
        } catch (ParseException pe) {
            return null;
        }
    }

    /**
     * 将字符串数组解析成Date数组
     * @param dateStrings 所要解析的字符串数组
     * @return
     */
    public static Date[] parseDates(String[] dateStrings){
        if(null == dateStrings || dateStrings.length == 0)
            return null;
        Date[] dates = new Date[dateStrings.length];
        for(int i = 0; i < dateStrings.length; i++){
            dates[i] = parseDate(dateStrings[i]);
        }
        return dates;
    }

    /**
     * 将指定时间转换成一天的起始时间yyyy-MM-dd 00:00:00
     * @param dateTime 指定时间
     * @return 转换后的时间
     */
    public static Date getStartDate(Date dateTime){
        return getDate(dateTime, true);
    }

    /**
     * 将指定时间转换成一天的结束时间yyyy-MM-dd 23:59:59)
     * @param dateTime 指定时间
     * @return 转换后的时间
     */
    public static Date getEndDate(Date dateTime){
        return getDate(dateTime, false);
    }

    /**
     * 将指定时间转换成一天的起始时间或结束时间
     * @param dateTime 指定的时间
     * @param isStart 取一天的起始还是结束
     * @return 一天的起始时间yyyy-MM-dd 00:00:00 或结束时间yyyy-MM-dd 23:59:59，如果dateTime为空则返回当天的时间
     */
    private static Date getDate(Date dateTime, boolean isStart) {
        if(null == dateTime)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        if(isStart){
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }else{
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        }
        return calendar.getTime();
    }

    /**
     * 把时间组合成yyyy-MM-dd HH:mm的格式
     * @param date 日期
     * @param hour 小时
     * @param minute 分钟
     * @return 如果为空则返回""
     */
    public static String buildDateTime(String date, String hour, String minute){
        String dateTime = "";
        if(null == date || date.length() == 0)
            return dateTime;

        if(null == hour || hour.length() == 0)
            return dateTime + " 00:00";
        else
        if(hour.length() == 1)
            dateTime += " 0" + hour;
        else
            dateTime += " " + hour;

        if(null == minute || minute.length() == 0)
            return dateTime + ":00:00";
        else
        if(minute.length() == 1)
            dateTime += ":0" + minute;
        else
            dateTime += ":" + minute;
        return dateTime;
    }

    /**
     * 把时间组合成yyyy-MM-dd HH:mm:ss的格式
     * @param date 日期
     * @param hour 小时
     * @param minute 分钟
     * @param second 秒数
     * @return 如果为空则返回""
     */
    public static String buildDateTime(String date, String hour, String minute, String second){
        String dateTime = "";
        if(null == date || date.length() == 0)
            return dateTime;

        if(null == hour || hour.length() == 0)
            return dateTime + " 00:00:00";
        else
        if(hour.length() == 1)
            dateTime += " 0" + hour;
        else
            dateTime += " " + hour;

        if(null == minute || minute.length() == 0)
            return dateTime + ":00:00";
        else
        if(minute.length() == 1)
            dateTime += ":0" + minute;
        else
            dateTime += ":" + minute;

        if(null == second || second.length() == 0)
            return dateTime + ":00";
        else
        if(second.length() == 1)
            dateTime += ":0" + second;
        else
            dateTime += ":" + second;
        return dateTime;
    }

    /**
     * 判断给定的日期是否在起至日期之间
     * @param date 所要判断的日期
     * @param startDate 起始日期
     * @param endDate 结束日期
     * @param byDay 是否按天来算
     * @return 如果在返回true，否则返回false
     */
    public static boolean in(Date date, Date startDate, Date endDate, boolean byDay) {
        if (date.after(startDate) && date.before(endDate))
            return true;
        if(byDay) {
            if (DateUtil.isSameDay(date, startDate))
                return true;
            if (DateUtil.isSameDay(date, endDate))
                return true;
        }
        return false;
    }

    /**
     * 判断两个日期是否是同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2){
        if(null == date1 || null ==date2)
            return false;
        String d1 = formatDate(date1);
        String d2 = formatDate(date2);
        return d1.equalsIgnoreCase(d2);
    }


    /**
     * 获取两个日期之间所跨越的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDays(Date startDate, Date endDate){
        if (null == startDate || null == endDate)
            return 0;

        if(startDate.after(endDate)){
            Date tmpDate = startDate;
            startDate = endDate;
            endDate = tmpDate;
        }

        return (int)((endDate.getTime() - startDate.getTime())/(24 * 60 * 60 * 1000) + 1);
    }

    /**
     * 判断是否当天
     * @param date
     * @return
     */
    public static boolean isToday(Date date){
        return isSameDay(date, new Date());
    }

    /**
     * 判断是否昨天
     * @param date
     * @return
     */
    public static boolean isYesterday(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calendar.getTime();
        return isSameDay(date, yesterday);
    }

    /**
     * 格式化日期为星期几
     * @param date 日期
     * @return 返回星期一、二等
     */
    public static String format2Week(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }
}
