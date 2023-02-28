package com.pitest01.util.wearther;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

public class HttpGetConfig extends HttpGet {
    public HttpGetConfig(String url) {
        super(url);
        setDefaultConfig();
    }

    private void setDefaultConfig() {
        this.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(1000 * 10)
                .setConnectTimeout(1000 * 10)
                .setSocketTimeout(1000 * 10)
                .build());
        this.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:92.0) Gecko/20100101 Firefox/92.0");
    }
}
