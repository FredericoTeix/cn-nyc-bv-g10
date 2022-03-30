package pt.fcul.keys.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import pt.fcul.keys.model.KeyInfo
import pt.fcul.keys.model.sha256

class ApiAuthentication(
    private val apiKey: String,
    val keyInfo: KeyInfo
) : Authentication {

    private val principal = User.builder()
        .accountExpired(keyInfo.used >= keyInfo.quota)
        .accountLocked(keyInfo.used >= keyInfo.quota)
        .credentialsExpired(false)
        .disabled(false)
        .passwordEncoder { s -> s.sha256() }
        .password(apiKey)
        .username(keyInfo.contact)
        .roles(keyInfo.scope.name)
        .build()

    override fun getName() = "api-key"

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = principal.authorities

    override fun getCredentials(): String = apiKey

    override fun getDetails(): Any? = null

    override fun getPrincipal(): UserDetails = principal

    override fun isAuthenticated(): Boolean = true

    override fun setAuthenticated(isAuthenticated: Boolean) {}

}