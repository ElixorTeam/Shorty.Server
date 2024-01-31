package org.elixor.shortyserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShortyServerApplication

fun main(args: Array<String>) {
	runApplication<ShortyServerApplication>(*args)
}
