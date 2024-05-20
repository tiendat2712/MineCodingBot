-- 004.sql
-- --> PAYMENT_METHOD, ORDER_STATUS

-- create table PAYMENT_METHOD
DROP TABLE IF EXISTS PAYMENT_METHOD;
CREATE TABLE IF NOT EXISTS PAYMENT_METHOD
(
   ID INT,
   `DESC` TEXT NOT NULL,
   PRIMARY KEY(ID)
);

-- create table ORDER_STATUS
DROP TABLE IF EXISTS ORDER_STATUS;
CREATE TABLE IF NOT EXISTS ORDER_STATUS
(
   ID INT,
   `DESC` TEXT NOT NULL,
   PRIMARY KEY(ID)
);