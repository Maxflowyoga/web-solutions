package com.xrpl.xrplutils;

import org.xrpl.xrpl4j.model.transactions.Address;
import org.xrpl.xrpl4j.model.transactions.EscrowCreate;
import org.xrpl.xrpl4j.model.transactions.XrpCurrencyAmount;

import com.google.common.io.BaseEncoding;
import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;
import com.ripple.cryptoconditions.CryptoConditionReader;

public class XRP_Ledger_Escrows {
	
	
	public EscrowCreate buildEscrow() {
		
		EscrowCreate escrowCreate = null;
		
		try {
			
			escrowCreate = EscrowCreate.builder() 
					.account(Address.of("publicKey"))
					.fee(XrpCurrencyAmount.ofDrops(12))
					.sequence(null)
					.amount(null)
					.destination(Address.of("publicKey_Destination"))
					.destinationTag(UnsignedInteger.valueOf(23480))
					.cancelAfter(UnsignedLong.valueOf(533257958))
					.finishAfter(UnsignedLong.valueOf(533171558))
					.condition(CryptoConditionReader.readCondition(
							BaseEncoding.base16()
								.decode(""))
							)
							.sourceTag(UnsignedInteger.valueOf(11747))	
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
