package cn.com.bigFs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.bigFs.entry.FsNode;

public interface FsNodeRepo extends JpaRepository<FsNode,Integer>{
	
	public FsNode findByParentNodeIdAndName(int parentNodeId,String name);
	
	public List<FsNode> findByParentNodeId(int parentNodeId);

}
