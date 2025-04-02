IF NOT EXISTS (SELECT name
                FROM sys.databases-- Get a list of databases
                WHERE name = N'test_db')
                CREATE DATABASE test_db;