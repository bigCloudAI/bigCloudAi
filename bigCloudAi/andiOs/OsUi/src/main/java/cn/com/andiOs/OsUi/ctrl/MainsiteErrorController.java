package cn.com.andiOs.OsUi.ctrl;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainsiteErrorController implements ErrorController { 
  
 private static final String ERROR_PATH = "/error"; 
   
 @RequestMapping(value=ERROR_PATH) 
  public String handleError(){ 
    return "index"; 
  } 
   
 @Override
 public String getErrorPath() { 
 // TODO Auto-generated method stub 
 return ERROR_PATH; 
 } 
  //http://www.rsdown.cn/down/55528.html
 //https://nchc.dl.sourceforge.net/project/ermaster/ermaster/plugins/org.insightech.er_1.0.0.v20150619-0219.jar
 //https://blog.csdn.net/qq_33429968/article/details/74501037
}
