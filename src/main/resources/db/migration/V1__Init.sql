
create table author (
                        author_id int8 generated by default as identity,
                        date_of_birth date,
                        deleted boolean not null,
                        full_name varchar(255),
                        primary key (author_id)
);

create table book (
                      book_id int8 generated by default as identity,
                      deleted boolean not null,
                      page_count int4 not null,
                      price int4 not null,
                      release_year date,
                      title varchar(255),
                      publisher_id int8,
                      primary key (book_id)
);

create table book_author (
                             book_id int8 not null,
                             author_id int8 not null,
                             primary key (book_id, author_id)
);

create table book_genre (
                            book_id int8 not null,
                            genre_id int8 not null,
                            primary key (book_id, genre_id)
);

create table genre (
                       genre_id int8 generated by default as identity,
                       genre_name varchar(255),
                       primary key (genre_id)
);

create table order_book (
                            order_id int8 not null,
                            book_id int8 not null
);

create table orders (
                        order_id int8 generated by default as identity,
                        created date,
                        status varchar(255),
                        sum int4 not null,
                        user_id int8,
                        primary key (order_id)
);

create table publisher (
                           publisher_id int8 generated by default as identity,
                           deleted boolean,
                           name varchar(255),
                           primary key (publisher_id)
);

create table users (
                       user_id int8 generated by default as identity,
                       blocked boolean not null,
                       deleted boolean,
                       login varchar(255),
                       password varchar(255),
                       role varchar(255),
                       primary key (user_id)
);


alter table if exists users
    add constraint UK_ow0gan20590jrb00upg3va2fn unique (login);


alter table if exists book
    add constraint FKgtvt7p649s4x80y6f4842pnfq
    foreign key (publisher_id)
    references publisher;

alter table if exists book_author
    add constraint FKbjqhp85wjv8vpr0beygh6jsgo
    foreign key (author_id)
    references author;

alter table if exists book_author
    add constraint FKhwgu59n9o80xv75plf9ggj7xn
    foreign key (book_id)
    references book;

alter table if exists book_genre
    add constraint FK8l6ops8exmjrlr89hmfow4mmo
    foreign key (genre_id)
    references genre;

alter table if exists book_genre
    add constraint FK52evq6pdc5ypanf41bij5u218
    foreign key (book_id)
    references book;

alter table if exists order_book
    add constraint FK9yvsui1wgflf4dy9q77rsl280
    foreign key (book_id)
    references book;

alter table if exists order_book
    add constraint FKpci06ofdi2x6lbcan47nlhe2y
    foreign key (order_id)
    references orders;

alter table if exists orders
    add constraint FK32ql8ubntj5uh44ph9659tiih
    foreign key (user_id)
    references users;