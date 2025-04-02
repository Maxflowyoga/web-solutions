use mbandw;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_mbandw_tasks`(

IN param_id_tb_tasks INT,
IN param_tasks_name VARCHAR(120),
IN param_tasks_description VARCHAR(120),
IN param_due_date VARCHAR(120),
IN param_start_date VARCHAR(120),
IN param_end_date VARCHAR(120),
IN param_priority VARCHAR(120),
IN param_status VARCHAR(120)

)
BEGIN

SET @taskIDExists = 0;
SET @getNewEntityID = 0;

IF 
	(SELECT id_tb_tasks FROM mbandw.tb_tasks WHERE id_tb_tasks = param_id_tb_tasks)
THEN
	SET @taskIDExists = 1;

END IF;

IF @entityAliasExists = 0 THEN
	INSERT INTO `mbandw`.`tb_tasks`
	(`id_tb_tasks`,
	`tasks_name`,
	`tasks_description`,
	`tasks_due_date`,
	`tasks_start_date`,
	`tasks_end_date`,
	`tasks_priority`,
	`tasks_status`,
	`tasks_created_date`,
	`tasks_modified_date`,
	`tasks_created_by`,
	`tasks_modified_by`,
	`tasks_active`)
    VALUES
	(<{id_tb_tasks: }>,
	<{tasks_name: }>,
	<{tasks_description: }>,
	<{tasks_due_date: }>,
	<{tasks_start_date: }>,
	<{tasks_end_date: }>,
	<{tasks_priority: }>,
	<{tasks_status: }>,
	<{tasks_created_date: }>,
	<{tasks_modified_date: }>,
	<{tasks_created_by: }>,
	<{tasks_modified_by: }>,
	<{tasks_active: }>);
    );

END IF;


END$$
DELIMITER ;
