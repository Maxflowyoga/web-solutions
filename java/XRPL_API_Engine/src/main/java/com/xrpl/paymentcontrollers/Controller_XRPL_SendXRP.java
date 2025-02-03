package com.xrpl.paymentcontrollers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xrpl.xrpl4j.model.client.transactions.TransactionResult;
import org.xrpl.xrpl4j.model.transactions.Payment;

import com.xrpl.models.Model_XRP_Transaction;
import com.xrpl.xrplutils.XRP_Ledger_SendTxns;

@RestController
public class Controller_XRPL_SendXRP {

	
	
	
	@GetMapping("/sendTestnetXRP")
	public String sendXRP() {
		
		String result = "Unable to send XRP";
		
		try {
			XRP_Ledger_SendTxns testnetTransaction = new XRP_Ledger_SendTxns();
			Model_XRP_Transaction modelTxn = new Model_XRP_Transaction();

			TransactionResult<Payment> txnResult = testnetTransaction.sendTestnetXRP();
			
			result = modelTxn.exportJSON(txnResult);
			
			return result;
			
		}
		catch(Exception e) {
			
			System.out.println("System error on XRP Send API: " + e);	
		}
		
		return result;
		
	}
	
	@GetMapping("/sendTestnetXRPJSON")
	public String sendXRPJSON() {
		
		String result = "Unable to send XRP";
		
		try {
			Model_XRP_Transaction modelTxn = new Model_XRP_Transaction();
			XRP_Ledger_SendTxns testnetTransaction = new XRP_Ledger_SendTxns();
			
			//result = testnetTransaction.sendTestnetXRP();
			
			result = modelTxn.exportJSON(result);
			// object map the successful result into proper JSON
			return "Succes" + result;
			
		}
		catch(Exception e) {
			
			System.out.println("System error on XRP Send API: " + e);	
		}
		
		return result;
		
	}
	
	
	
}
