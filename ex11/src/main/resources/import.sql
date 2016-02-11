insert into member values(1,"경기도 광명시", "하안로", "12345", "김동훈");
insert into member values(2,"경기도 광명시", "하안로", "12345", "김동훈2");
insert into member values(3,"경기도 광명시", "하안로", "12345", "김동훈3");
insert into member values(4,"경기도 광명시", "하안로", "12345", "김동훈4");
insert into member values(5,"경기도 광명시", "하안로", "12345", "김동훈5");

insert into product(product_type, id, name, price, stock_quantity) values("PD0101", 1,"상품 1", 5000, 10);
insert into product(product_type, id, name, price, stock_quantity) values("PD0102", 2,"상품 2", 15000, 120);
insert into product(product_type, id, name, price, stock_quantity) values("PD0103", 3,"상품 3", 35000, 1);
insert into product(product_type, id, name, price, stock_quantity) values("PD0101", 4,"상품 4", 565000, 5);
insert into product(product_type, id, name, price, stock_quantity) values("PD0101", 5,"상품 5", 25000, 100);

insert into delivery values(1, "경기도 광명시", "하안로", "12345", "COMP");
insert into delivery values(2, "경기도 광명시", "하안로", "12345", "READY");

insert into request values(1, now(), 'ORDER', 1, 1);
insert into request values(2, now(), 'CANCEL', 2, 2);

insert into requested_product values(1, 1, 5000, 1, 1);
insert into requested_product values(2, 2, 30000, 2, 1);
insert into requested_product values(3, 10, 250000, 5, 2);
