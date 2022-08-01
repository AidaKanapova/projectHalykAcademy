CREATE TABLE orders
(
    order_id int8 GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id  int8,
    sum int4,
    status   VARCHAR(255),
    created  date,
    CONSTRAINT pk_order PRIMARY KEY (order_id)
);

CREATE TABLE order_book
(
    order_id int8 NOT NULL,
    book_id  int8 NOT NULL,
    CONSTRAINT pk_order_books PRIMARY KEY (order_id,book_id)
);


ALTER TABLE if exists orders
    ADD CONSTRAINT FK_ORDER_ON_USER
    FOREIGN KEY (user_id)
    REFERENCES users;