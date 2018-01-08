package com.johu.spider.spider.task;

import com.johu.spider.spider.entity.Porn91VideoList;
import com.johu.spider.spider.mapper.Porn91VideoListMapper;
import com.johu.spider.spider.spider.pipeline.Porn91InitPipeline;
import com.johu.spider.spider.spider.pipeline.Porn91InitPipeline1;
import com.johu.spider.spider.spider.processor.Porn91InitProcessor;
import com.johu.spider.spider.spider.processor.Porn91PageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author wennan
 * 2018/1/6
 */

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

    @Autowired
    private Porn91InitPipeline1 porn91InitPipeline1;

    @Autowired
    private Executor initThreadPool;

    private final String SUFFIX = "&page=";

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Porn91VideoList> porn91VideoListList = porn91VideoListMapper.selectAll();
        String[] urlArray = porn91VideoListList.stream().map(Porn91VideoList::getUrl).toArray(String[]::new);
        if(urlArray.length==0){
            return;
        }

        //TODO:注释打开
//        Spider.create(porn91InitProcessor)
//                .addUrl(urlArray)
//                .setDownloader(httpClientDownloader)
//                .addPipeline(porn91InitPipeline)
//                .run();

        porn91VideoListList.stream().filter(x->x.getPageNo()<=x.getMaxPageNo()).forEach(x->CompletableFuture.runAsync(()->this.initDownloadUrl(x),initThreadPool));

        Thread.currentThread().join();

    }

    public void initDownloadUrl(Porn91VideoList porn91VideoList){
        String finalUrl = Optional.ofNullable(porn91VideoList).map(x->x.getUrl()+SUFFIX+x.getPageNo()).orElse("");
        String url = porn91VideoList.getUrl();
        Integer pageNo = porn91VideoList.getPageNo();
        Integer maxPageNo = porn91VideoList.getMaxPageNo();
        if(StringUtils.isEmpty(finalUrl)) return ;
        while (true){
            if(pageNo>maxPageNo) break;
            Spider.create(new Porn91PageProcessor())
                    .addUrl(finalUrl)
                    .setDownloader(httpClientDownloader)
                    .addPipeline(porn91InitPipeline1)
                    .run();

            pageNo++;
            Porn91VideoList updateEntity = new Porn91VideoList();
            updateEntity.setId(porn91VideoList.getId());
            finalUrl = url+SUFFIX+pageNo;
            updateEntity.setPageNo(pageNo);
            porn91VideoListMapper.updatePageNo(updateEntity);
        }


    }
}
