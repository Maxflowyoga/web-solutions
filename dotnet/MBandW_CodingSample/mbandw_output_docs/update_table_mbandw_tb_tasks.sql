UPDATE `mbandw`.`tb_tasks`
SET
`id_tb_tasks` = <{id_tb_tasks: }>,
`tasks_name` = <{tasks_name: }>,
`tasks_description` = <{tasks_description: }>,
`tasks_due_date` = <{tasks_due_date: }>,
`tasks_start_date` = <{tasks_start_date: }>,
`tasks_end_date` = <{tasks_end_date: }>,
`tasks_priority` = <{tasks_priority: }>,
`tasks_status` = <{tasks_status: }>,
`tasks_created_date` = <{tasks_created_date: }>,
`tasks_modified_date` = <{tasks_modified_date: }>,
`tasks_created_by` = <{tasks_created_by: }>,
`tasks_modified_by` = <{tasks_modified_by: }>,
`tasks_active` = <{tasks_active: }>
WHERE `id_tb_tasks` = <{expr}>;
