package com.mediagenix.api.persistence.infrastructure

import com.mediagenix.api.core.exception.BookNotFoundException
import com.mediagenix.api.core.infrastructure.BookInfrastructure
import com.mediagenix.api.core.model.Book
import com.mediagenix.api.persistence.mapper.BookEntityMapper
import com.mediagenix.api.persistence.repository.BookRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class BookInfrastructureImpl(private val bookRepository: BookRepository,
                             private val bookEntityMapper: BookEntityMapper): BookInfrastructure {
    override fun getALlBooks(): MutableList<Book> {
        return bookEntityMapper.mapBookEntitiesToBooks(bookRepository.findAll(Sort.by("title")))
    }

    override fun getBookById(bookId: Long): Book {
        return bookRepository.findById(bookId)
            .map { bookEntity -> bookEntityMapper.mapBookEntityToBook(bookEntity) }
            .orElseThrow { BookNotFoundException() }
    }

    override fun createBook(book: Book): Book {
        val booKEntity: com.mediagenix.api.persistence.entity.Book = bookEntityMapper.mapBookToBookEntity(book)
        return bookEntityMapper.mapBookEntityToBook(bookRepository.save(booKEntity))
    }

    override fun updateBook(book: Book): Book {
        if (!bookRepository.existsById(book.id)) {
            throw BookNotFoundException()
        }
        val booKEntity: com.mediagenix.api.persistence.entity.Book = bookEntityMapper.mapBookToBookEntity(book)
        return bookEntityMapper.mapBookEntityToBook(bookRepository.save(booKEntity))
    }

    override fun deleteBookById(bookId: Long) {
        if (!bookRepository.existsById(bookId)) {
            throw BookNotFoundException()
        }
        bookRepository.deleteById(bookId)
    }
}
