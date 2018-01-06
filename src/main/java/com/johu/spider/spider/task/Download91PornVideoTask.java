package com.johu.spider.spider.task;

import com.johu.spider.spider.entity.Porn91;
import com.johu.spider.spider.helper.VideoDownloadHelper;
import com.johu.spider.spider.mapper.Porn91Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author wennan
 * 2018/1/6
 */

@Component
public class Download91PornVideoTask implements ApplicationRunner {

    @Autowired
    private Porn91Mapper porn91Mapper;

    @Autowired
    private VideoDownloadHelper videoDownloadHelper;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        CompletableFuture.runAsync(this::task);
    }


    public void task() {
        while (true){
            while (true){
                if(Objects.isNull(getTaskIdAndUpdate(false))){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
            downloadDevil();
        }
    }

    @Async("downloadThreadPool")
    public void downloadDevil(){
        Porn91 porn91= getTaskIdAndUpdate(true);
        if(Objects.isNull(porn91)) return;
        videoDownloadHelper.downloadVideo(porn91.getUrl(),porn91.getTitle());
    }

    public synchronized Porn91 getTaskIdAndUpdate(Boolean isUpdate){
        Porn91 porn91 = porn91Mapper.getNextTask();
        if(Objects.isNull(porn91)) return null;
        Long taskId = porn91.getId();
        if(isUpdate) porn91Mapper.beginDownload(taskId);
        return porn91;
    }


}
