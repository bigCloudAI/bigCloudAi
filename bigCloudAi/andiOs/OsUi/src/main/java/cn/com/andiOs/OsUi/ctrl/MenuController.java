package cn.com.andiOs.OsUi.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.com.andiOs.OsUi.entry.User;
import cn.com.andiOs.repo.rbac.UserRepo;

/**
 * 项目各个界面的请求地址
 *
 * @since 2017-03-28
 */
@RestController
@RequestMapping("/osUi")
public class MenuController {
	
	  public final static String SESSION_KEY = "user";
	
	  @Autowired
	  UserRepo userRepo;
	@RequestMapping(value = "/..", method = RequestMethod.GET)
	public ModelAndView index() {
		System.out.println(userRepo.findAll().size());
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
    @PostMapping("/loginPost")
    public @ResponseBody Map<String, Object> loginPost(User user, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (!"123".equals(user.getPwd())) {
            map.put("success", false);
            map.put("message", "密码错误");
            return map;
        }
        // 设置session
        session.setAttribute(this.SESSION_KEY, user.getName());
        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }
	
	@RequestMapping(value = "/isLogin", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> isLogin(HttpServletRequest request,HttpServletResponse response) {
		 Map<String, Object> map = new HashMap<>(); 
		HttpSession session = request.getSession();
         if (session.getAttribute(SESSION_KEY) != null) {
        	 map.put("success", true);
             map.put("name", session.getAttribute(SESSION_KEY));
             return map;
         }else {
        	 map.put("success", false);
             return map;
         }
	}
	
	 @GetMapping("/logoutGet")
    public  @ResponseBody Map<String, Object> logoutGet(HttpSession session) {
		 Map<String, Object> map = new HashMap<>(); 
        // 移除session
        session.removeAttribute(this.SESSION_KEY);
        if (session.getAttribute(SESSION_KEY) != null) {
        	 map.put("success", false);
             return map;
        }else {
        	 map.put("success", true);
             return map;
        }
    }
	
}
