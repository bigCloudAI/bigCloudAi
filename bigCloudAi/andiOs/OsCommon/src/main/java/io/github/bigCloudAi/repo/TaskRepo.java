package io.github.bigCloudAi.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.bigCloudAi.entry.Task;

public interface TaskRepo extends CrudRepository<Task,Integer>{
	
	public Task findById(int id);
	
	public List<Task> findAll();
	
	@Query("SELECT T FROM Task T")
	public Page<Task> findTaskTopN(Pageable pageable);//查当前前15个未执行的任务
	
}
