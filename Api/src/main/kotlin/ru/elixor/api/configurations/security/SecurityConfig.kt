package ru.elixor.api.configurations.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

    companion object {
        private const val GROUPS = "groups"
        private const val REALM_ACCESS_CLAIM = "realm_access"
        private const val ROLES_CLAIM = "roles"
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .cors { }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/links").authenticated()
                    .requestMatchers("/").permitAll()
                    .anyRequest().authenticated()
//                    .hasRole("admin")
            }
            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt { }
                http.oauth2Login { }
                    .logout { logout ->
                        logout
                            .logoutSuccessUrl("http://172.16.2.9:8180/realms/Test/protocol/openid-connect/logout?post_logout_redirect_uri=http://localhost:2000")
                    }
            }
            .build()
    }

    @Bean
    fun userAuthoritiesMapperForKeycloak(): GrantedAuthoritiesMapper {
        return GrantedAuthoritiesMapper { authorities ->
            val mappedAuthorities = HashSet<GrantedAuthority>()
            val authority = authorities.firstOrNull() ?: return@GrantedAuthoritiesMapper emptyList()

            val userInfo = (authority as? OidcUserAuthority)?.userInfo ?: return@GrantedAuthoritiesMapper emptyList()

            val roles = when {
                userInfo.hasClaim(REALM_ACCESS_CLAIM) -> userInfo.getClaimAsMap(REALM_ACCESS_CLAIM)[ROLES_CLAIM] as? Collection<*>
                userInfo.hasClaim(GROUPS) -> userInfo.getClaim<Any>(GROUPS) as? Collection<*>
                else -> null
            }

            roles?.forEach { role ->
                mappedAuthorities.add(SimpleGrantedAuthority("ROLE_$role"))
            }
            mappedAuthorities
        }
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