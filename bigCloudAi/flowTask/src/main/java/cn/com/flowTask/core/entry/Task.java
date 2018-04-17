package cn.com.flowTask.core.entry;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Task {
	
	public Task() {
		super();
	}

	public Task( int status , String name, Date createTime) {
		super();
		this.status = status;
		this.name = name;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue
	int id;
	
	int status; //0未执行  1执行中 2执行完 3执行异常 

	String name;
	
	Date createTime;
	
	Date endTime;
	
	String execClass;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getExecClass() {
		return execClass;
	}

	public void setExecClass(String execClass) {
		this.execClass = execClass;
	}
	
}
