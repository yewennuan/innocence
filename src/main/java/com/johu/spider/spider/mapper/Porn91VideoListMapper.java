package com.johu.spider.spider.mapper;

import com.johu.spider.spider.entity.Porn91;
import com.johu.spider.spider.entity.Porn91VideoList;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wennan
 * 2018/1/5
 */
public interface Porn91VideoListMapper {

    @Select("SELECT * FROM tb_91porn_video_list ")
    @Results({
            @Result(property = "pageNo",  column = "page_no"),
            @Result(property = "maxPageNo",  column = "max_page_no"),
            @Result(property = "createTime", column = "create_time")
    })
    List<Porn91VideoList> selectAll();

    @Update("UPDATE tb_91porn_video_list SET max_page_no=#{maxPageNo} WHERE url=#{url}")
    void initMaxPageNo(Porn91VideoList porn91VideoList);

    @Update("UPDATE tb_91porn_video_list SET page_no=#{pageNo} WHERE id=#{id}")
    void updatePageNo(Porn91VideoList porn91VideoList);




}
