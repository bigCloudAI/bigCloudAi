package cn.com.bigFs.entry;

import java.util.ArrayList;
import java.util.List;

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
