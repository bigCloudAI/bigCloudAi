package cn.com.aiApp.webqq;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

public class Webqq {
	WebqqSocket webqqSocket=new WebqqSocket();
	public static void main2(String[] args) {
		Webqq webqq = new Webqq();
		webqq.login2();
	}
	public static void main(String[] args) {
		Webqq webqq = new Webqq();
		//webqq.getInitCookie();
		/*try {
			Thread.sleep(3000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		webqq.getLoginImg();
		System.out.println(webqq.webqqSocket.cookieQrsig);
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(webqq.webqqSocket.cookieQrsig);
		boolean flag = true;
		String tempcon;
		String checkSigUrl="";
		while(flag){
			try {
				Thread.sleep(3000l);
				webqq.checkLogin();
				Thread.sleep(1500l);
				tempcon = webqq.webqqSocket.content;
				if(tempcon.contains("ptuiCB")){
					String[] tempcons=tempcon.substring(tempcon.indexOf("(") + 1,
							tempcon.indexOf(")")).split(",");
					if(tempcons[0].equals("'0'")){
						System.out.println(tempcons[2]);
						checkSigUrl = tempcons[2].replaceAll("'", "");
						break;
					}
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		webqq.webqqSocket.cookieMaps.clear();
		webqq.checkSig(checkSigUrl);
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		webqq.getvfwebqq();
		try {
			Thread.sleep(1000l);
			webqq.login2();
			Thread.sleep(1000l);
			webqq.get_self_info2();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//{"result":{"cip":23600812,"f":0,"index":1075,"port":47450,"psessionid":"8368046764001d636f6e6e7365727665725f77656271714031302e3133332e34312e383400001ad00000066b026e040015808a206d0000000a406172314338344a69526d0000002859185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857","status":"online","uin":766597324,"user_state":0,"vfwebqq":"59185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857"},"retcode":0}

		
	}
	
	public void getInitCookie(){
		try {
			Socket qqSocket = new Socket("ui.ptlogin2.qq.com",80);
			webqqSocket= new WebqqSocket(qqSocket);
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("GET https://ui.ptlogin2.qq.com/cgi-bin/login?daid=164&target=self&style=16"
					+ "&mibao_css=m_webqq&appid=501004106&enable_qlogin=0&no_verifyimg=1"
					+ "&s_url=http%3A%2F%2Fw.qq.com%2Fproxy.html&f_url=loginerroralert"
					+ "&strong_login=1&login_state=10&t=20131024001 HTTP/2.0\r\n");
			dos.writeBytes("Host: ui.ptlogin2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) "
					+ "Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			dos.writeBytes("Accept-Encoding: gzip, deflate, br\r\n");
			dos.writeBytes("Referer: https://xui.ptlogin2.qq.com/cgi-bin/xlogin?daid=164&target=self&style=40"
					+ "&mibao_css=m_webqq&appid=501004106&enable_qlogin=0&no_verifyimg=1&"
					+ "s_url=http%3A%2F%2Fw.qq.com%2Fproxy.html&f_url=loginerroralert&strong_login=1&"
					+ "login_state=10&t=20131024001\r\n");
			dos.writeBytes("Cookie: pgv_pvid=9799748477; pac_uid=0_59785bcdd778a;"
					+ " pt_guid_sig=544008898e91c21635d3b1be3be34c1f676618f7b663c2ea392723ee239ae0e0;"
					+ " pgv_pvi=7303786496; pt2gguin=o0766597324;"
					+ " pt_recent_uins=ad0fd504b9c461c368dcbdb1160a2b51c66b27e03ecf8525c947d4d6679d4e4d74e6402567f79829667d6e940522deede26b07139f066c76;"
					+ " RK=jSGXfSA7Sb; ptcz=7b4105e3258369c8125ac7a8f36250e4783c0f55a1a3d46706430294cd499d89; "
					+ "qrsig=hS-G3OkSgswoa3U-zWm3ApGmjBasKFP6kk8REFSSKoJ0gGNl*n7nLKnNLm72Xisr; pgv_si=s6388849664\r\n");
			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("Upgrade-Insecure-Requests: 1\r\n");
			dos.writeBytes("\r\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getLoginImg(){
		try {
			Socket qqSocket = new Socket("ssl.ptlogin2.qq.com",80);
			if(webqqSocket!=null){
				webqqSocket.setWebqqSocket(qqSocket);
			}else{
				webqqSocket= new WebqqSocket(qqSocket);
			}
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("GET https://ssl.ptlogin2.qq.com/ptqrshow?appid=501004106&e=2&l=M&s=3&d=72&v=4"
					+ "&t=0.9142399367333609&daid=164 HTTP/2.0\r\n");
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
					+ "pt_clientip="+this.webqqSocket.getCookiePtClientIp()+"; pt_serverip="+this.webqqSocket.getCookiePtServerIp()+";"
					+ " pt_local_token=-1290381635; "
					+ "uikey="+this.webqqSocket.getCookieUikey()+"; "
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
			Socket qqSocket = new Socket("ssl.ptlogin2.qq.com",80);
			if(webqqSocket!=null){
				webqqSocket.setWebqqSocket(qqSocket);
			}else{
				webqqSocket= new WebqqSocket(qqSocket);
			}
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("GET https://ssl.ptlogin2.qq.com/ptqrlogin?"
					+ "ptqrtoken="+this.hash33()+"&webqq_type=10&remember_uin=1"
					+ "&login2qq=1&aid=501004106&u1=http%3A%2F%2Fw.qq.com%2Fproxy.html%3Flogin2qq%3D1%26webqq_type%3D10"
					+ "&ptredirect=0&ptlang=2052&daid=164&from_ui=1&pttype=1&dumy=&fp=loginerroralert"
					+ "&action=0-0-273540&mibao_css=m_webqq&t=undefined&g=1&js_type=0"
					+ "&js_ver=10228&login_sig="
					+ "&pt_randsalt=0 HTTP/2.0\r\n"); 
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
			dos.writeBytes("Cookie:"
					+ " qrsig="+this.webqqSocket.cookieQrsig.trim()+";"
					+ " pgv_si=s6388849664\r\n");
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
	
	public void checkSig(String checkSigUrl){
		try {
			Socket qqSocket = new Socket("ptlogin2.web2.qq.com",80);
			if(webqqSocket!=null){
				webqqSocket.setWebqqSocket(qqSocket);
			}else{
				webqqSocket= new WebqqSocket(qqSocket);
			}
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("GET "+checkSigUrl+" HTTP/2.0\r\n"); 
			
			dos.writeBytes("Host: ptlogin2.web2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			dos.writeBytes("Accept-Encoding: gzip, deflate\r\n");
			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("Upgrade-Insecure-Requests: 1\r\n");
			dos.writeBytes("\r\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getvfwebqq(){
		try {
			Socket qqSocket = new Socket("s.web2.qq.com",80);
			if(webqqSocket!=null){
				webqqSocket.setWebqqSocket(qqSocket);
			}else{
				webqqSocket= new WebqqSocket(qqSocket);
			}
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("GET http://s.web2.qq.com/api/getvfwebqq?ptwebqq="+this.webqqSocket.cookiePtwebqq+"&clientid=53999199"
					+ "&psessionid=&t=1503767669112 HTTP/1.1\r\n"); 
			
			dos.writeBytes("Host: s.web2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept: */*\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			dos.writeBytes("Accept-Encoding: gzip, deflate\r\n");
			dos.writeBytes("Referer: http://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1\r\n");
			dos.writeBytes("Content-Type: utf-8\r\n");
			dos.writeBytes("Cookie: uin=o0766597324; skey=@FIYgpv8OX;"
					+ " p_uin="+webqqSocket.cookieMaps.get("p_uin")+";"
					+ " p_skey="+webqqSocket.cookieMaps.get("p_skey")+";"
					+ " pt4_token="+webqqSocket.cookieMaps.get("pt4_token")+"\r\n");

			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("\r\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//
	public void login2(){
		try {
			Socket qqSocket = new Socket("d1.web2.qq.com",80);
			if(webqqSocket!=null){
				webqqSocket.setWebqqSocket(qqSocket);
			}else{
				webqqSocket= new WebqqSocket(qqSocket);
			}
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("POST http://d1.web2.qq.com/channel/login2 HTTP/1.1\r\n"); 
			dos.writeBytes("Host: d1.web2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			/*dos.writeBytes("Accept-Encoding: gzip, deflate\r\n");*/
			dos.writeBytes("Referer: http://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2\r\n");
			dos.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n");
			dos.writeBytes("Content-Length: 116\r\n");
			dos.writeBytes("Cookie: uin=o0766597324; skey=@FIYgpv8OX;"
					+ " p_uin="+webqqSocket.cookieMaps.get("p_uin")+";"
					+ " p_skey="+webqqSocket.cookieMaps.get("p_skey")+";"
					+ " pt4_token="+webqqSocket.cookieMaps.get("pt4_token")+"\r\n");
			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("\r\n");
			dos.writeBytes("r=%7B%22ptwebqq%22%3A%22%22%2C%22clientid%22%3A53999199%2C%22psessionid%22%3A%22%22%2C%22status%22%3A%22online%22%7D\r\n");
			dos.writeBytes("\r\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void get_self_info2(){
		try {
			Socket qqSocket = new Socket("s.web2.qq.com",80);
			if(webqqSocket!=null){
				webqqSocket.setWebqqSocket(qqSocket);
			}else{
				webqqSocket= new WebqqSocket(qqSocket);
			}
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("GET http://s.web2.qq.com/api/get_self_info2?t=1503778953188 HTTP/1.1\r\n"); 
			dos.writeBytes("Host: s.web2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			/*dos.writeBytes("Accept-Encoding: gzip, deflate\r\n");*/
			dos.writeBytes("Referer: http://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1\r\n");
			dos.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n");
			dos.writeBytes("Cookie: uin=o0766597324; skey=@FIYgpv8OX;"
					+ " p_uin="+webqqSocket.cookieMaps.get("p_uin")+";"
					+ " p_skey="+webqqSocket.cookieMaps.get("p_skey")+";"
					+ " pt4_token="+webqqSocket.cookieMaps.get("pt4_token")+"\r\n");
			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("\r\n");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
 	public String hash33(){
		String t = this.webqqSocket.cookieQrsig;
		int e=0;
		for(int i=0,n=t.length();n>i;++i){
			e+=(e<<5)+t.charAt(i);
		}
		return (2147483647&e)+"";
	}
		
	

}
