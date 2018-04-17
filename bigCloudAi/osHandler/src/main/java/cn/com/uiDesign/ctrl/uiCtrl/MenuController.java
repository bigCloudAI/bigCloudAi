package cn.com.uiDesign.ctrl.uiCtrl;

import org.springframework.stereotype.Controller;
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
@Controller
/*@RequestMapping("/uiDesign")*/
public class MenuController {

	/*@RequestMapping(value ="/index",method = RequestMethod.GET)
	public String index() {
		return "index";
	}*/
	@RequestMapping(value ="/index",method = RequestMethod.GET)
	 public ModelAndView login(){
	        ModelAndView mv = new ModelAndView("index");
	        return mv;
	    }
	@RequestMapping(value ="/main",method = RequestMethod.GET)
	 public ModelAndView main(){
	        ModelAndView mv = new ModelAndView("main");
	        return mv;
	    }
}
