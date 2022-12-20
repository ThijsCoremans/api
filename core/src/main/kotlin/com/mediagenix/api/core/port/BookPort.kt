package com.mediagenix.api.core.port

import com.mediagenix.api.core.model.Book

interface BookPort {
    fun getALlBooks(): MutableList<Book>
    fun getBookById(bookId: Long): Book
    fun createBook(book: Book): Book
    fun updateBook(book: Book): Book
    fun deleteBookById(bookId: Long)
}
