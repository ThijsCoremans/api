package com.mediagenix.api.rest.dto.book

data class CreateBookDto(
    val title: String?,
    val isbn: String?,
    val author: String?
)
