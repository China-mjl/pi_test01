package com.pitest01.util.search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pitest01.util.CookieUtil;
import com.pitest01.util.PI_Format;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 *webmagic爬虫实现
 */
public class PI_wmSearch implements PageProcessor{

    //爬取内容
    private static String contentStr;
    //爬取标识
    private static String seachSign;
    //爬取标识
    private static String searchCookie;

    private Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0")
            .setRetryTimes(3)
            .setSleepTime(1000)
            .addCookie("cookies", CookieUtil.getCookie(searchCookie));

    @Override
    public Site getSite() {
        // TODO Auto-generated method stub
        return site;
    }

    //爬取过程
    @Override
    public void process(Page page) {
        //System.out.println(page.getJson().toString());
        Html html = new Html(page.getJson().toString());
        //1、爬取百度百科简介
        if(StringUtils.isNotBlank(seachSign)&&seachSign.equals("BaiKe")){
            //获取对应查到的内容
            String content = html.xpath("//div[@class='lemma-summary']").toString();
            //处理获取到的内容
            contentStr = PI_Format.formatBaiKe(content);
        }

        //2、爬取百度名医简介
        if(StringUtils.isNotBlank(seachSign)&&seachSign.equals("MingYi")){
            //获取对应查到的内容
            String content = html.xpath("//div[@class='content line-clamp2']/span[2]").toString();
            //处理获取到的内容
            contentStr = PI_Format.formatBaiKe(content);
        }
        /*//爬取百度知道回答
        if(StringUtils.isNotBlank(seachSign)&&seachSign.equals("ZhiDao")){
            //获取对应查到的内容
            String content = html.xpath("//div[@id='1']/div[@class='sc_content']/div[@class='c_abstract']").toString();
            //处理获取到的内容
            contentStr = PI_Format.formatBaiKe(content);
        }*/
        //3、爬取百度知道
        //（1）获取搜索内容的链接
        if(StringUtils.isNotBlank(seachSign)&&seachSign.equals("ZhiDaoLink")){
            //获取对应查到的内容
            String content = html.xpath("//*[@id='wgt-list']/dl[1]/dt[1]/a[@class='ti'][1]").links().toString();
            //System.out.println("content Link："+content);
            contentStr = content;
        }
        //（2）获取搜索的内容
        if(StringUtils.isNotBlank(seachSign)&&seachSign.equals("ZhiDao")){
            //获取对应查到的内容
            String content = html.xpath("//div[@class='rich-content-container rich-text-']").toString();
            //System.out.println("content内容："+content);
            //处理获取到的内容
            contentStr = PI_Format.formatZhiDao(content);
            //System.out.println("content内容："+contentStr);
        }

        //System.out.println(contentStr);
        //System.out.println(unicodeToString(contentStr));

    }

    //获取网址页面
    public static void search(String Sign, String searchURL, String seachContent,String cookie){
        seachSign = Sign;
        searchCookie = cookie;
        //1、这里填写要爬的网址；
        //2、线程数15；
        Spider.create(new PI_wmSearch()).addUrl(searchURL+seachContent).addPipeline(new ConsolePipeline()).thread(15).run();
    }

    /*//爬取网页
    public static void search(String Sign, String searchURL, String seachContent){
        seachSign = Sign;
        //1、这里填写要爬的网址；
        //2、线程数15；
        Spider.create(new PI_wmSearch()).addUrl(searchURL+seachContent).addPipeline(new ConsolePipeline()).thread(15).run();
    }*/

    //返回爬取内容
    public static String reContent(){
        return contentStr;
    }

    //unicode 转中文
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }

}

