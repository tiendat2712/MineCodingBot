-- 1. Viết phương thức liệt kê các mặt hàng
DELIMITER $$
CREATE PROCEDURE P_PRINT_ALL_ITEMS()
BEGIN
     SELECT * FROM item;
END $$

-- call the procedure
CALL P_PRINT_ALL_ITEMS();


-- 2.1. Viết phương thức liệt kê các mặt hàng theo mã loại hàng
DROP PROCEDURE P_PRINT_ITEMS;

DELIMITER $$
CREATE PROCEDURE P_PRINT_ITEMS_BY_ITEM_GROUP_ID(IN p_item_group_id INT)
BEGIN
     SELECT *
       FROM item it	
       WHERE it.ITEM_GROUP_ID = p_item_group_id;
END $$

CALL P_PRINT_ITEMS_BY_ITEM_GROUP_ID(4);

-- 2.2. Viết phương thức liệt kê các mặt hàng theo tên loại hàng
DELIMITER $$
CREATE PROCEDURE P_PRINT_ITEMS_BY_ITEM_GROUP_NAME(IN p_item_group_name VARCHAR(100))
BEGIN
     SELECT *
       FROM item it	
       JOIN item_group itg
       ON it.ITEM_GROUP_ID = itg.ID
       WHERE itg.`NAME` = p_item_group_name;
END $$

SELECT * FROM item;
SELECT * FROM item_group;
CALL P_PRINT_ITEMS_BY_ITEM_GROUP_NAME('Áo'); 

-- 	CÁCH 2:
-- DELIMITER $$
-- CREATE PROCEDURE P_PRINT_ITEMS_BY_ITEM_GROUP_NAME(p_item_group_name VARCHAR(100))
-- BEGIN
-- 	DECLARE p_ig_id INT; -- default = null
--     
--     SET p_ig_id = (SELECT ID
-- 					 FROM ITEM_GROUP
-- 					 WHERE NAME = p_item_group_name);

--     SELECT * 
-- 	  FROM item 
-- 	 WHERE ITEM_GROUP_ID = p_ig_id;
-- END $$

-- 3. Viết phương thức thêm(append) N dòng dữ liệu cho bảng loại hàng
-- 1	Áo 2	Quần 3	Giày 4	Dép 5	Mũ 6	Thắt lưng
P_INSERT_DATA_ITEM_GROUP(3)
-- 7 Loại hàng 7
-- 8 Loại hàng 8
-- 9 Loại hàng 9

P_INSERT_DATA_ITEM_GROUP(2)
-- 10 Loại hàng 10
-- 11 Loại hàng 11
DROP PROCEDURE P_INSERT_DATA_ITEM_GROUP;

DELIMITER $$
CREATE PROCEDURE P_INSERT_DATA_ITEM_GROUP(p_rows INT)
BEGIN
     
     DECLARE COUNT INT DEFAULT 0;
     WHILE COUNT < p_rows DO
          INSERT INTO item_group(ID, `NAME`)
          SELECT MAX(ID) + 1,
				 concat('Loại Hàng',' ' ,MAX(ID) + 1)
		    FROM item_group;
		  SET COUNT = COUNT + 1;
	 END WHILE;
     
END $$

CALL P_INSERT_DATA_ITEM_GROUP(2);
CALL P_INSERT_DATA_ITEM_GROUP(3);
SELECT * FROM item_group;

-- 4. Viết phương thức liệt kê các phần tử chẵn nhỏ hơn N
-- global table temporary
CREATE TABLE GTT_DATA
(
	GTT_COL TEXT
);
SELECT * FROM GTT_DATA;

DELIMITER $$
CREATE PROCEDURE P_PRINT_EVEN_NUMBER(n INT)
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE result TEXT DEFAULT '';
    
    TRUNCATE GTT_DATA;
    WHILE i < n DO
		IF i MOD 2 = 0 THEN
			INSERT INTO GTT_DATA(GTT_COL)
            SELECT i;
        END IF;
        SET i = i + 1;
    END WHILE;
    SELECT * FROM GTT_DATA;
    TRUNCATE GTT_DATA;
END $$

CALL P_PRINT_EVEN_NUMBER(15);

-- FUNCTION
-- 1. Tính tổng các phần tử chẵn nhỏ hơn n
SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $$
CREATE FUNCTION F_SUM_EVEN_NUMBERS(n INT)
RETURNS INT --  table, array
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE result INT DEFAULT 0;
    
    WHILE i < n DO
		IF i MOD 2 = 0 THEN
			SET result = result + i;
        END IF;
        SET i = i + 1;
    END WHILE;
    
    RETURN result;
END $$

SELECT F_SUM_EVEN_NUMBERS(10);


-- TRIGGER
CREATE TABLE employees (
    ID INT PRIMARY KEY,
    First_Name VARCHAR(20),
    Last_Name VARCHAR(20),
    Hourly_pay FLOAT NOT NULL,
    Salary DECIMAL(10,2),
    Job VARCHAR(20),
    Hire_date DATE,
    Supervisor_ID int
);

INSERT INTO employees (ID, First_Name, Last_Name, Hourly_pay, Salary, Job, Hire_date, Supervisor_ID)
VALUES
(1, 'John', 'Doe', 20.50, 50000.00, 'Manager', '2023-01-15', NULL),
(2, 'Jane', 'Smith', 18.75, 45000.00, 'Supervisor', '2023-02-20', 1),
(3, 'Michael', 'Johnson', 15.25, 40000.00, 'Assistant', '2023-03-10', 2),
(4, 'Emily', 'Brown', 22.00, 55000.00, 'Manager', '2023-04-05', NULL),
(5, 'Chris', 'Davis', 17.00, 42000.00, 'Supervisor', '2023-05-12', 4),
(6, 'Jessica', 'Wilson', 14.75, 38000.00, 'Assistant', '2023-06-20', 5),
(7, 'Daniel', 'Martinez', 21.25, 52000.00, 'Manager', '2023-07-18', NULL),
(8, 'Sarah', 'Taylor', 19.00, 46000.00, 'Supervisor', '2023-08-25', 7),
(9, 'Kevin', 'Anderson', 16.50, 41000.00, 'Assistant', '2023-09-10', 8),
(10, 'Amanda', 'Thomas', 23.00, 57000.00, 'Manager', '2023-10-30', NULL);

SELECT * FROM employees;

CREATE TRIGGER before_hourly_pay_update 
BEFORE UPDATE ON employees FOR EACH ROW
SET NEW.Salary = (NEW.Hourly_pay * 2080);

-- show all of triggers in DB
SHOW TRIGGERS;

UPDATE employees
SET Hourly_pay = 50
WHERE ID = 1;
-- ----------------------------------------------------------------------------------------------------

DELETE FROM employees
WHERE ID = 6;

SELECT * FROM employees;

CREATE TRIGGER before_hourly_pay_insert
BEFORE INSERT ON employees FexpensesemployeesOR EACH ROW
       SET NEW.Salary = (NEW.Hourly_pay * 2080);
       
INSERT INTO employees
VALUES (6,"Cristiano", "Ronaldo", 100, NULL, 'Manager', '2023-01-15', NULL);

-- ------------------------------------------------------------------------------------------------------
CREATE TABLE expenses (
    ID INT PRIMARY KEY,
    E_Name VARCHAR(50),
    E_Total DECIMAL(10, 2)
);

SELECT * FROM expenses;

INSERT INTO expenses
VALUES (1, "salaries", 0), 
       (2, "supplies", 0), 
       (3, "taxes", 0);
 
 -- ===========================================================================
UPDATE expenses
SET E_Total = (SELECT SUM(Salary) FROM employees)
WHERE E_Name = "salaries";

CREATE TRIGGER after_salary_delete
AFTER DELETE ON employees
FOR EACH ROW
    UPDATE expenses
    SET E_Total = E_Total - OLD.Salary
    WHERE E_Name = "salaries";

CREATE TRIGGER after_salary_insert
AFTER INSERT ON employees
FOR EACH ROW
    UPDATE expenses
    SET E_Total = E_Total + NEW.Salary
    WHERE E_Name = "salaries";

CREATE TRIGGER after_salary_update
AFTER UPDATE ON employees
FOR EACH ROW
    UPDATE expenses
    SET E_Total = E_Total + (NEW.Salary - OLD.Salary)
    WHERE E_Name = "salaries";

SELECT * FROM employees;
SELECT * FROM expenses;

DELETE FROM employees
WHERE ID = 1;

-- tạo test case để test tiếp các Trigger
