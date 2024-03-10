package ru.elixor.api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.elixor.api.exceptions.errors.NotFoundByUidException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundByUidException::class)
    fun handleNotFoundByUidException(ex: NotFoundByUidException): ResponseEntity<Map<String, String>> {
        val errorAttributes = mapOf(
            "id" to (ex.message ?: "No message"),
            "error" to "not found"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorAttributes)
    }

}