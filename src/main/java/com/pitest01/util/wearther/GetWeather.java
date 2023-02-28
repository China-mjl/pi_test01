package com.pitest01.util.wearther;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetWeather {
    public static String getResult(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = httpClient.execute(new HttpGetConfig(url));
        String result = EntityUtils.toString(response.getEntity(),"utf-8"); //设置编码，防止乱码
        return result;

    }
}
