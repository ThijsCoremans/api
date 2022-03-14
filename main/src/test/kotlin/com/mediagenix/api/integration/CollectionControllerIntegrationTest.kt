package com.mediagenix.api.integration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mediagenix.api.rest.dto.book.BookDto
import com.mediagenix.api.rest.dto.collection.CreateCollectionDto
import com.mediagenix.api.rest.dto.collection.UpdateCollectionDto
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

internal class CollectionControllerIntegrationTest: AbstractRestControllerIntegrationTest() {

    private val collectionsPath = "/collections"

    @Test
    fun `getALlCollections returns list of all collections`() {
        mockMvc.get(collectionsPath)
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].id") { value(51) }
                jsonPath("$[0].name") { value("Science") }
                jsonPath("$[0].books[0].id") { value(52) }
                jsonPath("$[0].books[0].title") { value("Brief Answers to the Big Questions") }
                jsonPath("$[0].books[0].isbn") { value("978-1984819192") }
                jsonPath("$[0].books[0].author") { value("Stephen Hawking") }
                jsonPath("$[0].books[1].id") { value(51) }
                jsonPath("$[0].books[1].title") { value("Fysica") }
                jsonPath("$[0].books[1].isbn") { value("9789463930611") }
                jsonPath("$[0].books[1].author") { value("Lieven Scheire") }
                jsonPath("$[1].id") { value(50) }
                jsonPath("$[1].name") { value("Stephen King") }
                jsonPath("$[1].books[0].id") { value(50) }
                jsonPath("$[1].books[0].title") { value("Rita Hayworth and Shawshank Redemption") }
                jsonPath("$[1].books[0].isbn") { value("978-1982155759") }
                jsonPath("$[1].books[0].author") { value("Stephen King") }
            }
    }

    @Test
    fun `getCollectionById should return the collection by given id`() {
        mockMvc.get(collectionsPath.plus("/50"))
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id") { value(50) }
                jsonPath("$.name") { value("Stephen King") }
                jsonPath("$.books[0].id") { value(50) }
                jsonPath("$.books[0].title") { value("Rita Hayworth and Shawshank Redemption") }
                jsonPath("$.books[0].isbn") { value("978-1982155759") }
                jsonPath("$.books[0].author") { value("Stephen King") }
            }
    }

    @Test
    fun `getCollectionById should send not found with message if collection not found`() {
        mockMvc.get(collectionsPath.plus("/100"))
            .andExpect {
                status { isNotFound() }
                jsonPath("$.message") { value("Requested collection has not been found") }
            }
    }

    @Test
    fun `createCollection should create and return given collection`() {
        val bookDto = BookDto(51, "Fysica", "9789463930611", "Lieven Scheire")
        val createCollectionDto = CreateCollectionDto("new collection", arrayListOf(bookDto))

        mockMvc.post(collectionsPath) {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(createCollectionDto)
        }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id") { value(1) }
                jsonPath("$.name") { value("new collection") }
                jsonPath("$.books[0].id") { value(51) }
                jsonPath("$.books[0].title") { value("Fysica") }
                jsonPath("$.books[0].isbn") { value("9789463930611") }
                jsonPath("$.books[0].author") { value("Lieven Scheire") }
            }
    }

    @Test
    fun `updateCollection should update and return the collection`() {
        val updateCollectionDto = UpdateCollectionDto("updated collection")

        mockMvc.put(collectionsPath.plus("/50")) {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(updateCollectionDto)
        }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id") { value(50) }
                jsonPath("$.name") { value("updated collection") }
                jsonPath("$.books[0].id") { value(50) }
                jsonPath("$.books[0].title") { value("Rita Hayworth and Shawshank Redemption") }
                jsonPath("$.books[0].isbn") { value("978-1982155759") }
                jsonPath("$.books[0].author") { value("Stephen King") }
            }
    }

    @Test
    fun `updateCollection should send not found with message if collection not found`() {
        val updateCollectionDto = UpdateCollectionDto("updated collection")

        mockMvc.put(collectionsPath.plus("/100")) {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(updateCollectionDto)
        }
            .andExpect {
                status { isNotFound() }
                jsonPath("$.message") { value("Requested collection has not been found") }
            }
    }

    @Test
    fun `deleteCollection should delete given book`() {
        mockMvc.delete(collectionsPath.plus("/50"))
            .andExpect {
                status { isOk() }
            }

        mockMvc.get(collectionsPath.plus("/50"))
            .andExpect {
                status { isNotFound() }
            }
    }

    @Test
    fun `deleteCollection should send not found with message if collection not found`() {
        mockMvc.delete(collectionsPath.plus("/100"))
            .andExpect {
                status { isNotFound() }
                jsonPath("$.message") { value("Requested collection has not been found") }
            }
    }

    @Test
    fun `addBookToCollection should add a book to a collection`() {
        mockMvc.post(collectionsPath.plus("/50/books/53"))
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { value(50) }
                jsonPath("$.name") { value("Stephen King") }
                jsonPath("$.books[0].id") { value(53) }
                jsonPath("$.books[0].title") { value("Norse Mythology") }
                jsonPath("$.books[0].isbn") { value("9780393609097") }
                jsonPath("$.books[0].author") { value("Neil Gaiman") }
                jsonPath("$.books[1].id") { value(50) }
                jsonPath("$.books[1].title") { value("Rita Hayworth and Shawshank Redemption") }
                jsonPath("$.books[1].isbn") { value("978-1982155759") }
                jsonPath("$.books[1].author") { value("Stephen King") }
            }
    }

    @Test
    fun `addBookToCollection should send not found with message if collection not found`() {
        mockMvc.post(collectionsPath.plus("/100/books/53"))
            .andExpect {
                status { isNotFound() }
                jsonPath("$.message") { value("Requested collection has not been found") }
            }
    }

    @Test
    fun `addBookToCollection should send not found with message if book not found`() {
        mockMvc.post(collectionsPath.plus("/50/books/100"))
            .andExpect {
                status { isNotFound() }
                jsonPath("$.message") { value("Requested book has not been found") }
            }
    }

    @Test
    fun `deleteBookFromCollection should delete a book from a collection`() {
        mockMvc.delete(collectionsPath.plus("/51/books/51"))
            .andExpect {
                status { isOk() }
            }

        mockMvc.get(collectionsPath.plus("/51"))
            .andExpect {
                status { isOk() }
            }
            .andExpect {
                jsonPath("$.id") { value(51) }
                jsonPath("$.name") { value("Science") }
                jsonPath("$.books[0].id") { value(52) }
                jsonPath("$.books[0].title") { value("Brief Answers to the Big Questions") }
                jsonPath("$.books[0].isbn") { value("978-1984819192") }
                jsonPath("$.books[0].author") { value("Stephen Hawking") }
            }
    }

    @Test
    fun `deleteBookFromCollection should send not found with message if collection not found`() {
        mockMvc.delete(collectionsPath.plus("/100/books/51"))
            .andExpect {
                status { isNotFound() }
                jsonPath("$.message") { value("Requested collection has not been found") }
            }
    }

    @Test
    fun `deleteBookFromCollection should send not found with message if book not found in collection`() {
        mockMvc.delete(collectionsPath.plus("/51/books/100"))
            .andExpect {
                status { isNotFound() }
                jsonPath("$.message") { value("Requested book has not been found") }
            }
    }
}
