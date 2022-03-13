package com.mediagenix.api.rest.mapper.impl

import com.mediagenix.api.core.model.Book
import com.mediagenix.api.core.model.Collection
import com.mediagenix.api.rest.dto.collection.CollectionDto
import com.mediagenix.api.rest.dto.collection.CreateCollectionDto
import com.mediagenix.api.rest.mapper.BookDtoMapper
import com.mediagenix.api.rest.mapper.CollectionDtoMapper
import org.springframework.stereotype.Component

@Component
class CollectionDtoMapperImpl(private val bookDtoMapper: BookDtoMapper): CollectionDtoMapper {

    override fun mapCollectionsToCollectionDtos(collections: MutableList<Collection>): MutableList<CollectionDto> {
        return collections.map { bookCollection -> mapCollectionToCollectionDto(bookCollection) } as MutableList<CollectionDto>
    }

    override fun mapCollectionToCollectionDto(collection: Collection): CollectionDto {
        return CollectionDto(collection.id, collection.name, bookDtoMapper.mapBooksToBookDtos(collection.books))
    }

    override fun mapCollectionDtoToCollection(collectionDto: CollectionDto): Collection {
        val books: MutableList<Book> = bookDtoMapper.mapBookDtosToBooks(collectionDto.books)
        return Collection(collectionDto.id, collectionDto.name, books)
    }

    override fun mapCreateCollectionDtoToCollection(createCollectionDto: CreateCollectionDto): Collection {
        val books: MutableList<Book> = bookDtoMapper.mapBookDtosToBooks(createCollectionDto.books)
        return Collection(null, createCollectionDto.name, books)
    }
}
