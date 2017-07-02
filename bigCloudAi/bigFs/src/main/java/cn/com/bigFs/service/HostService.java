package cn.com.bigFs.service;

import java.net.InetAddress;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Service;

import cn.com.bigFs.entry.Host;

@Service
public class HostService {

	
	public Host getSelfHost() throws Exception{
		Host host = new Host();

		Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
		InetAddress addr;
	    addr = InetAddress.getLocalHost();
	    String ip = addr.getHostAddress();
	    host.setIp(ip);
	    host.setMemory(mem.getTotal() / 1024L);
	    host.setMemoryused(mem.getUsed() / 1024L);
	    host.setCpu(sigar.getCpuPercList().length);
	    double idle=0.0;
	    for (int i = 0; i < sigar.getCpuPercList().length; i++) {
	    	CpuPerc cpu = sigar.getCpuPercList()[i];
	    	System.out.println(cpu.getIdle());
	    	idle+=cpu.getIdle();
	    }
	    host.setCpuused(idle);
	    FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
        	FileSystem fs = fslist[i];
        	FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
        	if(fs.getDirName().equals("G:\\")){
        		host.setDiskSpace(usage.getTotal());
        		host.setDiskSpaceused(usage.getUsed());
        	}
         }
        return host;
	}
}
