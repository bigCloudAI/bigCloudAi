package cn.com.bigFs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.bigFs.entry.MinFile;

public interface MinFileRepo extends JpaRepository<MinFile,Integer>{

}
