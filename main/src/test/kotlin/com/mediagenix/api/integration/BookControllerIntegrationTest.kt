package com.mediagenix.api.integration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mediagenix.api.rest.dto.book.BookDto
import com.mediagenix.api.rest.dto.book.CreateBookDto
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

internal class BookControllerIntegrationTest: AbstractRestControllerIntegrationTest() {

    private val booksPath = "/books"

    @Test
    fun `getAllBooks returns list of all books`() {
        mockMvc.get(booksPath)
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].id") { value(52) }
                jsonPath("$[0].title") { value("Brief Answers to the Big Questions") }
                jsonPath("$[0].isbn") { value("978-1984819192") }
                jsonPath("$[0].author") { value("Stephen Hawking") }
                jsonPath("$[1].id") { value(51) }
                jsonPath("$[1].title") { value("Fysica") }
                jsonPath("$[1].isbn") { value("9789463930611") }
                jsonPath("$[1].author") { value("Lieven Scheire") }
                jsonPath("$[2].id") { value(53) }
                jsonPath("$[2].title") { value("Norse Mythology") }
                jsonPath("$[2].isbn") { value("9780393609097") }
                jsonPath("$[2].author") { value("Neil Gaiman") }
                jsonPath("$[3].id") { value(50) }
                jsonPath("$[3].title") { value("Rita Hayworth and Shawshank Redemption") }
                jsonPath("$[3].isbn") { value("978-1982155759") }
                jsonPath("$[3].author") { value("Stephen King") }
            }
    }

    @Test
    fun `getBookById should return the book by given id`() {
        mockMvc.get(booksPath.plus("/52"))
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id") { value(52) }
                jsonPath("$.title") { value("Brief Answers to the Big Questions") }
                jsonPath("$.isbn") { value("978-1984819192") }
                jsonPath("$.author") { value("Stephen Hawking") }
            }
    }

    @Test
    fun `getBookById should send not found with message if book not found`() {
        mockMvc.get(booksPath.plus("/100"))
            .andExpect {
                status { isNotFound() }
                jsonPath("$.message") { value("Requested entity has not been found") }
            }
    }

    @Test
    fun `createBook should create and return given book`() {
        val createBookDto = CreateBookDto("title", "isbn", "author")

        mockMvc.post(booksPath) {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(createBookDto)
        }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id") { value(1) }
                jsonPath("$.title") { value("title") }
                jsonPath("$.isbn") { value("isbn") }
                jsonPath("$.author") { value("author") }
            }
    }

    @Test
    fun `updateBook should update and return a book`() {
        val bookDto = BookDto(52,"Brief Answers to the Big Questions v2", "978-1984819192", "Stephen Hawking")

        mockMvc.put(booksPath) {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(bookDto)
        }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id") { value(52) }
                jsonPath("$.title") { value("Brief Answers to the Big Questions v2") }
                jsonPath("$.isbn") { value("978-1984819192") }
                jsonPath("$.author") { value("Stephen Hawking") }
            }
    }

    @Test
    fun `deleteBook should delete given book`() {
        mockMvc.delete(booksPath.plus("/52"))
            .andExpect {
                status { isOk() }
            }


        mockMvc.get(booksPath.plus("/52"))
            .andExpect {
                status { isNotFound() }
            }
    }
}
