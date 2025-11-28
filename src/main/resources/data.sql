INSERT INTO members (nick_name, role, point, status, created_at, updated_at) 
VALUES ('길동이', 'USER', 0, 'ACTIVE', NOW(), NOW());

INSERT INTO category (name) VALUES ('한식');

INSERT INTO addresses (address_name)
VALUES ('서울시 강남구');

INSERT INTO store (name, category_id, address_id, min_order_price, average_rating, created_at, updated_at) 
VALUES ('맛있는 식당', 1, 1, 10000, 4.5, NOW(), NOW());

INSERT INTO review (member_id, store_id, content, rating, created_at, updated_at) 
VALUES (1, 1, '정말 맛있어요!', 5, NOW(), NOW());

INSERT INTO review (member_id, store_id, content, rating, created_at, updated_at) 
VALUES (1, 1, '또 가고 싶어요.', 4, NOW(), NOW());
