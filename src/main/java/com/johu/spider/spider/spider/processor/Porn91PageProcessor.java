package com.johu.spider.spider.spider.processor;

import com.johu.spider.spider.entity.Porn91;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author wennan
 * 2017/12/26
 */

@Component
public class Porn91PageProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setDomain("91porn.com")
            .addHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6")
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(10000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");

    public static final String FIRST_URL = "http://91porn\\.com/video\\.php\\?category=rf";

    public static final String URL_LIST ="http://91porn\\.com/v\\.php\\?category=\\w+&viewtype=basic[\\s\\S]*";

    public static final String URL_GET ="http://91porn\\.com/view_video.php\\?viewkey=\\w+&page=\\w+&viewtype=basic&category=\\w+";



    @Override
    public void process(Page page) {
        if(page.getUrl().regex(FIRST_URL).match()||page.getUrl().regex(URL_LIST).match()){
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            page.addTargetRequests(page.getHtml().xpath("//div[@id='videobox']/table/tbody/tr/td").links().regex(URL_GET).all());
        }else if(page.getUrl().regex(URL_GET).match()){
            Porn91 porn91 = new Porn91();
            porn91.setPreUrl(page.getUrl().toString());
            porn91.setTitle(page.getHtml().xpath("//div[@id='viewvideo-title']/text()").toString());
            String duration = page.getHtml().xpath("//div[@class='boxPart']").regex("\\d\\d:\\d\\d").toString();
            porn91.setDuration(duration);
            page.putField("Porn91",porn91);
        }else{
            page.setSkip(true);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        Spider.create(new Porn91PageProcessor()).addUrl("http://91porn.com/video.php?category=rf")
//                .run();
        String content = "http://91porn.com/view_video.php?viewkey=33b4347c1114315d97b8&page=1&viewtype=basic&category=rp";

        String pattern = "http://91porn\\.com/view_video.php\\?viewkey=\\w+&page=\\w+&viewtype=basic&category=\\w+";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(1);

    }
}
