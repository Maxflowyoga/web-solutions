package com.xrpl.engine;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.xrpl.xrplcontrollers, com.xrpl.controllers")
public class XRPLApiEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(XRPLApiEngineApplication.class, args);
	}

}
