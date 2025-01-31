package com.xrpyoga.xrpledger;

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
			
			
			
			
			
			
		} catch (Exception e) {
			
			
			
			
			
		}
		
		
		return returnResult;
		
	}
	
	private Address createKeyPairTestWallet() {
		
		//Create a test KeyPair
		KeyPair randomTestKeyPair = Seed.ed25519Seed().deriveKeyPair();
		
		//Get the Classic Address for your test wallet
		Address classicAddress = randomTestKeyPair.publicKey().deriveAddress();
		
		
		return classicAddress;
	}
	
	
	
	// End Send XRP methods

}
