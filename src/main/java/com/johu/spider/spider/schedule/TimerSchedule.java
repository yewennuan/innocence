package com.johu.spider.spider.schedule;

import com.johu.spider.spider.mapper.Porn91Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
