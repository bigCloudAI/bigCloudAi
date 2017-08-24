package cn.com.aiApp.webqq;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class WebqqSocket implements Runnable {
	
	public String cookieQrsig="0E5f8OZ27ZM5Dro6RFRizU3fCfGJsjx9INjWWNBa8QsHVDmVuXyRm1v3y5GsO9cK";
	
	public WebqqSocket(Socket webqqSocket) {
		this.webqqSocket=webqqSocket;
	}
	public WebqqSocket() {
	}
	
	public Socket webqqSocket;
	int contentLength;
	int contentType;//0    1 png
	@Override
	public void run() {
		try {
			webqqSocket.setSoTimeout(30000);
		} catch (SocketException e) {
		}
		try {
			DataInputStream dis = new DataInputStream(
					this.webqqSocket.getInputStream());
			System.out.println("read");
			String line = dis.readLine();
			int i =0;
	        while (line != null) {
	        	System.out.println("line->"+line);
	            line = dis.readLine();
	            if ("".equals(line.trim())) {
	            		break;
	             }else if (line.indexOf("Content-Length") != -1) {
	                 this.contentLength = Integer.parseInt(line.substring(line.indexOf("Content-Length") + 16));
	             }else if(line.indexOf("image/png")!= -1){
	            	 contentType=1;
	             }else if(line.indexOf("Set-Cookie")!= -1){
	            	String[] cookies= line.split(":")[1].split(";");
	            	for (int j = 0; j < cookies.length; j++) {
						if(cookies[j].indexOf("qrsig")!=-1){
							this.cookieQrsig=cookies[j].split("=")[1];
						}
					}
	             }
	        }
	        System.out.println("begin reading data......");
	        readData(dis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.webqqSocket.close();
			} catch (IOException e) {
			}
		}
	}
	
	public void readData(DataInputStream reader) throws Exception {
      switch (contentType) {
	  case 0:
		  String line="";
		  while (line != null) {
		  line = reader.readLine();
		  System.out.println("line->"+line);
		  }
		break;
	  case 1:
		  File file = new File("C:/Users/Administrator/Desktop/cPic.png");
			FileOutputStream fos = new FileOutputStream(file);
			int len;
			byte[] b = new byte[this.contentLength];
			len=reader.read(b,0,this.contentLength);
			fos.write(b,0,len);
			fos.flush();
			fos.close();
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler file:///C:/Users/Administrator/Desktop/cPic.png");
		break;
	  default:
		break;
	}
	}

}
