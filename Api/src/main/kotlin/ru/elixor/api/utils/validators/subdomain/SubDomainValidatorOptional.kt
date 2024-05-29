package ru.elixor.api.utils.validators.subdomain

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [OptionalSubdomainValidator::class])
annotation class ValidOptionalSubDomain(
    val message: String = "Invalid subdomain",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class OptionalSubdomainValidator : ConstraintValidator<ValidOptionalSubDomain, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        return SubDomainUtils.isValid(value)
    }
}