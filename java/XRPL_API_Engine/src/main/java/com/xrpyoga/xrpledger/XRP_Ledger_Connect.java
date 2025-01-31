package com.xrpyoga.xrpledger;



import okhttp3.HttpUrl;
import org.xrpl.xrpl4j.client.JsonRpcClientErrorException;
import org.xrpl.xrpl4j.client.XrplClient;
import org.xrpl.xrpl4j.client.faucet.FaucetClient;
import org.xrpl.xrpl4j.client.faucet.FundAccountRequest;
import org.xrpl.xrpl4j.codec.addresses.AddressCodec;
import org.xrpl.xrpl4j.crypto.keys.KeyPair;
import org.xrpl.xrpl4j.crypto.keys.Seed;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoRequestParams;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoResult;
import org.xrpl.xrpl4j.model.ledger.AccountRootObject;
import org.xrpl.xrpl4j.model.ledger.LedgerObject;
import org.xrpl.xrpl4j.model.transactions.Address;
import org.xrpl.xrpl4j.model.transactions.XAddress;


public class XRP_Ledger_Connect {


	public String getXRPAccountInfo() throws JsonRpcClientErrorException  {
		
		 System.out.println("Running the GetAccountInfo sample...");
		 
		 String result = "No User Account Data Found...";
		
			//return string or JSON object
		try {
			
			 // Construct a network client
		    HttpUrl rippledUrl = HttpUrl.get("https://s.altnet.rippletest.net:51234/");
		    System.out.println("Constructing an XrplClient connected to " + rippledUrl);
		    XrplClient xrplClient = new XrplClient(rippledUrl);

		    // Create a random KeyPair
		    KeyPair randomKeyPair = Seed.ed25519Seed().deriveKeyPair();
		    System.out.println("Generated KeyPair: " + randomKeyPair);
		    
		    System.out.println("***" + randomKeyPair.privateKey().value());
		    System.out.println("***" + randomKeyPair.privateKey().toString());
		    // Derive the Classic and X-Addresses from testWallet
		    Address classicAddress = randomKeyPair.publicKey().deriveAddress();
		    XAddress xAddress = AddressCodec.getInstance().classicAddressToXAddress(classicAddress, true);
		    System.out.println("Classic Address: " + classicAddress);
		    System.out.println("X-Address: " + xAddress);

		    // Fund the account using the testnet Faucet
		    
		    FaucetClient faucetClient = FaucetClient.construct(HttpUrl.get("https://faucet.altnet.rippletest.net"));
		    faucetClient.fundAccount(FundAccountRequest.of(classicAddress));
		    System.out.println("Funded the account using the Testnet faucet.");
		    

		    // Look up your Account Info
		    AccountInfoRequestParams requestParams = AccountInfoRequestParams.of(classicAddress);
		    AccountInfoResult accountInfoResult = xrplClient.accountInfo(requestParams);

		    // Print the result
		    System.out.println(accountInfoResult);
		    
		    result = accountInfoResult.toString();
		    
			//result = accountInfoResult.accountData().account().toString();
	
		  //  LedgerObject rootObj = new LedgerObject();
		    
		 //   rootObj = accountInfoResult.accountData();
			
		} catch (Exception e) {
			
			System.out.println("System error in the XRP Ledger Test Account RPC Connect, "
					+ "Test Account Creation & Account funding...");
				
			
		}


		System.out.println("In the XRP Ledger, get Account Information...");
		
		
		return result;
		
	}
	
	
	
}
