package com.example.springbootuniversitiesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringBootUniversitiesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUniversitiesApiApplication.class, args);
	}

}
