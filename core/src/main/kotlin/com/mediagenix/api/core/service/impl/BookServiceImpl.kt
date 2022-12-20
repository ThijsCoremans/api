package com.mediagenix.api.core.service.impl

import com.mediagenix.api.core.model.Book
import com.mediagenix.api.core.port.BookPort
import com.mediagenix.api.core.service.BookService
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(private val bookPort: BookPort): BookService {
    override fun getALlBooks(): MutableList<Book> = bookPort.getALlBooks()
    override fun getBookById(bookId: Long): Book = bookPort.getBookById(bookId)
    override fun createBook(book: Book): Book = bookPort.createBook(book)
    override fun updateBook(book: Book): Book = bookPort.updateBook(book)
    override fun deleteBookById(bookId: Long) = bookPort.deleteBookById(bookId)
}
