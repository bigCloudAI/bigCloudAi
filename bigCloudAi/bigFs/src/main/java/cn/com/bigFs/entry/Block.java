package cn.com.bigFs.entry;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import net.minidev.json.annotate.JsonIgnore;

@Component
@Entity
public class Block {

//{block:1,event:add,object:{hostIp:'192.168.0.1',cpu:189812312,diskSpace:189812312,memory:189812312,cpuused:189812312,diskSpaceused:189812312,memoryused:189812312},handler:'192.168.0.1'}
//{block:2,event:update,object:{hostIp:'192.168.0.1',cpu:189812312,diskSpace:189812312,memory:189812312,cpuused:189812312,diskSpaceused:189812312,memoryused:189812312},handler:'192.168.0.1',fs:{where:'/root',event:'mkdir',object:{name:'jjsjhahd'}}}
//{block:3,event:update,object:{hostIp:'192.168.0.1',cpu:189812312,diskSpace:189812312,memory:189812312,cpuused:189812312,diskSpaceused:189812312,memoryused:189812312},handler:'192.168.0.1',fs:{where:'/root',event:'put',object:{name:'jjsjhahd.txt',file:[{0990901,192.5.4.1,192.5.4.2，192.5.4.4},{0990902,192.5.4.1,192.5.4.5,192.5.4.4}....{09909100,192.5.4.1,192.5.4.5,192.5.4.4}]}}}
	@Id
	@GeneratedValue
	int block;
	
	String event;//add update remove
	
	String objIp;
	String handlerIp;
	
	@JsonIgnore
	@Transient
	Host object;
	
	@JsonIgnore
	@Transient
	Map<String,String> handler;
	
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public Host getObject() {
		return object;
	}
	public void setObject(Host object) {
		this.object = object;
	}
	public Map<String, String> getHandler() {
		return handler;
	}
	public void setHandler(Map<String, String> handler) {
		this.handler = handler;
	}
	public String getObjIp() {
		return objIp;
	}
	public void setObjIp(String objIp) {
		this.objIp = objIp;
	}
	public String getHandlerIp() {
		return handlerIp;
	}
	public void setHandlerIp(String handlerIp) {
		this.handlerIp = handlerIp;
	}
}
