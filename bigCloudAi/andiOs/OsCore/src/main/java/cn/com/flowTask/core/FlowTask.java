package cn.com.flowTask.core;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;

import cn.com.flowTask.core.entry.Task;

public interface FlowTask {
	
	public List<Task> receiveTask();//取任务
	
	public void execTask(Task task);//执行任务
	@Async
	public Future<String> execTaskF(Task task);//执行任务
	
	public List<Task> distributeTask();//分发下阶段任务

}
