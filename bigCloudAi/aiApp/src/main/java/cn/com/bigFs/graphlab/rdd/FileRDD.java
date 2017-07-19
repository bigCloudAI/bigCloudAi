package cn.com.bigFs.graphlab.rdd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class FileRDD extends RDD {

	public static void main(String[] args) {
		RDDinf rdd = new FileRDD();
		rdd.setPath("G:\\ComWorkSpace2\\bigcloudaiGit\\data\\kddcup.data.corrected");
		rdd.showContinue();
		rdd.showContinue();
		rdd.showContinue();
		rdd.endShowCon();
	}
	
	BufferedReader reader;
	@Override
	public String[][] showTop() {//10行
		return this.showTop(",");
	}
	
	public String[][] showTop(String spro) {
		return this.showTop(spro, 10);
	}
	public String[][] showTop(String spro,int top) {//10行
		String[][] result=null;
		File file = new File(this.getPath());
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 0;
	            while ((tempString = reader.readLine()) != null&&line<top) {
	            	String[] tempStrings = tempString.split(spro);
	            	if(line==0){
	            		result = new String[top][tempStrings.length];
	            	}
	            	result[line] = tempStrings;
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
		return result;
	}

	public String[][] showContinue() {
		return this.showContinue(",");
	}
	public String[][] showContinue(String spro) {
		return this.showContinue(",", 10);
	}
	
	public String[][] showContinue(String spro,int top) {
		String[][] result=null;
		File file = new File(this.getPath());
	        try {
	        	if(reader!=null&&reader.ready()){
	        	}else{
	        		reader = new BufferedReader(new FileReader(file));
	        	}
	            String tempString = null;
	            int line = 0;
	            while ((tempString = reader.readLine()) != null&&line<top) {
	            	String[] tempStrings = tempString.split(spro);
	            	if(line==0){
	            		result = new String[top][tempStrings.length];
	            	}
	            	System.out.println(tempString);
	            	result[line] = tempStrings;
	                line++;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	        }
		return result;
	}
	public void endShowCon(){
			try{
			reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	            }
	        }
    }
		
	}
}
