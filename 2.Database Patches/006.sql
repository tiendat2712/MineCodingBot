-- 1. RENAME column `KEY` to `SIZE` in table SIZE

-- 1a. delete constraint CHK_SIZE_KEY
ALTER TABLE SIZE
DROP CONSTRAINT CHK_SIZE_KEY;

-- 1b. rename column
ALTER TABLE SIZE
RENAME COLUMN `KEY` TO `SIZE`;

-- 1c. recreate condepartmentdepartmentdepartmentdepartmentstraint
ALTER TABLE SIZE 
ADD CONSTRAINT `CHK_SIZE` CHECK ((`SIZE` IN ('S','M','L','XL','XXL','XXXL')));

-- 2. ADD column `NAME` to table ITEM
ALTER TABLE ITEM
ADD COLUMN `NAME` VARCHAR(100) NOT NULL AFTER ID;

-- 3. ADD column `DELIVERY_ADDRESS` to table ORDER
ALTER TABLE `ORDER`
ADD COLUMN `DELIVERY_ADDRESS` TEXT NOT NULL AFTER ID;

-- 4. ADD UNIQUE constraint for column ORDER_ID in table BILL
ALTER TABLE `BILL`
ADD CONSTRAINT UNQ_ORDER UNIQUE(ORDER_ID);

-- 18:56:30	ALTER TABLE SIZE RENAME COLUMN `KEY` TO `SIZE`	
-- Error Code: 3959. Check constraint 'CHK_SIZE_KEY' uses column 'KEY', hence column cannot be dropped or renamed.	0.000 sec