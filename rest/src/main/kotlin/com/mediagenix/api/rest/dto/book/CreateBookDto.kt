package com.mediagenix.api.rest.dto.book

data class CreateBookDto(
    val title: String?,
    val ISBN: String?,
    val author: String?
)
