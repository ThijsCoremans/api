package com.mediagenix.api.rest.mapper.impl

import com.mediagenix.api.core.model.Book
import com.mediagenix.api.rest.dto.book.BookDto
import com.mediagenix.api.rest.dto.book.CreateBookDto
import com.mediagenix.api.rest.mapper.BookDtoMapper
import org.springframework.stereotype.Component

@Component
class BookDtoMapperImpl: BookDtoMapper {

    override fun mapBooksToBookDtos(books: MutableList<Book>): MutableList<BookDto> {
        return books.map { book -> mapBookToBookDto(book) } as MutableList<BookDto>
    }

    override fun mapBookDtosToBooks(books: MutableList<BookDto>): MutableList<Book> {
        return books.map { bookDto -> mapBookDtoToBook(bookDto) } as MutableList<Book>
    }

    override fun mapBookToBookDto(book: Book): BookDto {
        return BookDto(book.id, book.title, book.ISBN, book.author)
    }

    override fun mapBookDtoToBook(bookDto: BookDto): Book {
        return Book(bookDto.id, bookDto.title, bookDto.ISBN, bookDto.author)
    }

    override fun mapCreateBookDtoToBook(createBookDto: CreateBookDto): Book {
        return Book(null, createBookDto.title, createBookDto.ISBN, createBookDto.author)
    }
}
