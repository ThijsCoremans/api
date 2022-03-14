package com.mediagenix.api.persistence.mapper.impl

import com.mediagenix.api.persistence.entity.Book
import com.mediagenix.api.persistence.entity.Collection
import com.mediagenix.api.persistence.mapper.BookEntityMapper
import com.mediagenix.api.persistence.mapper.CollectionEntityMapper
import org.springframework.stereotype.Component

@Component
class CollectionEntityMapperImpl(private val bookEntityMapper: BookEntityMapper): CollectionEntityMapper {

    override fun mapCollectionEntitiesToCollections(collectionEntities: MutableList<Collection>): MutableList<com.mediagenix.api.core.model.Collection> {
        return collectionEntities.map { collectionEntity -> mapCollectionEntityToCollection(collectionEntity) } as MutableList<com.mediagenix.api.core.model.Collection>
    }

    override fun mapCollectionsToCollectionEntities(collections: MutableList<com.mediagenix.api.core.model.Collection>): MutableList<Collection> {
        return collections.map { collection -> mapCollectionToCollectionEntity(collection) } as MutableList<Collection>
    }

    override fun mapCollectionEntityToCollection(collectionEntity: Collection): com.mediagenix.api.core.model.Collection {
        val books: MutableList<com.mediagenix.api.core.model.Book> = bookEntityMapper.mapBookEntitiesToBooks(collectionEntity.books)
        books.sortBy { it.title }
        return com.mediagenix.api.core.model.Collection(collectionEntity.id, collectionEntity.name, books)
    }

    override fun mapCollectionToCollectionEntity(collection: com.mediagenix.api.core.model.Collection): Collection {
        val bookEntities: MutableList<Book> = bookEntityMapper.mapBooksToBookEntities(collection.books)
        return Collection(collection.id, collection.name, bookEntities)
    }
}
