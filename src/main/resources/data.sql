--
-- data.sql - UMC 9th 프로젝트 초기 데이터 삽입 스크립트 (MySQL 호환, 루프 제거)
--

-- ⚠️ 주의: DDL-AUTO가 'update' 또는 'none'일 경우, 중복 삽입 오류 방지를 위해
-- 아래 4줄을 data.sql 맨 위에 추가해 주세요. (앱 시작 로그는 ddl-auto가 create-drop으로 추정됨)
-- SET FOREIGN_KEY_CHECKS = 0;
-- TRUNCATE TABLE review_image;
-- TRUNCATE TABLE review;
-- SET FOREIGN_KEY_CHECKS = 1;


-- 1. TERM 테이블 데이터 (약관)
-- ENUM: ('LOCATION_AGREEMENT','MARKETING_CONSENT','PRIVACY_POLICY','SERVICE_AGREEMENT')
INSERT INTO term (id, name) VALUES (1, 'SERVICE_AGREEMENT');
INSERT INTO term (id, name) VALUES (2, 'PRIVACY_POLICY');
INSERT INTO term (id, name) VALUES (3, 'LOCATION_AGREEMENT');
INSERT INTO term (id, name) VALUES (4, 'MARKETING_CONSENT');


-- 2. FOOD 테이블 데이터 (선호 음식 종류)
-- ENUM: ('ASIAN_FOOD','CHICKEN','CHINESE','DESSERT','FAST_FOOD','JAPANESE','KOREAN','LATE_NIGHT_SNACK','LUNCH_BOX','MEAT_GRILL','SNACK','WESTERN')
INSERT INTO food (id, name) VALUES (10, 'KOREAN');
INSERT INTO food (id, name) VALUES (11, 'CHINESE');
INSERT INTO food (id, name) VALUES (12, 'WESTERN');
INSERT INTO food (id, name) VALUES (13, 'CHICKEN');
INSERT INTO food (id, name) VALUES (14, 'JAPANESE');
INSERT INTO food (id, name) VALUES (15, 'FAST_FOOD');


-- 3. ADDRESS 테이블 데이터 (회원/가게 주소)
INSERT INTO address (id, zonecode, sido, sigungu, roadname, bname, address, detail)
VALUES (500, 12345, '서울', '강남구', '테헤란로', '역삼동', '강남구 역삼동 123-45', '101호');

INSERT INTO address (id, zonecode, sido, sigungu, roadname, bname, address, detail)
VALUES (501, 54321, '경기', '성남시', '분당로', '정자동', '성남시 분남구 11-22', NULL);

INSERT INTO address (id, zonecode, sido, sigungu, roadname, bname, address, detail)
VALUES (502, 33333, '부산', '해운대구', '해변로', '우동', '해운대구 우동 99-88', 'B1');


-- 4. MEMBER 테이블 데이터 (회원 정보)
-- gender ENUM: ('FEMALE','MALE','NONE'), social_type ENUM: ('APPLE','GOOGLE','KAKAO','NAVER')
INSERT INTO member (id, address_id, role, point, name, password, birth, email, phone, gender, social_type, created_at, updated_at, deleted_at)
VALUES (1000, 500, "ROLE_ADMIN",1000, '박수현', "$2a$10$sfy4aMVWHzbDG6CLyNQieu3jTJCcMnPtqG/v9nRJrfAjh/ktghxKO", '2003-10-23', 'test@email.com', '010-1234-5678', 'MALE', 'KAKAO', NOW(), NOW(), NULL);

INSERT INTO member (id, address_id, role, point, name, password, birth, email, phone, gender, social_type, created_at, updated_at, deleted_at)
VALUES (1001, 501, "ROLE_USER",500, '테스트회원2', "$2a$10$sfy4aMVWHzbDG6CLyNQieu3jTJCcMnPtqG/v9nRJrfAjh/ktghxKO", '1998-12-01', 'test2@umc.com', NULL, 'MALE', 'GOOGLE', NOW(), NOW(), NULL);

INSERT INTO member (id, address_id, role, point, name, password, birth, email, phone, gender, social_type, created_at, updated_at, deleted_at)
VALUES (1002, 502, "ROLE_USER",200, '신규회원3', "$2a$10$sfy4aMVWHzbDG6CLyNQieu3jTJCcMnPtqG/v9nRJrfAjh/ktghxKO", '2000-01-01', 'newuser3@umc.com', '010-5555-5555', 'NONE', 'NAVER', NOW(), NOW(), NULL);


-- 5. STORE 테이블 데이터 (가게 정보)
INSERT INTO store (id, address_id, name)
VALUES (1, 500, 'UMC 떡볶이집');

INSERT INTO store (id, address_id, name)
VALUES (2, 501, 'JPA 양식당');

INSERT INTO store (id, address_id, name)
VALUES (3, 502, '부산 일식 전문점');


-- 6. MEMBER_FOOD 테이블 데이터 (회원 선호 음식)
INSERT INTO member_food (id, member_id, food_id) VALUES (1, 1000, 10); -- 회원1: 한식
INSERT INTO member_food (id, member_id, food_id) VALUES (2, 1000, 11); -- 회원1: 중식
INSERT INTO member_food (id, member_id, food_id) VALUES (3, 1001, 12); -- 회원2: 양식
INSERT INTO member_food (id, member_id, food_id) VALUES (4, 1001, 13); -- 회원2: 치킨


-- 7. CATEGORY 테이블 데이터 (가게 카테고리)
-- ENUM: ('CHINESE','JAPANESE','KOREAN','WESTERN')
INSERT INTO category (store_id, type) VALUES (1, 'KOREAN');
INSERT INTO category (store_id, type) VALUES (1, 'CHINESE');
INSERT INTO category (store_id, type) VALUES (2, 'WESTERN');
INSERT INTO category (store_id, type) VALUES (3, 'JAPANESE');


-- 8. MISSION 테이블 데이터 (가게 미션)
INSERT INTO mission (id, store_id, point, deadline, body, created_at, updated_at, deleted_at)
VALUES (10, 1, 50, '2025-12-31 23:59:59', '떡볶이집 방문하고 인증샷 올리기', NOW(), NOW(), NULL);

INSERT INTO mission (id, store_id, point, deadline, body, created_at, updated_at, deleted_at)
VALUES (11, 2, 100, '2025-11-30 18:00:00', '양식당에서 스테이크 먹고 포인트 받기', NOW(), NOW(), NULL);

INSERT INTO mission (id, store_id, point, deadline, body, created_at, updated_at, deleted_at)
VALUES (12, 3, 30, '2025-12-05 23:59:59', '일식 전문점 방문 후 메뉴 추천하기', NOW(), NOW(), NULL);


-- 9. MEMBER_MISSION 테이블 데이터 (회원 미션 상태)
-- state ENUM: ('COMPLETED','PENDING','PROCESSING')
INSERT INTO member_mission (id, member_id, mission_id, state, created_at, updated_at, deleted_at)
VALUES (1, 1000, 10, 'PROCESSING', NOW(), NOW(), NULL);

INSERT INTO member_mission (id, member_id, mission_id, state, created_at, updated_at, deleted_at)
VALUES (2, 1001, 11, 'COMPLETED', NOW(), NOW(), NULL);

INSERT INTO member_mission (id, member_id, mission_id, state, created_at, updated_at, deleted_at)
VALUES (3, 1000, 12, 'PENDING', NOW(), NOW(), NULL);


-- 10. REVIEW 테이블 데이터 (리뷰) - 초기 데이터
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at)
VALUES (1, 1000, 1, 5, '떡볶이가 정말 맛있어요! 인생 떡볶이.', NOW(), NOW(), NULL);

INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at)
VALUES (2, 1001, 2, 4, '분위기 좋은 곳! 파스타가 일품입니다.', NOW(), NOW(), NULL);

INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at)
VALUES (3, 1002, 3, 3, '초밥은 괜찮은데 가격이 좀 비싸네요.', NOW(), NOW(), NULL);


-- 11. REVIEW_IMAGE 테이블 데이터 (리뷰 이미지) - 초기 데이터
INSERT INTO review_image (id, review_id, image_url) VALUES (1, 1, 'https://example.com/image1.jpg');
INSERT INTO review_image (id, review_id, image_url) VALUES (2, 2, 'https://example.com/pasta.jpg');
INSERT INTO review_image (id, review_id, image_url) VALUES (3, 3, 'https://example.com/sushi.jpg');


---

-- member_id: 1000, 1001, 1002 순환 사용 | store_id: 1, 2, 3 순환 사용 | star: 1, 2, 3, 4, 5 순환 사용
-- ID는 4부터 1003까지 생성됨.
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (4, 1000, 1, 1, '대량 데이터 4번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (5, 1001, 2, 2, '대량 데이터 5번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (6, 1002, 3, 3, '대량 데이터 6번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (7, 1000, 1, 4, '대량 데이터 7번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (8, 1001, 2, 5, '대량 데이터 8번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (9, 1002, 3, 1, '대량 데이터 9번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (10, 1000, 1, 2, '대량 데이터 10번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (11, 1001, 2, 3, '대량 데이터 11번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (12, 1002, 3, 4, '대량 데이터 12번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (13, 1000, 1, 5, '대량 데이터 13번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (14, 1001, 2, 1, '대량 데이터 14번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (15, 1002, 3, 2, '대량 데이터 15번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (16, 1000, 1, 3, '대량 데이터 16번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (17, 1001, 2, 4, '대량 데이터 17번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (18, 1002, 3, 5, '대량 데이터 18번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (19, 1000, 1, 1, '대량 데이터 19번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (20, 1001, 2, 2, '대량 데이터 20번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (21, 1002, 3, 3, '대량 데이터 21번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (22, 1000, 1, 4, '대량 데이터 22번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (23, 1001, 2, 5, '대량 데이터 23번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (24, 1002, 3, 1, '대량 데이터 24번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (25, 1000, 1, 2, '대량 데이터 25번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (26, 1001, 2, 3, '대량 데이터 26번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (27, 1002, 3, 4, '대량 데이터 27번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (28, 1000, 1, 5, '대량 데이터 28번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (29, 1001, 2, 1, '대량 데이터 29번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (30, 1002, 3, 2, '대량 데이터 30번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (31, 1000, 1, 3, '대량 데이터 31번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (32, 1001, 2, 4, '대량 데이터 32번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (33, 1002, 3, 5, '대량 데이터 33번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (34, 1000, 1, 1, '대량 데이터 34번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (35, 1001, 2, 2, '대량 데이터 35번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (36, 1002, 3, 3, '대량 데이터 36번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (37, 1000, 1, 4, '대량 데이터 37번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (38, 1001, 2, 5, '대량 데이터 38번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (39, 1002, 3, 1, '대량 데이터 39번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (40, 1000, 1, 2, '대량 데이터 40번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (41, 1001, 2, 3, '대량 데이터 41번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (42, 1002, 3, 4, '대량 데이터 42번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (43, 1000, 1, 5, '대량 데이터 43번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (44, 1001, 2, 1, '대량 데이터 44번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (45, 1002, 3, 2, '대량 데이터 45번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (46, 1000, 1, 3, '대량 데이터 46번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (47, 1001, 2, 4, '대량 데이터 47번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (48, 1002, 3, 5, '대량 데이터 48번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (49, 1000, 1, 1, '대량 데이터 49번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (50, 1001, 2, 2, '대량 데이터 50번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (51, 1002, 3, 3, '대량 데이터 51번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (52, 1000, 1, 4, '대량 데이터 52번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (53, 1001, 2, 5, '대량 데이터 53번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (54, 1002, 3, 1, '대량 데이터 54번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (55, 1000, 1, 2, '대량 데이터 55번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (56, 1001, 2, 3, '대량 데이터 56번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (57, 1002, 3, 4, '대량 데이터 57번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (58, 1000, 1, 5, '대량 데이터 58번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (59, 1001, 2, 1, '대량 데이터 59번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (60, 1002, 3, 2, '대량 데이터 60번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (61, 1000, 1, 3, '대량 데이터 61번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (62, 1001, 2, 4, '대량 데이터 62번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (63, 1002, 3, 5, '대량 데이터 63번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (64, 1000, 1, 1, '대량 데이터 64번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (65, 1001, 2, 2, '대량 데이터 65번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (66, 1002, 3, 3, '대량 데이터 66번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (67, 1000, 1, 4, '대량 데이터 67번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (68, 1001, 2, 5, '대량 데이터 68번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (69, 1002, 3, 1, '대량 데이터 69번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (70, 1000, 1, 2, '대량 데이터 70번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (71, 1001, 2, 3, '대량 데이터 71번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (72, 1002, 3, 4, '대량 데이터 72번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (73, 1000, 1, 5, '대량 데이터 73번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (74, 1001, 2, 1, '대량 데이터 74번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (75, 1002, 3, 2, '대량 데이터 75번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (76, 1000, 1, 3, '대량 데이터 76번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (77, 1001, 2, 4, '대량 데이터 77번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (78, 1002, 3, 5, '대량 데이터 78번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (79, 1000, 1, 1, '대량 데이터 79번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (80, 1001, 2, 2, '대량 데이터 80번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (81, 1002, 3, 3, '대량 데이터 81번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (82, 1000, 1, 4, '대량 데이터 82번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (83, 1001, 2, 5, '대량 데이터 83번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (84, 1002, 3, 1, '대량 데이터 84번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (85, 1000, 1, 2, '대량 데이터 85번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (86, 1001, 2, 3, '대량 데이터 86번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (87, 1002, 3, 4, '대량 데이터 87번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (88, 1000, 1, 5, '대량 데이터 88번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (89, 1001, 2, 1, '대량 데이터 89번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (90, 1002, 3, 2, '대량 데이터 90번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (91, 1000, 1, 3, '대량 데이터 91번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (92, 1001, 2, 4, '대량 데이터 92번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (93, 1002, 3, 5, '대량 데이터 93번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (94, 1000, 1, 1, '대량 데이터 94번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (95, 1001, 2, 2, '대량 데이터 95번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (96, 1002, 3, 3, '대량 데이터 96번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (97, 1000, 1, 4, '대량 데이터 97번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (98, 1001, 2, 5, '대량 데이터 98번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (99, 1002, 3, 1, '대량 데이터 99번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (100, 1000, 1, 2, '대량 데이터 100번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (101, 1001, 2, 3, '대량 데이터 101번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (102, 1002, 3, 4, '대량 데이터 102번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (103, 1000, 1, 5, '대량 데이터 103번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (104, 1001, 2, 1, '대량 데이터 104번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (105, 1002, 3, 2, '대량 데이터 105번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (106, 1000, 1, 3, '대량 데이터 106번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (107, 1001, 2, 4, '대량 데이터 107번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (108, 1002, 3, 5, '대량 데이터 108번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (109, 1000, 1, 1, '대량 데이터 109번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (110, 1001, 2, 2, '대량 데이터 110번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (111, 1002, 3, 3, '대량 데이터 111번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (112, 1000, 1, 4, '대량 데이터 112번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (113, 1001, 2, 5, '대량 데이터 113번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (114, 1002, 3, 1, '대량 데이터 114번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (115, 1000, 1, 2, '대량 데이터 115번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (116, 1001, 2, 3, '대량 데이터 116번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (117, 1002, 3, 4, '대량 데이터 117번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (118, 1000, 1, 5, '대량 데이터 118번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (119, 1001, 2, 1, '대량 데이터 119번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (120, 1002, 3, 2, '대량 데이터 120번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (121, 1000, 1, 3, '대량 데이터 121번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (122, 1001, 2, 4, '대량 데이터 122번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (123, 1002, 3, 5, '대량 데이터 123번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (124, 1000, 1, 1, '대량 데이터 124번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (125, 1001, 2, 2, '대량 데이터 125번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (126, 1002, 3, 3, '대량 데이터 126번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (127, 1000, 1, 4, '대량 데이터 127번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (128, 1001, 2, 5, '대량 데이터 128번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (129, 1002, 3, 1, '대량 데이터 129번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (130, 1000, 1, 2, '대량 데이터 130번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (131, 1001, 2, 3, '대량 데이터 131번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (132, 1002, 3, 4, '대량 데이터 132번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (133, 1000, 1, 5, '대량 데이터 133번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (134, 1001, 2, 1, '대량 데이터 134번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (135, 1002, 3, 2, '대량 데이터 135번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (136, 1000, 1, 3, '대량 데이터 136번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (137, 1001, 2, 4, '대량 데이터 137번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (138, 1002, 3, 5, '대량 데이터 138번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (139, 1000, 1, 1, '대량 데이터 139번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (140, 1001, 2, 2, '대량 데이터 140번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (141, 1002, 3, 3, '대량 데이터 141번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (142, 1000, 1, 4, '대량 데이터 142번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (143, 1001, 2, 5, '대량 데이터 143번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (144, 1002, 3, 1, '대량 데이터 144번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (145, 1000, 1, 2, '대량 데이터 145번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (146, 1001, 2, 3, '대량 데이터 146번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (147, 1002, 3, 4, '대량 데이터 147번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (148, 1000, 1, 5, '대량 데이터 148번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (149, 1001, 2, 1, '대량 데이터 149번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (150, 1002, 3, 2, '대량 데이터 150번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (151, 1000, 1, 3, '대량 데이터 151번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (152, 1001, 2, 4, '대량 데이터 152번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (153, 1002, 3, 5, '대량 데이터 153번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (154, 1000, 1, 1, '대량 데이터 154번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (155, 1001, 2, 2, '대량 데이터 155번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (156, 1002, 3, 3, '대량 데이터 156번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (157, 1000, 1, 4, '대량 데이터 157번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (158, 1001, 2, 5, '대량 데이터 158번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (159, 1002, 3, 1, '대량 데이터 159번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (160, 1000, 1, 2, '대량 데이터 160번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (161, 1001, 2, 3, '대량 데이터 161번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (162, 1002, 3, 4, '대량 데이터 162번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (163, 1000, 1, 5, '대량 데이터 163번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (164, 1001, 2, 1, '대량 데이터 164번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (165, 1002, 3, 2, '대량 데이터 165번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (166, 1000, 1, 3, '대량 데이터 166번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (167, 1001, 2, 4, '대량 데이터 167번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (168, 1002, 3, 5, '대량 데이터 168번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (169, 1000, 1, 1, '대량 데이터 169번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (170, 1001, 2, 2, '대량 데이터 170번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (171, 1002, 3, 3, '대량 데이터 171번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (172, 1000, 1, 4, '대량 데이터 172번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (173, 1001, 2, 5, '대량 데이터 173번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (174, 1002, 3, 1, '대량 데이터 174번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (175, 1000, 1, 2, '대량 데이터 175번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (176, 1001, 2, 3, '대량 데이터 176번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (177, 1002, 3, 4, '대량 데이터 177번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (178, 1000, 1, 5, '대량 데이터 178번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (179, 1001, 2, 1, '대량 데이터 179번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (180, 1002, 3, 2, '대량 데이터 180번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (181, 1000, 1, 3, '대량 데이터 181번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (182, 1001, 2, 4, '대량 데이터 182번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (183, 1002, 3, 5, '대량 데이터 183번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (184, 1000, 1, 1, '대량 데이터 184번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (185, 1001, 2, 2, '대량 데이터 185번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (186, 1002, 3, 3, '대량 데이터 186번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (187, 1000, 1, 4, '대량 데이터 187번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (188, 1001, 2, 5, '대량 데이터 188번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (189, 1002, 3, 1, '대량 데이터 189번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (190, 1000, 1, 2, '대량 데이터 190번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (191, 1001, 2, 3, '대량 데이터 191번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (192, 1002, 3, 4, '대량 데이터 192번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (193, 1000, 1, 5, '대량 데이터 193번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (194, 1001, 2, 1, '대량 데이터 194번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (195, 1002, 3, 2, '대량 데이터 195번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (196, 1000, 1, 3, '대량 데이터 196번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (197, 1001, 2, 4, '대량 데이터 197번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (198, 1002, 3, 5, '대량 데이터 198번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (199, 1000, 1, 1, '대량 데이터 199번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (200, 1001, 2, 2, '대량 데이터 200번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (201, 1002, 3, 3, '대량 데이터 201번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (202, 1000, 1, 4, '대량 데이터 202번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (203, 1001, 2, 5, '대량 데이터 203번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (204, 1002, 3, 1, '대량 데이터 204번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (205, 1000, 1, 2, '대량 데이터 205번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (206, 1001, 2, 3, '대량 데이터 206번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (207, 1002, 3, 4, '대량 데이터 207번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (208, 1000, 1, 5, '대량 데이터 208번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (209, 1001, 2, 1, '대량 데이터 209번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (210, 1002, 3, 2, '대량 데이터 210번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (211, 1000, 1, 3, '대량 데이터 211번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (212, 1001, 2, 4, '대량 데이터 212번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (213, 1002, 3, 5, '대량 데이터 213번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (214, 1000, 1, 1, '대량 데이터 214번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (215, 1001, 2, 2, '대량 데이터 215번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (216, 1002, 3, 3, '대량 데이터 216번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (217, 1000, 1, 4, '대량 데이터 217번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (218, 1001, 2, 5, '대량 데이터 218번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (219, 1002, 3, 1, '대량 데이터 219번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (220, 1000, 1, 2, '대량 데이터 220번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (221, 1001, 2, 3, '대량 데이터 221번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (222, 1002, 3, 4, '대량 데이터 222번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (223, 1000, 1, 5, '대량 데이터 223번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (224, 1001, 2, 1, '대량 데이터 224번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (225, 1002, 3, 2, '대량 데이터 225번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (226, 1000, 1, 3, '대량 데이터 226번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (227, 1001, 2, 4, '대량 데이터 227번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (228, 1002, 3, 5, '대량 데이터 228번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (229, 1000, 1, 1, '대량 데이터 229번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (230, 1001, 2, 2, '대량 데이터 230번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (231, 1002, 3, 3, '대량 데이터 231번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (232, 1000, 1, 4, '대량 데이터 232번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (233, 1001, 2, 5, '대량 데이터 233번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (234, 1002, 3, 1, '대량 데이터 234번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (235, 1000, 1, 2, '대량 데이터 235번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (236, 1001, 2, 3, '대량 데이터 236번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (237, 1002, 3, 4, '대량 데이터 237번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (238, 1000, 1, 5, '대량 데이터 238번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (239, 1001, 2, 1, '대량 데이터 239번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (240, 1002, 3, 2, '대량 데이터 240번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (241, 1000, 1, 3, '대량 데이터 241번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (242, 1001, 2, 4, '대량 데이터 242번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (243, 1002, 3, 5, '대량 데이터 243번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (244, 1000, 1, 1, '대량 데이터 244번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (245, 1001, 2, 2, '대량 데이터 245번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (246, 1002, 3, 3, '대량 데이터 246번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (247, 1000, 1, 4, '대량 데이터 247번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (248, 1001, 2, 5, '대량 데이터 248번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (249, 1002, 3, 1, '대량 데이터 249번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (250, 1000, 1, 2, '대량 데이터 250번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (251, 1001, 2, 3, '대량 데이터 251번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (252, 1002, 3, 4, '대량 데이터 252번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (253, 1000, 1, 5, '대량 데이터 253번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (254, 1001, 2, 1, '대량 데이터 254번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (255, 1002, 3, 2, '대량 데이터 255번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (256, 1000, 1, 3, '대량 데이터 256번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (257, 1001, 2, 4, '대량 데이터 257번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (258, 1002, 3, 5, '대량 데이터 258번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (259, 1000, 1, 1, '대량 데이터 259번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (260, 1001, 2, 2, '대량 데이터 260번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (261, 1002, 3, 3, '대량 데이터 261번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (262, 1000, 1, 4, '대량 데이터 262번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (263, 1001, 2, 5, '대량 데이터 263번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (264, 1002, 3, 1, '대량 데이터 264번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (265, 1000, 1, 2, '대량 데이터 265번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (266, 1001, 2, 3, '대량 데이터 266번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (267, 1002, 3, 4, '대량 데이터 267번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (268, 1000, 1, 5, '대량 데이터 268번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (269, 1001, 2, 1, '대량 데이터 269번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (270, 1002, 3, 2, '대량 데이터 270번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (271, 1000, 1, 3, '대량 데이터 271번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (272, 1001, 2, 4, '대량 데이터 272번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (273, 1002, 3, 5, '대량 데이터 273번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (274, 1000, 1, 1, '대량 데이터 274번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (275, 1001, 2, 2, '대량 데이터 275번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (276, 1002, 3, 3, '대량 데이터 276번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (277, 1000, 1, 4, '대량 데이터 277번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (278, 1001, 2, 5, '대량 데이터 278번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (279, 1002, 3, 1, '대량 데이터 279번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (280, 1000, 1, 2, '대량 데이터 280번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (281, 1001, 2, 3, '대량 데이터 281번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (282, 1002, 3, 4, '대량 데이터 282번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (283, 1000, 1, 5, '대량 데이터 283번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (284, 1001, 2, 1, '대량 데이터 284번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (285, 1002, 3, 2, '대량 데이터 285번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (286, 1000, 1, 3, '대량 데이터 286번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (287, 1001, 2, 4, '대량 데이터 287번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (288, 1002, 3, 5, '대량 데이터 288번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (289, 1000, 1, 1, '대량 데이터 289번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (290, 1001, 2, 2, '대량 데이터 290번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (291, 1002, 3, 3, '대량 데이터 291번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (292, 1000, 1, 4, '대량 데이터 292번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (293, 1001, 2, 5, '대량 데이터 293번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (294, 1002, 3, 1, '대량 데이터 294번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (295, 1000, 1, 2, '대량 데이터 295번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (296, 1001, 2, 3, '대량 데이터 296번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (297, 1002, 3, 4, '대량 데이터 297번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (298, 1000, 1, 5, '대량 데이터 298번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (299, 1001, 2, 1, '대량 데이터 299번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (300, 1002, 3, 2, '대량 데이터 300번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (301, 1000, 1, 3, '대량 데이터 301번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (302, 1001, 2, 4, '대량 데이터 302번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (303, 1002, 3, 5, '대량 데이터 303번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (304, 1000, 1, 1, '대량 데이터 304번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (305, 1001, 2, 2, '대량 데이터 305번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (306, 1002, 3, 3, '대량 데이터 306번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (307, 1000, 1, 4, '대량 데이터 307번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (308, 1001, 2, 5, '대량 데이터 308번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (309, 1002, 3, 1, '대량 데이터 309번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (310, 1000, 1, 2, '대량 데이터 310번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (311, 1001, 2, 3, '대량 데이터 311번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (312, 1002, 3, 4, '대량 데이터 312번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (313, 1000, 1, 5, '대량 데이터 313번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (314, 1001, 2, 1, '대량 데이터 314번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (315, 1002, 3, 2, '대량 데이터 315번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (316, 1000, 1, 3, '대량 데이터 316번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (317, 1001, 2, 4, '대량 데이터 317번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (318, 1002, 3, 5, '대량 데이터 318번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (319, 1000, 1, 1, '대량 데이터 319번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (320, 1001, 2, 2, '대량 데이터 320번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (321, 1002, 3, 3, '대량 데이터 321번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (322, 1000, 1, 4, '대량 데이터 322번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (323, 1001, 2, 5, '대량 데이터 323번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (324, 1002, 3, 1, '대량 데이터 324번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (325, 1000, 1, 2, '대량 데이터 325번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (326, 1001, 2, 3, '대량 데이터 326번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (327, 1002, 3, 4, '대량 데이터 327번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (328, 1000, 1, 5, '대량 데이터 328번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (329, 1001, 2, 1, '대량 데이터 329번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (330, 1002, 3, 2, '대량 데이터 330번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (331, 1000, 1, 3, '대량 데이터 331번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (332, 1001, 2, 4, '대량 데이터 332번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (333, 1002, 3, 5, '대량 데이터 333번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (334, 1000, 1, 1, '대량 데이터 334번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (335, 1001, 2, 2, '대량 데이터 335번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (336, 1002, 3, 3, '대량 데이터 336번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (337, 1000, 1, 4, '대량 데이터 337번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (338, 1001, 2, 5, '대량 데이터 338번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (339, 1002, 3, 1, '대량 데이터 339번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (340, 1000, 1, 2, '대량 데이터 340번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (341, 1001, 2, 3, '대량 데이터 341번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (342, 1002, 3, 4, '대량 데이터 342번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (343, 1000, 1, 5, '대량 데이터 343번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (344, 1001, 2, 1, '대량 데이터 344번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (345, 1002, 3, 2, '대량 데이터 345번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (346, 1000, 1, 3, '대량 데이터 346번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (347, 1001, 2, 4, '대량 데이터 347번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (348, 1002, 3, 5, '대량 데이터 348번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (349, 1000, 1, 1, '대량 데이터 349번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (350, 1001, 2, 2, '대량 데이터 350번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (351, 1002, 3, 3, '대량 데이터 351번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (352, 1000, 1, 4, '대량 데이터 352번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (353, 1001, 2, 5, '대량 데이터 353번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (354, 1002, 3, 1, '대량 데이터 354번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (355, 1000, 1, 2, '대량 데이터 355번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (356, 1001, 2, 3, '대량 데이터 356번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (357, 1002, 3, 4, '대량 데이터 357번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (358, 1000, 1, 5, '대량 데이터 358번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (359, 1001, 2, 1, '대량 데이터 359번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (360, 1002, 3, 2, '대량 데이터 360번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (361, 1000, 1, 3, '대량 데이터 361번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (362, 1001, 2, 4, '대량 데이터 362번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (363, 1002, 3, 5, '대량 데이터 363번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (364, 1000, 1, 1, '대량 데이터 364번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (365, 1001, 2, 2, '대량 데이터 365번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (366, 1002, 3, 3, '대량 데이터 366번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (367, 1000, 1, 4, '대량 데이터 367번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (368, 1001, 2, 5, '대량 데이터 368번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (369, 1002, 3, 1, '대량 데이터 369번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (370, 1000, 1, 2, '대량 데이터 370번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (371, 1001, 2, 3, '대량 데이터 371번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (372, 1002, 3, 4, '대량 데이터 372번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (373, 1000, 1, 5, '대량 데이터 373번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (374, 1001, 2, 1, '대량 데이터 374번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (375, 1002, 3, 2, '대량 데이터 375번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (376, 1000, 1, 3, '대량 데이터 376번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (377, 1001, 2, 4, '대량 데이터 377번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (378, 1002, 3, 5, '대량 데이터 378번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (379, 1000, 1, 1, '대량 데이터 379번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (380, 1001, 2, 2, '대량 데이터 380번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (381, 1002, 3, 3, '대량 데이터 381번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (382, 1000, 1, 4, '대량 데이터 382번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (383, 1001, 2, 5, '대량 데이터 383번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (384, 1002, 3, 1, '대량 데이터 384번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (385, 1000, 1, 2, '대량 데이터 385번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (386, 1001, 2, 3, '대량 데이터 386번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (387, 1002, 3, 4, '대량 데이터 387번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (388, 1000, 1, 5, '대량 데이터 388번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (389, 1001, 2, 1, '대량 데이터 389번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (390, 1002, 3, 2, '대량 데이터 390번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (391, 1000, 1, 3, '대량 데이터 391번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (392, 1001, 2, 4, '대량 데이터 392번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (393, 1002, 3, 5, '대량 데이터 393번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (394, 1000, 1, 1, '대량 데이터 394번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (395, 1001, 2, 2, '대량 데이터 395번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (396, 1002, 3, 3, '대량 데이터 396번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (397, 1000, 1, 4, '대량 데이터 397번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (398, 1001, 2, 5, '대량 데이터 398번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (399, 1002, 3, 1, '대량 데이터 399번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (400, 1000, 1, 2, '대량 데이터 400번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (401, 1001, 2, 3, '대량 데이터 401번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (402, 1002, 3, 4, '대량 데이터 402번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (403, 1000, 1, 5, '대량 데이터 403번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (404, 1001, 2, 1, '대량 데이터 404번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (405, 1002, 3, 2, '대량 데이터 405번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (406, 1000, 1, 3, '대량 데이터 406번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (407, 1001, 2, 4, '대량 데이터 407번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (408, 1002, 3, 5, '대량 데이터 408번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (409, 1000, 1, 1, '대량 데이터 409번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (410, 1001, 2, 2, '대량 데이터 410번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (411, 1002, 3, 3, '대량 데이터 411번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (412, 1000, 1, 4, '대량 데이터 412번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (413, 1001, 2, 5, '대량 데이터 413번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (414, 1002, 3, 1, '대량 데이터 414번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (415, 1000, 1, 2, '대량 데이터 415번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (416, 1001, 2, 3, '대량 데이터 416번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (417, 1002, 3, 4, '대량 데이터 417번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (418, 1000, 1, 5, '대량 데이터 418번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (419, 1001, 2, 1, '대량 데이터 419번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (420, 1002, 3, 2, '대량 데이터 420번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (421, 1000, 1, 3, '대량 데이터 421번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (422, 1001, 2, 4, '대량 데이터 422번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (423, 1002, 3, 5, '대량 데이터 423번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (424, 1000, 1, 1, '대량 데이터 424번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (425, 1001, 2, 2, '대량 데이터 425번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (426, 1002, 3, 3, '대량 데이터 426번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (427, 1000, 1, 4, '대량 데이터 427번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (428, 1001, 2, 5, '대량 데이터 428번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (429, 1002, 3, 1, '대량 데이터 429번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (430, 1000, 1, 2, '대량 데이터 430번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (431, 1001, 2, 3, '대량 데이터 431번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (432, 1002, 3, 4, '대량 데이터 432번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (433, 1000, 1, 5, '대량 데이터 433번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (434, 1001, 2, 1, '대량 데이터 434번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (435, 1002, 3, 2, '대량 데이터 435번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (436, 1000, 1, 3, '대량 데이터 436번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (437, 1001, 2, 4, '대량 데이터 437번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (438, 1002, 3, 5, '대량 데이터 438번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (439, 1000, 1, 1, '대량 데이터 439번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (440, 1001, 2, 2, '대량 데이터 440번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (441, 1002, 3, 3, '대량 데이터 441번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (442, 1000, 1, 4, '대량 데이터 442번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (443, 1001, 2, 5, '대량 데이터 443번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (444, 1002, 3, 1, '대량 데이터 444번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (445, 1000, 1, 2, '대량 데이터 445번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (446, 1001, 2, 3, '대량 데이터 446번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (447, 1002, 3, 4, '대량 데이터 447번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (448, 1000, 1, 5, '대량 데이터 448번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (449, 1001, 2, 1, '대량 데이터 449번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (450, 1002, 3, 2, '대량 데이터 450번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (451, 1000, 1, 3, '대량 데이터 451번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (452, 1001, 2, 4, '대량 데이터 452번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (453, 1002, 3, 5, '대량 데이터 453번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (454, 1000, 1, 1, '대량 데이터 454번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (455, 1001, 2, 2, '대량 데이터 455번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (456, 1002, 3, 3, '대량 데이터 456번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (457, 1000, 1, 4, '대량 데이터 457번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (458, 1001, 2, 5, '대량 데이터 458번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (459, 1002, 3, 1, '대량 데이터 459번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (460, 1000, 1, 2, '대량 데이터 460번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (461, 1001, 2, 3, '대량 데이터 461번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (462, 1002, 3, 4, '대량 데이터 462번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (463, 1000, 1, 5, '대량 데이터 463번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (464, 1001, 2, 1, '대량 데이터 464번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (465, 1002, 3, 2, '대량 데이터 465번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (466, 1000, 1, 3, '대량 데이터 466번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (467, 1001, 2, 4, '대량 데이터 467번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (468, 1002, 3, 5, '대량 데이터 468번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (469, 1000, 1, 1, '대량 데이터 469번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (470, 1001, 2, 2, '대량 데이터 470번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (471, 1002, 3, 3, '대량 데이터 471번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (472, 1000, 1, 4, '대량 데이터 472번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (473, 1001, 2, 5, '대량 데이터 473번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (474, 1002, 3, 1, '대량 데이터 474번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (475, 1000, 1, 2, '대량 데이터 475번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (476, 1001, 2, 3, '대량 데이터 476번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (477, 1002, 3, 4, '대량 데이터 477번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (478, 1000, 1, 5, '대량 데이터 478번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (479, 1001, 2, 1, '대량 데이터 479번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (480, 1002, 3, 2, '대량 데이터 480번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (481, 1000, 1, 3, '대량 데이터 481번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (482, 1001, 2, 4, '대량 데이터 482번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (483, 1002, 3, 5, '대량 데이터 483번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (484, 1000, 1, 1, '대량 데이터 484번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (485, 1001, 2, 2, '대량 데이터 485번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (486, 1002, 3, 3, '대량 데이터 486번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (487, 1000, 1, 4, '대량 데이터 487번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (488, 1001, 2, 5, '대량 데이터 488번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (489, 1002, 3, 1, '대량 데이터 489번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (490, 1000, 1, 2, '대량 데이터 490번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (491, 1001, 2, 3, '대량 데이터 491번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (492, 1002, 3, 4, '대량 데이터 492번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (493, 1000, 1, 5, '대량 데이터 493번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (494, 1001, 2, 1, '대량 데이터 494번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (495, 1002, 3, 2, '대량 데이터 495번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (496, 1000, 1, 3, '대량 데이터 496번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (497, 1001, 2, 4, '대량 데이터 497번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (498, 1002, 3, 5, '대량 데이터 498번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (499, 1000, 1, 1, '대량 데이터 499번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (500, 1001, 2, 2, '대량 데이터 500번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (501, 1002, 3, 3, '대량 데이터 501번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (502, 1000, 1, 4, '대량 데이터 502번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (503, 1001, 2, 5, '대량 데이터 503번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (504, 1002, 3, 1, '대량 데이터 504번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (505, 1000, 1, 2, '대량 데이터 505번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (506, 1001, 2, 3, '대량 데이터 506번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (507, 1002, 3, 4, '대량 데이터 507번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (508, 1000, 1, 5, '대량 데이터 508번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (509, 1001, 2, 1, '대량 데이터 509번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (510, 1002, 3, 2, '대량 데이터 510번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (511, 1000, 1, 3, '대량 데이터 511번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (512, 1001, 2, 4, '대량 데이터 512번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (513, 1002, 3, 5, '대량 데이터 513번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (514, 1000, 1, 1, '대량 데이터 514번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (515, 1001, 2, 2, '대량 데이터 515번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (516, 1002, 3, 3, '대량 데이터 516번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (517, 1000, 1, 4, '대량 데이터 517번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (518, 1001, 2, 5, '대량 데이터 518번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (519, 1002, 3, 1, '대량 데이터 519번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (520, 1000, 1, 2, '대량 데이터 520번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (521, 1001, 2, 3, '대량 데이터 521번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (522, 1002, 3, 4, '대량 데이터 522번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (523, 1000, 1, 5, '대량 데이터 523번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (524, 1001, 2, 1, '대량 데이터 524번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (525, 1002, 3, 2, '대량 데이터 525번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (526, 1000, 1, 3, '대량 데이터 526번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (527, 1001, 2, 4, '대량 데이터 527번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (528, 1002, 3, 5, '대량 데이터 528번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (529, 1000, 1, 1, '대량 데이터 529번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (530, 1001, 2, 2, '대량 데이터 530번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (531, 1002, 3, 3, '대량 데이터 531번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (532, 1000, 1, 4, '대량 데이터 532번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (533, 1001, 2, 5, '대량 데이터 533번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (534, 1002, 3, 1, '대량 데이터 534번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (535, 1000, 1, 2, '대량 데이터 535번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (536, 1001, 2, 3, '대량 데이터 536번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (537, 1002, 3, 4, '대량 데이터 537번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (538, 1000, 1, 5, '대량 데이터 538번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (539, 1001, 2, 1, '대량 데이터 539번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (540, 1002, 3, 2, '대량 데이터 540번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (541, 1000, 1, 3, '대량 데이터 541번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (542, 1001, 2, 4, '대량 데이터 542번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (543, 1002, 3, 5, '대량 데이터 543번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (544, 1000, 1, 1, '대량 데이터 544번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (545, 1001, 2, 2, '대량 데이터 545번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (546, 1002, 3, 3, '대량 데이터 546번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (547, 1000, 1, 4, '대량 데이터 547번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (548, 1001, 2, 5, '대량 데이터 548번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (549, 1002, 3, 1, '대량 데이터 549번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (550, 1000, 1, 2, '대량 데이터 550번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (551, 1001, 2, 3, '대량 데이터 551번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (552, 1002, 3, 4, '대량 데이터 552번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (553, 1000, 1, 5, '대량 데이터 553번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (554, 1001, 2, 1, '대량 데이터 554번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (555, 1002, 3, 2, '대량 데이터 555번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (556, 1000, 1, 3, '대량 데이터 556번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (557, 1001, 2, 4, '대량 데이터 557번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (558, 1002, 3, 5, '대량 데이터 558번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (559, 1000, 1, 1, '대량 데이터 559번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (560, 1001, 2, 2, '대량 데이터 560번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (561, 1002, 3, 3, '대량 데이터 561번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (562, 1000, 1, 4, '대량 데이터 562번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (563, 1001, 2, 5, '대량 데이터 563번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (564, 1002, 3, 1, '대량 데이터 564번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (565, 1000, 1, 2, '대량 데이터 565번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (566, 1001, 2, 3, '대량 데이터 566번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (567, 1002, 3, 4, '대량 데이터 567번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (568, 1000, 1, 5, '대량 데이터 568번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (569, 1001, 2, 1, '대량 데이터 569번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (570, 1002, 3, 2, '대량 데이터 570번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (571, 1000, 1, 3, '대량 데이터 571번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (572, 1001, 2, 4, '대량 데이터 572번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (573, 1002, 3, 5, '대량 데이터 573번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (574, 1000, 1, 1, '대량 데이터 574번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (575, 1001, 2, 2, '대량 데이터 575번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (576, 1002, 3, 3, '대량 데이터 576번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (577, 1000, 1, 4, '대량 데이터 577번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (578, 1001, 2, 5, '대량 데이터 578번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (579, 1002, 3, 1, '대량 데이터 579번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (580, 1000, 1, 2, '대량 데이터 580번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (581, 1001, 2, 3, '대량 데이터 581번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (582, 1002, 3, 4, '대량 데이터 582번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (583, 1000, 1, 5, '대량 데이터 583번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (584, 1001, 2, 1, '대량 데이터 584번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (585, 1002, 3, 2, '대량 데이터 585번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (586, 1000, 1, 3, '대량 데이터 586번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (587, 1001, 2, 4, '대량 데이터 587번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (588, 1002, 3, 5, '대량 데이터 588번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (589, 1000, 1, 1, '대량 데이터 589번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (590, 1001, 2, 2, '대량 데이터 590번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (591, 1002, 3, 3, '대량 데이터 591번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (592, 1000, 1, 4, '대량 데이터 592번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (593, 1001, 2, 5, '대량 데이터 593번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (594, 1002, 3, 1, '대량 데이터 594번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (595, 1000, 1, 2, '대량 데이터 595번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (596, 1001, 2, 3, '대량 데이터 596번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (597, 1002, 3, 4, '대량 데이터 597번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (598, 1000, 1, 5, '대량 데이터 598번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (599, 1001, 2, 1, '대량 데이터 599번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (600, 1002, 3, 2, '대량 데이터 600번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (601, 1000, 1, 3, '대량 데이터 601번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (602, 1001, 2, 4, '대량 데이터 602번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (603, 1002, 3, 5, '대량 데이터 603번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (604, 1000, 1, 1, '대량 데이터 604번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (605, 1001, 2, 2, '대량 데이터 605번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (606, 1002, 3, 3, '대량 데이터 606번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (607, 1000, 1, 4, '대량 데이터 607번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (608, 1001, 2, 5, '대량 데이터 608번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (609, 1002, 3, 1, '대량 데이터 609번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (610, 1000, 1, 2, '대량 데이터 610번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (611, 1001, 2, 3, '대량 데이터 611번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (612, 1002, 3, 4, '대량 데이터 612번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (613, 1000, 1, 5, '대량 데이터 613번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (614, 1001, 2, 1, '대량 데이터 614번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (615, 1002, 3, 2, '대량 데이터 615번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (616, 1000, 1, 3, '대량 데이터 616번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (617, 1001, 2, 4, '대량 데이터 617번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (618, 1002, 3, 5, '대량 데이터 618번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (619, 1000, 1, 1, '대량 데이터 619번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (620, 1001, 2, 2, '대량 데이터 620번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (621, 1002, 3, 3, '대량 데이터 621번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (622, 1000, 1, 4, '대량 데이터 622번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (623, 1001, 2, 5, '대량 데이터 623번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (624, 1002, 3, 1, '대량 데이터 624번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (625, 1000, 1, 2, '대량 데이터 625번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (626, 1001, 2, 3, '대량 데이터 626번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (627, 1002, 3, 4, '대량 데이터 627번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (628, 1000, 1, 5, '대량 데이터 628번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (629, 1001, 2, 1, '대량 데이터 629번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (630, 1002, 3, 2, '대량 데이터 630번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (631, 1000, 1, 3, '대량 데이터 631번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (632, 1001, 2, 4, '대량 데이터 632번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (633, 1002, 3, 5, '대량 데이터 633번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (634, 1000, 1, 1, '대량 데이터 634번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (635, 1001, 2, 2, '대량 데이터 635번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (636, 1002, 3, 3, '대량 데이터 636번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (637, 1000, 1, 4, '대량 데이터 637번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (638, 1001, 2, 5, '대량 데이터 638번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (639, 1002, 3, 1, '대량 데이터 639번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (640, 1000, 1, 2, '대량 데이터 640번 리뷰 입니다. 별점 2점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (641, 1001, 2, 3, '대량 데이터 641번 리뷰 입니다. 별점 3점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (642, 1002, 3, 4, '대량 데이터 642번 리뷰 입니다. 별점 4점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (643, 1000, 1, 5, '대량 데이터 643번 리뷰 입니다. 별점 5점.', NOW(), NOW(), NULL);
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at) VALUES (644, 1001, 2, 1, '대량 데이터 644번 리뷰 입니다. 별점 1점.', NOW(), NOW(), NULL);


-- 12. CONVERSION_HISTORY 테이블 데이터 (포인트 전환 내역)
INSERT INTO conversion_history (id, member_id, point, created_at, updated_at, deleted_at)
VALUES (1, 1000, 10, NOW(), NOW(), NULL);

INSERT INTO conversion_history (id, member_id, point, created_at, updated_at, deleted_at)
VALUES (2, 1001, 50, NOW(), NOW(), NULL);


-- 13. INQUIRY 테이블 데이터 (문의 내역)
-- inquiry_type ENUM: ('COUPON','DELIVERY','MEMBER','ORDER','PAYMENT','REVIEW')
INSERT INTO inquiry (id, member_id, store_id, title, comment, inquiry_type, recived, created_at, updated_at, deleted_at)
VALUES (1, 1001, 2, '주문 결제 오류 문의', '결제가 두 번 됐어요. 확인 부탁드립니다.', 'ORDER', 0, NOW(), NOW(), NULL);

INSERT INTO inquiry (id, member_id, store_id, title, comment, inquiry_type, recived, created_at, updated_at, deleted_at)
VALUES (2, 1000, 1, '리뷰 수정 관련 질문', '제가 작성한 리뷰 수정은 어떻게 하나요?', 'REVIEW', 1, NOW(), NOW(), NULL);