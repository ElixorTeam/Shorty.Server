package ru.elixor.api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.elixor.api.exceptions.errors.NotFoundByIdException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundByIdException::class)
    fun handleNotFoundByUidException(ex: NotFoundByIdException): ResponseEntity<Map<String, String>> {
        val errorAttributes = mapOf(
            "id" to ex.id,
            "error" to "${ex.name.lowercase()} not found"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorAttributes)
    }

}