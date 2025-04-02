DROP TABLE [mbandw].[dbo].[tb_tasks];

CREATE TABLE [mbandw].[dbo].[tb_tasks](
	[id_tb_tasks] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[tasks_name] [nvarchar](100) NOT NULL,
	[tasks_description] [nvarchar](3000) NULL,
	[tasks_due_date] [datetime] NOT NULL,

    [tasks_start_date] [datetime] NOT NULL,
    [task_end_date] [datetime] NOT NULL,

    [tasks_priority] [nvarchar] (45) NOT NULL,
    [tasks_status] [nvarchar] (45) NOT NULL,


    [tasks_created_date] [datetime] NULL,
    [tasks_modified_date] [datetime] NULL,

    [tasks_created_by] [nvarchar] (45) NOT NULL,
    [tasks_modified_by] [nvarchar] (45) NOT NULL,

    [tasks_active] [int] NOT Null


);


DBCC CHECKIDENT('mbandw.dbo.tb_tasks', RESEED, 0);
