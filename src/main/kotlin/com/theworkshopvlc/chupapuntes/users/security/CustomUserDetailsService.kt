package com.theworkshopvlc.chupapuntes.users.security

import com.theworkshopvlc.chupapuntes.users.model.entities.mapToSecurity
import com.theworkshopvlc.chupapuntes.users.model.usecases.GetUserByUsername
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
  private val getUserByUsername: GetUserByUsername
) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails =
    getUserByUsername.execute(username).mapToSecurity()
}
