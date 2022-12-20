package com.mediagenix.api.persistence.adapter

import com.mediagenix.api.core.exception.BookNotFoundException
import com.mediagenix.api.core.model.Book
import com.mediagenix.api.core.port.BookPort
import com.mediagenix.api.persistence.mapper.BookEntityMapper
import com.mediagenix.api.persistence.repository.BookRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.util.*

internal class BookAdapterTest {

    private val bookRepository: BookRepository = mockk()
    private val bookEntityMapper: BookEntityMapper = mockk()
    private val bookPort: BookPort = BookAdapter(bookRepository, bookEntityMapper)

    @Test
    fun `getBookById throws exception if book not found`() {
        every { bookRepository.findById(any()) } returns Optional.empty()

        assertThrows(BookNotFoundException::class.java) { bookPort.getBookById(1) }
    }

    @Test
    fun `updateBook throws exception if book not found`() {
        every { bookRepository.existsById(any()) } returns false

        assertThrows(BookNotFoundException::class.java) { bookPort.updateBook(Book(1, "title", "isbn", "author")) }
    }

    @Test
    fun `deleteBookById throws exception if book not found`() {
        every { bookRepository.existsById(any()) } returns false

        assertThrows(BookNotFoundException::class.java) { bookPort.deleteBookById(1) }
    }
}
