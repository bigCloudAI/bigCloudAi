package cn.com.aiApp.webqq;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebqqSocket implements Runnable {

	public String cookiePtUserId = "3312051082952809518";
	public String cookiePtLoginSig = "PB4yRKweZ71KjaFNAvJM6bTHdCAEi9kAE2za*Nb-vOkmBL-2i5CDSuGGZzkEPNch";
	public String cookiePtClientIp = "967edcf8f37ae9c8";
	public String cookiePtServerIp = "a3980af492097aab";
	public String cookieLoginParam = "daid%3D164%26target%3Dself%26style%3D16%26mibao_css%3Dm_webqq%26appid%3D501004106%26enable_qlogin%3D0%26no_verifyimg%3D1%26s_url%3Dhttp%253A%252F%252Fw.qq.com%252Fproxy.html%26f_url%3Dloginerroralert%26strong_login%3D1%26login_state%3D10%26t%3D20131024001";
	public String cookieUikey = "dd40ef970b33ac795b73dd67912c430ae1a79f86714ff53ff175105bf9fc22bc";
	public String cookiePtuiIdentifier = "000D54FAC028F09EB53AA632DDA353B5CD2FBA808C75A369FDC94CF6";
	
	public String cookieQrsig = "xxxxxxxxxxxxxxxxxxxxxx";
	
	public String cookiePtwebqq = "";	
	
	public Map<String,String> cookieMaps=new HashMap<String,String>();
	public Map<String,Map<String,String>> cookieMapMaps=new HashMap<String,Map<String,String>>();
	
	public String content;

	public WebqqSocket(Socket webqqSocket) {
		this.webqqSocket = webqqSocket;
	}

	public WebqqSocket() {
	}

	public Socket webqqSocket;
	int contentLength;
	int contentType;// 0 1 png

	@Override
	public void run() {
		try {
			webqqSocket.setSoTimeout(30000);
		} catch (SocketException e) {
		}
		try {
			DataInputStream dis = new DataInputStream(this.webqqSocket.getInputStream());
			System.out.println("read");
			String line = dis.readLine();// dis.readLine();
			int i = 0;
			while (line != null) {
				System.out.println("line->" + line);
				line = dis.readLine();
				if ("".equals(line.trim())) {
					break;
				} else if (line.indexOf("Content-Length") != -1) {
					this.contentLength = Integer.parseInt(line.substring(line.indexOf("Content-Length") + 16));
				} else if (line.indexOf("image/png") != -1) {
					contentType = 1;
				}else if(line.indexOf("Content-Type: application/x-javascript;") != -1){
					contentType = 0;
				} else if (line.indexOf("Set-Cookie") != -1) {
					String[] cookies = line.split(":")[1].split(";");
					for (int j = 0; j < cookies.length; j++) {
						if(line.contains("DOMAIN=web2.qq.com;")){
							if(cookies[j].indexOf("pt4_token") != -1||cookies[j].indexOf("p_uin") != -1
									||cookies[j].indexOf("p_skey") != -1){
								cookieMaps.put(cookies[j].split("=")[0].trim(), cookies[j].split("=")[1].trim());
							}
						}
						if(cookies[j].indexOf(" skey=") != -1){
							cookieMaps.put(cookies[j].split("=")[0].trim(), cookies[j].split("=")[1].trim());
						}
						if (cookies[j].indexOf("qrsig") != -1) {
							this.setCookieQrsig(cookies[j].split("=")[1]);
						}
						if (cookies[j].indexOf("pt_user_id") != -1) {
							this.setCookiePtUserId(cookies[j].split("=")[1]);
						}
						if (cookies[j].indexOf("pt_login_sig") != -1) {
							this.setCookiePtLoginSig(cookies[j].split("=")[1]);
						}
						if (cookies[j].indexOf("pt_clientip") != -1) {
							this.setCookiePtClientIp(cookies[j].split("=")[1]);
						}
						if (cookies[j].indexOf("pt_serverip") != -1) {
							this.setCookiePtServerIp(cookies[j].split("=")[1]);
						}
						if (cookies[j].indexOf("login_param") != -1) {
							this.setCookieLoginParam(cookies[j].split("=")[1]);
						}
						if (cookies[j].indexOf("uikey") != -1) {
							this.setCookieUikey(cookies[j].split("=")[1]);
						}
						if (cookies[j].indexOf("ptui_identifier") != -1) {
							this.setCookiePtuiIdentifier(cookies[j].split("=")[1]);
						}
						if (cookies[j].indexOf("ptwebqq") != -1) {
							this.cookiePtwebqq=cookies[j].split("=")[1];
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

	public void readDataAll(DataInputStream reader) throws Exception {
		List<Byte> conbyteAlls= new ArrayList<Byte>(); 
		while((this.contentLength=reader.available())!=0){
			System.out.println(this.contentLength);
			byte[] conbytes = new byte[this.contentLength];
			reader.read(conbytes, 0, this.contentLength);
			for (int i = 0; i < conbytes.length; i++) {
				conbyteAlls.add(conbytes[i]);
			}
			Thread.sleep(1000);
		}
		byte[] conbyteAllss = new byte[conbyteAlls.size()];
		for (int i = 0; i < conbyteAllss.length; i++) {
			conbyteAllss[i]=conbyteAlls.get(i);
		}
		String con = new String(conbyteAllss);
		System.out.println(con);
	} 
	
	public void readDataByteO(DataInputStream reader) throws Exception{
		this.contentLength=reader.available();
			//System.out.println("-->"+this.contentLength);
			byte[] conbytes = new byte[this.contentLength];
			reader.read(conbytes,0, this.contentLength);
			String con =  new String(conbytes,"UTF-8");//   reader.readUTF();
			System.out.println(con);
			this.content=con;
	} 
	public void readDataByte(DataInputStream reader) throws Exception{
		while((this.contentLength=reader.available())!=0){
			//System.out.println("-->"+this.contentLength);
			byte[] conbytes = new byte[this.contentLength];
			reader.read(conbytes,0, this.contentLength);
			String con =  new String(conbytes,"UTF-8");//   reader.readUTF();
			//System.out.println(con);
			this.content+=con;
			Thread.sleep(1000);
		}
		this.content=this.content.replaceAll("\r\n.{2,5}\r\n", "");
		System.err.println(this.content);
	} 
	public void readData(DataInputStream reader) throws Exception {
		switch (contentType) {
		case 0:
			this.readDataByteO(reader);
			break;
		case 2:
			this.readDataByte(reader);
			this.contentType = 0;
			break;
		case 1:
			String cPic = "F:/cPic.png";
			File file = new File(cPic);
			FileOutputStream fos = new FileOutputStream(file);
			int len;
			byte[] b = new byte[this.contentLength];
			len = reader.read(b, 0, this.contentLength);
			fos.write(b, 0, len);
			fos.flush();
			fos.close();
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler file:///" + cPic);
			this.contentType = 0;
			break;
		default:
			break;
		}
	}

	public String getCookieQrsig() {
		return cookieQrsig;
	}

	public void setCookieQrsig(String cookieQrsig) {
		this.cookieQrsig = cookieQrsig;
	}

	public String getCookiePtUserId() {
		return cookiePtUserId;
	}

	public void setCookiePtUserId(String cookiePtUserId) {
		this.cookiePtUserId = cookiePtUserId;
	}

	public String getCookiePtLoginSig() {
		return cookiePtLoginSig;
	}

	public void setCookiePtLoginSig(String cookiePtLoginSig) {
		this.cookiePtLoginSig = cookiePtLoginSig;
	}

	public String getCookiePtClientIp() {
		return cookiePtClientIp;
	}

	public void setCookiePtClientIp(String cookiePtClientIp) {
		this.cookiePtClientIp = cookiePtClientIp;
	}

	public String getCookiePtServerIp() {
		return cookiePtServerIp;
	}

	public void setCookiePtServerIp(String cookiePtServerIp) {
		this.cookiePtServerIp = cookiePtServerIp;
	}

	public String getCookieLoginParam() {
		return cookieLoginParam;
	}

	public void setCookieLoginParam(String cookieLoginParam) {
		this.cookieLoginParam = cookieLoginParam;
	}

	public String getCookieUikey() {
		return cookieUikey;
	}

	public void setCookieUikey(String cookieUikey) {
		this.cookieUikey = cookieUikey;
	}

	public String getCookiePtuiIdentifier() {
		return cookiePtuiIdentifier;
	}

	public void setCookiePtuiIdentifier(String cookiePtuiIdentifier) {
		this.cookiePtuiIdentifier = cookiePtuiIdentifier;
	}

	public Socket getWebqqSocket() {
		return webqqSocket;
	}

	public void setWebqqSocket(Socket webqqSocket) {
		this.webqqSocket = webqqSocket;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

}
