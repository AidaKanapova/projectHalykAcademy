/*alter table if exists book
    add column deleted boolean;


alter table if exists order_book
    add constraint FK9yvsui1wgflf4dy9q77rsl280
    foreign key (book_id)
    references book;


alter table if exists order_book
    add constraint FKpci06ofdi2x6lbcan47nlhe2y
    foreign key (order_id)
    references orders*/