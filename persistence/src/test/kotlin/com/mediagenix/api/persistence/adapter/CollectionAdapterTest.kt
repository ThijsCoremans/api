package com.mediagenix.api.persistence.adapter

import com.mediagenix.api.core.exception.BookNotFoundException
import com.mediagenix.api.core.exception.CollectionNotFoundException
import com.mediagenix.api.core.model.Collection
import com.mediagenix.api.core.port.CollectionPort
import com.mediagenix.api.persistence.mapper.CollectionEntityMapper
import com.mediagenix.api.persistence.repository.BookRepository
import com.mediagenix.api.persistence.repository.CollectionRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.util.*

internal class CollectionAdapterTest {

    private val collectionRepository: CollectionRepository = mockk()
    private val bookRepository: BookRepository = mockk()
    private val collectionEntityMapper: CollectionEntityMapper = mockk()
    private val collectionPort: CollectionPort = CollectionAdapter(collectionRepository, bookRepository, collectionEntityMapper)

    @Test
    fun `getCollectionById throws exception if collection not found`() {
        every { collectionRepository.findById(any()) } returns Optional.empty()

        assertThrows(CollectionNotFoundException::class.java) { collectionPort.getCollectionById(1) }
    }

    @Test
    fun `updateCollection throws exception if collection not found`() {
        every { collectionRepository.findById(any()) } returns Optional.empty()

        assertThrows(CollectionNotFoundException::class.java) { collectionPort.updateCollection(Collection(1, "name", arrayListOf())) }
    }

    @Test
    fun `deleteCollectionById throws exception if collection not found`() {
        every { collectionRepository.existsById(any()) } returns false

        assertThrows(CollectionNotFoundException::class.java) { collectionPort.deleteCollectionById(1) }
    }

    @Test
    fun `addBookToCollection throws exception if collection not found`() {
        every { collectionRepository.findById(any()) } returns Optional.empty()

        assertThrows(CollectionNotFoundException::class.java) { collectionPort.addBookToCollection(1, 1) }
    }

    @Test
    fun `addBookToCollection throws exception if book not found`() {
        every { collectionRepository.findById(any()) } returns Optional.of(com.mediagenix.api.persistence.entity.Collection(1, "name", arrayListOf()))
        every { bookRepository.findById(any()) } returns Optional.empty()

        assertThrows(BookNotFoundException::class.java) { collectionPort.addBookToCollection(1, 1) }
    }

    @Test
    fun `deleteBookFromCollection throws exception if collection not found`() {
        every { collectionRepository.findById(any()) } returns Optional.empty()

        assertThrows(CollectionNotFoundException::class.java) { collectionPort.deleteBookFromCollection(1, 1) }
    }

    @Test
    fun `deleteBookFromCollection throws exception if book not found in collection`() {
        every { collectionRepository.findById(any()) } returns Optional.of(com.mediagenix.api.persistence.entity.Collection(1, "name", arrayListOf()))

        assertThrows(BookNotFoundException::class.java) { collectionPort.deleteBookFromCollection(1, 1) }
    }
}
