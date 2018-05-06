package cn.com.flowTask.core.entry.impl;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import cn.com.flowTask.core.entry.Task;

@Component
@Entity
public interface ExecTask {
	
	public void exec(Task task);
	
}
