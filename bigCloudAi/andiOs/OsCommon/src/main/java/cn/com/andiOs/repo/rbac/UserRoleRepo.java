package cn.com.andiOs.repo.rbac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cn.com.andiOs.entry.rbac.UserRole;

public interface UserRoleRepo extends CrudRepository<UserRole,Integer>,JpaRepository<UserRole,Integer>{
	
	public UserRole findByUidAndRoleId(int uid,int roleId);
	
}
