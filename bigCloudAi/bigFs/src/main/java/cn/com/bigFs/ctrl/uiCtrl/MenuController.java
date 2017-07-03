package cn.com.bigFs.ctrl.uiCtrl;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.bigFs.domain.Result;
import cn.com.bigFs.service.ChainService;

/**
 * 项目各个界面的请求地址
 *
 * @since 2017-03-28
 */
@RestController
@RequestMapping("/bigFs")
public class MenuController {

	@Autowired
	ChainService chainSer;
	/**
	 * 文件系统首页
	 */
	@RequestMapping(value ="/index",method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	/**
	 * 初始化一个链账本
	 * @throws UnknownHostException 
	 */
	@RequestMapping(value ="/initChain",method = RequestMethod.GET)
	public Result<String> initChain() throws Exception {
		chainSer.initChain();
		return new Result<String>().success("success");
	}
	
	/**
	 * 将自己的信息加入到账本，（下载链账本，添加自己到链账本）
	 */
	@RequestMapping(value = "/joinChain/{ip}",method = RequestMethod.GET)
	public Result<String> joinChain(@PathVariable String ip) {
		chainSer.joinChain(ip);
		return new Result<String>().success("success");
	}
	
	/**
	 * 广播新动态信息到账本链中
	 */
	@RequestMapping(value ="/joinNewBlock",method = RequestMethod.GET)
	public String joinNewBlock() {
		return "index";
	}
	
	
	
	
}
