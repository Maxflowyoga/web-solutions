package com.xrpl.paymentcontrollers;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xrpl.xrpl4j.model.transactions.EscrowCreate;

import com.xrpl.models.Model_XRP_Transaction;
import com.xrpl.xrplutils.XRP_Ledger_Escrows;
import com.xrpl.xrplutils.XRP_Ledger_SendTxns;




@RestController
public class Controller_XRPL_Escrows {
	
	
	@CrossOrigin(origins = "http://localhost:8080/")
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
	
	@CrossOrigin(origins = "http://localhost:8080/")
	@GetMapping("/create-escrow")
	public String createEscrow(String accountAddress, int currencyDropsAmount,
			int feeDropsAmount, String destinationAddress,
			int destinationTag, int cancelAfter, int finishAfter, String conditionDecodeString,
			int sourceTag) {
		
		String result = "No resuls for escrow build API";
		
		try { 
			XRP_Ledger_Escrows xrpEscrow = new XRP_Ledger_Escrows();
			EscrowCreate escrowCreate = null;
			
			escrowCreate = xrpEscrow.buildEscrow(accountAddress, currencyDropsAmount, feeDropsAmount, 
					destinationAddress, destinationTag, cancelAfter, 
					finishAfter, conditionDecodeString, sourceTag);
			

			Model_XRP_Transaction modelTxn = new Model_XRP_Transaction();
			result = modelTxn.exportJSON(escrowCreate);
			
			
		} catch (Exception e) {
			System.out.println("Error in Escrow Creation API...");
		}
		
		return result;
	}
	
	
	public void sendTimeHeldEscrow() {
		
	}
	
	
	public void sendConditionalHeldEscrow() {
		
		
	}
	
	
	public void cancelExpiredEscrows() {
		
		
	}
	
	
	public void lookUpEscrows() {
		
		
		
	}
	
	
}
