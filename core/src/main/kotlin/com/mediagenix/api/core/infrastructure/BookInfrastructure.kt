package com.mediagenix.api.core.infrastructure

import com.mediagenix.api.core.model.Book
import java.util.Optional

interface BookInfrastructure {
    fun getALlBooks(): MutableList<Book>
    fun getBookById(bookId: Long): Book
    fun createBook(book: Book): Book
    fun updateBook(book: Book): Book
    fun deleteBookById(bookId: Long)
}
