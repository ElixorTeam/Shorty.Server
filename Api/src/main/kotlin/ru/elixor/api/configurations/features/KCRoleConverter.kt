package ru.elixor.api.configurations.features

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.core.GrantedAuthority
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.authority.SimpleGrantedAuthority

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Value

// convert JWT role to spring security
@Component
class KCRoleConverter(@Value("\${keycloak.client.business}") private val clientId: String) : Converter<Jwt, Collection<GrantedAuthority>> {
    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val clientAccess = (jwt.claims["resource_access"] as Map<*, *>?)?.get(clientId) as Map<*, *>?

        val returnValue: MutableCollection<GrantedAuthority> = ArrayList()
        val roles = clientAccess?.get("roles") as List<*>?

        if (roles.isNullOrEmpty())
            return returnValue

        for (roleName in roles)
            returnValue.add(SimpleGrantedAuthority("ROLE_$roleName"))

        return returnValue
    }
}