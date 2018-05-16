package cn.com.andiOs.repo.rbac;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cn.com.andiOs.entry.rbac.Role;
import cn.com.andiOs.entry.rbac.User;

public interface RoleRepo extends CrudRepository<Role,Integer>,JpaRepository<Role,Integer>{
	
	public Role findById(int id);
	
	public List<Role> findAll();
	public Page<Role> findAll(Pageable pageable);
}
