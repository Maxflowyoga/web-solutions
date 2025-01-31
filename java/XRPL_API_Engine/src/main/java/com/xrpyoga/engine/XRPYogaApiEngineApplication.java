package com.xrpyoga.engine;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.xrpyoga.xrplcontrollers, com.xrpyoga.controllers")
public class XRPYogaApiEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(XRPYogaApiEngineApplication.class, args);
	}

}
