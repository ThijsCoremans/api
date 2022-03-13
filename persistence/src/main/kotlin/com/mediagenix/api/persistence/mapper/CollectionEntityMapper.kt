package com.mediagenix.api.persistence.mapper

import com.mediagenix.api.persistence.entity.Collection

interface CollectionEntityMapper {
    fun mapCollectionEntitiesToCollections(collectionEntities: MutableList<Collection>): MutableList<com.mediagenix.api.core.model.Collection>
    fun mapCollectionsToCollectionEntities(collections: MutableList<com.mediagenix.api.core.model.Collection>): MutableList<Collection>
    fun mapCollectionEntityToCollection(collectionEntity: Collection): com.mediagenix.api.core.model.Collection
    fun mapCollectionToCollectionEntity(collection: com.mediagenix.api.core.model.Collection): Collection
}
