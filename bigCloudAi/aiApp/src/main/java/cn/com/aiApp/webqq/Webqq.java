package cn.com.aiApp.webqq;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Webqq {
	WebqqSocket webqqSocket=new WebqqSocket();
	public static void main(String[] args) {
		Webqq webqq = new Webqq();
		webqq.getLoginImg();
		try {
			Thread.sleep(3000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(webqq.webqqSocket.cookieQrsig);
		webqq.checkLogin();
		
	}
	
	public void getLoginImg(){
		try {
			Socket qqSocket = new Socket("121.51.141.121",80);
			webqqSocket= new WebqqSocket(qqSocket);
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("GET https://ssl.ptlogin2.qq.com/ptqrshow?appid=501004106&e=2&l=M&s=3&d=72&v=4"
					+ "&t=0.01915999622963327&daid=164 HTTP/1.1\r\n");
			dos.writeBytes("Host: ssl.ptlogin2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) "
					+ "Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept: */*\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			dos.writeBytes("Accept-Encoding: gzip, deflate, br\r\n");
			dos.writeBytes("Referer: https://xui.ptlogin2.qq.com/cgi-bin/xlogin?daid=164&target=self&style=40"
					+ "&mibao_css=m_webqq&appid=501004106&enable_qlogin=0&no_verifyimg=1&"
					+ "s_url=http%3A%2F%2Fw.qq.com%2Fproxy.html&f_url=loginerroralert&strong_login=1&"
					+ "login_state=10&t=20131024001\r\n");
			dos.writeBytes("Cookie: pt_login_sig=; "
					+ "pt_clientip=0542701c9adf7612; pt_serverip=24b80abf0e8d672b; pt_local_token=-1290381635; "
					+ "uikey=af1c787f877e80e3115d777413b79b6968a58818e061a0e9b7fbe456ad1c886f; "
					+ "pt_guid_sig=3bc11545777b74b280aa695524ca9294141b1c8ee8d7904cd093cca6644c84f9; "
					+ "pgv_pvi=286536704; pgv_si=s6915505152\r\n");
			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("\r\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void checkLogin(){
		try {
			Socket qqSocket = new Socket("121.51.141.121",80);
//					"ssl.ptlogin2.qq.com",443);
			webqqSocket= new WebqqSocket(qqSocket);
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("GET https://ssl.ptlogin2.qq.com/ptqrlogin?u1=http%3A%2F%2Fw.qq.com%2Fproxy.html"
					+ "&ptqrtoken="+1545312357
					+ "&ptredirect=0"
					+ "&h=1&t=1&g=1&from_ui=1&ptlang=2052"
					+ "&action=0-0-"+System.currentTimeMillis()+"&js_ver=10228&js_type=1"
					+ "&login_sig="
					+ "&pt_uistyle=40&aid=501004106&daid=164"
					+ "&mibao_css=m_webqq& HTTP/1.1\r\n");
			dos.writeBytes("Host: ssl.ptlogin2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) "
					+ "Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept: */*\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			dos.writeBytes("Accept-Encoding: gzip, deflate, br\r\n");
			dos.writeBytes("Referer: https://xui.ptlogin2.qq.com/cgi-bin/xlogin?daid=164&target=self&style=40"
					+ "&mibao_css=m_webqq&appid=501004106&enable_qlogin=0&no_verifyimg=1&"
					+ "s_url=http%3A%2F%2Fw.qq.com%2Fproxy.html&f_url=loginerroralert&strong_login=1&"
					+ "login_state=10&t=20131024001\r\n");
			dos.writeBytes("Cookie: pt_login_sig=UWu3fBZnYDYi5fQ0N75acjpdnPy9JxFTFQ0XSWRWzOFk7goMDWAv1U4T5egKtcA6; "
					+ "pt_clientip=0542701c9adf7612; pt_serverip=24b80abf0e8d672b; pt_local_token=-1290381635; "
					+ "uikey=af1c787f877e80e3115d777413b79b6968a58818e061a0e9b7fbe456ad1c886f; "
					+ "pt_guid_sig=3bc11545777b74b280aa695524ca9294141b1c8ee8d7904cd093cca6644c84f9; "
					+ "pgv_pvi=286536704; pgv_si=s6915505152; qrsig="+this.webqqSocket.cookieQrsig+"\r\n");
			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("Pragma: no-cache\r\n");
			dos.writeBytes("Cache-Control: no-cache\r\n");
			
			dos.writeBytes("\r\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
