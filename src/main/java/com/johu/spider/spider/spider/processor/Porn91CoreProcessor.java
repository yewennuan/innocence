package com.johu.spider.spider.spider.processor;

import com.johu.spider.spider.entity.Porn91;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author wennan
 * 2017/12/26
 */

public class Porn91CoreProcessor implements PageProcessor {

    private static List<String> userAgentList = Arrays.asList(
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/58.0.3029.96 Chrome/58.0.3029.96 Safari/537.36",
        "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:17.0; Baiduspider-ads) Gecko/17.0 Firefox/17.0",
        "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9b4) Gecko/2008030317 Firefox/3.0b4",
        "Mozilla/5.0 (Windows; U; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; BIDUBrowser 7.6)",
        "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko",
        "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
        "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.99 Safari/537.36",
        "Mozilla/5.0 (Windows NT 6.3; Win64; x64; Trident/7.0; Touch; LCJB; rv:11.0) like Gecko"
    );

    private String userAgent = userAgentList.get(new Random().nextInt(8));

    private String xForwardedFor = new Random().nextInt(256)+"."+new Random().nextInt(256)+"."+new Random().nextInt(256)+"."+new Random().nextInt(256);

    private Site site = Site
            .me()
            .setDomain("91porn.com")
            .addHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6")
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(10000)
            .setUserAgent(userAgent)
            .addHeader("X-Forwarded-For",xForwardedFor)
            .addHeader("Proxy-Connection","keep-alive")
            .addHeader("Cache-Control", "max-age=0")
            .setDisableCookieManagement(true);

    public static final String DOWNLOAD_URL ="src=\"(.+?)\"";




    @Override
    public void process(Page page) {
        String preUrl = page.getUrl().toString();
        String downloadUrl  = page.getHtml().xpath("//div[@class='example-video-container']/video/source").regex(DOWNLOAD_URL,1).toString();
        Porn91 porn91 = new Porn91();
        porn91.setPreUrl(preUrl);
        porn91.setUrl(downloadUrl);
        page.putField("Porn91",porn91);
    }

    @Override
    public Site getSite() {
        return site;
    }


}
