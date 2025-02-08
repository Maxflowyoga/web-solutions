package com.xrpl.xrplutils;

import org.xrpl.xrpl4j.client.XrplClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xrpl.models.Model_XRP_Transaction;

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
	
	
	public String getAllAccountNFTS(String account, String ledgerIndex, String limit, 
			String marker, String ledger_hash) {
		
		String requestJSON = "";
		String result = "";
		
		try {

			XRP_Ledger_Connect testNetConnect = new XRP_Ledger_Connect();

			// Connect to a Testnet Client Server
			XrplClient testnetXrplClient = testNetConnect.connectTestnetXRPLClientServer();
			
			//create and send the JSON object mapped metadata to the JSON-RPC client url
			ObjectMapper objectMapper = new ObjectMapper();
			
			requestJSON = "{\n"
					+ "	\"method\": \"account_nfts\",\n"
					+ "	\"params\": [{\n"
					+ "			\"account\":" + account + ",\n"
					+ "			\"ledger_index\":" + ledgerIndex + "\n"
					+ "			}]\n"
					+ "}";

			JsonNode nftJSON = objectMapper.readTree(requestJSON);
			System.out.println("NFT JSON result is : " + nftJSON);
			
			
			
			//send request to RPC client and await the results
			
			
			//get XRP JSON RPC client
			
			
			
			//send request to xrpl client
			
			
			
			//package result into the method response
			
			
			
			//JsonNode requestAccountNFTs = objectMapper.readTree(result);
			return result;
			
		} catch (Exception e) {
			System.out.println("Error in getting All Account NFTs: " + e);
		}
		
		return result;
		
		
	}
	

}
/*


Request Format for the Get Account NFTS:


{
	"method": "account_nfts",
	"params": [{
			"account": accounts,
			"ledger_index": ledger_index
			}]
}

-additional Parameters are:
+ ledger_hash
+ limit 
+ marker


*/