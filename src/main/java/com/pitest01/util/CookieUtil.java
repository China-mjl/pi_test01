package com.pitest01.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//获取对应网站的cookie
public class CookieUtil {
    /**
     * @vrifyCodeUrl 获取cookie的网站
     * @return cookie
     */
    public static String getCookie(String vrifyCodeUrl) {
        //String vrifyCodeUrl = "https://zhidao.baidu.com/search?lm=0&rn=10&pn=0&fr=search&ie=gbk&dyTabStr=null";
        String  cookie = "";
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(vrifyCodeUrl);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            //获取cookie值
            cookie = conn.getHeaderField("set-cookie").split(";")[0];
            //System.out.println("Cookie:"+cookie);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cookie;
    }

    /*public static void main(String[] args) {
        getCookie("https://zhidao.baidu.com/search?lm=0&rn=10&pn=0&fr=search&ie=gbk&dyTabStr=null");
    }*/
}
