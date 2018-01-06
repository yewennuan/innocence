package com.johu.spider.spider.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wennan
 * 2018/1/6
 */

@Data
public class Porn91VideoList {

    private Long id;

    private String code;

    private String url;

    private String name;

    private Integer pageNo;

    private Integer maxPageNo;

    private Date createTime;
}
