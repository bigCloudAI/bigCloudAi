package cn.com.flowTask.core.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.flowTask.MainApplication;
import cn.com.flowTask.core.entry.Task;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MainApplication.class })
public class TaskServiceTest {

	@Autowired
	TaskService taskService;
	@Test
	public void taskService() {
		System.out.println("start");
		for (int i = 0; i < 100; i++) {
			Task task = new Task(0, "task"+i, new Date());
			taskService.addTask(task);
		}
		System.out.println("end");
		
		List<Task> tasks = taskService.queryTask();
		System.out.println("tasks:"+tasks.size());
	}

}
