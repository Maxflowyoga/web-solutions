IF NOT EXISTS (SELECT name
                FROM sys.databases-- Get a list of databases
                WHERE name = N'mbandw')
                CREATE DATABASE mbandw;