package com.my.second;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = { "com.my.second.client.web" })
@EnableHystrix
public class SecondWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondWebApplication.class, args);
	}
}
