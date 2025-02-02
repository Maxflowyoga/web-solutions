package com.xrpl.xrplutils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XRP_Ledger_AccountData {
	
	
	
	
	public JsonNode stringToJSON(String incomingRawJSONString) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode jsonNode = null;
		
		
		//Map out account object
		
		try {
			
			jsonNode = objectMapper.readTree(incomingRawJSONString);
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		return jsonNode;
	}
	
	
	

}
