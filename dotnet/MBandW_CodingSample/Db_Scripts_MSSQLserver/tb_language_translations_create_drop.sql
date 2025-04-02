DROP TABLE [mbandw].[dbo].[tb_language_translations];
-- current languages are Spanish and New York City specific

-- add any key terms we wish to translate

-- can separate table into regions if records grow excessively in the future
-- i.e. we can add all languages that are possible based on the database/server side code
-- processing capabilities and presets, such as what languages MSSQl server
-- already incorporates in it's native language base, along with .NET server side processing
-- for those characters 
CREATE TABLE [mbandw].[dbo].[tb_language_translations](
	[id_tb_customer_brands] [int] IDENTITY(1,1)PRIMARY KEY NOT NULL,
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

DBCC CHECKIDENT('mbandw.dbo.tb_language_translations', RESEED, 0);
