package com.my.config;

import com.my.first.FirstWebApplication;
import com.my.first.controller.MovieWebController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FirstWebApplication.class)
public class FirstWebApplicationTests {

	@Resource
	MovieWebController controller;
	@Test
	public void contextLoads() {
		System.out.println(controller.hi("1"));
	}

}
