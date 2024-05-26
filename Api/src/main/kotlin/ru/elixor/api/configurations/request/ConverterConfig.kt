package ru.elixor.api.configurations.request

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import java.net.InetAddress

@Configuration
class ConverterConfig {

    @Bean
    fun stringToInetAddressConverter(): Converter<String, InetAddress> {
        return StringToInetAddressConverter()
    }

    inner class StringToInetAddressConverter : Converter<String, InetAddress> {
        override fun convert(source: String): InetAddress {
            return InetAddress.getByName(source)
        }
    }

}