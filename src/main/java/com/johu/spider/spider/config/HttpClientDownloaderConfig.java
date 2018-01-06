package com.johu.spider.spider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @author wennan
 * 2018/1/6
 */

@Configuration
public class HttpClientDownloaderConfig {

    @Value("${innocence.treasures.direction}")
    private String proxyIp;

    @Value("${innocence.treasures.entry}")
    private Integer proxyPort;

    @Bean
    public HttpClientDownloader httpClientDownloader() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy(proxyIp,proxyPort)));
        return httpClientDownloader;
    }
}
