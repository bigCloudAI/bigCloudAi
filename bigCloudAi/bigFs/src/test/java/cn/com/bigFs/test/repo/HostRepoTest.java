package cn.com.bigFs.test.repo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.bigFs.entry.Host;
import cn.com.bigFs.repo.HostRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HostRepoTest {
	@Autowired
	private HostRepo hostRepo;
	
	@Test
	public void save() {
		System.out.println("save start");
		hostRepo.save(new Host()); 
		hostRepo.save(new Host()); 
		hostRepo.save(new Host()); 
		System.out.println("save end");
		System.out.println("find all start");
		List<Host> hosts = hostRepo.findAll();
		System.out.println(hosts.size());
		System.out.println("find all end");
		
	}
	
	//@Test
	public void findAll() {
		System.out.println("find all start");
		List<Host> hosts = hostRepo.findAll();
		System.out.println(hosts.size());
		System.out.println("find all end");
	}
}
