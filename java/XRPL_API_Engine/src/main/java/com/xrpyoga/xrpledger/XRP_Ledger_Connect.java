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
	
	
	
	
	protected Address createKeyPairTestWallet() {
		
		Address classicAddress = null;
		try {
		
			//Create a test KeyPair
			KeyPair randomTestKeyPair = Seed.ed25519Seed().deriveKeyPair();
			
			//Get the Classic Address for your test wallet
			classicAddress = randomTestKeyPair.publicKey().deriveAddress();
			
			
		} catch (Exception e) {
			System.out.println("Error in createKeyPairTestWallet: " + e);
		}
		
		
		return classicAddress;
	}
	
	
	protected XrplClient connectTestnetXRPLClientServer() {
		
		XrplClient xrplClient = null;
		
		try {
		
			
			HttpUrl rippledTestnetURL = HttpUrl.get("https://s.altnet.rippletest.net:51234/");
			
			xrplClient = new XrplClient(rippledTestnetURL);
			
			
		} catch (Exception e) {
			System.out.println("Error in connectTestnetXRPLClientServer: " + e);
		}
		
		
		return xrplClient;
		
	}
	
	protected void fundTestnetXRPAccount(Address classicAddress) {
		
		try {
			
			FaucetClient testnetFaucetClient = FaucetClient.construct(HttpUrl.get("https://faucet.altnet.rippletest.net"));
			testnetFaucetClient.fundAccount(FundAccountRequest.of(classicAddress));
			
			
		} catch (Exception e) {
			System.out.println("Error in fundTestnetXRPAccount: " + e);
		}
		
	}


	public String getXRPTestnetAccountInfo() throws JsonRpcClientErrorException  {
		
		 System.out.println("Running the GetAccountInfo sample...");
		 
		 String result = "No User Account Data Found...";
		
			//return string or JSON object
		try {
			
			 // Construct a network client
		    XrplClient xrplClient = connectTestnetXRPLClientServer();

		    // Create a random KeyPair
		    // Derive the Classic and X-Addresses from testWallet
		    Address classicAddress = createKeyPairTestWallet();
		    
		    
		    XAddress xAddress = AddressCodec.getInstance().classicAddressToXAddress(classicAddress, true);
		    System.out.println("Classic Address: " + classicAddress);
		    System.out.println("X-Address: " + xAddress);

		    // Fund the account using the testnet Faucet
		    
		    fundTestnetXRPAccount(classicAddress);
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
