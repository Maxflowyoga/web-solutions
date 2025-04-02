DROP TABLE [mbandw].[dbo].[tb_customer_retention];

CREATE TABLE [mbandw].[dbo].[tb_customer_retention](
	[id_tb_customer_retention] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[customer_retention_name] [nvarchar](100) NOT NULL,
	[customer_retention_description] [nvarchar](3000) NULL,

    [customer_retention_host_country] [nvarchar] (45) NOT NULL,
    [customer_retention_language] [nvarchar] (45) NOT NULL,

    [customer_retention_created_date] [datetime] NULL,
    [customer_retention_modified_date] [datetime] NULL,

    [customer_retention_created_by] [nvarchar] (45) NOT NULL,
    [customer_retentiond_modified_by] [nvarchar] (45) NOT NULL,

    [customer_retention_active] [int] NOT Null


);

DBCC CHECKIDENT('mbandw.dbo.tb_customer_retention', RESEED, 0);