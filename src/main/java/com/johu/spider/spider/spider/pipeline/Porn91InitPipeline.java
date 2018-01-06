package com.johu.spider.spider.spider.pipeline;

import com.johu.spider.spider.entity.Porn91;
import com.johu.spider.spider.entity.Porn91VideoList;
import com.johu.spider.spider.mapper.Porn91Mapper;
import com.johu.spider.spider.mapper.Porn91VideoListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Objects;

/**
 * @author wennan
 * 2018/1/6
 */

@Component
public class Porn91InitPipeline implements Pipeline {

    @Autowired
    private Porn91VideoListMapper porn91VideoListMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Porn91VideoList porn91VideoList = (Porn91VideoList) resultItems.get("Porn91VideoList");
        if(Objects.nonNull(porn91VideoList)){
            porn91VideoListMapper.initMaxPageNo(porn91VideoList);
        }

    }
}
