package cn.com.aiApp.graphlab.rdd;

public interface RDDinf {

	public String getPath();
	public void setPath(String path);
	public String getType() ;
	public void setType(String type) ;
	public abstract Object showTop();
	public abstract String[][] showContinue() ;
	public void endShowCon();
	
}
