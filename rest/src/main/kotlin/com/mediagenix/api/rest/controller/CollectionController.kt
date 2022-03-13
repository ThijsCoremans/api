package com.mediagenix.api.rest.controller

import com.mediagenix.api.core.model.Collection
import com.mediagenix.api.core.service.CollectionService
import com.mediagenix.api.rest.dto.collection.CollectionDto
import com.mediagenix.api.rest.dto.collection.CreateCollectionDto
import com.mediagenix.api.rest.mapper.CollectionDtoMapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/collections")
class CollectionController(private val collectionService: CollectionService,
                           private val collectionDtoMapper: CollectionDtoMapper
) {
    @GetMapping
    fun getALlCollections(): MutableList<CollectionDto> {
        return collectionDtoMapper.mapCollectionsToCollectionDtos(collectionService.getALlCollections())
    }

    @GetMapping("/{collectionId}")
    fun getCollectionById(@PathVariable collectionId: Long): CollectionDto {
        return collectionDtoMapper.mapCollectionToCollectionDto(collectionService.getCollectionById(collectionId))
    }

    @PostMapping
    fun createCollection(@RequestBody createCollectionDto: CreateCollectionDto): CollectionDto {
        val collection: Collection = collectionDtoMapper.mapCreateCollectionDtoToCollection(createCollectionDto)
        return collectionDtoMapper.mapCollectionToCollectionDto(collectionService.createCollection(collection))
    }

    @PutMapping
    fun updateCollection(@RequestBody collectionDto: CollectionDto): CollectionDto {
        val collection: Collection = collectionDtoMapper.mapCollectionDtoToCollection(collectionDto)
        return collectionDtoMapper.mapCollectionToCollectionDto(collectionService.updateCollection(collection))
    }

    @DeleteMapping("/{collectionId}")
    fun deleteCollection(@PathVariable collectionId: Long) {
        collectionService.deleteCollectionById(collectionId)
    }

    @PostMapping("/{collectionId}/books/{bookId}")
    fun addBookToCollection(@PathVariable collectionId: Long, @PathVariable bookId: Long) {
        collectionService.addBookToCollection(collectionId, bookId)
    }

    @DeleteMapping("/{collectionId}/books/{bookId}")
    fun deleteBookFromCollection(@PathVariable collectionId: Long, @PathVariable bookId: Long) {
        collectionService.deleteBookFromCollection(collectionId, bookId)
    }
}
