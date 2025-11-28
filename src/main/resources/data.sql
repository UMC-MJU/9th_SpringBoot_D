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

INSERT IGNORE INTO mission (store_id, description, reward_points, start_date, end_date, created_at, updated_at)
VALUES (1, '10000원 이상 주문하기', 500, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), NOW(), NOW());

INSERT IGNORE INTO mission (store_id, description, reward_points, start_date, end_date, created_at, updated_at)
VALUES (1, '리뷰 작성하기', 300, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW(), NOW());
