package com.mediagenix.api.rest.dto.collection

import com.mediagenix.api.rest.dto.book.BookDto

data class CreateCollectionDto(
    val name: String?,
    val books: MutableList<BookDto>
)
