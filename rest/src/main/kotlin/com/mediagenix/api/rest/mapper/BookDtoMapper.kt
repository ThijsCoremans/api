package com.mediagenix.api.rest.mapper

import com.mediagenix.api.core.model.Book
import com.mediagenix.api.rest.dto.book.BookDto
import com.mediagenix.api.rest.dto.book.WriteBookDto

interface BookDtoMapper {
    fun mapBooksToBookDtos(books: MutableList<Book>): MutableList<BookDto>
    fun mapBookDtosToBooks(books: MutableList<BookDto>): MutableList<Book>
    fun mapBookToBookDto(book: Book): BookDto
    fun mapBookDtoToBook(bookDto: BookDto): Book
    fun mapWriteBookDtoToBook(writeBookDto: WriteBookDto): Book
    fun mapWriteBookDtoToBook(bookId: Long, writeBookDto: WriteBookDto): Book
}
