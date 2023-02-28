package com.pitest01.util;

//Html转换为String
public class PI_Format {
    /*
     * @htmlToStr:Html转换为String
     * 通用
     * */
    public static String htmlToStr(String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        //去掉空白行
        content = content.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
        //去掉所有中括号、大括号
        content = content.replaceAll("\\[|\\{","").replaceAll("\\]|\\}","");
        // 还原HTML
        // content = HTMLDecoder.decode(content);
        return content;
    }

    /**
     * 即时新闻
     * @param content
     * @return
     */
    public String formatNews (String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        //去掉空白行
        content = content.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
        // 还原HTML
        // content = HTMLDecoder.decode(content);
        return content;
    }
    /*
     * @htmlToStr:Html转换为String
     * 百度百科
     * */
    public static String formatBaiKe(String content) {
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        //去掉换行、空格
        content = content.replaceAll("\r\n|\r|\n| |&nbsp", "");
        // 还原HTML
        // content = HTMLDecoder.decode(content);
        return content;
    }
    /*
     * @htmlToStr:Html转换为String
     * 百度知道
     * */
    public static String formatZhiDao(String content) {
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        //去掉空白行
        content = content.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
        //去掉换行、空格
        return content;
    }

}
