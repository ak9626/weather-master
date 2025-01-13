package com.example.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName: UniversityApplication
 * Package: com.example.university
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 19:55
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.example.common", "com.example.university"})
public class UniversityApplication {
    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }
}