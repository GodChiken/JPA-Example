INSERT INTO `jpql_test_product` (`id`, `name`, `price`, `stock_amount`) VALUES (1, '컴퓨터', 500, 10);
INSERT INTO `jpql_test_product` (`id`, `name`, `price`, `stock_amount`) VALUES (2, '마우스', 10, 10);
INSERT INTO `jpql_test_product` (`id`, `name`, `price`, `stock_amount`) VALUES (3, '키보드', 30, 10);

INSERT INTO `jpql_test_team` (`id`, `name`) VALUES (1, '개발 1팀');
INSERT INTO `jpql_test_team` (`id`, `name`) VALUES (2, '개발 2팀');
INSERT INTO `jpql_test_team` (`id`, `name`) VALUES (3, '운영팀');
INSERT INTO `jpql_test_team` (`id`, `name`) VALUES (4, '기획디자인팀');

INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (1, 29, '김동훈', 2);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (2, 29, '안정훈', 2);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (3, 27, '권성봉', 2);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (4, 26, '정현일', 2);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (5, 29, '왕선호', 1);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (6, 29, '김민규', 1);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (7, 25, '김성인', 1);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (8, 28, '임보연', 1);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (9, 25, '김소라', 1);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (10, 28, '김현주', 3);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (11, 33, '홍예원', 3);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (12, 33, '설효정', 4);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (13, 40, '박만호', 4);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (14, 30, '김수철', 4);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (15, 31, '이미연', 4);
INSERT INTO `jpql_test_member` (`id`, `age`, `name`, `team_id`) VALUES (16, 25, '최현하', 4);

INSERT INTO `jpql_test_order` (`id`, `city`, `street`, `zipcode`, `order_amount`, `member_id`, `product_id`) VALUES (1, '서울', '가산로', '123123', 30, 1, 1);
INSERT INTO `jpql_test_order` (`id`, `city`, `street`, `zipcode`, `order_amount`, `member_id`, `product_id`) VALUES (2, '경기', '하안로', '123444', 10, 1, 2);

INSERT INTO `jpql_test_item` (`type`, `id`, `name`, `author`) VALUES ('B', 1, '첫번째 아이템', '김동훈');
INSERT INTO `jpql_test_item` (`type`, `id`, `name`, `author`) VALUES ('B', 2, '두번째 아이템', '김동훈2');
INSERT INTO `jpql_test_item` (`type`, `id`, `name`, `author`) VALUES ('A', 3, '세번째 아이템', NULL);
INSERT INTO `jpql_test_item` (`type`, `id`, `name`, `author`) VALUES ('A', 4, '네번째 아이템', '김동훈');
