package cn.com.andiOs.repo.rbac;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.com.andiOs.entry.rbac.User;

public interface UserRepo extends CrudRepository<User,Integer>{
	
	public User findById(int id);
	
	public List<User> findAll();
	
	
}
