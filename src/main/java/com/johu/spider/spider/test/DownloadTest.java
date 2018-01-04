package com.johu.spider.spider.test;

import com.johu.spider.spider.helper.VideoDownloadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wennan
 * 2018/1/4
 */
@Component
public class DownloadTest {

    @Autowired
    private VideoDownloadHelper videoDownloadHelper;

    @PostConstruct
    public void download(){
        String url = "http://185.38.13.159//mp43/249133.mp4?st=JAO5dsAnbHRve8lPDjxB9Q&e=1515146854";
        videoDownloadHelper.downloadVideo(url,"hehe");
    }

}
