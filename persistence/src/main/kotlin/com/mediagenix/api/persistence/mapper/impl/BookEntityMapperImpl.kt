package com.mediagenix.api.persistence.mapper.impl

import com.mediagenix.api.persistence.entity.Book
import com.mediagenix.api.persistence.mapper.BookEntityMapper
import org.springframework.stereotype.Component

@Component
class BookEntityMapperImpl: BookEntityMapper {

    override fun mapBookEntitiesToBooks(bookEntities: MutableList<Book>): MutableList<com.mediagenix.api.core.model.Book> {
        return bookEntities.map { bookEntity -> mapBookEntityToBook(bookEntity) } as MutableList<com.mediagenix.api.core.model.Book>
    }

    override fun mapBooksToBookEntities(books: MutableList<com.mediagenix.api.core.model.Book>): MutableList<Book> {
        return books.stream()
            .map { book -> mapBookToBookEntity(book) }
            .toList()
    }

    override fun mapBookEntityToBook(bookEntity: Book): com.mediagenix.api.core.model.Book {
        return com.mediagenix.api.core.model.Book(bookEntity.id, bookEntity.title, bookEntity.isbn, bookEntity.author)
    }

    override fun mapBookToBookEntity(book: com.mediagenix.api.core.model.Book): Book {
        return Book(book.id, book.title, book.isbn, book.author)
    }
}
