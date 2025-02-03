package com.xrpl.xrplcontrollers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xrpl.xrplutils.XRP_Ledger_SendTxns;




@RestController
public class Controller_XRPL_Escrows {
	
	
	
	@GetMapping("/escrows")
	public String getEscrows() {
		
		String result = "Unable to locate escrows";
		
		try {
		//	XRP_Ledger_Txns testnetTransaction = new XRP_Ledger_Txns();
		//	result = testnetTransaction.sendTestnetXRP();
			
			return "Succes Escrows: " + result;
			
		}
		catch(Exception e) {
			
			System.out.println("System error on XRP Send API: " + e);
			
		}
		
		return result;
		
		
	}
	
	

}
