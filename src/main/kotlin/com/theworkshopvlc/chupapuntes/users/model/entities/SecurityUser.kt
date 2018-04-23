package com.theworkshopvlc.chupapuntes.users.model.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(
  private val username: String = "",
  private val password: String = "",
  private val roles: List<Authority> = emptyList()
) : UserDetails {
  override fun isAccountNonExpired(): Boolean = true

  override fun isAccountNonLocked(): Boolean = true

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
    roles.toMutableList()

  override fun isEnabled(): Boolean = true

  override fun getUsername(): String = username

  override fun isCredentialsNonExpired(): Boolean = true

  override fun getPassword(): String = password
}

class Authority(val id: Long = 0, val name: String) : GrantedAuthority {
  override fun getAuthority(): String = name
}
