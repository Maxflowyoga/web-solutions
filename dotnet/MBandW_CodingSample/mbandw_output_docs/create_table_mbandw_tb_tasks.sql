CREATE TABLE `tb_tasks` (
  `id_tb_tasks` int NOT NULL AUTO_INCREMENT,
  `tasks_name` varchar(150) NOT NULL,
  `tasks_description` varchar(3000) NOT NULL,
  `tasks_due_date` datetime NOT NULL,
  `tasks_start_date` datetime NOT NULL,
  `tasks_end_date` datetime NOT NULL,
  `tasks_priority` varchar(45) NOT NULL,
  `tasks_status` varchar(45) NOT NULL,
  `tasks_created_date` datetime DEFAULT NULL,
  `tasks_modified_date` datetime DEFAULT NULL,
  `tasks_created_by` varchar(45) DEFAULT NULL,
  `tasks_modified_by` varchar(45) DEFAULT NULL,
  `tasks_active` int NOT NULL,
  PRIMARY KEY (`id_tb_tasks`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
