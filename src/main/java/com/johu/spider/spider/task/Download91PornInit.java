package com.johu.spider.spider.task;

import com.johu.spider.spider.config.HttpClientDownloaderConfig;
import com.johu.spider.spider.entity.Porn91VideoList;
import com.johu.spider.spider.mapper.Porn91VideoListMapper;
import com.johu.spider.spider.spider.pipeline.Porn91InitPipeline;
import com.johu.spider.spider.spider.processor.Porn91InitProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wennan
 * 2018/1/6
 */

@Order(value = 1)
@Component
public class Download91PornInit implements ApplicationRunner {

    @Autowired
    private Porn91InitProcessor porn91InitProcessor;

    @Autowired
    private Porn91VideoListMapper porn91VideoListMapper;

    @Autowired
    private HttpClientDownloader httpClientDownloader;

    @Autowired
    private Porn91InitPipeline porn91InitPipeline;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Porn91VideoList> porn91VideoListList = porn91VideoListMapper.selectAll();
        String[] urlArray = porn91VideoListList.stream().map(Porn91VideoList::getUrl).toArray(String[]::new);
        if(urlArray.length==0){
            return;
        }
        Spider.create(porn91InitProcessor)
                .addUrl(urlArray)
                .setDownloader(httpClientDownloader)
                .addPipeline(porn91InitPipeline)
                .run();
    }
}
