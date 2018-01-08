package com.johu.spider.spider.spider.pipeline;

import com.johu.spider.spider.entity.Porn91;
import com.johu.spider.spider.mapper.Porn91Mapper;
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
public class Porn91InitPipeline1 implements Pipeline {

    @Autowired
    private Porn91Mapper porn91Mapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Porn91 porn91 = (Porn91) resultItems.get("Porn91");
        if(Objects.nonNull(porn91)){
            porn91Mapper.insert(porn91);
        }
    }
}
