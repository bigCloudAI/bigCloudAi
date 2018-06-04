package cn.com.andiOs.repo.rbac;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.com.andiOs.entry.rbac.User;
import cn.com.andiOs.entry.rbac.dto.UserRoleUser;

public interface UserRepo extends CrudRepository<User,Integer>,JpaRepository<User,Integer>{
	
	public User findById(int id);
	public User findByName(String name);
	
	public List<User> findAll();
	public Page<User> findAll(Pageable pageable);
	
/*	@Query("select new cn.com.andiOs.entry.rbac.dto.UserRoleUser(u,ur,r) from User u,Role r,UserRole ur"
			+ " where u.id=ur.uid and r.id = ur.roleId")
	public List<UserRoleUser> findUserRoleUser();*/
	
	/*@Query("select new cn.com.andiOs.entry.rbac.dto.UserRoleUser(ur,r) from Role r left join UserRole ur"
			+ " on r.id = ur.roleId ")*/
	@Query("select new cn.com.andiOs.entry.rbac.dto.UserRoleUser((select ur from UserRole ur where ur.roleId=r.id and ur.uid=:userId),r) from Role r ")
	public List<UserRoleUser> findRoleUserRole(@Param("userId")int userId);
	
}
