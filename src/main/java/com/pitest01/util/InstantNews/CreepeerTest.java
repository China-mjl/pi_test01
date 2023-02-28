package com.pitest01.util.InstantNews;

import com.pitest01.util.date.PI_date;

import java.io.IOException;
import java.util.Date;

public class CreepeerTest {
    public static void main(String[] args) throws IOException {

        //时间工具类
        PI_date nowDate = new PI_date();

        //要爬取的网页首页
        //中国新闻网：https://www.chinanews.com.cn/
        String firstPageURL = "https://www.chinanews.com.cn/";
        //要爬取的网页二级页面：根据首页拼成一级页面
        //即时新闻：https://www.chinanews.com.cn/scroll-news/news1.html

        //要爬取的网页二级页面
        //国内即时新闻二：https://www.chinanews.com.cn/gn/2022/05-15/9755134.shtml
        String secondPageURL = "https://www\\.chinanews\\.com\\.cn/.*/"
                +nowDate.strNowDateYear(new Date())+"/"
                +nowDate.strNowDateMD(new Date())
                //获取05月18日或05月19日
                //+"05-1[8|9]"
                //获取05月19日或05月20日
                //+"05-[1|2][9|0]"
                +"/.*.shtml";

        //设置导出爬取内容路径
        String fileStr = "D:\\mjl_work\\testData\\News\\news_"+nowDate.strNowDateYmd(new Date())+".xlsx";

        //调用爬虫方法
        CreeperCollect creeperCollect = new CreeperCollect();
        creeperCollect.collect(firstPageURL,secondPageURL,fileStr);

        //终止虚拟机
        System.exit(0);
    }
}
