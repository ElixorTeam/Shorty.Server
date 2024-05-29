package ru.elixor.api.utils.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import java.net.URL
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [URLValidator::class])
annotation class ValidURL(
    val message: String = "Invalid URL",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class URLValidator : ConstraintValidator<ValidURL, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrBlank()) {
            return false
        }

        return try {
            val url = URL(value)
            val host = url.host
            val protocol = url.protocol
            val port = url.port

            val ipRegex = Regex("^\\d+\\.\\d+\\.\\d+\\.\\d+\$")
            val hostRegex = Regex("^([a-zA-Z0-9-]+\\.)*[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}\$")

            if (!host.matches(hostRegex) || host.matches(ipRegex))
                return false

            return (protocol == "https" || protocol.isEmpty()) &&
                    !host.endsWith(".local") && host != "localhost" && port == -1
        } catch (e: Exception) {
            false
        }
    }
}