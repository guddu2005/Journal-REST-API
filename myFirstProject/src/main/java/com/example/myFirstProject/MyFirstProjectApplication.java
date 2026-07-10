package com.example.myFirstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class MyFirstProjectApplication {

	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(MyFirstProjectApplication.class , args);
//		ConfigurableEnvironment environment = context.getEnvironment();
//		System.out.println(environment.getActiveProfiles());
		SpringApplication.run(MyFirstProjectApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}


}

// platformTransactionManager
// MangoTransactionManager
