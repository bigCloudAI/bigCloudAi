package io.github.bigCloudAi.ctrl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.cfg.Configuration;  
import org.hibernate.tool.hbm2ddl.SchemaExport; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.bigCloudAi.dataSource.DBConfig;
import io.github.bigCloudAi.repo.TaskRepo;
import io.github.bigCloudAi.util.SpringUtil;

/**
 * 项目各个界面的请求地址
 *
 * @since 2017-03-28
 */
@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	TaskRepo tr;
	
	@RequestMapping(value ="/1",method = RequestMethod.GET)
	 public String index(){
	        DriverManagerDataSource ds = (DriverManagerDataSource)SpringUtil.getBean("dataSource");
	        DBConfig.changeDs6(ds);
	        tr.findAll();
	        return ds.getUrl();
	    }
	
	@RequestMapping(value ="/2",method = RequestMethod.GET)
	 public String index2(){
		 DriverManagerDataSource ds = (DriverManagerDataSource)SpringUtil.getBean("dataSource");
		 DBConfig.changeDs7(ds);
		 tr.findAll();
		 return ds.getUrl();
	  }
	@RequestMapping(value ="/3",method = RequestMethod.GET)
	 public String index3(){
		 DriverManagerDataSource ds = (DriverManagerDataSource)SpringUtil.getBean("dataSource");
		 DBConfig.changeDs8(ds);
		 tr.findAll();
		 return ds.getUrl();
	  }
	
	
	@RequestMapping(value ="/0",method = RequestMethod.GET)
	 public String index0(){
		EntityManagerFactory emf = null;   
		emf = Persistence.createEntityManagerFactory("myJPA");   
		EntityManager em = emf.createEntityManager();
//		emf..
	/*	Configuration cfg = new Configuration().configure();
		SchemaExport export = new SchemaExport();
		export.
		export.create(true,true);*/
		SchemaExport export = new SchemaExport();
		export.create(targetTypes, metadata);
		return "";
	  }
}
