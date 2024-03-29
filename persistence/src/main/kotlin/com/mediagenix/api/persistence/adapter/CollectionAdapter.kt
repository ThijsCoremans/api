package com.mediagenix.api.persistence.adapter

import com.mediagenix.api.core.exception.BookNotFoundException
import com.mediagenix.api.core.exception.CollectionNotFoundException
import com.mediagenix.api.core.model.Collection
import com.mediagenix.api.core.port.CollectionPort
import com.mediagenix.api.persistence.entity.Book
import com.mediagenix.api.persistence.mapper.CollectionEntityMapper
import com.mediagenix.api.persistence.repository.BookRepository
import com.mediagenix.api.persistence.repository.CollectionRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.*

@Component
class CollectionAdapter(private val collectionRepository: CollectionRepository,
                        private val bookRepository: BookRepository,
                        private val collectionEntityMapper: CollectionEntityMapper): CollectionPort {

    override fun getALlCollections(): MutableList<Collection> {
        return collectionEntityMapper.mapCollectionEntitiesToCollections(collectionRepository.findAll(Sort.by("name")))
    }

    override fun getCollectionById(collectionId: Long): Collection {
        return collectionRepository.findById(collectionId)
            .map { collectionEntity -> collectionEntityMapper.mapCollectionEntityToCollection(collectionEntity) }
            .orElseThrow { CollectionNotFoundException() }
    }

    override fun createCollection(collection: Collection): Collection {
        val collectionEntity: com.mediagenix.api.persistence.entity.Collection = collectionEntityMapper.mapCollectionToCollectionEntity(collection)
        return collectionEntityMapper.mapCollectionEntityToCollection(collectionRepository.save(collectionEntity))
    }

    override fun updateCollection(collection: Collection): Collection {
        val optCollection: Optional<com.mediagenix.api.persistence.entity.Collection> = collectionRepository.findById(collection.id)
        if (optCollection.isEmpty) {
            throw CollectionNotFoundException()
        }

        var collectionEntity: com.mediagenix.api.persistence.entity.Collection = optCollection.get()
        collectionEntity.name = collection.name
        return collectionEntityMapper.mapCollectionEntityToCollection(collectionRepository.save(collectionEntity))
    }

    override fun deleteCollectionById(collectionId: Long) {
        if (!collectionRepository.existsById(collectionId)) {
            throw CollectionNotFoundException()
        }
        collectionRepository.deleteById(collectionId)
    }

    override fun addBookToCollection(collectionId: Long, bookId: Long): Collection {
        val optCollection: Optional<com.mediagenix.api.persistence.entity.Collection> = collectionRepository.findById(collectionId)
        if (optCollection.isEmpty) {
            throw CollectionNotFoundException()
        }
        val collection: com.mediagenix.api.persistence.entity.Collection = optCollection.get()
        val optBook: Optional<Book> = bookRepository.findById(bookId)
        if (optBook.isEmpty) {
            throw BookNotFoundException()
        }

        collection.books.add(optBook.get())
        return collectionEntityMapper.mapCollectionEntityToCollection(collectionRepository.save(collection))
    }

    override fun deleteBookFromCollection(collectionId: Long, bookId: Long) {
        val optCollection: Optional<com.mediagenix.api.persistence.entity.Collection> = collectionRepository.findById(collectionId)
        if (optCollection.isEmpty) {
            throw CollectionNotFoundException()
        }

        val collection: com.mediagenix.api.persistence.entity.Collection = optCollection.get()
        if (!collection.books.any { it.id == bookId }) {
            throw BookNotFoundException()
        }

        collection.books = collection.books.filter { book -> book.id != bookId } as MutableList<Book>
        collectionRepository.save(collection)
    }
}
