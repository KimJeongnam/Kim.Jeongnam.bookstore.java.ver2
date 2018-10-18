
/* Drop Tables */

DROP TABLE carts CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE books CASCADE CONSTRAINTS;
DROP TABLE users CASCADE CONSTRAINTS;
DROP TABLE permissions CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE books
(
	book_code number(4) NOT NULL,
	book_name varchar2(20) NOT NULL,
	author varchar2(20) NOT NULL,
	price number(10) NOT NULL,
    stock number(4) NOT NULL,
	PRIMARY KEY (book_code)
);


CREATE TABLE carts
(
	user_id varchar2(20) NOT NULL,
	book_code number(4) NOT NULL,
	wish_stock number(4) NOT NULL,
	PRIMARY KEY (user_id, book_code)
);


CREATE TABLE orders
(
	order_code number(4) NOT NULL,
	book_code number(4) NOT NULL,
	user_id varchar2(20) NOT NULL,
	order_stock number(4) NOT NULL,
	payment_status number(1),
	refundask number(1),
	PRIMARY KEY (order_code)
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
    ADD CONSTRAINT negativenumber_price_CK
    CHECK(price > 0);
    ADD CONSTRAINT negativenumber_stock_CK
    CHECK(stock > 0);
ALTER TABLE orders
	ADD CONSTRAINT boolean_payment_status_CK
	CHECK(payment_status IN (0, 1))
	ADD CONSTRAINT boolean_refundask_CK
	CHECK(payment_status IN (0, 1))
	ADD CONSTRAINT negativenumber_orderstock_CK
    CHECK(order_stock > 0);
;

INSERT INTO permissions VALUES('host');
INSERT INTO permissions VALUES('guest');

DESC users;
INSERT INTO users VALUES('host', '1234', 'host');
INSERT INTO users VALUES('user', '1234', 'guest');

DESC books;
INSERT INTO books VALUES(8845, 'JAVA', '고슬링', 35000, 500);

DESC orders;
INSERT INTO orders VALUES(1001, 8845, 'user', 50, 0, 0);

commit;

