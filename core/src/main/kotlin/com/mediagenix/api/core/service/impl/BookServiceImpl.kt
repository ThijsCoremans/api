package com.mediagenix.api.core.service.impl

import com.mediagenix.api.core.infrastructure.BookInfrastructure
import com.mediagenix.api.core.model.Book
import com.mediagenix.api.core.service.BookService
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(private val bookInfrastructure: BookInfrastructure): BookService {
    override fun getALlBooks(): MutableList<Book> = bookInfrastructure.getALlBooks()
    override fun getBookById(bookId: Long): Book = bookInfrastructure.getBookById(bookId)
    override fun createBook(book: Book): Book = bookInfrastructure.createBook(book)
    override fun updateBook(book: Book): Book = bookInfrastructure.updateBook(book)
    override fun deleteBookById(bookId: Long) = bookInfrastructure.deleteBookById(bookId)
}
