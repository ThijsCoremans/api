package com.mediagenix.api.rest.mapper.impl

import com.mediagenix.api.core.model.Collection
import com.mediagenix.api.rest.dto.collection.CollectionDto
import com.mediagenix.api.rest.dto.collection.CreateCollectionDto
import com.mediagenix.api.rest.dto.collection.UpdateCollectionDto
import com.mediagenix.api.rest.mapper.BookDtoMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CollectionDtoMapperImplTest {

    private val bookDtoMapper: BookDtoMapper = BookDtoMapperImpl()
    private val collectionDtoMapper: CollectionDtoMapperImpl = CollectionDtoMapperImpl(bookDtoMapper)

    @Test
    fun `mapCollectionsToCollectionDtos should map single collection with correct values`() {
        val collections: MutableList<Collection> = arrayListOf(createCollection(1))
        val collectionDtos: MutableList<CollectionDto> = arrayListOf(createCollectionDto())

        assertEquals(collectionDtos, collectionDtoMapper.mapCollectionsToCollectionDtos(collections))
    }

    @Test
    fun `mapCollectionsToCollectionDtos should map multiple collections with correct values`() {
        val collections: MutableList<Collection> = arrayListOf(createCollection(1))
        collections.add(Collection(2, "my second collection", arrayListOf()))
        val collectionDtos: MutableList<CollectionDto> = arrayListOf(createCollectionDto())
        collectionDtos.add(CollectionDto(2, "my second collection", arrayListOf()))

        assertEquals(collectionDtos, collectionDtoMapper.mapCollectionsToCollectionDtos(collections))
    }

    @Test
    fun `mapCollectionToCollectionDto should map correct values`() {
        assertEquals(createCollectionDto(), collectionDtoMapper.mapCollectionToCollectionDto(createCollection(1)))
    }

    @Test
    fun `mapCollectionDtoToCollection should map correct values`() {
        assertEquals(createCollection(1), collectionDtoMapper.mapCollectionDtoToCollection(createCollectionDto()))
    }

    @Test
    fun `mapCreateCollectionDtoToCollection should map correct values`() {
        assertEquals(createCollection(null), collectionDtoMapper.mapCreateCollectionDtoToCollection(createCreateCollectionDto()))
    }

    @Test
    fun `mapUpdateCollectionDtoToCollection should map correct values`() {
        assertEquals(createCollection(1), collectionDtoMapper.mapUpdateCollectionDtoToCollection(1, createUpdateCollectionDto()))
    }

    private fun createCollection(id: Long?): Collection {
        return Collection(id, "my collection", arrayListOf())
    }

    private fun createCollectionDto(): CollectionDto {
        return CollectionDto(1, "my collection", arrayListOf())
    }

    private fun createCreateCollectionDto(): CreateCollectionDto {
        return CreateCollectionDto("my collection", arrayListOf())
    }

    private fun createUpdateCollectionDto(): UpdateCollectionDto {
        return UpdateCollectionDto("my collection")
    }
}
