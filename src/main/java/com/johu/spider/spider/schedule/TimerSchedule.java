package com.johu.spider.spider.schedule;

import com.johu.spider.spider.mapper.Porn91Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wennan
 * 2018/1/6
 */

@Slf4j
@Component
public class TimerSchedule {
    @Autowired
    private Porn91Mapper porn91Mapper;


    @Scheduled(cron = "0 0/10 * * * ?")
    public void fixDownloadException(){
        int updateItems = porn91Mapper.updateDirtyData();
        log.info("reset dirty data {}",updateItems);
    }

//    @Scheduled(cron = "*/15 * * * * ?")
//    public void test(){
//        for(int i=0;i<10;i++){
//            run();
//            System.out.println(1);
//        }
//    }
//
//    @Async
//    public void run(){
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
