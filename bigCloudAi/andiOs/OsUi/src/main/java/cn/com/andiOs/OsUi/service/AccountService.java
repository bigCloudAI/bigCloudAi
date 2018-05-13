package cn.com.andiOs.OsUi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.andiOs.OsUi.entry.User;

@Service
public class AccountService {
	  /**** 
     * 通过用户名获取用户对象 
     * @param username 
     * @return 
     */  
    public User getUserByUserName(String username) {  
        User user = new User();  
        return user;  
    }  
  
    /*** 
     * 通过用户名获取权限资源 
     *  
     * @param username 
     * @return 
     */  
    public List<String> getPermissionsByUserName(String username) {  
        return new ArrayList<>();  
    }  
  
}
