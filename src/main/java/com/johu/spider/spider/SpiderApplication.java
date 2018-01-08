package com.johu.spider.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.johu.spider.spider.mapper")
public class SpiderApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpiderApplication.class, args);
	}
}
