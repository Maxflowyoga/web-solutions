SELECT 
    COUNT (*)   
FROM 
    [mbandw].[dbo].[tb_tasks] 
WHERE 
    task_priority = 'High' 
AND 
    task_status = 'Finished';