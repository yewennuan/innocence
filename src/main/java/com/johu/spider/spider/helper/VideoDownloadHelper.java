package com.johu.spider.spider.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

/**
 * @author wennan
 * 2018/1/4
 */

@Slf4j
@Component
public class VideoDownloadHelper {

    @Value("${innocence.treasures.direction}")
    private String proxyIp;

    @Value("${innocence.treasures.entry}")
    private Integer proxyPort;

    @Value("${innocence.treasures.location}")
    private String directory;

    private Tika tika = new Tika();

    private final String SEPARATOR = "/";


    public void downloadVideo(String url,String name){
        if(StringUtils.isEmpty(url)) {
            log.error("片名为{}的网页连接失效");
            return ;
        }
        CloseableHttpClient client = HttpClients.createDefault();
        RequestConfig config = null;
        if(StringUtils.isNotBlank(proxyIp) && Objects.nonNull(proxyPort)){
            HttpHost proxy = new HttpHost(proxyIp, proxyPort);
            config = RequestConfig.custom().setProxy(proxy).build();
        }else{
            config = RequestConfig.custom().build();
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        try {
            HttpResponse response = client.execute(httpGet);
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                return ;
            }
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                InputStream is = entity.getContent();

                //get file suffix
                String originSuffix = tika.detect(url);
                String suffix = originSuffix.replaceAll("video/",".");
                if(suffix.contains("/")) suffix="";
                String fullName = name+suffix;

                //get uri suffix
                String uriSuffix = DateHelper.formatDate2Str(new Date());
                String fullDirectory = directory+SEPARATOR+uriSuffix;
                File fileTmp = new File(fullDirectory);
                if(!fileTmp.isDirectory()){
                    Boolean result = fileTmp.mkdirs();
                    if(!result) {
                        log.error(fileTmp.getParent()+" 创建文件夹失败失败,很可能是没有权限");
                        return ;
                    }
                }

                File file = new File(fullDirectory+SEPARATOR+fullName);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[4*1024*1024];
                int len = -1;
                while((len = is.read(buffer) )!= -1){
                    fos.write(buffer, 0, len);
                }
                fos.close();
                is.close();
            }
        } catch (Exception e) {
            log.error("小片子下载失败",e);
        }finally{
            try {
                client.close();

            } catch (IOException e) {
                log.error("client关闭失败",e);
            }
        }
    }
}
