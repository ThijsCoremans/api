package com.mediagenix.api.rest.controller

import com.mediagenix.api.core.service.CollectionService
import com.mediagenix.api.rest.dto.collection.CollectionDto
import com.mediagenix.api.rest.dto.collection.CreateCollectionDto
import com.mediagenix.api.rest.mapper.CollectionDtoMapper
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class CollectionControllerTest {

    private val collectionService: CollectionService = mockk(relaxed = true)
    private val collectionDtoMapper: CollectionDtoMapper = mockk(relaxed = true)
    private val collectionController = CollectionController(collectionService, collectionDtoMapper)

    @Test
    fun `getALlCollections should call service and mapper`() {
        collectionController.getALlCollections()

        verify(exactly = 1) { collectionService.getALlCollections() }
        verify(exactly = 1) { collectionDtoMapper.mapCollectionsToCollectionDtos(any()) }
    }

    @Test
    fun `getCollectionById should call service and mapper`() {
        collectionController.getCollectionById(1)

        verify(exactly = 1) { collectionService.getCollectionById(1) }
        verify(exactly = 1) { collectionDtoMapper.mapCollectionToCollectionDto(any()) }
    }

    @Test
    fun `createCollection should call service and mapper`() {
        collectionController.createCollection(CreateCollectionDto("my collection", arrayListOf()))

        verify(exactly = 1) { collectionDtoMapper.mapCreateCollectionDtoToCollection(any()) }
        verify(exactly = 1) { collectionService.createCollection(any()) }
        verify(exactly = 1) { collectionDtoMapper.mapCollectionToCollectionDto(any()) }
    }

    @Test
    fun `updateCollection should call service and mapper`() {
        collectionController.updateCollection(CollectionDto(1, "my collection", arrayListOf()))

        verify(exactly = 1) { collectionDtoMapper.mapCollectionDtoToCollection(any()) }
        verify(exactly = 1) { collectionService.updateCollection(any()) }
        verify(exactly = 1) { collectionDtoMapper.mapCollectionToCollectionDto(any()) }
    }

    @Test
    fun `addBookToCollection should call service and mapper`() {
        collectionController.addBookToCollection(1, 1)

        verify(exactly = 1) { collectionService.addBookToCollection(1, 1) }
    }

    @Test
    fun `deleteBookFromCollection should call service and mapper`() {
        collectionController.deleteBookFromCollection(1, 1)

        verify(exactly = 1) { collectionService.deleteBookFromCollection(1, 1) }
    }
}
