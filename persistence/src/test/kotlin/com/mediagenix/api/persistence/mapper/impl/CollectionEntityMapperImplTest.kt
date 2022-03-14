package com.mediagenix.api.persistence.mapper.impl

import com.mediagenix.api.persistence.mapper.BookEntityMapper
import com.mediagenix.api.persistence.mapper.CollectionEntityMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CollectionEntityMapperImplTest {

    private val bookEntityMapper: BookEntityMapper = BookEntityMapperImpl()
    private val collectionEntityMapper: CollectionEntityMapper = CollectionEntityMapperImpl(bookEntityMapper)

    @Test
    fun `mapCollectionEntitiesToCollections should map correct values`() {
        val collections: MutableList<com.mediagenix.api.core.model.Collection> = arrayListOf(createCollection())
        val collectionEntities: MutableList<com.mediagenix.api.persistence.entity.Collection> = arrayListOf(createCollectionEntity())

        assertEquals(collections, collectionEntityMapper.mapCollectionEntitiesToCollections(collectionEntities))
    }

    @Test
    fun `mapCollectionsToCollectionEntities should map correct values`() {
        val collectionEntities: MutableList<com.mediagenix.api.persistence.entity.Collection> = arrayListOf(createCollectionEntity())
        val collections: MutableList<com.mediagenix.api.core.model.Collection> = arrayListOf(createCollection())

        assertEquals(collectionEntities, collectionEntityMapper.mapCollectionsToCollectionEntities(collections))
    }

    @Test
    fun `mapCollectionEntityToCollection should map correct values`() {
        assertEquals(createCollection(), collectionEntityMapper.mapCollectionEntityToCollection(createCollectionEntity()))
    }

    @Test
    fun `mapCollectionToCollectionEntity should map correct values`() {
        assertEquals(createCollectionEntity(), collectionEntityMapper.mapCollectionToCollectionEntity(createCollection()))
    }

    private fun createCollection(): com.mediagenix.api.core.model.Collection {
        return com.mediagenix.api.core.model.Collection(1, "name", arrayListOf())
    }

    private fun createCollectionEntity(): com.mediagenix.api.persistence.entity.Collection {
        return com.mediagenix.api.persistence.entity.Collection(1, "name", arrayListOf())
    }
}
