package com.pitest01.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

//计算两个时间的时间差
public class DatePoor {

    //1、处理时间格式：年-月-日
    // 精确到天
    public static int datePoorymd(String dateStart, String dateStop){
        //String dateStart = "2013-02-19";
        //String dateStop = "2013-02-20";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DatePoorBean datePoorBean = new DatePoorBean();
        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            //毫秒ms
            long diff = d2.getTime() - d1.getTime();
            //天
            long diffDays = diff / (24 * 60 * 60 * 1000);

            datePoorBean.setDiff(new Long(diff).intValue());
            datePoorBean.setDiff(new Long(diffDays).intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datePoorBean.getDiffDays();

    }

    //2、处理时间格式：年-月-日 时:分:秒；
    // 精确到天-时-分-秒
    public static int datePoorymdhmsAccurate(String dateStart, String dateStop){

        //String dateStart = "2013-02-19 09:29:58";
        //String dateStop = "2013-02-20 11:31:48";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DatePoorBean datePoorBean = new DatePoorBean();
        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            //毫秒ms
            long diff = d2.getTime() - d1.getTime();
            //秒
            long diffSeconds = diff / 1000 % 60;
            //分钟
            long diffMinutes = diff / (60 * 1000) % 60;
            //小时
            long diffHours = diff / (60 * 60 * 1000) % 24;
            //diffDays
            long diffDays = diff / (24 * 60 * 60 * 1000);

            datePoorBean.setDiff(new Long(diff).intValue());
            datePoorBean.setDiff(new Long(diffSeconds).intValue());
            datePoorBean.setDiff(new Long(diffMinutes).intValue());
            datePoorBean.setDiff(new Long(diffHours).intValue());
            datePoorBean.setDiff(new Long(diffDays).intValue());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return datePoorBean.getDiffDays();

    }

    //3、处理时间格式：年-月-日 时:分:秒
    // 按天算的时间差、按小时算的时间差、按分算的时间差、按秒算的时间差
    public static DatePoorBean datePoorymdhmsEquate(String dateStart, String dateStop){

        //String dateStart = "2013-02-19 09:29:58";
        //String dateStop = "2013-02-20 11:31:48";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DatePoorBean datePoorBean = new DatePoorBean();
        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            //毫秒ms
            long diff = d2.getTime() - d1.getTime();
            //秒
            long diffSeconds = diff / 1000;
            //分钟
            long diffMinutes = diff / (60 * 1000);
            //小时
            long diffHours = diff / (60 * 60 * 1000);
            //diffDays
            long diffDays = diff / (24 * 60 * 60 * 1000);

            datePoorBean.setDiff(new Long(diff).intValue());
            datePoorBean.setDiffSeconds(new Long(diffSeconds).intValue());
            datePoorBean.setDiffMinutes(new Long(diffMinutes).intValue());
            datePoorBean.setDiffHours(new Long(diffHours).intValue());
            datePoorBean.setDiffDays(new Long(diffDays).intValue());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return datePoorBean;

    }

}


