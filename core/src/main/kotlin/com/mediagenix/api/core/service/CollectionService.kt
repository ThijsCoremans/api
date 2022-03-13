package com.mediagenix.api.core.service

import com.mediagenix.api.core.model.Collection

interface CollectionService {
    fun getALlCollections(): MutableList<Collection>
    fun getCollectionById(collectionId: Long): Collection
    fun createCollection(collection: Collection): Collection
    fun updateCollection(collection: Collection): Collection
    fun deleteCollectionById(collectionId: Long)
    fun addBookToCollection(collectionId: Long, bookId: Long)
    fun deleteBookFromCollection(collectionId: Long, bookId: Long)
}
