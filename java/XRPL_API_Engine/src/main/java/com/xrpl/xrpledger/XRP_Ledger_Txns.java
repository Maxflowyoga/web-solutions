package com.xrpl.xrpledger;

import org.xrpl.xrpl4j.client.JsonRpcClientErrorException;

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

public class XRP_Ledger_Txns {
	
	
	
	
	
	// Send XRP methods
	
	
	public Object sendTestnetXRP() throws JsonRpcClientErrorException  {
		
		Object returnResult = new Object();
		
		try {
			
			XRP_Ledger_Connect testNet = new XRP_Ledger_Connect();
			// Get Credentials Key Pair
			Address testnetClassicAddress = testNet.createKeyPairTestWallet();
			
			
			// Connect to a Testnet Client Server
			
			XrplClient testnetXrplClient = testNet.connectTestnetXRPLClientServer();
			
			
			// Prepare the Transaction 
			
			
			
			// Sign the Transaction
			
			
			
			// Submit the Signed Transaction Blob 
			
			
			
			// Wait for Validation 
			

			
		} catch (Exception e) {
			
			
		}
		
		
		return returnResult;
		
	}
	
	
	public void sendXRPMainnet(String incomingAddress, String destinationAddress) {
		
		
	}
	// End Send XRP methods

}
