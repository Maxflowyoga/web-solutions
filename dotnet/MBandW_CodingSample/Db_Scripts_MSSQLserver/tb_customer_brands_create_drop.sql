DROP TABLE [mbandw].[dbo].[tb_customer_brands];

CREATE TABLE [mbandw].[dbo].[tb_customer_brands](
	[id_tb_customer_brands] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[customer_brand_name] [nvarchar](100) NOT NULL,
	[customer_brand_description] [nvarchar](3000) NULL,

    [customer_brand_host_country] [nvarchar] (45) NOT NULL,
    [customer_brand_language] [nvarchar] (45) NOT NULL,


    [customer_brand_created_date] [datetime] NULL,
    [customer_brand_modified_date] [datetime] NULL,

    [customer_brand_created_by] [nvarchar] (45) NOT NULL,
    [customer_brand_modified_by] [nvarchar] (45) NOT NULL,

    [customer_brand_active] [int] NOT Null


);

DBCC CHECKIDENT('mbandw.dbo.tb_customer_brands', RESEED, 0);

