INSERT INTO [mbandw].[dbo].[tb_holidays]
(
    [holiday_name]
      ,[holiday_description]
      ,[holiday_date]
      ,[holiday_host_country]
      ,[holiday_status]
      ,[holiday_created_date]
      ,[holiday_modified_date]
      ,[holiday_created_by]
      ,[holiday_modified_by]
      ,[holiday_active]) 
VALUES
(
    'New Years',
    'New Years Day',
    '2025-01-01',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Martin Luther King, Jr. Day',
    'Birthday of Martin Luther King, Jr.',
    '2025-01-20',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Washington''s Birthday',
    'George Washington''s Birthday',
    '2025-02-17',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Memorial Day',
    'Memorial Day',
    '2025-05-26',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Juneteenth National Independece Day',
    'Juneteenth National Independece Day',
    '2025-06-19',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Independece Day',
    'Fourth of July Independece Day',
    '2025-07-04',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Labor Day',
    'Labor Day',
    '2025-09-01',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Columbus Day',
    'Columbus Day',
    '2025-10-13',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Veterans Day',
    'Veterans Day',
    '2025-11-11',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Thanksgiving Day',
    'Thanksgiving Day',
    '2025-11-27',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
),
(
    'Christmas Day',
    'Christmas Day',
    '2025-12-25',
    'USA',
    'Federal Holiday',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'John M. Figueroa',
    'John M. Figueroa',
    1
);


SELECT TOP (1000) [id_tb_holidays]
      ,[holiday_name]
      ,[holiday_description]
      ,[holiday_date]
      ,[holiday_host_country]
      ,[holiday_status]
      ,[holiday_created_date]
      ,[holiday_modified_date]
      ,[holiday_created_by]
      ,[holiday_modified_by]
      ,[holiday_active]
  FROM [mbandw].[dbo].[tb_holidays]