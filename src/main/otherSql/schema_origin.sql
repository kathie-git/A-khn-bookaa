drop table if exists book;
create table book (
    id bigint unsigned not null auto_increment primary key,
    isbn varchar(20) not null,
    name varchar(40) not null,
    author varchar(40) not null,
    date_created timestamp default 0,
    date_modified timestamp default current_timestamp on update current_timestamp,
    unique index book_idx1 (isbn, name, author, publishDate)
) engine = InnoDB;
