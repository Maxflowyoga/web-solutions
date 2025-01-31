package com.example.demo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.helloworld.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.helloworld")
public class ApiEngineTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEngineTestApplication.class, args);
	}

}
