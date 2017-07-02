package cn.com.bigFs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.bigFs.entry.FsNode;
import cn.com.bigFs.repo.FsNodeRepo;

@Service
public class FsService {
	
	
	@Autowired
	FsNodeRepo fsNodeRepo;
	
	public FsNode findFsNode(String where) throws Exception{
		String[] fss=where.split(">");
		FsNode fsNode = new FsNode();
		fsNode.setFsNodeId(0);
		for (int i = 1; i < fss.length; i++) {
			fsNode = fsNodeRepo.findByParentNodeIdAndName(fsNode.getFsNodeId(),fss[i]);
			if(fsNode==null||fsNode.getType()==1){
				throw new Exception("没有该路径");
			}
		}
		return fsNode;
	}
	
	
	public FsNode findFsNode(String where,String dirNameOrfileName) throws Exception{
		FsNode fsNode = this.findFsNode(where);
		FsNode fsNodeChild = fsNodeRepo.findByParentNodeIdAndName(fsNode.getFsNodeId(),dirNameOrfileName);
		if(fsNodeChild!=null){
			throw new Exception("该文件或文件夹已存在");
		}
		return fsNode;
		
	}
	
	public List<FsNode> findFsNodeChildNode(String where) throws Exception{
		 List<FsNode> childFsNode = fsNodeRepo.findByParentNodeId(this.findFsNode(where).getFsNodeId());//子文件，目录时  有值
		return childFsNode;
	}
	
	public String fsNodeMkdir(String where,String dirName) throws Exception{
		FsNode newFsNode = new FsNode();
		newFsNode.setName(dirName);
		newFsNode.setType(0);
		newFsNode.setParentNodeId(this.findFsNode(where,dirName).getFsNodeId());
		fsNodeRepo.save(newFsNode);
		return "success";
	}
	
	
	public String fsNodePut(String where, String fileName, MultipartFile partFile) throws Exception{
		FsNode newFsNode = new FsNode();
		newFsNode.setName(fileName);
		newFsNode.setType(1);
		newFsNode.setParentNodeId(this.findFsNode(where,fileName).getFsNodeId());
		fsNodeRepo.save(newFsNode);
		System.out.println(partFile);
		return "success";
	}
	

}
