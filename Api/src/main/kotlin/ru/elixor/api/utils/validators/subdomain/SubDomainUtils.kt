package ru.elixor.api.utils.validators.subdomain

internal object SubDomainUtils {
    private val pattern = "^[a-zA-Z]{2,16}\$".toRegex()
    fun isValid(value: String): Boolean = pattern.matches(value) && value.length in 2..16
}