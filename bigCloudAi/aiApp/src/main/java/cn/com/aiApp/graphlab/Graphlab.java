package cn.com.aiApp.graphlab;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.aiApp.graphlab.rdd.RDD;

public class Graphlab {

	@Autowired
	RDD rdd;
	
	public RDD SFrame(String path){
		rdd.setPath(path);rdd.setType("file");
		return rdd;
	} 
	//加载文件里的数据到模型
	
	
}
