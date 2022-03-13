package com.mediagenix.api.persistence.entity

import java.util.Collections.emptyList
import javax.persistence.*

@Entity
class Collection(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collection_gen")
    @SequenceGenerator(name = "collection_gen", sequenceName = "collection_gen", allocationSize = 1)
    val id: Long? = null,

    var name: String? = null,

    @OneToMany
    @JoinColumn(name = "collection_id")
    var books: MutableList<Book> = emptyList()
): AbstractEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Collection

        if (name != other.name) return false
        if (books != other.books) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + books.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Collection(id=$id, name=$name, books=$books)"
    }
}
