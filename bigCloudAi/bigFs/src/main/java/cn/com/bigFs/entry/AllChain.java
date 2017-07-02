package cn.com.bigFs.entry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AllChain {
	//全链
	List<Block> chainBlock  = new  ArrayList<Block>(); //所有区块，顺序。通过解析 链账本得到
	
	HostResPool hostResPool = new HostResPool(); //主机资源池 （主机列表，主机的资源量）   ,资源账本
	
	Fs fs = new Fs();//文件系统（目录，文件 及大小. 包含文件存储位置）,文件账本
	
	public boolean newBlock(Block newBlock){
		chainBlock.add(newBlock);
		return true;
	}
	
}
