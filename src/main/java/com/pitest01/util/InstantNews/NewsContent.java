package com.pitest01.util.InstantNews;

import lombok.Data;

import java.util.Date;

//新闻内容类
@Data
public class NewsContent {

    private String type;    //分类
    private String title;   //标题
    private Date time;      //时间
    private String content; //内容
    private String pageURL; //网页连接

}
