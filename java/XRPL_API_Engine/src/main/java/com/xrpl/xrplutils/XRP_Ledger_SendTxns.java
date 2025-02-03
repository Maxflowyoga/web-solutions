package com.xrpl.xrplutils;

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

import com.xrpl.models.Model_XRP_Transaction;
import org.xrpl.xrpl4j.crypto.signing.SingleSignedTransaction;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoRequestParams;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoResult;
import org.xrpl.xrpl4j.model.client.common.LedgerIndex;
import org.xrpl.xrpl4j.model.client.common.LedgerSpecifier;
import org.xrpl.xrpl4j.model.client.fees.FeeResult;
import org.xrpl.xrpl4j.model.client.ledger.LedgerRequestParams;
import org.xrpl.xrpl4j.model.client.transactions.SubmitResult;
import org.xrpl.xrpl4j.model.client.transactions.TransactionRequestParams;
import org.xrpl.xrpl4j.model.client.transactions.TransactionResult;
import org.xrpl.xrpl4j.model.immutables.FluentCompareTo;
import org.xrpl.xrpl4j.model.ledger.AccountRootObject;
import org.xrpl.xrpl4j.model.ledger.LedgerObject;
import org.xrpl.xrpl4j.model.transactions.Address;
import org.xrpl.xrpl4j.model.transactions.Payment;
import org.xrpl.xrpl4j.model.transactions.XAddress;
import org.xrpl.xrpl4j.model.transactions.XrpCurrencyAmount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.primitives.UnsignedInteger;

public class XRP_Ledger_SendTxns {
	
	
	
	// Send XRP methods
	
	public TransactionResult<Payment> sendTestnetXRP() throws JsonRpcClientErrorException  {
		
	//	try {
			
			XRP_Ledger_Connect testNet = new XRP_Ledger_Connect();
			Model_XRP_Transaction xrpTxn = new Model_XRP_Transaction();

			// Connect to a Testnet Client Server
			XrplClient testnetXrplClient = testNet.connectTestnetXRPLClientServer();
			
			// Get Credentials Key Pair
			KeyPair randomTestKeyPair = testNet.createKeyPairTest();
			System.out.println("Random Test Key Pair is: " + randomTestKeyPair.toString());
			
			Address testnetClassicAddress = testNet.getClassicAddress(randomTestKeyPair);
			System.out.println("Classic address is: " + testnetClassicAddress);
			
			testNet.fundTestnetXRPAccount(testnetClassicAddress);
		    System.out.println("Funded the account using the Testnet faucet.");
		    
			// Prepare the Transaction 
			// Get the latest validated ledger index
			//LedgerIndex validatedLedger = getLatestXRPLedgerSequence(testnetXrplClient);

			// Get the latest validated ledger index
			LedgerIndex validatedLedger = testnetXrplClient.ledger(
						LedgerRequestParams.builder()
						.ledgerSpecifier(LedgerSpecifier.VALIDATED)
						.build()
						)
					.ledgerIndex()
					.orElseThrow(() -> new RuntimeException("LedgerIndex not available."));
			
			System.out.println("Validated Ledger is: " + validatedLedger);
			System.out.println("Send Testnet XRP to validated ledger #: " + validatedLedger);
			
			UnsignedInteger lastLedgerSequence = validatedLedger.plus(UnsignedInteger.valueOf(4)).unsignedIntegerValue();
			
			System.out.println("***Last Ledger Sequence: " + lastLedgerSequence);
			
			Payment payment = xrpTxn.prepareTestnetXRPTransaction(testnetXrplClient, randomTestKeyPair, testnetClassicAddress, lastLedgerSequence);
			
			// Construct the signature and Sign the Transaction
			SignatureService<PrivateKey> signatureService = new BcSignatureService();
			System.out.println("Signature Service: " + signatureService);
			
			
			SingleSignedTransaction<Payment> signedPayment = signatureService.sign(randomTestKeyPair.privateKey(), payment);
			System.out.println("Signed payment object is: " + signedPayment);

			// Submit the Signed Transaction Blob 
			SubmitResult<Payment> paymentSubmitResult;
			try {
				paymentSubmitResult = testnetXrplClient.submit(signedPayment);
				System.out.println("Payment Submitted Results is: " + paymentSubmitResult);
				
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonRpcClientErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Process and await for Validation 
			TransactionResult<Payment> transactionResult = xrpTxn.validateSendPaymentXRP(testnetXrplClient, signedPayment, lastLedgerSequence);
			
			// Check the Transaction Status
			System.out.println(transactionResult);
			System.out.println("Explorer link is: https://testnet.xrpl.org/transactions/" + signedPayment);
			
			transactionResult.metadata().ifPresent(metadata -> {
				
				System.out.println("Result code: " + metadata.transactionResult());
				
				metadata.deliveredAmount().ifPresent(deliveredAmount ->
				System.out.println("XRP Delivered: " + ((XrpCurrencyAmount) deliveredAmount).toXrp()));
				
			});
			
		//	if(transactionResult.metadata().isPresent()) {
				
				return transactionResult;
				
	//		} 
	/*
			else {
				
				return transactionResult.toString();
			}
			
		} catch (Exception e) {
			
			System.out.println("System error in sendTestnetXRP...");
			
		}
		
		return "No Data sendTestnetXRP...";
		*/
	}
	
	
	public void sendXRPMainnet(String incomingAddress, String destinationAddress) {
		
		
	}
	// End Send XRP methods

}
