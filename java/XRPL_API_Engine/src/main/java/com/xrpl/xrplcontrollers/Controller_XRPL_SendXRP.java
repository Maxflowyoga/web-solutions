package com.xrpl.xrplcontrollers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.xrpl.xrpledger.XRP_Ledger_Txns;

@RestController
public class Controller_XRPL_SendXRP {

	
	
	
	@GetMapping("/sendTestnetXRP")
	public String sendXRP() {
		
		String result = "Unable to send XRP";
		
		try {
			XRP_Ledger_Txns testnetTransaction = new XRP_Ledger_Txns();
			result = testnetTransaction.sendTestnetXRP();
			
			return result;
			
		}
		catch(Exception e) {
			
			System.out.println("System error on XRP Send API: " + e);
			
		}
		
		return result;
		
		
	}
	
	
	
}
