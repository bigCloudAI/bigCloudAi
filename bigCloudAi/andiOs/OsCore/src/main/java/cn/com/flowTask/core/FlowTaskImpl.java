package cn.com.flowTask.core;

import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import cn.com.flowTask.core.entry.Task;
import cn.com.flowTask.core.entry.impl.ExecTask;

@Component
public class FlowTaskImpl implements FlowTask{
	
	private static final Logger log = LoggerFactory.getLogger(FlowTaskImpl.class);

	
	@Override
	public List<Task> receiveTask() {
		// TODO Auto-generated method stub
		//从数据库里取可以执行的任务，任务参数都在Task【表】中，或缓冲中
		return null;
	}

	@Override
	@Async
	public void execTask(Task task) {
		try {
			log.info("execTask-start");
			Thread.sleep(10000l);
			log.info("execTask-end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return false;
	}
	
	@Override
	@Async
	public Future<String> execTaskF(Task task) {//時間限制
		Future<String> future;
		ExecTask execTask = null;
		 try {
			Class<?> execTaskClass=Class.forName(task.getExecClass());
			Object obj = execTaskClass.newInstance();
			if(obj instanceof ExecTask) {
				execTask=(ExecTask)obj;
			}else {
				return new AsyncResult<String>("class cannot instance");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 execTask.exec(task);
		try {
			Thread.sleep(1000 * 1);
			future = new AsyncResult<String>("success");
		} catch (InterruptedException e) {
			future = new AsyncResult<String>("error");
		}
		TaskExecer.countadd();
		return future;
	}

	@Override
	public List<Task> distributeTask() {
		// TODO Auto-generated method stub
		return null;
	}

}
