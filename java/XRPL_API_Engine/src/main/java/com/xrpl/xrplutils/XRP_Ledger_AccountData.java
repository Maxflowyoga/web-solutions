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
			System.out.println("System error in stringToJSON: " + e);
		} 
		return jsonNode;
	}
	
	
	public String getAllAccountNFTS(String account, String ledgerIndex, String limit, String marker,
			String ledger_hash) {
		
		String result = "";
		
		try {
			//create and send the JSON object mapped metadata to the JSON-RPC client url
			
			
			
			
		} catch (Exception e) {
			
			
			
			
		}
		
		return result;
		
		
	}
	

}
/*


Request Format for the Get Account NFTS:


{
	"method": "account_nfts",
	"params": [{
			"account": "rs2r32r32423e3...PublicKey",
			"ledger_index": "validated"
			}]
}

-additional Parameters are:
+ ledger_hash
+ limit 
+ marker


*/