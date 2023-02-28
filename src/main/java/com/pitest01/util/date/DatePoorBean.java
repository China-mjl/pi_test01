package com.pitest01.util.date;

import lombok.Data;

@Data
//时间差实体类
public class DatePoorBean {
    int diff;           //毫秒
    int diffSeconds;    //秒
    int diffMinutes;    //分钟
    int diffHours;      //小时
    int diffDays;       //天

}


