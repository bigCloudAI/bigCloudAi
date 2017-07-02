package cn.com.bigFs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.bigFs.entry.Block;

public interface BlockRepo extends JpaRepository<Block,Integer>{

}
