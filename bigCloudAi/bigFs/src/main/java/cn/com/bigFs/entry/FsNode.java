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
public class FsNode {

	@Id
	@GeneratedValue	
  int fsNodeId;
	
  int type;//0 目录  1 文件
  String name;//:'jjsjhahd.txt',
  int parentNodeId;
   /* file:[{0990901,192.5.4.1,192.5.4.2，192.5.4.4},{0990902,192.5.4.1,192.5.4.5,192.5.4.4}
	.....{09909100,192.5.4.1,192.5.4.5,192.5.4.4}]*/
  
  @JsonIgnore
  @Transient
  FsNode parentId;

  @JsonIgnore
  @Transient
  List<FsNode> childFsNode;//子文件，目录时  有值
  
  @JsonIgnore
  @Transient
  List<MinFile> minFile;//该文件对应的小文件

public int getFsNodeId() {
	return fsNodeId;
}

public void setFsNodeId(int fsNodeId) {
	this.fsNodeId = fsNodeId;
}

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getParentNodeId() {
	return parentNodeId;
}

public void setParentNodeId(int parentNodeId) {
	this.parentNodeId = parentNodeId;
}

public FsNode getParentId() {
	return parentId;
}

public void setParentId(FsNode parentId) {
	this.parentId = parentId;
}

public List<FsNode> getChildFsNode() {
	return childFsNode;
}

public void setChildFsNode(List<FsNode> childFsNode) {
	this.childFsNode = childFsNode;
}

public List<MinFile> getMinFile() {
	return minFile;
}

public void setMinFile(List<MinFile> minFile) {
	this.minFile = minFile;
}
  
  //文件，
  /*文件名
   * a.txt 100。5Mb
   *  100份1MB
   * 第一份
   * 3个节点  一个节点1MB 
   * 第二份
   * 3个节点  一个节点1MB 
   * 。。。。
   * 第101份
   * 3个节点  一个节点0.5MB 
   * */
  //账本信息   a.txt,{0990901,192.5.4.1,192.5.4.2，192.5.4.4},{0990902,192.5.4.1,192.5.4.5,192.5.4.4}
//  .....{09909100,192.5.4.1,192.5.4.5,192.5.4.4}
  
  
  
}
