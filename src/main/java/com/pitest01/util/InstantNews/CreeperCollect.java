package com.pitest01.util.InstantNews;

import com.pitest01.util.PI_Format;
import com.pitest01.util.date.PI_date;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//爬虫类
public class CreeperCollect implements PageProcessor {

    public void collect(String firstPageURL,String secondPageURL,String fileStr) throws IOException {
        // Spider是爬虫启动入口，设置爬虫类
        Spider.create(new CreeperCollect(firstPageURL,secondPageURL))
                // 从互联网上下载页面
                .setDownloader(new HttpClientDownloader())
                .addUrl(firstPageURL)// 设置爬取地址
                // 将爬取的结果输出到控制台
                //.addPipeline(new ConsolePipeline())
                .thread(10) // 设置爬取的线程数
                .run(); // 启动爬虫
        System.out.println("网页总数为---------------------------："+linkCount);

        //将结果导出至excel文件
        exportVunl(fileStr);
    }

    // 计算爬取网页总数
    private static int linkCount;

    //存储爬取到内容
    private static List<NewsContent> newsList = new ArrayList<>();

    // 设置爬取网页总页数
    private int pageNum=10;
    // 抓取网站的相关配置：重试次数、重试的睡眠时间、延时执行时间
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    //要爬取的网页首页
    static String firstPage = null;
    //要爬取的网页二级页面
    static String secondPage = null;

    CreeperCollect(){

    }

    private CreeperCollect(String firstPageURL,String secondPageURL){
        firstPage = firstPageURL;
        secondPage = secondPageURL;
    }

    @SneakyThrows
    @Override
    public void process(Page page) {
        // 获取页面url
        String url = page.getUrl().get();
        //判断是否为首页
        if (url.equals(firstPage)){
            //获取一级页面分页
            for (int i = 1; i <= pageNum; i++) {
                String oneUrl = firstPage+"scroll-news/news"+i+".html";
                //将下一页链接添加到爬虫队列中
                page.addTargetRequest(oneUrl);
            }
            //判断是否为一级分页页面
        } else if(url.startsWith(firstPage+"scroll-news/news")) {
            // 获取二级页面;regex():根据正则表达式匹配
            List<String> twoUrlList = page.getHtml().links().regex(secondPage).all();
            //list集合去重
            twoUrlList = twoUrlList.stream().distinct().collect(Collectors.toList());
            // 增加要抓取的二级页面
            page.addTargetRequests(twoUrlList);
        }else {

            linkCount++;

            NewsContent newsContent = new NewsContent();

            String pageType = "";
            String pageTitle = "";
            String pageTime = "";
            String pageContent = "";

            //处理特殊类型地址：图片、视频；
            //是否为视频新闻
            if (page.getUrl().toString().contains("/shipin/")){

                // 抓取每个详情页中分类
                pageType = "视频新闻";
                // 抓取每个详情页中标题
                pageTitle = page.getHtml().css("div.left").xpath("h1/text()").toString();
                if (pageTitle == null){
                    pageTitle = page.getHtml().css("div.content_title div.title").xpath("div/text()").toString();
                    if (pageTitle == null){
                        //System.out.println("暂无标题：----------------------"+url);
                        pageTitle = "暂无";
                    }

                }

                // 抓取每个详情页中时间
                pageTime = page.getHtml().css("div.left").xpath("p/text()").toString();
                if (pageTime != null){
                    pageTime = pageTime.substring(6,23)
                            .replace("年","-")
                            .replace("月","-")
                            .replace("日","");
                }else {
                    //System.out.println("暂无时间：----------------------"+url);
                    pageTime = PI_date.strNowDateYmdHm(new Date());
                }

                // 抓取每个详情页中内容
                pageContent = page.getHtml().css("div.content_desc").toString();
                if (pageContent != null){
                    pageContent = new PI_Format().formatNews(pageContent);
                }else {
                    //System.out.println("暂无内容：----------------------"+url);
                    pageContent = "暂无";
                }


                //是否图片新闻
            }else if(page.getUrl().toString().contains("/tp/")){
                // 抓取每个详情页中分类
                pageType = "图片新闻";
                // 抓取每个详情页中标题
                pageTitle = page.getHtml().css("div#_playpic i.title").xpath("i/text()").toString();
                if(pageTitle == null){

                    pageTitle = page.getHtml().css("div#cont_1_1_2 h1").xpath("h1/text()").toString();
                    if (pageTitle == null){
                        //System.out.println("暂无标题：----------------------"+url);
                        pageTitle = page.getHtml().css("h1.page_title").xpath("h1/text()").toString();
                        if (pageTitle == null){
                            pageTitle = "暂无";
                        }

                    }
                }

                // 抓取每个详情页中时间
                pageTime = page.getHtml().css("div.zxians div.t3+div").xpath("div/text()").toString();
                if (pageTime != null){
                    pageTime = pageTime.substring(5,21);
                }else {
                    pageTime = page.getHtml().css("div.left-t").xpath("div/text()").toString();
                    if (pageTime != null){
                        pageTime = pageTime.substring(0,17)
                                .replace("年","-")
                                .replace("月","-")
                                .replace("日","");
                    }else {
                        //System.out.println("暂无时间：----------------------"+url);
                        pageTime = PI_date.strNowDateYmdHm(new Date());
                    }
                }

                // 抓取每个详情页中内容
                pageContent = page.getHtml().css("div.t3").toString();
                if (pageContent != null){
                    pageContent = new PI_Format().formatNews(pageContent);
                }else {
                    pageContent = page.getHtml().css("div.left_zw").toString();
                    if (pageContent != null){
                        pageContent = new PI_Format().formatNews(pageContent);
                    }else {
                        pageContent = page.getHtml().css("div.desc").toString();
                        if (pageContent != null){
                            pageContent = new PI_Format().formatNews(pageContent);
                        }else {
                            //System.out.println("暂无内容：----------------------"+url);
                            pageContent = "暂无";
                        }

                    }

                }


            }else{
                // 抓取每个详情页中分类
                pageType = page.getHtml().css("div#nav a:nth-child(2)").xpath("a/text()").toString();
                if (pageType == null){
                    //System.out.println("暂无分类：----------------------"+url);
                    pageType = "暂无";
                }

                // 抓取每个详情页中标题
                pageTitle = page.getHtml().css("div#cont_1_1_2").xpath("h1/text()").toString();
                if (pageTitle == null){
                    //System.out.println("暂无标题：----------------------"+url);
                    pageTitle = "暂无";
                }

                // 抓取每个详情页中时间
                pageTime = page.getHtml().css("div.left-t").toString();
                if (pageTime != null){
                    pageTime = pageTime.substring(22,39)
                            .replace("年","-")
                            .replace("月","-")
                            .replace("日","");
                }else{
                    //System.out.println("暂无时间：----------------------"+url);
                    pageTime = PI_date.strNowDateYmdHm(new Date());
                }

                // 抓取每个详情页中内容
                pageContent = page.getHtml().css("div.left_zw").toString();
                if (pageContent != null){
                    pageContent = new PI_Format().formatNews(pageContent);
                }else {
                    //System.out.println("暂无内容：----------------------"+url);
                    pageContent = "暂无";
                }


            }

            newsContent.setType(pageType);
            newsContent.setTitle(pageTitle);
            newsContent.setTime(new PI_date().strToDate(pageTime));
            newsContent.setContent(pageContent);
            newsContent.setPageURL(url);

            newsList.add(newsContent);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    //实现导出数据方法
    public static void exportVunl(String fileStr) throws IOException {
        //集合按时间属性排序--降序
        newsList.sort((oneItem, twoItem) -> twoItem.getTime().compareTo(oneItem.getTime()));

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        int rowNumber = 0;
        XSSFRow row = sheet.createRow(rowNumber++);
        //创建列标题
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("分类");
        row.createCell(2).setCellValue("标题");
        row.createCell(3).setCellValue("时间");
        row.createCell(4).setCellValue("内容");
        row.createCell(5).setCellValue("地址");

        for(NewsContent newsContent: newsList) {
            row = sheet.createRow(rowNumber);

            row.createCell(0).setCellValue(rowNumber++);
            row.createCell(1).setCellValue(newsContent.getType());
            row.createCell(2).setCellValue(newsContent.getTitle());
            row.createCell(3).setCellValue(new PI_date().strNowDateYmdHm(newsContent.getTime()));
            row.createCell(4).setCellValue(newsContent.getContent());
            row.createCell(5).setCellValue(newsContent.getPageURL());
        }

        //设置导出爬取内容路径
        OutputStream out = new FileOutputStream(new File(fileStr));
        workbook.write(out);
        out.flush();
        out.close();
    }


}
