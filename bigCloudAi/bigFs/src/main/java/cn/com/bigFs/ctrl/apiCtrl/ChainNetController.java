package cn.com.bigFs.ctrl.apiCtrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bigFsChain")
public class ChainNetController {
	
	/**
	 * 
	 */
	@RequestMapping("/block/syn")
	public String blockSyn() {//将自己的账本共享给其他节点
		return "index";
	}
	
	@RequestMapping("/chain/newBlock")
	public String newBlock() {//接收到新区块  带时间搓  （返回成功/失败）
		return "index";
	}
	
	
}
