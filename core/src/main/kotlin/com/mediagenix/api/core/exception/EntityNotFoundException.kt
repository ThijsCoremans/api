package com.mediagenix.api.core.exception

open class EntityNotFoundException(val entity: String): RuntimeException()
