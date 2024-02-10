package ru.elixor.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.elixor.domain.models.Link
import java.util.*

@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
    val link = Link(UUID.randomUUID());
    runApplication<ApiApplication>(*args)
}
