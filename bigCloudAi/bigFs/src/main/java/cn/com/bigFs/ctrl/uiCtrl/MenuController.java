package cn.com.bigFs.ctrl.uiCtrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目各个界面的请求地址
 *
 * @since 2017-03-28
 */
@Controller
@RequestMapping("/bigFs")
public class MenuController {

	/**
	 * 文件系统首页
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	/**
	 * 初始化一个链账本
	 */
	@RequestMapping("/initChain")
	public String initChain() {
		return "index";
	}
	
	/**
	 * 将自己的信息加入到账本，（下载链账本，添加自己到链账本）
	 */
	@RequestMapping("/joinChain")
	public String joinChain() {
		return "index";
	}
	
	/**
	 * 广播新动态信息到账本链中
	 */
	@RequestMapping("/joinNewBlock")
	public String joinNewBlock() {
		return "index";
	}
	
	
	
	
}
