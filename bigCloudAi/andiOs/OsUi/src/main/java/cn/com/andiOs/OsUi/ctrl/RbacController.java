package cn.com.andiOs.OsUi.ctrl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.andiOs.entry.rbac.Access;
import cn.com.andiOs.entry.rbac.Role;
import cn.com.andiOs.entry.rbac.User;
import cn.com.andiOs.repo.rbac.AccessRepo;
import cn.com.andiOs.repo.rbac.RoleRepo;
import cn.com.andiOs.repo.rbac.UserRepo;

/**
 * 项目各个界面的请求地址
 *
 * @since 2017-03-28
 */
@RestController
@RequestMapping("/rbac")
public class RbacController {
	
	
	@Autowired
	UserRepo userRepo;
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public @ResponseBody User userAdd(User user) {
		user.setCreatedTime(new Date());
		user.setUpdatedTime(new Date());
		user.setIsAdmin(0);
		user.setStatus(0);
		user = userRepo.save(user);
		return user;
	}
	
	@RequestMapping(value = "/user/page/{start}/{size}", method = RequestMethod.GET)
	public @ResponseBody Page<User> userPage(@PathVariable int start,@PathVariable int size) {
		Page<User> pusers = userRepo.findAll(PageRequest.of(start, size));
		return pusers;
	}
	
	@RequestMapping(value = "/user/page/{start}/{size}", method = RequestMethod.POST)
	public @ResponseBody Page<User> userPagelike(@PathVariable int start,@PathVariable int size,@RequestBody String name) {
		User user = new User();
		System.out.println(name);
		user.setName(name);
		 Example<User> userExample = Example.of(user, ExampleMatcher.matching().withMatcher("name",
	                /*startsWith -> 10010%
	                * endsWith -> %10010
	                * contains -> %10010%
	                * */
	                ExampleMatcher.GenericPropertyMatchers.contains()));
		 
		Page<User> pusers = userRepo.findAll(userExample,PageRequest.of(start, size));
		return pusers;
	}
	
	@RequestMapping(value = "/user/del/{id}", method = RequestMethod.GET)
	public @ResponseBody boolean userDel(@PathVariable int id) {
		userRepo.deleteById(id);
		return true;
	}
	
	
	
	@Autowired
	RoleRepo roleRepo;
	@RequestMapping(value = "/role/add", method = RequestMethod.POST)
	public @ResponseBody Role userAdd(Role role) {
		role.setCreatedTime(new Date());
		role.setUpdatedTime(new Date());
		/*role.setIsAdmin(0);
		role.setStatus(0);*/
		role = roleRepo.save(role);
		return role;
	}
	
	@RequestMapping(value = "/role/page/{start}/{size}", method = RequestMethod.GET)
	public @ResponseBody Page<Role> rolePage(@PathVariable int start,@PathVariable int size) {
		Page<Role> pusers = roleRepo.findAll(PageRequest.of(start, size));
		return pusers;
	}
	
	@RequestMapping(value = "/role/page/{start}/{size}", method = RequestMethod.POST)
	public @ResponseBody Page<Role> rolePagelike(@PathVariable int start,@PathVariable int size,@RequestBody String name) {
		Role role = new Role();
		System.out.println(name);
		role.setName(name);
		 Example<Role> roleExample = Example.of(role, ExampleMatcher.matching().withMatcher("name",
	                ExampleMatcher.GenericPropertyMatchers.contains()));
		 
		Page<Role> proles = roleRepo.findAll(roleExample,PageRequest.of(start, size));
		return proles;
	}
	
	@RequestMapping(value = "/role/del/{id}", method = RequestMethod.GET)
	public @ResponseBody boolean roleDel(@PathVariable int id) {
		roleRepo.deleteById(id);
		return true;
	}
	
	
	@Autowired
	AccessRepo accessRepo;
	@RequestMapping(value = "/access/add", method = RequestMethod.POST)
	public @ResponseBody Access userAdd(Access access) {
		access.setCreatedTime(new Date());
		access.setUpdatedTime(new Date());
		/*access.setIsAdmin(0);
		access.setStatus(0);*/
		access = accessRepo.save(access);
		return access;
	}
	
	@RequestMapping(value = "/access/page/{start}/{size}", method = RequestMethod.GET)
	public @ResponseBody Page<Access> accessPage(@PathVariable int start,@PathVariable int size) {
		Page<Access> pusers = accessRepo.findAll(PageRequest.of(start, size));
		return pusers;
	}
	
	@RequestMapping(value = "/access/page/{start}/{size}", method = RequestMethod.POST)
	public @ResponseBody Page<Access> accessPagelike(@PathVariable int start,@PathVariable int size,@RequestBody String title) {
		Access access = new Access();
		System.out.println(title);
		access.setTitle(title);
		 Example<Access> accessExample = Example.of(access, ExampleMatcher.matching().withMatcher("name",
	                ExampleMatcher.GenericPropertyMatchers.contains()));
		 
		Page<Access> paccesss = accessRepo.findAll(accessExample,PageRequest.of(start, size));
		return paccesss;
	}
	
	@RequestMapping(value = "/access/del/{id}", method = RequestMethod.GET)
	public @ResponseBody boolean accessDel(@PathVariable int id) {
		accessRepo.deleteById(id);
		return true;
	}
}
