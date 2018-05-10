package cn.com.flowTask.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.flowTask.core.entry.Task;
import cn.com.flowTask.core.repo.TaskRepo;

@Component
public class TaskExecer {
	List<Task> tasks = new ArrayList<Task>();
	//任务执行者
	//持有FlowTask，持有线程池。线程池空闲时，先领取同类型任务，没有时领取其他任务.执行任务后任务分发
	//执行领取任务（n个线程，可取n*x[如：1.2]个任务）。并执行。
	@Autowired
	FlowTask flowTaskImpl;
	@Autowired
	TaskRepo taskRepo;
	static int count;
	
	public synchronized static void countadd() {
		TaskExecer.count++;
	}
	
	@Scheduled(fixedDelay=60000)
	public void taskExecer() {
		//判斷當前任務是不是都完成了。tasks。完成了 就再去取，沒有就不執行
		if(count!=0) {return;}
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		/*Pageable pageable = new PageRequest(0, 15, Sort.Direction.ASC, "id");  
		tasks = taskRepo.findTaskTopN(pageable).getContent();*/
		
		for (int i = 0; i < tasks.size(); i++) {
			flowTaskImpl.execTaskF(tasks.get(i));
		}
		while(true) {
			try {
				if(TaskExecer.count==tasks.size()) {
					TaskExecer.count=0;
					if(tasks.size()>0){tasks.clear();}
					break;
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}
	
	
	
	
}
