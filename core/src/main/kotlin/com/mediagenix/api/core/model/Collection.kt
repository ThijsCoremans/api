package com.mediagenix.api.core.model

data class Collection(
    val id: Long?,
    val name: String?,
    val books: MutableList<Book>
)
