package cn.com.flowTask.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.flowTask.core.entry.Task;
import cn.com.flowTask.core.repo.TaskRepo;

@Service
public class TaskService {
	
	@Autowired
	TaskRepo taskRepo;
	
	public void addTask(Task task) {
		taskRepo.save(task);
	}
	
	public List<Task> queryTask(){
		List<Task> tasks = taskRepo.findAll();
		return tasks;
	}
	
	public Page<Task> queryTopTask(Pageable pageable){
		Page<Task> tasks = taskRepo.findTaskTopN(pageable);
		return tasks;
	}
	
}
