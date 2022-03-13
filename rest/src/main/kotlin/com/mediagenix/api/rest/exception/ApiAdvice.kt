package com.mediagenix.api.rest.exception

import com.mediagenix.api.core.exception.EntityNotFoundException
import com.mediagenix.api.rest.dto.ErrorResponseDto
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApiAdvice {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException) = ErrorResponseDto("Requested entity has not been found")
}
