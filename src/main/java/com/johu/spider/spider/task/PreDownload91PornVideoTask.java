package com.johu.spider.spider.task;

import com.johu.spider.spider.entity.Porn91;
import com.johu.spider.spider.helper.VideoDownloadHelper;
import com.johu.spider.spider.mapper.Porn91Mapper;
import com.johu.spider.spider.spider.pipeline.Porn91PreDownloadPipeline;
import com.johu.spider.spider.spider.processor.Porn91PageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author wennan
 * 2018/1/6
 */

@Component
public class PreDownload91PornVideoTask implements ApplicationRunner {

    @Autowired
    Porn91PreDownloadPipeline porn91PreDownloadPipeline;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("localhost",1087)));
        Spider.create(new Porn91PageProcessor()).setDownloader(httpClientDownloader).addPipeline(porn91PreDownloadPipeline).addUrl("http://91porn.com/video.php?category=rf")
                .run();
    }


}
