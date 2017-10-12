package cn.com.andiOs.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 项目各个界面的请求地址
 *
 * @since 2017-03-28
 */
@RestController
@RequestMapping("/andiOs")
public class Menu {

	/*@RequestMapping(value ="/index",method = RequestMethod.GET)
	public String index() {
		return "index";
	}*/
	@RequestMapping(value ="/index",method = RequestMethod.GET)
	 public ModelAndView index(){
	        ModelAndView mv = new ModelAndView("index");
	        return mv;
	    }
}
