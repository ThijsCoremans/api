create sequence if not exists collection_gen start 1 increment 1;
create sequence if not exists book_gen start 1 increment 1;

create table if not exists collection
(
    collection_id bigint not null,
    created_at timestamp,
    updated_at timestamp,
    name       varchar(255),
    primary key (collection_id)
);

create table if not exists book
(
    book_id bigint not null,
    created_at    timestamp,
    updated_at    timestamp,
    isbn          varchar(255) constraint isbn_unique unique,
    author        varchar(255),
    title         varchar(255),
    primary key (book_id)
);

create table collection_books
(
    collection_id bigint not null constraint collection_collection_id_fk references collection,
    book_id      bigint not null constraint book_book_id_fk references book
);
