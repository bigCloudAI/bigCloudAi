package cn.com.andiOs.repo.rbac;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cn.com.andiOs.entry.rbac.Access;
import cn.com.andiOs.entry.rbac.User;

public interface AccessRepo extends CrudRepository<Access,Integer>,JpaRepository<Access,Integer>{
	
	public User findById(int id);
	
	public List<Access> findAll();
	public Page<Access> findAll(Pageable pageable);
	
	
}
