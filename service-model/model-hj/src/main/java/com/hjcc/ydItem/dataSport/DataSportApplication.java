package com.hjcc.ydItem.dataSport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.jeremy.eureka_common")
@ComponentScan(basePackages = "com.hjcc.ydItem.dataSport")
@SpringBootApplication
public class DataSportApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataSportApplication.class, args);
	}

}
