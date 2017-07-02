package cn.com.bigFs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.com.bigFs.entry.AllChain;
import cn.com.bigFs.entry.Block;
import cn.com.bigFs.entry.FsNode;
import cn.com.bigFs.entry.Host;
import cn.com.bigFs.entry.ShortChain;
import cn.com.bigFs.repo.BlockRepo;
import cn.com.bigFs.repo.FsNodeRepo;
import cn.com.bigFs.repo.HostRepo;
import net.minidev.json.JSONObject;

@Service
public class ChainService {

	@Autowired
	AllChain allChain;
	
	@Autowired
	ShortChain shortChain;
	        
	@Autowired
	BlockRepo blockRepo;
	@Autowired
	HostRepo hostRepo;
	@Autowired
	FsNodeRepo fsNodeRepo;
	@Autowired
	RestTemplate rest;
	@Autowired
	HostService hostSer;
	
	public AllChain initChain() throws Exception{
		//初始化chain fs hostrespool
		Host host = hostSer.getSelfHost();
		Block block = new Block();
		block.setBlock(0);
		block.setEvent("add");
		block.setObject(host);
		Map<String,String> handler = new HashMap<String,String>();
		handler.put("hostIp", host.getIp());
		block.setHandler(handler);
		
		JSONObject blockJson = new  JSONObject();
			blockJson.put("block", block);
			
		//System.out.println(blockJson);
		hostRepo.save(host);	
		blockRepo.save(block);
			
		FsNode fsRoot = new FsNode();
		fsRoot.setParentNodeId(0);
		fsRoot.setName("bigFs");
		fsRoot.setType(0);
		fsNodeRepo.save(fsRoot);
		return allChain;
	}
	
	
	public void joinChain(String ip){
		JSONObject chain = rest.getForObject("http://"+ip+":6066/block/syn", JSONObject.class);
	}
	
	public AllChain loadAllChain(){
		
		
		return allChain;
	}
	
	public ShortChain loadShortChain(){
		
		return shortChain;
	}
	
	
}
