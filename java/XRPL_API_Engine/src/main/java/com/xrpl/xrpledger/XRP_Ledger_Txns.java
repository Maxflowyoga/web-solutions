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
	
	
	protected LedgerIndex getLatestXRPLedgerSequence(XrplClient xrplClient) throws JsonRpcClientErrorException {
		
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
	
	
	protected Payment prepareTestnetXRPTransaction(XrplClient xrplClient, 
			KeyPair randomTestKeyPair,  Address classicAdress, 
			UnsignedInteger lastLedgerSequence) throws JsonRpcClientErrorException  {
		
		
		System.out.println("In the prepare Testnet XRP Transaction...");
	//	Payment payment = null;
		
	//	try {
			
			AccountInfoRequestParams requestParams = AccountInfoRequestParams.builder()
					.account(classicAdress)
					.ledgerSpecifier(LedgerSpecifier.VALIDATED)
					.build();
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
	
	
	
	protected TransactionResult<Payment> validateSendPaymentXRP (XrplClient xrplClient, SingleSignedTransaction<Payment> signedPayment,
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
	
	// Send XRP methods
	
	
	public String sendTestnetXRP() throws JsonRpcClientErrorException  {
		
	//	try {
			
			XRP_Ledger_Connect testNet = new XRP_Ledger_Connect();
			

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
			
			//Payment payment = prepareTestnetXRPTransaction(testnetXrplClient, randomTestKeyPair, testnetClassicAddress, lastLedgerSequence);
			/*
			AccountInfoRequestParams requestParams = AccountInfoRequestParams.builder()
					.account(testnetClassicAddress)
					.ledgerSpecifier(LedgerSpecifier.VALIDATED)
					.build();
			*/
			AccountInfoRequestParams requestParams = AccountInfoRequestParams.of(testnetClassicAddress);
		    
			
			System.out.println("Request Params are: " + requestParams);
			//get public key from classicAddress was derived from to pass into the payment object
			
			AccountInfoResult accountInfoResult = testnetXrplClient.accountInfo(requestParams);

			System.out.println("Account Info Result is: " + accountInfoResult.toString());
			
			UnsignedInteger sequence = accountInfoResult.accountData().sequence();
			System.out.println("Sequence is: " + sequence);
			
			
			// Request current fee information from rippled
			XrpCurrencyAmount openLedgerFee = getFeeResult(testnetXrplClient);
			System.out.println("Open ledger fee is: " + openLedgerFee);
			
			// Finish the constructed Payment object
			
			Payment payment = Payment.builder()
					.account(testnetClassicAddress)
					.amount(XrpCurrencyAmount.ofXrp(BigDecimal.ONE))
					.destination(testnetClassicAddress)
					.sequence(sequence)
					.fee(openLedgerFee)
					.signingPublicKey(randomTestKeyPair.publicKey())
					.lastLedgerSequence(lastLedgerSequence)
					.build();
			
			System.out.println("Constructed Payment: " + payment);
			
			
			
			
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
			TransactionResult<Payment> transactionResult = validateSendPaymentXRP(testnetXrplClient, signedPayment, lastLedgerSequence);
			
			
			// Check the Transaction Status
			System.out.println(transactionResult);
			System.out.println("Explorer link is: https://testnet.xrpl.org/transactions/" + signedPayment);
			
			transactionResult.metadata().ifPresent(metadata -> {
				
				System.out.println("Result code: " + metadata.transactionResult());
				
				metadata.deliveredAmount().ifPresent(deliveredAmount ->
				System.out.println("XRP Delivered: " + ((XrpCurrencyAmount) deliveredAmount).toXrp()));
				
			});
			
			if(transactionResult.metadata().isPresent()) {
				
				return transactionResult.metadata().toString();
				
			} 
			else {
				
				return transactionResult.toString();
			}
			
			/*
			
		} catch (Exception e) {
			
			System.out.println("System error in sendTestnetXRP...");
			
		}
		
		return "No Data";
		*/
	}
	
	
	public void sendXRPMainnet(String incomingAddress, String destinationAddress) {
		
		
	}
	// End Send XRP methods

}
