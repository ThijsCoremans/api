insert into collection(collection_id, name, created_at, updated_at)
values(50, 'Stephen King', '2022-03-12 00:00:00.000', '2022-03-12 00:00:00.000');

insert into collection(collection_id, name, created_at, updated_at)
values(51, 'Science', '2022-03-12 00:00:00.000', '2022-03-12 00:00:00.000');

insert into book(book_id, isbn, author, title, created_at, updated_at)
values(50, '978-1982155759', 'Stephen King', 'Rita Hayworth and Shawshank Redemption', '2022-03-12 00:00:00.000', '2022-03-12 00:00:00.000');

insert into book(book_id, isbn, author, title, created_at, updated_at)
values(51, '9789463930611', 'Lieven Scheire', 'Fysica', '2022-03-12 00:00:00.000', '2022-03-12 00:00:00.000');

insert into book(book_id, isbn, author, title, created_at, updated_at)
values(52, '978-1984819192', 'Stephen Hawking', 'Brief Answers to the Big Questions', '2022-03-12 00:00:00.000', '2022-03-12 00:00:00.000');

insert into book(book_id, isbn, author, title, created_at, updated_at)
values(53, '9780393609097', 'Neil Gaiman', 'Norse Mythology', '2022-03-12 00:00:00.000', '2022-03-12 00:00:00.000');

insert into collection_books(collection_id, book_id)
values(50, 50);

insert into collection_books(collection_id, book_id)
values(51, 51);

insert into collection_books(collection_id, book_id)
values(51, 52);
