-- disable all fks
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM bill;
DELETE FROM customer;
DELETE FROM department;
DELETE FROM employee;
DELETE FROM item;
DELETE FROM item_detail;
DELETE FROM item_group;
DELETE FROM `order`;
DELETE FROM order_detail;
DELETE FROM order_status;
DELETE FROM order_status_detail;
DELETE FROM payment_method;
DELETE FROM size;

-- enable all fks
SET FOREIGN_KEY_CHECKS = 1;