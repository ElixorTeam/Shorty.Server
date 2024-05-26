package ru.elixor.api.utils.validators.subdomain

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [SubdomainValidator::class])
annotation class ValidSubDomain(
    val message: String = "Invalid subdomain",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


open class SubdomainValidator : ConstraintValidator<ValidSubDomain, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value?.let { SubDomainUtils.isValid(it) } ?: false
    }
}