package cn.com.andiOs.entry.rbac.dto;

import cn.com.andiOs.entry.rbac.Role;
import cn.com.andiOs.entry.rbac.User;
import cn.com.andiOs.entry.rbac.UserRole;

public class UserRoleUser {
	
	User user;
	UserRole userRole;
	Role role;
	boolean hasUserRole;
	public boolean isHasUserRole() {
		return hasUserRole;
	}

	public void setHasUserRole(boolean hasUserRole) {
		this.hasUserRole = hasUserRole;
	}

	public UserRoleUser(User user, UserRole userRole, Role role) {
		super();
		this.user = user;
		this.userRole = userRole;
		this.role = role;
	}

	public UserRoleUser(UserRole userRole, Role role) {
		super();
		this.userRole = userRole;
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRole getUserRole() {
		if(userRole==null) {
			this.setHasUserRole(false);
		}else {
			this.setHasUserRole(true);
		}
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
