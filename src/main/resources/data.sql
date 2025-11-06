--
-- data.sql - UMC 9th 프로젝트 초기 데이터 삽입 스크립트
--
-- 주의: INSERT 순서는 Foreign Key 제약 조건을 고려하여 참조되는 테이블(부모)을 먼저 삽입합니다.
--

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
INSERT INTO member (id, address_id, point, name, birth, email, phone, gender, social_type, created_at, updated_at, deleted_at)
VALUES (1000, 500, 1000, '테스트회원1', '1995-05-15', 'test1@umc.com', '010-1234-5678', 'FEMALE', 'KAKAO', NOW(), NOW(), NULL);

INSERT INTO member (id, address_id, point, name, birth, email, phone, gender, social_type, created_at, updated_at, deleted_at)
VALUES (1001, 501, 500, '테스트회원2', '1998-12-01', 'test2@umc.com', NULL, 'MALE', 'GOOGLE', NOW(), NOW(), NULL);

INSERT INTO member (id, address_id, point, name, birth, email, phone, gender, social_type, created_at, updated_at, deleted_at)
VALUES (1002, 502, 200, '신규회원3', '2000-01-01', 'newuser3@umc.com', '010-5555-5555', 'NONE', 'NAVER', NOW(), NOW(), NULL);


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


-- 10. REVIEW 테이블 데이터 (리뷰)
INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at)
VALUES (1, 1000, 1, 5, '떡볶이가 정말 맛있어요! 인생 떡볶이.', NOW(), NOW(), NULL);

INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at)
VALUES (2, 1001, 2, 4, '분위기 좋은 곳! 파스타가 일품입니다.', NOW(), NOW(), NULL);

INSERT INTO review (id, member_id, store_id, star, comment, created_at, updated_at, deleted_at)
VALUES (3, 1002, 3, 3, '초밥은 괜찮은데 가격이 좀 비싸네요.', NOW(), NOW(), NULL);


-- 11. REVIEW_IMAGE 테이블 데이터 (리뷰 이미지)
INSERT INTO review_image (id, review_id, image_url) VALUES (1, 1, 'https://example.com/image1.jpg');
INSERT INTO review_image (id, review_id, image_url) VALUES (2, 2, 'https://example.com/pasta.jpg');
INSERT INTO review_image (id, review_id, image_url) VALUES (3, 3, 'https://example.com/sushi.jpg');


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