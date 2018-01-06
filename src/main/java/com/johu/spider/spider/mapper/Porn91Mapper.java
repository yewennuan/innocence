package com.johu.spider.spider.mapper;

import com.johu.spider.spider.entity.Porn91;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author wennan
 * 2018/1/5
 */
public interface Porn91Mapper {

    @Select("SELECT * FROM tb_91porn WHERE is_deleted = 0")
    @Results({
            @Result(property = "beginTime",  column = "begin_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "resetTimes", column = "reset_times"),
            @Result(property = "isDeleted", column = "is_deleted"),
            @Result(property = "createTime", column = "create_time")

    })
    List<Porn91> getAll();

    @Update("UPDATE tb_91porn SET status=0,begin_time = null,end_time=null,reset_times=reset_times+1 WHERE status =1 and reset_times<3 AND (to_days(now())-to_days(begin_time)>=1) AND is_deleted=0")
    int updateDirtyData();

    @Update("UPDATE tb_91porn SET status=1,begin_time = now() WHERE id=#{id}")
    void beginDownload(Long id);

    @Update("UPDATE tb_91porn SET status=2,end_time = now() WHERE id=#{id}")
    void finishDownload(Long id);

    @Insert("INSERT INTO tb_91porn(url, title, remark) VALUES (#{url},#{title},#{remark})")
    void insert(Porn91 porn91);

    @Select("SELECT * FROM tb_91porn WHERE status=0 AND is_deleted=0 limit 1")
    @Results({
            @Result(property = "beginTime",  column = "begin_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "resetTimes", column = "reset_times"),
            @Result(property = "isDeleted", column = "is_deleted"),
            @Result(property = "createTime", column = "create_time")

    })
    Porn91 getNextTask();



}
