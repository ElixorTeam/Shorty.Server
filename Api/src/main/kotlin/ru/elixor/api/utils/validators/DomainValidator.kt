package ru.elixor.api.utils.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DomainValidator::class])
annotation class ValidDomain(
    val message: String = "Invalid domain",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class DomainValidator : ConstraintValidator<ValidDomain, String> {
    private val pattern = "^[a-z0-9]+[.:][a-z0-9]{2,}$".toRegex()

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value?.let {
            pattern.matches(it) && it.length in 5..32
        } ?: false
    }
}