package cn.com.aiApp.graphlab.rdd;

import org.springframework.stereotype.Component;

@Component
public abstract class RDD implements RDDinf{

	String path;
	String type;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public abstract Object showTop();
	
	public abstract String[][] showContinue() ;
	
}
