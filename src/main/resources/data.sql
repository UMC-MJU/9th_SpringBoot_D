INSERT IGNORE INTO members (nick_name, role, point, status, created_at, updated_at) 
VALUES ('길동이', 'CUSTOMER', 0, 'ACTIVE', NOW(), NOW());

INSERT IGNORE INTO category (name) VALUES ('한식');

INSERT IGNORE INTO addresses (address_name)
VALUES ('서울시 강남구');

INSERT IGNORE INTO store (name, category_id, address_id, min_order_price, average_rating, created_at, updated_at) 
VALUES ('맛있는 식당', 1, 1, 10000, 4.5, NOW(), NOW());

INSERT IGNORE INTO review (member_id, store_id, content, rating, created_at, updated_at) 
VALUES (1, 1, '정말 맛있어요!', 5, NOW(), NOW());

INSERT IGNORE INTO review (member_id, store_id, content, rating, created_at, updated_at) 
VALUES (1, 1, '또 가고 싶어요.', 4, NOW(), NOW());
