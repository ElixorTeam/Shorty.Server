package ru.elixor.api.utils.validators

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig
@SpringBootTest
class DomainValidatorTests {

    private var domainValidator = DomainValidator()

    @ParameterizedTest
    @CsvSource(
        "example.com, true",
        "sh0.su, true",
        "vlsu.com, true",
        "example.co.uk, false",
        "example., false",
        "example., false",
        "'', false"
    )
    fun test_domain_valid(input: String, expected: Boolean) {
        val result = domainValidator.isValid(input, null)
        assertEquals(expected, result, "Expected $expected but got $result for domain $input")
    }
}