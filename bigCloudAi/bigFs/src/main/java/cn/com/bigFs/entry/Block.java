package cn.com.bigFs.entry;

public class Block {

//{block:1,event:add,object:{hostIp:'192.168.0.1',cpu:189812312,diskSpace:189812312,memory:189812312,cpuused:189812312,diskSpaceused:189812312,memoryused:189812312},handler:'192.168.0.1'}
//{block:2,event:update,object:{hostIp:'192.168.0.1',cpu:189812312,diskSpace:189812312,memory:189812312,cpuused:189812312,diskSpaceused:189812312,memoryused:189812312},handler:'192.168.0.1',fs:{where:'/root',event:'mkdir',object:{name:'jjsjhahd'}}}
//{block:3,event:update,object:{hostIp:'192.168.0.1',cpu:189812312,diskSpace:189812312,memory:189812312,cpuused:189812312,diskSpaceused:189812312,memoryused:189812312},handler:'192.168.0.1',fs:{where:'/root',event:'put',object:{name:'jjsjhahd.txt',file:[{0990901,192.5.4.1,192.5.4.2，192.5.4.4},{0990902,192.5.4.1,192.5.4.5,192.5.4.4}....{09909100,192.5.4.1,192.5.4.5,192.5.4.4}]}}}

	int block;
	String event;//add update remove
	Host object;
	
	
}
