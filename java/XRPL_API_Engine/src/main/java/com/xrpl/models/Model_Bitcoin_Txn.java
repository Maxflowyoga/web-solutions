package com.xrpl.models;






public class Model_Bitcoin_Txn {

	
	
	
	
	
	
	
}

/*
-- https://btcscan.org/

Use sys;
CREATE TABLE `el_blockchain_bitcoin_txns` (

  `id_bitcoin_txns` int NOT NULL AUTO_INCREMENT,

  `btc_txhash` varchar(120) DEFAULT NULL,
  `btc_status` varchar(120) DEFAULT NULL,
  `btc_eta` varchar(120) DEFAULT NULL,
  `btc_txn_fees` varchar(120) DEFAULT NULL,
  `btc_size` varchar(120) DEFAULT NULL,
  `btc_virtual_size` varchar(120) DEFAULT NULL,
  `btc_weight_units` varchar(120) DEFAULT NULL,
  `btc_version` varchar(120) DEFAULT NULL,
  `btc_lock_time` varchar(120) DEFAULT NULL,
  `btc_replace_by_fee` varchar(120) DEFAULT NULL,
  `btc_segwit_fee_savings` varchar(120) DEFAULT NULL,
  `btc_privacy_analysis` varchar(120) DEFAULT NULL,

  `btc_blockno` varchar(120) DEFAULT NULL,
  `btc_unixTimestamp` varchar(120) DEFAULT NULL,
  `btc_dateTime_UTC` varchar(120) DEFAULT NULL,
  `btc_from` varchar(120) DEFAULT NULL,
  `btc_to` varchar(120) DEFAULT NULL,
  `btc_contractAddress` varchar(120) DEFAULT NULL,
  `btc_value_IN` varchar(120) DEFAULT NULL,
  `btc_value_OUT` varchar(120) DEFAULT NULL,
  `btc_currentValue` varchar(120) DEFAULT NULL,
  `btc_txnFee` varchar(120) DEFAULT NULL,
  `btc_txnFee_USD` varchar(120) DEFAULT NULL,
  `btc_historical_Price` varchar(120) DEFAULT NULL,

  `btc_errCode` varchar(120) DEFAULT NULL,
  `btc_method` varchar(120) DEFAULT NULL,

  `created_date_bitcoin_txns` varchar(120) DEFAULT NULL,
  `modified_date_bitcoin_txns` varchar(120) DEFAULT NULL,
  `active_bitcoin_txns` tinyint DEFAULT NULL,

  PRIMARY KEY (`id_bitcoin_txns`),
  UNIQUE KEY `id_bitcoin_txns_UNIQUE` (`id_bitcoin_txns`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
*/