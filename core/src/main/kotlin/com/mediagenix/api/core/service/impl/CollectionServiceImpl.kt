package com.mediagenix.api.core.service.impl

import com.mediagenix.api.core.infrastructure.CollectionInfrastructure
import com.mediagenix.api.core.model.Collection
import com.mediagenix.api.core.service.CollectionService
import org.springframework.stereotype.Service

@Service
class CollectionServiceImpl(private val collectionInfrastructure: CollectionInfrastructure) : CollectionService {
    override fun getALlCollections(): MutableList<Collection> = collectionInfrastructure.getALlCollections()
    override fun getCollectionById(collectionId: Long): Collection = collectionInfrastructure.getCollectionById(collectionId)
    override fun createCollection(collection: Collection): Collection = collectionInfrastructure.createCollection(collection)
    override fun updateCollection(collection: Collection): Collection = collectionInfrastructure.updateCollection(collection)
    override fun deleteCollectionById(collectionId: Long) = collectionInfrastructure.deleteCollectionById(collectionId)
    override fun addBookToCollection(collectionId: Long, bookId: Long): Collection = collectionInfrastructure.addBookToCollection(collectionId, bookId)
    override fun deleteBookFromCollection(collectionId: Long, bookId: Long) = collectionInfrastructure.deleteBookFromCollection(collectionId, bookId)
}
