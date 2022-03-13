package com.mediagenix.api.persistence.mapper

import com.mediagenix.api.persistence.entity.Book

interface BookEntityMapper {
    fun mapBookEntitiesToBooks(bookEntities: MutableList<Book>): MutableList<com.mediagenix.api.core.model.Book>
    fun mapBooksToBookEntities(books: MutableList<com.mediagenix.api.core.model.Book>): MutableList<Book>
    fun mapBookEntityToBook(bookEntity: Book): com.mediagenix.api.core.model.Book
    fun mapBookToBookEntity(book: com.mediagenix.api.core.model.Book): Book
}
