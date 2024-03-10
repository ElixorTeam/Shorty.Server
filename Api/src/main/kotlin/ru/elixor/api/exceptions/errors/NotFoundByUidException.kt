package ru.elixor.api.exceptions.errors

import java.util.UUID

class NotFoundByUidException(id: UUID) : RuntimeException(id.toString())