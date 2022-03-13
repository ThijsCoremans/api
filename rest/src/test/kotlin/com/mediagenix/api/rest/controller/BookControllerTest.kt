package com.mediagenix.api.rest.controller

import com.mediagenix.api.core.service.BookService
import com.mediagenix.api.rest.dto.book.BookDto
import com.mediagenix.api.rest.dto.book.CreateBookDto
import com.mediagenix.api.rest.mapper.BookDtoMapper
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BookControllerTest {

    private val bookService: BookService = mockk(relaxed = true)
    private val bookDtoMapper: BookDtoMapper = mockk(relaxed = true)
    private val bookController = BookController(bookService, bookDtoMapper)

    @Test
    fun `getALlBooks should call service and mapper`() {
        bookController.getALlBooks()

        verify(exactly = 1) { bookService.getALlBooks() }
        verify(exactly = 1) { bookDtoMapper.mapBooksToBookDtos(any()) }
    }

    @Test
    fun `getBookById should call service and mapper`() {
        bookController.getBookById(1)

        verify(exactly = 1) { bookService.getBookById(1) }
        verify(exactly = 1) { bookDtoMapper.mapBookToBookDto(any()) }
    }

    @Test
    fun `createBook should call service and mapper`() {
        bookController.createBook(CreateBookDto("title", "isbn", "author"))

        verify(exactly = 1) { bookDtoMapper.mapCreateBookDtoToBook(any()) }
        verify(exactly = 1) { bookService.createBook(any()) }
        verify(exactly = 1) { bookDtoMapper.mapBookToBookDto(any()) }
    }

    @Test
    fun `updateBook should call service and mapper`() {
        bookController.updateBook(BookDto(1, "title", "isbn", "author"))

        verify(exactly = 1) { bookDtoMapper.mapBookDtoToBook(any()) }
        verify(exactly = 1) { bookService.updateBook(any()) }
        verify(exactly = 1) { bookDtoMapper.mapBookToBookDto(any()) }
    }

    @Test
    fun `deleteBook should call service and mapper`() {
        bookController.deleteBookById(1)

        verify(exactly = 1) { bookService.deleteBookById(1) }
    }
}
