package cn.com.bigFs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bigFs.entry.AllChain;
import cn.com.bigFs.entry.ShortChain;

@Service
public class ChainService {

	@Autowired
	AllChain allChain;
	
	@Autowired
	ShortChain shortChain;
	
	public AllChain initChain(){
		//初始化chain fs hostrespool
		
		
		return allChain;
	}
	
	public AllChain loadAllChain(){
		
		
		return allChain;
	}
	
	public ShortChain loadShortChain(){
		
		return shortChain;
	}
	
	
}
