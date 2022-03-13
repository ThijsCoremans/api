package com.mediagenix.api.rest.exception

import com.mediagenix.api.core.exception.EntityNotFoundException
import com.mediagenix.api.rest.dto.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApiAdvice {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException)= ResponseEntity(ErrorResponseDto("Requested entity has not been found"), HttpStatus.NOT_FOUND)
}
