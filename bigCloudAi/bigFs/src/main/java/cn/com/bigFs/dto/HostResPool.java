package cn.com.bigFs.dto;

import java.util.ArrayList;
import java.util.List;

import cn.com.bigFs.entry.Host;

public class HostResPool {
	List<Host> hosts = new ArrayList<Host>();
	int cpu;
	int diskSpace;
	int memory;
	
	int cpuused;
	int diskSpaceused;
	int memoryused;
	
	public boolean newHost(Host newHost){
		hosts.add(newHost);
		return true;
	}
	
}
