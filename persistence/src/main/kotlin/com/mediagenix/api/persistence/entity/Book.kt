package com.mediagenix.api.persistence.entity

import javax.persistence.*

@Entity
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_gen")
    @SequenceGenerator(name = "book_gen", sequenceName = "book_gen", allocationSize = 1)
    val id: Long? = null,

    var title: String? = null,

    @Column(unique = true)
    var isbn: String? = null,

    var author: String? = null
): AbstractEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (id != other.id) return false
        if (title != other.title) return false
        if (isbn != other.isbn) return false
        if (author != other.author) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (isbn?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Book(id=$id, title=$title, isbn=$isbn, author=$author)"
    }
}
