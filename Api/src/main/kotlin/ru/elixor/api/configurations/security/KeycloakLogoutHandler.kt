package ru.elixor.api.configurations.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder


@Component
class KeycloakLogoutHandler : LogoutHandler {
    override fun logout(request: HttpServletRequest?, response: HttpServletResponse?, auth: Authentication) {
        logoutFromKeycloak(auth.principal as OidcUser)
    }

    private fun logoutFromKeycloak(user: OidcUser) {
        val endSessionEndpoint = user.issuer.toString() + "/protocol/openid-connect/logout"
        UriComponentsBuilder
            .fromUriString(endSessionEndpoint)
            .queryParam("id_token_hint", user.idToken.tokenValue)
    }
}