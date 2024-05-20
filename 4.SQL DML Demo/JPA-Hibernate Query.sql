-- Câu 1: Display item-groups
-- a. ALL
     SELECT * FROM item_group;
-- b. by ID/NAME

-- Câu 2: Display items
-- a. Not contain the item-group infomation
     SELECT * FROM item;
-- b. Contain the item-group infomation
-- c. Display all items from each item-groups

-- Câu 3: Display items by ITEM_ID
-- a. Using native | persistence query return entity with no cache 
-- b. Using Session#get - 1st level cache