
/* Drop Tables */

DROP TABLE carts CASCADE CONSTRAINTS;
DROP TABLE order_info CASCADE CONSTRAINTS;
DROP TABLE books CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE users CASCADE CONSTRAINTS;
DROP TABLE permissions CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE books
(
	book_code varchar2(50) NOT NULL,
	book_name varchar2(20) NOT NULL,
	author varchar2(50) NOT NULL,
	price number,
	stock number NOT NULL,
	delete_status number(1) NOT NULL,
	PRIMARY KEY (book_code)
);


CREATE TABLE carts
(
	user_id varchar2(50) NOT NULL,
	book_code varchar2(50) NOT NULL,
	wish_stock number NOT NULL,
	PRIMARY KEY (user_id, book_code)
);


CREATE TABLE orders
(
	order_code varchar2(50) NOT NULL,
	user_id varchar2(50) NOT NULL,
	payment_status number(1),
	refund_ask number(1),
	PRIMARY KEY (order_code)
);


CREATE TABLE order_info
(
	order_code varchar2(50) NOT NULL,
	book_code varchar2(50) NOT NULL,
	order_stock number NOT NULL,
	PRIMARY KEY (order_code, book_code)
);


CREATE TABLE permissions
(
	permission varchar2(20) NOT NULL,
	PRIMARY KEY (permission)
);


CREATE TABLE users
(
	user_id varchar2(50) NOT NULL,
	user_pw varchar2(50) NOT NULL,
	permission varchar2(20) NOT NULL,
	PRIMARY KEY (user_id)
);



/* Create Foreign Keys */

ALTER TABLE carts
	ADD FOREIGN KEY (book_code)
	REFERENCES books (book_code)
;


ALTER TABLE order_info
	ADD FOREIGN KEY (book_code)
	REFERENCES books (book_code)
;


ALTER TABLE order_info
	ADD FOREIGN KEY (order_code)
	REFERENCES orders (order_code)
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
    ADD CONSTRAINT books_price_ck
    CHECK(price > 0)
    ADD CONSTRAINT books_stock_ck
    CHECK(stock >= 0)
    ADD CONSTRAINT books_delete_status_ck
    CHECK(delete_status IN(-1, 0, 1));

ALTER TABLE orders
	ADD CONSTRAINT orders_payment_status_ck
	CHECK(payment_status IN (-1, 0, 1))
	ADD CONSTRAINT orders_refund_ask_ck
	CHECK(refund_ask IN (-1, 0, 1));
	
ALTER TABLE order_info
	ADD CONSTRAINT orders_orderstock_ck
    CHECK(order_stock > 0);


INSERT INTO permissions VALUES('host');
INSERT INTO permissions VALUES('guest');

DESC users;
INSERT INTO users VALUES('host', '1234', 'host');
INSERT INTO users VALUES('user', '1234', 'guest');

DESC books;
INSERT INTO books VALUES('8c4f9', INITCAP('JAVA'), '고슬링', 35000, 500, 0);



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
USING ( SELECT 'user' user_id, '8c4f9' book_code, 10 wish_stock   -- USING절에 뷰가 올수 있다.
        FROM dual) s
ON ( c.user_id = s.user_id 
    AND c.book_code = s.book_code)
WHEN MATCHED THEN
  UPDATE SET c.wish_stock = c.wish_stock + s.wish_stock
  WHERE c.wish_stock+s.wish_stock <= (SELECT stock FROM books WHERE book_code = s.book_code)
WHEN NOT MATCHED THEN
    INSERT (c.user_id, c.book_code, c.wish_stock)
    VALUES (s.user_id, s.book_code, s.wish_stock)
    WHERE s.wish_stock <= (SELECT stock FROM books WHERE book_code = s.book_code);

select * from carts;
-- INSERT 절의 조건절도 지정이 가능하다
                        
                        
SELECT count(c.book_code) "count", sum(b.stock*b.price) "total_price"
    FROM carts c JOIN books b
    ON c.book_code = b.book_code
    WHERE user_id = 'user';
    
    
SELECT count(c.book_code) "count", TO_CHAR(sum(c.wish_stock*b.price), 'L999,999,999,999') "total_price" 
				FROM carts c JOIN books
				ON c.book_code = b.book_code
				WHERE user_id = 'user';
				
				
UPDATE carts SET wish_stock=10
	WHERE user_id='user' 
	AND book_code='';

COMMIT;