package ru.elixor.api.utils.validators

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig


@SpringJUnitConfig
@SpringBootTest
class UrlValidatorTests {

    private var urlValidator: URLValidator = URLValidator()
    
    @ParameterizedTest
    @CsvSource(
        "https://example.com, true",
        "https://sh0.su, true",
        "https://vlsu.com, true",
        "https://example.co.uk, true",
        "http://example.com, false",
        "https://localhost, false",
        "https://example.local, false",
        "example.com, false",
        "vk.com, false",
        "example., false",
        "192.168.1.1, false",
        "https://192.168.1.1, false",
        "https://example.com:8080, false",
        "'', false"
    )
    fun test_url_valid(input: String, expected: Boolean) {
        val result = urlValidator.isValid(input, null)
        assertEquals(expected, result, "Expected $expected but got $result for url $input")
    }
}