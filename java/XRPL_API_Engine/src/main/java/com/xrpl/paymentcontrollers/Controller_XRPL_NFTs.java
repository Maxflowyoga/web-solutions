package com.xrpl.paymentcontrollers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


public class Controller_XRPL_NFTs {
	
	
	
	@PostMapping("/nfts")
	public void getAccountNFTs() {
		
		
		
		
		
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


*/