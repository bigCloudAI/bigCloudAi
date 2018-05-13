/*package io.github.bigCloudAi.ctrl;

import java.sql.SQLException;

import org.hibernate.boot.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.bigCloudAi.dataSource.DBConfig;
import io.github.bigCloudAi.repo.TaskRepo;
import io.github.bigCloudAi.util.SpringUtil;

*//**
 * 项目各个界面的请求地址
 *
 * @since 2017-03-28
 *//*
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
//		Configuration config = new Configuration().configure();
		System.out.println();
		ServiceRegistry ssr = (ServiceRegistry)SpringUtil.getBean(ServiceRegistry.class);
      System.err.println(ssr);
      
		LocalContainerEntityManagerFactoryBean entityManager = (LocalContainerEntityManagerFactoryBean)SpringUtil.getBean(LocalContainerEntityManagerFactoryBean.class);
		DriverManagerDataSource ds = (DriverManagerDataSource)entityManager.getDataSource();
		System.out.println(ds.getUrl());
		entityManager.afterPropertiesSet();
		
			
		// entityManager.createEntityGraph(Task.class);
      Metadata m = (Metadata)SpringUtil.getBean(Metadata.class);
      System.err.println(m);
		  StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
    schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
		
		return "";
	  }
}
*/