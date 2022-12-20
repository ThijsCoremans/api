package com.mediagenix.api.core.service.impl

import com.mediagenix.api.core.model.Collection
import com.mediagenix.api.core.port.CollectionPort
import com.mediagenix.api.core.service.CollectionService
import org.springframework.stereotype.Service

@Service
class CollectionServiceImpl(private val collectionPort: CollectionPort) : CollectionService {
    override fun getALlCollections(): MutableList<Collection> = collectionPort.getALlCollections()
    override fun getCollectionById(collectionId: Long): Collection = collectionPort.getCollectionById(collectionId)
    override fun createCollection(collection: Collection): Collection = collectionPort.createCollection(collection)
    override fun updateCollection(collection: Collection): Collection = collectionPort.updateCollection(collection)
    override fun deleteCollectionById(collectionId: Long) = collectionPort.deleteCollectionById(collectionId)
    override fun addBookToCollection(collectionId: Long, bookId: Long): Collection = collectionPort.addBookToCollection(collectionId, bookId)
    override fun deleteBookFromCollection(collectionId: Long, bookId: Long) = collectionPort.deleteBookFromCollection(collectionId, bookId)
}
