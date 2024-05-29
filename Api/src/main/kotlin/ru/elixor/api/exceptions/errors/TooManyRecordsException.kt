package ru.elixor.api.exceptions.errors

class TooManyRecordsException(
    val maxRecordCount: Int
) : RuntimeException("Too many records in the database. Maximum allowed: $maxRecordCount")