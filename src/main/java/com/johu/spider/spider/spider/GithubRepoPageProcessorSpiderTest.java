package com.johu.spider.spider.spider;

import com.johu.spider.spider.test.GithubRepoPageProcessorTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import javax.annotation.PostConstruct;

/**
 * @author wennan
 * 2017/12/27
 */

@Component
public class GithubRepoPageProcessorSpiderTest {

    @Autowired
    private GithubRepoPageProcessorTest githubRepoPageProcessorTest;

//    @PostConstruct
//    public void start(){
//        Spider.create(githubRepoPageProcessorTest).addUrl("https://github.com/code4craft").thread(5).run();
//    }
}
