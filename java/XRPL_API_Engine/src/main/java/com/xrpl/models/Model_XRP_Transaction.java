package com.xrpl.models;

import java.math.BigDecimal;

import org.xrpl.xrpl4j.client.JsonRpcClientErrorException;
import org.xrpl.xrpl4j.client.XrplClient;
import org.xrpl.xrpl4j.crypto.keys.KeyPair;
import org.xrpl.xrpl4j.crypto.signing.SingleSignedTransaction;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoRequestParams;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoResult;
import org.xrpl.xrpl4j.model.client.common.LedgerIndex;
import org.xrpl.xrpl4j.model.client.common.LedgerSpecifier;
import org.xrpl.xrpl4j.model.client.fees.FeeResult;
import org.xrpl.xrpl4j.model.client.ledger.LedgerRequestParams;
import org.xrpl.xrpl4j.model.client.transactions.TransactionRequestParams;
import org.xrpl.xrpl4j.model.client.transactions.TransactionResult;
import org.xrpl.xrpl4j.model.immutables.FluentCompareTo;
import org.xrpl.xrpl4j.model.jackson.ObjectMapperFactory;
import org.xrpl.xrpl4j.model.transactions.Address;
import org.xrpl.xrpl4j.model.transactions.Payment;
import org.xrpl.xrpl4j.model.transactions.XrpCurrencyAmount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.primitives.UnsignedInteger;

public class Model_XRP_Transaction {
	
	private String transactionType;
	private String account;
	private String amount;
	private String destination;
	
	
	
	
	
	
	
	public XrpCurrencyAmount getFeeResult( XrplClient xrplClient) {
		
		try {
			
			FeeResult feeResult = xrplClient.fee();
			XrpCurrencyAmount openLedgerFee = feeResult.drops().openLedgerFee();
			
			return openLedgerFee;
			
		} catch (Exception e) {
			
			System.out.println("System error in getFeeResult...");
		}
		
		return null;
		
	}
	
	
	public LedgerIndex getLatestXRPLedgerSequence(XrplClient xrplClient) throws JsonRpcClientErrorException {
		
		//LedgerIndex validatedLedger = null;
		System.out.println("in getLatestXRPLedgerSequence()...");
		
	//	try {
		
		// Get the latest validated ledger index
		LedgerIndex validatedLedger = xrplClient.ledger(
					LedgerRequestParams.builder()
					.ledgerSpecifier(LedgerSpecifier.VALIDATED)
					.build()
					)
				.ledgerIndex()
				.orElseThrow(() -> new RuntimeException("LedgerIndex not available."));
		
		System.out.println("Validated Ledger is: " + validatedLedger);
			// LastLedgerSequence is the current ledger index + 4
			//UnsignedInteger lastLedgerSequence = validatedLedger.plus(UnsignedInteger.valueOf(4)).unsignedIntegerValue();
			
			return validatedLedger;
	/*		
		} catch (Exception e) {
			
			System.out.println("Error in getLatestXRPLedgerSequence");
		}
	
		return validatedLedger;
		*/
	}
	
	
	public Payment prepareTestnetXRPTransaction(XrplClient xrplClient, 
			KeyPair randomTestKeyPair,  Address classicAdress, 
			UnsignedInteger lastLedgerSequence) throws JsonRpcClientErrorException  {
		
		
		System.out.println("In the prepare Testnet XRP Transaction...");
	//	Payment payment = null;
		
	//	try {
			/*
			AccountInfoRequestParams requestParams = AccountInfoRequestParams.builder()
					.account(classicAdress)
					.ledgerSpecifier(LedgerSpecifier.VALIDATED)
					.build();
			*/
			AccountInfoRequestParams requestParams = AccountInfoRequestParams.of(classicAdress);
		    
			System.out.println("Account Info Request Params are: " + requestParams);
			//get public key from classicAddress was derived from to pass into the payment object
			
			
			AccountInfoResult accountInfoResult = xrplClient.accountInfo(requestParams);

			System.out.println("Account Info Result is: " + accountInfoResult.toString());
			
			UnsignedInteger sequence = accountInfoResult.accountData().sequence();
			System.out.println("Sequence is: " + sequence);
			
			
			// Request current fee information from rippled
			XrpCurrencyAmount openLedgerFee = getFeeResult(xrplClient);
			System.out.println("Open ledger fee is: " + openLedgerFee);
			
			// Finish the constructed Payment object
			
			Payment payment = Payment.builder()
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
		/*
			
		} catch (Exception e) {
			
			System.out.println("Error in prepareTestnetXRPTransaction...");
		}
		
		return payment;
		*/
	}
	
	
	
	public TransactionResult<Payment> validateSendPaymentXRP (XrplClient xrplClient, SingleSignedTransaction<Payment> signedPayment,
		UnsignedInteger lastLedgerSequence) {
	
		
		TransactionResult<Payment> transactionResult = null;
	
		// check for validation
		try {
			
			boolean transactionValidated = false;
			boolean transactionExpired = false;
			
			while (!transactionValidated && !transactionExpired) {
				
				Thread.sleep(4*1000);
				
				LedgerIndex latestValidatedLedgerIndex = xrplClient.ledger(
						LedgerRequestParams.builder()
						.ledgerSpecifier(LedgerSpecifier.VALIDATED)
						.build()						
						)
						.ledgerIndex()
						.orElseThrow(() -> new RuntimeException("Ledger response did not contain a LedgerIndex..."));
				
				transactionResult = xrplClient.transaction(TransactionRequestParams.of(signedPayment.hash()), Payment.class);
				
				if (transactionResult.validated()) {
					System.out.println("Payment was validated with result code ");
					
					transactionValidated = true;
					
				} else {

					
					boolean lastLedgerSequenceHasPassed = FluentCompareTo.is(latestValidatedLedgerIndex.unsignedIntegerValue())
							.greaterThan(UnsignedInteger.valueOf(lastLedgerSequence.intValue()));
					
					
					if (lastLedgerSequenceHasPassed) {
						
						System.out.println("LastLedgerSequence has passed. Last tx response: " + transactionResult);
						transactionExpired = true;
						
					} else {
						
						System.out.println("Payment not yet validated...");
						
					}
						
						
				}
						
			}
			return transactionResult;
			
		} catch (Exception e) {
			
			System.out.println("Error in validateSendPaymentXRP");
		}
		
		return transactionResult;
		
	}
	
	
	public String exportJSON(Object incomingTxnBuilder) {
		
		String json = "";
		ObjectMapper objectmapper = ObjectMapperFactory.create();
	
		try {
			json = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(incomingTxnBuilder);
		} catch (JsonProcessingException e) {
			
			System.out.println("Erorr in exportJSON object mapper: " + e);
			
		}
		
		return json;
		
		
	}

}

/*

https://xrpl.org/docs/introduction/transactions-and-requests


{
"TransactionType": "Payment",
"Account": "rf1BiGeXwwQoi8Z2ueFYTEXSwuJYfV2Jpn",
"Amount": "1000000",
"Destination": "ra5nK24KXen9AHvsdFTKHSANinZseWnPcX"
}

*/