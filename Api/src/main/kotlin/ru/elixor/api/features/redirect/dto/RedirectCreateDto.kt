package ru.elixor.api.features.redirect.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import ru.elixor.api.entities.redirect.RedirectEntity
import ru.elixor.api.enums.DeviceTypes
import ru.elixor.api.utils.validators.ValidDomain
import ru.elixor.api.utils.validators.subdomain.ValidOptionalSubDomain
import java.net.InetAddress
import java.util.*

class RedirectCreateDto(
    @field:Size(min = 2, max = 16, message = "os must be [2, 16] characters")
    val os : String,

    @field:NotNull
    val device: DeviceTypes,

    @field:NotNull
    val ip: InetAddress,

    @field:NotNull
    val userKey: UUID,

    @field:ValidDomain
    val domain: String,

    @field:ValidOptionalSubDomain
    val subdomain: String?,

    @field:NotNull
    @field:Size(min = 2, max = 16, message = "Path must be [2, 16] characters")
    val path: String
)

fun RedirectCreateDto.toEntity(): RedirectEntity {
    val redirect = RedirectEntity()
    redirect.os = os
    redirect.ip = ip
    redirect.device = device
    redirect.clientKey = userKey
    return redirect
}