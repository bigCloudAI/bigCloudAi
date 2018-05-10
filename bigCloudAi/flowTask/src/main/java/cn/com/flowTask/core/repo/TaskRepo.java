package cn.com.flowTask.core.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.com.flowTask.core.entry.Task;

public interface TaskRepo extends CrudRepository<Task,Integer>{
	
	@SuppressWarnings("unchecked")
	public Task save(Task task);
	
	public Task findById(int id);
	
	public List<Task> findAll();
	
	@Query("SELECT T FROM Task T ORDER BY T.createTime")
	public Page<Task> findTaskTopN(Pageable pageable);//查当前前15个未执行的任务
	
}
