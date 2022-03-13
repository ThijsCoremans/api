package com.mediagenix.api.core.service

import com.mediagenix.api.core.model.Book

interface BookService {
    fun getALlBooks(): MutableList<Book>
    fun getBookById(bookId: Long): Book
    fun createBook(book: Book): Book
    fun updateBook(book: Book): Book
    fun deleteBookById(bookId: Long)
}
