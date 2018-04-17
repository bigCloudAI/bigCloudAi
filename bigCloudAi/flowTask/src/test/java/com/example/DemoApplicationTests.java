package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.flowTask.MainApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MainApplication.class })
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("000000000000000");
	}

}
