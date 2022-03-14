package com.mediagenix.api.rest.mapper

import com.mediagenix.api.core.model.Collection
import com.mediagenix.api.rest.dto.collection.CollectionDto
import com.mediagenix.api.rest.dto.collection.CreateCollectionDto
import com.mediagenix.api.rest.dto.collection.UpdateCollectionDto

interface CollectionDtoMapper {
    fun mapCollectionsToCollectionDtos(collections: MutableList<Collection>): MutableList<CollectionDto>
    fun mapCollectionToCollectionDto(collection: Collection): CollectionDto
    fun mapCollectionDtoToCollection(collectionDto: CollectionDto): Collection
    fun mapCreateCollectionDtoToCollection(createCollectionDto: CreateCollectionDto): Collection
    fun mapUpdateCollectionDtoToCollection(collectionId: Long, updateCollectionDto: UpdateCollectionDto): Collection
}
