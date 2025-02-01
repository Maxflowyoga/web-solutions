package com.xrpl.xrplcontrollers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller_XRPL_SendXRP {

	
	
	
	@GetMapping("/sendXRP")
	public String sendXRP() {
		
		try {
			
			
			// 
			
			
			return "Send XRP API!";
			
			
		}
		catch(Exception e) {
			
			
			System.out.println("System error on XRP Send API: " + e);
			
			
		}
		
		
		return "Null output";
		
		
	}
	
	
	
}
