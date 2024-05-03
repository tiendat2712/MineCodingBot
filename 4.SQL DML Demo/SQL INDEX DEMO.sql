-- DEMO INDEX - 14 rows
SELECT * FROM item;

-- singleton row - constant
-- query cost - 1(100%)
-- loop n - get n
-- rows = 1

-- where by primary key(auto index - unique)
EXPLAIN SELECT * FROM item WHERE ID = 6;

-- full table scan
-- query cost 1.65

-- where by normal column
-- rows = 14
ALTER TABLE item ADD INDEX IDX_NAME(NAME);
EXPLAIN SELECT * FROM item WHERE NAME = 'Mũ 1';

-- rows = 3
EXPLAIN SELECT * FROM item WHERE ITEM_GROUP_ID = 3;