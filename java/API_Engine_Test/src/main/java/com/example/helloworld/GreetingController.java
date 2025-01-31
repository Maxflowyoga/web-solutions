package com.example.helloworld;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	
	@GetMapping("/greeting")
	@ResponseBody
	public Greeting greeting(@RequestParam(defaultValue = "World") String name) {
		
		try {
			
			return new Greeting(counter.incrementAndGet(), String.format(template, name));
		}
		catch(Exception e) {
			System.out.println("System error on greeting: " + e);
		}
		return null;
	}
	
	
	@GetMapping("/")
	public String home() {
		
		try {
			return "Hello World!";
		}
		catch(Exception e) {
			System.out.println("System error on greeting: " + e);
		}
		return "Null output";
	}
	

}
