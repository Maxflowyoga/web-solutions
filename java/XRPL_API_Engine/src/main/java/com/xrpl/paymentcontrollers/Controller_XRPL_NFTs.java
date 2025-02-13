package com.xrpl.paymentcontrollers;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xrpl.xrplutils.XRP_Ledger_AccountData;


public class Controller_XRPL_NFTs {
	
	
	@CrossOrigin(origins = "http://localhost:8080/")
	@PostMapping("/nfts")
	public String getAccountNFTs(String account, String ledgerIndex, String limit, 
			String marker, String ledger_hash) {
		
		XRP_Ledger_AccountData xrpAccountData = new XRP_Ledger_AccountData();
		
		String result = "";
		
		try {
			
			result = xrpAccountData.getAllAccountNFTS(account, ledgerIndex, limit, marker, ledger_hash);
			
			System.out.println("NFT APIes is: " + result);
			
		} catch (Exception e) {
			
			System.out.println("System error in get NFTs API: " + e);
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