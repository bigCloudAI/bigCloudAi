方式一：整合spring在web环境中
第一步： 在spring配置文件中配置,该服务会随着tomcat加载web应用启动而启动,随着tomcat的关闭而关闭
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
	 http://www.springframework.org/schema/aop  http://www.springframework.org/schema/aop/spring-aop-2.5.xsd">
	<bean id="mydfsStorageServer" class="mydfs.storage.server.MydfsStorageServer" 
		lazy-init="false" init-method="startServer" 
		destroy-method="stopServer">
		<property name="host" value="127.0.0.1"/>
		<property name="port" value="9999"/>
		<property name="workers" value="10"/>
		<property name="basepath" value="E:/data/mydfs/store"/>
	</bean>
	<bean id="mydfsTrackerServer" class="mydfs.storage.server.MydfsTrackerServer" scope="prototype">
		<property name="host" value="127.0.0.1"/>
		<property name="port" value="9999"/>
	</bean>
</beans>
 
第二步：在web.xml中配置
	<!-- Begin Author:wuqiwei:Data:2014-06-05 AddReason:内嵌式小型分布式文件系统集成 -->
	 <servlet>
		<servlet-name>storageClient</servlet-name>
		<servlet-class>mydfs.storage.server.MydfsServerServlet</servlet-class>
		<!--127.0.0.1:9999 分别是指向dfsServer服务的ip和端口  -->
		<init-param>
			<param-name>mydfsServerHost:Port</param-name>
			<param-value>127.0.0.1:9999</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>storageClient</servlet-name>
		<url-pattern>/group/M00/*</url-pattern>
	</servlet-mapping>
	<!-- End Author:wuqiwei:Data:2014-06-05 AddReason:内嵌式小型分布式文件系统集成 -->

第三步：编写java代码
  // 第一个参数：inputStream 上传的文件流  第二个参数：extention：文件的后缀名
  String storepath= mydfsTrackerServer.upload(inputStream, extention);


方式二：作为单独服务启动
  第一步： ant jar 执行之后会生成一个可执行的mydfsServer.jar文件
  第二步：java -jar mydfsServer.jar
       mydfsServer.jar 文件中有mydfs.properties文件，里面有ip和端口相关配置可以进行配置
                 默认端口9999 host默认127.0.0.1 线程数worker默认是5 基路径basepath默认：E:/data/mydfs/store/
  第三步：编写客户端代码将mydfsServer.jar放入客户端的的环境变量中
    MydfsTrackerServer client=new MydfsTrackerServer("127.0.0.1", 9999);
  
  
方式三：硬编码
如果不是在spring的web的环境中,在代码中使用方式
 0.启动服务
           第一个参数:启动的端口号  第二个参数:图片存放地址  第三个参数:工作线程数  第四个参数:监听ip地址
    MydfsStorageServer storageServer=new MydfsStorageServer(9999,"D:/data/mydfs/store",4,"127.0.0.1");
    storageServer.startServer();
 1.上传图片,获取上传图片之后的路径
	MydfsTrackerServer client=new MydfsTrackerServer("localhost", 9999);
	InputStream inputStream = new FileInputStream("D:/20130412062742872.jpg");
	String storepath = client.upload(inputStream,"jpg");
	System.out.println(storepath);
 2.获取上传的图片数据
    MydfsTrackerServer client=new MydfsTrackerServer("127.0.0.1", 9999);
	String url="http://www.jobs.com/group/M00/EF/2C/3696-CCD2-43E7-9854-6B51F6AA2315.jpg?w=100&h=100";
	InputStream inputStream=client.receiveData(url);
 3.删除上传的图片数据
   MydfsTrackerServer client=new MydfsTrackerServer("127.0.0.1", 9999);
   String url="http://www.jobs.com/group/M00/78/79/53D9-34E2-4110-BDBA-8BF808E2C4BD.swf";
   boolean success = client.removeData(url);
   System.out.println("删除情况："+success);
 
