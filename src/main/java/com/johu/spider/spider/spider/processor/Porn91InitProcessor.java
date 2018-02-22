package com.johu.spider.spider.spider.processor;

import com.johu.spider.spider.entity.Porn91;
import com.johu.spider.spider.entity.Porn91VideoList;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author wennan
 * 2017/12/26
 */

@Component
public class Porn91InitProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setDomain("91porn.com")
            .addHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6")
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(10000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36")
            .addHeader("Proxy-Connection","keep-alive")
            .addHeader("Cache-Control", "max-age=0")
            .setDisableCookieManagement(true);


    public static final String URL_LIST ="http://91porn\\.com/v\\.php\\?category=\\w+&[\\s\\S]*?viewtype=basic";




    @Override
    public void process(Page page) {
        if(page.getUrl().regex(URL_LIST).match()){
            List<String> pageNoList = page.getHtml().xpath("//div[@id='paging']/div/form/a/text()").regex("\\d+").all();
            Integer maxPageNo = pageNoList.stream().mapToInt(Integer::parseInt).max().orElse(-1);
            String url = page.getUrl().toString();
            Porn91VideoList porn91VideoList = new Porn91VideoList();
            porn91VideoList.setMaxPageNo(maxPageNo);
            porn91VideoList.setUrl(url);
            page.putField("Porn91VideoList", porn91VideoList);

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

        String pattern = "http://91porn\\.com/v\\.php\\?category=\\w+&viewtype=basic";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(1);

    }
}
