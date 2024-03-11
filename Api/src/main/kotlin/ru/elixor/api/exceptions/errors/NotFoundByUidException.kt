package ru.elixor.api.exceptions.errors

import java.util.*

class NotFoundByUidException(val id: UUID, val name: String) : RuntimeException()