package com.johu.spider.spider.task;

import com.johu.spider.spider.entity.Porn91;
import com.johu.spider.spider.helper.VideoDownloadHelper;
import com.johu.spider.spider.mapper.Porn91Mapper;
import com.johu.spider.spider.spider.pipeline.Porn91CorePipeline;
import com.johu.spider.spider.spider.processor.Porn91CoreProcessor;
import com.johu.spider.spider.spider.processor.Porn91PageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * @author wennan
 * 2018/1/6
 */

@Slf4j
@Component
public class Download91PornCore  {

    @Autowired
    private Porn91Mapper porn91Mapper;

    @Autowired
    private VideoDownloadHelper videoDownloadHelper;

    @Autowired
    private Executor downloadThreadPool;

    @Autowired
    private Porn91CorePipeline porn91CorePipeline;


    @Autowired
    private HttpClientDownloader httpClientDownloader;


    public void run()  {
        for(int i=0;i<5;i++){
            CompletableFuture.runAsync(this::task,downloadThreadPool);
        }

    }


    public void task() {
        while (true){
            List<Porn91> porn91List = getTaskListAndUpdate();
            if(CollectionUtils.isEmpty(porn91List)){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            downloadDevil(porn91List);
        }
    }

    private List<Porn91> getDownloadUrlList(List<Porn91> porn91List){
        List<String> preUrlList = porn91List.stream().map(Porn91::getPreUrl).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(preUrlList)) return Collections.emptyList();

        Porn91CoreProcessor porn91CoreProcessor = new Porn91CoreProcessor();
        Spider.create(porn91CoreProcessor)
                .addUrl(preUrlList.toArray(new String[preUrlList.size()]))
                .addPipeline(porn91CorePipeline)
                .setDownloader(httpClientDownloader)
                .run();

        for(Porn91 porn91:porn91List){
            Porn91 porn91Tmp = porn91Mapper.getByPreUrl(porn91.getPreUrl());
            if(Objects.isNull(porn91Tmp)|| StringUtils.isEmpty(porn91Tmp.getUrl())) continue;
            porn91.setUrl(porn91Tmp.getUrl());

        }
        return porn91List;

    }


    private void downloadDevil(List<Porn91> porn91List){
        if(CollectionUtils.isEmpty(porn91List)) return ;
        log.info("url爬取开始");
        porn91List = getDownloadUrlList(porn91List);
        log.info("url爬取结束，开始下载");
        porn91List.forEach(x->videoDownloadHelper.downloadVideo(x.getUrl(),x.getTitle()));
        log.info("下载完成 =v=");
    }

    private synchronized List<Porn91> getTaskListAndUpdate(){
        List<Porn91> porn91List = porn91Mapper.getNextTaskList();
        porn91List.forEach(x->porn91Mapper.beginDownload(x.getId()));
        return porn91List;
    }


}
