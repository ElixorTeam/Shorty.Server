package ru.elixor.api.utils.validators

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import ru.elixor.api.utils.validators.subdomain.SubdomainValidator

@SpringJUnitConfig
@SpringBootTest
class SubDomainValidatorTests {

    private var subDomainValidator: SubdomainValidator = SubdomainValidator()

    @ParameterizedTest
    @CsvSource(
        "example, true",
        "baggerfast, true",
        "andreyytadadwadwd, false",
        "Gosha0, false",
        "sub-domain, false",
        "e, false",
        "sub_domain, false",
        "example.ru, false",
        "123subdomain, false",
        "https://192.168.1.1, false",
        "https://example.com:8080, false",
        "'', false"
    )
    fun test_url_valid(input: String, expected: Boolean) {
        val result = subDomainValidator.isValid(input, null)
        assertEquals(expected, result, "Expected $expected but got $result for subdomain $input")
    }
}