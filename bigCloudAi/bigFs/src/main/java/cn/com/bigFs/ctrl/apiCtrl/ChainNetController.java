package cn.com.bigFs.ctrl.apiCtrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.bigFs.domain.Result;
import cn.com.bigFs.entry.Block;
import cn.com.bigFs.entry.FsNode;
import cn.com.bigFs.entry.Host;
import cn.com.bigFs.entry.MinFile;
import cn.com.bigFs.repo.BlockRepo;
import cn.com.bigFs.repo.FsNodeRepo;
import cn.com.bigFs.repo.HostRepo;
import cn.com.bigFs.repo.MinFileRepo;

@RestController
@RequestMapping("/bigFsChain")
public class ChainNetController {
	
	/**
	 * 
	 */
	@Autowired
	BlockRepo blockRepo;
	@Autowired
	HostRepo hostRepo;
	@Autowired
	FsNodeRepo fsNodeRepo;
	@Autowired
	MinFileRepo minFileRepo;
	
	@RequestMapping(value = "/syn",method = RequestMethod.GET)
	public Result<Object> blockSyn() throws Exception{//将自己的账本共享给其他节点
		List<Block> blocks =  blockRepo.findAll();
		List<Host> hosts = hostRepo.findAll();
		List<FsNode> fsNodes = fsNodeRepo.findAll();
		List<MinFile> minFiles =  minFileRepo.findAll();
		
		Map<String,List> chain = new HashMap<String,List>();
		chain.put("blocks", blocks);
		chain.put("hosts", hosts);
		chain.put("fsNodes", fsNodes);
		chain.put("minFiles", minFiles);
		
		return  new Result<Object>().success(chain);
	}
	
	@RequestMapping(value = "/chain/newBlock",method = RequestMethod.GET)
	public String newBlock() {//接收到新区块  带时间搓  （返回成功/失败）
		return "index";
	}
	
	
}
