-- enable all fks
-- make sure data is fullfilled correctly(parent, child)
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO CUSTOMER(ID, `NAME`, EMAIL, ADDRESS, PHONE, `PASSWORD`)
VALUES(1,	'Susan',	'susan@gmail.com',	'Hòa Khánh Nam',	'123456789',	'88888888'),
	  (2,	'Smith',	'smith@gmail.com',	'Hòa Châu',	        '123456789',	'88888888'),
	  (3,	'Henry',	'henry@gmail.com',	'Hải Châu 1',	    '123456789',	'88888888'),
	  (4,	'Pepe',	    'pepe@gmail.com',	'Hòa Hiệp Bắc',	    '123456789',	'88888888'),
	  (5,	'Carlos',	'carlis@gmail.com',	'Hòa Minh',	        '123456789',	'88888888');

-- CTE: common table expression
-- scope: in sql query start until ;
		
INSERT INTO ORDER_STATUS(ID, `DESC`)
WITH CTE_ORDER_STATUS_DATA AS
(
	SELECT 1 COL_ID, 'Chờ xác nhận' COL_DESC UNION
	SELECT 2, 'Xác nhận thành công'    	     UNION
	SELECT 3, 'Đang đóng gói'          	     UNION
	SELECT 4, 'Đóng gói thành công'    	     UNION
	SELECT 5, 'Đang giao hàng'         	     UNION
	SELECT 6, 'Giao hàng thành công'   	     UNION
	SELECT 7, 'Hủy đơn hàng'           	     UNION
	SELECT 8, 'Giao hàng thất bại'
)
SELECT COL_ID, COL_DESC FROM CTE_ORDER_STATUS_DATA;

INSERT INTO SIZE(ID, SIZE, GENDER, `DESC`)
VALUES  (1,  'S',   0, 'Mô tả: S Nữ chi tiết …'),
		(2,  'S',   1, 'Mô tả: S Nam chi tiết ...'),
		(3,  'M',   0, 'Mô tả: M Nữ chi tiết ...'),
		(4,  'M',   1, 'Mô tả: M Nam chi tiết ...'),
		(5,  'L',   0, 'Mô tả: L Nữ chi tiết ...'),
		(6,  'L',   1, 'Mô tả: L Nam chi tiết ...'),
		(7,  'XL',  0, 'Mô tả: XL Nữ chi tiết ...'),
		(8,  'XL',  1, 'Mô tả: XL Nam chi tiết ...'),
		(9,  'XXL', 0, 'Mô tả: XXL Nữ chi tiết ...'),
		(10, 'XXL', 1, 'Mô tả: XXL Nam chi tiết ...');
        
INSERT INTO ITEM_GROUP(ID, `NAME`)
SELECT 1, 'Áo'    UNION
SELECT 2, 'Quần'  UNION
SELECT 3, 'Giày'  UNION
SELECT 4, 'Dép'   UNION
SELECT 5, 'Mũ'    UNION
SELECT 6, 'Thắt lưng';

INSERT INTO PAYMENT_METHOD(ID, `DESC`)
VALUES(1, 'Tiền mặt'),
	  (2, 'Thẻ tín dụng'),
      (3, 'Thẻ ghi nợ'),
      (4, 'Ví điện tử');
      
INSERT INTO DEPARTMENT(ID, `NAME`)
VALUES(1, 'Bộ phận nhân sự'),
	  (2, 'Bộ phận bán hàng'),
      (3, 'Bộ phận giao hàng'),
      (4, 'Bộ phận giám sát'),
      (5, 'Bộ phận quảng cáo');
      
INSERT INTO ITEM(ID, `NAME`, MATERIAL, BUY_PRICE, COLOR, ITEM_GROUP_ID)
VALUES  (1, 'Áo 1',         'Chất Liệu 1', 195, 'Trắng',	1),
		(2, 'Áo 2',         'Chất Liệu 2', 185, 'Đen',	    3),
		(3, 'Giày 1',       'Chất Liệu 3', 175, 'Trắng',	3),
		(4, 'Giày 2',       'Chất Liệu 4', 165, 'Đen',	    3),
		(5, 'Giày 3',       'Chất Liệu 1', 155, 'Trắng',	5),
		(6, 'Mũ 1',         'Chất Liệu 2', 284, 'Đen',	    5),
		(7, 'Mũ 2',         'Chất Liệu 3', 268, 'Trắng',	4),
		(8, 'Dép 1',        'Chất Liệu 4', 252, 'Đen',	    5),
		(9, 'Mũ 3',         'Chất Liệu 5', 236, 'Trắng',	5),
		(10, 'Thắt lưng 1', 'Chất Liệu 2', 220, 'Đen',	    6),
		(11, 'Thắt lưng 2', 'Chất Liệu 2', 195, 'Trắng',	6),
		(12, 'Quần 3',	   'Chất Liệu 3', 185, 'Đen',	    2),
		(13, 'Quần 1',	   'Chất Liệu 3', 175, 'Đen',	    2),
		(14, 'Quần 2',	   'Chất Liệu 1', 165, 'Trắng',	    2);
        
INSERT INTO EMPLOYEE(ID,`NAME`, PHONE, ADDRESS, DEPARTMENT_ID)
VALUES  (1,	'Nhân viên A',	'123456789',	'Đà Nẵng',	1),
		(2, 'Nhân viên B',	'123456789',	'Đà Nẵng',	1),
		(3,	'Nhân viên C',	'123456789',	'Đà Nẵng',	2),
		(4,	'Nhân viên D',	'123456789',	'Đà Nẵng',	3),
		(5,	'Nhân viên E',	'123456789',	'Đà Nẵng',	2),
		(6,	'Nhân viên F',	'123456789',	'Đà Nẵng',	2),
		(7,	'Nhân viên G',	'123456789',	'Đà Nẵng',	4),
		(8,	'Nhân viên K',	'123456789',	'Đà Nẵng',	5);

INSERT INTO `ORDER`(ID, DELIVERY_ADDRESS, DELIVERY_FEE, CREATED_AT, PAYMENT_METHOD_ID, EMPLOYEE_ID, CUSTOMER_ID)
VALUES	(1,  'Địa chỉ 1',	20,	str_to_date('7/9/2023 22:05', '%d/%m/%Y %H:%i'),	1,	1,	1),
		(2,  'Địa chỉ 2',	20,	str_to_date('7/7/2023 22:05', '%d/%m/%Y %H:%i'),	1,	1,	2),
		(3,  'Địa chỉ 3',	20,	str_to_date('7/8/2023 22:05', '%d/%m/%Y %H:%i'),	1,	2,	3),
		(4,  'Địa chỉ 4',	30,	str_to_date('15/2/2023 22:05', '%d/%m/%Y %H:%i'),	2,	3,	4),
		(5,  'Địa chỉ 5',	30,	str_to_date('15/2/2023 22:05', '%d/%m/%Y %H:%i'),	3,	2,	2),
		(6,  'Địa chỉ 6',	20,	str_to_date('7/9/2023 22:05', '%d/%m/%Y %H:%i'),	1,	3,	5),
		(7,  'Địa chỉ 7',	40,	str_to_date('12/12/2023 22:05', '%d/%m/%Y %H:%i'),	2,	4,	1),
		(8,  'Địa chỉ 8',	20,	str_to_date('7/9/2023 22:05', '%d/%m/%Y %H:%i'),	3,	5,	2),
		(9,  'Địa chỉ 9',	50,	str_to_date('7/9/2023 22:05', '%d/%m/%Y %H:%i'),	3,	2,	3),
		(10, 'Địa chỉ 10',	20,	str_to_date('7/9/2023 22:05', '%d/%m/%Y %H:%i'),	2,	2,	5),
		(11, 'Địa chỉ 11',	10,	str_to_date('6/9/2023 22:05', '%d/%m/%Y %H:%i'),	2,	1,	4),
		(12, 'Địa chỉ 12',	20,	str_to_date('7/9/2023 22:05', '%d/%m/%Y %H:%i'),	1,	1,	3);

SET @running = 0; 
INSERT INTO ITEM_DETAIL(ID, ITEM_ID, SIZE_ID, AMOUNT, SALES_PRICE)
WITH CTE_ITEM_DETAIL_ODD AS
(
	SELECT ITEM.ID            ITEM_ID,
		   SIZE.ID            SIZE_ID,
		   100                AMOUNT,
		   ITEM.ID * 100 + SIZE.ID * 10 SALES_PRICE
	  FROM ITEM, SIZE
	 WHERE ITEM.ID MOD 2 != 0
	   AND SIZE.ID MOD 2 != 0
	 ORDER BY ITEM.ID, SIZE.ID
),
CTE_ITEM_DETAIL_EVEN AS
(
	SELECT ITEM.ID            ITEM_ID,
		   SIZE.ID            SIZE_ID,
		   120                AMOUNT,
		   ITEM.ID * 100 + SIZE.ID * 20 SALES_PRICE
	  FROM ITEM, SIZE
	 WHERE ITEM.ID MOD 2 = 0
	   AND SIZE.ID MOD 2 = 0
	 ORDER BY ITEM.ID, SIZE.ID
),
CTE_ITEM_DETAIL_DATA AS
(
	SELECT * FROM CTE_ITEM_DETAIL_ODD
    UNION
    SELECT * FROM CTE_ITEM_DETAIL_EVEN
)
SELECT  @running := @running + 1 AS ID,
		CTE_ITEM_DETAIL_DATA.* 
FROM CTE_ITEM_DETAIL_DATA
ORDER BY ITEM_ID, SIZE_ID;

INSERT INTO ORDER_DETAIL(ID, ORDER_ID, ITEM_DETAIL_ID, AMOUNT)
VALUES
(1,1,4,2), (2,1,31,5), (3,1,49,1), (4,1,68,2), (5,2,39,2),
(6,2,48,2), (7,2,50,12), (8,2,53,3), (9,3,21,2), (10,3,22,2),
(11,3,39,2), (12,3,49,11), (13,4,11,2), (14,4,41,3), (15,4,46,3),
(16,4,69,3), (17,5,5,3), (18,5,24,2), (19,5,39,8),(20,5,56,8),
(21,6,10,2), (22,6,23,9), (23,6,29,3), (24,6,30,11), (25,7,17,2),
(26,7,43,15), (27,7,54,10), (28,7,57,5), (29,8,8,20), (30,8,18,7),
(31,8,59,2), (32,8,65,9), (33,9,29,10), (34,9,34,1), (35,9,38,7),
(36,9,53,2), (37,10,22,10), (38,10,24,10), (39,10,40,2), (40,10,50,2),
(41,11,37,2), (42,11,38,2), (43,11,48,11), (44,11,51,2), (45,12,1,12),
(46,12,29,2), (47,12,35,1), (48,12,42,3);

-- DATE_SUB(date, INTERVAL value interval)

SELECT 0 INTO @running;
INSERT INTO ORDER_STATUS_DETAIL(ID, ORDER_ID, ORDER_STATUS_ID, EMPLOYEE_ID, UPDATED_AT)
WITH CTE_ORDER_STATUS_DETAIL_DATA AS
(
	-- 1. Đơn hàng từ 1 - 5 --> Giao thành công (trạng thái từ 1 - 6) --> Nhân viên 1(ngẫu nhiên)		
	SELECT od.ID  ORDER_ID,
		   ods.ID ORDER_STATUS_ID,
		   FLOOR(1 + (rand() * (SELECT MAX(ID) FROM EMPLOYEE))) EMPLOYEE_ID,
		   DATE_SUB(current_timestamp(), INTERVAL (6-ods.ID) DAY) UPDATED_AT
	  FROM `ORDER` od, 
			ORDER_STATUS ods
	 WHERE od.ID BETWEEN 1 AND 5 -- [1,5] inclusive
	   AND ods.ID BETWEEN 1 AND 6

	UNION

	-- 2 Đơn hàng từ 6 - 8 --> Đóng gói thành công (trạng thái từ 1 - 4) --> Nhân viên 2
	SELECT od.ID  ORDER_ID,
		   ods.ID ORDER_STATUS_ID,
		   FLOOR(1 + (rand() * (SELECT MAX(ID) FROM EMPLOYEE))) EMPLOYEE_ID,
		   DATE_SUB(current_timestamp(), INTERVAL (4-ods.ID) DAY) UPDATED_AT
	  FROM `ORDER` od, 
			ORDER_STATUS ods
	 WHERE od.ID BETWEEN 6 AND 8 -- [6,8] inclusive
	   AND ods.ID BETWEEN 1 AND 4	

	UNION

	-- 3. Đơn hàng từ 9 - 10 --> Đang giao hàng (trạng thái từ 1 - 5) --> Nhân viên 3
	SELECT od.ID  ORDER_ID,
		   ods.ID ORDER_STATUS_ID,
		   FLOOR(1 + (rand() * (SELECT MAX(ID) FROM EMPLOYEE))) EMPLOYEE_ID,
		   DATE_SUB(current_timestamp(), INTERVAL (5-ods.ID) DAY) UPDATED_AT
	  FROM `ORDER` od, 
			ORDER_STATUS ods
	 WHERE od.ID BETWEEN 9 AND 10 -- [9,10] inclusive
	   AND ods.ID BETWEEN 1 AND 5		

	UNION

	-- 4. Đơn hàng từ 11 - 12 --> Hủy đơn hàng (trạng thái 1, 7) --> Nhân viên 4		
	SELECT od.ID  ORDER_ID,
		   ods.ID ORDER_STATUS_ID,
		   FLOOR(1 + (rand() * (SELECT MAX(ID) FROM EMPLOYEE))) EMPLOYEE_ID,
		   DATE_SUB(current_timestamp(), INTERVAL (7-ods.ID) DAY) UPDATED_AT
	  FROM `ORDER` od, 
			ORDER_STATUS ods
	 WHERE od.ID BETWEEN 11 AND 12 -- [11,12] inclusive
	   AND ods.ID IN (1,7)
)
SELECT @running := @running + 1 AS ID,
       cte_data.*
  FROM CTE_ORDER_STATUS_DETAIL_DATA cte_data
 ORDER BY ORDER_ID, ORDER_STATUS_ID;
 
INSERT INTO BILL(ID, CREATED_AT, TOTAL_OF_MONEY, ORDER_ID)
SELECT ID, current_timestamp(), 0, ID FROM `ORDER` ORDER BY ID;

