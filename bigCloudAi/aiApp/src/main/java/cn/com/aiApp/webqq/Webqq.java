package cn.com.aiApp.webqq;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.json.JSONObject;



public class Webqq {
	WebqqSocket webqqSocket=new WebqqSocket();
	public String vfwebqq;
	public String psessionid;
	
	
    public Webqq initWebqq(){
		this.getLoginImg();
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean flag = true;
		String tempcon;
		String checkSigUrl="";
		while(flag){
			try {
				Thread.sleep(3000l);
				this.checkLogin();
				Thread.sleep(1500l);
				tempcon = this.webqqSocket.content;
				if(tempcon.contains("ptuiCB")){
					String[] tempcons=tempcon.substring(tempcon.indexOf("(") + 1,
							tempcon.indexOf(")")).split(",");
					if(tempcons[0].equals("'0'")){
						checkSigUrl = tempcons[2].replaceAll("'", "");
						break;
					}
				}
				}catch(Exception e){
				}
		}
		try {
				this.webqqSocket.cookieMaps.clear();
				this.checkSig(checkSigUrl);
				Thread.sleep(1000l);
				this.getvfwebqq();
				Thread.sleep(1000l);
				tempcon = this.webqqSocket.content;
				JSONObject jsonObj = new JSONObject(tempcon);
				this.vfwebqq = jsonObj.getJSONObject("result").getString("vfwebqq");
				this.login2();
				Thread.sleep(1500l);
				tempcon = this.webqqSocket.content;
				tempcon=tempcon.substring(tempcon.indexOf("{"), tempcon.lastIndexOf("}")+1);
				jsonObj = new JSONObject(tempcon);
				this.psessionid = jsonObj.getJSONObject("result").getString("psessionid");
			//	this.send_buddy_msg2("1");
			/*	this.get_self_info2();
				Thread.sleep(1000l);*/
				this.get_user_friends2();
				//Thread.sleep(1000l);
				
		}catch(Exception e){
			e.printStackTrace();
			}
		return this;
    }
	
    public static void main(String[] args) {
		Webqq webqq = new Webqq();
		/*webqq.vfwebqq="86dc3808931dfa15e543e947b01386c63f5359ad755489b3705ea082c7d9e02b2806cd9843dc5861";
		webqq.webqqSocket.cookieMaps.put("p_uin", "o0766597324");
		webqq.webqqSocket.cookieMaps.put("p_skey", "58yDCfcYtMzgfF5HiDGUclcwlh0M0HLtgDXUWe*Nd*4_");
		webqq.webqqSocket.cookieMaps.put("pt4_token", "FAYakgmzjZd873BBRI5wWkHmvemuQky-Sfk0G5NwT0o");
		webqq.webqqSocket.cookieMaps.put("skey", "@BCPmbCVXJ");*/
		
		/*
		Cookie: pgv_pvid=9799748477; 
		pac_uid=0_59785bcdd778a; 
		pgv_pvi=7303786496; 
		pt2gguin=o0766597324; RK=jSGXfSA7Sb; 
		ptcz=7b4105e3258369c8125ac7a8f36250e4783c0f55a1a3d46706430294cd499d89;
		pgv_si=s5603393536; uin=o0766597324;
		ptisp=ctc; 
		skey=@BCPmbCVXJ;
		p_uin=o0766597324; 
		p_skey=HO2GSGDKHuWbUH*KHnY-DVnVp3OmGFDq2wFyL6laGzg_; 
		pt4_token=yMWAnIiA3w1k*cgGh4hZJZZXkK9jKaY8gUJ2Kyjhgok_*/
		
		webqq.vfwebqq="99d8aff29ebe6ca7f14a9476daf7bd0cca7d946406605251a697e546c02991c8d3dba010ca952569";
		webqq.webqqSocket.cookieMaps.put("p_uin", "o0766597324");
		webqq.webqqSocket.cookieMaps.put("p_skey", "w7EtuSJSlXi08Ri3eKAON1GjbaLPvbCl*L00mCFawe0_");
		webqq.webqqSocket.cookieMaps.put("pt4_token", "PGiO27c-yL2EVRgaLi1ggTLOH*qHtHGSScyL3PQfup8_");
		webqq.webqqSocket.cookieMaps.put("skey", "@BCPmbCVXJ");
		webqq.webqqSocket.cookieMaps.put("uin", "766597324");

		//webqq.get_self_info2();
		webqq.get_user_friends2();
		//webqq.hash2();
	}
    
	public static void main2(String[] args) {
		Webqq webqq = new Webqq();
	 //	webqq.send_buddy_msg2Test("1");
  	    webqq = webqq.initWebqq();
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
			dos.writeBytes("POST http://s.web2.qq.com/api/get_self_info2?t=1503900084275 HTTP/1.1\r\n"); 
			dos.writeBytes("Host: s.web2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
		
			dos.writeBytes("Referer: http://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1\r\n");
			dos.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n");
			dos.writeBytes("Cookie: uin=o0766597324; skey="+webqqSocket.cookieMaps.get("skey")+";"
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
	
	public void get_user_friends2(){
		try {
			Socket qqSocket =
					//new Socket("127.0.0.1",8080); 
					new Socket("s.web2.qq.com",80);
			if(webqqSocket!=null){
				webqqSocket.setWebqqSocket(qqSocket);
			}else{
				webqqSocket= new WebqqSocket(qqSocket);
			}
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("POST http://s.web2.qq.com/api/get_user_friends2 HTTP/1.1\r\n"); 
			dos.writeBytes("Host: s.web2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
		
			dos.writeBytes("Referer: http://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1\r\n");
			dos.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n");
			dos.writeBytes("Cookie: uin=o0766597324; skey="+webqqSocket.cookieMaps.get("skey")+";"
					+ " p_uin="+webqqSocket.cookieMaps.get("p_uin")+";"
					+ " p_skey="+webqqSocket.cookieMaps.get("p_skey")+";"
					+ " pt4_token="+webqqSocket.cookieMaps.get("pt4_token")+"\r\n");
			String hash=this.hash2();//"006800F200170087";
			String contenttemp="r=%7B%22vfwebqq%22%3A%22"+this.vfwebqq+"%22%2C%22hash%22%3A%22"+hash+"%22%7D";
			dos.writeBytes("Content-Length: "+contenttemp.length()+"\r\n");
			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("\r\n");
			dos.writeBytes(contenttemp+"\r\n");
			dos.writeBytes("\r\n");
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send_buddy_msg2(String msg){
		System.out.println("POST http://d1.web2.qq.com/channel/send_buddy_msg2 HTTP/1.1");
		System.out.println(this.psessionid);
		String to="2136530392";
		String msgId="91350003";
	   String contenttemp = "r="
	   		+ "%7B%22to%22%3A"+to+"%2C"
	   		+ "%22content%22%3A%22%5B%5C"
	   		+ "%22"+msg+"%5C%22%2C%5B%5C"
	   		+ "%22font%5C%22%2C%7B%5C%22name%5C%22%3A%5C%22%E5%AE%8B%E4%BD%93%5C%22%2C%5C"
	   		+ "%22size%5C%22%3A10%2C%5C%22style%5C%22"
	   		+ "%3A%5B0%2C0%2C0%5D%2C%5C%22color%5C%22"
	   		+ "%3A%5C%22000000%5C%22%7D%5D%5D%22%2C%22face%22"
	   		+ "%3A702%2C%22clientid%22"
	   		+ "%3A53999199%2C%22"
	   		+ "msg_id%22%3A"+msgId+"%2C%22"
			+ "psessionid%22%3A%"+this.psessionid+"%22%7D\r\n";
		try {
			Socket qqSocket = new Socket("d1.web2.qq.com",80);
			if(webqqSocket!=null){
				webqqSocket.setWebqqSocket(qqSocket);
			}else{
				webqqSocket= new WebqqSocket(qqSocket);
			}
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("POST http://d1.web2.qq.com/channel/send_buddy_msg2 HTTP/1.1\r\n"); 
			
			dos.writeBytes("Host: d1.web2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept: */*\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			dos.writeBytes("Referer: http://d1.web2.qq.com/cfproxy.html?v=20151105001&callback=1\r\n");
			dos.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n");
			dos.writeBytes("Cookie: uin=o0766597324; skey=@FIYgpv8OX;"
					+ " p_uin="+webqqSocket.cookieMaps.get("p_uin")+";"
					+ " p_skey="+webqqSocket.cookieMaps.get("p_skey")+";"
					+ " pt4_token="+webqqSocket.cookieMaps.get("pt4_token")+"\r\n");
			dos.writeBytes("Content-Length: "+contenttemp.length()+"\r\n");
			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("\r\n");
			dos.writeBytes(contenttemp);
			//{"to":2986669199,"content":"[\"123\",[\"font\",{\"name\":\"����\",\"size\":10,\"style\":[0,0,0],\"color\":\"000000\"}]]","face":702,"clientid":53999199,"msg_id":35830003,"psessionid":"8368046764001d636f6e6e7365727665725f77656271714031302e3133332e34312e383400001ad00000066b026e040015808a206d0000000a406172314338344a69526d0000002859185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857"}
			dos.writeBytes("\r\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send_buddy_msg2Test(String msg){
		System.out.println("POST http://d1.web2.qq.com/channel/send_buddy_msg2 HTTP/1.1");
		System.out.println(this.psessionid);
	   String contenttemp = "r=%7B%22to%22%3A2136530392%2C%22content%22%3A%22%5B%5C%22"+msg+"%5C%22%2C%5B%5C%22font%5C%22%2C%7B%5C%22name%5C%22%3A%5C%22%E5%AE%8B%E4%BD%93%5C%22%2C%5C%22size%5C%22%3A10%2C%5C%22style%5C%22%3A%5B0%2C0%2C0%5D%2C%5C%22color%5C%22%3A%5C%22000000%5C%22%7D%5D%5D%22%2C%22face%22%3A702%2C%22"
	   		+ "clientid%22%3A53999199%2C%22msg_id%22%3A91350003%2C%22"
				+ "psessionid%22%3A%228368046764001d636f6e6e7365727665725f77656271714031302e3133332e34312e383400001ad00000066b026e040015808a206d0000000a406172314338344a69526d0000002859185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857%22%7D";
		try {
			Socket qqSocket = //new Socket("127.0.0.1",8080);
			 new Socket("d1.web2.qq.com",80);
			if(webqqSocket!=null){
				webqqSocket.setWebqqSocket(qqSocket);
			}else{
				webqqSocket= new WebqqSocket(qqSocket);
			}
			new Thread(webqqSocket).start(); 
			DataOutputStream dos = new DataOutputStream(qqSocket.getOutputStream()); 
			dos.writeBytes("POST http://d1.web2.qq.com/channel/send_buddy_msg2 HTTP/1.1\r\n"); 
			
			dos.writeBytes("Host: d1.web2.qq.com\r\n");
			dos.writeBytes("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0\r\n");
			dos.writeBytes("Accept: */*\r\n");
			dos.writeBytes("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			dos.writeBytes("Referer: http://d1.web2.qq.com/cfproxy.html?v=20151105001&callback=1\r\n");
			dos.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n");
			dos.writeBytes("Cookie: uin=o0766597324; skey=@gCxsDKoKq;"
					+ " p_skey=J0N3jYWWH7JFzx9usTLL9QElwViu6h13z1RU1nGlnwM_;"
					+ " pt4_token=IG*LOTQgXCqa9JuJJ4f5q0FoA-4kt4vZ8UTlgtL*nv0_\r\n");
			dos.writeBytes("Content-Length: "+contenttemp.length()+"\r\n");
			dos.writeBytes("Connection: keep-alive\r\n");
			dos.writeBytes("\r\n");
			dos.writeBytes(contenttemp+"\r\n");
			//{"to":2986669199,"content":"[\"123\",[\"font\",{\"name\":\"����\",\"size\":10,\"style\":[0,0,0],\"color\":\"000000\"}]]","face":702,"clientid":53999199,"msg_id":35830003,"psessionid":"8368046764001d636f6e6e7365727665725f77656271714031302e3133332e34312e383400001ad00000066b026e040015808a206d0000000a406172314338344a69526d0000002859185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857"}
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
		
 	
	public String hash2(){
		String js= "var hash2 = function(uin,ptvfwebqq){\n" +
						"            uin += \"\";\n" + 
						"            var ptb = [];\n" + 
						"            for (var i=0;i<ptvfwebqq.length;i++){\n" + 
						"                var ptbIndex = i%4;\n" + 
						"                ptb[ptbIndex] ^= ptvfwebqq.charCodeAt(i);\n" + 
						"            }\n" + 
						"            var salt = [\"EC\", \"OK\"];\n" + 
						"            var uinByte = [];\n" + 
						"            uinByte[0] = (((uin >> 24) & 0xFF) ^ salt[0].charCodeAt(0));\n" + 
						"            uinByte[1] = (((uin >> 16) & 0xFF) ^ salt[0].charCodeAt(1));\n" + 
						"            uinByte[2] = (((uin >> 8) & 0xFF) ^ salt[1].charCodeAt(0));\n" + 
						"            uinByte[3] = ((uin & 0xFF) ^ salt[1].charCodeAt(1));\n" + 
						"            var result = [];\n" + 
						"            for (var i=0;i<8;i++){\n" + 
						"                if (i%2 == 0)\n" + 
						"                    result[i] = ptb[i>>1];\n" + 
						"                else\n" + 
						"                    result[i] = uinByte[i>>1];\n" + 
						"            }\n" + 
						"            return byte2hex(result);\n" + 
						"        };\n" + 
						"        var byte2hex = function(bytes){//bytes array\n" + 
						"            var hex = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'];\n" + 
						"            var buf = \"\";\n" + 
						"            for (var i=0;i<bytes.length;i++){\n" + 
						"                buf += (hex[(bytes[i]>>4) & 0xF]);\n" + 
						"                buf += (hex[bytes[i] & 0xF]);\n" + 
						"            }\n" + 
						"            return buf;\n" + 
						"        }\n";
		 ScriptEngineManager mgr = new ScriptEngineManager();
         ScriptEngine engine = mgr.getEngineByName("javascript");
        /* engine.put("a", 4);   
         engine.put("b", 3);   
         Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);*/ 
         String hash="";
         try {
        	 engine.eval(js);
			engine.eval("hash = hash2(\""+this.webqqSocket.cookieMaps.get("uin")+"\",\""+this.webqqSocket.cookiePtwebqq+"\");");
			hash = (String)engine.get("hash");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return hash;
	}

}
