-- Execute these commands in MS SQL Server
USE master
GO
IF NOT EXISTS (
   SELECT name
   FROM sys.databases
   WHERE name = N'lunch_location_decider'
)
CREATE DATABASE [lunch_location_decider]
GO

USE [lunch_location_decider]

-- Create a new table called 'lunch_session' in schema 'dbo'
-- Drop the table if it already exists
IF OBJECT_ID('dbo.lunch_session', 'U') IS NOT NULL
    DROP TABLE dbo.lunch_session
GO

-- Create the table in the specified schema
CREATE TABLE dbo.lunch_session (
    room_id UNIQUEIDENTIFIER NOT NULL PRIMARY KEY, -- primary key column
    owner_code VARCHAR(50) NOT NULL UNIQUE,
    active_status BIT NOT NULL,
	restaurants NVARCHAR(MAX) NULL
)
GO

-- OPTIONAL STEP
-- Insert sample rows into table 'lunch_session'
INSERT INTO dbo.lunch_session
   ([room_id],[owner_code],[active_status],[restaurants])
VALUES
   ('e58ed763-928c-4155-bee9-fdbaaadc15f3', 'e58ed763', 'true', ''),
   ('e58ed763-928c-4155-bee9-fdbaaadc15f4', 'e58ed764', 'true', 'Macs,KFC,Kopitiam'),
   ('e58ed763-928c-4155-bee9-fdbaaadc15f5', 'e58ed765', 'false', ''),
   ('e58ed763-928c-4155-bee9-fdbaaadc15f6', 'e58ed766', 'false', 'Macs')
GO