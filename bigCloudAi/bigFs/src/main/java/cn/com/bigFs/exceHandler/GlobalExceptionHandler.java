package cn.com.bigFs.exceHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.bigFs.domain.Result;

@ControllerAdvice
public class GlobalExceptionHandler {
  
	    @ExceptionHandler(value = Exception.class)
	    @ResponseBody
	    public Result<Object> handle(Exception e) {
	            return new Result<Object>().error(-1, e.getMessage()+"");
	    }

}
