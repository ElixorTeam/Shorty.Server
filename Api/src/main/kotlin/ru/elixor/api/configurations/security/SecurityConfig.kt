package ru.elixor.api.configurations.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.stream.Collectors


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(private val kcRoleConverter: KCRoleConverter, private val keycloakLogoutHandler: KeycloakLogoutHandler) {


    private val GROUPS = "groups"
    private val REALM_ACCESS_CLAIM = "realm_access"
    private val ROLES_CLAIM = "roles"

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .csrf { it.disable() }
            .cors { }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/links").hasAnyRole("admin")
                    .requestMatchers("/").permitAll()
                    .anyRequest().authenticated()
//                    .anyRequest().permitAll()
                //
            }

            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt(Customizer.withDefaults())
                http.oauth2Login(Customizer.withDefaults())
                    .logout { logout -> logout.addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl("/") }
            }
            .build()
    }

    @Bean
    fun userAuthoritiesMapperForKeycloak(): GrantedAuthoritiesMapper {
        return GrantedAuthoritiesMapper { authorities: Collection<GrantedAuthority> ->
            val mappedAuthorities: MutableSet<GrantedAuthority> = HashSet()
            val authority = authorities.iterator().next()
            val isOidc = authority is OidcUserAuthority

            if (isOidc) {
                val oidcUserAuthority = authority as OidcUserAuthority
                val userInfo = oidcUserAuthority.userInfo


                if (userInfo.hasClaim(REALM_ACCESS_CLAIM)) {
                    val realmAccess = userInfo.getClaimAsMap(REALM_ACCESS_CLAIM)
                    val roles = realmAccess[ROLES_CLAIM] as Collection<String>?
                    mappedAuthorities.addAll(generateAuthoritiesFromClaim(roles))
                } else if (userInfo.hasClaim(GROUPS)) {
                    val roles = userInfo.getClaim<Any>(GROUPS) as Collection<String>
                    mappedAuthorities.addAll(generateAuthoritiesFromClaim(roles))
                }
            } else {
                val oauth2UserAuthority = authority as OAuth2UserAuthority
                val userAttributes = oauth2UserAuthority.attributes

                if (userAttributes.containsKey(REALM_ACCESS_CLAIM)) {
                    val realmAccess = userAttributes[REALM_ACCESS_CLAIM] as Map<String, Any>?
                    val roles =
                        realmAccess!![ROLES_CLAIM] as Collection<String>?
                    mappedAuthorities.addAll(generateAuthoritiesFromClaim(roles))
                }
            }
            mappedAuthorities
        }
    }

    fun generateAuthoritiesFromClaim(roles: Collection<String>?): Collection<GrantedAuthority> {
        return roles!!.stream().map { role: String ->
            SimpleGrantedAuthority(
                "ROLE_$role"
            )
        }.collect(
            Collectors.toList()
        )
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedHeaders = listOf("*")
            allowedOriginPatterns = listOf("*")
            allowCredentials = true
            allowedMethods = listOf("*")
        }
        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
        return source
    }
}