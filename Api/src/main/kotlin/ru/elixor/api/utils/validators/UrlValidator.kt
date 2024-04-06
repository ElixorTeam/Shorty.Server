package ru.elixor.api.utils.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import ru.elixor.api.utils.UrlUtils
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
        if (value == null) {
            return false
        }

        return try {
            val url = UrlUtils.convert(value)
            val host = url.host
            val protocol = url.protocol
            val port = url.port

            protocol == "https" && !host.endsWith(".local") && host != "localhost" && port == -1
        } catch (e: Exception) {
            false
        }
    }
}