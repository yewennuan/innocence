package com.johu.spider.spider.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wennan
 * 2018/1/6
 */

@Data
public class Porn91 {
    private Long id;

    private String url;

    private String title;

    private Integer status;// 0就绪，1处理中，2完成

    private Date beginTime;

    private Date endTime;

    private String remark;

    private Integer isDeleted;

    private Date createTime;

}
