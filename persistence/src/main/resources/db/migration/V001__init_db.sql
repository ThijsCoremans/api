create sequence if not exists collection_gen start 1 increment 1;
create sequence if not exists book_gen start 1 increment 1;

create table if not exists collection
(
    id bigint not null,
    created_at timestamp,
    updated_at timestamp,
    name       varchar(255),
    primary key (id)
);

create table if not exists book
(
    id bigint not null,
    created_at    timestamp,
    updated_at    timestamp,
    isbn          varchar(255) constraint isbn_unique unique,
    author        varchar(255),
    title         varchar(255),
    collection_id bigint constraint collection_id_book_fk references collection,
    primary key (id)
);
