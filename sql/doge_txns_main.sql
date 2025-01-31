-- https://drc-20.org/

USE sys;
CREATE TABLE `el_blockchain_doge_txns` (

  `id_doge_txns` int NOT NULL AUTO_INCREMENT,

  `doge_txhash` varchar(120) DEFAULT NULL,
  `doge_status` varchar(120) DEFAULT NULL,
  `doge_eta` varchar(120) DEFAULT NULL,
  `doge_txn_fees` varchar(120) DEFAULT NULL,
  `doge_size` varchar(120) DEFAULT NULL,
  `doge_virtual_size` varchar(120) DEFAULT NULL,
  `doge_weight_units` varchar(120) DEFAULT NULL,
  `doge_version` varchar(120) DEFAULT NULL,
  `doge_lock_time` varchar(120) DEFAULT NULL,
  `doge_replace_by_fee` varchar(120) DEFAULT NULL,
  `doge_segwit_fee_savings` varchar(120) DEFAULT NULL,
  `doge_privacy_analysis` varchar(120) DEFAULT NULL,
  
  `doge_blockno` varchar(120) DEFAULT NULL,
  `doge_unixTimestamp` varchar(120) DEFAULT NULL,
  `doge_dateTime_UTC` varchar(120) DEFAULT NULL,
  `doge_from` varchar(120) DEFAULT NULL,
  `doge_to` varchar(120) DEFAULT NULL,
  `doge_contractAddress` varchar(120) DEFAULT NULL,
  `doge_value_IN` varchar(120) DEFAULT NULL,
  `doge_value_OUT` varchar(120) DEFAULT NULL,
  `doge_currentValue` varchar(120) DEFAULT NULL,
  `doge_txnFee` varchar(120) DEFAULT NULL,
  `doge_txnFee_USD` varchar(120) DEFAULT NULL,
  `doge_historical_Price` varchar(120) DEFAULT NULL,
  
  `doge_errCode` varchar(120) DEFAULT NULL,
  `doge_method` varchar(120) DEFAULT NULL,

  `created_date_bitcoin_txns` varchar(120) DEFAULT NULL,
  `modified_date_bitcoin_txns` varchar(120) DEFAULT NULL,
  `active_bitcoin_txns` tinyint DEFAULT NULL,

  PRIMARY KEY (`id_doge_txns`),
  UNIQUE KEY `id_doge_txns_UNIQUE` (`id_doge_txns`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

