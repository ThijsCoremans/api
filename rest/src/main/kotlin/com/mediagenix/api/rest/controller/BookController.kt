package com.mediagenix.api.rest.controller

import com.mediagenix.api.core.model.Book
import com.mediagenix.api.core.service.BookService
import com.mediagenix.api.rest.dto.book.BookDto
import com.mediagenix.api.rest.dto.book.WriteBookDto
import com.mediagenix.api.rest.mapper.BookDtoMapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService,
                     private val bookDtoMapper: BookDtoMapper
) {
    @GetMapping
    fun getALlBooks(): MutableList<BookDto> {
        return bookDtoMapper.mapBooksToBookDtos(bookService.getALlBooks())
    }

    @GetMapping("/{bookId}")
    fun getBookById(@PathVariable bookId: Long): BookDto {
        return bookDtoMapper.mapBookToBookDto(bookService.getBookById(bookId))
    }

    @PostMapping
    fun createBook(@RequestBody writeBookDto: WriteBookDto): BookDto {
        val book: Book = bookDtoMapper.mapWriteBookDtoToBook(writeBookDto)
        return bookDtoMapper.mapBookToBookDto(bookService.createBook(book))
    }

    @PutMapping("/{bookId}")
    fun updateBook(@PathVariable bookId: Long, @RequestBody writeBookDto: WriteBookDto): BookDto {
        val book: Book = bookDtoMapper.mapWriteBookDtoToBook(bookId, writeBookDto)
        return bookDtoMapper.mapBookToBookDto(bookService.updateBook(book))
    }

    @DeleteMapping("/{bookId}")
    fun deleteBookById(@PathVariable bookId: Long) {
        bookService.deleteBookById(bookId)
    }
}
