package io.github.bigCloudAi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class AppStart {
	public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
		System.out.println();
		
	}
}
