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
    id BIGINT NOT NULL PRIMARY KEY, -- primary key column
    owner_code VARCHAR(50) NOT NULL,
    room_code VARCHAR(50) NOT NULL,
    active_status BIT NOT NULL,
	restaurants NVARCHAR(MAX) NULL
)
GO

-- OPTIONAL STEP
-- Insert sample rows into table 'lunch_session'
INSERT INTO dbo.lunch_session
   ([id],[owner_code],[room_code],[active_status],[restaurants])
VALUES
   ( 1, 'e58ed763_e58ed763-928c-4155-bee9-fdbaaadc15f3', 'e58ed763-928c-4155-bee9-fdbaaadc15f3', 'true', ''),
   ( 2, 'e58ed764_e58ed763-928c-4155-bee9-fdbaaadc15f4', 'e58ed763-928c-4155-bee9-fdbaaadc15f4', 'true', 'Macs,KFC,Kopitiam'),
   ( 3, 'e58ed765_e58ed763-928c-4155-bee9-fdbaaadc15f5', 'e58ed763-928c-4155-bee9-fdbaaadc15f5', 'false', ''),
   ( 4, 'e58ed766_e58ed763-928c-4155-bee9-fdbaaadc15f6', 'e58ed763-928c-4155-bee9-fdbaaadc15f6', 'false', 'Macs,KFC,Kopitiam')
GO