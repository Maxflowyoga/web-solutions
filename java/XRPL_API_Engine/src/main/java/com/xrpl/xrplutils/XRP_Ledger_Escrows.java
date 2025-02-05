package com.xrpl.xrplutils;

import org.xrpl.xrpl4j.model.transactions.Address;
import org.xrpl.xrpl4j.model.transactions.EscrowCreate;
import org.xrpl.xrpl4j.model.transactions.XrpCurrencyAmount;

import com.google.common.io.BaseEncoding;
import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;
import com.ripple.cryptoconditions.CryptoConditionReader;

public class XRP_Ledger_Escrows {
	
	
	public EscrowCreate buildEscrow(String accountAddress, int currencyDropsAmount,
			int feeDropsAmount, String destinationAddress,
			int destinationTag, int cancelAfter, int finishAfter, String conditionDecodeString,
			int sourceTag) 
	{
		
		EscrowCreate escrowCreate = null;
		
		try {
			
			escrowCreate = EscrowCreate.builder() 
					.account(Address.of(accountAddress))
					.fee(XrpCurrencyAmount.ofDrops(feeDropsAmount)) // 12
					.sequence(UnsignedInteger.ONE)
					.amount(XrpCurrencyAmount.ofDrops(currencyDropsAmount))
					.destination(Address.of(destinationAddress))
					.destinationTag(UnsignedInteger.valueOf(destinationTag))// 23480
					.cancelAfter(UnsignedLong.valueOf(cancelAfter)) //533257958
					.finishAfter(UnsignedLong.valueOf(finishAfter)) // 533171558
					.condition(CryptoConditionReader.readCondition(
							BaseEncoding.base16()
								.decode(conditionDecodeString))
							)
							.sourceTag(UnsignedInteger.valueOf(sourceTag))	 // 11747
							.build();
			
			return escrowCreate;
			
		} catch (Exception e) {
			System.out.println("Error in Escrow Creation: " + e);
		}
		
		return escrowCreate;
	}
	
	
	public void getTimeHeldEscrow() {
		
		
		
	}
	
	
	public void getConditionalHeldEscrow() {
		
		
		
	}
	
	
	public void putCancelExpiredEscrows() {
		
		
		
	}
	
	
	public void getLookUpEscrows() {
		
		
		
		
	}

}
