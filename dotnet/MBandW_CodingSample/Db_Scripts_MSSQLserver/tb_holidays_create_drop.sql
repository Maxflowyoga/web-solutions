DROP TABLE [mbandw].[dbo].[tb_holidays];


CREATE TABLE [mbandw].[dbo].[tb_holidays](
	[id_tb_holidays] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,

	[holiday_name] [nvarchar](100) NOT NULL,
	[holiday_description] [nvarchar](3000) NULL,
	[holiday_date] [datetime] NOT NULL,

    [holiday_host_country] [nvarchar] (45) NOT NULL,
    [holiday_status] [nvarchar] (45) NOT NULL,


    [holiday_created_date] [datetime] NULL,
    [holiday_modified_date] [datetime] NULL,

    [holiday_created_by] [nvarchar] (45) NOT NULL,
    [holiday_modified_by] [nvarchar] (45) NOT NULL,

    [holiday_active] [int] NOT Null


);


DBCC CHECKIDENT('mbandw.dbo.tb_holidays', RESEED, 0);
