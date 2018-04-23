package com.theworkshopvlc.chupapuntes.users

import com.theworkshopvlc.chupapuntes.users.model.entities.User
import com.theworkshopvlc.chupapuntes.users.persistence.IUserDAO
import com.theworkshopvlc.chupapuntes.users.security.TokenHelper
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(
  private val authenticationManager: AuthenticationManager,
  private val tokenHelper: TokenHelper,
  private val userDAO: IUserDAO
) {

  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  @PostMapping("/login")
  fun login(@RequestBody user: User): ResponseEntity<*> {
    val authentication = authenticationManager.authenticate(
      UsernamePasswordAuthenticationToken(user.username, user.password)
    )
    SecurityContextHolder.getContext().authentication = authentication

    val token = tokenHelper.generateToken(user.username)
    return ResponseEntity.ok(token)
  }

  @PostMapping("/signup")
  fun signup(@RequestBody user: User): ResponseEntity<*> {
    val userWithHashedPassword = user.copy(password = passwordEncoder().encode(user.password))
    userDAO.save(userWithHashedPassword)

    return ResponseEntity.ok().body("User ${user.username} created")
  }

}
