package ru.elixor.api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.elixor.api.exceptions.errors.*
import ru.elixor.api.exceptions.misc.ExceptionErrorsConstants
import ru.elixor.api.exceptions.misc.ExceptionFieldsConstants

@ControllerAdvice
class GlobalExceptionHandler {

    // region HttpStatus.NOT_FOUND

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundByUidException(ex: NotFoundException): ResponseEntity<Map<String, String>> {
        val errorResponse = mapOf(
            ExceptionFieldsConstants.ERROR to (ExceptionErrorsConstants.NOT_FOUND),
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(FkNotFoundException::class)
    fun handleNotFoundByUidException(ex: FkNotFoundException): ResponseEntity<Map<String, String>> {
        val errorResponse = mapOf(
            ExceptionFieldsConstants.ERROR to (ExceptionErrorsConstants.FK_NOT_FOUND),
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }


    // endregion

    // region HttpStatus.CONFLICT

    @ExceptionHandler(UniqueConflictException::class)
    fun handleUniqueConflictException(ex: UniqueConflictException): ResponseEntity<Any> {
        val errorResponse = mapOf(
            ExceptionFieldsConstants.ERROR to (ExceptionErrorsConstants.NOT_UNIQUE),
        )
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse)
    }

    @ExceptionHandler(RecordInUseException::class)
    fun handleRecordInUseException(ex: RecordInUseException): ResponseEntity<Any> {
        val errorResponse = mapOf(
            ExceptionFieldsConstants.ERROR to (ExceptionErrorsConstants.IS_USED),
        )
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse)
    }

    @ExceptionHandler(DataNotSyncException::class)
    fun handleDataNotSyncException(ex: DataNotSyncException): ResponseEntity<Any> {
        val errorResponse = mapOf(
            ExceptionFieldsConstants.ERROR to (ExceptionErrorsConstants.DATA_NOT_SYNC),
        )
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse)
    }

    // endregion

    @ExceptionHandler(TooManyRecordsException::class)
    fun handleTooManyRecordsException(ex: TooManyRecordsException): ResponseEntity<Any> {
        val errorResponse = mapOf(
            ExceptionFieldsConstants.ERROR to (ExceptionErrorsConstants.TOO_MANY_RECORDS_ERROR),
            ExceptionFieldsConstants.MAX_ALLOWED_RECORDS to ex.maxRecordCount
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors = ex.bindingResult.fieldErrors.map { it.field }

        val errorResponse = mapOf(
            ExceptionFieldsConstants.ERROR to (ExceptionErrorsConstants.NOT_VALID),
            ExceptionFieldsConstants.INVALID_FIELDS to errors
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse)
    }
}

