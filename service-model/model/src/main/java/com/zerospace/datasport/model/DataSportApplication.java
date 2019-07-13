package com.zerospace.datasport.model;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableAutoConfiguration
@EnableFeignClients(basePackages = "com.zerospace.datasport.joggle")
@EnableEurekaClient
@SpringBootApplication
public class DataSportApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataSportApplication.class, args);
	}

}
