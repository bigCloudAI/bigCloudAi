package io.github.bigCloudAi.dataSource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//@Configuration
public class DBConfig {
   @Bean(name = "dataSource")     //声明其为Bean实例
   @Primary  //在同样的DataSource中，首先使用被标注的DataSource
   public DataSource dataSource(){
	   DriverManagerDataSource ds = new DriverManagerDataSource ();      
	   ds.setDriverClassName("com.mysql.jdbc.Driver");   
	   /*use mysql;
	   ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123';
	   flush privileges;*/
	   
	   ds.setUrl("jdbc:mysql://192.168.80.231:3306/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false");      
	   ds.setUsername("root");      
	   ds.setPassword("123");   
	   return ds;
	   //Connection actualCon = ds.getConnection();  
  }
   
   public static  void changeDs6(DriverManagerDataSource ds) {
	   ds.setUrl("jdbc:mysql://192.168.80.231:3306/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false");      
   }
   public static  void changeDs7(DriverManagerDataSource ds) {
	   ds.setUrl("jdbc:mysql://192.168.80.231:3307/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false");      
   }
   
   public static  void changeDs8(DriverManagerDataSource ds) {
	   ds.setUrl("jdbc:mysql://192.168.80.231:3308/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false");      
   }
}
