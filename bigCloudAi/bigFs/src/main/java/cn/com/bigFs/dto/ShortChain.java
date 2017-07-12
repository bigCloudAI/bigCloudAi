package cn.com.bigFs.dto;

import org.springframework.stereotype.Component;

@Component
public class ShortChain {

	//短链，避免账本太长，加载慢使用
		HostResPool hostResPool; //主机资源池 （主机列表，主机的资源量）   ,资源账本
		
		Fs fs;//文件系统（目录，文件 及大小. 包含文件存储位置）,文件账本
}
