package ru.elixor.api.configurations.request

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import ru.elixor.api.enums.PeriodType
import java.net.InetAddress

@Configuration
class ConverterConfig {

    @Bean
    fun stringToInetAddressConverter(): Converter<String, InetAddress> {
        return StringToInetAddressConverter()
    }

    @Bean
    fun stringToPeriodTypeConverter(): Converter<String, PeriodType> {
        return StringToPeriodTypeConverter()
    }

    inner class StringToInetAddressConverter : Converter<String, InetAddress> {
        override fun convert(source: String): InetAddress {
            return InetAddress.getByName(source)
        }
    }

    inner class StringToPeriodTypeConverter : Converter<String, PeriodType> {
        override fun convert(source: String): PeriodType? {
            return PeriodType.fromValue(source)
    }
    }

}