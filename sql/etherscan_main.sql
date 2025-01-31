 -- https://etherscan.io/
Use sys;
CREATE TABLE `el_blockchain_etherscan_txns` (

  `id_etherscan_txns` int NOT NULL AUTO_INCREMENT,

  `eth_txhash` varchar(120) DEFAULT NULL,
  `eth_blockno` varchar(120) DEFAULT NULL,
  `eth_unixTimestamp` varchar(120) DEFAULT NULL,
  `eth_dateTime_UTC` varchar(120) DEFAULT NULL,
  `eth_from` varchar(120) DEFAULT NULL,
  `eth_to` varchar(120) DEFAULT NULL,
  `eth_contractAddress` varchar(120) DEFAULT NULL,
  `eth_value_IN` varchar(120) DEFAULT NULL,
  `eth_value_OUT` varchar(120) DEFAULT NULL,
  `eth_currentValue` varchar(120) DEFAULT NULL,
  `eth_txnFee` varchar(120) DEFAULT NULL,
  `eth_txnFee_USD` varchar(120) DEFAULT NULL,
  `eth_historical_Price` varchar(120) DEFAULT NULL,
  `eth_status` varchar(120) DEFAULT NULL,
  `eth_errCode` varchar(120) DEFAULT NULL,
  `eth_method` varchar(120) DEFAULT NULL,

  `created_date_etherscan_txns` varchar(120) DEFAULT NULL,
  `modified_date_etherscan_txns` varchar(120) DEFAULT NULL,
  `active_etherscan_txns` tinyint DEFAULT NULL,

  PRIMARY KEY (`id_etherscan_txns`),
  UNIQUE KEY `id_etherscan_txns_UNIQUE` (`id_etherscan_txns`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
