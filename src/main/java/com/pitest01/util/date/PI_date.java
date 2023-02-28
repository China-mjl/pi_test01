package com.pitest01.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//日期类
public class PI_date {
    //当前日期Date类型
    public Date dateNewDate( ){
        return new Date();
    }
    //当前日期秒类型
    public long secondNowDate(){
        return new Date().getTime();
    }
    //当前日期string类型：年－月－日－时－分－秒
    public static String strNowDateAll(Date newDate){
        String nowdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newDate);
        return nowdate;
    }
    //当前日期string类型：年－月－日－时－分
    public static String strNowDateYmdHm(Date nowDate){
        String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(nowDate);
        return strDate;
    }
    //当前日期string类型：年－月－日－时
    public static String strNowDateYmdH(Date nowDate){
        String strDate = new SimpleDateFormat("yyyy-MM-dd HH").format(nowDate);
        return strDate;
    }
    //当前日期string类型：年－月－日
    public String strNowDateYmd(Date newDate){
        String nowdate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
        return nowdate;
    }
    //当前日期string类型：年－月
    public static String strNowDateym(Date newDate){
        String nowdate = new SimpleDateFormat("yyyy-MM").format(newDate);
        return nowdate;
    }
    //当前日期string类型：年
    public String strNowDateYear(Date newDate){
        String nowdateyear = new SimpleDateFormat("yyyy").format(newDate);
        return nowdateyear;
    }
    //当前日期string类型：月-日
    public String strNowDateMD(Date newDate){
        String nowdatemd = new SimpleDateFormat("MM-dd").format(newDate);
        return nowdatemd;
    }

    //当前日期string类型：日
    public static String strNowDateD(Date nowDate){
        String nowdatemd = new SimpleDateFormat("dd").format(nowDate);
        return nowdatemd;
    }
    //当前日期string类型：时－分
    public static String strNowDateHm(Date nowDate){
        String strDate = new SimpleDateFormat("HH:mm").format(nowDate);
        return strDate;
    }

    //当前日期string类型：时
    public static String strNowDateH(Date nowDate){
        String strDate = new SimpleDateFormat("HH").format(nowDate);
        return strDate;
    }

    //当前日期string类型：分
    public static String strNowDatem(Date nowDate){
        String strDate = new SimpleDateFormat("mm").format(nowDate);
        return strDate;
    }

    //string转date：年-月-日 时：分
    public Date strToDate(String strDate) throws ParseException {
        SimpleDateFormat nowdate =new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date nowdateall = nowdate.parse(strDate);
        return nowdateall;
    }

    //计算时间:年－月－日
    public static String strNowDateAddymd(Date newDate, int days){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(newDate);
        // 把日期往后增加几天,整数  往后推,负数往前移动
        calendar.add(calendar.DATE, days);
        //calendar.(calendar.DATE, days);
        // 这个时间就是日期往后推几天的结果
        newDate=calendar.getTime();
        String nowdate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
        return nowdate;
    }

    //计算时间:年－月－日－时－分－秒
    public static String strNowDateAddymdhms(Date newDate, int days){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(newDate);
        // 把日期往后增加几天,整数  往后推,负数往前移动
        calendar.add(calendar.DATE, days);
        //calendar.(calendar.DATE, days);
        // 这个时间就是日期往后推几天的结果
        newDate=calendar.getTime();
        String nowdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newDate);
        return nowdate;
    }

    //今天是星期几
    public static String dayOFWeek(){
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar=Calendar.getInstance();
        int testSign = calendar.get(Calendar.DAY_OF_WEEK)-1;
        return weekDays[testSign];
    }


}

