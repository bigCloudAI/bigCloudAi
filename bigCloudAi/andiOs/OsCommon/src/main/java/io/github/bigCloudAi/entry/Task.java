package io.github.bigCloudAi.entry;

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

	public Task( int status , String name) {
		super();
		this.status = status;
		this.name = name;
	}

	@Id
	@GeneratedValue
	int id;
	
	int status; //0未执行  1执行中 2执行完 3执行异常 

	String name;
	
	String execer;
	
	/*Date createTime;
	
	Date endTime;*/
	
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


	public String getExecClass() {
		return execClass;
	}

	public void setExecClass(String execClass) {
		this.execClass = execClass;
	}

	public String getExecer() {
		return execer;
	}

	public void setExecer(String execer) {
		this.execer = execer;
	}
	
	
}
