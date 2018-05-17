package cn.com.andiOs.repo.rbac;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaPersistentProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cn.com.andiOs.entry.rbac.User;

public interface UserRepo extends CrudRepository<User,Integer>,JpaRepository<User,Integer>{
	
	public User findById(int id);
	public User findByName(String name);
	
	public List<User> findAll();
	public Page<User> findAll(Pageable pageable);
	
	
}
