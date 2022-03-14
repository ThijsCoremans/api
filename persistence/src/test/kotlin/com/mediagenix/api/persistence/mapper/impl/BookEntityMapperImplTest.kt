package com.mediagenix.api.persistence.mapper.impl

import com.mediagenix.api.persistence.entity.Book
import com.mediagenix.api.persistence.mapper.BookEntityMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BookEntityMapperImplTest {

    private val bookEntityMapper: BookEntityMapper = BookEntityMapperImpl()

    @Test
    fun `mapBookEntitiesToBooks should map correct values`() {
        val books: MutableList<com.mediagenix.api.core.model.Book> = arrayListOf(createBook())
        val bookentities: MutableList<Book> = arrayListOf(createBookEntity())

        assertEquals(books, bookEntityMapper.mapBookEntitiesToBooks(bookentities))
    }

    @Test
    fun `mapBooksToBookEntities should map correct values`() {
        val bookentities: MutableList<Book> = arrayListOf(createBookEntity())
        val books: MutableList<com.mediagenix.api.core.model.Book> = arrayListOf(createBook())

        assertEquals(bookentities, bookEntityMapper.mapBooksToBookEntities(books))
    }

    @Test
    fun `mapBookEntityToBook should map correct values`() {
        assertEquals(createBook(), bookEntityMapper.mapBookEntityToBook(createBookEntity()))
    }

    @Test
    fun `mapBookToBookEntity should map correct values`() {
        assertEquals(createBookEntity(), bookEntityMapper.mapBookToBookEntity(createBook()))
    }

    private fun createBook(): com.mediagenix.api.core.model.Book {
        return com.mediagenix.api.core.model.Book(1, "title", "isbn", "author")
    }

    private fun createBookEntity(): Book {
        return Book(1, "title", "isbn", "author")
    }
}
