
/* Drop Tables */

DROP TABLE carts CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE books CASCADE CONSTRAINTS;
DROP TABLE users CASCADE CONSTRAINTS;
DROP TABLE permissions CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE books
(
	book_code varchar2(5) NOT NULL,
	book_name varchar2(20) NOT NULL,
	author varchar2(20) NOT NULL,
	price number(10) NOT NULL,
    stock number(4) NOT NULL,
	PRIMARY KEY (book_code)
);


CREATE TABLE carts
(
	user_id varchar2(20) NOT NULL,
	book_code varchar2(5) NOT NULL,
	wish_stock number(4) NOT NULL,
	PRIMARY KEY (user_id, book_code)
);


CREATE TABLE orders
(
	user_id varchar2(20) NOT NULL,
	book_code varchar2(5) NOT NULL,
	order_stock number(4) NOT NULL,
	payment_status number(1),
	refund_ask number(1),
	PRIMARY KEY (user_id, book_code)
);


CREATE TABLE permissions
(
	permission varchar2(20) NOT NULL,
	PRIMARY KEY (permission)
);


CREATE TABLE users
(
	user_id varchar2(20) NOT NULL,
	user_pw varchar2(20) NOT NULL,
	permission varchar2(20) NOT NULL,
	PRIMARY KEY (user_id)
);



/* Create Foreign Keys */

ALTER TABLE carts
	ADD FOREIGN KEY (book_code)
	REFERENCES books (book_code)
;


ALTER TABLE orders
	ADD FOREIGN KEY (book_code)
	REFERENCES books (book_code)
;


ALTER TABLE users
	ADD FOREIGN KEY (permission)
	REFERENCES permissions (permission)
;


ALTER TABLE carts
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
;


ALTER TABLE orders
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
;

/* add constraint */
ALTER TABLE books
    ADD CONSTRAINT orders_price_CK
    CHECK(price > 0)
    ADD CONSTRAINT orders_stock_CK
    CHECK(stock > 0);

ALTER TABLE orders
	ADD CONSTRAINT orders_payment_status_CK
	CHECK(payment_status IN (0, 1))
	ADD CONSTRAINT orders_refund_ask_CK
	CHECK(refund_ask IN (0, 1))
	ADD CONSTRAINT orders_orderstock_CK
    CHECK(order_stock > 0);


INSERT INTO permissions VALUES('host');
INSERT INTO permissions VALUES('guest');

DESC users;
INSERT INTO users VALUES('host', '1234', 'host');
INSERT INTO users VALUES('user', '1234', 'guest');

DESC books;
INSERT INTO books VALUES('8c4f9', 'JAVA', '고슬링', 35000, 500);

DESC orders;
INSERT INTO orders VALUES('user', '8c4f9', 50, 0, 0);

commit;

INSERT INTO carts VALUES('user', '8c4f9', 30);


SELECT count(*) 
    FROM carts 
    WHERE user_id = 'user' 
    AND book_code='8c4f9';
    
/*
    ON 조건을 만족하면 WHEN MATCHED THEN 실행
    만족하지 못하면 WHEN NOT MATCHED THEN 실행
    
    장바구니에 해당 user_id와 book_code가 이미 있다면 wish_stock에 수량을 더하며
    없다면 새로 추가하는 query
*/
MERGE INTO carts c
USING ( SELECT 'user' user_id, '8c4f9' book_code   -- USING절에 뷰가 올수 있다.
        FROM dual) s
ON ( c.user_id = s.user_id 
    AND c.book_code = s.book_code)
WHEN MATCHED THEN
  UPDATE SET c.wish_stock = c.wish_stock+10
WHEN NOT MATCHED THEN
INSERT (c.user_id, c.book_code, c.wish_stock)
VALUES (s.user_id, s.book_code, 10);            
-- INSERT 절의 조건절도 지정이 가능하다


MERGE INTO carts c
USING ( SELECT 'user' user_id, '8c4f9' book_code, 10 wish_stock   -- USING절에 뷰가 올수 있다.
        FROM dual) s
ON ( c.user_id = s.user_id 
    AND c.book_code = s.book_code)
WHEN MATCHED THEN
  UPDATE SET c.wish_stock = c.wish_stock+s.wish_stock
  WHERE s.wish_stock < (SELECT stock
                        FROM books
                        WHERE book_code = s.book_code)
WHEN NOT MATCHED THEN
    INSERT (c.user_id, c.book_code, c.wish_stock)
    VALUES (s.user_id, s.book_code, s.wish_stock)
    WHERE s.wish_stock < (SELECT stock
                            FROM books
                            WHERE book_code = s.book_code);
                        
COMMIT;