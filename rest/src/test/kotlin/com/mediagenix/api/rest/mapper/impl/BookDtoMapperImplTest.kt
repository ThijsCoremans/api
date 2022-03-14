package com.mediagenix.api.rest.mapper.impl

import com.mediagenix.api.core.model.Book
import com.mediagenix.api.rest.dto.book.BookDto
import com.mediagenix.api.rest.dto.book.WriteBookDto
import com.mediagenix.api.rest.mapper.BookDtoMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BookDtoMapperImplTest {

    private val bookDtoMapper: BookDtoMapper = BookDtoMapperImpl()

    @Test
    fun `mapBooksToBookDtos should map single book with correct values`() {
        val bookDtos: MutableList<BookDto> = arrayListOf(createBookDto())
        val books: MutableList<Book> = arrayListOf(createBook(1))

        assertEquals(bookDtos, bookDtoMapper.mapBooksToBookDtos(books))
    }

    @Test
    fun `mapBooksToBookDtos should map multiple books with correct values`() {
        val bookDtos: MutableList<BookDto> = arrayListOf(createBookDto())
        bookDtos.add(BookDto(2, "second book", "isbn2", "author"))
        val books: MutableList<Book> = arrayListOf(createBook(1))
        books.add(Book(2, "second book", "isbn2", "author"))

        assertEquals(bookDtos, bookDtoMapper.mapBooksToBookDtos(books))
    }

    @Test
    fun `mapBookDtosToBooks should map single book with correct values`() {
        val books: MutableList<Book> = arrayListOf(createBook(1))
        val bookDtos: MutableList<BookDto> = arrayListOf(createBookDto())

        assertEquals(books, bookDtoMapper.mapBookDtosToBooks(bookDtos))
    }

    @Test
    fun `mapBookToBookDto should map correct values`() {
        assertEquals(createBookDto(), bookDtoMapper.mapBookToBookDto(createBook(1)))
    }

    @Test
    fun `mapBookDtoToBook should map correct values`() {
        assertEquals(createBook(1), bookDtoMapper.mapBookDtoToBook(createBookDto()))
    }

    @Test
    fun `mapWriteBookDtoToBook should map correct values`() {
        assertEquals(createBook(null), bookDtoMapper.mapWriteBookDtoToBook(createWriteBookDto()))
    }

    @Test
    fun `mapWriteBookDtoToBook should map id and correct values`() {
        assertEquals(createBook(1), bookDtoMapper.mapWriteBookDtoToBook(1, createWriteBookDto()))
    }

    private fun createBook(id: Long?): Book {
        return Book(id, "book", "isbn", "author")
    }

    private fun createBookDto(): BookDto {
        return BookDto(1, "book", "isbn", "author")
    }

    private fun createWriteBookDto(): WriteBookDto {
        return WriteBookDto("book", "isbn", "author")
    }
}
