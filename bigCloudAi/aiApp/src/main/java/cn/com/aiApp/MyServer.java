package cn.com.aiApp;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	public static void main(String[] args) throws IOException{
		ServerSocket svrSocket = new ServerSocket(8080);
		while(true){
			Socket socket = svrSocket.accept();
			//足够大的一个缓冲区
			byte[] buf = new byte[1024*1024];
			InputStream in = socket.getInputStream();
			int byteRead = in.read(buf, 0, 1024*1024);
			String dataString = new String(buf, 0, byteRead);
			System.out.println(dataString);
			in.close();
			socket.close();
		}
	}
}
