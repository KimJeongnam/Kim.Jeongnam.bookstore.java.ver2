SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS CARTS;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS PERMISSIONS;




/* Create Tables */

CREATE TABLE BOOKS
(
	book_id int(4) NOT NULL,
	book_name varchar(30) NOT NULL,
	author varchar(20) NOT NULL,
	stock int(4) NOT NULL,
	PRIMARY KEY (book_id)
);


CREATE TABLE CARTS
(
	user_id varchar(20) NOT NULL,
	book_id int(4) NOT NULL,
	wish_stock int,
	PRIMARY KEY (user_id, book_id)
);


CREATE TABLE orders
(
	order_code int(4) NOT NULL,
	book_id int(4) NOT NULL,
	user_id varchar(20) NOT NULL,
	order_stock int NOT NULL,
	payment_status boolean NOT NULL,
	refund_status boolean NOT NULL,
	PRIMARY KEY (order_code)
);


CREATE TABLE PERMISSIONS
(
	permission varchar(20) NOT NULL,
	PRIMARY KEY (permission)
);


CREATE TABLE USERS
(
	user_id varchar(20) NOT NULL,
	user_pw varchar(255) NOT NULL,
	permission varchar(20) NOT NULL,
	PRIMARY KEY (user_id)
);



/* Create Foreign Keys */

ALTER TABLE CARTS
	ADD FOREIGN KEY (book_id)
	REFERENCES BOOKS (book_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE orders
	ADD FOREIGN KEY (book_id)
	REFERENCES BOOKS (book_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USERS
	ADD FOREIGN KEY (permission)
	REFERENCES PERMISSIONS (permission)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CARTS
	ADD FOREIGN KEY (user_id)
	REFERENCES USERS (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE orders
	ADD FOREIGN KEY (user_id)
	REFERENCES USERS (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



