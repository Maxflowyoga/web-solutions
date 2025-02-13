package com.xrpl.paymentcontrollers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.xrpl.db.DB_MYSQL_Utils;
import com.xrpl.models.Model_Greeting;
import com.xrpl.xrplutils.XRP_Ledger_Connect;


@RestController
public class Controller_XRPL_Home {
	
	
	private static final String template = "Greetings, %s Soul Fit Fam!";
	
	
	private final AtomicLong counter = new AtomicLong();

	
	@CrossOrigin(origins = "http://localhost:8080/")
	@GetMapping("/xrp-greeting")
	@ResponseBody
	public Model_Greeting greeting(@RequestParam(defaultValue = "XRP Yoga") String name) {
		
		try {
			
			return new Model_Greeting(counter.incrementAndGet(), String.format(template, name));
		}
		catch(Exception e) {
			System.out.println("System error on xrp-greeting: " + e);
		}
		return null;
	}
	
	
	@CrossOrigin(origins = "http://localhost:8080/")
	@GetMapping("/xrp")
	public String xrpHome() {

		String resultSet = "{ \"message\" : \"No Data\"}";
		
		try {
			
			System.out.println("Calling getXRP Testnet Account Info API...");
			
			XRP_Ledger_Connect xrpConnect = new XRP_Ledger_Connect();
			
			
			resultSet = xrpConnect.getXRPTestnetAccountInfo();
			
			//View_JSON_Export json = new View_JSON_Export();
			
			//JsonNode jsonNode = json.stringToJSON(resultSet);
			
			return resultSet;
			
		}
		catch(Exception e) {
			System.out.println("System error on xrp: " + e);
		}

		return resultSet;
	}
	
	@CrossOrigin(origins = "http://localhost:8080/")
	@GetMapping("/")
	public String home() {
		
		try {
			return "HOME <3!";
		}
		catch(Exception e) {
			System.out.println("System error on HOME: " + e);
		}
		return "Null output";
	}
	

}
