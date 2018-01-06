package com.johu.spider.spider;

import com.johu.spider.spider.entity.Porn91;
import com.johu.spider.spider.helper.VideoDownloadHelper;
import com.johu.spider.spider.mapper.Porn91Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {

	@Autowired
	private Porn91Mapper porn91Mapper;

	@Autowired
	private VideoDownloadHelper videoDownloadHelper;

	@Test
	public void contextLoads() {

	}

	@Test
	public void myBatisTest() {
		List<Porn91> porn91List = porn91Mapper.getAll();
		System.out.println(1);
	}

	@Test
	public void downloadTest(){
		String url = "http://185.38.13.159//mp43/249133.mp4?st=JAO5dsAnbHRve8lPDjxB9Q&e=1515146854";
        videoDownloadHelper.downloadVideo(url,"hehe");
		System.out.println(1);
	}

}
