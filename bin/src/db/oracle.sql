
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



