-- A. INSERT
-- Sử dụng bảng dữ liệu mẫu. Thực hiện thêm dữ liệu vào cơ sở dữ liệu cho table
-- + Mathang
-- + LoaiHang
-- + Sử dụng shopping refresh data.sql để refresh data

-- ======================= REFRESH DATA =======================
-- DISABLE FOREIGN KEY
-- B. DELETE
-- 1. Xóa nhân viên có tên 'abcxyz' trong hệ thống
-- 2. Xóa bộ phận giao hàng trong hệ thống
-- 3. Xóa tất cả các mặt hàng thuộc loại hàng là mũ
-- 4. Xóa tất cả các mặt hàng trong hệ thống
-- 5. Vì hệ thống bị lỗi. Hủy tất cả các đơn hàng ngày 23/11/2019
--    Thực hiện xóa các đơn hàng bị lỗi

-- ======================= REFRESH DATA =======================
-- C. UPDATE
-- 1. Cập nhật tên mới cho phòng ban có MaPB = 2 thành 'Bộ phận quản lý'
-- 2. Cập nhật ghi chú của đơn hàng 01 thành 'Giao hàng sau 10H sáng'
-- 3. Đơn hàng 01 đã được giao hàng thành công. Thực hiện cập nhật
--    MaTT = 03, TrangThai = 'Đã giao', ThoiGian = Thời gian hiện tại
-- 4. Cập nhật số tiền cần thanh toán trong bảng hóa đơn thành 890 cho đơn hàng 01
-- 5. Cập nhật tất cả các mặt hàng thuộc loại hàng là 'Mũ' với giá bán là 199K

-- ======================= REFRESH DATA - DEMO SELECT 1 TABLE =======================
-- D. SELECT - SLIDE 25-44
-- 1. Toàn bộ thông tin các loại hàng
SELECT * FROM item_group;

-- Mặt hàng thuộc loại hàng là 'Thắt lưng'
-- Truy vấn lồng
SELECT * FROM item
WHERE ITEM_GROUP_ID IN (SELECT ID 
                        FROM item_group 
						WHERE NAME = 'Thắt lưng');
                        
-- Truy vấn lồng song song		
SELECT * FROM item
WHERE EXISTS (SELECT *
			  FROM item_group
              WHERE NAME = 'Thắt lưng'
              AND item_group.ID = item.ITEM_GROUP_ID);

-- +) Truy vấn lồng
-- Thực thi câu lệnh truy vấn con trước, được kết quả thực câu lệnh cha
-- oralce: limit in IN clause is 1000 values

-- +) Truy vấn lồng song song <cơ chế hoạt động như JOIN hai bảng>
-- Duyệt từng phần tử bên bảng cha
-- Lấy ra các phần tử ở bảng cha khi dòng hiện tại thỏa mãn điều kiện trong bảng con
-- Mệnh đề SELECT trong bảng con không quan trong, SELECT bất kỳ column nào                

-- Top 5 mặt hàng(kích cỡ) có giá bán cao nhất
SELECT * 
  FROM item_detail
  ORDER BY SALES_PRICE DESC
  LIMIT 5;

-- Nếu trường mặt hàng giá bán cao thứ 5 có giá trùng với các mặt hàng khác, lấy hết 
WITH CTE_TOP_5_SALES_PRICE AS (
     SELECT SALES_PRICE
     FROM item_detail
     ORDER BY SALES_PRICE DESC, AMOUNT DESC
     LIMIT 5
)
  SELECT * 
  FROM item_detail 
  -- WHERE SALES_PRICE IN (SELECT SALES_PRICE
					  -- FROM CTE_TOP_5_SALES_PRICE)
  WHERE EXISTS (SELECT 43
                FROM CTE_TOP_5_SALES_PRICE top5
                WHERE item_detail.SALES_PRICE = top5.SALES_PRICE)
  ORDER BY SALES_PRICE DESC, AMOUNT DESC;

-- 2. Đơn hàng	
-- Được bán trong ngày 7/9/2023
SELECT *
  FROM `order`
  -- WHERE cast(CREATED_AT AS DATE) = '2023-9-7'; 
  WHERE cast(CREATED_AT AS DATE) = str_to_date('7/9/2023','%d/%m/%Y');

-- +) default pattern yyyy-mm-dd
--    '2023-02-15' date
--    '15-02-2023' varchar
--    cast(CREATED_AT AS DATE) = '2023-02-15'
--    cast(CREATED_AT AS DATE) = str_to_date('15/02/2023', '%d/%m/%Y') with any pattern

-- Được bán từ ngày 5/9/2023 đến ngày 02/12/2023
SELECT *
  FROM `order`
  WHERE cast(CREATED_AT AS DATE) BETWEEN '2023-9-5' AND '2023-12-2';

-- Được bán trong tháng 02/2023
SELECT *
  FROM `order`
  WHERE month(CREATED_AT) = 2 AND year(CREATED_AT) = 2023; 

-- Được giao hàng tại Hòa Khánh

-- 3. Giá của toàn bộ các mặt hàng sau khi được khuyến mãi 27%, làm tròn 2 chữ số thập phân
SELECT *,
       (SALES_PRICE * 0.73) PROMOTION_PRICE,
       ROUND(SALES_PRICE * 0.73, 2) ROUNDED_PROMOTION_PRICE
  FROM item_detail;
   
-- 4. Giam giá tất cả các mặt hàng trong ngày 15/2/2023
  --  decimal(10, 2) --> MathContext.DECIMAL32 | 64 | 128
  SELECT * FROM `order` WHERE cast(CREATED_AT AS DATE) = "2023-2-15"; -- id = 4,5
  SELECT * FROM order_detail WHERE ORDER_ID IN (4,5); -- item_detail_id (5,11,24,39,41,46,56,69);
  SELECT * FROM item_detail WHERE ID IN (5,11,24,39,41,46,56,69);
  
  SELECT SALES_PRICE,
        CAST(SALES_PRICE * 0.8 AS DECIMAL(10, 2)) ROUNDED_PROMOTION_SALES_PRICE
    FROM item_detail itd
    WHERE itd.ID IN (SELECT odd.ITEM_DETAIL_ID
                       FROM order_detail odd
                       WHERE odd.ORDER_ID IN (SELECT od.ID
                                                FROM `order` od
                                                WHERE cast(od.CREATED_AT AS DATE) = "2023-2-15")
                     );
                     

-- 5. Liệt kê tất cả các màu sắc của sản phẩm có bán trong cửa hàng.
SELECT COLOR 
  FROM item
  GROUP BY COLOR;

-- 6. Liệt kê tất cả các mặt hàng (MaMH, TenMH, ThoiGianDatHang) được bán trong ngày 7/9/2023
SELECT * FROM item;
SELECT * FROM `order`;

SELECT it.ID,
       it.`NAME`,
       od.CREATED_AT ORDER_DAY
  FROM item it 
  JOIN item_detail itd 
	ON it.ID = itd.ITEM_ID
  JOIN order_detail odd 
    ON itd.ID = odd.ITEM_DETAIL_ID
  JOIN `order` od 
    ON od.ID = odd.ORDER_ID
  WHERE cast(od.CREATED_AT AS DATE) = "2023-9-7";

-- 7. Liệt kê các mặt hàng có giá bán từ 100 - 300
SELECT * FROM item_detail;

SELECT it.ID,
       it.`NAME`,
       itd.SIZE_ID,
       itd.SALES_PRICE
  FROM item it
  JOIN item_detail itd
    ON it.ID = itd.ITEM_ID
  WHERE itd.SALES_PRICE BETWEEN 100 AND 300;

-- 8. Liệt kê tất cả các mặt hàng thuộc loại hàng là 'Mũ' và 'Thắt lưng'
SELECT it.ID, 
	   it.`NAME`
  FROM item it
  JOIN item_group itg
    ON itg.ID = it.ITEM_GROUP_ID
  WHERE it.`NAME` LIKE '%Mũ%' OR it.`NAME` LIKE '%Thắt lưng%';

-- 9. Liệt kê các đơn hàng được đặt trong ngày (28/11/2019, 14/12/2019)
   -- similar
-- ======================= REFRESH DATA - DEMO MULTI TABLES AND GROUPING BY =======================
-- 11. Sắp xếp các mặt hàng với giá bán tăng dần
SELECT * FROM item;
SELECT * FROM item_detail;

SELECT it.`NAME`,
	   itd.SIZE_ID,
	   itd.SALES_PRICE
  FROM item it
  JOIN item_detail itd
  ON it.ID = itd.ITEM_ID
  ORDER BY itd.SALES_PRICE;
  
-- 11. Tìm giá bán lớn nhất trong 'mỗi mặt hàng' --> GROUP BY FOR SURE!
--     Sắp xếp giá bán giảm dần
SELECT * FROM item;
SELECT * FROM item_detail;

SELECT it.ID ITEM_ID,
       it.`NAME` ITEM_NAME,
       MAX(itd.SALES_PRICE) MAX_SALES_PRICE
  FROM item it
  JOIN item_detail itd
    ON it.ID = itd.ITEM_ID
  GROUP BY it.ID, it.`NAME`
  ORDER BY MAX_SALES_PRICE DESC;

-- 12. Sắp xếp các mặt hàng với giá mua giảm dần
SELECT ID, `NAME`, BUY_PRICE 
  FROM item
  ORDER BY BUY_PRICE DESC;

-- 13. Sắp xếp các mặt hàng với giá bán tăng dần, giá mua giảm dần
SELECT it.ID ITEM_ID,
       it.NAME ITEM_NAME,
       itd.SIZE_ID,
       itd.SALES_PRICE,
       it.BUY_PRICE
  FROM item it
  JOIN item_detail itd
    ON it.ID = itd.ITEM_ID
  ORDER BY itd.SALES_PRICE ASC,
           it.BUY_PRICE DESC;

-- 14. Đếm số lượng các mặt hàng trong hệ thống
SELECT COUNT(*) AS AMOUNT_OF_ITEMS
  FROM item;
  -- *) "Đề" --> đếm số lượng hàng trong bảng item 
  --         --> không cần dùng GROUP BY

-- 15. Đếm số lượng 'Giày' được bán trong ngày 15/02/2023
SELECT * FROM `order`;
SELECT * FROM order_detail;
SELECT * FROM item_detail;
SELECT * FROM item;

SELECT SUM(odd.AMOUNT) AS NUMBER_SALES 
  FROM item_group itg
  JOIN item it 
  ON itg.ID = it.ITEM_GROUP_ID
  JOIN item_detail itd
  ON it.ID = itd.ITEM_ID
  JOIN order_detail odd 
  ON itd.ID = odd.ITEM_DETAIL_ID
  JOIN `order` od
  ON od.ID = odd.ORDER_ID
  WHERE DATE(od.CREATED_AT) = "2023-2-15" AND
        itg.NAME = "Giày";
        
-- 16. Tổng số lượng các mặt hàng còn lại <số lượng gốc ban đầu mới nhập hàng về> 
--     theo từng loại hàng (item_group)
-- MaLoai  TenLoai SoLuong
-- 	1       Giày    20
-- 	2       Áo      28

SELECT * FROM item;
SELECT * FROM item_detail;
SELECT * FROM item_group;

SELECT itg.ID, 
       itg.`NAME`, 
       SUM(itd.AMOUNT) SUM_CURRENT_AMOUNT,
       group_concat(concat(it.ID, '-', it.NAME, '-', itd.SIZE_ID, '-', itd.AMOUNT) SEPARATOR ', ') DETAIL_INFO
  FROM item_group itg
  JOIN item it
  ON itg.ID = it.ITEM_GROUP_ID
  JOIN item_detail itd
  ON it.ID = itd.ITEM_ID
  GROUP BY itg.ID, itg.`NAME`;  

-- 17. Tìm mặt hàng có giá bán cao nhất trong loại hàng 'Giày' 
-- <XEM LẠI>
SELECT it.ID,
       it.`NAME`,
       itd.SALES_PRICE
  FROM item_group itg
  JOIN item it
  ON itg.ID = it.ITEM_GROUP_ID -- Vấn đề: GROUP BY theo loại hàng nhưng lại muốn SELECT --> đưa ra thông tin của mặt hàng
  JOIN item_detail itd                    -- > dùng limit 1 và mệnh đề where
  ON it.ID = itd.ITEM_ID
  WHERE itg.`NAME` = "Giày" 
  ORDER BY itd.SALES_PRICE DESC
  LIMIT 1;
         
-- 18. Tìm mặt hàng có giá bán cao nhất của mỗi loại hàng
SELECT itg.ID, 
       itg.`NAME`,
       MAX(itd.SALES_PRICE) MAX_SALES_PRICE
  FROM item_group itg
  JOIN item it
  ON itg.ID = it.ITEM_GROUP_ID
  JOIN item_detail itd 
  ON it.ID = itd.ITEM_ID
  GROUP BY itg.ID, itg.`NAME`;

-- 20. Hiển thị tổng số lượng mặt hàng của mỗi loại hàng trong hệ thống
--     Điều kiện tổng số lượng > 1500 mặt hàng >> HAVING
SELECT itg.ID ITEM_GROUP_ID,
       itg.`NAME` ITEM_GROUP_NAME,
       SUM(itd.AMOUNT) CURRENT_AMOUNT_OF_ITEMS
  FROM item_group itg
  JOIN item it
  ON itg.ID = it.ITEM_GROUP_ID
  JOIN item_detail itd 
  ON it.ID = itd.ITEM_ID
  GROUP BY itg.ID, itg.`NAME`
  HAVING SUM(itd.AMOUNT) > 1500;

-- =================================================================================
UPDATE item_detail SET AMOUNT = 555 WHERE ID = 67; -- ig 2
UPDATE item_detail SET AMOUNT = 888 WHERE ID = 33; -- ig 4
UPDATE item_detail SET AMOUNT = 555 WHERE ID = 34; -- ig 4
UPDATE item_detail SET AMOUNT = 720 WHERE ID = 26; -- ig 5
UPDATE item_detail SET AMOUNT = 562 WHERE ID = 8;
UPDATE item_detail SET AMOUNT = 346 WHERE ID = 17;
UPDATE item_detail SET AMOUNT = 984 WHERE ID = 39;
UPDATE item_detail SET AMOUNT = 220 WHERE ID = 48;
-- 21. Hiển thị mặt hàng[kèm với size] có số lượng nhiều nhất trong mỗi loại hàng
-- item_group, item, item_detail --> group by item_group_id --> max(amount)
-- item_group_id --> max

-- test
SELECT *
  FROM item_group itg
  JOIN item it
  ON itg.ID = it.ITEM_GROUP_ID
  JOIN item_detail itd
  ON it.ID = itd.ITEM_ID
  WHERE itg.ID IN (2, 4, 5)
  ORDER BY itg.ID, it.`NAME`;
  
  -- process
WITH CTE_ITEM_GROUP_DETAIL AS
(
     SELECT itg.ID        ITEM_GROUP_ID,
            itg.NAME      ITEM_GROUP_NAME,
            it.ID         ITEM_ID,
            it.NAME       ITEM_NAME,
            itd.ID        ITEM_DETAIL_ID,
            itd.SIZE_ID   SIZE,
            itd.AMOUNT    AMOUNT
       FROM item_group itg
       JOIN item it
       ON itg.ID = it.ITEM_GROUP_ID
       JOIN item_detail itd
       ON itd.ITEM_ID = it.ID
),

CTE_ITEM_GROUP_MAX_AMOUNT AS 
(
     SELECT ITEM_GROUP_ID,
            ITEM_GROUP_NAME,
            MAX(AMOUNT) MAX_REMAINNING_ITEM_AMOUNT
       FROM CTE_ITEM_GROUP_DETAIL
       GROUP BY ITEM_GROUP_ID, ITEM_GROUP_NAME	
)

SELECT itg_d.ITEM_GROUP_ID, itg_d.ITEM_GROUP_NAME, 
       itg_d.ITEM_ID,       itg_d.ITEM_NAME, 
       itg_d.SIZE,          itg_d.AMOUNT
  FROM CTE_ITEM_GROUP_DETAIL itg_d
  JOIN CTE_ITEM_GROUP_MAX_AMOUNT itg_max
  ON itg_d.ITEM_GROUP_ID = itg_max.ITEM_GROUP_ID  AND
	 itg_d.AMOUNT = itg_max.MAX_REMAINNING_ITEM_AMOUNT;
  
-- 22. Hiển thị giá bán trung bình của mỗi loại hàng

-- 23. In ra 3 loại hàng có số lượng hàng còn lại nhiều nhất ở thời điểm hiện tại

-- 24. Liệt kê những mặt hàng có MaLoai = 2 và thuộc đơn hàng 100100

-- 25. Tìm những mặt hàng có Mã Loại = 2 và đã được bán trong ngày 15/02/2023
WITH BE_SALED_ITEMS AS (
     SELECT it.ID ITEM_ID
       FROM item it 
	   JOIN item_detail itd
       ON it.ID = itd.ITEM_ID
       JOIN order_detail odd
       ON itd.ID = odd.ITEM_DETAIL_ID 
       JOIN `order` od
       ON od.ID = odd.ORDER_ID
       WHERE DATE(od.CREATED_AT) = "2023-2-15"
)

SELECT *,
       '2023-02-15' SALES_DATE
  FROM item it
-- WHERE ID IN (SELECT ITEM_ID FROM SALES_ITEM);
-- WHERE EXISTS (SELECT 1 FROM SALES_ITEM WHERE it.ID = ITEM_ID);
  JOIN BE_SALED_ITEMS bsi
    ON it.ID = bsi.ITEM_ID
  WHERE it.ITEM_GROUP_ID = 2;

-- 26. Liệt kê những mặt hàng là 'Mũ' không bán được trong ngày 15/02/2023
WITH BE_SALED_ITEMS AS (
     SELECT it.ID ITEM_ID
       FROM item it 
	   JOIN item_detail itd
       ON it.ID = itd.ITEM_ID
       JOIN order_detail odd
       ON itd.ID = odd.ITEM_DETAIL_ID 
       JOIN `order` od
       ON od.ID = odd.ORDER_ID
       WHERE DATE(od.CREATED_AT) = "2023-2-15"
)

SELECT *, 
       '2023-02-15' NOT_BE_SALES_DATE
  FROM item it  
  WHERE NOT EXISTS (SELECT 1 FROM BE_SALED_ITEMS bsi WHERE it.ID = bsi.ITEM_ID)  
    AND EXISTS (SELECT 1 FROM item_group itg WHERE `NAME` LIKE '%Mũ%' 
			    AND it.ITEM_GROUP_ID = itg.ID);
                
-- 27. Cập nhật giá bán của tất cả các mặt hàng thuộc loại hàng 'Áo' thành 199

-- 28. Backup data. Tạo table LoaiHang_SaoLuu(MaLoai, TenLoai)
--     Sao chép dữ liệu từ bảng LoaiHang sang LoaiHang_SaoLuu
CREATE TABLE BACKUP_ITEM_GROUP (
   ID INT, 
   `NAME` VARCHAR(100)
);

INSERT INTO BACKUP_ITEM_GROUP(ID, `NAME`)
SELECT ID, `NAME` FROM item_group;

SELECT * FROM BACKUP_ITEM_GROUP;

-- 30. Liệt kê 2 sản phẩm (có số lượng tồn kho nhiều nhất) của loại hàng 'Áo' và 'Quần'
--  1:38:00 bớt lười dcmmmm nha Đạt
-- B1: Tìm số lượng hàng còn lại của mỗi mặt hàng thuộc loại hàng 'Áo', 'Quần'
-- B2: ORDER BY SoLuongTon DESC
-- B3: LIMIT 2
SELECT itg.ID        ITEM_GROUP_ID,
           itg.NAME      ITEM_GROUP_NAME,
           it.ID         ITEM_ID,
           it.NAME       ITEM_NAME,
           itd.ID        ITEM_DETAIL_ID,
           itd.SIZE_ID   SIZE,
           itd.AMOUNT    AMOUNT
  FROM item_group itg
  JOIN item it
	ON itg.ID = it.ITEM_GROUP_ID
  JOIN item_detail itd
	ON it.ID = itd.ITEM_ID
  WHERE itg.NAME LIKE '%Áo%' OR itg.NAME LIKE '%Quần%'
  ORDER BY itd.AMOUNT DESC, itd.ID DESC
  LIMIT 2;
  
-- Cách 2
WITH CTE_ITEMS AS 
(
SELECT itg.ID        ITEM_GROUP_ID,
	   itg.NAME      ITEM_GROUP_NAME,
	   it.ID         ITEM_ID,
	   it.NAME       ITEM_NAME,
	   itd.ID        ITEM_DETAIL_ID,
	   itd.SIZE_ID   SIZE,
	   itd.AMOUNT    AMOUNT
  FROM item_group itg
  JOIN item it
	ON itg.ID = it.ITEM_GROUP_ID
  JOIN item_detail itd
	ON it.ID = itd.ITEM_ID
  WHERE itg.NAME LIKE '%Áo%' OR itg.NAME LIKE '%Quần%'
),
CTE_TOP_2_SHIRTS AS 
(
	SELECT * FROM CTE_ITEMS WHERE ITEM_GROUP_NAME LIKE '%Áo%' ORDER BY AMOUNT DESC, ITEM_DETAIL_ID DESC LIMIT 2
),
CTE_TOP_2_PANTS AS
(
	SELECT * FROM CTE_ITEMS WHERE ITEM_GROUP_NAME LIKE '%Quần%' ORDER BY AMOUNT DESC, ITEM_DETAIL_ID DESC LIMIT 2
)
SELECT * FROM CTE_TOP_2_SHIRTS
UNION
SELECT * FROM CTE_TOP_2_PANTS;

-- 31. Tính tổng tiền cho đơn hàng 02
   -- Với tổng tiền được tính bằng tổng các sản phẩm và số lượng của sản phẩm tương ứng

-- 32. Xuất thông tin hóa đơn của đơn hàng 02 với thông tin như sau.
	-- SoDH ChiTietDonHang           TongTien
    -- 02   TenMH:GiaBan:SoLuong     100

-- 1	Áo 1	1
-- 3	Giày 1	3
-- 5	Giày 3	5
-- 7	Mũ 2	4
-- 8	Dép 1	5

-- DEMO EXISTS | NOT EXITS


-- 1	Áo
-- 2	Quần
-- 5	Mũ 


-- TEMPORARY
-- CROSS JOIN

-- truy vấn con(sub query) --> IN chạy câu truy vấn con trước

  
-- DEMO EXISTS

-- truy vấn con(sub query) --> IN chạy câu truy vấn con trước
-- lấy từng dòng trong main query
--    lấy từng dòng trong sub query
--        nếu thỏa mãn điều kiện(???) 
--        --> lấy dòng của main query ra

ALTER TABLE item_group
ADD CONSTRAINT UNQ_ITEM_GROUP_NAME UNIQUE(NAME);

-- USE FOR JDBC

SELECT it.*,
	   '2023-09-07' SALES_DATE
  FROM item it
  JOIN item_detail itd
  ON it.ID = itd.ITEM_ID
  JOIN order_detail odt
  ON itd.ID = odt.ITEM_DETAIL_ID
  JOIN `order` od
  ON od.ID = odt.ORDER_ID
  WHERE CAST(od.CREATED_AT AS DATE) = '2023-09-07';





