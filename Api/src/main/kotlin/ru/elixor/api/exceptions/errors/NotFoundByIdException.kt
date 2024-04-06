package ru.elixor.api.exceptions.errors

class NotFoundByIdException(val id: String, val name: String) : RuntimeException()