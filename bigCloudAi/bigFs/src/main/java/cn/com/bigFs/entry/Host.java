package cn.com.bigFs.entry;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import net.minidev.json.annotate.JsonIgnore;

@Component
@Entity
public class Host {
	
	@Id
	@GeneratedValue
	int hostId;
	
	String ip;
	
	int cpu;
	
	long diskSpace;
	
	long memory;
	
	double cpuused;
	
	long diskSpaceused;
	
	long memoryused;
	
	@JsonIgnore
	@Transient
	List<MinFile> minFile;
	
	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public long getDiskSpace() {
		return diskSpace;
	}

	public void setDiskSpace(long diskSpace) {
		this.diskSpace = diskSpace;
	}

	public long getMemory() {
		return memory;
	}

	public void setMemory(long memory) {
		this.memory = memory;
	}

	public double getCpuused() {
		return cpuused;
	}

	public void setCpuused(double cpuused) {
		this.cpuused = cpuused;
	}

	public long getDiskSpaceused() {
		return diskSpaceused;
	}

	public void setDiskSpaceused(long diskSpaceused) {
		this.diskSpaceused = diskSpaceused;
	}

	public long getMemoryused() {
		return memoryused;
	}

	public void setMemoryused(long memoryused) {
		this.memoryused = memoryused;
	}

	public List<MinFile> getMinFile() {
		return minFile;
	}

	public void setMinFile(List<MinFile> minFile) {
		this.minFile = minFile;
	}

	

}
