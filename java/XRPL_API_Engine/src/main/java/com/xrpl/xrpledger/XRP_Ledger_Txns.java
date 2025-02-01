package com.xrpl.xrpledger;

import java.math.BigDecimal;

import org.xrpl.xrpl4j.client.JsonRpcClientErrorException;

import okhttp3.HttpUrl;
import org.xrpl.xrpl4j.client.JsonRpcClientErrorException;
import org.xrpl.xrpl4j.client.XrplClient;
import org.xrpl.xrpl4j.client.faucet.FaucetClient;
import org.xrpl.xrpl4j.client.faucet.FundAccountRequest;
import org.xrpl.xrpl4j.codec.addresses.AddressCodec;
import org.xrpl.xrpl4j.crypto.keys.KeyPair;
import org.xrpl.xrpl4j.crypto.keys.PrivateKey;
import org.xrpl.xrpl4j.crypto.keys.Seed;
import org.xrpl.xrpl4j.crypto.signing.SignatureService;
import org.xrpl.xrpl4j.crypto.signing.bc.BcSignatureService;

import org.xrpl.xrpl4j.crypto.signing.SingleSignedTransaction;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoRequestParams;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoResult;
import org.xrpl.xrpl4j.model.client.common.LedgerIndex;
import org.xrpl.xrpl4j.model.client.common.LedgerSpecifier;
import org.xrpl.xrpl4j.model.client.fees.FeeResult;
import org.xrpl.xrpl4j.model.client.ledger.LedgerRequestParams;
import org.xrpl.xrpl4j.model.ledger.AccountRootObject;
import org.xrpl.xrpl4j.model.ledger.LedgerObject;
import org.xrpl.xrpl4j.model.transactions.Address;
import org.xrpl.xrpl4j.model.transactions.Payment;
import org.xrpl.xrpl4j.model.transactions.XAddress;
import org.xrpl.xrpl4j.model.transactions.XrpCurrencyAmount;

import com.google.common.primitives.UnsignedInteger;

public class XRP_Ledger_Txns {
	
	
	
	protected XrpCurrencyAmount getFeeResult( XrplClient xrplClient) {
		
		try {
			
			FeeResult feeResult = xrplClient.fee();
			XrpCurrencyAmount openLedgerFee = feeResult.drops().openLedgerFee();
			
			return openLedgerFee;
			
		} catch (Exception e) {
			
			System.out.println("System error in getFeeResult...");
		}
		
		return null;
		
	}
	
	protected Payment prepareTestnetXRPTransaction(XrplClient xrplClient, 
			KeyPair randomTestKeyPair,  Address classicAdress) throws JsonRpcClientErrorException  {
		
		Payment payment = null;
		
		
		try {
			
			AccountInfoRequestParams requestParams = AccountInfoRequestParams.builder()
					.account(classicAdress)
					.ledgerSpecifier(LedgerSpecifier.VALIDATED)
					.build();
			
			//get public key from classicAddress was derived from to pass into the payment object
			
			AccountInfoResult accountInfoResult = xrplClient.accountInfo(requestParams);
			UnsignedInteger sequence = accountInfoResult.accountData().sequence();
			
			
			// Request current fee information from rippled
			XrpCurrencyAmount openLedgerFee = getFeeResult(xrplClient);
			
			
			// Get the latest validated ledger index
			LedgerIndex validatedLedger = xrplClient.ledger(
					LedgerRequestParams.builder()
					.ledgerSpecifier(LedgerSpecifier.VALIDATED)
					.build()
					)
				.ledgerIndex()
				.orElseThrow(() -> new RuntimeException("LedgerIndex not available."));
			
			// LastLedgerSequence is the current ledger index + 4
			UnsignedInteger lastLedgerSequence = validatedLedger.plus(UnsignedInteger.valueOf(4)).unsignedIntegerValue();
			
			
			// Finish the constructed Payment object
			
			payment = Payment.builder()
					.account(classicAdress)
					.amount(XrpCurrencyAmount.ofXrp(BigDecimal.ONE))
					.destination(classicAdress)
					.sequence(sequence)
					.fee(openLedgerFee)
					.signingPublicKey(randomTestKeyPair.publicKey())
					.lastLedgerSequence(lastLedgerSequence)
					.build();
			System.out.println("Constructed Payment: " + payment);
			
			return payment;
			
			
		} catch (Exception e) {
			
			System.out.println("Error in prepareTestnetXRPTransaction...");
		}
		
		return payment;
	}
	
	
	// Send XRP methods
	
	
	public Object sendTestnetXRP() throws JsonRpcClientErrorException  {
		
		Object returnResult = new Object();
		
		try {
			
			XRP_Ledger_Connect testNet = new XRP_Ledger_Connect();
			// Get Credentials Key Pair
			KeyPair randomTestKeyPair = testNet.createKeyPairTest();
			Address testnetClassicAddress = testNet.getClassicAddress(randomTestKeyPair);
			
			
			// Connect to a Testnet Client Server
			
			XrplClient testnetXrplClient = testNet.connectTestnetXRPLClientServer();
			
			
			// Prepare the Transaction 
			
			Payment payment = prepareTestnetXRPTransaction(testnetXrplClient, randomTestKeyPair, testnetClassicAddress);
			
			// Construct the signature and Sign the Transaction
			SignatureService<PrivateKey> signatureService = new BcSignatureService();
			SingleSignedTransaction<Payment> signedPayment = signatureService.sign(randomTestKeyPair.privateKey(), payment);
			
			
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
